package com.example.miggyisidro.mysterymanilabetademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OngoingActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter2 mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem;
    Button btnNextPage;
    Button btnLogOut;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    String title = "", description = "";

    static ArrayList<String> ongoingList = new ArrayList<>();

    DatabaseReference databaseBooking;
    DatabaseReference databaseGroupDetails;



    //temp
    private TextView test;

    static String bookingID;
    static int count = 0;

    static String asd;

    DatabaseReference databaseGameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        databaseBooking = FirebaseDatabase.getInstance().getReference("bookings");
        databaseGroupDetails = FirebaseDatabase.getInstance().getReference("gameInput");
        databaseGameInput = FirebaseDatabase.getInstance().getReference();



        // btnNextPage = (Button) findViewById(R.id.nextPage);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapter = new RecyclerAdapter2(OngoingActivity.this, myList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        test = findViewById(R.id.ongoing);



        /*
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(BookingsActivity.this, LogInActivity.class));
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(BookingsActivity.this, ViewGameActivity.class);
                startActivity(next);
            }
        });
         */



    }

    public ArrayList getOngoingList(){

        databaseGroupDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    if(!uniqueKeySnapshot.child("bookingID").getValue(String.class).isEmpty() && uniqueKeySnapshot.child("bookingID").getValue(String.class) != null){
                        //test.setText(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                        String s = (String) uniqueKeySnapshot.child("bookingID").getValue();
                        ongoingList.add(s);
                    }
                }


                //Integer size = ongoingList.size();
                //test.setText(size.toString());


                /*
                ongoingList.clear();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    if(!uniqueKeySnapshot.child("bookingID").getValue(String.class).isEmpty() && uniqueKeySnapshot.child("bookingID").getValue(String.class) != null){
                        //test.setText(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                        ongoingList.add(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                    }
                }

                Integer size = ongoingList.size();

                test.setText(size.toString());
                 */


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return ongoingList;
    }

    public void insertRecyclerView(){

        this.getOngoingList();
        test.setText(getOngoingList().size());
        databaseBooking.addValueEventListener(new ValueEventListener()

        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myList.clear();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    for(int i = 0; i < ongoingList.size(); i++){
                        String s = (String) uniqueKeySnapshot.child("transactionID").getValue();
                        if(s.equals(ongoingList.get(i).toString()) && !s.equals(null)){
                            RecyclerData bookings = uniqueKeySnapshot.getValue(RecyclerData.class);
                            myList.add(bookings);
                        }


                    }

                }


                Integer size = ongoingList.size();
                test.setText("hello");


                RecyclerAdapter2 adapter = new RecyclerAdapter2(OngoingActivity.this, myList);
                mRecyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    protected void onStart(){
        super.onStart();

        databaseGroupDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    if(!uniqueKeySnapshot.child("bookingID").getValue(String.class).isEmpty() && uniqueKeySnapshot.child("bookingID").getValue(String.class) != null){
                        //test.setText(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                        String s = (String) uniqueKeySnapshot.child("bookingID").getValue();
                        ongoingList.add(s);
                    }
                }

                asd="asd";

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseBooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer size = ongoingList.size();
                //test.setText(size.toString());

                myList.clear();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    if(ongoingList.contains(uniqueKeySnapshot.child("transactionID").getValue(String.class)))
                    {
                        RecyclerData bookings = uniqueKeySnapshot.getValue(RecyclerData.class);
                        myList.add(bookings);
                        //test.setText(size.toString()+ "PASOK!");
                    }
                }


                //Integer size = ongoingList.size();
                //test.setText(size.toString());
                //test.setText(myList.size());

                RecyclerAdapter2 adapter = new RecyclerAdapter2(OngoingActivity.this, myList);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //working recyclerview only without query





        /*
        databaseBooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myList.clear();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                            RecyclerData bookings = uniqueKeySnapshot.getValue(RecyclerData.class);
                            myList.add(bookings);
                }

                RecyclerAdapter2 adapter = new RecyclerAdapter2(OngoingActivity.this, myList);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         */






        /*
        databaseGroupDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ongoingList.clear();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                    if(!uniqueKeySnapshot.child("bookingID").getValue(String.class).isEmpty() && uniqueKeySnapshot.child("bookingID").getValue(String.class) != null){
                        test.setText(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                        ongoingList.add(uniqueKeySnapshot.child("bookingID").getValue(String.class));
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */








    }
}

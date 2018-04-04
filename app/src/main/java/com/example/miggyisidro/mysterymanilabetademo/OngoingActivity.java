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

    DatabaseReference databaseBooking;
    DatabaseReference databaseGroupDetails;

    //temp
    private TextView test;

    static String bookingID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        databaseBooking = FirebaseDatabase.getInstance().getReference("bookings");
        databaseGroupDetails = FirebaseDatabase.getInstance().getReference("groupDetails");



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

    protected void onStart(){
        super.onStart();

        //pulling from firebase



        databaseGroupDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("gameInputID")) {
                    // get booking ID

                    databaseGroupDetails.child("bookingID").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            bookingID = dataSnapshot.getValue().toString();

                            test.setText(bookingID);



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                    // get booking
                    /*
                    databaseBooking.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(bookingID)){

                                DataSnapshot bookingSnapshot = (DataSnapshot) dataSnapshot.getChildren();
                                RecyclerData bookings = bookingSnapshot.getValue(RecyclerData.class);

                                myList.add(bookings);

                                RecyclerAdapter2 adapter = new RecyclerAdapter2(OngoingActivity.this, myList);
                                mRecyclerView.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                     */

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        databaseBooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myList.clear();

                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    RecyclerData bookings = bookingSnapshot.getValue(RecyclerData.class);

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
    }
}

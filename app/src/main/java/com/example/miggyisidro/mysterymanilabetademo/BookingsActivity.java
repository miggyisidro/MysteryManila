package com.example.miggyisidro.mysterymanilabetademo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BookingsActivity extends Activity implements RemoveClickListner {

    private FirebaseAuth firebaseAuth;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem;
    Button btnNextPage;
    Button btnLogOut;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    String title = "",description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }

        btnNextPage = (Button) findViewById(R.id.nextPage);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapter = new RecyclerAdapter(myList,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        prepareData();

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

        /*
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                description = etDescription.getText().toString();
                if (title.matches("")) {
                    Toast.makeText(v.getContext(), "You did not enter a Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (description.matches("")) {
                    Toast.makeText(v.getContext(), "You did not enter a description", Toast.LENGTH_SHORT).show();
                    return;
                }

                RecyclerData mLog = new RecyclerData();
                mLog.setTitle(title);
                mLog.setDescription(description);
                myList.add(mLog);
                mRecyclerAdapter.notifyData(myList);
                etTitle.setText("");
                etDescription.setText("");
            }
        });
         */


        //etTitle.setText("");
        //etDescription.setText("");
    }

    public void prepareData(){
        RecyclerData mLog = new RecyclerData();
        mLog.setRoomName("Pym Particle");
        mLog.setSchedule("3:00pm-4:30pm");
        mLog.setName("Miggy Isidro");
        mLog.setTransactionID("0001");

        myList.add(mLog);
        mRecyclerAdapter.notifyData(myList);

        RecyclerData mLog1 = new RecyclerData();
        mLog1.setRoomName("Sinister Sensorium");
        mLog1.setSchedule("7:15pm-8:45pm");
        mLog1.setName("Raffy Cañares");
        mLog1.setTransactionID("0002");

        myList.add(mLog1);
        mRecyclerAdapter.notifyData(myList);

        RecyclerData mLog2 = new RecyclerData();
        mLog2.setRoomName("Mutant Mission");
        mLog2.setSchedule("6:15pm-7:45pm");
        mLog2.setName("Albert Einstein");
        mLog2.setTransactionID("2158");

        myList.add(mLog2);
        mRecyclerAdapter.notifyData(myList);

        RecyclerData mLog3 = new RecyclerData();
        mLog3.setRoomName("Rebecca's Revenge");
        mLog3.setSchedule("8:30pm-10:00pm");
        mLog3.setName("Chuck Chang");
        mLog3.setTransactionID("3342");

        myList.add(mLog3);
        mRecyclerAdapter.notifyData(myList);

        RecyclerData mLog4 = new RecyclerData();
        mLog4.setRoomName("Alien Assault");
        mLog4.setSchedule("7:15-8:45");
        mLog4.setName("Pauline Ordenes");
        mLog4.setTransactionID("0033");

        myList.add(mLog4);
        mRecyclerAdapter.notifyData(myList);

    }
    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }
}



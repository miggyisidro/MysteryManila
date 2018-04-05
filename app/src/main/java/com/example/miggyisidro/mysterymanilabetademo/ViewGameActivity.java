package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewGameActivity extends AppCompatActivity {

    private Button nextPage;
    private Spinner gamekeepers ;
    private Spinner groupsize;
    private Spinner numberID;
    private String game;
    private String bookingID;
    private String data;
    private String name;
    private String schedule;
    private String roomName;
    private String holder;
    private TextView display;
    private TextView details;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    List<String> list;
    List<String> groupsizelist;
    List<String> numeberIDlist;


    DatabaseReference databaseGameDetails;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game);

        nextPage = (Button) findViewById(R.id.button3);
        gamekeepers = (Spinner) findViewById(R.id.gameKeeperSpinner);
        groupsize = (Spinner) findViewById(R.id.groupSizeSpinner);
        numberID = (Spinner) findViewById(R.id.IdSpinner);
        display = (TextView) findViewById(R.id.gameNumber);
        details = (TextView) findViewById(R.id.detailsTxt);


        databaseGameDetails = FirebaseDatabase.getInstance().getReference("gameInput");


        bookingID =  getIntent().getStringExtra("bookingID").toString();
        name = getIntent().getStringExtra("name");
        schedule = getIntent().getStringExtra("schedule");
        roomName = getIntent().getStringExtra("roomName");


        holder = roomName + " | " + schedule + " | " + name;

        details.setText(holder);





        display.setText("Game#" + bookingID);



        list = new ArrayList<String>();
        list.add("Veejay");
        list.add("Rexel");
        list.add("Banda");
        list.add("Raffy");
        list.add("Miggy");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gamekeepers.setAdapter(adapter);

        groupsizelist = new ArrayList<String>();
        groupsizelist.add("1");
        groupsizelist.add("2");
        groupsizelist.add("3");
        groupsizelist.add("4");
        groupsizelist.add("5");
        groupsizelist.add("6");
        groupsizelist.add("7");
        groupsizelist.add("8");
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, groupsizelist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupsize.setAdapter(adapter2);

        numeberIDlist = new ArrayList<String>();
        numeberIDlist.add("1");
        numeberIDlist.add("2");
        numeberIDlist.add("3");
        numeberIDlist.add("4");
        numeberIDlist.add("5");
        numeberIDlist.add("6");
        numeberIDlist.add("7");
        numeberIDlist.add("8");
        adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, numeberIDlist);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberID.setAdapter(adapter3);



        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gameInputID = databaseGameDetails.push().getKey();

                String gk = gamekeepers.getSelectedItem().toString();
                String size = groupsize.getSelectedItem().toString();
                // number of members who have IDs is only for the repititions of the loop for the OCR feature
                String id = numberID.getSelectedItem().toString();
                String booking = bookingID;

                // mDatabase.child("users").child(userId).setValue(user);
                databaseGameDetails.child(gameInputID).child("bookingID").setValue(booking);
                databaseGameDetails.child(gameInputID).child("gamekeeper").setValue(gk);
                databaseGameDetails.child(gameInputID).child("groupSize").setValue(size);



                Intent nextPageView = new Intent(ViewGameActivity.this, PaymentActivity.class);
                nextPageView.putExtra("gSize", Integer.parseInt(groupsize.getSelectedItem().toString()));
                nextPageView.putExtra("ocrNo", Integer.parseInt(numberID.getSelectedItem().toString()));
                nextPageView.putExtra("gk",gk);
                nextPageView.putExtra("gameInputID",gameInputID);
                nextPageView.putExtra("size",size);
                nextPageView.putExtra("id",id);
                nextPageView.putExtra("bookingID",booking);
                startActivity(nextPageView);


            }
        });
    }

    private void addDetails(){
        String gk = gamekeepers.getSelectedItem().toString();
        String getGroupSize = groupsize.getSelectedItem().toString();
        String getNumberOfID = numberID.getSelectedItem().toString();

        if(!TextUtils.isEmpty(gk)){

        }
        else{
            Toast.makeText(this, "You should choose a Game Keeper", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(getGroupSize)){

        }
        else{
            Toast.makeText(this, "You should choose a Group Size", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(getNumberOfID)){

        }
        else{
            Toast.makeText(this, "You should choose the number of IDs your group has", Toast.LENGTH_LONG).show();
        }


    }




}

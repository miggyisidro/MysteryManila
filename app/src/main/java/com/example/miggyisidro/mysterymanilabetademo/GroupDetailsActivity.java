package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupDetailsActivity extends AppCompatActivity {

    private Button nextPage;
    private EditText groupName;
    private TextView gameNumber;
    private RadioGroup group;
    private RadioButton radio;
    private CheckBox fb;
    private CheckBox twitter;
    private CheckBox ig;
    private CheckBox friends;
    private ArrayList<String> list;
    private String heard = "";
    private String bookingID;
    private String holder;

    DatabaseReference databaseGroupDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        nextPage = (Button) findViewById(R.id.button);
        group = (RadioGroup) findViewById(R.id.radioGroup);
        groupName = (EditText) findViewById(R.id.groupNameTxt);
        fb = (CheckBox) findViewById(R.id.fbBox);
        ig = (CheckBox) findViewById(R.id.igBox);
        twitter = (CheckBox) findViewById(R.id.twitterBox);
        friends = (CheckBox) findViewById(R.id.friendsBox);
        gameNumber = (TextView) findViewById(R.id.gameNumberGd);


        databaseGroupDetails = FirebaseDatabase.getInstance().getReference("groupDetails");

        bookingID = getIntent().getStringExtra("bookingID").toString();

        holder = "Game# " + bookingID;

        gameNumber.setText(holder);




        nextPage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            int selectedDiscount = group.getCheckedRadioButtonId();

                                            radio = (RadioButton) findViewById(selectedDiscount);

                                            list = new ArrayList<String>();

                                            if (fb.isChecked()==true) {
                                                String a = fb.getText().toString();
                                            list.add(a);
                                            }
                                            if (ig.isChecked()==true) {

                                                String b = ig.getText().toString();
                                                list.add(b);
                                            }
                                            if (twitter.isChecked()==true) {

                                                String c = twitter.getText().toString();
                                                list.add(c);
                                            }
                                            if (friends.isChecked()==true) {

                                                String d = friends.getText().toString();
                                                list.add(d);
                                            }

                                            for(int i = 0; list.size()>i;i++) {
                                                heard = heard + " " + list.get(i).toString();
                                            }



                //insert Group Details
                String id = databaseGroupDetails.push().getKey();
                databaseGroupDetails.child(id).child("firstTime").setValue(radio.getText().toString());
                databaseGroupDetails.child(id).child("groupName").setValue(groupName.getText().toString());
                databaseGroupDetails.child(id).child("socialMedia").setValue(heard.toString());




                Intent next = new Intent(GroupDetailsActivity.this, OCRActivity.class);
                startActivity(next);
            }
        });
    }
}

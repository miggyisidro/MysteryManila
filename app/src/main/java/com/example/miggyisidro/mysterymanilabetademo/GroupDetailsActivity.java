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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupDetailsActivity extends AppCompatActivity {

    private Button nextPage;
    private EditText groupName;
    private RadioGroup group;
    private RadioButton radio;
    private CheckBox fb;
    private CheckBox twitter;
    private CheckBox ig;
    private CheckBox friends;
    private ArrayList<String> list;
    private String heard = "";

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

        databaseGroupDetails = FirebaseDatabase.getInstance().getReference("groupDetails");





        nextPage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            int selectedDiscount = group.getCheckedRadioButtonId();

                                            radio = (RadioButton) findViewById(selectedDiscount);


                                            if (fb.isChecked()==true) {
                                            list.add(fb.getText().toString());
                                            }
                                            if (ig.isChecked()==true) {
                                                list.add(ig.getText().toString());
                                            }
                                            if (twitter.isChecked()==true) {
                                                list.add(twitter.getText().toString());
                                            }
                                            if (friends.isChecked()==true) {
                                                list.add(friends.getText().toString());
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

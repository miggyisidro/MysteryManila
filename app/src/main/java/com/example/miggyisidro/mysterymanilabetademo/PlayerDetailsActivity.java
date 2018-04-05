package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlayerDetailsActivity extends AppCompatActivity {

    Button next;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText Number;
    private EditText city;
    int gSize, ocrNo;
    DatabaseReference databasePlayerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        databasePlayerDetails = FirebaseDatabase.getInstance().getReference("customerDetails");

        next = (Button)findViewById(R.id.next_playerdetails);
        firstName = (EditText) findViewById(R.id.firstNameTxt);
        lastName = (EditText) findViewById(R.id.lastNameTxt);
        email = (EditText) findViewById(R.id.emailTxt);
        Number = (EditText) findViewById(R.id.numberTxt);
        city = (EditText) findViewById(R.id.cityTxt);

        gSize = Integer.parseInt(getIntent().getStringExtra("gSize"));
        ocrNo = Integer.parseInt(getIntent().getStringExtra("ocrNo"));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert to database query
                //firstName.getText().toString();
                //lastName.getText().toString();
                //email.getText().toString();
                //Number.getText().toString();
                //city.getText().toString();

                String customerID = databasePlayerDetails.getKey();

                //sample
                databasePlayerDetails.child(customerID).child("firstName").setValue(firstName.getText().toString());
                databasePlayerDetails.child(customerID).child("lastName").setValue(lastName.getText().toString());
                databasePlayerDetails.child(customerID).child("email").setValue(email.getText().toString());
                databasePlayerDetails.child(customerID).child("city").setValue(city.getText().toString());
                databasePlayerDetails.child(customerID).child("number").setValue(Number.getText().toString());



                if(gSize > 0){
                    Intent refresh = new Intent(PlayerDetailsActivity.this, PlayerDetailsActivity.class);
                    gSize--;
                    refresh.putExtra("gSize",getIntent().getStringExtra("gSize"));
                    startActivity(refresh);
                }else {
                    Intent next = new Intent(PlayerDetailsActivity.this, TermsActivity.class);
                    startActivity(next);
                }
            }
        });
    }


    private void addPlayerDetails(){
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String emailadd = email.getText().toString();
        String phoneNumber = firstName.getText().toString();
        String city = firstName.getText().toString();

        if(!TextUtils.isEmpty(fName)){
                String id = databasePlayerDetails.push().getKey();
                databasePlayerDetails.child(id).setValue(fName,lastName);
        }
        else{
            Toast.makeText(this, "You should enter your First name", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(lName)){

        }
        else{
            Toast.makeText(this, "You should enter your Last Name", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(emailadd)){

        }
        else{
            Toast.makeText(this, "You should enter an email address", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(phoneNumber)){

        }
        else{
            Toast.makeText(this, "You should enter a Phone Number", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(city)){

        }
        else{
            Toast.makeText(this, "You should enter a City", Toast.LENGTH_LONG).show();
        }


    }

}

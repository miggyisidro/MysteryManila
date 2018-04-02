package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerDetailsActivity extends AppCompatActivity {

    Button next;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText Number;
    private EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        next = (Button)findViewById(R.id.next_playerdetails);
        firstName = (EditText) findViewById(R.id.firstNameTxt);
        lastName = (EditText) findViewById(R.id.lastNameTxt);
        email = (EditText) findViewById(R.id.emailTxt);
        Number = (EditText) findViewById(R.id.numberTxt);
        city = (EditText) findViewById(R.id.cityTxt);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(PlayerDetailsActivity.this, TermsActivity.class);
                startActivity(next);
            }
        });
    }
}

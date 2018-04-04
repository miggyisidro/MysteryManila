package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PostGame extends AppCompatActivity {



    private String holder;
    private String bookingID;
    private int newminute;
    private int penalty;
    private int hourstominutes;
    private int totalseconds;
    private String choice;
    private TextView gameNumber;
    private EditText clues;
    private EditText hours;
    private EditText minutes;
    private EditText seconds;
    private Button next;
    private RadioGroup solved;
    private RadioButton radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);


        gameNumber = (TextView) findViewById(R.id.gameNumberTxtPost);
        clues = (EditText) findViewById(R.id.clueTxt);
        hours = (EditText) findViewById(R.id.hours);
        minutes = (EditText) findViewById(R.id.minutes);
        seconds = (EditText) findViewById(R.id.seconds);
        next = (Button) findViewById(R.id.nextToTime);
        solved =(RadioGroup) findViewById(R.id.solved);

        bookingID = getIntent().getStringExtra("bookingID");

        holder = "Game# " + bookingID;

        gameNumber.setText(holder);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int check = solved.getCheckedRadioButtonId();

                radio = (RadioButton) findViewById(check);

                choice = radio.getText().toString();

                if(choice == "No") {

                }else
                {
                    penalty = Integer.parseInt(clues.getText().toString()) * 5;
                    hourstominutes = Integer.parseInt(hours.getText().toString())*60;
                    newminute = Integer.parseInt(minutes.getText().toString()) + penalty + hourstominutes;
                    totalseconds = newminute * 60 + Integer.parseInt(seconds.getText().toString());

                        if(totalseconds < 1800)
                        {
                            Intent i = new Intent(PostGame.this,freeItems.class);
                            startActivity(i);

                        }else if(totalseconds <2700)
                        {
                            Intent i = new Intent(PostGame.this,freeItems.class);
                            startActivity(i);
                        }else
                        {

                        }

                }




            }
        });










    }
}

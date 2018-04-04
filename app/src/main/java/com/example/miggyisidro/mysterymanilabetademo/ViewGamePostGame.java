package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewGamePostGame extends AppCompatActivity{

    private String holderheader;
    private String detailsholder;
    private String schedule;
    private String roomname;
    private String name;
    private String game;
    private TextView gameNumber;
    private TextView details;
    private TextView OS;
    private TextView gameKeeper;
    private TextView groupSize;
    private Button next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game_post_game);


    gameNumber = (TextView) findViewById(R.id.gameNumberpost);
    details = (TextView) findViewById(R.id.detailsTxtPost);
    //os = (TextView) findViewById(R.id.osTxt);
        //gameKeeper = (TextView) findViewById(R.id.gkTxtPost);
        groupSize = (TextView) findViewById(R.id.grTxtPost);
        next = (Button) findViewById(R.id.nextPost);

        schedule = getIntent().getStringExtra("schedulePost");
        roomname = getIntent().getStringExtra("roomNamePost");
        name = getIntent().getStringExtra("name");
        game = getIntent().getStringExtra("bookingIDPost");

        holderheader = "Game# " + game;
        detailsholder = roomname + " | " + schedule + " | " + name;

                gameNumber.setText(holderheader);
                details.setText(detailsholder);

      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent nextPageView = new Intent(ViewGamePostGame.this, PostGame.class);
              nextPageView.putExtra("bookingID", game);
              //nextPageView.putExtra("groupSize",groupSize)
              startActivity(nextPageView);

          }
      });


        }
    }



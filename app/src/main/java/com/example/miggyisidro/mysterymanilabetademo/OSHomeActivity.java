package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class OSHomeActivity extends AppCompatActivity {

    private ImageButton bookingsImageButton;
    private ImageButton ongoingsImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oshome);

        bookingsImageButton = (ImageButton) findViewById(R.id.bookingImageButton);
        ongoingsImageButton = (ImageButton) findViewById(R.id.ongoingImageButton);

        bookingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBookings = new Intent(OSHomeActivity.this, BookingsActivity.class);
                startActivity(goToBookings);
            }
        });

        ongoingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToOngoing = new Intent(OSHomeActivity.this, OngoingActivity.class);
                startActivity(goToOngoing);
            }
        });
    }
}

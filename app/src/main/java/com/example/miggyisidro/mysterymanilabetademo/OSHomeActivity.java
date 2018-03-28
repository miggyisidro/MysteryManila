package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OSHomeActivity extends AppCompatActivity {

    private ImageView bookingsImageButton;
    private ImageView ongoingsImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oshome);

        bookingsImageButton = (ImageView) findViewById(R.id.bookingImageButton);
        ongoingsImageButton = (ImageView) findViewById(R.id.ongoingImageButton);

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
                Intent goToOngoing = new Intent(OSHomeActivity.this, BookingsActivity.class);
                startActivity(goToOngoing);
            }
        });
    }
}

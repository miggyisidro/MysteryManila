package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class OCRActivity extends AppCompatActivity {

    Button next;
    EditText ocr;

    ImageView imageView;
    Button btnCamera;

    Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        next = (Button) findViewById(R.id.ocr_next);
        ocr = (EditText) findViewById(R.id.ocr);

        skip = (Button) findViewById(R.id.button6);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBookings = new Intent(OCRActivity.this, BookingsActivity.class);
                startActivity(goToBookings);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change -- Direct to OS_HOME
                Intent next = new Intent(OCRActivity.this, PlayerDetailsActivity.class);
              
                startActivity(next);
            }
        });

    }
}

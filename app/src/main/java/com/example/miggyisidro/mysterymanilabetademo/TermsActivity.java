package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TermsActivity extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        next = (Button) findViewById(R.id.submitTerms);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change direct to OS HOME
                Intent next = new Intent(TermsActivity.this, OCRActivity.class);
                startActivity(next);
            }
        });
    }
}

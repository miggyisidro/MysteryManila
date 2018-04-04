package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class TermsActivity extends AppCompatActivity {

    Button next;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        next = (Button) findViewById(R.id.submitTerms);
        check = (CheckBox) findViewById(R.id.termsCheck);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change direct to OS HOME

                if(check.isChecked()) {
                    Intent next = new Intent(TermsActivity.this, OSHomeActivity.class);
                    startActivity(next);
                }else
                {
                    Toast.makeText(TermsActivity.this, "You did not agree to the terms", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}

package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupDetailsActivity extends AppCompatActivity {

    private Button nextPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        nextPage = (Button) findViewById(R.id.button);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GroupDetailsActivity.this, OCRActivity.class);
                startActivity(next);
            }
        });
    }
}

package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GroupDetailsActivity extends AppCompatActivity {

    private Button nextPage;
    private EditText groupName;
    private RadioGroup group;
    private RadioButton radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        nextPage = (Button) findViewById(R.id.button);
        group = (RadioGroup) findViewById(R.id.radioGroup);


        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedDiscount = group.getCheckedRadioButtonId();

                radio = (RadioButton) findViewById(selectedDiscount);

                Intent next = new Intent(GroupDetailsActivity.this, OCRActivity.class);
                startActivity(next);
            }
        });
    }
}

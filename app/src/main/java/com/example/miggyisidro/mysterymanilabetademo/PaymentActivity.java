package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private Button nextPage;
    private EditText payment;
    private TextView gameNumber;
    private Spinner status;
    ArrayAdapter<String> adapter;
    List<String> list;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String id;
    private String gk;
    private String size;
    private String bookingID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        nextPage = (Button) findViewById(R.id.button2);
        payment =(EditText) findViewById(R.id.amoutTxt);
        gameNumber = (TextView) findViewById(R.id.gameNumber);
        status = (Spinner) findViewById (R.id.statusSpinner);
        radioGroup = (RadioGroup) findViewById(R.id.groupdiscount);


        list = new ArrayList<String>();
        list.add("PAID: FULLY PAID");
        list.add("PAID: WITH ADDITIONAL");
        list.add("PEND: FULLY PIAD");
        list.add("PEND; PARTIALLY PAID");
        list.add("PEND: CASH");
        list.add("FREE: GAME");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        id = getIntent().getStringExtra("id");
        size = getIntent().getStringExtra("size");
        gk = getIntent().getStringExtra("gk");
        bookingID = getIntent().getStringExtra("bookingID");





        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedDiscount = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedDiscount);



                Intent next = new Intent(PaymentActivity.this, GroupDetailsActivity.class);

                startActivity(next);
            }
        });
    }



}

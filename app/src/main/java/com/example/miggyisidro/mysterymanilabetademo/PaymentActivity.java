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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private String gameInputID;
    private String gameNumberText;
    int gSize, ocrno;

    DatabaseReference databasePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        nextPage = (Button) findViewById(R.id.button2);
        payment =(EditText) findViewById(R.id.amoutTxt);
        gameNumber = (TextView) findViewById(R.id.gameNumberPayment);
        status = (Spinner) findViewById (R.id.statusSpinner);
        radioGroup = (RadioGroup) findViewById(R.id.groupdiscount);




        list = new ArrayList<String>();
        list.add("PAID: FULLY PAID");
        list.add("PAID: WITH ADDITIONAL");
        list.add("PEND: FULLY PAID");
        list.add("PEND; PARTIALLY PAID");
        list.add("PEND: CASH");
        list.add("FREE: GAME");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        id = getIntent().getStringExtra("id").toString();
        bookingID = getIntent().getStringExtra("bookingID").toString();
        size = getIntent().getStringExtra("size");

        gameInputID = getIntent().getStringExtra("gameInputID");

        gameNumberText = "Game# " + bookingID;

        gameNumber.setText(gameNumberText);




        databasePayment = FirebaseDatabase.getInstance().getReference("gameInput");


        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedDiscount = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedDiscount);




                databasePayment.child(gameInputID).child("cashPaymentAmount").setValue(payment.getText().toString());
                databasePayment.child(gameInputID).child("paymentStatus").setValue(status.getSelectedItem().toString());
                databasePayment.child(gameInputID).child("discount").setValue(radioButton.getText().toString());



                Intent next = new Intent(PaymentActivity.this, GroupDetailsActivity.class);
                next.putExtra("gSize", getIntent().getStringExtra("gSize"));
                next.putExtra("ocrNo", getIntent().getStringExtra("ocrNo"));
                next.putExtra("bookingID", bookingID);

                startActivity(next);
            }
        });
    }



}

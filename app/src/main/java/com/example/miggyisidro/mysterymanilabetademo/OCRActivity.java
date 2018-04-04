package com.example.miggyisidro.mysterymanilabetademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.google.cloud.vision.v1.Feature.Type.TEXT_DETECTION;

public class OCRActivity extends AppCompatActivity {

    Button next;
    EditText ocr;
    Button hello;
    ImageView imageView;
    Button btnCamera;
    TextView ocrView;
    Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        next = (Button) findViewById(R.id.ocr_next);
        ocr = (EditText) findViewById(R.id.ocr);
        ocrView = (TextView) findViewById(R.id.textView15);
        skip = (Button) findViewById(R.id.button6);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        imageView = (ImageView) findViewById(R.id.imageView);

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

        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent("android.provider.MediaStore.ACTION_IMAGE_CAPTURE");
                startActivity(cameraIntent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePhoto, 0);

        }


    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

    }




}

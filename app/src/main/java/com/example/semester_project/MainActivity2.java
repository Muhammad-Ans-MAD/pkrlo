package com.example.semester_project;



import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

public class MainActivity2 extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    TextView detectedText;
    Button btn_detect, phone;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;


    private static final int GalleryPick = 1;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Card Loader");

        //click = findViewById(R.id.click);
        imageView = (ImageView) findViewById(R.id.img);
        //detectedText = (TextView) findViewById(R.id.text);
        btn_detect = (Button) findViewById(R.id.btn);
        phone = (Button) findViewById(R.id.btn1);
        radioGroup=(RadioGroup)findViewById(R.id.radiogrp);
        radioButton1=(RadioButton)findViewById(R.id.jazz);
        radioButton2=(RadioButton)findViewById(R.id.ufone);
        radioButton3=(RadioButton)findViewById(R.id.Zong);
        radioButton4=(RadioButton)findViewById(R.id.telenor);
        // detectedText.getTextSize()
        showImagePicDialog();
//
        // allowing permissions of camera
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        //dispatchTakePictureIntent();

        btn_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });
        //RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(radioButton1.isChecked())
                {

//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                    String number;
//                    number="*123*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:" + number));
//                    startActivity(intent);
//                    call();
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String a;
                    a=phone.getText().toString();

                    StringBuilder number=new StringBuilder();

//                    number="*123*"+phone+"#";
//                    number=
                    //number="*123*"+phone.getText().toString()+"#".trim();
                    number=number.append("*123*");
                    number=number.append(a);
                    number=number.append("%23");

                    // Toast.makeText(getApplicationContext(),""+number,Toast.LENGTH_LONG).show();
                    Uri newuri = Uri.parse("tel:"+number.toString());
                    intent.setData(newuri);

                    startActivity(intent);
                    call();
                }
                if (radioButton2.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String a;
                    a=phone.getText().toString();
                    StringBuilder number1=new StringBuilder();
                    number1=number1.append("*123*");
                    number1=number1.append(a);
                    number1=number1.append("%23");
                    Uri newuri = Uri.parse("tel:"+number1.toString());
                    intent.setData(newuri);
//                    String number1;
//                    number1="*123*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:"+number1));
                    startActivity(intent);
                    call();
                }
                if (radioButton3.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String a;
                    a=phone.getText().toString();
                    StringBuilder number2=new StringBuilder();
                    number2=number2.append("*101*");
                    number2=number2.append(a);
                    number2=number2.append("%23");
                    Uri newuri = Uri.parse("tel:"+number2.toString());
                    intent.setData(newuri);
                    startActivity(intent);
                    call();
                }
                if (radioButton4.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String a;
                    a=phone.getText().toString();
                    StringBuilder number3=new StringBuilder();
                    number3=number3.append("*555*");
                    number3=number3.append(a);
                    number3=number3.append("%23");
                    Uri newuri = Uri.parse("tel:"+number3.toString());
                    intent.setData(newuri);

                    startActivity(intent);
                    call();
                }

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity2.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {

                }
            }
        });
    }



    public void detect()  //yes
    {
        //perform text detection here

        //1. define TextRecognizer
        TextRecognizer recognizer=new TextRecognizer.Builder(MainActivity2.this).build();

        //2. Get bitmap from imageview
        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();

        //3. get frame from bitmap
        Frame frame=new Frame.Builder().setBitmap(bitmap).build();

        //4. get data from frame
        SparseArray<TextBlock> sparseArray=recognizer.detect(frame);

        //sparseArray=20;
        //5. set data on textview

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<sparseArray.size(); i++)
        {
            TextBlock tx=sparseArray.get(i);
            String str=tx.getValue();
            // stringBuilder.setLength(1);
            stringBuilder.append(str);
        }

        try {

            //detectedText.setText(stringBuilder);
            phone.setText(stringBuilder);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"OOPs something went wrong",Toast.LENGTH_SHORT).show();
        }

    }
    public  void call()
    {

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(MainActivity2.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
            requestpermission();
        }
        else
        {

        }
    }
    private void requestpermission()
    {
        ActivityCompat.requestPermissions(MainActivity2.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    //crop image

    private void showImagePicDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        pickFromGallery();

    }



    // checking storage permissions
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // Requesting  gallery permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    // Requesting camera permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    // Requesting camera and gallery
    // permission if not given
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accepted && writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Camera Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }

    // Here we will pick image from gallery or camera
    private void pickFromGallery() {
        CropImage.activity().start(MainActivity2.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(imageView);
            }
        }
    }
}







package com.example.aitech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Page1 extends AppCompatActivity {
    Button nxtbtn;
    LinearLayout filepicker;
    TextView filename;
    Spinner algospinner;
    String[] Algorithems= {"Logistic Regression","Decision Tree","Support Vector Machine(SVM)","K-Nearest Neighbours(KNN)"};
    private String selecteditem;
    ImageView uploadimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        nxtbtn=findViewById(R.id.nxtbtn);
        filepicker=findViewById(R.id.filepicker);
        filename=findViewById(R.id.filename);
        algospinner=findViewById(R.id.AlgoSpinner);
        uploadimg=findViewById(R.id.uploadimg);

        ArrayAdapter<String> Algo= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Algorithems);
        Algo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algospinner.setAdapter(Algo);


        filepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showfilechooser();


            }
        });


        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Page1.this, Page2.class);
                selecteditem=algospinner.getSelectedItem().toString().trim();
                intent.putExtra("selectedItem",selecteditem);
                startActivity(intent);
            };
        });
    }
    private void showfilechooser(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent,"Select A File"),100);
        }
        catch (Exception e){
            Toast.makeText(this,"Please Download File Manager", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String path = uri.getPath();
            File file= new File(path);
            filename.setText("Path:"+path+"\n"+"\n"+ "File Name: "+file.getName());
            uploadimg.setImageResource(R.drawable.checked);
        }
    }
}
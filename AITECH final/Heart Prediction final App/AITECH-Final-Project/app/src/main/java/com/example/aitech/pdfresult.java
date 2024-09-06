package com.example.aitech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class pdfresult extends AppCompatActivity {

    PDFView analysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfresult);

        analysis=findViewById(R.id.pdfresult);

        analysis.fromAsset("DataVisualization.pdf").load();
    }
}
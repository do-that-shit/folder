package com.example.aitech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class output extends AppCompatActivity {

    TextView details,title,output;
    private String Selected,prediction;
    Button analysbtn;

    String logisticRegression = "● Logistic Regression models the probability of a binary outcome using a logistic function.\n● It's a linear model that uses a logistic function to model the probability of a binary outcome.\n● It's simple, interpretable, and efficient for linearly separable data.\n● It's widely used in various fields such as healthcare, marketing, and finance.";
    String decisionTree = "● Decision Tree recursively splits the data based on the features to create a tree-like structure.\n● It's a non-parametric model that can handle both numerical and categorical data.\n● It's easy to understand and visualize, and it can handle non-linear relationships well.\n● It's used in various fields such as healthcare, finance, and customer relationship management.";
    String supportVectorMachine = "● Support Vector Machine (SVM) finds the hyperplane that best separates the classes in a high-dimensional space.\n● It's effective in high-dimensional spaces and is memory efficient.\n● It's widely used in various fields such as image recognition, text classification, and bioinformatics.\n● It can handle both linear and non-linear data.";
    String kNearestNeighbors = "● K-Nearest Neighbors (KNN) classifies data points based on the majority class of their k-nearest neighbors.\n● It's a simple, non-parametric model that can handle multi-class classification.\n● It's easy to understand and implement, and it can handle noisy data well.\n● It's used in various fields such as recommendation systems, anomaly detection, and pattern recognition.";

    String[] Algorithems= {"Logistic Reggeration","Descision Tree","Support Vector Machine(SVM)","K-Nearest Neighbours(KNN)"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

         title=findViewById(R.id.algotitle);
        output=findViewById(R.id.output);
         details= findViewById(R.id.details);
         analysbtn=findViewById(R.id.analysbtn);

        Selected= getIntent().getStringExtra("selectedItem").toString().trim();
        prediction=getIntent().getStringExtra("Output").toString().trim();

        analysbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(output.this, pdfresult.class));
            }
        });

        title.setText(Selected);
        output.setText("Prediction : \n"+prediction);

        // Get the selected algorithm (for example, from a spinner or radio button)
        if (Selected.equals("Logistic Regression")) {
            details.setText(logisticRegression);
        } else if (Selected.equals("Decision Tree")) {
            details.setText(decisionTree);
        } else if (Selected.equals("Support Vector Machine(SVM)")) {
            details.setText(supportVectorMachine);
        } else if (Selected.equals("K-Nearest Neighbours(KNN)")) {
            details.setText(kNearestNeighbors);
        }

    }

    public void back(View view) {
        finish();
    }
}
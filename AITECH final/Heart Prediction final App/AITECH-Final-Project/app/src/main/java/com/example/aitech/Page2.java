package com.example.aitech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aitech.ml.KnnModel;
import com.example.aitech.ml.LogisticRegressionModel;
import com.example.aitech.ml.SvmModel;
import com.google.android.material.textfield.TextInputEditText;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Page2 extends AppCompatActivity {
    Button predict;
    TextInputEditText Age,Gender,RestingBP,Cholesterol,RestingECG,MaxHR;

//    TextView result;
    private String Selected,prediction;

    private TextView selectedItem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Age = (TextInputEditText)findViewById(R.id.Age);
        Gender = (TextInputEditText)findViewById(R.id.Gender);
        RestingBP = (TextInputEditText) findViewById(R.id.RestingBP);
        Cholesterol = (TextInputEditText) findViewById(R.id.Cholesterol);
        RestingECG = (TextInputEditText) findViewById(R.id.RestingECG);
        MaxHR = (TextInputEditText) findViewById(R.id.MaxHR);

        Selected= getIntent().getStringExtra("selectedItem").toString().trim();
        selectedItem=findViewById(R.id.slectedalgo);
        selectedItem.setText(Selected);

//        predict = findViewById(R.id.predict);
////        result = findViewById(R.id.result);
//        predict.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Page2.this, "hi", Toast.LENGTH_LONG);
//
//
//            }
//
//        });

        Toast.makeText(Page2.this, "hi" , Toast.LENGTH_LONG);

    }

    public void Predict(View view) {

        float age = Float.parseFloat(Age.getText().toString());
        float gender = Float.parseFloat(Gender.getText().toString());
        float bp = Float.parseFloat(RestingBP.getText().toString());
        float chol = Float.parseFloat(Cholesterol.getText().toString());
        float ecg = Float.parseFloat(RestingECG.getText().toString());
        float hr = Float.parseFloat(MaxHR.getText().toString());

        // Allocate a direct ByteBuffer with the appropriate size and byte order
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * Float.SIZE / 8).order(ByteOrder.nativeOrder());

// Put the float values into the ByteBuffer
/*        byteBuffer.putFloat(40.0f); // Age
        byteBuffer.putFloat(1.0f);  // Sex (assuming 1 for male and 0 for female)
        byteBuffer.putFloat(140.0f); // Resting BP
        byteBuffer.putFloat(289.0f); // Cholesterol
        byteBuffer.putFloat(0.0f);   // Resting ECG (assuming 0 for normal)
        byteBuffer.putFloat(172.0f); // Max HR
 */
        byteBuffer.putFloat(age); // Age
        byteBuffer.putFloat(gender);  // Sex (assuming 1 for male and 0 for female)
        byteBuffer.putFloat(bp); // Resting BP
        byteBuffer.putFloat(chol); // Cholesterol
        byteBuffer.putFloat(ecg);   // Resting ECG (assuming 0 for normal)
        byteBuffer.putFloat(hr); // Max HR

// Rewind the ByteBuffer to the beginning
        byteBuffer.rewind();
        if (Selected.equals("Logistic Regression")) {
            try {
                LogisticRegressionModel model = LogisticRegressionModel.newInstance(getApplicationContext());


                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 6}, DataType.FLOAT32);
                inputFeature0.loadBuffer(byteBuffer);

                // Runs model inference and gets result.
                LogisticRegressionModel.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                float heartDisease = outputFeature0.getFloatValue(0);
                if (heartDisease >= 0.5) {
                    prediction = "Heart disease is present";
                } else {
                    prediction = "Heart disease is not present";
                }

                // Display the prediction
                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }

        } else if (Selected.equals("Decision Tree")) {
            //left to code
            try {
                LogisticRegressionModel model = LogisticRegressionModel.newInstance(getApplicationContext());


                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 6}, DataType.FLOAT32);
                inputFeature0.loadBuffer(byteBuffer);

                // Runs model inference and gets result.
                LogisticRegressionModel.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                float heartDisease = outputFeature0.getFloatValue(0);
                if (heartDisease >= 0.5) {
                    prediction = "Heart disease is present";
                } else {
                    prediction = "Heart disease is not present";
                }

                // Display the prediction
                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }
//left to code because not able to genrate the decision tree algorithm in the tensorflow lite format

        } else if (Selected.equals("Support Vector Machine(SVM)")) {
            try {
                SvmModel model = SvmModel.newInstance(getApplicationContext());

                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 6}, DataType.FLOAT32);
                inputFeature0.loadBuffer(byteBuffer);

                // Runs model inference and gets result.
                SvmModel.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                float heartDisease = outputFeature0.getFloatValue(0);
                if (heartDisease >= 0.5) {
                    prediction = "Heart disease is present";
                } else {
                    prediction = "Heart disease is not present";
                }
                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }



        } else if (Selected.equals("K-Nearest Neighbours(KNN)")) {
            try {
//                LogisticRegressionModel model = LogisticRegressionModel.newInstance(getApplicationContext());
                KnnModel model = KnnModel.newInstance(getApplicationContext());


                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 6}, DataType.FLOAT32);
                inputFeature0.loadBuffer(byteBuffer);

                // Runs model inference and gets result.
                LogisticRegressionModel.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                float heartDisease = outputFeature0.getFloatValue(0);
                if (heartDisease >= 0.5) {
                    prediction = "Heart disease is present";
                } else {
                    prediction = "Heart disease is not present";
                }

                // Display the prediction
                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }
//left to code because not able to genrate the K-Nearest Neighbours(KNN) algorithm in the tensorflow lite format

        }

        Selected= getIntent().getStringExtra("selectedItem").toString().trim();

        Intent intent= new Intent(Page2.this, output.class);
        intent.putExtra("Output",prediction);
        intent.putExtra("selectedItem",Selected);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}
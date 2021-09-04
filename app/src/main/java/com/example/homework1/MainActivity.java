/*
Homework1
MainActivity.java
Mariann Szabo-Freund

Group 7
9/1/2021

 */


package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.BlockingDeque;


public class MainActivity extends AppCompatActivity {
    final String TAG = "demo";
    EditText billTotal;
    TextView seekBarProgress;
    TextView tipValue;
    TextView totalValue;
    TextView perPerson;
    RadioGroup percentGroup;
    SeekBar seekBar;
    RadioGroup splitByGroup;
    Button clearButton;
    String billTotalString;
    double tipAmnt;
    double billAmnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = findViewById(R.id.editTextNumberDecimalTotalBill);
        seekBarProgress = findViewById(R.id.textViewPercentDisplay);
        tipValue = findViewById(R.id.textViewTipDisplay);
        totalValue = findViewById(R.id.textViewTotalDisplay);
        perPerson = findViewById(R.id.textViewTotalPersonDisplay);
        percentGroup = findViewById(R.id.radioGroup2);
        seekBar = findViewById(R.id.seekBar);
        splitByGroup = findViewById(R.id.radioGroup3);
        clearButton = findViewById(R.id.buttonClear);

        percentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                calculateAndDisplay();
            }
        });
        splitByGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                calculateAndDisplay();
            }
        });

        billTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAndDisplay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
            }
        });



    }
    public void calculateAndDisplay(){


        billTotalString = billTotal.getText().toString();




        double billTip = 0;
        if( billTotalString.equals("")){
            billAmnt=0;
        }else {
            billAmnt = Double.parseDouble(billTotalString);
        }

        int i = percentGroup.getCheckedRadioButtonId();

        if(i == R.id.radioButton10){
            double tipD = 0.1;
            tipAmnt = billAmnt * tipD;


            tipValue.setText("$"+tipAmnt);
            totalValue.setText("$"+(billAmnt+tipAmnt));


        }else if(i == R.id.radioButton15){
            double tipD = 0.15;
            double tipAmnt = billAmnt * tipD;


            tipValue.setText("$"+(billAmnt * tipD));
            totalValue.setText("$"+(billAmnt+tipAmnt));



        }else if(i == R.id.radioButton18){
            double tipD = 0.18;
            double tipAmnt = billAmnt * tipD;


            tipValue.setText("$"+(billAmnt * tipD));
            totalValue.setText("$"+(billAmnt+tipAmnt));


        }else if(i == R.id.radioButtonCustom){

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    seekBarProgress.setText(String.valueOf(i));
                    double tipD = (Double.parseDouble(String.valueOf(i)))/100;
                    Log.d(TAG, String.valueOf(tipD));
                    double tipAmnt = billAmnt * tipD;


                    tipValue.setText("$"+(billAmnt * tipD));
                    totalValue.setText("$"+(billAmnt+tipAmnt));




                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }

        int y = splitByGroup.getCheckedRadioButtonId();


        if(y == R.id.radioButtonSplit1){
            Log.d(TAG, totalValue.getText().toString());
            //String text = totalValue.getText().toString();
            //Log.d(TAG, "text "+ text);
            perPerson.setText("$"+(billAmnt+tipAmnt));

        }else if (y == R.id.radioButtonSplit2){
            Log.d(TAG, "the bill and tip amt are "+billAmnt+" "+tipAmnt);
            double split = billAmnt+tipAmnt;
            double splitV = split/2;
            perPerson.setText("$"+splitV);




            Log.d(TAG, totalValue.getText().toString());


        }else if (y == R.id.radioButtonSplit3){
            Log.d(TAG, totalValue.getText().toString());



        }else if (y == R.id.radioButtonSplit4){
            Log.d(TAG, totalValue.getText().toString());



        }

    }
}


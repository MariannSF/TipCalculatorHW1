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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
final String TAG = "demo";
    EditText editTextTotalBill;
    TextView textViewTipDisplay;
    TextView textViewSBpercentD;
    SeekBar seekBar;
    TextView textViewTotalDisplay;
    TextView textViewPerson;
    double tipP;
    double total;
    double person;
    double tipTotal;
    double tip;
    /*
    To do:
    - When user modifies input update calculations
    - Activate Clear button
    - if total bill is empty then tip and total/person should be set to $0.0
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RadioGroup radioGroupTip = findViewById(R.id.radioGroup2);
        //RadioGroup radioGroupSplit = findViewById(R.id.radioGroup3);
        editTextTotalBill= findViewById(R.id.editTextNumberDecimalTotalBill);
        textViewTipDisplay = findViewById(R.id.textViewTipDisplay);
        textViewSBpercentD = findViewById(R.id.textViewPercentDisplay);
        seekBar = findViewById(R.id.seekBar);
        textViewTotalDisplay = findViewById(R.id.textViewTotalDisplay);
        textViewPerson = findViewById(R.id.textViewTotalPersonDisplay);


        editTextTotalBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tip = total*.1;
                tipTotal = total+tip;
                person = tipTotal/1;
                calculateAndDisplay();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));

                double tip = (total/100)*10;
                calculateAndDisplay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        findViewById(R.id.radioButton10).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tipP = 0.1;
                calculateAndDisplay();

            }
        });
        findViewById(R.id.radioButton15).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tipP = 0.15;
                calculateAndDisplay();

            }
        });
        findViewById(R.id.radioButton18).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tipP = 0.18;
                calculateAndDisplay();

            }
        });
        findViewById(R.id.radioButtonCustom).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        textViewSBpercentD.setText(String.valueOf(i));
                        tipP = (Double.parseDouble(String.valueOf(i))/100);
                        Log.d(TAG, String.valueOf(i));
                        calculateAndDisplay();

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });


            }
        });
        findViewById(R.id.radioButtonSplit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              person= tipTotal/1;
                calculateAndDisplay();
                Log.d(TAG, "split is "+ person);



            }
        });
        findViewById(R.id.radioButtonSplit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                person= tipTotal/2;
                calculateAndDisplay();
                Log.d(TAG, "split is "+ person);


            }
        });
        findViewById(R.id.radioButtonSplit3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                person= tipTotal/3;
                calculateAndDisplay();
                Log.d(TAG, "split is "+ person);


            }
        });
        findViewById(R.id.radioButtonSplit4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                person= tipTotal/4;
                Log.d(TAG, "split is "+ person);
                calculateAndDisplay();
            }
        });



    }
    public void calculateAndDisplay(){
        //get the bill

        try {
            total = Double.parseDouble(editTextTotalBill.getText().toString());
            Log.d(TAG, "from Calcualte "+total);
            tip = total*tipP;
            tipTotal = total+tip;

            Log.d(TAG, "Input is "+editTextTotalBill.getText().toString());
            Log.d(TAG, "Tip is "+textViewTipDisplay.getText().toString());
            Log.d(TAG, "Total is "+textViewTotalDisplay.getText().toString());
            Log.d(TAG, "Per Person "+textViewPerson.getText().toString());

            textViewTipDisplay.setText("$"+String.valueOf(tip));
            textViewTotalDisplay.setText("$"+String.valueOf(tipTotal));
            //textViewPerson.setText("$"+String.valueOf(tipTotal));
            textViewPerson.setText("$"+String.valueOf(person));
            //get the tip
            //calculate the tip
            //calculate the total
            //calculate split amount
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}


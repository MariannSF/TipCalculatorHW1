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

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));

                double tip = (total/100)*10;
                double tipTotal = total+tip;

                Log.d(TAG, editTextTotalBill.getText().toString());
                textViewTipDisplay.setText(String.valueOf(tip));
                textViewTotalDisplay.setText(String.valueOf(tipTotal));
                textViewPerson.setText(String.valueOf(tipTotal));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        findViewById(R.id.radioButton10).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));
                tipP = 0.1;

                double tip = total*tipP;
                double tipTotal = total+tip;

                Log.d(TAG, editTextTotalBill.getText().toString());
                textViewTipDisplay.setText(String.valueOf(tip));
                textViewTotalDisplay.setText(String.valueOf(tipTotal));
                textViewPerson.setText(String.valueOf(tipTotal));


            }
        });
        findViewById(R.id.radioButton15).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));
                tipP = 0.15;
                double tip = total*tipP;
                double tipTotal = total+tip;

                Log.d(TAG, editTextTotalBill.getText().toString());
                textViewTipDisplay.setText(String.valueOf(tip));
                textViewTotalDisplay.setText(String.valueOf(tipTotal));
                textViewPerson.setText(String.valueOf(tipTotal));




            }
        });
        findViewById(R.id.radioButton18).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));
                tipP = 0.18;
                double tip = total*tipP;
                double tipTotal = total+tip;

                Log.d(TAG, editTextTotalBill.getText().toString());
                textViewTipDisplay.setText(String.valueOf(tip));
                textViewTotalDisplay.setText(String.valueOf(tipTotal));
                textViewPerson.setText(String.valueOf(tipTotal));



            }
        });
        findViewById(R.id.radioButtonCustom).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /*double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));

                double tip = (total/100)*40;
                double tipTotal = total+tip;
                textViewTipDisplay.setText(String.valueOf(tipTotal));
                textViewTotalDisplay.setText(String.valueOf(tipTotal));
                textViewPerson.setText(String.valueOf(tipTotal));
                textViewSBpercentD.setText(String.valueOf(40));
*/
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        textViewSBpercentD.setText(String.valueOf(i));
                        double total = Double.parseDouble(String.valueOf(editTextTotalBill.getText()));
                        //tipP = (i/100);
                        double tip = (total/100)*i;
                        //double tip = total*tipP;
                        double tipTotal = total+tip;
                        textViewTipDisplay.setText(String.valueOf(tip));
                        textViewTotalDisplay.setText(String.valueOf(tipTotal));
                        textViewPerson.setText(String.valueOf(tipTotal));


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
              double total = Double.parseDouble(textViewTotalDisplay.getText().toString());
              double person= total/1;

              textViewPerson.setText(String.valueOf(person));
                calculateAndDisplay();

            }
        });
        findViewById(R.id.radioButtonSplit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(textViewTotalDisplay.getText().toString());
                double person= total/2;
                textViewPerson.setText(String.valueOf(person));

            }
        });
        findViewById(R.id.radioButtonSplit3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(textViewTotalDisplay.getText().toString());
                double person= total/3;
                textViewPerson.setText(String.valueOf(person));

            }
        });
        findViewById(R.id.radioButtonSplit4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(textViewTotalDisplay.getText().toString());
                double person= total/4;
                textViewPerson.setText(String.valueOf(person));

            }
        });



    }
    public void calculateAndDisplay(){
        //get the bill
        Log.d(TAG, "Input is "+editTextTotalBill.getText().toString());
        Log.d(TAG, "Tip is "+textViewTipDisplay.getText().toString());
        Log.d(TAG, "Total is "+textViewTotalDisplay.getText().toString());
        Log.d(TAG, "Per Person "+textViewPerson.getText().toString());
        //get the tip
        //calculate the tip
        //calculate the total
        //calculate split amount
    }
}


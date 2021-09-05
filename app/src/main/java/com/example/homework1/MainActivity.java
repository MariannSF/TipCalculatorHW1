/*
Homework1
MainActivity.java
Mariann Szabo-Freund,
Bhaskararayuni Sai Datta


Group 7
9/1/2021

 */


package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    double totalPerson;
    double tipD;
    double billTip;
    int i;
    int y;
    double splitV;


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
        //setting up listener on percent radio Group
        percentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //if radio group is checked then resetting the seekBar to initial state
                seekBar.setProgress(40);
                //Calling methods
                calculateAndDisplay();
                updateSplit();
            }
        });
        //seting up listener on splitBy radio group
        splitByGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                calculateAndDisplay();
                updateSplit();
            }
        });
        //setting up listener to the Bill Total field if user changes input
        billTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAndDisplay();
                updateSplit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // Listener on clear Button  and resetting values to initial state
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billTotal.setText("");
                tipValue.setText("0.00");
                totalValue.setText("0.00");
                perPerson.setText("0.00");
                seekBarProgress.setText("40");
                seekBar.setProgress(40);
                percentGroup.check(R.id.radioButton10);
                splitByGroup.check(R.id.radioButtonSplit1);
                calculateAndDisplay();
                updateSplit();

            }
        });
    }
    //method calculate and display calculates the percentage amount and
    //perPerson amounts accordingly
    public void calculateAndDisplay(){

        billTotalString = billTotal.getText().toString();

        billTip = 0;
        if( billTotalString.equals("")){
            billAmnt=0;
        }else {
            billAmnt = Double.parseDouble(billTotalString);
        }

        i = percentGroup.getCheckedRadioButtonId();
        //Checking which radio button is pushed and calculating amounts accordingly
        if(i == R.id.radioButton10){
            tipD = 0.1;
            tipAmnt = billAmnt * tipD;
            totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));
            //setting tip and total Textviews and calling the method updateSplit
            tipValue.setText("$"+String.format("%.2f",tipAmnt));
            totalValue.setText("$"+String.format("%.2f",totalPerson));
            updateSplit();


        }else if(i == R.id.radioButton15){
            tipD = 0.15;
            tipAmnt = billAmnt * tipD;
            totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));

            tipValue.setText("$"+String.format("%.2f",tipAmnt));
            totalValue.setText("$"+String.format("%.2f",totalPerson));
            updateSplit();


        }else if(i == R.id.radioButton18){
            tipD = 0.18;
            tipAmnt = billAmnt * tipD;
            totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));

            tipValue.setText("$"+String.format("%.2f",tipAmnt));
            totalValue.setText("$"+String.format("%.2f",totalPerson));
            updateSplit();

        }else if(i == R.id.radioButtonCustom){
            //setting up a seekBar listener when the Custom percentage radio Button is selected
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    //setting the seekBar TextVew to the seekBar progress
                    seekBarProgress.setText(String.valueOf(i));
                    //Since it is an inner class we need to reference the outer class to have a
                    // ccess to variables
                    MainActivity.this.tipD = (Double.parseDouble(String.valueOf(i)))/100;
                    Log.d(TAG, String.valueOf(i));
                    MainActivity.this.tipAmnt = billAmnt * tipD;
                    MainActivity.this.totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));
                    Log.d(TAG, String.valueOf(splitV));

                    tipValue.setText("$"+String.format("%.2f",tipAmnt));
                    totalValue.setText("$"+String.format("%.2f",totalPerson));
                    updateSplit();

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }




    }
    //Creating updateSplit method so based on the selected radioButton it will calculate and update
    // the perPerson amount
    public void updateSplit(){
        y = splitByGroup.getCheckedRadioButtonId();


        if(y == R.id.radioButtonSplit1){
            splitV = totalPerson/1;

            perPerson.setText("$"+String.format("%.2f",splitV));

        }else if (y == R.id.radioButtonSplit2){

            splitV = totalPerson/2;
            perPerson.setText("$"+String.format("%.2f",splitV));

            Log.d(TAG, totalValue.getText().toString());


        }else if (y == R.id.radioButtonSplit3){
            Log.d(TAG, totalValue.getText().toString());
            double split = totalPerson;
            splitV = split/3;
            perPerson.setText("$"+String.format("%.2f",splitV));


        }else if (y == R.id.radioButtonSplit4){
            Log.d(TAG, totalValue.getText().toString());
            double split = totalPerson;
            splitV = split/4;
            perPerson.setText("$"+String.format("%.2f",splitV));


        }


    }
}


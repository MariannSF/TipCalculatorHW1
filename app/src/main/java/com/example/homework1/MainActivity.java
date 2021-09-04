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
    double totalPerson;
    double tipD;
    double billTip;




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
    }

        public void calculateAndDisplay(){


            billTotalString = billTotal.getText().toString();





            billTip = 0;
            if( billTotalString.equals("")){
                billAmnt=0;
            }else {
                billAmnt = Double.parseDouble(billTotalString);
            }

            int i = percentGroup.getCheckedRadioButtonId();

            if(i == R.id.radioButton10){
                tipD = 0.1;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+tipAmnt);
                totalValue.setText("$"+(totalPerson));
                perPerson.setText("$"+totalPerson);


            }else if(i == R.id.radioButton15){
                tipD = 0.15;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+(tipAmnt));
                totalValue.setText("$"+(totalPerson));
                perPerson.setText("$"+totalPerson);



            }else if(i == R.id.radioButton18){
                tipD = 0.18;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+(tipAmnt));
                totalValue.setText("$"+(totalPerson));
                perPerson.setText("$"+totalPerson);



            }else if(i == R.id.radioButtonCustom){

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        seekBarProgress.setText(String.valueOf(i));

                        MainActivity.this.tipD = (Double.parseDouble(String.valueOf(i)))/100;
                        MainActivity.this.tipAmnt = billAmnt * tipD;
                        MainActivity.this.totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                        tipValue.setText("$"+(tipAmnt));
                        totalValue.setText("$"+(totalPerson));
                        perPerson.setText("$"+totalPerson);





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

                perPerson.setText("$"+totalPerson);

            }else if (y == R.id.radioButtonSplit2){

                double splitV = totalPerson/2;
                perPerson.setText("$"+splitV);

                Log.d(TAG, totalValue.getText().toString());


            }else if (y == R.id.radioButtonSplit3){
                Log.d(TAG, totalValue.getText().toString());
                double split = totalPerson;
                double splitV = split/3;
                perPerson.setText("$"+splitV);



            }else if (y == R.id.radioButtonSplit4){
                Log.d(TAG, totalValue.getText().toString());
                double split = totalPerson;
                double splitV = split/4;
                perPerson.setText("$"+splitV);



            }

        }
    }


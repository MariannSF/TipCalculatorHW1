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

        percentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                seekBar.setProgress(40);

                calculateAndDisplay();
                updateSplit();
            }
        });
        splitByGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                calculateAndDisplay();
                updateSplit();
            }
        });

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


               /* public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    seekBarProgress.setText(String.valueOf(i));*/




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

            i = percentGroup.getCheckedRadioButtonId();

            if(i == R.id.radioButton10){
                tipD = 0.1;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+String.format("%.2f",tipAmnt));
                totalValue.setText("$"+String.format("%.2f",totalPerson));
                updateSplit();
                //perPerson.setText("$"+String.format("%.2f",(totalPerson)));


            }else if(i == R.id.radioButton15){
                tipD = 0.15;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+String.format("%.2f",tipAmnt));
                totalValue.setText("$"+String.format("%.2f",totalPerson));
                updateSplit();
                //perPerson.setText("$"+String.format("%.2f",(totalPerson)));



            }else if(i == R.id.radioButton18){
                tipD = 0.18;
                tipAmnt = billAmnt * tipD;
                totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));


                tipValue.setText("$"+String.format("%.2f",tipAmnt));
                totalValue.setText("$"+String.format("%.2f",totalPerson));
                updateSplit();
                //perPerson.setText("$"+String.format("%.2f",(totalPerson)));



            }else if(i == R.id.radioButtonCustom){

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                        seekBarProgress.setText(String.valueOf(i));

                        MainActivity.this.tipD = (Double.parseDouble(String.valueOf(i)))/100;
                        Log.d(TAG, String.valueOf(i));
                        MainActivity.this.tipAmnt = billAmnt * tipD;
                        MainActivity.this.totalPerson = Double.parseDouble(String.valueOf(billAmnt+tipAmnt));
                        //MainActivity.this.y = splitByGroup.getCheckedRadioButtonId();
                        Log.d(TAG, String.valueOf(splitV));



                        tipValue.setText("$"+String.format("%.2f",tipAmnt));
                        totalValue.setText("$"+String.format("%.2f",totalPerson));
                        //perPerson.setText("$"+String.format("%.2f",(totalPerson)));
                        updateSplit();

                        //perPerson.setText("$"+String.format("%.2f",(totalPerson)));





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


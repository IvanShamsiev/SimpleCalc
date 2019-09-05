package com.example.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyCalc myCalc;

    TextView textConclusion, textCurrentOperation;

    Button btnNumber0,
            btnNumber1, btnNumber2, btnNumber3,
            btnNumber4, btnNumber5, btnNumber6,
            btnNumber7, btnNumber8, btnNumber9,
            btnClear, btnPlus, btnMinus, btnMultiply, btnDivide,
            btnDot, btnEqually, btnRemain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCalc = new MyCalc(this);

        setButtons();
        setButtonsListeners();
        textConclusion = findViewById(R.id.textConclusion);
        textCurrentOperation = findViewById(R.id.textCurrentOperation);
    }

    void setButtons() {
        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        btnNumber6 = findViewById(R.id.btnNumber6);
        btnNumber7 = findViewById(R.id.btnNumber7);
        btnNumber8 = findViewById(R.id.btnNumber8);
        btnNumber9 = findViewById(R.id.btnNumber9);
        btnNumber0 = findViewById(R.id.btnNumber0);

        btnDot = findViewById(R.id.btnDot);
        btnEqually = findViewById(R.id.btnEqually);
        btnRemain = findViewById(R.id.btnRemain);
        btnClear = findViewById(R.id.btnClear);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
    }

    void setButtonsListeners() {
        btnNumber1.setOnClickListener(myCalc.numListener);
        btnNumber2.setOnClickListener(myCalc.numListener);
        btnNumber3.setOnClickListener(myCalc.numListener);
        btnNumber4.setOnClickListener(myCalc.numListener);
        btnNumber5.setOnClickListener(myCalc.numListener);
        btnNumber6.setOnClickListener(myCalc.numListener);
        btnNumber7.setOnClickListener(myCalc.numListener);
        btnNumber8.setOnClickListener(myCalc.numListener);
        btnNumber9.setOnClickListener(myCalc.numListener);
        btnNumber0.setOnClickListener(myCalc.numListener);

        btnRemain.setOnClickListener(myCalc.opListener);
        btnPlus.setOnClickListener(myCalc.opListener);
        btnMinus.setOnClickListener(myCalc.opListener);
        btnMultiply.setOnClickListener(myCalc.opListener);
        btnDivide.setOnClickListener(myCalc.opListener);

        btnDot.setOnClickListener(myCalc.otherListener);
        btnEqually.setOnClickListener(myCalc.otherListener);
        btnClear.setOnClickListener(myCalc.otherListener);

        btnClear.setOnLongClickListener(myCalc.longListener);
    }
}

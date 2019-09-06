package com.example.simplecalc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    MyCalc myCalc;

    TextView textConclusion, textCurrentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCalc = new MyCalc(this);


        // Set buttons listeners

        for (int id: myCalc.numButtons)
            findViewById(id).setOnClickListener(myCalc.numListener);

        for (int id: myCalc.operationButtons)
            findViewById(id).setOnClickListener(myCalc.opListener);

        for (int id: myCalc.othedButtons)
            findViewById(id).setOnClickListener(myCalc.otherListener);

        findViewById(R.id.btnClear).setOnLongClickListener(myCalc.longListener);


        // Bind TextViews
        textConclusion = findViewById(R.id.textConclusion);
        textCurrentOperation = findViewById(R.id.textCurrentOperation);
    }

}

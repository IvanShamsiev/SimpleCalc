package com.example.simplecalc;

import android.view.View;
import android.widget.Toast;

class MyCalc {

    private double bufferNum = 0;
    private String currentNum = "0";
    private Operation currentOp = Operation.PLUS;
    private boolean dotAlreadyExists = false, equally = false;

    private int[] btnNums = {R.id.btnNumber1, R.id.btnNumber2, R.id.btnNumber3,
            R.id.btnNumber4, R.id.btnNumber5, R.id.btnNumber6,
            R.id.btnNumber7, R.id.btnNumber8, R.id.btnNumber9, R.id.btnNumber0};
    private int[] btnOps = {R.id.btnRemain, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};

    private MainActivity activity;

    MyCalc(MainActivity activity) {
        this.activity = activity;
    }

    View.OnClickListener listener = btn -> {
        for (int id: btnNums) {
            if (id == btn.getId()) {
                if (equally) {
                    currentNum = "0";
                    equally = false;
                }
                switch (id) {
                    case R.id.btnNumber1:
                        currentNum += "1";
                        break;
                    case R.id.btnNumber2:
                        currentNum += "2";
                        break;
                    case R.id.btnNumber3:
                        currentNum += "3";
                        break;
                    case R.id.btnNumber4:
                        currentNum += "4";
                        break;
                    case R.id.btnNumber5:
                        currentNum += "5";
                        break;
                    case R.id.btnNumber6:
                        currentNum += "6";
                        break;
                    case R.id.btnNumber7:
                        currentNum += "7";
                        break;
                    case R.id.btnNumber8:
                        currentNum += "8";
                        break;
                    case R.id.btnNumber9:
                        currentNum += "9";
                        break;
                    case R.id.btnNumber0:
                        currentNum += "0";
                        break;
                }
                delNulls();
                activity.textConclusion.setText(currentNum);
                return;
            }
        }

        if (btn.getId() == R.id.btnDot) {
            if (!dotAlreadyExists) {
                currentNum += ".";
                activity.textConclusion.setText(currentNum);
                dotAlreadyExists = true;
            }
            else Toast.makeText(activity, "Dot already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int id: btnOps) {
            if (id == btn.getId()) {
                switch (btn.getId()) {
                    case R.id.btnRemain:
                        switchNum();
                        currentOp = Operation.REMAIN;
                        break;
                    case R.id.btnPlus:
                        switchNum();
                        currentOp = Operation.PLUS;
                        break;
                    case R.id.btnMinus:
                        switchNum();
                        currentOp = Operation.MINUS;
                        break;
                    case R.id.btnMultiply:
                        switchNum();
                        currentOp = Operation.MULTIPLY;
                        break;
                    case R.id.btnDivide:
                        switchNum();
                        currentOp = Operation.DIVIDE;
                        break;
                }
                setCurrentOp(currentOp);
                dotAlreadyExists = false;
                return;
            }
        }

        if (btn.getId() == R.id.btnClear) {
            if (currentNum.substring(currentNum.length()).equals(".")) dotAlreadyExists = false;
            currentNum = currentNum.substring(0, currentNum.length()-1);
            activity.textConclusion.setText(currentNum);
            equally = false;
            return;
        }

        if (btn.getId() == R.id.btnEqually) {
            currentNum = String.valueOf(
                    getResult(bufferNum, Double.parseDouble(currentNum), currentOp));
            bufferNum = 0;
            activity.textConclusion.setText(currentNum);
            equally = true;
            currentOp = Operation.PLUS;
            setCurrentOp(currentOp);
            dotAlreadyExists = false;
        }
    };

    View.OnLongClickListener longListener = btn -> {
        if (btn.getId() == R.id.btnClear) {
            bufferNum = 0;
            currentNum = "0";
            activity.textConclusion.setText(currentNum);
            currentOp = Operation.PLUS;
            setCurrentOp(currentOp);
            dotAlreadyExists = false;
            equally = false;
        }
        return true;
    };

    private void switchNum() {
        bufferNum = getResult(bufferNum, Double.parseDouble(currentNum), currentOp);
        currentNum = "0";
        activity.textConclusion.setText("0");
    }

    private double getResult(double bufferNum, double currentNum, Operation currentOp) {
        switch (currentOp) {
            case PLUS:
                return bufferNum + currentNum;
            case MINUS:
                return bufferNum - currentNum;
            case MULTIPLY:
                return bufferNum * currentNum;
            case DIVIDE:
                return bufferNum / currentNum;
            case REMAIN:
                return bufferNum % currentNum;
        }
        return 0;
    }

    private void setCurrentOp(Operation currentOp) {
        String operation = "";
        switch (currentOp) {
            case PLUS:
                operation = "Plus (+)";
                break;
            case MINUS:
                operation = "Minus (-)";
                break;
            case MULTIPLY:
                operation = "Multiply (*)";
                break;
            case DIVIDE:
                operation = "Divide (/)";
                break;
            case REMAIN:
                operation = "Remain (%)";
                break;
        }
        activity.textCurrentOperation.setText(String.format("Current operation: %s", operation));
    }

    private void delNulls() {
        if (currentNum.contains(".")) {
            String beforeDot = currentNum.substring(0, currentNum.indexOf("."));
            String after = currentNum.substring(currentNum.indexOf("."));
            currentNum = Long.parseLong(beforeDot) + after;
        } else {
            currentNum = String.valueOf(Long.parseLong(currentNum));
        }
    }
}

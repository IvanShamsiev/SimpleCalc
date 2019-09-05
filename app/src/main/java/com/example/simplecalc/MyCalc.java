package com.example.simplecalc;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Toast;


class MyCalc {

    private double bufferNum = 0;
    private String currentNum = "0";
    private Operation currentOp = Operation.PLUS;
    private boolean dotAlreadyExists = false, equally = false;

    private int[] numButtons = {R.id.btnNumber0,
            R.id.btnNumber1, R.id.btnNumber2, R.id.btnNumber3,
            R.id.btnNumber4, R.id.btnNumber5, R.id.btnNumber6,
            R.id.btnNumber7, R.id.btnNumber8, R.id.btnNumber9};

    private int[] operationButtons = {R.id.btnRemain, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};

    private SparseIntArray btnNumMap = new SparseIntArray(numButtons.length);
    private SparseArray<Operation> btnOpMap = new SparseArray<>(operationButtons.length);

    private MainActivity activity;

    MyCalc(MainActivity activity) {
        this.activity = activity;

        for (int i = 0; i < numButtons.length; i++) btnNumMap.put(numButtons[i], i);

        Operation[] operations = {Operation.REMAIN, Operation.PLUS, Operation.MINUS,
                Operation.MULTIPLY, Operation.DIVIDE};
        for (int i = 0; i < operationButtons.length; i++) btnOpMap.put(operationButtons[i], operations[i]);
    }

    View.OnClickListener numListener = numBtn -> {
        for (int id: numButtons)
            if (id == numBtn.getId()) {
                if (equally) {
                    currentNum = "0";
                    equally = false;
                }
                currentNum += btnNumMap.get(id);
                delNulls();
                activity.textConclusion.setText(currentNum);
                return;
            }
    };

    View.OnClickListener opListener = opBtn -> {
        for (int id: operationButtons)
            if (id == opBtn.getId()) {
                if (currentNum.isEmpty() || !hasNumber(currentNum)) bufferNum = 0;
                else bufferNum = getResult(bufferNum, Double.parseDouble(currentNum), currentOp);
                currentNum = "0";
                activity.textConclusion.setText("0");

                currentOp = btnOpMap.get(id);
                setCurrentOp(currentOp);
                dotAlreadyExists = false;
            }
    };

    View.OnClickListener otherListener = btn -> {

        if (btn.getId() == R.id.btnDot) {
            if (!dotAlreadyExists) {
                currentNum += ".";
                activity.textConclusion.setText(currentNum);
                dotAlreadyExists = true;
            }
            else Toast.makeText(activity, "Dot already exists", Toast.LENGTH_SHORT).show();
            return;
        }


        if (btn.getId() == R.id.btnClear) {
            if (currentNum.isEmpty()) return;
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

    private static double getResult(double bufferNum, double currentNum, Operation currentOp) {
        switch (currentOp) {
            case PLUS: return bufferNum + currentNum;
            case MINUS: return bufferNum - currentNum;
            case MULTIPLY: return bufferNum * currentNum;
            case DIVIDE: return bufferNum / currentNum;
            case REMAIN: return bufferNum % currentNum;
        }
        throw new RuntimeException("Текущая операция равна null");
    }

    private static boolean hasNumber(String s) {
        char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        for (char c: numbers) if (s.contains(String.valueOf(c))) return true;
        return false;
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

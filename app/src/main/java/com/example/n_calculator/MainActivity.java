package com.example.n_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTextView;
    MaterialButton buttonC, buttonAC, buttonDivide, buttonMultiply, buttonSubtract, buttonAdd, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot;

    String currentInput = "";
    String operator = "";
    Double firstOperand = null;
    boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing result TextView and all buttons
        resultTextView = findViewById(R.id.result_tv);

        assignButtonIds();

        // Set onClickListeners for all buttons
        setOnClickListeners();
    }

    private void assignButtonIds() {
        buttonC = findViewById(R.id.button_c);
        buttonAC = findViewById(R.id.button_ac);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonSubtract = findViewById(R.id.button_substraction);
        buttonAdd = findViewById(R.id.button_add);
        buttonEqual = findViewById(R.id.button_equal);

        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonDot = findViewById(R.id.button_dot);
    }

    private void setOnClickListeners() {
        buttonC.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("C")) {
            clearLastCharacter();
        } else if (buttonText.equals("AC")) {
            clearAll();
        } else if (buttonText.equals("=")) {
            calculateResult();
        } else if (isOperator(buttonText)) {
            handleOperator(buttonText);
        } else {
            handleNumberInput(buttonText);
        }
    }

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

    private void handleOperator(String buttonText) {
        if (currentInput.isEmpty()) return;

        if (firstOperand == null) {
            firstOperand = Double.parseDouble(currentInput);
        } else if (!operator.isEmpty()) {
            calculateResult();
        }

        operator = buttonText;
        isNewOperation = true;
    }

    private void handleNumberInput(String buttonText) {
        if (isNewOperation) {
            currentInput = "";
            isNewOperation = false;
        }

        currentInput += buttonText;
        resultTextView.setText(currentInput);
    }

    private void calculateResult() {
        if (firstOperand == null || currentInput.isEmpty() || operator.isEmpty()) {
            return;
        }

        double secondOperand = Double.parseDouble(currentInput);
        double result = 0.0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    resultTextView.setText("Error");
                    return;
                }
                break;
        }

        resultTextView.setText(String.valueOf(result));
        firstOperand = result;
        isNewOperation = true;
    }

    private void clearLastCharacter() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            resultTextView.setText(currentInput);
        }
    }

    private void clearAll() {
        currentInput = "";
        firstOperand = null;
        operator = "";
        isNewOperation = true;
        resultTextView.setText("0");
    }
}

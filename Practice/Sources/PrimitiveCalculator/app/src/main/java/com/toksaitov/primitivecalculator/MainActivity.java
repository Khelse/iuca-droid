package com.toksaitov.primitivecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RadioGroup operationsRadioGroup;

    private EditText firstNumberEditText;
    private EditText secondNumberEditText;

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationsRadioGroup = findViewById(R.id.operationsRadioGroup);

        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);

        resultTextView = findViewById(R.id.resultTextView);

        setupEventListeners();
    }

    private void setupEventListeners() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                calculate();
            }
        };

        firstNumberEditText.addTextChangedListener(textWatcher);
        secondNumberEditText.addTextChangedListener(textWatcher);

        operationsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                calculate();
            }
        });
    }

    private void calculate() {
        String firstNumberText = firstNumberEditText.getText().toString();
        String secondNumberText = secondNumberEditText.getText().toString();

        double firstNumber;
        double secondNumber;
        try {
            firstNumber = Double.parseDouble(firstNumberText);
            secondNumber = Double.parseDouble(secondNumberText);
        } catch (NumberFormatException e) {
            return;
        }

        double result = 0;

        int id = operationsRadioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.additionRadioButton:
                result = firstNumber + secondNumber;
                break;
            case R.id.subtractionRadioButton:
                result = firstNumber - secondNumber;
                break;
            case R.id.multiplicationRadioButton:
                result = firstNumber * secondNumber;
                break;
            case R.id.divisionRadioButton:
                if (Math.abs(secondNumber - 0) > 0.000001) {
                    result = firstNumber / secondNumber;
                } else {
                    reportError(getString(R.string.cant_divide_by_zero));
                    resultTextView.setText("");

                    return;
                }
                break;
        }

        resultTextView.setText(
            String.format(Locale.getDefault(), "%.2f", result)
        );
    }

    private void reportError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

package com.example.toksaitov.converter;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

public class UnitConversionActivity extends AppCompatActivity {

    private EditText firstValueEditText,
                     resultEditText;

    private Spinner firstUnitSpinner,
                    resultUnitSpinner;

    private TypedArray conversionFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);

        firstValueEditText = findViewById(R.id.firstValueEditText);
        resultEditText = findViewById(R.id.resultEditText);

        firstUnitSpinner = findViewById(R.id.firstUnitSpinner);
        resultUnitSpinner = findViewById(R.id.resultUnitSpinner);

        setupSpinners();
        loadConversionFactors();
        setupInputFields();
    }

    private void setupSpinners() {
        Intent intent =
            getIntent();

        int unitNamesResourceID =
            intent.getIntExtra("unit_names", -1);
        ArrayAdapter<CharSequence> adapter =
            ArrayAdapter.createFromResource(
            this,
                unitNamesResourceID,
                android.R.layout.simple_spinner_item
            );
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        );
        firstUnitSpinner.setAdapter(adapter);
        resultUnitSpinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };

        firstUnitSpinner.setOnItemSelectedListener(listener);
        resultUnitSpinner.setOnItemSelectedListener(listener);
    }

    private void loadConversionFactors() {
        Intent intent = getIntent();
        int unitFactrosResourceID =
            intent.getIntExtra("unit_factors", -1);

        conversionFactors = getResources().obtainTypedArray(unitFactrosResourceID);
    }

    private void setupInputFields() {
        firstValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                convert();
            }
        });
    }

    private void convert() {
        double firstValue;
        try {
            firstValue = Double.parseDouble(firstValueEditText.getText().toString());
        } catch (NumberFormatException e) {
            resultEditText.setText("");
            return;
        }

        int from = firstUnitSpinner.getSelectedItemPosition();
        int to = resultUnitSpinner.getSelectedItemPosition();

        double fromFactor = conversionFactors.getFloat(from, 0.0f);
        double toFactor = conversionFactors.getFloat(to, 0.0f);

        double result = firstValue * fromFactor / toFactor;

        resultEditText.setText(String.format(Locale.getDefault(), "%.2f", result));
    }
}

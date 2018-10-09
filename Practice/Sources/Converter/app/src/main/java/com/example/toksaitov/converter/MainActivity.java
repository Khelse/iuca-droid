package com.example.toksaitov.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigate(View view) {
        int buttonID = view.getId();
        switch (buttonID) {
            case R.id.lengthConversionButton: {
                Intent intent = new Intent(this, UnitConversionActivity.class);
                intent.putExtra("unit_names", R.array.length_conversion_unit_names);
                intent.putExtra("unit_factors", R.array.length_conversion_factors);
                startActivity(intent);
                break;
            } case R.id.massConversionButton: {
                Intent intent = new Intent(this, UnitConversionActivity.class);
                intent.putExtra("unit_names", R.array.mass_conversion_unit_names);
                intent.putExtra("unit_factors", R.array.mass_conversion_factors);
                startActivity(intent);
                break;
            } case R.id.currencyConversionButton:
                // TODO
                break;
        }
    }

}

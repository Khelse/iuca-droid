package com.toksaitov.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private Button counterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        counterButton = findViewById(R.id.countButton);

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = Integer.parseInt(counterTextView.getText().toString());
                ++counter;
                counterTextView.setText(
                    String.format(
                        Locale.getDefault(),
                        "%04d",
                        counter
                    )
                );
            }
        });
    }

}

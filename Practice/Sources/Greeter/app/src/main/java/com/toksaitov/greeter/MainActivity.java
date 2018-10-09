package com.toksaitov.greeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingTextView = findViewById(R.id.greetingTextView);
        nameEditText = findViewById(R.id.nameEditText);
    }

    public void greet(View view) {
        String name = nameEditText.getText().toString();
        String message = "Hello, " + name;
        greetingTextView.setText(message);
    }

}

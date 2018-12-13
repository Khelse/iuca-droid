package com.example.toksaitov.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView degreesTextView;
    private ImageView weatherIconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        degreesTextView = findViewById(R.id.degreesTextView);
        weatherIconImageView = findViewById(R.id.weatherIconImageView);

        loadWeatherData();
    }

    private void loadWeatherData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q=Tokmok&units=metric&APPID=3f6ecb618d77683f6f424a4543e1ca1a";

        final Context context = this;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    showTemperature(response);
                    showWeatherIcon(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Failed to load the weather data", Toast.LENGTH_SHORT).show();
                }
            }
        );
        queue.add(jsonObjectRequest);
    }

    private void showTemperature(JSONObject response) {
        double temp;
        try {
            temp = response.getJSONObject("main").getDouble("temp");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        degreesTextView.setText(String.format(Locale.getDefault(), "%.1f °C", temp));
    }

    private void showWeatherIcon(JSONObject response) {
        String weatherIcon;
        try {
            weatherIcon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        String url = "https://openweathermap.org/img/w/" + weatherIcon + ".png";
        Glide.with(this).load(url).into(weatherIconImageView);
    }

}
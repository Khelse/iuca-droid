package com.toksaitov.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int[] DIE_IMAGES = {
        R.drawable.ic_dice_1,
        R.drawable.ic_dice_2,
        R.drawable.ic_dice_3,
        R.drawable.ic_dice_4,
        R.drawable.ic_dice_5,
        R.drawable.ic_dice_6
    };

    private ImageView firstDieImageView;
    private ImageView secondDieImageView;

    private Dice dice = new Dice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstDieImageView = findViewById(R.id.firstDieImageView);
        secondDieImageView = findViewById(R.id.secondDieImageView);

        if (savedInstanceState != null) {
            dice.setFirstDieIndex(savedInstanceState.getInt("firstDieIndex"));
            dice.setSecondDieIndex(savedInstanceState.getInt("secondDieIndex"));
            updateImages();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("firstDieIndex", dice.getFirstDieIndex());
        outState.putInt("secondDieIndex", dice.getSecondDieIndex());
    }

    public void roll(View view) {
        dice.roll();
        updateImages();
    }

    private void updateImages() {
        int firstDieResource = DIE_IMAGES[dice.getFirstDieIndex()];
        int secondDieResource = DIE_IMAGES[dice.getSecondDieIndex()];

        firstDieImageView.setImageResource(firstDieResource);
        secondDieImageView.setImageResource(secondDieResource);
    }

}

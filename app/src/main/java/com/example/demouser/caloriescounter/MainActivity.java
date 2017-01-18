package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {


    private RadioButton noRestrictionButton;
    private RadioButton VeganButton;
    private RadioButton HalalButton;
    private RadioButton NutFreeButton;
    private CaloriesCounter counter;
    private boolean isHalal, isVegan, isNoRestriction, isNutFree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noRestrictionButton = (RadioButton) findViewById(R.id.noRestrictionButton);
        noRestrictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNoRestriction = true;
            }
        });

        VeganButton = (RadioButton) findViewById(R.id.veganButton);
        VeganButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVegan = true;
            }
        });

        HalalButton = (RadioButton) findViewById(R.id.halalButton);
        HalalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHalal = true;
            }
        });

        NutFreeButton = (RadioButton) findViewById(R.id.noNutButton);
        NutFreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNutFree = true;
            }
        });

        counter = new CaloriesCounter(isNoRestriction, isVegan, isHalal, isNutFree);

    }
}

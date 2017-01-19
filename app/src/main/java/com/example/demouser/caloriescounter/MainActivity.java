package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean checked = false;
    public static String NO_RESTRICTIONS="package com.example.demouser.caloriescounter.NO_RESTRICTIONS";
    public static String NUT_FREE="package com.example.demouser.caloriescounter.NUT_FREE";
    public static String VEGAN="package com.example.demouser.caloriescounter.VEGAN";
    public static String HALAL="package com.example.demouser.caloriescounter.HALAL";
    public static String CALORIES="package com.example.demouser.caloriescounter.CALORIES";


    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, Result.class);


        ((RadioButton) findViewById(R.id.noRestrictionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=true;
                intent.putExtra(NO_RESTRICTIONS, true);
            }
        });

        ((RadioButton) findViewById(R.id.veganButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=true;
                intent.putExtra(VEGAN, true);
            }
        });

        ((RadioButton) findViewById(R.id.halalButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=true;
                intent.putExtra(HALAL, true);
            }
        });

        findViewById(R.id.noNutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=true;
                intent.putExtra(NUT_FREE, true);
            }
        });


        ((Button)findViewById(R.id.submit)) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // see if the value in the calories text is wihing range
                int calories = Integer.parseInt(((TextView)findViewById(R.id.CaloriesLimitText)).getText().toString());
                // if calories within "healthy range", do this, else, give a warning
                if (calories > 1000 && calories < 2100) {
                    intent.putExtra(CALORIES, calories);
                    startActivity(intent);
                    //after this, we want to be done
                    finish();
                    return;
                }
                else {

                }
            }
        });

    }


    public void showResult() {
        // AFTER THE user submits the button, start a new intent, giving it
        Intent intent = new Intent(this, Result.class);
        // intent.putExtra(USER_SCORE, String.valueOf(playerTotal));// --> in player wins
        startActivity(intent);
    }

}

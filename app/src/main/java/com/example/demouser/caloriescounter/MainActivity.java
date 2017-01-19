package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String NO_RESTRICTIONS="package com.example.demouser.caloriescounter.NO_RESTRICTIONS";
    public static String NUT_FREE="package com.example.demouser.caloriescounter.NUT_FREE";
    public static String VEGAN="package com.example.demouser.caloriescounter.VEGAN";
    public static String HALAL="package com.example.demouser.caloriescounter.HALAL";
    public static String CALORIES="package com.example.demouser.caloriescounter.CALORIES";

    private boolean checked = false;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, Result.class);

        ((Button)findViewById(R.id.submit)) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) findViewById(R.id.noRestrictionButton)).isChecked()) {
                    intent.putExtra(NO_RESTRICTIONS, true);
                    checked = true;
                }

                if (((CheckBox) findViewById(R.id.veganButton)).isChecked()) {
                    intent.putExtra(VEGAN, true);
                    checked = true;
                }

                if (((CheckBox) findViewById(R.id.halalButton)).isChecked()) {
                    intent.putExtra(HALAL, true);
                    checked = true;
                }

                if (((CheckBox) findViewById(R.id.noNutButton)).isChecked()) {
                    intent.putExtra(NUT_FREE, true);
                    checked = true;
                }


                // see if the value in the calories text is within range
                int calories = Integer.parseInt(((TextView)findViewById(R.id.CaloriesLimitText)).getText().toString());

                // if calories within "healthy range", do this, else, give a warning
                if (calories >= 1000 && calories <= 2100 && checked) {
                    intent.putExtra(CALORIES, calories);
                    startActivity(intent);
                    //after this, we want to be done
                    finish();
                    return;
                }
                else {
                    // if no dietary restriction selected
                    if(!checked){
                        Toast.makeText(MainActivity.this, "Please select a dietary restriction.", Toast.LENGTH_SHORT).show();
                    } else { // else calories limit is out of range
                        Toast.makeText(MainActivity.this, "Please enter a value between 500 and 2100.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    public static String NO_RESTRICTIONS="package com.example.demouser.caloriescounter.NO_RESTRICTIONS";
    public static String NUT_FREE="package com.example.demouser.caloriescounter.NUT_FREE";
    public static String VEGAN="package com.example.demouser.caloriescounter.VEGAN";
    public static String HALAL="package com.example.demouser.caloriescounter.HALAL";
    public static String CALORIES="package com.example.demouser.caloriescounter.CALORIES";

    private boolean checked = false;

    // firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabase;

    private String mUserEmail;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser == null){
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }else {
            mUserEmail = mFirebaseUser.getEmail();
            mUserEmail = mUserEmail.substring(0, mUserEmail.indexOf('@'))
                    .replace('.', '_')
                    .replace('#', '_')
                    .replace('$', '_')
                    .replace('[', '_')
                    .replace(']', '_');
        }

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

                // check if user selected at least one dietary restrictions
                if(!checked){
                    Toast.makeText(MainActivity.this, "Please select a dietary restriction.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int calories;
                String UserCaloriesEntry = ((EditText)findViewById(R.id.CaloriesLimitText)).getText().toString();
                // check user actually entered a value
                if (UserCaloriesEntry.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter a value between 250 and 1000.", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    calories = Integer.parseInt(UserCaloriesEntry);
                }

                // see if the value in the calories text is within range
                // if calories within "healthy range", do calculation
                if (calories >= 250 && calories <= 1000 && checked) {
                    intent.putExtra(CALORIES, calories);
                    startActivity(intent);
                    // it only comes back if the user hit reset. reset everything
                    ((CheckBox) findViewById(R.id.noRestrictionButton)).setChecked(false);

                    ((CheckBox) findViewById(R.id.veganButton)).setChecked(false);

                    ((CheckBox) findViewById(R.id.halalButton)).setChecked(false);

                    ((CheckBox) findViewById(R.id.noNutButton)).setChecked(false);
                    ((EditText)findViewById(R.id.CaloriesLimitText)).setText("");

                } else{
                    Toast.makeText(MainActivity.this, "Please enter a value between 250 and 1000.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
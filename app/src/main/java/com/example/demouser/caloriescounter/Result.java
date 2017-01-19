package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    private CaloriesCounter caloriesCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        caloriesCounter = new CaloriesCounter();

        Intent intent = getIntent();
        caloriesCounter.setUserVegan(intent.getBooleanExtra(MainActivity.VEGAN, false));
        caloriesCounter.setUserNutFree(intent.getBooleanExtra(MainActivity.NUT_FREE, false));
        caloriesCounter.setUserHalal(intent.getBooleanExtra(MainActivity.HALAL, false));
        //this has to be last because if the user selects none, we want all to be selected!!!
        caloriesCounter.setUserNone(intent.getBooleanExtra(MainActivity.NO_RESTRICTIONS, false));

        //default is 1500
        int calories = intent.getIntExtra(MainActivity.CALORIES, 1500);

        int oneThird = calories/3;
        // get the edible appetizers, main course and dessert
        ArrayList<Menu> appetizers = getSubset(caloriesCounter.getEdibleAppetizers(), oneThird);
        ArrayList<Menu> mainCourse = getSubset(caloriesCounter.getEdibleMainCourse(), (oneThird*2));
        ArrayList<Menu> dessert = getSubset(caloriesCounter.getEdibleDessert(), oneThird);


        StringBuilder sbAppetizer = new StringBuilder();

        //now put them in the text box
        for(Menu menu : appetizers) {
            sbAppetizer.append(menu.toString());
            sbAppetizer.append("/n");
        }
        ((TextView) findViewById(R.id.appetizer_menu)).setText(sbAppetizer.toString());


        StringBuilder sbMain = new StringBuilder();


        //now put them in the text box
        for(Menu menu : mainCourse) {
            sbMain.append(menu.toString());
            sbMain.append("/n");
        }
        ((TextView) findViewById(R.id.main_menu)).setText(sbMain.toString());


        StringBuilder sbDessert = new StringBuilder();

        //now put them in the text box
        for(Menu menu : dessert) {
            sbMain.append(menu.toString());
            sbMain.append("/n");
        }
        ((TextView) findViewById(R.id.dessert_menu)).setText(sbMain.toString());


        ((Button) findViewById(R.id.reset_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                finish();
            }
        });
    }


    public ArrayList<Menu> getSubset(ArrayList<Food> menu, int caloriesSum){
        //base case
        if (caloriesSum < 0 || menu==null) {
            return null;
        }
        //if it is the last thing on menu, return it
        if (menu.size()==1 ) {
            // lm = list of menu
            ArrayList<Menu> lm = new ArrayList<Menu>();
            if (menu.get(0).calories < caloriesSum) {

                Menu temp =  new Menu();
                temp.add(menu.get(0));
                lm.add(temp);
            }
            return lm;
        }

        //working case

        //recursive case

        // there are two possiblities, add the first thing, or not
        /*
         * with case
         * reduce the calories sum
         */
        Food temp = menu.remove(0);

        ArrayList<Menu> withList = getSubset(menu, (caloriesSum - temp.getCalories()));
        if ( withList==null) {
            withList = new ArrayList<Menu>();

        }
        if (withList.isEmpty()) {
            withList.add(new Menu());

        }
        for(Menu foodMenu : withList) {
            // add temp to all of them
            foodMenu.add(temp);
        }

        /*
         * without case: cases that dosesn't include the temp
         */
        ArrayList<Menu> withoutList = getSubset(menu, caloriesSum);
        if (withoutList!=null) {

            withList.addAll(withoutList);
        }

        return withList;
    }


}


 class Menu{
    ArrayList<Food> menu = new ArrayList<Food>();

    int calories =0;

    int getCalories() {
        return calories;
    }

    void add(Food food) {
        menu.add(food);
        calories+=food.calories;
    }

    void add(KnapsackProbSolver.Menu another) {
        menu.addAll(another.menu);
        calories+=another.getCalories();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Food food: menu) {
            sb.append(food.getName());
            sb.append(", ");
        }
        if(sb.length() > 0) {
            //remove the last ,
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}

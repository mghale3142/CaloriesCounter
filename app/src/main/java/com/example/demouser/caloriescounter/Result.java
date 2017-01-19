package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Result extends AppCompatActivity {

    private CaloriesCounter caloriesCounter;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        int calories = intent.getIntExtra(MainActivity.CALORIES, 750);

        int ratio = calories/4;
        // get the edible appetizers, main course and dessert
        HashSet<Menu> appetizers = getSubset(caloriesCounter.getEdibleAppetizers(), ratio);
        HashSet<Menu> mainCourse = getSubset(caloriesCounter.getEdibleMainCourse(), (ratio*2));
        HashSet<Food> teDe = caloriesCounter.getEdibleDessert();
        if(teDe==null) {
            System.out.println("oppppppsssss");
        }
        HashSet<Menu> dessert = getSubset(teDe, ratio);

        StringBuilder sbAppetizer = new StringBuilder();
        //now put them in the text box
        for(Menu menu : appetizers) {
            sbAppetizer.append("<div>");
            sbAppetizer.append(menu.toString());
            sbAppetizer.append("</div>");
        }
        ((TextView) findViewById(R.id.appetizer_menu)).setText(Html.fromHtml(sbAppetizer.toString(), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));


        StringBuilder sbMain = new StringBuilder();
        //now put them in the text box
        for(Menu menu : mainCourse) {
            sbMain.append(menu.toString());
            sbMain.append("\n");
        }
        ((TextView) findViewById(R.id.main_menu)).setText(sbMain.toString());


        StringBuilder sbDessert = new StringBuilder();
        //now put them in the text box
        for(Menu menu : dessert) {
            System.out.println("here");
            sbDessert.append(menu.toString());
            sbDessert.append("\n");
        }
        ((TextView) findViewById(R.id.dessert_menu)).setText(sbDessert.toString());

        ((Button) findViewById(R.id.reset_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public HashSet<Menu> getSubset(HashSet<Food> menu, int caloriesSum){
        //base case
        if (caloriesSum < 0 || menu==null) {
            return null;
        }
        //if it is the last thing on menu, return it
        if (menu.size()==1) {
            // lm = list of menu
            HashSet<Menu> lm = new HashSet<Menu>();
            Food tempFood = menu.iterator().next();
            if (tempFood.calories < caloriesSum) {

                Menu tempMenu =  new Menu();
                tempMenu.add(tempFood);
                lm.add(tempMenu);
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
        Food temp = menu.iterator().next();
        // remove it
        menu.remove(temp);
        HashSet<Food> excluding = new HashSet<>(menu);
        HashSet<Menu> withList = new HashSet<Menu>();
        if(temp.getCalories() < caloriesSum) {
            withList = getSubset(excluding, (caloriesSum - temp.getCalories()));
            if ( withList==null) {
                withList = new HashSet<Menu>();

            }
            if (withList.isEmpty()) {
                withList.add(new Menu());

            }
            for(Menu foodMenu : withList) {
                // add temp to all of them
                foodMenu.add(temp);
            }

        }

        /*
         * without case: cases that dosesn't include the temp
         */
        HashSet<Menu> withoutList = getSubset(excluding, caloriesSum);
        if (withoutList!=null) {

            withList.addAll(withoutList);
        }

        return withList;
    }


}

 class Menu{
    HashSet<Food> menu = new HashSet<Food>();

    int calories =0;

    int getCalories() {
        return calories;
    }

    void add(Food food) {
        if(!menu.contains(food)) {
            menu.add(food);
            calories += food.calories;
        }
    }

    void add(Menu another) {
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
            sb.replace(sb.lastIndexOf(","), sb.length(), ".");
        }
        return sb.toString();
    }

}

package com.example.demouser.caloriescounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        caloriesCounter.setUserNone(intent.getBooleanExtra(MainActivity.NO_RESTRICTIONS, false));
        caloriesCounter.setUserVegan(intent.getBooleanExtra(MainActivity.VEGAN, false));
        caloriesCounter.setUserNutFree(intent.getBooleanExtra(MainActivity.NUT_FREE, false));
        caloriesCounter.setUserHalal(intent.getBooleanExtra(MainActivity.HALAL, false));
        int calories = intent.getIntExtra(MainActivity.CALORIES, 1500);

        // get the edible appetizers, main course and dessert
        ArrayList<Food> appetizers = caloriesCounter.getEdibleAppetizers();
        ArrayList<Food> mainCourse = caloriesCounter.getEdibleMainCourse();
        ArrayList<Food> dessert = caloriesCounter.getEdibleDessert();

    }



    public List<Menu> getSubset(List<Food> menu, int caloriesSum){
        //base case
        if (caloriesSum < 0 || menu==null) {
            return null;
        }
        //if it is the last thing on menu, return it
        if (menu.size()==1 ) {
            // lm = list of menu
            List<Menu> lm = new ArrayList<Menu>();
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

        List<Menu> withList = getSubset(menu, caloriesSum-temp.calories);
        if (withList.isEmpty()) {
            withList = new ArrayList<Menu>();
            withList.add(new Menu());

        }
        for(Menu foodMenu : withList) {
            // add temp to all of them
            foodMenu.add(temp);
        }

        /*
         * without case: cases that dosesn't include the temp
         */
        List<Menu> withoutList = getSubset(menu, caloriesSum);
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

}

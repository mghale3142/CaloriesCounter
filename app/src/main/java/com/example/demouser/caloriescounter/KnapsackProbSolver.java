package com.example.demouser.caloriescounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demouser on 1/13/17.
 */

public class KnapsackProbSolver {

    public  List<Menu> getSubset(List<Food> menu, int caloriesSum){
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

        void add(Menu another) {
            menu.addAll(another.menu);
            calories+=another.getCalories();
        }

    }
}

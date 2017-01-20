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
import java.util.Objects;

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
            if(menu.getCalories()>0) {
                sbAppetizer.append("<div>");
                sbAppetizer.append(menu.toString());
                sbAppetizer.append("</div>");
            }
        }
        ((TextView) findViewById(R.id.appetizer_menu)).setText(Html.fromHtml(sbAppetizer.toString(), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));


        StringBuilder sbMain = new StringBuilder();
        //now put them in the text box
        for(Menu menu : mainCourse) {
            if(menu.getCalories()>0) {
                sbMain.append("<div>");
                sbMain.append(menu.toString());
                sbMain.append("</div>");
            }
        }
        ((TextView) findViewById(R.id.main_menu)).setText(Html.fromHtml(sbMain.toString(), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));


        StringBuilder sbDessert = new StringBuilder();
        //now put them in the text box
        for(Menu menu : dessert) {
            if(menu.getCalories()>0) {
                sbDessert.append("<div>");
                sbDessert.append(menu.toString());
                sbDessert.append("</div>");
            }
        }
        ((TextView) findViewById(R.id.dessert_menu)).setText(Html.fromHtml(sbDessert.toString(), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));

        ((Button) findViewById(R.id.reset_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static HashSet<Menu> getSubset(HashSet<Food> menu, int caloriesSum) {
//        HashSet<Menu> sets = new HashSet<Menu>();
//        if (menu.isEmpty()) {
//            sets.add(new Menu());
//            return sets;
//        }
//        Food head = menu.iterator().next();
//        menu.remove(head);
//        if (head.getCalories() < caloriesSum) {
//            //HashSet<Food> rest = new HashSet<Food>(menu.remove(head));
//            for (Menu set : getSubset(menu, caloriesSum - head.getCalories())) {
//                Menu newSet = new Menu();
//
//                newSet.add(head);
//                newSet.add(set);
//                sets.add(newSet);
//                if(set.getCalories() > 0) {
//                    sets.add(set);
//                }
//            }
//
//        }
//
//        sets.addAll(getSubset(menu, caloriesSum));
////        for (Menu set : getSubset(menu, caloriesSum)) {
////            Menu newSet = new Menu();
////            if(head.getCalories() < caloriesSum) {
////                newSet.add(head);
////                newSet.add(set);
////                sets.add(newSet);
////            }
////            sets.add(set);
//
// //       }
//        return sets;
        //base case
        if (caloriesSum < 0 || menu==null || menu.isEmpty()) {
            return null;
        }
//        //if it is the last thing on menu, return it
//        if (menu.size()==1) {
//            // lm = list of menu
//            HashSet<Menu> lm = new HashSet<Menu>();
//            Food tempFood = menu.iterator().next();
//            if (tempFood.calories < caloriesSum) {
//
//                Menu tempMenu =  new Menu();
//                tempMenu.add(tempFood);
//                lm.add(tempMenu);
//            }
//            return lm;
//        }

        //working case

        // there are two possiblities, add the first thing, or not
        //*         * with case     * reduce the calories sum/
        Food head = menu.iterator().next();
        // remove it
        menu.remove(head);
        HashSet<Food> excluding = new HashSet<>(menu);
        HashSet<Menu> withList = new HashSet<Menu>();
        if(head.getCalories() < caloriesSum) {
            withList = getSubset(excluding, (caloriesSum - head.getCalories()));
            if ( withList==null) {
                withList = new HashSet<Menu>();

            }
            if (withList.isEmpty()) {
                withList.add(new Menu());

            }
            HashSet<Menu> doubleList = new HashSet<Menu>();
            for(Menu foodMenu : withList) {
                // add temp to all of them
                Menu tempAdder = new Menu();
                tempAdder.add(head);
                tempAdder.add(foodMenu);
                doubleList.add(tempAdder);
            }

            withList.addAll((doubleList));
        }

        // without case: cases that dosesn't include the temp

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

     public Menu(){

     }
     public Menu(HashSet<Food> temp) {
         menu = new HashSet<Food>(temp);
         calories=0;
         for(Food f : menu){
             calories+=f.getCalories();
         }
     }
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

     @Override
     public boolean equals(Object o){

         if(o instanceof  Menu){
            if(menu.equals(((Menu) o).menu)){
                return true;
            }
         }
         return false;
     }

     @Override
     public int hashCode() {
         return menu.hashCode();
     }

}

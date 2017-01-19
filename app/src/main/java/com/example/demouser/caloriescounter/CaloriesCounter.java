package com.example.demouser.caloriescounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demouser on 1/13/17.
 */

public class CaloriesCounter {

    private HashMap<String, HashSet<Food>> appetizer = new HashMap<>();
    private HashMap<String, HashSet<Food>> mainCourse = new HashMap<>();
    private HashMap<String, HashSet<Food>> desserts = new HashMap<>();

    private String[] foodOptions = {"noNuts", "vegan", "halal", "none"};
    private boolean[] optionsChosen = {false, false, false, false};

    public CaloriesCounter() {
        initMenu();
    }

    /**
     * Food(String name, int calories, boolean containsNuts, boolean vegan, boolean halal)
     */

    private void initMenu(){
        // appetizer
        Food appe1 = new Food("Chicken salad", 50, true, false, true);
        Food appe2 = new Food("Shrimp salad", 52, true, false, true);
        Food appe3 = new Food("Vegetable salad", 35, false, true, true);
        Food appe4 = new Food("Mozzarella sticks", 65, false, false, true);

        Food[] appetizerList = new Food[]{appe1, appe2, appe3, appe4};

        for(Food f : appetizerList){
            if(!f.containsNuts){
                if (!appetizer.containsKey("noNuts")) {
                    appetizer.put("noNuts", new HashSet<Food>());
                }
                appetizer.get("noNuts").add(f);
            }

            if(f.isVegan()){
                if (!appetizer.containsKey("vegan")) {
                    appetizer.put("vegan", new HashSet<Food>());
                }
                appetizer.get("vegan").add(f);
            }

            if(f.isHalal()){
                if (!appetizer.containsKey("halal")) {
                    appetizer.put("halal", new HashSet<Food>());
                }
                appetizer.get("halal").add(f);
            }

            // all food should go to none because they can eat everything
            //if(f.isNone()){
            //if (f.isNone()) {
                if (!appetizer.containsKey("none")) {
                    appetizer.put("none", new HashSet<Food>());
                }
                appetizer.get("none").add(f);
            //}
        }

        // main course
        Food main1 = new Food("Hot and spicy ramen", 490, false, false, true);
        Food main2 = new Food("Red curry shrimp", 585, false, false, true);
        Food main3 = new Food("Vegan bean burrito", 350, true, true, true);
        Food main4 = new Food("Spicy pork bulgogi", 464, false, false, false);

        Food[] mainList = new Food[]{main1, main2, main3, main4};

        for(Food f : mainList){
            if(!f.containsNuts){
                if (!mainCourse.containsKey("noNuts")) {
                    mainCourse.put("noNuts", new HashSet<Food>());
                }
                mainCourse.get("noNuts").add(f);
            }

            if(f.isVegan()){
                if (!mainCourse.containsKey("vegan")) {
                    mainCourse.put("vegan", new HashSet<Food>());
                }
                mainCourse.get("vegan").add(f);
            }

            if(f.isHalal()){
                if (!mainCourse.containsKey("halal")) {
                    mainCourse.put("halal", new HashSet<Food>());
                }
                mainCourse.get("halal").add(f);
            }

            // all food should go to none because they can eat everything
            //if(f.isNone()){
                if (!mainCourse.containsKey("none")) {
                    mainCourse.put("none", new HashSet<Food>());
                }
                mainCourse.get("none").add(f);
        }

        // desserts
        Food dess1 = new Food("Green tea mochi ice cream", 100, false, false, true);
        Food dess2 = new Food("Strawberry & mango pudding", 283, false, false, true);
        Food dess3 = new Food("Chocolate waffle", 225, true, true, true);
        Food dess4 = new Food("Mango madness", 98, false, true, true);

        Food[] dessertList = new Food[]{dess1, dess2, dess3, dess4};

        for(Food f : dessertList){
            if(!f.containsNuts){
                if (!desserts.containsKey("noNuts")) {
                    desserts.put("noNuts", new HashSet<Food>());
                }
                desserts.get("noNuts").add(f);
            }

            if(f.isVegan()){
                if (!desserts.containsKey("vegan")) {
                    desserts.put("vegan", new HashSet<Food>());
                }
                desserts.get("vegan").add(f);
            }

            if(f.isHalal()){
                if (!desserts.containsKey("halal")) {
                    desserts.put("halal", new HashSet<Food>());
                }
                desserts.get("halal").add(f);
            }

            // all food should go to none because they can eat everything
            //if(f.isNone()){
                if (!desserts.containsKey("none")) {
                    desserts.put("none", new HashSet<Food>());
                }
                desserts.get("none").add(f);
            //}
        }
    }

    /**
     * no restrictions
     * @param userNone
     */
    public void setUserNone(boolean userNone) {
        // this basically means that the user can eat halal food, glutten free, nut free and other foods
        for (int i =0; i < optionsChosen.length; i++ ) {
            //set all to true
            optionsChosen[i] = true;
        }
    }

    public void setUserNutFree(boolean userNutFree) {
        // nutfree is 0th
        if (userNutFree) {
        optionsChosen[0] = true;
        optionsChosen[optionsChosen.length-1] = false;
        }
        else {
            optionsChosen[0] = false;

        }

    }

    public void setUserHalal(boolean userHalal) {
        if (userHalal) {
            optionsChosen[2] = true;
            optionsChosen[optionsChosen.length - 1] = false;
        }
        else {
            optionsChosen[2] = true;

        }

    }

    public void setUserVegan(boolean userVegan) {
        if(userVegan) {
            optionsChosen[1] = true;
            optionsChosen[optionsChosen.length - 1] = false;
        }
        else {
            optionsChosen[1] = true;
        }

    }

    public HashSet<Food> getEdibleAppetizers() {

        // IF NONE, RETURN THE NONE, ELSE, DO INTERSECTION OF FOODS
        if (optionsChosen[optionsChosen.length-1]) {
            return appetizer.get(foodOptions[optionsChosen.length-1]);
        }
        HashSet<Food> finalResult = null;

        for(int i = 0 ; i < optionsChosen.length-1; i++) {
                //intersection
                if (optionsChosen[i]) {
                    if (finalResult == null) {
                        finalResult = appetizer.get(foodOptions[i]);
                    }
                    else {
                        //find intersection
                        finalResult.retainAll(appetizer.get(foodOptions[i]));
                    }
                }
        }
        return finalResult;
    }


    public HashSet<Food> getEdibleMainCourse() {
        if(optionsChosen[optionsChosen.length-1]) {
            return mainCourse.get(foodOptions[optionsChosen.length-1]);
        }
        HashSet<Food> finalResult = null;

        for(int i = 0 ; i < optionsChosen.length-1; i++) {

                //intersection
                if (optionsChosen[i]) {
                    if (finalResult == null) {
                        finalResult = mainCourse.get(foodOptions[i]);
                    }
                    else {
                        //find intersection
                        finalResult.retainAll(mainCourse.get(foodOptions[i]));
                    }
                }

        }
        return finalResult;
    }

    public HashSet<Food> getEdibleDessert() {
        if(optionsChosen[optionsChosen.length-1]) {
            return desserts.get(foodOptions[optionsChosen.length-1]);
        }
        HashSet<Food> finalResult = null;
        for(int i = 0 ; i < optionsChosen.length-1; i++) {

                //intersection
                if (optionsChosen[i]) {
                    if (finalResult == null) {
                        finalResult = desserts.get(foodOptions[i]);
                    }
                    else {
                        //find intersection
                        finalResult.retainAll(desserts.get(foodOptions[i]));
                    }
                }

        }
        return finalResult;
    }
}

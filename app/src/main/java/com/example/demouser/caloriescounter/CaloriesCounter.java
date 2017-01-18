package com.example.demouser.caloriescounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by demouser on 1/13/17.
 */

public class CaloriesCounter {

    private HashMap<String, Set<Food>> appetizer = new HashMap<>();
    private HashMap<String, Set<Food>> mainCourse = new HashMap<>();
    private HashMap<String, Set<Food>> desserts = new HashMap<>();

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
        Food appe1 = new Food("chicken salad", 50, true, false, true);
        Food appe2 = new Food("shrimp salad", 52, true, false, true);
        Food appe3 = new Food("vegetable salad", 35, false, true, true);
        Food appe4 = new Food("mozzarella sticks", 65, false, false, true);

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
        Food main1 = new Food("hot and spicy ramen", 490, false, false, true);
        Food main2 = new Food("red curry shrimp", 585, false, false, true);
        Food main3 = new Food("vegan bean burrito", 350, true, true, true);
        Food main4 = new Food("spicy pork bulgogi", 464, false, false, false);

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
        Food dess1 = new Food("green tea mochi ice cream", 100, false, false, true);
        Food dess2 = new Food("strawberry & mango pudding", 283, false, false, true);
        Food dess3 = new Food("chocolate waffle", 225, true, true, true);
        Food dess4 = new Food("mango madness", 98, false, true, true);

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
        optionsChosen[0] = true;
    }

    public void setUserHalal(boolean userHalal) {
        optionsChosen[2] = true;
    }

    public void setUserVegan(boolean userVegan) {
        optionsChosen[1] = true;
    }

    public ArrayList<Food> getEdibleAppetizers() {
        ArrayList<Food> result = new ArrayList<Food>();
        Set<Food> finalResult = null;

        return result;
    }


}

package com.example.demouser.caloriescounter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by demouser on 1/13/17.
 */

public class CaloriesCounter {

    private HashMap<String, ArrayList<String>> appetizer = new HashMap<>();
    private HashMap<String, ArrayList<String>> mainCourse = new HashMap<>();
    private HashMap<String, ArrayList<String>> desserts = new HashMap<>();

    public CaloriesCounter() {
        initMenu();
    }

    private void initMenu(){

    }
}

package com.example.demouser.caloriescounter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by demouser on 1/20/17.
 */

public class ResultTest {
    private HashSet<Food> menu;
    private  int caloriesSum = 1000;

    @Before
    public void setUp(){
        //if all things are
        Food food1 = new Food("Hummus", 83, true, true, true);
        Food food2 = new Food("Vietnamese Spring Roll", 121, true, false, true);

        Food food3 = new Food("Shrimp fried rice", 321, true, false, true);
        Food food4 = new Food("Vegetable stir fry", 200, true, true, true);
        menu = new HashSet<Food>();
        menu.add(food1);
        menu.add(food2);
        menu.add(food3);
        menu.add(food4);

    }

    @Test
    public void getSubsetTest() throws Exception {
        HashSet<Menu> returned = Result.getSubset( menu,  caloriesSum);
        //should be 12
        for (Menu m: returned) {
            System.out.println(m.toString() + ": "+ m.getCalories());


        }
        assertEquals(4, 2 + 2);
    }
}

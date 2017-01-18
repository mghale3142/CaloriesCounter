package com.example.demouser.caloriescounter;

/**
 * Created by demouser on 1/13/17.
 */

public class Food implements Comparable {

    String name;
    int calories;
    boolean containsNuts;
    boolean vegan;
    boolean halal;
    boolean none;

    public Food(){
        this.name = null;
    }

    public Food(String name, int calories, boolean containsNuts, boolean vegan, boolean halal){
        this.name = name;
        this.calories = calories;
        this.containsNuts = containsNuts;
        this.vegan = vegan;
        this.halal = halal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isContainsNuts() {
        return containsNuts;
    }

    public void setContainsNuts(boolean containsNuts) {
        this.containsNuts = containsNuts;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isHalal() {
        return halal;
    }

    public void setHalal(boolean halal) {
        this.halal = halal;
    }

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = true;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Food) {
            if (this.calories < ((Food) o).calories) {
                return -1;
            }
            if (this.calories == ((Food) o).calories) {
                return 0;
            }
            if (this.calories > ((Food) o).calories) {
                return 1;
            }
        }
        return 0;
    }
}


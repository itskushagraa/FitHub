package model;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;

import persistance.Writable;


/**
 * Represents a diet plan for a week.
 * Stores information about the meals planned for each day of the week.
 * Each day is mapped to a list of three meals: Breakfast, Lunch, and Dinner.
 * This allows users to track their planned meals for each day and their
 * nutritional values.
 */

public class DietPlan implements Writable { 
    private Map<String, List<Meal>> meals; // each day of a week (String) mapped to a list of meals

    /*
     * REQUIRES:
     * - weeklyPlan must contain exactly 7 keys of type String
     * - the keys must be limited to the days of the week
     * - each key must have a List<Meal> value
     * - each List<Meal> must have exactly 3 meals: Breakfast, Lunch, Dinner
     * EFFECTS: Initializes the diet plan with the given weekly meal plan
     */
    public DietPlan(Map<String, List<Meal>> meals) {
        this.meals = meals;
    }

    // EFFECTS: returns DietPlan as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        for(Map.Entry<String, List<Meal>> entry : this.meals.entrySet()) {
            String day = entry.getKey();
            List<Meal> meals = entry.getValue();

            JSONArray jsonMealsList = new JSONArray();
            for (Meal meal : meals) {
                jsonMealsList.put(meal.toJson());
            }

            json.put(day, jsonMealsList);
        }

        return json;
    }
    
    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * - mealType must be one of "Breakfast", "Lunch", or "Dinner"
     * - meal must not be null
     * MODIFIES: this.meals
     * EFFECTS:
     * - Adds the given meal to the specified day and meal type
     * - If a meal already exists for the given day and meal type, it will be
     * replaced
     */
    public void addMeal(String day, String mealType, Meal mealToAdd) {
        List<Meal> dayMeals = this.meals.get(day);
        switch (mealType) {
            case "Breakfast":
                dayMeals.set(0, mealToAdd);
                break;
            case "Lunch":
                dayMeals.set(1, mealToAdd);
                break;
            case "Dinner":
                dayMeals.set(2, mealToAdd);
                break;
            default:
                break;
            // Case Never Reached
        }
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * - mealType must be one of "Breakfast", "Lunch", or "Dinner"
     * - There must be a meal set for the given day and meal type
     * MODIFIES: this.meals
     * EFFECTS:
     * - Removes the meal from the specified day and meal type by setting it to null
     */
    public void removeMeal(String day, String mealType) {
        List<Meal> dayMeals = this.meals.get(day);
        switch (mealType) {
            case "Breakfast":
                dayMeals.set(0, null);
            case "Lunch":
                dayMeals.set(1, null);
            case "Dinner":
                dayMeals.set(2, null);
            default:
                // Case Never Reached
        }
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * MODIFIES: this.meals
     * EFFECTS:
     * - Clears all meals for the specified day (sets them to null)
     */
    public void clearDay(String day) {
        List<Meal> dayMeals = this.meals.get(day);
        for (int i = 0; i < dayMeals.size(); i++) {
            dayMeals.set(i, null);
        }
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total calorie count for the specified day based on all meals
     * - If no meals are set for the day, returns 0
     */
    public int calculateDailyCalories(String day) {
        int ret = 0;

        for (int i = 0; i < 3; i++) {
            if (this.meals.get(day).get(i) != null) {
                ret += this.meals.get(day).get(i).getCalories();
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total quantity of food consumed during a day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyQuantity(String day) {
        int ret = 0;

        for (int i = 0; i < 3; i++) {
            if (this.meals.get(day).get(i) != null) {
                ret += this.meals.get(day).get(i).getQuantity();
            }
        }

        return ret;
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total protein consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyProtein(String day) {
        int ret = 0;

        for (int i = 0; i < 3; i++) {
            if (this.meals.get(day).get(i) != null) {
                ret += this.meals.get(day).get(i).getProtein();
            }
        }

        return ret;
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total fat consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyFat(String day) {
        int ret = 0;

        for (int i = 0; i < 3; i++) {
            if (this.meals.get(day).get(i) != null) {
                ret += this.meals.get(day).get(i).getFat();
            }
        }

        return ret;
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total carbohydrates consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyCarb(String day) {
        int ret = 0;

        for (int i = 0; i < 3; i++) {
            if (this.meals.get(day).get(i) != null) {
                ret += this.meals.get(day).get(i).getCarb();
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total calorie count for the entire week based on all meals
     */
    public int calculateWeeklyCalories() {
        int ret = 0;

        for (String day : this.meals.keySet()) {
            List<Meal> dailyMeals = this.meals.get(day);

            for (Meal meal : dailyMeals) {
                if (meal != null) {
                    ret += meal.getCalories();
                }
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total quantity of food consumed during a week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyQuantity() {
        int ret = 0;

        for (String day : this.meals.keySet()) {
            List<Meal> dailyMeals = this.meals.get(day);

            for (Meal meal : dailyMeals) {
                if (meal != null) {
                    ret += meal.getQuantity();
                }
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total protein consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyProtein() {
        int ret = 0;

        for (String day : this.meals.keySet()) {
            List<Meal> dailyMeals = this.meals.get(day);

            for (Meal meal : dailyMeals) {
                if (meal != null) {
                    ret += meal.getProtein();
                }
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total fat consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyFat() {
        int ret = 0;

        for (String day : this.meals.keySet()) {
            List<Meal> dailyMeals = this.meals.get(day);

            for (Meal meal : dailyMeals) {
                if (meal != null) {
                    ret += meal.getFat();
                }
            }
        }

        return ret;
    }

    /*
     * EFFECTS:
     * - Returns the total carbs consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyCarb() {
        int ret = 0;

        for (String day : this.meals.keySet()) {
            List<Meal> dailyMeals = this.meals.get(day);

            for (Meal meal : dailyMeals) {
                if (meal != null) {
                    ret += meal.getCarb();
                }
            }
        }

        return ret;
    }

    /*
     * GETTERS:
     */
    public Map<String, List<Meal>> getCompleteWeeklyPlan() {
        return this.meals;
    }

    public Meal getMeal(String day, String type) {
        List<Meal> dayMeals = this.meals.get(day);

        for (Meal meal : dayMeals) {
            if (meal != null && meal.getType().equals(type)) {
                return meal;
            }
        }

        return null;
    }
}

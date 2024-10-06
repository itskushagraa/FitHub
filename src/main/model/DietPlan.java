package model;

import java.util.List;
import java.util.Map;

/**
 * Represents a diet plan for a week.
 * Stores information about the meals planned for each day of the week.
 * Each day is mapped to a list of three meals: Breakfast, Lunch, and Dinner.
 * This allows users to track their planned meals for each day and their
 * nutritional values.
 */

public class DietPlan {
    private Map<String, List<Meal>> meals; // each day of a week (String) mapped to a list of meals

    /*
     * REQUIRES:
     * - weeklyPlan must contain exactly 7 keys of type String
     * - the keys must be limited to the days of the week
     * - each key must have a List<Meal> value
     * - each List<Meal> must have exactly 3 meals: Breakfast, Lunch, Dinner
     * EFFECTS: Initializes the diet plan with the given weekly meal plan
     */
    public DietPlan(Map<String, List<Meal>> weeklyPlan) {
        // STUB TODO
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
    public void addMeal(String day, String mealType, Meal meal) {
        // STUB TODO
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
        // STUB TODO
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * MODIFIES: this.meals
     * EFFECTS:
     * - Clears all meals for the specified day (sets them to null)
     */
    public void clearDay(String day) {
        // STUB TODO
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total calorie count for the specified day based on all meals
     * - If no meals are set for the day, returns 0
     */
    public int calculateDailyCalories(String day) {
        return 0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total quantity of food consumed during a day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyQuantity(String day) {
        return 0.0; // STUB TODO
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total protein consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyProtein(String day) {
        return 0.0; // STUB TODO
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total fat consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyFat(String day) {
        return 0.0; // STUB TODO
    }

    /*
     * REQUIRES:
     * - day must be a valid day of the week (e.g., "Monday", "Tuesday", etc.)
     * EFFECTS:
     * - Returns the total carbohydrates consumed in a given day
     * - If no meals are set for the day, returns 0
     */
    public double calculateDailyCarb(String day) {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total calorie count for the entire week based on all meals
     * - If no meals are set for any day, returns 0
     */
    public int calculateWeeklyCalories() {
        return 0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total quantity of food consumed during a week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyQuantity() {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total protein consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyProtein() {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total fat consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyFat() {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS:
     * - Returns the total carbs consumed during the week
     * - If no meals are set for any day, returns 0
     */
    public double calculateWeeklyCarb() {
        return 0.0; // STUB TODO
    }

    /*
     * GETTERS:
     */
    public Map<String, List<Meal>> getCompleteWeeklyPlan() {
        return null; // STUB TODO
    }

    public Meal getSpecificMeal(String day, String type) {
        return null; // STUB TODO
    }
}

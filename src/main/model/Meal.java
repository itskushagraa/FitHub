package model;

import java.util.List;

/**
 * Represents a meal consumed in a day.
 * Stores information about the meal:
 * - Type (Breakfast, Lunch, Dinner)
 * - List of ingredients
 * - Calorific value (in kcal)
 * - Quantity (in grams)
 * - Macronutrient information (Protein, Fat, Carbohydrates)
 * Allows the user to add or remove ingredients and modify attributes, including
 * calories, quantity, and macronutrients.
 */

public class Meal {
    private String name;
    private String type;
    private List<String> ingredients;
    private int calories;
    private double quantity;
    private double protein;
    private double fat;
    private double carb;

    /*
     * REQUIRES:
     * - type must be one of "Breakfast", "Lunch", or "Dinner"
     * - ingredients.size() >= 0
     * - calories >= 0
     * - quantity > 0
     * - 0 <= [protein, fat, carb] <= quantity
     * - 0 <= (protein + fat + carb) <= quantity
     */
    public Meal(String name, String type, List<String> ingredients, int calories, double quantity, double protein,
            double fat, double carb) {
        // STUB TODO
    }

    /*
     * REQUIRES: ingredient.length() > 0
     * MODIFIES: this.ingredients
     * EFFECTS: adds the given ingredient to the list of ingredients
     */
    public void addIngredient(String ingredient) {
        // STUB TODO
    }

    /*
     * REQUIRES:
     * - ingredient.length() > 0
     * - ingredient must be in present in this.ingredients
     * - this.ingredients.size() > 0
     * MODIFIES: this.ingredients
     * EFFECTS: removes the given ingredient from the list of ingredients
     */
    public void removeIngredient(String ingredient) {
        // STUB TODO
    }

    /*
     * EFFECTS: returns the number of calories per gram of the meal
     */
    public int calculateCaloriesPerGram() {
        return 0; // STUB TODO
    }

    /*
     * EFFECTS: returns the number of grams of protein per gram of the meal
     */
    public double calculateProteinPerGram() {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS: returns the number of grams of protein per gram of the meal
     */
    public double calculateFatPerGram() {
        return 0.0; // STUB TODO
    }

    /*
     * EFFECTS: returns the number of grams of carbohydrates per gram of the meal
     */
    public double calculateCarbPerGram() {
        return 0.0; // STUB TODO
    }

    /*
     * SETTERS:
     */
    public void setName(String name) {
        // STUB TODO
    }

    public void setType(String type) {
        // STUB TODO
    }

    /*
     * MODIFIES: 
     * - this.quantity 
     * - this.calories
     * - this.protein
     * - this.fat 
     * - this.carb
     * EFFECTS: 
     * - changes this.quantity to the given quantity
     * - updates calories and macro quantities accordingly
     */
    public void setQuantity(double quantity) {
        // STUB TODO
    }

    /*
     * GETTERS:
     */
    public String getName() {
        return ""; // STUB TODO
    }

    public String getType() {
        return ""; // STUB TODO
    }

    public List<String> getIngredients() {
        return null; // STUB TODO
    }

    public int getCalories() {
        return 0; // STUB TODO
    }

    public double getQuantity() {
        return 0.0; // STUB TODO
    }

    public double getProtein() {
        return 0.0; // STUB TODO
    }

    public double getFat() {
        return 0.0; // STUB TODO
    }

    public double getCarb() {
        return 0.0; // STUB TODO
    }
}

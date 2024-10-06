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
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
        this.calories = calories;
        this.quantity = quantity;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    /*
     * REQUIRES: ingredient.length() > 0
     * MODIFIES: this.ingredients
     * EFFECTS: adds the given ingredient to the list of ingredients
     */
    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
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
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (this.ingredients.get(i).equalsIgnoreCase(ingredient)) {
                this.ingredients.remove(i);
            }
        }
    }

    /*
     * EFFECTS: returns the number of calories per gram of the meal
     */
    public int calculateCaloriesPerGram() {
        return (int) (this.calories / this.quantity);
    }

    /*
     * EFFECTS: returns the number of grams of protein per gram of the meal
     */
    public double calculateProteinPerGram() {
        return this.protein / this.quantity;
    }

    /*
     * EFFECTS: returns the number of grams of protein per gram of the meal
     */
    public double calculateFatPerGram() {
        return this.fat / this.quantity;
    }

    /*
     * EFFECTS: returns the number of grams of carbohydrates per gram of the meal
     */
    public double calculateCarbPerGram() {
        return this.carb / this.quantity;
    }

    /*
     * SETTERS:
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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
        double ratio = quantity / this.quantity;
        this.quantity = quantity;
        this.calories = (int) (this.calories * ratio);
        this.protein *= ratio;
        this.fat *= ratio;
        this.carb *= ratio;
    }

    /*
     * GETTERS:
     */
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public int getCalories() {
        return this.calories;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public double getProtein() {
        return this.protein;
    }

    public double getFat() {
        return this.fat;
    }

    public double getCarb() {
        return this.carb;
    }
}

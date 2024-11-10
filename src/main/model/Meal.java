package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

/**
 * Represents a meal consumed in a day.
 * Stores information about the meal:
 * - Type (Breakfast, Lunch, Dinner)
 * - List of ingredients
 * - Calorific value (in kcal)
 * - Quantity (in grams)
 * - Macronutrient information (Protein, Fat, Carbohydrates)
 */

public class Meal implements Writable {
    private String name;
    private String type;
    private List<String> ingredients;
    private double calories;
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
    public Meal(String name, String type, List<String> ingredients, double calories, double quantity, double protein,
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

    // EFFECTS: returns a Meal as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("type", this.type);

        JSONArray jsonIngredients = new JSONArray();
        for (String ingredient : this.ingredients) {
            jsonIngredients.put(ingredient);
        }
        json.put("ingredients", jsonIngredients);
        json.put("calories", this.calories);
        json.put("quantity", this.quantity);
        json.put("protein", this.protein);
        json.put("fat", this.fat);
        json.put("carb", this.carb);

        return json;
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
    public double calculateCaloriesPerGram() {
        return (this.calories / this.quantity);
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

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
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
        this.calories = this.calories * ratio;
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

    public double getCalories() {
        return Math.round(this.calories * 100.0) / 100.0;
    }

    public double getQuantity() {
        return Math.round(this.quantity * 100.0) / 100.0;
    }

    public double getProtein() {
        return Math.round(this.protein * 100.0) / 100.0;
    }

    public double getFat() {
        return Math.round(this.fat * 100.0) / 100.0;
    }

    public double getCarb() {
        return Math.round(this.carb * 100.0) / 100.0;
    }
}

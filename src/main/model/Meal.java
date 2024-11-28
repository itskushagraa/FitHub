package model;

import java.util.List;
import java.util.ArrayList;

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

    // EFFECTS: logs an event where a meal's information was viewed
    public void mealInfoViewed() {
        EventLog.getInstance().logEvent(new Event("Viewed Meal Info: " + this.name));
    }

    /*
     * SETTERS:
     */
    public void setName(String name) {
        if (!this.name.equalsIgnoreCase(name)) {
            EventLog.getInstance().logEvent(new Event("Updated Name For " + this.name + " To: " + name));
        }
        this.name = name;
    }

    public void setCalories(double calories) {
        if (this.calories != calories) {
            EventLog.getInstance().logEvent(new Event("Updated Calories For " + this.name + " To: " + calories));
        }
        this.calories = calories;
    }

    public void setProtein(double protein) {
        if (this.protein != protein) {
            EventLog.getInstance().logEvent(new Event("Updated Protein For " + this.name + " To: " + protein));
        }
        this.protein = protein;
    }

    public void setFat(double fat) {
        if (this.fat != fat) {
            EventLog.getInstance().logEvent(new Event("Updated Fat For " + this.name + " To: " + fat));
        }
        this.fat = fat;
    }

    public void setCarb(double carb) {
        if (this.carb != carb) {
            EventLog.getInstance().logEvent(new Event("Updated Carbs For " + this.name + " To: " + carb));
        }
        this.carb = carb;
    }

    public void setIngredients(List<String> ingredients) {
        if (!this.ingredients.equals(ingredients)) {
            List<String> addedIngredients = getAddedIngredients(ingredients);
            List<String> removedIngredients = getRemovedIngredients(addedIngredients);
            if (addedIngredients.size() != 0) {
                for (String ing : addedIngredients) {
                    EventLog.getInstance().logEvent(new Event("Added Ingredient To " + this.name + ": " + ing));
                }
            }
            if (removedIngredients.size() != 0) {
                for (String ing : removedIngredients) {
                    EventLog.getInstance().logEvent(new Event("Removed Ingredient From " + this.name + ": " + ing));
                }
            }
        }
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
        if (this.quantity != quantity) {
            // EventLog.getInstance().logEvent(new Event("Updated Quantity For " + this.name
            // + " To: " + quantity));
        }
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

    public List<String> getAddedIngredients(List<String> newIngredients) {
        List<String> addedIngredients = new ArrayList<>();
        for (String ingredient : newIngredients) {
            if (!this.ingredients.contains(ingredient)) {
                addedIngredients.add(ingredient);
            }
        }
        return addedIngredients;
    }

    public List<String> getRemovedIngredients(List<String> newIngredients) {
        List<String> removedIngredients = new ArrayList<>();
        for (String ingredient : this.ingredients) {
            if (!newIngredients.contains(ingredient)) {
                removedIngredients.add(ingredient);
            }
        }
        return removedIngredients;
    }
}

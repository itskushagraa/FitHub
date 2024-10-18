package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDietPlan {
    private DietPlan testPlan;
    private Map<String, List<Meal>> meals = new HashMap<>();

    @BeforeEach
    void runBefore() {
        // creating a sample HashMap of days and meals per day for testing purposes
        List<Meal> mon = new ArrayList<>(Arrays.asList(SCRAMBLED_EGGS_TOAST, CHICKEN_SALAD, GRILLED_FISH_VEGGIES));
        List<Meal> tue = new ArrayList<>(Arrays.asList(OATMEAL_BANANA, QUINOA_BOWL, SPAGHETTI_BOLOGNESE));
        List<Meal> wed = new ArrayList<>(Arrays.asList(AVOCADO_TOAST, TURKEY_SANDWICH, STEAK));
        List<Meal> thu = new ArrayList<>(Arrays.asList(SMOOTHIE, CHICKEN_RICE, CHICKEN_CURRY));
        List<Meal> fri = new ArrayList<>(Arrays.asList(PANCAKES, VEGGIE_SALAD, SHRIMP_STIRFRY));
        List<Meal> sat = new ArrayList<>(Arrays.asList(GREEK_YOGURT, PASTA_PRIMAVERA, BEEF_TACOS));
        List<Meal> sun = new ArrayList<>(Arrays.asList(SIMPLE_PBJ, SALMON_RICE, VEGGIE_STEW));
        meals.put("Monday", mon);
        meals.put("Tuesday", tue);
        meals.put("Wednesday", wed);
        meals.put("Thursday", thu);
        meals.put("Friday", fri);
        meals.put("Saturday", sat);
        meals.put("Sunday", sun);

        testPlan = new DietPlan(meals);
    }

    @Test
    void testConstructor() {
        assertEquals(meals, testPlan.getCompleteWeeklyPlan());
    }

    @Test
    void testToJson() {
        JSONObject json = testPlan.toJson();
        Set<String> expectedDays = new HashSet<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        assertEquals(expectedDays, json.keySet());

        for (Map.Entry<String, List<Meal>> entry : testPlan.getCompleteWeeklyPlan().entrySet()) {
            String day = entry.getKey();
            List<Meal> originalMeals = entry.getValue();
            JSONArray jsonMeals = json.getJSONArray(day);
            assertEquals(originalMeals.size(), jsonMeals.length());
            for (int i = 0; i < originalMeals.size(); i++) {
                Meal originalMeal = originalMeals.get(i);
                Meal parsedMeal = parseMeal(jsonMeals.getJSONObject(i));
                assertTrue(compareMeals(originalMeal, parsedMeal));
            }
        }
    }

    // EFFECTS: returns true if both meals contain the same data even though they're
    // 2 distinct objects
    private boolean compareMeals(Meal m1, Meal m2) {
        return m1.getName().equals(m2.getName())
                && m1.getType().equals(m2.getType())
                && m1.getIngredients().equals(m2.getIngredients())
                && m1.getCalories() == m2.getCalories()
                && m1.getQuantity() == m2.getQuantity()
                && m1.getProtein() == m2.getProtein()
                && m1.getFat() == m2.getFat()
                && m1.getCarb() == m2.getCarb();
    }

    // EFFECTS: parses jsonObject and returns the corresponding Meal object
    private Meal parseMeal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");

        JSONArray jsonIngredients = jsonObject.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < jsonIngredients.length(); i++) {
            ingredients.add(jsonIngredients.getString(i));
        }

        double calories = jsonObject.getDouble("calories");
        double quantity = jsonObject.getDouble("quantity");
        double protein = jsonObject.getDouble("protein");
        double fat = jsonObject.getDouble("fat");
        double carb = jsonObject.getDouble("carb");

        return new Meal(name, type, ingredients, calories, quantity, protein, fat, carb);
    }

    @Test
    void testAddSingleMeal() {
        testPlan.removeMeal("Monday", "Lunch");
        testPlan.addMeal("Monday", "Lunch", QUINOA_BOWL);
        assertEquals(QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
    }

    @Test
    void testAddMultipleMeal() {
        testPlan.removeMeal("Monday", "Dinner");
        testPlan.removeMeal("Tuesday", "Breakfast");
        testPlan.addMeal("Monday", "Dinner", BEEF_TACOS);
        testPlan.addMeal("Tuesday", "Breakfast", SIMPLE_PBJ);
        assertEquals(BEEF_TACOS, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(SIMPLE_PBJ, testPlan.getMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testReplaceSingleMeal() {
        assertEquals(CHICKEN_SALAD, testPlan.getMeal("Monday", "Lunch"));
        testPlan.addMeal("Monday", "Lunch", QUINOA_BOWL);
        assertEquals(QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
    }

    @Test
    void testReplaceMultipleMeal() {
        assertEquals(CHICKEN_SALAD, testPlan.getMeal("Monday", "Lunch"));
        assertEquals(GRILLED_FISH_VEGGIES, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(OATMEAL_BANANA, testPlan.getMeal("Tuesday", "Breakfast"));
        testPlan.addMeal("Monday", "Lunch", QUINOA_BOWL);
        testPlan.addMeal("Monday", "Dinner", STEAK);
        testPlan.addMeal("Tuesday", "Breakfast", SIMPLE_PBJ);
        assertEquals(QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
        assertEquals(STEAK, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(SIMPLE_PBJ, testPlan.getMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testRemoveSingleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        assertNull(testPlan.getMeal("Thursday", "Breakfast"));
    }

    @Test
    void testRemoveMultipleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        testPlan.removeMeal("Thursday", "Lunch");
        testPlan.removeMeal("Thursday", "Dinner");
        assertNull(testPlan.getMeal("Thursday", "Breakfast"));
        assertNull(testPlan.getMeal("Thursday", "Lunch"));
        assertNull(testPlan.getMeal("Thursday", "Dinner"));
    }

    @Test
    void clearSingleDay() {
        testPlan.clearDay("Tuesday");
        assertNull(testPlan.getMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getMeal("Tuesday", "Dinner"));
    }

    @Test
    void clearMultipleDays() {
        testPlan.clearDay("Tuesday");
        testPlan.clearDay("Wednesday");
        testPlan.clearDay("Sunday");
        assertNull(testPlan.getMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getMeal("Tuesday", "Dinner"));
        assertNull(testPlan.getMeal("Wednesday", "Breakfast"));
        assertNull(testPlan.getMeal("Wednesday", "Lunch"));
        assertNull(testPlan.getMeal("Wednesday", "Dinner"));
        assertNull(testPlan.getMeal("Sunday", "Breakfast"));
        assertNull(testPlan.getMeal("Sunday", "Lunch"));
        assertNull(testPlan.getMeal("Sunday", "Dinner"));
    }

    @Test
    void testCalculatorMethods() {
        assertEquals(1400, testPlan.calculateDailyCalories("Saturday"));
        assertEquals(800, testPlan.calculateDailyQuantity("Saturday"));
        assertEquals(52, testPlan.calculateDailyProtein("Saturday"));
        assertEquals(55, testPlan.calculateDailyFat("Saturday"));
        assertEquals(160, testPlan.calculateDailyCarb("Saturday"));
        assertEquals(9420, testPlan.calculateWeeklyCalories());
        assertEquals(5800, testPlan.calculateWeeklyQuantity());
        assertEquals(444, testPlan.calculateWeeklyProtein());
        assertEquals(349, testPlan.calculateWeeklyFat());
        assertEquals(985, testPlan.calculateWeeklyCarb());
    }

    @Test
    void testCalculatorsWhenNull() {
        testPlan.clearDay("Saturday");
        assertEquals(0, testPlan.calculateDailyCalories("Saturday"));
        assertEquals(0, testPlan.calculateDailyQuantity("Saturday"));
        assertEquals(0, testPlan.calculateDailyProtein("Saturday"));
        assertEquals(0, testPlan.calculateDailyFat("Saturday"));
        assertEquals(0, testPlan.calculateDailyCarb("Saturday"));

        testPlan.clearDay("Monday");
        testPlan.clearDay("Tuesday");
        testPlan.clearDay("Wednesday");
        testPlan.clearDay("Thursday");
        testPlan.clearDay("Friday");
        testPlan.clearDay("Sunday");
        assertEquals(0, testPlan.calculateWeeklyCalories());
        assertEquals(0, testPlan.calculateWeeklyQuantity());
        assertEquals(0, testPlan.calculateWeeklyProtein());
        assertEquals(0, testPlan.calculateWeeklyFat());
        assertEquals(0, testPlan.calculateWeeklyCarb());
    }

    /*
     * MEAL OBJECTS FOR TESTING
     * NOTE: I DONT ACTUALLY EAT THESE MEALS EVERYDAY (I wish my meals were this
     * diverse).
     * THIS IS A SAMPLE COLLECTION TAKEN FROM THE INTERNET.
     */

    // Breakfast Meals
    public static final Meal SCRAMBLED_EGGS_TOAST = new Meal(
            "Scrambled Eggs with Toast",
            "Breakfast",
            Arrays.asList("Eggs", "Toast", "Butter"),
            400, 200.0, 20.0, 15.0, 25.0);

    public static final Meal OATMEAL_BANANA = new Meal(
            "Oatmeal with Banana",
            "Breakfast",
            Arrays.asList("Oats", "Banana", "Milk"),
            350, 250.0, 10.0, 5.0, 60.0);

    public static final Meal AVOCADO_TOAST = new Meal(
            "Avocado Toast", "Breakfast",
            Arrays.asList("Bread", "Avocado", "Olive Oil", "Salt", "Pepper"),
            300, 150.0, 4.0, 14.0, 30.0);

    public static final Meal SMOOTHIE = new Meal(
            "Berry Banana Smoothie", "Breakfast",
            Arrays.asList("Banana", "Strawberries", "Greek Yogurt", "Almond Milk", "Honey"),
            220, 300.0, 8.0, 3.0, 45.0);

    public static final Meal PANCAKES = new Meal(
            "Protein Pancakes", "Breakfast",
            Arrays.asList("Whole Wheat Flour", "Egg", "Milk", "Baking Powder", "Protein Powder"),
            400, 200.0, 25.0, 8.0, 50.0);

    public static final Meal GREEK_YOGURT = new Meal(
            "Greek Yogurt Parfait", "Breakfast",
            Arrays.asList("Greek Yogurt", "Strawberries", "Honey", "Granola", "Chia Seeds"),
            350, 250.0, 15.0, 10.0, 40.0);

    public static final Meal SIMPLE_PBJ = new Meal(
            "Peanut Butter and Jelly Sandwich", "Breakfast",
            Arrays.asList("Bread", "Peanut Butter", "Strawberry Jam"),
            350, 150.0, 12.0, 16.0, 30.0);

    // Lunch Meals
    public static final Meal CHICKEN_RICE = new Meal(
            "Chicken and Rice",
            "Lunch",
            Arrays.asList("Chicken Breast", "White Rice", "Olive Oil"),
            550, 300.0, 40.0, 10.0, 70.0);

    public static final Meal VEGGIE_SALAD = new Meal(
            "Vegetable Salad",
            "Lunch",
            Arrays.asList("Lettuce", "Tomato", "Cucumber", "Olive Oil", "Feta Cheese"),
            300, 200.0, 8.0, 20.0, 15.0);

    public static final Meal CHICKEN_SALAD = new Meal(
            "Grilled Chicken Salad", "Lunch",
            Arrays.asList("Chicken Breast", "Lettuce", "Tomato", "Cucumber", "Olive Oil"),
            350, 300.0, 35.0, 12.0, 10.0);

    public static final Meal QUINOA_BOWL = new Meal(
            "Quinoa Bean Bowl", "Lunch",
            Arrays.asList("Quinoa", "Bell Peppers", "Corn", "Black Beans", "Avocado"),
            400, 350.0, 12.0, 18.0, 60.0);

    public static final Meal TURKEY_SANDWICH = new Meal(
            "Turkey Sandwich", "Lunch",
            Arrays.asList("Whole Wheat Bread", "Turkey Breast", "Lettuce", "Tomato", "Mustard"),
            450, 200.0, 20.0, 10.0, 60.0);

    public static final Meal PASTA_PRIMAVERA = new Meal(
            "Pasta Primavera", "Lunch",
            Arrays.asList("Whole Wheat Pasta", "Broccoli", "Carrot", "Zucchini", "Olive Oil"),
            500, 300.0, 12.0, 15.0, 80.0);

    public static final Meal SALMON_RICE = new Meal(
            "Salmon with Brown Rice", "Lunch",
            Arrays.asList("Salmon", "Brown Rice", "Green Beans", "Garlic", "Olive Oil"),
            600, 350.0, 30.0, 25.0, 50.0);

    // Dinner Meals
    public static final Meal GRILLED_FISH_VEGGIES = new Meal(
            "Grilled Fish with Vegetables",
            "Dinner",
            Arrays.asList("Fish Fillet", "Broccoli", "Carrots", "Olive Oil"),
            450, 250.0, 35.0, 18.0, 20.0);

    public static final Meal SPAGHETTI_BOLOGNESE = new Meal(
            "Spaghetti Bolognese",
            "Dinner",
            Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese"),
            700, 350.0, 25.0, 30.0, 85.0);

    public static final Meal STEAK = new Meal(
            "Grilled Steak with Sweet Potatoes", "Dinner",
            Arrays.asList("Beef Steak", "Sweet Potatoes", "Asparagus", "Butter", "Garlic"),
            700, 400.0, 40.0, 30.0, 45.0);

    public static final Meal CHICKEN_CURRY = new Meal(
            "Chicken Curry with Rice", "Dinner",
            Arrays.asList("Chicken Thighs", "Coconut Milk", "Curry Powder", "White Rice", "Onion"),
            650, 350.0, 25.0, 35.0, 60.0);

    public static final Meal SHRIMP_STIRFRY = new Meal(
            "Shrimp Stir Fry", "Dinner",
            Arrays.asList("Shrimp", "Bell Peppers", "Carrot", "Soy Sauce", "Ginger"),
            450, 300.0, 35.0, 10.0, 45.0);

    public static final Meal BEEF_TACOS = new Meal(
            "Beef Tacos", "Dinner",
            Arrays.asList("Ground Beef", "Taco Shells", "Cheddar Cheese", "Lettuce", "Sour Cream"),
            550, 250.0, 25.0, 30.0, 40.0);

    public static final Meal VEGGIE_STEW = new Meal(
            "Vegetable Stew", "Dinner",
            Arrays.asList("Potatoes", "Carrot", "Celery", "Tomato Paste", "Olive Oil"),
            400, 400.0, 8.0, 15.0, 65.0);
}

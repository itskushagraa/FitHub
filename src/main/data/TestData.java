package data;

import java.util.Arrays;

import model.Exercise;
import model.ExerciseSet;
import model.Meal;

/*
 * - THIS CLASS IS A TEMPORARY REPRESENTATION OF DATA ONLY FOR PHASE 1
 * - OBJECTS DECLARED IN THIS CLASS ARE USED FOR TESTING PURPOSES
 * - ONCE PHASE 2 BEGINS, THIS CLASS WILL BE DELETED AND DATA WILL BE 
 *   REPRESENTED USING JSON FILES
 */

public class TestData {

    /*
     * EXERCISE OBJECTS FOR TESTING
     */

    public static final Exercise BENCH_PRESS = new Exercise(
            "Bench Press (Barbell)",
            Arrays.asList("Chest", "Front Delts", "Triceps"),
            Arrays.asList(new ExerciseSet(15, 20)));

    public static final Exercise SQUAT = new Exercise(
            "Squat (Barbell)",
            Arrays.asList("Quads", "Hamstrings", "Glutes"),
            Arrays.asList(new ExerciseSet(10, 60)));

    public static final Exercise LAT_PULLDOWN = new Exercise(
            "Lat Pulldown",
            Arrays.asList("Lats", "Biceps"),
            Arrays.asList(new ExerciseSet(10, 60)));

    public static final Exercise LATERAL_RAISE = new Exercise(
            "Lateral Raise (Cable)",
            Arrays.asList("Side Delts"),
            Arrays.asList(new ExerciseSet(10, 10)));

    public static final Exercise FACE_PULL = new Exercise(
            "Face Pull (Cable)",
            Arrays.asList("Rear Delts"),
            Arrays.asList(new ExerciseSet(10, 20)));

    /*
     * MEAL OBJECTS FOR TESTING
     * NOTE: I DONT ACTUALLY EAT THESE MEALS EVERYDAY (I wish my meals were this diverse). 
     *       THIS IS A SAMPLE COLLECTION TAKEN FROM THE INTERNET.
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

    private TestData() {
    }
}

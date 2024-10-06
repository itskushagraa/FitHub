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

public class TemporaryData {

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
     */

    // Breakfast Meals
    public static final Meal SCRAMBLED_EGGS_TOAST = new Meal(
            "Scrambled Eggs with Toast",
            "Breakfast",
            Arrays.asList("Eggs", "Toast", "Butter"),
            400,
            200.0,
            20.0,
            15.0,
            25.0);

    public static final Meal OATMEAL_BANANA = new Meal(
            "Oatmeal with Banana",
            "Breakfast",
            Arrays.asList("Oats", "Banana", "Milk"),
            350,
            250.0,
            10.0,
            5.0,
            60.0);

    // Lunch Meals
    public static final Meal CHICKEN_RICE = new Meal(
            "Chicken and Rice",
            "Lunch",
            Arrays.asList("Chicken Breast", "White Rice", "Olive Oil"),
            550,
            300.0,
            40.0,
            10.0,
            70.0);

    public static final Meal VEGGIE_SALAD = new Meal(
            "Vegetable Salad",
            "Lunch",
            Arrays.asList("Lettuce", "Tomato", "Cucumber", "Olive Oil", "Feta Cheese"),
            300,
            200.0,
            8.0,
            20.0,
            15.0);

    // Dinner Meals
    public static final Meal GRILLED_FISH_VEGGIES = new Meal(
            "Grilled Fish with Vegetables",
            "Dinner",
            Arrays.asList("Fish Fillet", "Broccoli", "Carrots", "Olive Oil"),
            450,
            250.0,
            35.0,
            18.0,
            20.0);

    public static final Meal SPAGHETTI_BOLOGNESE = new Meal(
            "Spaghetti Bolognese",
            "Dinner",
            Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese"),
            700,
            350.0,
            25.0,
            30.0,
            85.0);

    private TemporaryData() {
    }
}

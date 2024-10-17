package persistance;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import model.DietPlan;
import model.Exercise;
import model.ExerciseSet;
import model.Meal;
import model.UserProfile;
import model.Workout;

/* Represents a leader that reads FitHub data (UserProfile, WorkoutSplit, DietPlan)
 * from JSON data stored in file
 */

public class JsonReader {
    private String source;

    /*
     * REQUIRES: source must be a valid file path
     * EFFECTS: constructs a reader to read from the specified source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * REQUIRES: userProfile.json file must exist and contain valid JSON code
     * EFFECTS: reads UserProfile from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public UserProfile readUserProfile() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserProfile(jsonObject);
    }

    /*
     * REQUIRES: workoutSplit.json file must exist and contain valid JSON code
     * EFFECTS: reads workoutSplit (list of Workout objects) from the file and
     * returns it;
     * throws IOException if an error occurs reading data from file
     */
    public List<Workout> readWorkoutSplit() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseWorkoutSplit(jsonArray);
    }

    /*
     * REQUIRES: dietPlan.json file must exist and contain valid JSON code
     * EFFECTS: reads dietPlan from the file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public DietPlan readDietPlan() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDietPlan(jsonObject);
    }

    /*
     * REQUIRES: source file must be valid and readable
     * EFFECTS: reads source file as a string and returns it;
     * throws IOException if an error occurs reading data from file
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * REQUIRES: JSONObject must represent a valid user profile
     * EFFECTS: parse UserProfile from JSONObject and returns it
     */
    private UserProfile parseUserProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double height = jsonObject.getDouble("height");
        double weight = jsonObject.getDouble("weight");
        int age = jsonObject.getInt("age");
        int intensity = jsonObject.getInt("intensity");
        String goal = jsonObject.getString("goal");
        return new UserProfile(name, height, weight, age, intensity, goal);
    }

    /*
     * REQUIRES: JSONArray must represent a valid list of workouts
     * EFFECTS: parse List<Workout> from JSONObject and returns it
     */
    private List<Workout> parseWorkoutSplit(JSONArray jsonArray) {
        List<Workout> workoutSplit = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonWorkout = jsonArray.getJSONObject(i);
            workoutSplit.add(parseWorkout(jsonWorkout));
        }

        return workoutSplit;
    }

    // EFFECTS: parses a workout from a JSON object and returns it
    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        JSONArray jsonExercises = jsonObject.getJSONArray("exercises");
        List<Exercise> exercises = parseExercises(jsonExercises);

        return new Workout(name, exercises);
    }

    // EFFECTS: parses exercises from a JSON array and returns a list of exercises
    private List<Exercise> parseExercises(JSONArray jsonArray) {
        List<Exercise> exercises = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonExercise = jsonArray.getJSONObject(i);
            exercises.add(parseExercise(jsonExercise));
        }

        return exercises;
    }

    // EFFECTS: parses an exercise from a JSON object and returns it
    private Exercise parseExercise(JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        JSONArray jsonMusclesWorked = jsonObject.getJSONArray("musclesWorked");
        List<String> musclesWorked = new ArrayList<>();
        for (int i = 0; i < jsonMusclesWorked.length(); i++) {
            musclesWorked.add(jsonMusclesWorked.getString(i));
        }

        JSONArray jsonSets = jsonObject.getJSONArray("sets");
        List<ExerciseSet> sets = parseExerciseSets(jsonSets);

        return new Exercise(name, musclesWorked, sets);
    }

    /*
     * EFFECTS: parses exercise sets from a JSON array and returns a list of
     * ExerciseSet objects
     */
    private List<ExerciseSet> parseExerciseSets(JSONArray jsonArray) {
        List<ExerciseSet> sets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject setJson = jsonArray.getJSONObject(i);
            sets.add(parseExerciseSet(setJson));
        }

        return sets;
    }

    // EFFECTS: parses an exercise set from a JSON object and returns it
    private ExerciseSet parseExerciseSet(JSONObject jsonObject) {
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");
        return new ExerciseSet(reps, weight);
    }

    /*
     * REQUIRES: JSONObject must represent a valid user profile
     * EFFECTS: parse DietPlan from JSONObject and returns it
     */
    private DietPlan parseDietPlan(JSONObject jsonObject) {
        Map<String, List<Meal>> dietPlanMap = new HashMap<>();

        for (String day : jsonObject.keySet()) {
            JSONArray jsonMeals = jsonObject.getJSONArray(day);
            List<Meal> meals = new ArrayList<>();

            for (int i = 0; i < jsonMeals.length(); i++) {
                JSONObject jsonMeal = jsonMeals.getJSONObject(i);
                meals.add(parseMeal(jsonMeal));
            }

            dietPlanMap.put(day, meals);
        }

        return new DietPlan(dietPlanMap);
    }

    // EFFECTS: parses a meal from a JSON object and returns it
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
}

package persistance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.FileWriter;

import org.json.JSONObject;
import org.json.JSONArray;

import model.DietPlan;
import model.Exercise;
import model.ExerciseSet;
import model.Meal;
import model.UserProfile;
import model.Workout;

/*
 * Represents a writer that writes FitHub data (UserProfile, DietPlan, WorkoutSplit) to a JSON file
 */

public class JsonWriter {
    private static final int INDENTATION = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens the writer; throws FileNotFoundException if destination file
     * cannot be opened for writing
     */
    public void open() throws IOException {
        writer = new PrintWriter(new FileWriter(destination));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of user profile to file
     */
    public void writeUserProfile(UserProfile userProfile) {
        JSONObject json = new JSONObject();
        json.put("name", userProfile.getName());
        json.put("height", userProfile.getHeight());
        json.put("weight", userProfile.getWeight());
        json.put("age", userProfile.getAge());
        json.put("intensity", userProfile.getIntensity());
        json.put("goal", userProfile.getGoal());

        saveToFile(json.toString(INDENTATION));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of the workout split to file
     */
    public void writeWorkoutSplit(List<Workout> workoutSplit) {
        JSONArray jsonArray = new JSONArray();

        for (Workout workout : workoutSplit) {
            JSONObject jsonWorkout = new JSONObject();
            jsonWorkout.put("name", workout.getName());

            JSONArray jsonExercises = new JSONArray();
            for (Exercise exercise : workout.getExercises()) {
                JSONObject jsonExercise = new JSONObject();
                jsonExercise.put("name", exercise.getName());
                jsonExercise.put("musclesWorked", new JSONArray(exercise.getMusclesWorked()));

                JSONArray jsonSets = new JSONArray();
                for (ExerciseSet set : exercise.getSets()) {
                    JSONObject jsonSet = new JSONObject();
                    jsonSet.put("reps", set.getReps());
                    jsonSet.put("weight", set.getWeight());
                    jsonSets.put(jsonSet);
                }

                jsonExercise.put("sets", jsonSets);
                jsonExercises.put(jsonExercise);
            }
            jsonWorkout.put("exercises", jsonExercises);
            jsonArray.put(jsonWorkout);
        }
        saveToFile(jsonArray.toString(INDENTATION));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of the diet plan to file
     */
    public void writeDietPlan(DietPlan dietPlan) {
        JSONObject jsonDietPlan = new JSONObject();

        for (String day : dietPlan.getCompleteWeeklyPlan().keySet()) {
            JSONArray jsonMeals = new JSONArray();
            List<Meal> meals = dietPlan.getCompleteWeeklyPlan().get(day);

            for (Meal meal : meals) {
                JSONObject jsonMeal = new JSONObject();
                jsonMeal.put("name", meal.getName());
                jsonMeal.put("type", meal.getType());
                jsonMeal.put("ingredients", new JSONArray(meal.getIngredients()));
                jsonMeal.put("calories", meal.getCalories());
                jsonMeal.put("quantity", meal.getQuantity());
                jsonMeal.put("protein", meal.getProtein());
                jsonMeal.put("fat", meal.getFat());
                jsonMeal.put("carb", meal.getCarb());
                jsonMeals.put(jsonMeal);
            }
            jsonDietPlan.put(day, jsonMeals);
        }
        saveToFile(jsonDietPlan.toString(INDENTATION));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes the writer
     */
    public void close() {
        writer.close();
    }

    // EFFECTS: writes string to file with proper indentation
    public void saveToFile(String json) {
        writer.print(json);
    }
}

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import persistance.*;

/**
 * Represents a user profile in FitHub
 * Stores personal data such as name, height, weight, age, fitness goal, weekly
 * workout intensity, bmi, daily calorie consumption, workout split, diet plan
 **/

public class UserProfile implements Writable {
    private String name;
    private double height;
    private double weight;
    private double bmi;
    private int age;
    private int intensity;
    private String goal;
    private double targetCalories;

    private List<Workout> userWorkoutSplit;
    private DietPlan userDietPlan;

    /*
     * REQUIRES:
     * - height > 0
     * - weight > 0
     * - age > 0
     * - name.length() > 0
     * - intensity must be in the range [1, 7] (representing days per week)
     * - goal must be one of "cut", "bulk", "maintain"
     *
     * EFFECTS:
     * - Initializes this UserProfile with the provided name, height, weight, age,
     * intensity level, and goal.
     * - Sets targetCalories and BMI based on the parameters provided
     * - Generates a workout split and diet plan tailored to the aforementioned user
     * attributes
     */
    public UserProfile(String name, double height, double weight, int age, int intensity, String goal)
            throws IOException {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = this.calculateBMI();
        this.age = age;
        this.intensity = intensity;
        this.goal = goal;
        this.targetCalories = this.calculateCalories();
        generateWorkoutList();
        generateDietPlan();
    }

    /*
     * REQUIRES: userProfile.json, workoutSplit.json and dietPlan.json must exist
     * MODIFIES: userProfile.json, workoutSplit.json and dietPlan.json
     * EFFECTS: writes the current state of the user profile, workout split, and
     * diet plan to their respective JSON files, (overwriting any existing data)
     */
    public void save(String userPath, String splitPath, String planPath) throws IOException {
        JsonWriter userWriter = new JsonWriter(userPath);
        JsonWriter splitWriter = new JsonWriter(splitPath);
        JsonWriter planWriter = new JsonWriter(planPath);

        userWriter.open();
        splitWriter.open();
        planWriter.open();

        userWriter.writeUserProfile(this);
        splitWriter.writeWorkoutSplit(this.userWorkoutSplit);
        planWriter.writeDietPlan(this.userDietPlan);

        userWriter.close();
        splitWriter.close();
        planWriter.close();
        EventLog.getInstance().logEvent(new Event("Saved User Data To File"));

    }

    /*
     * REQUIRES: userProfile.json, workoutSplit.json and dietPlan.json must exist
     * and be non-empty
     * MODIFIES: this, this.userWorkoutSplit, this.userDietPlan
     * EFFECTS: updates the current objects with the data from their respective JSON
     * files
     */
    public void load(String userPath, String splitPath, String planPath) throws IOException {
        JsonReader userReader = new JsonReader(userPath);
        JsonReader splitReader = new JsonReader(splitPath);
        JsonReader planReader = new JsonReader(planPath);
        this.name = userReader.readUserProfile().getName();
        this.height = userReader.readUserProfile().getHeight();
        this.weight = userReader.readUserProfile().getWeight();
        this.bmi = userReader.readUserProfile().getBmi();
        this.age = userReader.readUserProfile().getAge();
        this.intensity = userReader.readUserProfile().getIntensity();
        this.goal = userReader.readUserProfile().getGoal();
        this.targetCalories = userReader.readUserProfile().getTargetCalories();
        this.userWorkoutSplit = splitReader.readWorkoutSplit();
        this.userDietPlan = planReader.readDietPlan();
        EventLog.getInstance().logEvent(new Event("Loaded User Data From File"));
    }

    // EFFECTS: returns a UserProfile as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("height", this.height);
        json.put("weight", this.weight);
        json.put("bmi", this.bmi);
        json.put("age", this.age);
        json.put("intensity", this.intensity);
        json.put("goal", this.goal);
        json.put("targetCalories", this.targetCalories);
        JSONArray jsonWorkoutSplit = new JSONArray();
        for (Workout workout : this.userWorkoutSplit) {
            jsonWorkoutSplit.put(workout.toJson());
        }
        json.put("userWorkoutSplit", jsonWorkoutSplit);
        json.put("userDietPlan", this.userDietPlan.toJson());
        return json;
    }

    /*
     * MODIFIES: this.userWorkoutSplit
     * EFFECTS:
     * - reads a workoutSplit from workoutSplit.json
     * - randomizes order of exercises in workoutSplit
     * - sets user's workoutsplit to the new split
     */
    private void generateWorkoutList() throws IOException {
        JsonReader reader = new JsonReader("./data/Generation Dataset/workoutSplit.json");
        List<Workout> workoutSplit = reader.readWorkoutSplit();
        for (int i = 0; i < workoutSplit.size(); i++) {
            Collections.shuffle(workoutSplit.get(i).getExercises());
        }
        this.userWorkoutSplit = workoutSplit;
    }

    /*
     * MODIFIES: this.userDietPlan
     * EFFECTS:
     * - reads a dietPlan from dietPlan.json
     * - randomizes order of meals for each day in dietPlan
     * - adjusts quantites of each meal to match user's calorific goals
     * - sets user's dietPlan to the new dietPlan
     */
    private void generateDietPlan() throws IOException {
        JsonReader reader = new JsonReader("./data/Generation Dataset/dietPlan.json");
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        DietPlan dietPlan = reader.readDietPlan();
        dietPlan = randomize(dietPlan, daysOfWeek);

        for (String day : daysOfWeek) {
            adjustQuantities(dietPlan.getCompleteWeeklyPlan(), day);
        }
        this.userDietPlan = dietPlan;
    }

    /*
     * HELPER FUNCTION FOR generateDietPlan()
     * REQUIRES: new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday",
     * "Thursday", "Friday", "Saturday", "Sunday"));
     * MODIFIES: dietPlan
     * EFFECTS: Adjusts the quantities breakfast, lunch and dinner for a given day
     * in accordance to the user's attributes
     */
    private DietPlan randomize(DietPlan dietPlan, List<String> daysOfWeek) {
        List<Meal> breakfasts = new ArrayList<>();
        List<Meal> lunches = new ArrayList<>();
        List<Meal> dinners = new ArrayList<>();
        for (String day : daysOfWeek) {
            breakfasts.add(dietPlan.getCompleteWeeklyPlan().get(day).get(0));
            lunches.add(dietPlan.getCompleteWeeklyPlan().get(day).get(1));
            dinners.add(dietPlan.getCompleteWeeklyPlan().get(day).get(2));
        }
        Collections.shuffle(breakfasts);
        Collections.shuffle(lunches);
        Collections.shuffle(dinners);

        for (int i = 0; i < 7; i++) {
            List<Meal> newMeals = new ArrayList<>(Arrays.asList(breakfasts.get(i), lunches.get(i), dinners.get(i)));
            dietPlan.getCompleteWeeklyPlan().put(daysOfWeek.get(i), newMeals);
        }
        return dietPlan;
    }

    /*
     * HELPER FUNCTION FOR generateDietPlan()
     * MODIFIES: dietPlan
     * EFFECTS: Adjusts the quantities breakfast, lunch and dinner for a given day
     * in accordance to the user's attributes
     */
    private void adjustQuantities(Map<String, List<Meal>> map, String day) {
        List<Meal> dailyMeals = map.get(day);

        Meal breakfast = dailyMeals.get(0);
        Meal lunch = dailyMeals.get(1);
        Meal dinner = dailyMeals.get(2);

        breakfast.setQuantity((this.calculateCalories() * 0.3) / breakfast.calculateCaloriesPerGram());
        lunch.setQuantity((this.calculateCalories() * 0.3) / lunch.calculateCaloriesPerGram());
        dinner.setQuantity((this.calculateCalories() * 0.4) / dinner.calculateCaloriesPerGram());
    }

    /*
     * EFFECTS:
     * - returns the BMI based on height and weight
     */
    public double calculateBMI() {
        double heightInMeters = this.height / 100.0;
        double bmi = this.weight / (heightInMeters * heightInMeters);
        return Math.round(bmi * 10.0) / 10.0;
    }

    /*
     * EFFECTS:
     * - returns the estimated daily caloric intake based on the user's metrics and
     * fitness goal
     */
    public double calculateCalories() {
        double bmr = 10 * this.weight + 6.25 * this.height - 5 * this.age + 5;
        double intensityMultiplier = 1.2 + ((this.intensity - 1) * (0.4 / 6));
        double adjustedBmr = bmr * intensityMultiplier;

        if (this.goal.equals("cut")) {
            return Math.round((adjustedBmr * 0.85) * 100.0) / 100.0;
        } else if (this.goal.equals("bulk")) {
            return Math.round((adjustedBmr * 1.15) * 100.0) / 100.0;
        } else {
            return Math.round(adjustedBmr * 100.0) / 100.0;
        }
    }

    // EFFECTS: logs an event where workout split statistics were viewed
    public void workoutStatisticsViewed() {
        EventLog.getInstance().logEvent(new Event("Viewed Workout Split Statistics"));
    }

    // EFFECTS: logs an event where user profile was viewed
    public void userProfileViewed() {
        EventLog.getInstance().logEvent(new Event("Viewed User Profile"));
    }

    // EFFECTS: logs an event where a new user profile was created
    public void newUserCreated() {
        EventLog.getInstance().logEvent(new Event("Created New User Profile with the name: " + this.name));
    }

    /*
     * SETTER METHODS:
     */
    public void setName(String name) {
        if (!this.name.equalsIgnoreCase(name)) {
            EventLog.getInstance().logEvent(new Event("Updated User Name To: " + name));
        }
        this.name = name;
    }

    public void setHeight(double height) {
        if (this.height != height) {
            EventLog.getInstance().logEvent(new Event("Updated User Height To: " + height + "cm"));
        }
        this.height = height;
        setBmi();
        this.targetCalories = calculateCalories();
    }

    public void setWeight(double weight) {
        if (this.weight != weight) {
            EventLog.getInstance().logEvent(new Event("Updated User Weight To: " + weight + "kg"));
        }
        this.weight = weight;
        setBmi();
        this.targetCalories = calculateCalories();
    }

    public void setBmi() {
        this.bmi = calculateBMI();
    }

    public void setAge(int age) {
        if (this.age != age) {
            EventLog.getInstance().logEvent(new Event("Updated User Age To: " + age + " years"));
        }
        this.age = age;
        this.targetCalories = calculateCalories();
    }

    public void setIntensity(int intensity) {
        if (this.intensity != intensity) {
            EventLog.getInstance().logEvent(new Event("Updated Weekly Workout Intensity To: " + intensity
                    + ((intensity == 1) ? " day a week" : " days a week")));
        }
        this.intensity = intensity;
        this.targetCalories = calculateCalories();
    }

    public void setGoal(String goal) {
        if (!this.goal.equalsIgnoreCase(goal)) {
            EventLog.getInstance().logEvent(new Event("Updated Fitness Goal To: " + goal));
        }
        this.goal = goal;
        this.targetCalories = calculateCalories();
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                "Sunday");
        for (String day : daysOfWeek) {
            adjustQuantities(userDietPlan.getCompleteWeeklyPlan(), day);
        }
    }

    /*
     * GETTER METHODS:
     */
    public String getName() {
        return this.name;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getBmi() {
        return this.bmi;
    }

    public int getAge() {
        return this.age;
    }

    public int getIntensity() {
        return this.intensity;
    }

    public String getGoal() {
        return this.goal;
    }

    public double getTargetCalories() {
        return this.targetCalories;
    }

    public List<Workout> getWorkoutList() {
        return this.userWorkoutSplit;
    }

    public DietPlan getDietPlan() {
        return this.userDietPlan;
    }
}

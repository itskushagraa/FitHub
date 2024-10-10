package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.ModelExerciseData;
import data.ModelMealData;

/**
 * Represents a user profile in FitHub
 * Stores personal data such as height, weight, age, fitness goal, weekly
 * workout intensity
 * Methods include: calculating metrics like BMI, calorie intake etc !!! TODO
 **/

public class UserProfile {
    private String name; // user's name
    private double height; // user's height (in cm)
    private double weight; // user's weight (in kg)
    private double bmi; // user's BMI
    private int age; // user's age (in yrs)
    private int intensity; // user's weekly workout intensity (Integer[1,7] in days per week)
    private String goal; // user's fitness goal (one of: "bulk", "cut", "maintain")
    private double targetCalories; // user's daily recommended calorie intake (based on goal)

    private List<Workout> userWorkoutSplit; // workout split tailored to user attributes
    private DietPlan userDietPlan; // dietPlan tailored to user attributes

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
    public UserProfile(String name, double height, double weight, int age, int intensity, String goal) {
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
     * MODIFIES: this.userWorkoutSplit
     * EFFECTS: Generates a complete workout split for the user, consisting of
     * three workouts: Push, Pull, and Legs.
     */
    public List<Workout> generateWorkoutList() {
        return null; // STUB TODO
    }

    /*
     * HELPER FUNCTION FOR generateWorkout()
     * EFFECTS: Generates a Push Day workout, consisting of random exercises for the
     * chest, front delts, triceps and side delts
     */
    public Workout generatePush() {
        return null; // STUB TODO
    }

    /*
     * HELPER METHOD FOR generateWorkoutList()
     * EFFECTS: Generates a Pull Day workout, consisting of random exercises for the
     * upper back, lower back, biceps, lats, rear delts
     */
    public Workout generatePull() {
        return null; // STUB TODO
    }

    /*
     * HELPER FUNCTION FOR generateWorkoutList()
     * EFFECTS: Generates a Leg Day workout, consisting of random exercises for the
     * quads, hams, glutes, calves, abs
     */
    public Workout generateLegs() {
        return null; // STUB TODO
    }

    /*
     * HELPER FUNCTION FOR generatePull(), generatePush() and generateLegs()
     * REQUIRES: count < muscleGroupList.size()
     * EFFECTS: picks 'count' amount of random exercises from the exercise list
     */
    public List<Exercise> selectRandomExercises(List<Exercise> exercises, int count) {
        return null; // STUB TODO
    }

    /*
     * MODIFIES: this.userDietPlan
     * EFFECTS: generates a list of workouts for the user based on user attributes
     */
    public DietPlan generateDietPlan() {
        return null; // STUB TODO
    }

    /*
     * HELPER FUNCTION FOR generateDietPlan()
     * MODIFIES: dietPlan
     * EFFECTS: Adjusts the quantities breakfast, lunch and dinner for a given day
     * in accordance to the user's attributes
     */
    public void adjustQuantities(Map<String, List<Meal>> map, String day) {
        // STUB TODO
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
            return adjustedBmr * 0.85;
        } else if (this.goal.equals("bulk")) {
            return adjustedBmr * 1.15;
        } else {
            return adjustedBmr;
        }
    }

    /*
     * SETTER METHODS:
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(double height) {
        this.height = height;
        this.bmi = calculateBMI();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.bmi = calculateBMI();
    }

    public void setBmi() {
        this.bmi = calculateBMI();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setGoal(String goal) {
        this.goal = goal;
        userDietPlan = generateDietPlan();
    }

    public void setTargetCalories(double cals) {
        this.targetCalories = cals;
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
}

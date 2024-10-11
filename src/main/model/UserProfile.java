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
 * Stores personal data such as name, height, weight, age, fitness goal, weekly
 * workout intensity, bmi, daily calorie consumption, workout split, diet plan
 **/

public class UserProfile {
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
        userWorkoutSplit = new ArrayList<>();
        userWorkoutSplit.add(generatePush());
        userWorkoutSplit.add(generatePull());
        userWorkoutSplit.add(generateLegs());
        return userWorkoutSplit;
    }

    /*
     * HELPER FUNCTION FOR generateWorkout()
     * EFFECTS: Generates a Push Day workout, consisting of random exercises for the
     * chest, front delts, triceps and side delts
     */
    public Workout generatePush() {
        Workout pushDay = new Workout("Push Day", new ArrayList<>(), null, 0);

        List<Exercise> chest = selectRandomExercises(ModelExerciseData.CHEST_LIST, 4);
        List<Exercise> triceps = selectRandomExercises(ModelExerciseData.TRICEP_LIST, 2);
        List<Exercise> sideDelts = selectRandomExercises(ModelExerciseData.SIDE_DELT_LIST, 1);

        pushDay.getExercises().addAll(chest);
        pushDay.getExercises().addAll(triceps);
        pushDay.getExercises().addAll(sideDelts);

        return pushDay;
    }

    /*
     * HELPER METHOD FOR generateWorkoutList()
     * EFFECTS: Generates a Pull Day workout, consisting of random exercises for the
     * upper back, lower back, biceps, lats, rear delts
     */
    public Workout generatePull() {
        Workout pullDay = new Workout("Pull Day", new ArrayList<>(), null, 0);

        List<Exercise> upperBack = selectRandomExercises(ModelExerciseData.UPPER_BACK_LIST, 2);
        List<Exercise> lowerBack = selectRandomExercises(ModelExerciseData.LOWER_BACK_LIST, 1);
        List<Exercise> biceps = selectRandomExercises(ModelExerciseData.BICEP_LIST, 2);
        List<Exercise> lats = selectRandomExercises(ModelExerciseData.LAT_LIST, 1);
        List<Exercise> rearDelts = selectRandomExercises(ModelExerciseData.REAR_DELT_LIST, 1);

        pullDay.getExercises().addAll(upperBack);
        pullDay.getExercises().addAll(lowerBack);
        pullDay.getExercises().addAll(biceps);
        pullDay.getExercises().addAll(lats);
        pullDay.getExercises().addAll(rearDelts);

        return pullDay;
    }

    /*
     * HELPER FUNCTION FOR generateWorkoutList()
     * EFFECTS: Generates a Leg Day workout, consisting of random exercises for the
     * quads, hams, glutes, calves, abs
     */
    public Workout generateLegs() {
        Workout legDay = new Workout("Leg Day", new ArrayList<>(), null, 0);

        List<Exercise> legs = selectRandomExercises(ModelExerciseData.LEGS_LIST, 4);
        List<Exercise> abs = selectRandomExercises(ModelExerciseData.ABS_LIST, 1);

        legDay.getExercises().addAll(legs);
        legDay.getExercises().addAll(abs);

        return legDay;
    }

    /*
     * HELPER FUNCTION FOR generatePull(), generatePush() and generateLegs()
     * REQUIRES: count < muscleGroupList.size()
     * EFFECTS: picks 'count' amount of random exercises from the exercise list
     */
    public List<Exercise> selectRandomExercises(List<Exercise> exercises, int count) {
        List<Exercise> shuffledExercises = new ArrayList<>(exercises);
        Collections.shuffle(shuffledExercises);
        return shuffledExercises.subList(0, count);
    }

    /*
     * MODIFIES: this.userDietPlan
     * EFFECTS: generates a list of workouts for the user based on user attributes
     */
    public DietPlan generateDietPlan() {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        Map<String, List<Meal>> dietPlanMap = new HashMap<>();

        List<Meal> randomizedBreakfasts = new ArrayList<>((ModelMealData.BREAKFAST_LIST));
        List<Meal> randomizedLunches = new ArrayList<>(ModelMealData.LUNCH_LIST);
        List<Meal> randomizedDinners = new ArrayList<>(ModelMealData.DINNER_LIST);

        Collections.shuffle(randomizedBreakfasts);
        Collections.shuffle(randomizedLunches);
        Collections.shuffle(randomizedDinners);

        for (int i = 0; i < daysOfWeek.size(); i++) {
            List<Meal> dailyMeals = new ArrayList<>(
                    Arrays.asList(randomizedBreakfasts.get(i), randomizedLunches.get(i), randomizedDinners.get(i)));
            dietPlanMap.put(daysOfWeek.get(i), dailyMeals);
        }

        for (String day : daysOfWeek) {
            adjustQuantities(dietPlanMap, day);
        }

        userDietPlan = new DietPlan(dietPlanMap);
        return userDietPlan;
    }

    /*
     * HELPER FUNCTION FOR generateDietPlan()
     * MODIFIES: dietPlan
     * EFFECTS: Adjusts the quantities breakfast, lunch and dinner for a given day
     * in accordance to the user's attributes
     */
    public void adjustQuantities(Map<String, List<Meal>> map, String day) {
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
        setBmi();
        this.targetCalories = calculateCalories();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        setBmi();
        this.targetCalories = calculateCalories();
    }

    public void setBmi() {
        this.bmi = calculateBMI();
    }

    public void setAge(int age) {
        this.age = age;
        this.targetCalories = calculateCalories();
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
        this.targetCalories = calculateCalories();
    }

    public void setGoal(String goal) {
        this.goal = goal;
        this.targetCalories = calculateCalories();
        userDietPlan = generateDietPlan();
    }

    public void setTargetCalories(double cals) {
        this.targetCalories = cals;
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

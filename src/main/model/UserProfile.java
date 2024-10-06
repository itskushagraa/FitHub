package model;

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
    private int targetCalories; // user's daily recommended calorie intake (based on goal)

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
     */
    public UserProfile(String name, double height, double weight, int age, int intensity, String goal) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = calculateBMI();
        this.age = age;
        this.intensity = intensity;
        this.goal = goal;
        this.targetCalories = calculateTargetCalories();
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
    public int calculateTargetCalories() {
        double bmr = 10 * this.weight + 6.25 * this.height - 5 * this.age + 5;
        double intensityMultiplier = 1.2 + ((this.intensity - 1) * (0.4 / 6));
        double adjustedBmr = bmr * intensityMultiplier;

        if (this.goal.equals("cut")) {
            return (int) (adjustedBmr * 0.85);
        } else if (this.goal.equals("bulk")) {
            return (int) (adjustedBmr * 1.15);
        } else {
            return (int) adjustedBmr;
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
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
    }

    public void setTargetCalories() {
        this.targetCalories = calculateTargetCalories();
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

    public int getTargetCalories() {
        return this.targetCalories;
    }
}

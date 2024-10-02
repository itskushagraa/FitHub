package model;

/** 
 * Represents a user profile in FitHub
 * Stores personal data such as height, weight, age, fitness goal, weekly workout intensity
 * Methods include: calculating metrics like BMI, calorie intake etc !!! TODO
**/

public class UserProfile {
    private String name;               // user's name
    private double height;             // user's height (in cm)
    private double weight;             // user's weight (in kg)
    private double bmi;                // user's BMI
    private int age;                   // user's age (in yrs)
    private int intensity;             // user's weekly workout intensity (Integer[1,7] in days per week)
    private String goal;               // user's fitness goal (one of: "bulk", "cut", "maintain")
    private int targetCalories;        // user's daily recommended calorie intake (based on goal)

                 
    /*
     * REQUIRES: 
     *  - height > 0
     *  - weight > 0
     *  - age > 0
     *  - name.length() > 0
     *  - intensity must be in the range [1, 7] (representing days per week)
     *  - goal must be one of "cut", "bulk", "maintain"
     *
     * EFFECTS: 
     *  - Initializes this UserProfile with the provided name, height, weight, age, intensity level, and goal.
     *  - Sets targetCalories and BMI based on the parameters provided
     */
    public UserProfile(String name, double height, double weight, int age, int intensity, String goal) {
        // STUB TODO
    }


    /*    
     * EFFECTS: 
     *  - returns the BMI based on height and weight
     */
    public double calculateBMI() {
        return 0.0; //STUB TODO
    }


    /*
     * EFFECTS: 
     *  - returns the estimated daily caloric intake based on the user's metrics and fitness goal
     */
    public int calculateTargetCalories() {
        return 0; //STUB TODO
    }


    /*
     * SETTER METHODS: 
     */
    public void setName(String name) {
        //STUB TODO
    }

    public void setHeight(double height) {
        //STUB TODO
    }

    public void setWeight(double weight) {
        //STUB TODO
    }

    public void setAge(int age) {
        //STUB TODO
    }

    public void setIntensity(int intensity) {
        //STUB TODO
    }

    public void setGoal(String goal) {
        //STUB TODO
    }

    
    /*
     * GETTER METHODS:
     */
    public String getName() {
        return ""; //STUB TODO
    }

    public double getHeight() {
        return 0.0; //STUB TODO
    }

    public double getWeight() {
        return 0.0; //STUB TODO
    }

    public double getBmi() {
        return 0.0; //STUB TODO
    }

    public int getAge() {
        return 0; //STUB TODO
    }

    public int getIntensity() {
        return 0; //STUB TODO
    }

    public String getGoal() {
        return ""; //STUB TODO
    }

    public int getTargetCalories() {
        return 0; //STUB TODO
    }
}

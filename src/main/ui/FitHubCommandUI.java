package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.UserProfile;

/*
 * The command-line user interface for the FitHub application. 
 * This class handles the interaction between the user and the application 
 * through a series of text-based menus and prompts.
 */

public class FitHubCommandUI {
    private AppState currentState;

    public FitHubCommandUI() {
        currentState = AppState.WELCOME;
    }

    public void run() throws IOException {
        while (true) {
            switch (currentState) {
                case WELCOME:
                    displayWelcomeScreen();
                    break;
                case USER_SETUP:
                    configureUserScreen();
                    break;
                case MAIN_MENU:
                    displayMenu();
                    break;
                case WORKOUT_PLANNER:
                    displayWorkoutPlanner();
                    break;
                case DIET_PLANNER: 
                    displayDietPlanner();
                    break;
                case USER_PROFILE:
                    displayUserProfile();
                    break;
            }
        }
    }

    public void displayWelcomeScreen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to FitHub! Press any key to get started!");

        try {
            reader.readLine();
            clearConsole();
        } catch (IOException e) {
            System.out.println("IOException occured");
        }

        currentState = AppState.USER_SETUP;
    }

    public void configureUserScreen() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = getValidName(reader);
        double height = getValidHeight(reader);
        double weight = getValidWeight(reader);
        int age = getValidAge(reader);
        int intensity = getValidIntensity(reader);
        String goal = getValidGoal(reader);
        UserProfile mainUser = new UserProfile(name, height, weight, age, intensity, goal);

        System.out.println("Based on your inputs, your daily recommended calorie intake is "
                + String.format("%.2f", mainUser.getTargetCalories()) + ".");
        System.out.println("The following items have been generated for you: ");
        System.out.println("- A meal plan to help you meet the daily calorie intake");
        System.out.println("- A workout split containing a list of exercises that you can complete at anytime! ");
        System.out.println("You can customize your workouts and meals however you like");
        System.out.println("Press any key to continue to the main menu!");

        try {
            reader.readLine();
            clearConsole();
        } catch (IOException e) {
            System.out.println("IOException occured");
        }

        currentState = AppState.WELCOME;
    }

    public void displayMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to your FitHub dashboard!");
        System.out.println("1 -> Access Workout Planner\n2 -> Access Diet Planner\n3 -> Access User Profile");
        System.out.println("4 -> Exit FitHub\nEnter a number to choose:");
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input < 1 || input > 4) {
                    System.out.println("Please choose from the given options: ");
                } else if (input == 1) {
                    currentState = AppState.WORKOUT_PLANNER;
                } else if (input == 2) {
                    currentState = AppState.DIET_PLANNER;
                } else if (input == 3) {
                    currentState = AppState.USER_PROFILE;
                } else if (input == 4) {
                    exitApp();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number: ");
            }
        } while (input < 1 || input > 4);
    }

    public void displayWorkoutPlanner() {
        // STUB TODO
    }

    public void displayDietPlanner() {
        // STUB TODO
    }

    public void displayUserProfile() {
        // STUB TODO
    }

    /*
     * HELPER FUNCTIONS FOR THE APP STATES
     */
    private String getValidName(BufferedReader reader) throws IOException {
        String name = "";
        System.out.println("Enter your name: ");
        do {
            name = reader.readLine();
            if (name.length() == 0) {
                System.out.println("Please enter a name");
            }
        } while (name.length() == 0);
        return name;
    }

    private double getValidHeight(BufferedReader reader) throws IOException {
        double height = 0.0;
        System.out.println("Enter your height (in centimeters): ");
        do {
            try {
                height = Double.parseDouble(reader.readLine());
                if (height <= 0) {
                    System.out.println("Please enter a positive non-zero height:");
                } else if (height > 250) {
                    System.out.println("That's a bit too much! Please enter a height under 250 cm:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number:");
            }
        } while (height <= 0.0 || height > 250);
        return height;
    }

    private double getValidWeight(BufferedReader reader) throws IOException {
        double weight = 0.0;
        System.out.println("Enter your weight (in kilograms): ");
        do {
            try {
                weight = Double.parseDouble(reader.readLine());
                if (weight <= 0) {
                    System.out.println("Please enter a positive non-zero weight");
                } else if (weight > 250) {
                    System.out.println("That's a bit too much! Please enter a weight under 250 kg:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number number");
            }
        } while (weight <= 0.0 || weight > 250);
        return weight;
    }

    private int getValidAge(BufferedReader reader) throws IOException {
        int age = 0;
        System.out.println("Enter your age (in years): ");
        do {
            try {
                age = (int) (Double.parseDouble(reader.readLine()));
                if (age <= 0) {
                    System.out.println("Please enter a positive non-zero age");
                } else if (age > 100) {
                    System.out.println("That's a bit too much! Please enter an age under 100 years:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        } while (age <= 0 || age > 100);
        return age;
    }

    private int getValidIntensity(BufferedReader reader) throws IOException {
        int intensity = 0;
        System.out.println("Enter your weekly workout intensity from 1 to 7 (1 = once a week): ");
        do {
            try {
                intensity = Integer.parseInt(reader.readLine());
                if (intensity < 1 || intensity > 7) {
                    System.out.println("Please enter a number between 1 to 7: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        } while (intensity < 1 || intensity > 7);
        return intensity;
    }

    private String getValidGoal(BufferedReader reader) throws IOException {
        Set<String> validGoals = new HashSet<>(Arrays.asList("b", "c", "m"));
        String goal;

        System.out.println("Choose one of the following fitness goals:");
        System.out.println("b -> Bulk (Gain Muscle)");
        System.out.println("c -> Cut (Lose Weight)");
        System.out.println("m -> Maintain (Maintain current weight)");

        do {
            goal = reader.readLine().toLowerCase();
            if (!validGoals.contains(goal)) {
                System.out.println("Please enter a valid character (one of b, c, or m): ");
            }
        } while (!validGoals.contains(goal));

        return goal;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void exitApp() {
        clearConsole();
        System.exit(0);
    }
}

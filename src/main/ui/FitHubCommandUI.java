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
                    System.out.println("Please enter a positive non-zero height");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number");
            }
        } while (height <= 0.0);
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
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number number");
            }
        } while (weight <= 0.0);
        return weight;
    }

    private int getValidAge(BufferedReader reader) throws IOException {
        int age = 0;
        System.out.println("Enter your age (in years): ");
        do {
            try {
                age = Integer.parseInt(reader.readLine());
                if (age <= 0) {
                    System.out.println("Please enter a positive non-zero age");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        } while (age <= 0);
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
}

package ui;

import java.io.IOException;
import java.util.Scanner;

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

    public void showWelcomeScreen() {
        System.out.println("Welcome to FitHub! Press any key to get started...");

        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("IOException occured");
        }
    }

    @SuppressWarnings("methodlength")
    public void configureUserScreen() {
        // STUB TODO
    }

    private String getValidName(Scanner scanner) {
        return ""; // STUB TODO
    }

    private double getValidHeight(Scanner scanner) {
        return 0.0; // STUB TODO
    }

    private double getValidWeight(Scanner scanner) {
        return 0.0; // STUB TODO
    }

    private int getValidAge(Scanner scanner) {
        return 0; // STUB TODO
    }

    private int getValidIntensity(Scanner scanner) {
        return 0; // STUB TODO
    }

    private String getValidGoal(Scanner scanner) {
        return "";// STUB TODO
    }
}

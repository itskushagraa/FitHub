package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.DietPlan;
import model.Exercise;
import model.ExerciseSet;
import model.Meal;
import model.UserProfile;
import model.Workout;

/*
* The command-line user interface for the FitHub application. 
* This class handles the interaction between the user and the application 
* through a series of text-based menus and prompts.
*/

public class FitHubCommandUI {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private AppState currentState;
    private UserProfile mainUser;

    // EFFECTS: clears whatever was on the console screen and runs the UI
    public FitHubCommandUI() throws Exception {
        currentState = AppState.WELCOME;
        clearConsole();
        run();
    }

    // EFFECTS: runs the state machine, toggles app states according to the user's
    // navigation of the app screens
    private void run() throws IOException {
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
                    clearConsole();
                    displayUserProfile();
                    break;
            }
        }
    }

    // EFFECTS: displays WELCOME screen for the user
    private void displayWelcomeScreen() {
        System.out.println("Welcome to FitHub! Press any key to get started!");

        try {
            reader.readLine();
            clearConsole();
        } catch (IOException e) {
            System.out.println("IOException occured");
        }

        currentState = AppState.USER_SETUP;
    }

    // EFFECTS: configures the USER_SETUP portion of FitHub
    private void configureUserScreen() throws IOException {
        String name = getValidName(reader);
        double height = getValidHeight(reader);
        double weight = getValidWeight(reader);
        int age = getValidAge(reader);
        int intensity = getValidIntensity(reader);
        String goal = getValidGoal(reader);
        mainUser = new UserProfile(name, height, weight, age, intensity, goal);

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
        currentState = AppState.MAIN_MENU;
    }

    // EFFECTS: display the FitHub MAIN_MENU
    private void displayMenu() throws IOException {
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

    // EFFECTS: displays and executes the USER_PROFILE portion of FitHub
    private void displayWorkoutPlanner() throws IOException {
        clearConsole();
        System.out.println("Here is your workout split!");
        showWorkoutSplit(reader);
    }

    // EFFECTS: displays and executes the USER_PROFILE
    private void displayUserProfile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("This is your current user profile");
        showProfile();
        System.out.println("Choose from the given options to edit your user profile: ");
        System.out.println("1 -> Name\n2 -> Height\n3 -> Weight\n4 -> Age\n5 -> Workout Intensity\n6 -> Fitness Goal");
        System.out.println("7 -> Back to main menu\n8 -> Exit App");
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input < 1 || input > 8) {
                    System.out.println("Please choose from the given options.");
                } else {
                    executeUserProfileInput(input, reader);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number.");
            }
        } while (input < 1 || input > 8);
    }

    // EFFECTS: displays and executes the DIET_PLANNER portion of FitHub
    private void displayDietPlanner() throws IOException {
        clearConsole();
        DietPlan mainPlan = mainUser.getDietPlan();
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        System.out.println("Here's your Diet Plan!\n");
        System.out.printf("%-13s %-35s %-35s %-35s%n", "DAY", "BREAKFAST", "LUNCH", "DINNER");
        System.out.println();
        for (String day : daysOfWeek) {
            System.out.printf("%-13s %-35s %-35s %-35s%n", day, mainPlan.getMeal(day, "Breakfast").getName(),
                    mainPlan.getMeal(day, "Lunch").getName(), mainPlan.getMeal(day, "Dinner").getName());
        }
        System.out.println("\nChoose from the options below: ");
        System.out.println("1 -> View a meal\n2 -> View stats for a day");
        System.out.println("3 -> View weekly stats\n4 -> Go back to main menu\n5 -> Exit App");
        executeDietPlanInput();
    }

    /*
     * HELPER FUNCTIONS FOR THE APP STATES
     */

    // EFFECTS: peforms the task chosen from the diet plan screen
    private void executeDietPlanInput() throws IOException {
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input < 1 || input > 5) {
                    System.out.println("Please choose a valid option.");
                } else if (input == 1) {
                    showMeal();
                } else if (input == 2) {
                    showDayStatistics();
                } else if (input == 3) {
                    showWeekStatistics();
                } else if (input == 4) {
                    clearConsole();
                    currentState = AppState.MAIN_MENU;
                    return;
                } else if (input == 5) {
                    exitApp();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please choose a valid option");
            }
        } while (input < 1 || input > 5);
    }

    // EFFECTS: displays statistics for a given day of the diet plan
    private void showDayStatistics() throws IOException {
        System.out.println("Choose the day whose statistics you want to view: ");
        System.out.println(
                "1 -> Monday\n2 -> Tuesday\n3 -> Wednesday\n4 -> Thursday\n5 -> Friday\n6 -> Saturday\n7 -> Sunday");
        String day = getValidDayOfWeek();
        System.out.println("Total Calories: " + (mainUser.getDietPlan().calculateDailyCalories(day)) + "kcal");
        System.out.println("Total Quantity: " + (mainUser.getDietPlan().calculateDailyQuantity(day)) + "g");
        System.out.println("Total Protein: " + (mainUser.getDietPlan().calculateDailyProtein(day)) + "g");
        System.out.println("Total Fat: " + (mainUser.getDietPlan().calculateDailyFat(day)) + "g");
        System.out.println("Total Carbs: " + (mainUser.getDietPlan().calculateDailyCarb(day)) + "g");
        System.out.println("Enter 'x' to go back: ");
        String input = "";
        do {
            input = reader.readLine().toLowerCase();
            if (!input.equals("x")) {
                System.out.println("Invalid Input. Enter 'x' to go back to the Diet Plan");
            }
        } while (!input.equals("x"));
    }

    // EFFECTS: displays the diet plan statistics of all 7 days combined
    private void showWeekStatistics() throws IOException {
        System.out.println("Total Calories: " + (mainUser.getDietPlan().calculateWeeklyCalories()) + "kcal");
        System.out.println("Total Quantity: " + (mainUser.getDietPlan().calculateWeeklyQuantity()) + "g");
        System.out.println("Total Protein: " + (mainUser.getDietPlan().calculateWeeklyProtein()) + "g");
        System.out.println("Total Fat: " + (mainUser.getDietPlan().calculateWeeklyFat()) + "g");
        System.out.println("Total Carbs: " + (mainUser.getDietPlan().calculateWeeklyCarb()) + "g");
        System.out.println("Enter 'x' to go back: ");
        String input = "";
        do {
            input = reader.readLine().toLowerCase();
            if (!input.equals("x")) {
                System.out.println("Invalid Input. Enter 'x' to go back to the Diet Plan");
            }
        } while (!input.equals("x"));
    }

    // EFFECTS: displays the specified meal's name, macronutrient and calorie
    // information, and list of ingredients
    private void showMeal() throws IOException {
        System.out.println("Enter the type of meal you want to view: \nb -> Breakfast\nl -> Lunch\nd -> Dinner");
        String mealType = getValidMealType();
        System.out.println("Choose the day whose " + mealType + " you want to view: ");
        System.out.println(
                "1 -> Monday\n2 -> Tuesday\n3 -> Wednesday\n4 -> Thursday\n5 -> Friday\n6 -> Saturday\n7 -> Sunday");
        String day = getValidDayOfWeek();
        clearConsole();
        Meal meal = mainUser.getDietPlan().getMeal(day, mealType);
        System.out.println(meal.getName());
        showMealInfo(meal);
        System.out.println("Choose from the following options: ");
        System.out.println("1 -> Change Meal Name\n2 -> Change Meal Quantity");
        System.out.println("3 -> Replace this meal\n4 -> Back to diet plan\n5 -> Exit App");
        executeMealInput(meal, day);
    }

    // EFFECTS: performs the option chosen in the list of meal options
    private void executeMealInput(Meal meal, String day) throws IOException {
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input < 1 || input > 5) {
                    System.out.println("Please choose a valid option");
                } else if (input == 1) {
                    meal.setName(getValidMealName());
                } else if (input == 2) {
                    meal.setQuantity(getValidQuantity());
                } else if (input == 3) {
                    createMeal(meal, day);
                } else if (input == 4) {
                    return;
                } else if (input == 5) {
                    exitApp();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number");
            }
        } while (input < 1 || input > 5);
    }

    // EFFECTS: creates a newMeal with user-chosen parameters and replaces the
    // current meal with the new meal
    private void createMeal(Meal meal, String day) throws NumberFormatException, IOException {
        String mealName = getValidMealName();
        double mealQuantity = getValidQuantity();
        System.out.println("Enter the amount of calories in " + mealQuantity + "g of the meal: ");
        double calories = getValidDouble();
        System.out.println("Enter the amount of protein in " + mealQuantity + "g of the meal: ");
        double protein = getValidDouble();
        System.out.println("Enter the amount of fat in " + mealQuantity + "g of the meal: ");
        double fat = getValidDouble();
        System.out.println("Enter the amount of carbs in " + mealQuantity + "g of the meal: ");
        double carb = getValidDouble();
        System.out.println("Enter a list of ingredients used (type 'done' to stop): ");
        List<String> ingredients = getValidIngredients();
        Meal newMeal = new Meal(mealName, meal.getType(), ingredients, calories, mealQuantity, protein, fat, carb);
        mainUser.getDietPlan().addMeal(day, meal.getType(), newMeal);
    }

    // EFFECTS: returns a double value thats higher than 0, forces the user to input
    // a valid value
    private double getValidDouble() throws IOException {
        double ret = 0.0;
        do {
            try {
                ret = Double.parseDouble(reader.readLine());
                if (ret <= 0.0) {
                    System.out.println("Please enter a positive non-zero value: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (ret <= 0.0);
        return ret;
    }

    // EFFECTS: returns a list of string of ingredients for a meal
    private List<String> getValidIngredients() throws IOException {
        List<String> ingredients = new ArrayList<>();
        System.out.println("Enter list of ingredients (type in 'done' to finish adding): ");
        String ingredientToAdd = reader.readLine();
        while (!ingredientToAdd.equalsIgnoreCase("done")) {
            ingredients.add(ingredientToAdd);
            ingredientToAdd = reader.readLine();
        }
        return ingredients;
    }

    // EFFECTS: returns a double type quantity greater than 0 for a meal, forces
    // user to input valid values
    private double getValidQuantity() throws IOException {
        System.out.println("Enter quantity (in grams): ");
        double ret = 0.0;
        do {
            try {
                ret = Double.parseDouble(reader.readLine());
                if (ret <= 0.0) {
                    System.out.println("Please enter a positive non-zero value: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid input.");
            }
        } while (ret <= 0.0);
        return ret;
    }

    // EFFECTS: returns a valid (non-empty) meal name, forces user to enter valid
    // name
    private String getValidMealName() throws IOException {
        String name = "";
        System.out.println("Enter new meal name: ");
        do {
            name = reader.readLine();
            if (name.length() == 0) {
                System.out.println("Please enter a name");
            }
        } while (name.length() == 0);
        return name;
    }

    // EFFECTS: displays the attributes of a meal object
    private void showMealInfo(Meal meal) {
        System.out.println("Ingredients Required: " + String.join(", ", meal.getIngredients()));
        System.out.println("Quantity: " + String.format("%.2f", meal.getQuantity()) + "g");
        System.out.println("Protein: " + String.format("%.2f", meal.getProtein()) + "g");
        System.out.println("Fat: " + String.format("%.2f", meal.getFat()) + "g");
        System.out.println("Carbs: " + String.format("%.2f", meal.getCarb()) + "g");
        System.out.println("Calories: " + String.format("%.2f", meal.getCalories()) + "kcals");
    }

    // EFFECTS: returns a valid day of the week falling between monday to sunday
    private String getValidDayOfWeek() throws IOException {
        int dayNum = 0;
        String day = "";
        do {
            try {
                dayNum = Integer.parseInt(reader.readLine());
                if (dayNum < 1 || dayNum > 7) {
                    System.out.println("Please enter a number between 1 and 7");
                } else {
                    day = getDayFromNumber(dayNum);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number between 1 and 7");
            }

        } while (dayNum < 1 || dayNum > 7);
        return day;
    }

    // EFFECTS: returns the corresponding day for a number (1 = monday, 7 = sunday)
    private String getDayFromNumber(int dayNum) {
        switch (dayNum) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return "";
        }
    }

    // EFFECTS: returns one of 3 valid meal types (breakfast, lunch, dinner)
    private String getValidMealType() throws IOException {
        Set<String> validTypes = new HashSet<>(Arrays.asList("b", "l", "d"));
        String type;
        do {
            type = reader.readLine().toLowerCase();
            if (!validTypes.contains(type)) {
                System.out.println("Please enter a valid character (one of b, l, or d): ");
            }
        } while (!validTypes.contains(type));

        if (type.equals("b")) {
            return "Breakfast";
        } else if (type.equals("l")) {
            return "Lunch";
        } else {
            return "Dinner";
        }
    }

    // EFFECTS: displays the workout split
    private void showWorkoutSplit(BufferedReader reader) throws IOException {
        List<Workout> workoutSplit = mainUser.getWorkoutList();
        for (int i = 0; i < workoutSplit.size(); i++) {
            System.out.println((i + 1) + " -> " + workoutSplit.get(i).getName());
        }
        System.out.println((workoutSplit.size() + 1) + " -> View Overall Statistics");
        System.out.println((workoutSplit.size() + 2) + " -> Back to main menu");
        System.out.println((workoutSplit.size() + 3) + " -> Exit App");
        executeWorkoutSplitInput(workoutSplit, reader);
    }

    // EFFECTS: performs option chosen from the list of workout split options
    private void executeWorkoutSplitInput(List<Workout> workoutSplit, BufferedReader reader) throws IOException {
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input < 1 || input > workoutSplit.size() + 4) {
                    System.out.println("Please choose a valid option:");
                } else if (input == workoutSplit.size() + 1) {
                    showOverallStatistics(workoutSplit, reader, 0, 0, 0);
                } else if (input == workoutSplit.size() + 2) {
                    clearConsole();
                    currentState = AppState.MAIN_MENU;
                    return;
                } else if (input == workoutSplit.size() + 3) {
                    exitApp();
                } else if (input <= workoutSplit.size()) {
                    showWorkout((input - 1), workoutSplit, reader);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number.");
            }
        } while (input < 1 || input > workoutSplit.size() + 4);
    }

    // EFFECTS: shows all workout statistics for the overall workout split
    private void showOverallStatistics(List<Workout> workoutSplit, BufferedReader reader, int totalSets, int totalReps,
            int totalVolume) throws IOException {
        List<String> muscles = new ArrayList<>();
        for (int i = 0; i < workoutSplit.size(); i++) {
            totalSets += workoutSplit.get(i).calculateTotalSets();
            totalReps += workoutSplit.get(i).calculateTotalReps();
            totalVolume += workoutSplit.get(i).calculateTotalVolume();
            muscles.addAll(workoutSplit.get(i).calculateMusclesWorked());
        }
        List<String> uniqueMuscles = muscles.stream().distinct().collect(Collectors.toList());
        System.out.println("Total Weight Lifted: " + totalVolume + " kgs");
        System.out.println("Total Sets completed: " + totalSets);
        System.out.println("Total Reps completed: " + totalReps);
        System.out.println("Muscles Worked: " + String.join(", ", uniqueMuscles));
        System.out.println("Enter 'x' to go back to the workout split");
        String input = "";
        do {
            input = reader.readLine().toLowerCase();
            if (!input.equals("x")) {
                System.out.println("Invalid Input. Enter 'x' to go back to the workout split:");
            } else {
                clearConsole();
                displayWorkoutPlanner();
            }
        } while (!input.equals("x"));
    }

    // EFFECTS: shows a workout with its list of exercises and other options
    private void showWorkout(int input, List<Workout> workoutSplit, BufferedReader reader) throws IOException {
        clearConsole();
        Workout workout = workoutSplit.get(input);
        System.out.println(workout.getName());
        for (int i = 0; i < workout.getExercises().size(); i++) {
            System.out.println((i + 1) + " -> " + workout.getExercises().get(i).getName());
        }
        System.out.println("Enter the corresponding exercise number to view it or choose from below:");
        System.out.println((workout.getExercises().size() + 1) + " -> Rename this workout");
        System.out.println((workout.getExercises().size() + 2) + " -> Back to workout list");
        System.out.println((workout.getExercises().size() + 3) + " -> Create New Exercise");
        System.out.println((workout.getExercises().size() + 4) + " -> View Workout Statistics");
        System.out.println((workout.getExercises().size() + 5) + " -> Exit App");
        executeWorkoutInput(workout, reader);
    }

    // EFFECTS: performs option chosen from the list of workout options
    private void executeWorkoutInput(Workout workout, BufferedReader reader) throws IOException {
        int input = 0;
        do {
            try {
                input = performSpecifiedWorkoutOperation(input, reader, workout);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number");
            }
        } while (input < 1 || input > workout.getExercises().size() + 5);
    }

    // HELPER METHOD ADDED FOR METHOD-LENGTH REDUCTION PURPOSES
    // EFFECTS: does literally what executeWorkoutInput() does
    private int performSpecifiedWorkoutOperation(int input, BufferedReader reader, Workout workout)
            throws IOException {
        input = Integer.parseInt(reader.readLine());
        if (input < 1 || input > workout.getExercises().size() + 5) {
            System.out.println("Please choose a valid option");
        } else if (input <= workout.getExercises().size()) {
            showExercise(input - 1, workout, reader);
        } else if (input == workout.getExercises().size() + 1) {
            renameWorkout(workout, reader);
            displayWorkoutPlanner();
        } else if (input == workout.getExercises().size() + 2) {
            displayWorkoutPlanner();
        } else if (input == workout.getExercises().size() + 3) {
            createExercise(reader, workout);
        } else if (input == workout.getExercises().size() + 4) {
            showWorkoutStatistics(workout, reader);
        } else if (input == workout.getExercises().size() + 5) {
            exitApp();
        }
        return input;
    }

    // EFFECTS: renames a workout according to the user's preferences
    private void renameWorkout(Workout workout, BufferedReader reader) throws IOException {
        String newName = "";
        System.out.println("Enter Workout Name: ");
        do {
            newName = reader.readLine();
            if (newName.length() == 0) {
                System.out.println("Please enter a name");
            } else {
                workout.setName(newName);
            }
        } while (newName.length() == 0);
    }

    // EFFECTS: displays statistics for a single workout session
    private void showWorkoutStatistics(Workout workout, BufferedReader reader) throws IOException {
        int totalReps = workout.calculateTotalReps();
        int totalWeight = workout.calculateTotalVolume();
        int totalSets = workout.calculateTotalSets();
        List<String> muscleGroups = workout.calculateMusclesWorked();
        System.out.println("Total Weight Lifted: " + totalWeight + " kgs");
        System.out.println("Total Sets Completed: " + totalSets);
        System.out.println("Total Reps Completed: " + totalReps);
        System.out.println("Muscle Worked: " + String.join(", ", muscleGroups));
        System.out.println("Enter 1 to go back to the previous menu");
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input != 1) {
                    System.out.println("Invalid Input. Please enter 1 if you want to go back");
                } else {
                    showWorkout(mainUser.getWorkoutList().indexOf(workout), mainUser.getWorkoutList(), reader);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter 1 if you want to go back:");
            }
        } while (input != 1);
    }

    // EFFECTS: creates a new exercise object to add to a workout
    private void createExercise(BufferedReader reader, Workout workout) throws IOException {
        String name = getValidExerciseName(reader);
        List<String> musclesWorked = new ArrayList<>();
        System.out.println("Enter list of muscles worked (type in 'done' to finish adding): ");
        String muscleToAdd = reader.readLine();
        while (!muscleToAdd.equalsIgnoreCase("done")) {
            musclesWorked.add(muscleToAdd);
            muscleToAdd = reader.readLine();
        }
        Exercise exercise = new Exercise(name, musclesWorked, new ArrayList<ExerciseSet>());
        workout.addExercise(exercise);
        clearConsole();
        showWorkout(mainUser.getWorkoutList().indexOf(workout), mainUser.getWorkoutList(), reader);
    }

    // EFFECTS: displays an exercise with its name and all its sets and other
    // options
    private void showExercise(int input, Workout workout, BufferedReader reader) throws IOException {
        clearConsole();
        Exercise exercise = workout.getExercises().get(input);
        System.out.println(exercise.getName());
        for (int i = 0; i < exercise.getSets().size(); i++) {
            ExerciseSet set = exercise.getSets().get(i);
            System.out.println((i + 1) + " -> " + set.getWeight() + " kg for " + set.getReps() + " reps");
        }
        System.out.println("Enter the corresponding set number to view it or choose from below:");
        System.out.println((exercise.getSets().size() + 1) + " -> Delete this exercise");
        System.out.println((exercise.getSets().size() + 2) + " -> Back to exercise list");
        System.out.println((exercise.getSets().size() + 3) + " -> Create New Set");
        System.out.println((exercise.getSets().size() + 4) + " -> Show Muscles Worked");
        System.out.println((exercise.getSets().size() + 5) + " -> Exit app");
        executeExerciseInput(exercise, reader, workout);
    }

    // EFFECTS: performs option chosen from the list of exercise options
    private void executeExerciseInput(Exercise exercise, BufferedReader reader, Workout workout) throws IOException {
        int input = 0;
        do {
            try {
                input = performSpecificExerciseOperation(input, reader, exercise, workout);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number: ");
            }
        } while (input < 1 || input > exercise.getSets().size() + 5);
    }

    // HELPER METHOD ADDED FOR METHOD-LENGTH REDUCTION PURPOSES
    // EFFECTS: does literally what executeExerciseInput() does
    private int performSpecificExerciseOperation(int input, BufferedReader reader, Exercise exercise, Workout workout)
            throws IOException {
        input = Integer.parseInt(reader.readLine());
        if (input < 1 || input > exercise.getSets().size() + 5) {
            System.out.println("Please choose a valid option");
        } else if (input == exercise.getSets().size() + 1) {
            workout.getExercises().remove(exercise);
            clearConsole();
            showWorkout(mainUser.getWorkoutList().indexOf(workout), mainUser.getWorkoutList(), reader);
        } else if (input == exercise.getSets().size() + 2) {
            clearConsole();
            showWorkout(mainUser.getWorkoutList().indexOf(workout), mainUser.getWorkoutList(), reader);
        } else if (input == exercise.getSets().size() + 3) {
            createExerciseSet(reader, exercise, workout);
        } else if (input == exercise.getSets().size() + 4) {
            showMusclesWorked(exercise, reader, workout);
        } else if (input == exercise.getSets().size() + 5) {
            exitApp();
        } else if (input <= exercise.getSets().size()) {
            showExerciseSet(input - 1, exercise, reader, workout);
        }
        return input;
    }

    // EFFECTS: produces a unique list of muscle groups worked in an exercise
    private void showMusclesWorked(Exercise exercise, BufferedReader reader, Workout workout) throws IOException {
        List<String> musclesWorked = exercise.getMusclesWorked();
        for (String muscle : musclesWorked) {
            System.out.println((musclesWorked.indexOf(muscle) + 1) + ") " + muscle);
        }
        System.out.println("Enter 1 to go back to the previous menu");
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input != 1) {
                    System.out.println("Invalid Input. Please enter 1 if you want to go back");
                } else {
                    showExercise(workout.getExercises().indexOf(exercise), workout, reader);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter 1 if you want to go back:");
            }
        } while (input != 1);
    }

    // EFFECTS: creates a new set to add to an exercise
    private void createExerciseSet(BufferedReader reader, Exercise exercise, Workout workout) throws IOException {
        int weightLifted = getValidWeightLifted(reader);
        int reps = getValidReps(reader);
        exercise.addSet(reps, weightLifted);
        showExercise(workout.getExercises().indexOf(exercise), workout, reader);
    }

    // EFFECTS: shows an exercise set with its given weight and reps, along with
    // other options
    private void showExerciseSet(int setIndex, Exercise exercise, BufferedReader reader, Workout workout)
            throws IOException {
        displayExerciseSetOptions();
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input == 1) {
                    exercise.getSets().remove(setIndex);
                    showExercise(workout.getExercises().indexOf(exercise), workout, reader);
                } else if (input == 2) {
                    exercise.getSets().get(setIndex).setWeight(getValidWeightLifted(reader));
                    showExercise(workout.getExercises().indexOf(exercise), workout, reader);
                } else if (input == 3) {
                    exercise.getSets().get(setIndex).setReps(getValidReps(reader));
                    showExercise(workout.getExercises().indexOf(exercise), workout, reader);
                } else if (input == 4) {
                    showExercise(workout.getExercises().indexOf(exercise), workout, reader);
                } else if (input == 5) {
                    exitApp();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }

        } while (input < 1 || input > 5);
    }

    // EFFECTS: forces user to input Strings till a valid (non-empty) name for an
    // exercise is returned
    private String getValidExerciseName(BufferedReader reader) throws IOException {
        String name = "";
        System.out.println("Enter exercise name: ");
        do {
            name = reader.readLine();
            if (name.length() == 0) {
                System.out.println("Please enter an exercise name");
            }
        } while (name.length() == 0);
        return name;

    }

    // EFFECTS: forces user to input integers till a valid weight is returned
    private int getValidWeightLifted(BufferedReader reader) throws IOException {
        int weightLifted = 0;
        System.out.println("Enter weight lifted:");
        do {
            try {
                weightLifted = Integer.parseInt(reader.readLine());
                if (weightLifted <= 0) {
                    System.out.println("Please enter a positive weight");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        } while (weightLifted <= 0);
        return weightLifted;
    }

    // EFFECTS: forces user to input integers till a valid rep amount is returned
    private int getValidReps(BufferedReader reader) throws IOException {
        int reps = 0;
        System.out.println("Enter reps completed: ");
        do {
            try {
                reps = Integer.parseInt(reader.readLine());
                if (reps <= 0) {
                    System.out.println("Please enter a positive amount:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        } while (reps <= 0);
        return reps;
    }

    // HELPER METHOD ADDED FOR METHOD-LENGTH REDUCTION PURPOSES
    // EFFECTS: displays list of options to be executed on an ExerciseSet object
    private void displayExerciseSetOptions() {
        System.out.println("Pick one of the options below: ");
        System.out.println("1 -> Delete this set\n2 -> Change weight lifted\n3 -> Change reps");
        System.out.println("4 -> Back to set list\n5 -> Exit app");
    }

    // EFFECTS: shows user's profile with all the attributes
    private void showProfile() {
        System.out.println("Name: " + mainUser.getName());
        System.out.println("Height: " + String.format("%.2f", mainUser.getHeight()) + " cm");
        System.out.println("Weight: " + String.format("%.2f", mainUser.getWeight()) + " kg");
        System.out.println("BMI: " + String.format("%.1f", mainUser.getBmi()));
        System.out.println("Age: " + mainUser.getAge() + " years");
        System.out.println("Workout Intensity: " + mainUser.getIntensity() + " days a week");
        if (mainUser.getGoal().equals("bulk")) {
            System.out.println("Fitness Goal: Gain Muscle (bulk)");
        } else if (mainUser.getGoal().equals("cut")) {
            System.out.println("Fitness Goal: Lose Weight (cut)");
        } else if (mainUser.getGoal().equals("maintain")) {
            System.out.println("Fitness Goal: Maintain Weight");
        }
        System.out.println("Daily calorie consumption: "
                + String.format("%.2f", mainUser.getTargetCalories()) + " kcal");
    }

    // EFFECTS: performs option chosen to edit user profile
    private void executeUserProfileInput(int input, BufferedReader reader) throws IOException {
        if (input == 1) {
            mainUser.setName(getValidName(reader));
        } else if (input == 2) {
            mainUser.setHeight(getValidHeight(reader));
        } else if (input == 3) {
            mainUser.setWeight(getValidWeight(reader));
        } else if (input == 4) {
            mainUser.setAge(getValidAge(reader));
        } else if (input == 5) {
            mainUser.setIntensity(getValidIntensity(reader));
        } else if (input == 6) {
            mainUser.setGoal(getValidGoal(reader));
        } else if (input == 7) {
            clearConsole();
            currentState = AppState.MAIN_MENU;
        } else if (input == 8) {
            exitApp();
        }
        clearConsole();
    }

    /*
     * GETTERS METHODS
     * EFFECTS: These methods all return certain user attributes after validating
     * user inputs so as to not cause errors or exceptions
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

        if (goal.equals("b")) {
            return "bulk";
        } else if (goal.equals("c")) {
            return "cut";
        } else {
            return "maintain";
        }
    }

    // EFFECTS: clears console
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // EFFECTS: closes the app
    private void exitApp() {
        clearConsole();
        System.exit(0);
    }
}
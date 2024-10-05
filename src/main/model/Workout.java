package model;

import java.time.LocalDate;
import java.util.List;

/** 
 * Represents the workout session for a day
 * Stores the workout name, list of exercises completed, the date of completion and duration (in minutes)
 * Methods include: !!! TODO
**/

public class Workout {
    private String name;
    private List<Exercise> exercises;
    private LocalDate date;
    private int duration;
    private boolean isCompleted;

    /*
     * REQUIRES: 
     *  - name.length() > 0
     *  - dateCompleted != null
     *  - duration >= 0
     * EFFECTS: 
     *  - initializes the workout with a name, list of exercises completed, date, duration
     *  - sets the initial completion status of the workout as false
     */
    public Workout(String name, List<Exercise> exercises, LocalDate dateCompleted, int duration) {
        // STUB TODO
    }

    /*
     * MODIFIES: this.exercises
     * EFFECTS:
     *  - adds the given exercises to the list of exercises in the workout
     */
    public void addExercise(Exercise exercise) {
        // STUB TODO
    }

    /*
     * REQUIRES: exercises.size() > 0
     * MODIFIES: this.exercises
     * EFFECTS: removes the most recently completed exercise from the list of exercises
     */
    public void removeExercise() {
        // STUB TODO
    }

    /*
     * REQUIRES: exercises.size() > 0
     * MODIFIES: this.exercises
     * EFFECTS: removes all exercises from the workout
     */
    public void clearWorkout() {
        // STUB TODO
    }

    //EFFECTS: returns the total weight lifted across all exercises completed
    public int calculateTotalVolume() {
        return 0; // STUB TODO
    }

    //EFFECTS: returns the sum of all sets completed across all exercises
    public int calculateTotalSets() {
        return 0; // STUB TODO
    }

    //EFFECTS: returns the sum of all reps completed across all exercises
    public int calculateTotalReps() {
        return 0; // STUB TODO
    }

    //EFFECTS: returns a list of all unique muscle groups trained in a workout
    public List<String> calculateMusclesWorked() {
        return null; //STUB TODO
    }

    //EFFECTS: returns the given exercise if found in the workout, null otherwise
    public Exercise findExerciseByName(String name) {
        return null; //STUB TODO
    }


    /*
     * SETTERS:
     */
    public void setName(String name) {
        //STUB TODO
    }

    public void setDuration(int duration) {
        //STUB TODO
    }

    public void setDate(LocalDate date) {
        //STUB TODO
    }

    public void setCompletionStatus(boolean completionStatus) {
        //STUB TODO
    }


    /*
     * GETTERS: 
     */
    public String getName() {
        return ""; //STUB TODO
    }

    public List<Exercise> getExercises() {
        return null; //STUB TODO
    }

    public LocalDate getDate() {
        return null; //STUB TODO
    }

    public int getDuration() {
        return 0; // STUB TODO
    }

    public boolean isCompleted() {
        return false; //STUB TODO
    }
}

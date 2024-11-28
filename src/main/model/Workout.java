package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONArray;

import persistance.Writable;

/**
 * Represents the workout session for a day
 * Stores the workout name, list of exercises completed, the date of completion,
 * duration (in minutes), and completion status
 **/

public class Workout implements Writable {
    private String name;
    private List<Exercise> exercises;

    /*
     * REQUIRES:
     * - name.length() > 0
     * - dateCompleted != null
     * - duration >= 0
     * EFFECTS:
     * - initializes the workout with a name, list of exercises completed
     */
    public Workout(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    // EFFECTS: returns Workout as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);

        JSONArray jsonExercises = new JSONArray();
        for (Exercise exercise : this.exercises) {
            jsonExercises.put(exercise.toJson());
        }
        json.put("exercises", jsonExercises);

        return json;
    }

    /*
     * MODIFIES: this.exercises
     * EFFECTS:
     * - adds the given exercises to the list of exercises in the workout
     */
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        EventLog.getInstance().logEvent(new Event("Added Exercise To " + this.name + ": " + exercise.getName()));
    }

    /*
     * REQUIRES: exercises.size() > 0
     * MODIFIES: this.exercises
     * EFFECTS: removes given exercise from the list of exercises
     */
    public void removeExercise(Exercise exercise) {
        if (this.exercises.contains(exercise)) {
            this.exercises.remove(exercise);
            EventLog.getInstance()
                    .logEvent(new Event("Removed Exercise From " + this.name + ": " + exercise.getName()));
        }
    }

    // EFFECTS: returns the total weight lifted across all exercises completed
    public int calculateTotalVolume() {
        int totalVolume = 0;

        for (int i = 0; i < this.exercises.size(); i++) {
            totalVolume += this.exercises.get(i).calculateTotalWeightLifted();
        }

        return totalVolume;
    }

    // EFFECTS: returns the sum of all sets completed across all exercises
    public int calculateTotalSets() {
        int totalSets = 0;

        for (int i = 0; i < this.exercises.size(); i++) {
            totalSets += this.exercises.get(i).getSets().size();
        }

        return totalSets;
    }

    // EFFECTS: returns the sum of all reps completed across all exercises
    public int calculateTotalReps() {
        int totalReps = 0;

        for (int i = 0; i < this.exercises.size(); i++) {
            totalReps += this.exercises.get(i).calculateTotalReps();
        }

        return totalReps;
    }

    // EFFECTS: returns a list of all unique muscle groups trained in a workout
    public List<String> calculateMusclesWorked() {
        Set<String> uniqueMuscles = new LinkedHashSet<>();

        for (Exercise exercise : this.exercises) {
            uniqueMuscles.addAll(exercise.getMusclesWorked());
        }

        return new ArrayList<>(uniqueMuscles);
    }

    /*
     * SETTERS:
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * GETTERS:
     */
    public String getName() {
        return this.name;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }
}

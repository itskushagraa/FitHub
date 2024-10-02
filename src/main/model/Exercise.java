package model;

import java.util.List;

/** 
 * Represents an Exercise at the gym
 * Stores information about an exercise such as: name, muscles worked, sets completed
 * Allows user to add/remove sets/reps, change weights lifted, and etc !!! TODO
**/

public class Exercise {
    private String name;                        // name of the exercise
    private List<String> musclesWorked;         // list of muscles worked
    private List<ExerciseSet> sets;             // sets completed in the exercise
    private boolean isCompleted;                // completion status of the exercise
    
    /*
     * REQUIRES: 
     *  - name.length() > 0
     *  - musclesWorked.size() > 0
     *  - sets.size() > 0
     *
     * EFFECTS: 
     *  - Initializes this Exercise with the provided name, muscles worked and sets
     *    with initial completion status set to false
     */
    public Exercise(String name, List<String> musclesWorked, List<ExerciseSet> sets) {
        // STUB TODO
    }


    /*
     * REQUIRES: 
     *  - 0 <= setIndex <= sets.size()
     *  - reps > 0
     *  - weight > 0
     * MODIFIES: this.sets
     * EFFECTS: adds a set to the list of sets at the specified index with the given reps and weight
     */
    public void addSet(int setIndex, int reps, int weight) {
        // STUB TODO
    }

    /*
     * REQUIRES: 
     *  - 0 <= setIndex < sets.size()
     * MODIFIES: this.sets
     * EFFECTS: removes the set at the specified index from the list of sets
     */
    public void removeSet(int setIndex) {
        // STUB TODO
    }

    /*
     * REQUIRES: 
     *  - 0 <= setIndex < sets.size()
     *  - reps > 0
     *  - weight > 0
     * MODIFIES: this.sets
     * EFFECTS: updates the set at the specified index in the list of sets with the given reps and weight
     */
    public void updateSet(int setIndex, int reps, int weight) {
        // STUB TODO
    }

    /*
     * MODIFIES: this.sets
     * EFFECTS: clears all ExerciseSets from the list of sets
     */
    public void clearSets() {
        // STUB TODO
    }


    //EFFECTS: returns the sum of the total reps completed across all sets
    public int calculateTotalReps() {
        return 0; // STUB TODO
    }

    //EFFECTS: returns the sum of the total weight lifted across all sets
    public int calculateTotalWeightLifted() {
        return 0; // STUB TODO
    }


    /*
     * SETTER METHODS:
     */
    public void setName(String name) {
        // STUB TODO
    }

    public void setMusclesWorked(List<String> musclesWorked) {
        // STUB TODO
    }

    public void setCompletionStatus(boolean completionStatus) {
        // STUB TODO
    }

    
    /*
     * GETTER METHODS:
     */
    public String getName() {
        return ""; // STUB TODO
    }

    public List<String> getMusclesWorked() {
        return null; // STUB TODO
    }

    public List<ExerciseSet> getSets() {
        return null; // STUB TODO
    }

    public boolean isCompleted() {
        return false; // STUB TODO
    }
}

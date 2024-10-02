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
        this.name = name;
        this.musclesWorked = musclesWorked;
        this.sets = sets;
        this.isCompleted = false;
    }


    /*
     * REQUIRES:
     *  - reps > 0
     *  - weight > 0
     * MODIFIES: this.sets
     * EFFECTS: adds a set to the list of sets with the given reps and weight
     */
    public void addSet(int reps, int weight) {
        ExerciseSet setToAdd = new ExerciseSet(reps, weight);
        this.sets.add(setToAdd);
    }

    /*
     * REQUIRES: 
     *  - sets.size() > 0
     * MODIFIES: this.sets
     * EFFECTS: removes the most recent set completed in the list of sets
     */
    public void removeSet() {
        this.sets.remove(this.sets.size() - 1);
    }


    /*
     * MODIFIES: this.sets
     * EFFECTS: clears all ExerciseSets from the list of sets
     */
    public void clearSets() {
        while (!this.sets.isEmpty()) {
            this.sets.remove(0);
        }
    }


    //EFFECTS: returns the sum of the total reps completed across all sets
    public int calculateTotalReps() {
        int totalReps = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            totalReps += this.sets.get(i).getReps();
        }

        return totalReps;
    }

    //EFFECTS: returns the sum of the total weight lifted across all sets
    public int calculateTotalWeightLifted() {
        int totalWeight = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            totalWeight += (this.sets.get(i).getReps() * this.sets.get(i).getWeight());
        }

        return totalWeight;
    }


    /*
     * SETTER METHODS:
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setMusclesWorked(List<String> musclesWorked) {
        this.musclesWorked = musclesWorked;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.isCompleted = completionStatus;
    }

    
    /*
     * GETTER METHODS:
     */
    public String getName() {
        return this.name;
    }

    public List<String> getMusclesWorked() {
        return this.musclesWorked;
    }

    public List<ExerciseSet> getSets() {
        return this.sets;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }
}

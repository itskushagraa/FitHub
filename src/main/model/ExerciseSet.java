package model;

import org.json.JSONObject;

import persistance.Writable;

/**
 * Represents one set of an exercise
 * Stores the number of reps and weight lifted in that set
 **/

public class ExerciseSet implements Writable {
    private int reps; // reps completed in the set
    private int weight; // weight lifted in the set

    /*
     * REQUIRES:
     * - reps > 0
     * - weight > 0
     *
     * EFFECTS:
     * - Initializes this ExerciseSet with the provided reps and weight lifted
     */
    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    // EFFECTS: returns an ExerciseSet as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("reps", this.reps);
        json.put("weight", this.weight);
        return json;
    }
    
    /*
     * SETTER METHODS:
     */
    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /*
     * GETTER METHODS:
     */
    public int getReps() {
        return this.reps;
    }

    public int getWeight() {
        return this.weight;
    }
}

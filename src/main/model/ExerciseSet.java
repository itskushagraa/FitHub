package model;

/** 
 * Represents one set of an exercise
 * Stores the number of reps and weight lifted in that set
**/

public class ExerciseSet {
    private int reps;       // reps completed in the set
    private int weight;     // weight lifted in the set

    /*
     * REQUIRES: 
     *  - reps > 0
     *  - weight > 0
     *
     * EFFECTS: 
     *  - Initializes this ExerciseSet with the provided reps and weight lifted
     */
    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
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

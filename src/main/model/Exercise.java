package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

/**
 * Represents an Exercise at the gym
 * Stores information about an exercise such as: name, muscles worked, sets
 * completed
 **/

public class Exercise implements Writable {
    private String name; // name of the exercise
    private List<String> musclesWorked; // list of muscles worked
    private List<ExerciseSet> sets; // sets completed in the exercise

    /*
     * REQUIRES:
     * - name.length() > 0
     * - musclesWorked.size() > 0
     * - sets.size() > 0
     *
     * EFFECTS:
     * - Initializes this Exercise with the provided name, muscles worked and sets
     * with initial completion status set to false
     */
    public Exercise(String name, List<String> musclesWorked, List<ExerciseSet> sets) {
        this.name = name;
        this.musclesWorked = musclesWorked;
        this.sets = sets;
    }

    // EFFECTS: returns an Exercise as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);

        JSONArray jsonMusclesWorked = new JSONArray();
        for (String muscle : this.musclesWorked) {
            jsonMusclesWorked.put(muscle);
        }
        json.put("musclesWorked", jsonMusclesWorked);

        JSONArray jsonSets = new JSONArray();
        for (ExerciseSet set : this.sets) {
            jsonSets.put(set.toJson());
        }
        json.put("sets", jsonSets);
        return json;
    }

    /*
     * REQUIRES:
     * - reps > 0
     * - weight > 0
     * MODIFIES: this.sets
     * EFFECTS: adds a set to the list of sets with the given reps and weight
     */
    public void addSet(int reps, int weight) {
        ExerciseSet set = new ExerciseSet(reps, weight);
        this.sets.add(set);
        EventLog.getInstance().logEvent(new Event("Added Set: " + set.getWeight() + " for " + set.getReps()
                + ((set.getReps() == 1) ? " rep" : " reps")));
    }

    /*
     * REQUIRES:
     * - sets.size() > 0
     * MODIFIES: this.sets
     * EFFECTS: removes the most recent set completed in the list of sets
     */
    public void removeSet() {
        this.sets.remove(this.sets.size() - 1);
        EventLog.getInstance().logEvent(new Event("Removed Latest Set"));
    }

    // EFFECTS: returns the sum of the total reps completed across all sets
    public int calculateTotalReps() {
        int totalReps = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            totalReps += this.sets.get(i).getReps();
        }

        return totalReps;
    }

    // EFFECTS: returns the sum of the total weight lifted across all sets
    public int calculateTotalWeightLifted() {
        int totalWeight = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            totalWeight += (this.sets.get(i).getReps() * this.sets.get(i).getWeight());
        }

        return totalWeight;
    }

    // EFFECTS: Logs an event where an exercise's information was viewed
    public void exerciseViewed() {
        EventLog.getInstance().logEvent(new Event("Viewed Exercise: " + this.name));
    }

    /*
     * SETTER METHODS:
     */
    public void setName(String name) {
        if (!this.name.equalsIgnoreCase(name)) {
            EventLog.getInstance().logEvent(new Event("Updated Exercise Name For: " + this.name + " To: " + name));
        }
        this.name = name;
    }

    public void setMusclesWorked(List<String> musclesWorked) {
        if (!this.musclesWorked.equals(musclesWorked)) {
            List<String> addedMuscles = getAddedMuscles(musclesWorked);
            List<String> removedMuscles = getRemovedMuscles(musclesWorked);

            if (addedMuscles.size() != 0) {
                for (String muscle : addedMuscles) {
                    EventLog.getInstance()
                            .logEvent(new Event("Added " + muscle + " To List Of Muscles Worked in " + this.name));
                }
            }

            if (removedMuscles.size() != 0) {
                for (String muscle : removedMuscles) {
                    EventLog.getInstance()
                            .logEvent(new Event("Removed " + muscle + "  List Of Muscles Worked in " + this.name));
                }
            }
        }
        this.musclesWorked = musclesWorked;
    }

    public void setSets(List<ExerciseSet> sets) {
        if (!this.sets.equals(sets)) {
            List<ExerciseSet> addedSets = getAddedSets(sets);
            List<ExerciseSet> removedSets = getRemovedSets(sets);
            if (addedSets.size() != 0) {
                for (ExerciseSet set : addedSets) {
                    EventLog.getInstance().logEvent(new Event("Added Set: " + set.getWeight() + " for " + set.getReps()
                            + ((set.getReps() == 1) ? " rep" : " reps")));
                }
            }
            if (removedSets.size() != 0) {
                for (ExerciseSet set : removedSets) {
                    EventLog.getInstance()
                            .logEvent(new Event("Removed Set: " + set.getWeight() + " for " + set.getReps()
                                    + ((set.getReps() == 1) ? " rep" : " reps")));
                }
            }
        }
        this.sets = sets;
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

    private List<String> getAddedMuscles(List<String> newMuscles) {
        List<String> addedMuscles = new ArrayList<>();
        for (String muscle : newMuscles) {
            if (!this.musclesWorked.contains(muscle)) {
                addedMuscles.add(muscle);
            }
        }
        return addedMuscles;
    }

    private List<String> getRemovedMuscles(List<String> newMuscles) {
        List<String> removedMuscles = new ArrayList<>();
        for (String muscle : this.musclesWorked) {
            if (!newMuscles.contains(muscle)) {
                removedMuscles.add(muscle);
            }
        }
        return removedMuscles;
    }

    private List<ExerciseSet> getAddedSets(List<ExerciseSet> newSets) {
        List<ExerciseSet> addedSets = new ArrayList<>();
        for (ExerciseSet set : newSets) {
            if (!this.sets.contains(set)) {
                addedSets.add(set);
            }
        }
        return addedSets;
    }

    private List<ExerciseSet> getRemovedSets(List<ExerciseSet> newSets) {
        List<ExerciseSet> removedSets = new ArrayList<>();
        for (ExerciseSet set : this.sets) {
            if (!newSets.contains(set)) {
                removedSets.add(set);
            }
        }
        return removedSets;
    }
}

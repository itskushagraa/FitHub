package persistance;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import model.DietPlan;
import model.UserProfile;
import model.Workout;

// Represents a writer that writes JSON representation of workroom to file

public class JsonWriter {
    private static final int INDENTATION = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens the writer; throws FileNotFoundException if destination file 
     *          cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of user profile to file
     */
    public void writeUserProfile(UserProfile userProfile) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of the workout split to file
     */
    public void writeWorkoutSplit(List<Workout> workoutSplit) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of the diet plan to file
     */
    public void writeDietPlan(DietPlan dietPlan) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes the writer
     */
    public void close() {

    }

     // EFFECTS: writes string to file with proper indentation
     public void saveToFile(String json) {
        // TODO
     }
}

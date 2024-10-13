package persistance;

import java.io.IOException;

import java.util.List;

import org.json.JSONObject;

import model.DietPlan;
import model.UserProfile;
import model.Workout;

/* Represents a leader that reads FitHub data (UserProfile, WorkoutSplit, DietPlan)
 * from JSON data stored in file
 */

public class JsonReader {
    private String source;

    /*
     * REQUIRES: source must be a valid file path
     * EFFECTS: constructs a reader to read from the specified source file
     */
    public JsonReader(String source) {
        // TODO
    }

    /*
     * REQUIRES: userProfile.json file must exist and contain valid JSON code
     * EFFECTS: reads UserProfile from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public UserProfile readUserProfile() throws IOException {
        return null; // TODO
    }

    /*
     * REQUIRES: workoutSplit.json file must exist and contain valid JSON code
     * EFFECTS: reads workoutSplit (list of Workout objects) from the file and
     * returns it;
     * throws IOException if an error occurs reading data from file
     */
    public List<Workout> readWorkoutSplit() throws IOException {
        return null; // TODO
    }

    /*
     * REQUIRES: dietPlan.json file must exist and contain valid JSON code
     * EFFECTS: reads dietPlan from the file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public DietPlan readDietPlan() throws IOException {
        return null; // TODO
    }

    /*
     * REQUIRES: source file must be valid and readable
     * EFFECTS: reads source file as a string and returns it;
     * throws IOException if an error occurs reading data from file
     */
    private String readFile(String source) throws IOException {
        return null; // TODO
    }

    /*
     * REQUIRES: JSONObject must represent a valid user profile
     * EFFECTS: parse UserProfile from JSONObject and returns it
     */
    private UserProfile parseUserProfile(JSONObject jsonObject) {
        return null; // TODO
    }

    /*
     * REQUIRES: JSONObject must represent a valid user profile
     * EFFECTS: parse List<Workout> from JSONObject and returns it
     */
    private List<Workout> parseWorkoutSplit(JSONObject jsonObject) {
        return null; // TODO
    }
}

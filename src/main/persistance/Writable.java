package persistance;

import org.json.JSONObject;

/*
 * If a class implements this interface:
 * It means that that class can be converted into a JSON Object and then be stored in JSON files
 * Essentially, the data in any class that implements this interface is persisted when the app runs
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

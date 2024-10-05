package data;

import java.util.Arrays;

import model.Exercise;
import model.ExerciseSet;

/*
 * - THIS CLASS IS A TEMPORARY REPRESENTATION OF DATA ONLY FOR PHASE 1
 * - OBJECTS DECLARED IN THIS CLASS ARE USED FOR TESTING PURPOSES
 * - ONCE PHASE 2 BEGINS, THIS CLASS WILL BE DELETED AND DATA WILL BE 
 *   REPRESENTED USING JSON FILES
 */

public class TemporaryData {

    public static final Exercise BENCH_PRESS = new Exercise(
            "Bench Press (Barbell)",
            Arrays.asList("Chest", "Front Delts", "Triceps"),
            Arrays.asList(new ExerciseSet(15, 20)));

    public static final Exercise SQUAT = new Exercise(
            "Squat (Barbell)",
            Arrays.asList("Quads", "Hamstrings", "Glutes"),
            Arrays.asList(new ExerciseSet(10, 60)));

    public static final Exercise LAT_PULLDOWN = new Exercise(
            "Lat Pulldown",
            Arrays.asList("Lats", "Biceps"),
            Arrays.asList(new ExerciseSet(10, 60)));

    public static final Exercise LATERAL_RAISE = new Exercise(
            "Lateral Raise (Cable)",
            Arrays.asList("Side Delts"),
            Arrays.asList(new ExerciseSet(10, 10)));

    public static final Exercise FACE_PULL = new Exercise(
            "Face Pull (Cable)",
            Arrays.asList("Rear Delts"),
            Arrays.asList(new ExerciseSet(10, 20)));

    private TemporaryData() {
    }
}

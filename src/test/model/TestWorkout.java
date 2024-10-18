package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWorkout {
    private Workout testWorkout;

    @BeforeEach
    void runBefore() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(BENCH_PRESS);
        testWorkout = new Workout("FBD", exercises);
    }

    @Test
    void testConstructor() {
        assertEquals("FBD", testWorkout.getName());
        assertEquals(BENCH_PRESS, testWorkout.getExercises().get(0));
        assertEquals(1, testWorkout.getExercises().size());
    }

    @Test
    void testToJson() {
        JSONObject json = testWorkout.toJson();
        assertEquals("FBD", json.getString("name"));
        assertEquals(1, json.getJSONArray("exercises").length());
        assertEquals("Bench Press (Barbell)", json.getJSONArray("exercises").getJSONObject(0).getString("name"));
        assertEquals("Chest",
                json.getJSONArray("exercises").getJSONObject(0).getJSONArray("musclesWorked").getString(0));
        assertEquals("Front Delts",
                json.getJSONArray("exercises").getJSONObject(0).getJSONArray("musclesWorked").getString(1));
        assertEquals("Triceps",
                json.getJSONArray("exercises").getJSONObject(0).getJSONArray("musclesWorked").getString(2));
        assertEquals(15,
                json.getJSONArray("exercises").getJSONObject(0).getJSONArray("sets").getJSONObject(0).getInt("reps"));
        assertEquals(20,
                json.getJSONArray("exercises").getJSONObject(0).getJSONArray("sets").getJSONObject(0).getInt("weight"));
    }

    @Test
    void testAddSingleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(SQUAT);
        assertEquals(2, testWorkout.getExercises().size());
        assertEquals(SQUAT, testWorkout.getExercises().get(1));
    }

    @Test
    void testAddMultipleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(FACE_PULL);
        testWorkout.addExercise(LATERAL_RAISE);
        testWorkout.addExercise(LAT_PULLDOWN);
        assertEquals(5, testWorkout.getExercises().size());
        assertEquals(SQUAT, testWorkout.getExercises().get(1));
        assertEquals(FACE_PULL, testWorkout.getExercises().get(2));
        assertEquals(LATERAL_RAISE, testWorkout.getExercises().get(3));
        assertEquals(LAT_PULLDOWN, testWorkout.getExercises().get(4));
    }

    @Test
    void testRemoveSingleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.removeExercise();
        assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    void testRemoveMultipleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        testWorkout.removeExercise();
        testWorkout.removeExercise();
        testWorkout.removeExercise();
        assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    void testClearWorkout() {
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(FACE_PULL);
        testWorkout.addExercise(LATERAL_RAISE);
        testWorkout.addExercise(LAT_PULLDOWN);
        assertEquals(5, testWorkout.getExercises().size());
        testWorkout.clearWorkout();
        assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    void calculateTotalVolume() {
        assertEquals(300, testWorkout.calculateTotalVolume());
        testWorkout.addExercise(SQUAT);
        assertEquals(900, testWorkout.calculateTotalVolume());
    }

    @Test
    void testCalculateTotalSets() {
        assertEquals(1, testWorkout.calculateTotalSets());
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        assertEquals(5, testWorkout.calculateTotalSets());
    }

    @Test
    void testCalculateTotalReps() {
        assertEquals(15, testWorkout.calculateTotalReps());
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        assertEquals(55, testWorkout.calculateTotalReps());
    }

    @Test
    void testCalculateMusclesWorked() {
        assertEquals("Chest", testWorkout.calculateMusclesWorked().get(0));
        assertEquals("Front Delts", testWorkout.calculateMusclesWorked().get(1));
        assertEquals("Triceps", testWorkout.calculateMusclesWorked().get(2));
        assertEquals(3, testWorkout.calculateMusclesWorked().size());
        testWorkout.addExercise(BENCH_PRESS);
        testWorkout.addExercise(SQUAT);
        testWorkout.addExercise(SQUAT);
        assertEquals("Quads", testWorkout.calculateMusclesWorked().get(3));
        assertEquals("Hamstrings", testWorkout.calculateMusclesWorked().get(4));
        assertEquals("Glutes", testWorkout.calculateMusclesWorked().get(5));
        assertEquals(6, testWorkout.calculateMusclesWorked().size());
        testWorkout.addExercise(FACE_PULL);
        testWorkout.addExercise(LATERAL_RAISE);
        testWorkout.addExercise(LAT_PULLDOWN);
        assertEquals("Rear Delts", testWorkout.calculateMusclesWorked().get(6));
        assertEquals("Side Delts", testWorkout.calculateMusclesWorked().get(7));
        assertEquals("Lats", testWorkout.calculateMusclesWorked().get(8));
        assertEquals("Biceps", testWorkout.calculateMusclesWorked().get(9));
        assertEquals(10, testWorkout.calculateMusclesWorked().size());
    }

    @Test
    void testFindExerciseByName() {
        testWorkout.addExercise(FACE_PULL);
        assertEquals(FACE_PULL, testWorkout.findExerciseByName("Face Pull (Cable)"));
        assertNull(testWorkout.findExerciseByName("Squat (Barbell)"));
    }

    @Test
    void testSetName() {
        testWorkout.setName("random workout");
        assertEquals("random workout", testWorkout.getName());
    }

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
}

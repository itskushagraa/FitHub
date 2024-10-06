package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.TemporaryData;

public class TestWorkout {
    private Workout testWorkout;

    @BeforeEach
    void runBefore() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(TemporaryData.BENCH_PRESS);
        testWorkout = new Workout("FBD", exercises, LocalDate.now(), 60);
    }

    @Test
    void testConstructor() {
        assertEquals("FBD", testWorkout.getName());
        assertEquals(TemporaryData.BENCH_PRESS, testWorkout.getExercises().get(0));
        assertEquals(1, testWorkout.getExercises().size());
        assertEquals(LocalDate.now(), testWorkout.getDate());
        assertEquals(60, testWorkout.getDuration());
        assertFalse(testWorkout.isCompleted());
    }

    @Test
    void testAddSingleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(TemporaryData.SQUAT);
        assertEquals(2, testWorkout.getExercises().size());
        assertEquals(TemporaryData.SQUAT, testWorkout.getExercises().get(1));
    }

    @Test
    void testAddMultipleExercise() {
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.FACE_PULL);
        testWorkout.addExercise(TemporaryData.LATERAL_RAISE);
        testWorkout.addExercise(TemporaryData.LAT_PULLDOWN);
        assertEquals(5, testWorkout.getExercises().size());
        assertEquals(TemporaryData.SQUAT, testWorkout.getExercises().get(1));
        assertEquals(TemporaryData.FACE_PULL, testWorkout.getExercises().get(2));
        assertEquals(TemporaryData.LATERAL_RAISE, testWorkout.getExercises().get(3));
        assertEquals(TemporaryData.LAT_PULLDOWN, testWorkout.getExercises().get(4));
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
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.removeExercise();
        testWorkout.removeExercise();
        testWorkout.removeExercise();
        assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    void testClearWorkout() {
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.FACE_PULL);
        testWorkout.addExercise(TemporaryData.LATERAL_RAISE);
        testWorkout.addExercise(TemporaryData.LAT_PULLDOWN);
        assertEquals(5, testWorkout.getExercises().size());
        testWorkout.clearWorkout();
        assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    void calculateTotalVolume() {
        assertEquals(300, testWorkout.calculateTotalVolume());
        testWorkout.addExercise(TemporaryData.SQUAT);
        assertEquals(900, testWorkout.calculateTotalVolume());
    }

    @Test
    void testCalculateTotalSets() {
        assertEquals(1, testWorkout.calculateTotalSets());
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        assertEquals(5, testWorkout.calculateTotalSets());
    }

    @Test
    void testCalculateTotalReps() {
        assertEquals(15, testWorkout.calculateTotalReps());
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        assertEquals(55, testWorkout.calculateTotalReps());
    }

    @Test
    void testCalculateMusclesWorked() {
        assertEquals("Chest", testWorkout.calculateMusclesWorked().get(0));
        assertEquals("Front Delts", testWorkout.calculateMusclesWorked().get(1));
        assertEquals("Triceps", testWorkout.calculateMusclesWorked().get(2));
        assertEquals(3, testWorkout.calculateMusclesWorked().size());
        testWorkout.addExercise(TemporaryData.BENCH_PRESS);
        testWorkout.addExercise(TemporaryData.SQUAT);
        testWorkout.addExercise(TemporaryData.SQUAT);
        assertEquals("Quads", testWorkout.calculateMusclesWorked().get(3));
        assertEquals("Hamstrings", testWorkout.calculateMusclesWorked().get(4));
        assertEquals("Glutes", testWorkout.calculateMusclesWorked().get(5));
        assertEquals(6, testWorkout.calculateMusclesWorked().size());
        testWorkout.addExercise(TemporaryData.FACE_PULL);
        testWorkout.addExercise(TemporaryData.LATERAL_RAISE);
        testWorkout.addExercise(TemporaryData.LAT_PULLDOWN);
        assertEquals("Rear Delts", testWorkout.calculateMusclesWorked().get(6));
        assertEquals("Side Delts", testWorkout.calculateMusclesWorked().get(7));
        assertEquals("Lats", testWorkout.calculateMusclesWorked().get(8));
        assertEquals("Biceps", testWorkout.calculateMusclesWorked().get(9));
        assertEquals(10, testWorkout.calculateMusclesWorked().size());
    }

    @Test
    void testFindExerciseByName() {
        testWorkout.addExercise(TemporaryData.FACE_PULL);
        assertEquals(TemporaryData.FACE_PULL, testWorkout.findExerciseByName("Face Pull (Cable)"));
        assertNull(testWorkout.findExerciseByName("Squat (Barbell)"));
    }

    @Test
    void testSetterMethods() {
        testWorkout.setName("random workout");
        assertEquals("random workout", testWorkout.getName());
        testWorkout.setDuration(1000);
        assertEquals(1000, testWorkout.getDuration());
        testWorkout.setDate(LocalDate.of(2024, 10, 1));
        assertEquals(LocalDate.of(2024, 10, 1), testWorkout.getDate());
        testWorkout.setCompletionStatus(true);
        assertTrue(testWorkout.isCompleted());

    }
}

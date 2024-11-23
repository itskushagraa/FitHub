package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TestExercise {
    private Exercise testExercise;

    private ExerciseSet set1 = new ExerciseSet(15, 20);
    private List<ExerciseSet> sets = new ArrayList<>();
    private List<String> musclesWorked = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        musclesWorked.add("Chest");
        musclesWorked.add("Front Delts");
        musclesWorked.add("Triceps");
        sets.add(set1);
        testExercise = new Exercise("Barbell Bench Press", musclesWorked, sets);
    }

    @Test
    void testConstructor() {
        assertEquals("Barbell Bench Press", testExercise.getName());
        assertEquals("Chest", testExercise.getMusclesWorked().get(0));
        assertEquals("Front Delts", testExercise.getMusclesWorked().get(1));
        assertEquals("Triceps", testExercise.getMusclesWorked().get(2));
        assertEquals(set1, testExercise.getSets().get(0));
    }

    @Test 
    void testToJson() {
        JSONObject json = testExercise.toJson();
        assertEquals("Barbell Bench Press", json.getString("name"));
        assertEquals("Chest", json.getJSONArray("musclesWorked").getString(0));
        assertEquals("Front Delts", json.getJSONArray("musclesWorked").getString(1));
        assertEquals("Triceps", json.getJSONArray("musclesWorked").getString(2));
        assertEquals(15, json.getJSONArray("sets").getJSONObject(0).getInt("reps"));
        assertEquals(20, json.getJSONArray("sets").getJSONObject(0).getInt("weight"));
    }

    @Test
    void testAddSingleSet() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.addSet(12, 20);
        assertEquals(2, testExercise.getSets().size());
        assertEquals(12, testExercise.getSets().get(1).getReps());
        assertEquals(20, testExercise.getSets().get(1).getWeight());
    }

    @Test
    void testAddMultipleSet() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.addSet(12, 20);
        testExercise.addSet(1, 120);
        assertEquals(3, testExercise.getSets().size());
        assertEquals(12, testExercise.getSets().get(1).getReps());
        assertEquals(20, testExercise.getSets().get(1).getWeight());
        assertEquals(1, testExercise.getSets().get(2).getReps());
        assertEquals(120, testExercise.getSets().get(2).getWeight());
    }

    @Test
    void testRemoveSingleSet() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.removeSet();
        assertEquals(0, testExercise.getSets().size());
    }

    @Test
    void testRemoveMultipleSets() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.addSet(12, 20);
        testExercise.addSet(1, 120);
        assertEquals(3, testExercise.getSets().size());
        testExercise.removeSet();
        assertEquals(2, testExercise.getSets().size());
        testExercise.removeSet();
        assertEquals(1, testExercise.getSets().size());
        testExercise.removeSet();
        assertEquals(0, testExercise.getSets().size());
    }

    @Test
    void testClearSetSingle() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.clearSets();
        assertEquals(0, testExercise.getSets().size());
    }

    @Test
    void testClearSetMultiple() {
        assertEquals(1, testExercise.getSets().size());
        testExercise.addSet(12, 20);
        testExercise.addSet(1, 120);
        assertEquals(3, testExercise.getSets().size());
        testExercise.clearSets();
        assertEquals(0, testExercise.getSets().size());
    }

    @Test
    void testCalculateTotalRepsSingle() {
        assertEquals(15, testExercise.calculateTotalReps());
    }

    @Test
    void testCalculateTotalRepsMultiple() {
        testExercise.addSet(12, 60);
        testExercise.addSet(1, 120);
        assertEquals(28, testExercise.calculateTotalReps());
    }

    @Test
    void testCalculateTotalWeightSingle() {
        assertEquals(300, testExercise.calculateTotalWeightLifted());
    }

    @Test
    void testCalculateTotalWeightMultiple() {
        testExercise.addSet(12, 60);
        testExercise.addSet(1, 120);
        assertEquals(1140, testExercise.calculateTotalWeightLifted());
    }

    @Test
    void testSetterMethods() {
        List<String> musclesWorked = new ArrayList<>();
        musclesWorked.add("Quadriceps");
        musclesWorked.add("Hamstrings");
        musclesWorked.add("Glutes");
        musclesWorked.add("Calves");
        musclesWorked.add("Lower Back");

        List<ExerciseSet> sets = new ArrayList<>();
        sets.add(new ExerciseSet(100, 100));
        sets.add(new ExerciseSet(50, 50));
        sets.add(new ExerciseSet(10, 10));

        testExercise.setSets(sets);
        testExercise.setName("Barbell Squat");
        testExercise.setMusclesWorked(musclesWorked);

        assertEquals("Barbell Squat", testExercise.getName());
        assertEquals(musclesWorked, testExercise.getMusclesWorked());
        assertEquals(sets, testExercise.getSets());
    }
}

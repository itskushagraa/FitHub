package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestExerciseSet {
    private ExerciseSet testSet;

    @BeforeEach
    void runBefore() {
        testSet = new ExerciseSet(10, 100);
    }

    @Test
    void testConstructor() {
        assertEquals(10, testSet.getReps());
        assertEquals(100, testSet.getWeight());
    }

    @Test
    void testToJson() {
        JSONObject json = testSet.toJson();
        assertEquals(10, json.getInt("reps"));
        assertEquals(100, json.getInt("weight"));
    }

    @Test
    void testSetterMethods() {
        testSet.setReps(5);
        testSet.setWeight(150);
        assertEquals(5, testSet.getReps());
        assertEquals(150, testSet.getWeight());
    }
}

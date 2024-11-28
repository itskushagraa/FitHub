package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testEquals_SameObject() {
        ExerciseSet set = new ExerciseSet(10, 100); // Example constructor with reps and weight
        assertTrue(set.equals(set));
    }

    @Test
    void testEquals_NullObject() {
        ExerciseSet set = new ExerciseSet(10, 100);
        assertFalse(set.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals_DifferentClass() {
        ExerciseSet set = new ExerciseSet(10, 100);
        String other = "Not an ExerciseSet";
        assertFalse(set.equals(other));
    }

    @Test
    void testEquals_DifferentReps() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(5, 100);
        assertFalse(set1.equals(set2));
    }

    @Test
    void testEquals_DifferentWeight() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(10, 200);
        assertFalse(set1.equals(set2));
    }

    @Test
    void testEquals_SameValues() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(10, 100);
        assertTrue(set1.equals(set2));
    }

    @Test
    void testHashCode_SameValues() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(10, 100);
        assertEquals(set1.hashCode(), set2.hashCode());
    }

    @Test
    void testHashCode_DifferentReps() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(5, 100);
        assertNotEquals(set1.hashCode(), set2.hashCode());
    }

    @Test
    void testHashCode_DifferentWeight() {
        ExerciseSet set1 = new ExerciseSet(10, 100);
        ExerciseSet set2 = new ExerciseSet(10, 200);
        assertNotEquals(set1.hashCode(), set2.hashCode());
    }
}

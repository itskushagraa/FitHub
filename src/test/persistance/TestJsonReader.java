package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.DietPlan;
import model.UserProfile;
import model.Workout;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;

public class TestJsonReader {
    private JsonReader reader;
    private UserProfile testUser;
    private List<Workout> testSplit;
    private DietPlan testPlan;

    @Test
    void testReaderNonExistentUserProfile() {
        reader = new JsonReader("./data/NonExistentUserProfile.json");
        try {
            testUser = reader.readUserProfile();
            fail("IOException expected but not thrown");
        } catch (IOException e) {
            // TEST PASSED
        }
    }

    @Test
    void testReaderEmptyUserProfile() {
        reader = new JsonReader("./data/testReaderEmptyUserProfile.json");
        try {
            testUser = reader.readUserProfile();
            assertEquals("", testUser.getName());
            assertEquals(0.0, testUser.getHeight());
            assertEquals(0.0, testUser.getWeight());
            assertEquals(0.0, testUser.getBmi());
            assertEquals(0, testUser.getAge());
            assertEquals(0, testUser.getIntensity());
            assertEquals("", testUser.getGoal());
            assertEquals(0.0, testUser.getTargetCalories());
            assertTrue(testUser.getWorkoutList().isEmpty());
            assertTrue(testUser.getDietPlan().getCompleteWeeklyPlan().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUserProfile() {
        reader = new JsonReader("./data/testReaderGeneralUserProfile.json");
        try {
            testUser = reader.readUserProfile();
            assertEquals("Ellie Williams", testUser.getName());
            assertEquals(165.0, testUser.getHeight());
            assertEquals(60.0, testUser.getWeight());
            assertEquals(22.0, testUser.getBmi());
            assertEquals(19, testUser.getAge());
            assertEquals(5, testUser.getIntensity());
            assertEquals("maintain", testUser.getGoal());
            assertEquals(2260.5, testUser.getTargetCalories());
            assertEquals("Push Day", testUser.getWorkoutList().get(0).getName());
            assertEquals("Pull Day", testUser.getWorkoutList().get(1).getName());
            assertEquals("Leg Day", testUser.getWorkoutList().get(2).getName());
            assertEquals(3, testUser.getWorkoutList().size());
            assertEquals(7, testUser.getWorkoutList().get(0).getExercises().size());
            assertEquals(5, testUser.getWorkoutList().get(2).getExercises().size());
            assertTrue(!testUser.getDietPlan().getCompleteWeeklyPlan().isEmpty());
            assertEquals(3, testUser.getDietPlan().getCompleteWeeklyPlan().get("Monday").size());
            assertEquals(7, testUser.getDietPlan().getCompleteWeeklyPlan().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonExistentWorkoutSplit() {
        reader = new JsonReader("./data/NonExistentWorkoutSplit.json");
        try {
            testSplit = reader.readWorkoutSplit();
            fail("IOException expected but not thrown");
        } catch (IOException e) {
            // TEST PASSED
        }
    }

    @Test
    void testReaderEmptyWorkoutSplit() {
        reader = new JsonReader("./data/testReaderEmptyWorkoutSplit.json");
        try {
            testSplit = reader.readWorkoutSplit();
            assertEquals(3, testSplit.size());
            for (int i = 0; i < 3; i++) {
                assertEquals("", testSplit.get(i).getName());
                assertTrue(testSplit.get(i).getExercises().isEmpty());
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkoutSplit() {
        reader = new JsonReader("./data/testReaderGeneralWorkoutSplit.json");
        try {
            testSplit = reader.readWorkoutSplit();
            assertEquals(3, testSplit.size());
            assertEquals("Push Day", testSplit.get(0).getName());
            assertEquals("Pull Day", testSplit.get(1).getName());
            assertEquals("Leg Day", testSplit.get(2).getName());
            assertEquals(7, testSplit.get(0).getExercises().size());
            assertEquals(7, testSplit.get(1).getExercises().size());
            assertEquals(5, testSplit.get(2).getExercises().size());
            for (int i = 0; i < testSplit.size(); i++) {
                assertFalse(testSplit.get(i).getExercises().isEmpty());
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonExistentDietPlan() {
        reader = new JsonReader("./data/NonExistentDietPlan.json");
        try {
            testPlan = reader.readDietPlan();
            fail("IOException expected but not thrown");
        } catch (IOException e) {
            // TEST PASSED
        }
    }

    @Test
    void testReaderEmptyDietPlan() {
        reader = new JsonReader("./data/testReaderEmptyDietPlan.json");
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        try {
            testPlan = reader.readDietPlan();
            assertEquals(7, testPlan.getCompleteWeeklyPlan().size());
            for (String day : daysOfWeek) {
                assertEquals(3, testUser.getDietPlan().getCompleteWeeklyPlan().get(day).size());
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDietPlan() {
        reader = new JsonReader("./data/testReaderGeneralDietPlan.json");
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        try {
            testPlan = reader.readDietPlan();
            assertEquals(7, testPlan.getCompleteWeeklyPlan().size());
            Set<String> keys = testPlan.getCompleteWeeklyPlan().keySet();
            assertTrue(keys.containsAll(daysOfWeek));
            for (int i = 0; i < 7; i++) {
                assertEquals(3, testPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(i)).size());
                assertFalse(testPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(i)).isEmpty());
                assertEquals("Breakfast", testPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(i)).get(0).getName());
                assertEquals("Lunch", testPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(i)).get(1).getName());
                assertEquals("Dinner", testPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(i)).get(2).getName());
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

package persistance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import model.UserProfile;
import model.Workout;
import model.Exercise;
import model.ExerciseSet;
import model.Meal;
import model.DietPlan;

public class TestJsonWriter {
    private JsonWriter writer;
    private JsonReader reader;
    private UserProfile testProfile;
    private List<Workout> testSplit;
    private DietPlan testPlan;

    @Test
    void testInvalidFile() {
        try {
            writer = new JsonWriter("./data/Writer Test Data/Invalid\0File\0Name.json");
            writer.open();
            fail("IOException expected but not thrown");
        } catch (IOException e) {
            // TEST PASSES
        }
    }

    @Test
    void testWriterEmptyUserProfile() {
        try {
            testProfile = new UserProfile("", 0.0, 0.0, 0, 0, "");
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterEmptyUserProfile.json");
            writer.open();
            writer.writeUserProfile(testProfile);
            writer.close();

            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterEmptyUserProfile.json");
            UserProfile readProfile = reader.readUserProfile();

            assertEquals("", readProfile.getName());
            assertEquals(0.0, readProfile.getHeight());
            assertEquals(0.0, readProfile.getWeight());
            assertEquals(0, readProfile.getAge());
            assertEquals(0, readProfile.getIntensity());
            assertEquals("", readProfile.getGoal());
            /*
             * - The following attributes are automatically generated by UserProfile.java
             * - The writer does NOT read these attributes from the json files
             * - These tests aim to confirm that automatic generation is completed as
             * expected when calling the UserProfile constructor.
             */
            assertEquals(0.0, readProfile.getBmi());
            assertEquals(5.67, readProfile.getTargetCalories());
            assertFalse(readProfile.getWorkoutList().isEmpty());
            assertFalse(readProfile.getDietPlan().getCompleteWeeklyPlan().isEmpty());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUserProfile() {
        try {
            testProfile = new UserProfile("Ellie Williams", 165.0, 60.0, 19, 5, "maintain");
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterGeneralUserProfile.json");
            writer.open();
            writer.writeUserProfile(testProfile);
            writer.close();

            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterGeneralUserProfile.json");
            UserProfile readProfile = reader.readUserProfile();

            assertEquals("Ellie Williams", readProfile.getName());
            assertEquals(165.0, readProfile.getHeight());
            assertEquals(60.0, readProfile.getWeight());
            assertEquals(19, readProfile.getAge());
            assertEquals(5, readProfile.getIntensity());
            assertEquals("maintain", readProfile.getGoal());
            /*
             * - The following attributes are automatically generated by UserProfile.java
             * - The writer does NOT read these attributes from the json files
             * - These tests aim to confirm that automatic generation is completed as
             * expected when calling the UserProfile constructor.
             */
            assertEquals(22.0, readProfile.getBmi());
            assertEquals(2260.5, readProfile.getTargetCalories());
            assertEquals("Push Day", readProfile.getWorkoutList().get(0).getName());
            assertEquals("Pull Day", readProfile.getWorkoutList().get(1).getName());
            assertEquals("Leg Day", readProfile.getWorkoutList().get(2).getName());
            assertEquals(7, readProfile.getDietPlan().getCompleteWeeklyPlan().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyWorkoutSplit() {
        try {
            Workout workout = new Workout("", new ArrayList<>());
            testSplit = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                testSplit.add(workout);
            }
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterEmptyWorkoutSplit.json");
            writer.open();
            writer.writeWorkoutSplit(testSplit);
            writer.close();
            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterEmptyWorkoutSplit.json");
            List<Workout> readSplit = reader.readWorkoutSplit();
            assertEquals(3, readSplit.size());
            for (int i = 0; i < 3; i++) {
                assertEquals("", readSplit.get(i).getName());
                assertTrue(readSplit.get(i).getExercises().isEmpty());
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkoutSplit() {
        try {
            List<Workout> testSplit = createTestSplit();
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterGeneralWorkoutSplit.json");
            writer.open();
            writer.writeWorkoutSplit(testSplit);
            writer.close();
            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterGeneralWorkoutSplit.json");
            List<Workout> readSplit = reader.readWorkoutSplit();
            assertEquals(3, readSplit.size());
            for (int i = 0; i < 3; i++) {
                assertEquals("Test Workout", readSplit.get(i).getName());
                for (int j = 0; j < readSplit.get(i).getExercises().size(); j++) {
                    List<String> musclesWorked = new ArrayList<>(Arrays.asList("muscle1", "muscle2", "muscle3"));
                    assertEquals("Test Exercise", readSplit.get(i).getExercises().get(j).getName());
                    assertEquals(musclesWorked, readSplit.get(i).getExercises().get(j).getMusclesWorked());
                    assertEquals(20, readSplit.get(i).getExercises().get(j).calculateTotalReps());
                    assertEquals(1000, readSplit.get(i).getExercises().get(j).calculateTotalWeightLifted());
                }
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyDietPlan() {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        try {
            testPlan = createEmptyPlan();
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterEmptyDietPlan.json");
            writer.open();
            writer.writeDietPlan(testPlan);
            writer.close();

            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterEmptyDietPlan.json");
            DietPlan readPlan = reader.readDietPlan();
            assertEquals(7, readPlan.getCompleteWeeklyPlan().size());
            for (String day : daysOfWeek) {
                assertEquals(3, readPlan.getCompleteWeeklyPlan().get(day).size());
                assertEquals("Breakfast", readPlan.getCompleteWeeklyPlan().get(day).get(0).getType());
                assertEquals("Lunch", readPlan.getCompleteWeeklyPlan().get(day).get(1).getType());
                assertEquals("Dinner", readPlan.getCompleteWeeklyPlan().get(day).get(2).getType());
                assertEquals("", readPlan.getCompleteWeeklyPlan().get(day).get(0).getName());
                assertEquals("", readPlan.getCompleteWeeklyPlan().get(day).get(1).getName());
                assertEquals("", readPlan.getCompleteWeeklyPlan().get(day).get(2).getName());
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDietPlan() {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        try {
            testPlan = createGeneralPlan();
            writer = new JsonWriter("./data/Test Data/Writer Test Data/testWriterGeneralDietPlan.json");
            writer.open();
            writer.writeDietPlan(testPlan);
            writer.close();

            reader = new JsonReader("./data/Test Data/Writer Test Data/testWriterGeneralDietPlan.json");
            DietPlan readPlan = reader.readDietPlan();
            assertEquals(7, readPlan.getCompleteWeeklyPlan().size());
            for (String day : daysOfWeek) {
                assertEquals(3, readPlan.getCompleteWeeklyPlan().get(day).size());
                assertEquals("Test Breakfast", readPlan.getCompleteWeeklyPlan().get(day).get(0).getName());
                assertEquals("Test Lunch", readPlan.getCompleteWeeklyPlan().get(day).get(1).getName());
                assertEquals("Test Dinner", readPlan.getCompleteWeeklyPlan().get(day).get(2).getName());
                assertEquals(500.0, readPlan.getCompleteWeeklyPlan().get(day).get(0).getCalories());
                assertEquals(250.0, readPlan.getCompleteWeeklyPlan().get(day).get(1).getQuantity());
                assertEquals(10.0, readPlan.getCompleteWeeklyPlan().get(day).get(2).getProtein());
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    // EFFECTS: creates a DietPlan with sample values as attributes and returns it
    private DietPlan createGeneralPlan() {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        Meal testBreakfast = new Meal("Test Breakfast", "Breakfast", new ArrayList<>(), 500.0, 250.0, 10.0, 10.0,
                100.0);
        Meal testLunch = new Meal("Test Lunch", "Lunch", new ArrayList<>(), 500.0, 250.0, 10.0, 10.0, 100.0);
        Meal testDinner = new Meal("Test Dinner", "Dinner", new ArrayList<>(), 500.0, 250.0, 10.0, 10.0, 100.0);
        Map<String, List<Meal>> dietPlanMap = new HashMap<>();
        for (String day : daysOfWeek) {
            dietPlanMap.put(day, new ArrayList<>(Arrays.asList(testBreakfast, testLunch, testDinner)));
        }
        return new DietPlan(dietPlanMap);
    }

    // EFFECTS: creates a DietPlan with empty attributes and returns it
    private DietPlan createEmptyPlan() {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        Meal testBreakfast = new Meal("", "Breakfast", new ArrayList<>(), 0.0, 0.0, 0.0, 0.0, 0.0);
        Meal testLunch = new Meal("", "Lunch", new ArrayList<>(), 0.0, 0.0, 0.0, 0.0, 0.0);
        Meal testDinner = new Meal("", "Dinner", new ArrayList<>(), 0.0, 0.0, 0.0, 0.0, 0.0);
        Map<String, List<Meal>> dietPlanMap = new HashMap<>();
        for (String day : daysOfWeek) {
            dietPlanMap.put(day, new ArrayList<>(Arrays.asList(testBreakfast, testLunch, testDinner)));
        }
        DietPlan testPlan = new DietPlan(dietPlanMap);
        return testPlan;
    }

    // EFFECTS: creates a sample List<Workout> and returns it
    private List<Workout> createTestSplit() {
        List<String> musclesWorked = new ArrayList<>(Arrays.asList("muscle1", "muscle2", "muscle3"));
        ExerciseSet testSet = new ExerciseSet(10, 50);
        List<ExerciseSet> testSets = new ArrayList<>(Arrays.asList(testSet, testSet));
        Exercise testExercise = new Exercise("Test Exercise", musclesWorked, testSets);
        Workout testWorkout = new Workout("Test Workout", new ArrayList<>(Arrays.asList(testExercise, testExercise)));
        List<Workout> testSplit = new ArrayList<>(Arrays.asList(testWorkout, testWorkout, testWorkout));
        return testSplit;
    }
}

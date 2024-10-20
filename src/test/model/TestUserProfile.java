package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistance.JsonReader;

public class TestUserProfile {
    private UserProfile maintainUser;
    private UserProfile bulkUser;
    private UserProfile cutUser;

    @BeforeEach
    void runBefore() throws IOException {
        maintainUser = new UserProfile("Ellie", 165.0, 60.0, 19, 5, "maintain");
        bulkUser = new UserProfile("Joel", 180, 80, 40, 4, "bulk");
        cutUser = new UserProfile("Dina", 160, 55, 19, 3, "cut");
    }

    @Test
    void testConstructor() {
        assertEquals("Ellie", maintainUser.getName());
        assertEquals(165.0, maintainUser.getHeight());
        assertEquals(60.0, maintainUser.getWeight());
        assertEquals(22.0, maintainUser.getBmi());
        assertEquals(19, maintainUser.getAge());
        assertEquals(5, maintainUser.getIntensity());
        assertEquals("maintain", maintainUser.getGoal());
        assertEquals(2260.5, maintainUser.getTargetCalories());
        assertEquals(3, maintainUser.getWorkoutList().size());
        assertEquals("Push Day", maintainUser.getWorkoutList().get(0).getName());
        assertEquals("Pull Day", maintainUser.getWorkoutList().get(1).getName());
        assertEquals("Leg Day", maintainUser.getWorkoutList().get(2).getName());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Monday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Tuesday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Wednesday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Thursday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Friday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Saturday").size());
        assertEquals(3, maintainUser.getDietPlan().getCompleteWeeklyPlan().get("Sunday").size());
    }

    @Test
    void testSave() throws IOException {
        try {
            UserProfile originalProfile = new UserProfile("Ellie", 165.0, 60.0, 19, 5, "maintain");

            originalProfile.save("./data/Test Data/Save Test Data/userProfile.json",
                    "./data/Test Data/Save Test Data/workoutSplit.json",
                    "./data/Test Data/Save Test Data/dietPlan.json");

            JsonReader userReader = new JsonReader("./data/Test Data/Save Test Data/userProfile.json");
            JsonReader workoutSplitReader = new JsonReader("./data/Test Data/Save Test Data/workoutSplit.json");
            JsonReader dietPlanReader = new JsonReader("./data/Test Data/Save Test Data/dietPlan.json");

            UserProfile savedProfile = userReader.readUserProfile();
            List<Workout> savedSplit = workoutSplitReader.readWorkoutSplit();
            DietPlan savedPlan = dietPlanReader.readDietPlan();

            assertEquals(originalProfile, savedProfile);
            assertEquals(originalProfile.getWorkoutList(), savedSplit);
            assertEquals(originalProfile.getDietPlan(), savedPlan);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testLoad() throws IOException {
        UserProfile testProfile = new UserProfile("", 0.0, 0.0, 0, 0, "");

        testProfile.load("./data/Test Data/Load Test Data/userProfile.json",
                    "./data/Test Data/Load Test Data/workoutSplit.json",
                    "./data/Test Data/Load Test Data/dietPlan.json");

        assertEquals("Ellie", testProfile.getName());
        assertEquals(165.0, testProfile.getHeight());
        assertEquals(60.0, testProfile.getWeight());
        assertEquals(19, testProfile.getAge());
        assertEquals(5, testProfile.getIntensity());
        assertEquals("maintain", testProfile.getGoal());

        assertEquals(3, testProfile.getWorkoutList().size());
        assertEquals("Push Day", testProfile.getWorkoutList().get(0).getName());
        assertEquals("Pull Day", testProfile.getWorkoutList().get(0).getName());
        assertEquals("Leg Day", testProfile.getWorkoutList().get(0).getName());


        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Monday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Tuesday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Wednesday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Thursday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Friday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Saturday").size());
        assertEquals(3, testProfile.getDietPlan().getCompleteWeeklyPlan().get("Sunday").size());
    }

    @Test
    void testToJson() {
        JSONObject json = maintainUser.toJson();
        assertEquals("Ellie", json.getString("name"));
        assertEquals(165.0, json.getDouble("height"), 0.001);
        assertEquals(60.0, json.getDouble("weight"), 0.001);
        assertEquals(22.0, json.getDouble("bmi"));
        assertEquals(19, json.getInt("age"));
        assertEquals(5, json.getInt("intensity"));
        assertEquals("maintain", json.getString("goal"));
        assertEquals(2260.5, json.getDouble("targetCalories"));
    }

    @Test
    void testCalculateBMI() {
        assertEquals(22.0, maintainUser.getBmi());
        assertEquals(24.7, bulkUser.getBmi());
        assertEquals(21.5, cutUser.getBmi());
    }

    @Test
    void testCalculateTargetCalories() {
        assertEquals(2260.5, maintainUser.calculateCalories());
        assertEquals(1654.67, cutUser.calculateCalories());
        assertEquals(2785.30, bulkUser.calculateCalories());
    }

    @Test
    void testSetterMethods() {
        maintainUser.setName("Jesse");
        maintainUser.setHeight(185);
        maintainUser.setWeight(85);
        maintainUser.setBmi();
        maintainUser.setAge(25);
        maintainUser.setIntensity(7);
        maintainUser.setGoal("bulk");
        maintainUser.setTargetCalories(3000.0);

        assertEquals("Jesse", maintainUser.getName());
        assertEquals(185, maintainUser.getHeight());
        assertEquals(85, maintainUser.getWeight());
        assertEquals(24.8, maintainUser.getBmi());
        assertEquals(25, maintainUser.getAge());
        assertEquals(7, maintainUser.getIntensity());
        assertEquals("bulk", maintainUser.getGoal());
        assertEquals(3000.0, maintainUser.getTargetCalories());
    }
}

package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.ModelExerciseData;
import data.ModelMealData;

public class TestUserProfile {
    private UserProfile maintainUser;
    private UserProfile bulkUser;
    private UserProfile cutUser;

    @BeforeEach
    void runBefore() {
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
        assertEquals(1654.6666666666665, cutUser.calculateCalories());
        assertEquals(2785.2999999999997, bulkUser.calculateCalories());
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

    @Test
    void testGeneratePush() {
        Workout testPushDay = maintainUser.generatePush();
        assertEquals(7, testPushDay.getExercises().size());
        assertEquals("Chest", testPushDay.getExercises().get(0).getMusclesWorked().get(0));
        assertEquals("Chest", testPushDay.getExercises().get(1).getMusclesWorked().get(0));
        assertEquals("Chest", testPushDay.getExercises().get(2).getMusclesWorked().get(0));
        assertEquals("Chest", testPushDay.getExercises().get(3).getMusclesWorked().get(0));
        assertEquals("Triceps", testPushDay.getExercises().get(4).getMusclesWorked().get(0));
        assertEquals("Triceps", testPushDay.getExercises().get(5).getMusclesWorked().get(0));
        assertEquals("Side Delts", testPushDay.getExercises().get(6).getMusclesWorked().get(0));
    }

    @Test
    void testGeneratePull() {
        Workout testPullDay = maintainUser.generatePull();
        assertEquals(7, testPullDay.getExercises().size());
        assertEquals("Upper Back", testPullDay.getExercises().get(0).getMusclesWorked().get(0));
        assertEquals("Upper Back", testPullDay.getExercises().get(1).getMusclesWorked().get(0));
        assertEquals("Lower Back", testPullDay.getExercises().get(2).getMusclesWorked().get(0));
        assertEquals("Biceps", testPullDay.getExercises().get(3).getMusclesWorked().get(0));
        assertEquals("Biceps", testPullDay.getExercises().get(4).getMusclesWorked().get(0));
        assertEquals("Lats", testPullDay.getExercises().get(5).getMusclesWorked().get(0));
        assertEquals("Rear Delts", testPullDay.getExercises().get(6).getMusclesWorked().get(0));
    }

    @Test
    void testGenerateLegs() {
        Workout testLegDay = maintainUser.generateLegs();
        assertEquals(5, testLegDay.getExercises().size());
        for (int i = 0; i < 4; i++) {
            assertTrue(ModelExerciseData.LEGS_LIST.contains(testLegDay.getExercises().get(i)));
        }
        assertTrue(ModelExerciseData.ABS_LIST.contains(testLegDay.getExercises().get(4)));
    }

    @Test
    void testSelectRandomExercises() {
        List<Exercise> testListExercise = maintainUser.selectRandomExercises(ModelExerciseData.CHEST_LIST, 8);

        assertEquals(8, testListExercise.size());
        for (int i = 0; i < testListExercise.size(); i++) {
            assertEquals("Chest", testListExercise.get(i).getMusclesWorked().get(0));
            assertTrue(ModelExerciseData.CHEST_LIST.contains(testListExercise.get(i)));
        }
    }

    @Test
    void testGenerateWorkoutList() {
        List<Workout> testSplit = maintainUser.generateWorkoutList();
        assertEquals(3, testSplit.size());
        assertEquals("Push Day", testSplit.get(0).getName());
        assertEquals("Pull Day", testSplit.get(1).getName());
        assertEquals("Leg Day", testSplit.get(2).getName());
    }

    @Test
    void testAdjustQuantity() {
        Map<String, List<Meal>> testMap = new HashMap<>();
        List<Meal> testList = new ArrayList<>(
                Arrays.asList(ModelMealData.PANCAKES, ModelMealData.QUINOA_BOWL, ModelMealData.STEAK));

        testMap.put("Monday", testList);
        maintainUser.adjustQuantities(testMap, "Monday");

        assertEquals(678.15, testMap.get("Monday").get(0).getCalories());
        assertEquals(678.15, testMap.get("Monday").get(1).getCalories());
        assertEquals(904.20, testMap.get("Monday").get(2).getCalories());

        double returnedCalories = testMap.get("Monday").get(0).getCalories()
                + testMap.get("Monday").get(1).getCalories() + testMap.get("Monday").get(2).getCalories();

        assertEquals(returnedCalories, maintainUser.getTargetCalories());
    }

    @Test
    void testGenerateDietPlan() {
        DietPlan testDietPlan = maintainUser.generateDietPlan();
        Set<Meal> breakfasts = new HashSet<>();
        Set<Meal> lunches = new HashSet<>();
        Set<Meal> dinners = new HashSet<>();
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                "Sunday");

        for (String day : daysOfWeek) {
            breakfasts.add(testDietPlan.getMeal(day, "Breakfast"));
            lunches.add(testDietPlan.getMeal(day, "Lunch"));
            dinners.add(testDietPlan.getMeal(day, "Dinner"));
        }

        Set<Meal> originalBreakfasts = new HashSet<>(ModelMealData.BREAKFAST_LIST);
        Set<Meal> originalLunches = new HashSet<>(ModelMealData.LUNCH_LIST);
        Set<Meal> originalDinners = new HashSet<>(ModelMealData.DINNER_LIST);

        assertEquals(originalBreakfasts, breakfasts);
        assertEquals(originalLunches, lunches);
        assertEquals(originalDinners, dinners);
    }
}

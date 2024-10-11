package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.TestData;

public class TestDietPlan {
    private DietPlan testPlan;
    private Map<String, List<Meal>> meals = new HashMap<>();

    @BeforeEach
    void runBefore() {
        // creating a sample HashMap of days and meals per day for testing purposes
        List<Meal> mon = new ArrayList<>(Arrays.asList(TestData.SCRAMBLED_EGGS_TOAST, TestData.CHICKEN_SALAD,
                TestData.GRILLED_FISH_VEGGIES));
        List<Meal> tue = new ArrayList<>(Arrays.asList(TestData.OATMEAL_BANANA, TestData.QUINOA_BOWL,
                TestData.SPAGHETTI_BOLOGNESE));
        List<Meal> wed = new ArrayList<>(Arrays.asList(TestData.AVOCADO_TOAST, TestData.TURKEY_SANDWICH,
                TestData.STEAK));
        List<Meal> thu = new ArrayList<>(Arrays.asList(TestData.SMOOTHIE, TestData.CHICKEN_RICE,
                TestData.CHICKEN_CURRY));
        List<Meal> fri = new ArrayList<>(Arrays.asList(TestData.PANCAKES, TestData.VEGGIE_SALAD,
                TestData.SHRIMP_STIRFRY));
        List<Meal> sat = new ArrayList<>(Arrays.asList(TestData.GREEK_YOGURT, TestData.PASTA_PRIMAVERA,
                TestData.BEEF_TACOS));
        List<Meal> sun = new ArrayList<>(Arrays.asList(TestData.SIMPLE_PBJ, TestData.SALMON_RICE,
                TestData.VEGGIE_STEW));
        meals.put("Monday", mon);
        meals.put("Tuesday", tue);
        meals.put("Wednesday", wed);
        meals.put("Thursday", thu);
        meals.put("Friday", fri);
        meals.put("Saturday", sat);
        meals.put("Sunday", sun);

        testPlan = new DietPlan(meals);
    }

    @Test
    void testConstructor() {
        assertEquals(meals, testPlan.getCompleteWeeklyPlan());
    }

    @Test
    void testAddSingleMeal() {
        testPlan.removeMeal("Monday", "Lunch");
        testPlan.addMeal("Monday", "Lunch", TestData.QUINOA_BOWL);
        assertEquals(TestData.QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
    }

    @Test
    void testAddMultipleMeal() {
        testPlan.removeMeal("Monday", "Dinner");
        testPlan.removeMeal("Tuesday", "Breakfast");
        testPlan.addMeal("Monday", "Dinner", TestData.BEEF_TACOS);
        testPlan.addMeal("Tuesday", "Breakfast", TestData.SIMPLE_PBJ);
        assertEquals(TestData.BEEF_TACOS, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(TestData.SIMPLE_PBJ, testPlan.getMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testReplaceSingleMeal() {
        assertEquals(TestData.CHICKEN_SALAD, testPlan.getMeal("Monday", "Lunch"));
        testPlan.addMeal("Monday", "Lunch", TestData.QUINOA_BOWL);
        assertEquals(TestData.QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
    }

    @Test
    void testReplaceMultipleMeal() {
        assertEquals(TestData.CHICKEN_SALAD, testPlan.getMeal("Monday", "Lunch"));
        assertEquals(TestData.GRILLED_FISH_VEGGIES, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(TestData.OATMEAL_BANANA, testPlan.getMeal("Tuesday", "Breakfast"));
        testPlan.addMeal("Monday", "Lunch", TestData.QUINOA_BOWL);
        testPlan.addMeal("Monday", "Dinner", TestData.STEAK);
        testPlan.addMeal("Tuesday", "Breakfast", TestData.SIMPLE_PBJ);
        assertEquals(TestData.QUINOA_BOWL, testPlan.getMeal("Monday", "Lunch"));
        assertEquals(TestData.STEAK, testPlan.getMeal("Monday", "Dinner"));
        assertEquals(TestData.SIMPLE_PBJ, testPlan.getMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testRemoveSingleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        assertNull(testPlan.getMeal("Thursday", "Breakfast"));
    }

    @Test
    void testRemoveMultipleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        testPlan.removeMeal("Thursday", "Lunch");
        testPlan.removeMeal("Thursday", "Dinner");
        assertNull(testPlan.getMeal("Thursday", "Breakfast"));
        assertNull(testPlan.getMeal("Thursday", "Lunch"));
        assertNull(testPlan.getMeal("Thursday", "Dinner"));
    }

    @Test
    void clearSingleDay() {
        testPlan.clearDay("Tuesday");
        assertNull(testPlan.getMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getMeal("Tuesday", "Dinner"));
    }

    @Test
    void clearMultipleDays() {
        testPlan.clearDay("Tuesday");
        testPlan.clearDay("Wednesday");
        testPlan.clearDay("Sunday");
        assertNull(testPlan.getMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getMeal("Tuesday", "Dinner"));
        assertNull(testPlan.getMeal("Wednesday", "Breakfast"));
        assertNull(testPlan.getMeal("Wednesday", "Lunch"));
        assertNull(testPlan.getMeal("Wednesday", "Dinner"));
        assertNull(testPlan.getMeal("Sunday", "Breakfast"));
        assertNull(testPlan.getMeal("Sunday", "Lunch"));
        assertNull(testPlan.getMeal("Sunday", "Dinner"));
    }

    @Test
    void testCalculatorMethods() {
        assertEquals(1400, testPlan.calculateDailyCalories("Saturday"));
        assertEquals(800, testPlan.calculateDailyQuantity("Saturday"));
        assertEquals(52, testPlan.calculateDailyProtein("Saturday"));
        assertEquals(55, testPlan.calculateDailyFat("Saturday"));
        assertEquals(160, testPlan.calculateDailyCarb("Saturday"));
        assertEquals(9420, testPlan.calculateWeeklyCalories());
        assertEquals(5800, testPlan.calculateWeeklyQuantity());
        assertEquals(444, testPlan.calculateWeeklyProtein());
        assertEquals(349, testPlan.calculateWeeklyFat());
        assertEquals(985, testPlan.calculateWeeklyCarb());
    }

    @Test
    void testCalculatorsWhenNull() {
        testPlan.clearDay("Saturday");
        assertEquals(0, testPlan.calculateDailyCalories("Saturday"));
        assertEquals(0, testPlan.calculateDailyQuantity("Saturday"));
        assertEquals(0, testPlan.calculateDailyProtein("Saturday"));
        assertEquals(0, testPlan.calculateDailyFat("Saturday"));
        assertEquals(0, testPlan.calculateDailyCarb("Saturday"));

        testPlan.clearDay("Monday");
        testPlan.clearDay("Tuesday");
        testPlan.clearDay("Wednesday");
        testPlan.clearDay("Thursday");
        testPlan.clearDay("Friday");
        testPlan.clearDay("Sunday");
        assertEquals(0, testPlan.calculateWeeklyCalories());
        assertEquals(0, testPlan.calculateWeeklyQuantity());
        assertEquals(0, testPlan.calculateWeeklyProtein());
        assertEquals(0, testPlan.calculateWeeklyFat());
        assertEquals(0, testPlan.calculateWeeklyCarb());
    }
}

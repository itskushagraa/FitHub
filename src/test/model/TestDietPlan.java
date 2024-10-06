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

import data.TemporaryData;

public class TestDietPlan {
    private DietPlan testPlan;
    private Map<String, List<Meal>> meals = new HashMap<>();

    @BeforeEach
    void runBefore() {
        List<Meal> mon = new ArrayList<>(Arrays.asList(TemporaryData.SCRAMBLED_EGGS_TOAST, TemporaryData.CHICKEN_SALAD,
                TemporaryData.GRILLED_FISH_VEGGIES));
        List<Meal> tue = new ArrayList<>(Arrays.asList(TemporaryData.OATMEAL_BANANA, TemporaryData.QUINOA_BOWL,
                TemporaryData.SPAGHETTI_BOLOGNESE));
        List<Meal> wed = new ArrayList<>(Arrays.asList(TemporaryData.AVOCADO_TOAST, TemporaryData.TURKEY_SANDWICH,
                TemporaryData.STEAK));
        List<Meal> thu = new ArrayList<>(Arrays.asList(TemporaryData.SMOOTHIE, TemporaryData.CHICKEN_RICE,
                TemporaryData.CHICKEN_CURRY));
        List<Meal> fri = new ArrayList<>(Arrays.asList(TemporaryData.PANCAKES, TemporaryData.VEGGIE_SALAD,
                TemporaryData.SHRIMP_STIRFRY));
        List<Meal> sat = new ArrayList<>(Arrays.asList(TemporaryData.GREEK_YOGURT, TemporaryData.PASTA_PRIMAVERA,
                TemporaryData.BEEF_TACOS));
        List<Meal> sun = new ArrayList<>(Arrays.asList(TemporaryData.SIMPLE_PBJ, TemporaryData.SALMON_RICE,
                TemporaryData.VEGGIE_STEW));
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
        testPlan.addMeal("Monday", "Lunch", TemporaryData.AVOCADO_TOAST);
        assertEquals(TemporaryData.AVOCADO_TOAST, testPlan.getSpecificMeal("Monday", "Lunch"));
    }

    @Test
    void testAddMultipleMeal() {
        testPlan.removeMeal("Monday", "Lunch");
        testPlan.removeMeal("Monday", "Dinner");
        testPlan.removeMeal("Tuesday", "Breakfast");
        testPlan.addMeal("Monday", "Lunch", TemporaryData.AVOCADO_TOAST);
        testPlan.addMeal("Monday", "Dinner", TemporaryData.STEAK);
        testPlan.addMeal("Tuesday", "Breakfast", TemporaryData.SIMPLE_PBJ);
        assertEquals(TemporaryData.AVOCADO_TOAST, testPlan.getSpecificMeal("Monday", "Lunch"));
        assertEquals(TemporaryData.STEAK, testPlan.getSpecificMeal("Monday", "Dinner"));
        assertEquals(TemporaryData.SIMPLE_PBJ, testPlan.getSpecificMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testReplaceSingleMeal() {
        assertEquals(TemporaryData.CHICKEN_SALAD, testPlan.getSpecificMeal("Monday", "Lunch"));
        testPlan.addMeal("Monday", "Lunch", TemporaryData.AVOCADO_TOAST);
        assertEquals(TemporaryData.AVOCADO_TOAST, testPlan.getSpecificMeal("Monday", "Lunch"));
    }

    @Test
    void testReplaceMultipleMeal() {
        assertEquals(TemporaryData.CHICKEN_SALAD, testPlan.getSpecificMeal("Monday", "Lunch"));
        assertEquals(TemporaryData.GRILLED_FISH_VEGGIES, testPlan.getSpecificMeal("Monday", "Dinner"));
        assertEquals(TemporaryData.QUINOA_BOWL, testPlan.getSpecificMeal("Tuesday", "Breakfast"));
        testPlan.addMeal("Monday", "Lunch", TemporaryData.AVOCADO_TOAST);
        testPlan.addMeal("Monday", "Dinner", TemporaryData.STEAK);
        testPlan.addMeal("Tuesday", "Breakfast", TemporaryData.SIMPLE_PBJ);
        assertEquals(TemporaryData.AVOCADO_TOAST, testPlan.getSpecificMeal("Monday", "Lunch"));
        assertEquals(TemporaryData.STEAK, testPlan.getSpecificMeal("Monday", "Dinner"));
        assertEquals(TemporaryData.SIMPLE_PBJ, testPlan.getSpecificMeal("Tuesday", "Breakfast"));
    }

    @Test
    void testRemoveSingleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        assertNull(testPlan.getSpecificMeal("Thursday", "Breakfast"));
    }

    @Test
    void testRemoveMultipleMeal() {
        testPlan.removeMeal("Thursday", "Breakfast");
        testPlan.removeMeal("Thursday", "Lunch");
        testPlan.removeMeal("Thursday", "Dinner");
        assertNull(testPlan.getSpecificMeal("Thursday", "Breakfast"));
        assertNull(testPlan.getSpecificMeal("Thursday", "Lunch"));
        assertNull(testPlan.getSpecificMeal("Thursday", "Dinner"));
    }

    @Test
    void clearSingleDay() {
        testPlan.clearDay("Tuesday");
        assertNull(testPlan.getSpecificMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getSpecificMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getSpecificMeal("Tuesday", "Dinner"));
    }

    @Test
    void clearMultipleDays() {
        testPlan.clearDay("Tuesday");
        testPlan.clearDay("Wednesday");
        testPlan.clearDay("Sunday");
        assertNull(testPlan.getSpecificMeal("Tuesday", "Breakfast"));
        assertNull(testPlan.getSpecificMeal("Tuesday", "Lunch"));
        assertNull(testPlan.getSpecificMeal("Tuesday", "Dinner"));
        assertNull(testPlan.getSpecificMeal("Wednesday", "Breakfast"));
        assertNull(testPlan.getSpecificMeal("Wednesday", "Lunch"));
        assertNull(testPlan.getSpecificMeal("Wednesday", "Dinner"));
        assertNull(testPlan.getSpecificMeal("Sunday", "Breakfast"));
        assertNull(testPlan.getSpecificMeal("Sunday", "Lunch"));
        assertNull(testPlan.getSpecificMeal("Sunday", "Dinner"));
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

package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMeal {
    private Meal testMeal;

    @BeforeEach
    void runBefore() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Spaghetti");
        ingredients.add("Ground Beef");
        ingredients.add("Tomato Sauce");
        ingredients.add("Parmesan Cheese");
        testMeal = new Meal(
                "Spaghetti Bolognese",
                "Dinner",
                ingredients,
                700,
                350.0,
                25.0,
                30.0,
                85.0);
    }

    @Test
    void testConstructor() {
        assertEquals("Spaghetti Bolognese", testMeal.getName());
        assertEquals("Dinner", testMeal.getType());
        List<String> testIngredients = Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese");
        assertEquals(testIngredients, testMeal.getIngredients());
        assertEquals(700, testMeal.getCalories());
        assertEquals(350.0, testMeal.getQuantity());
        assertEquals(25.0, testMeal.getProtein());
        assertEquals(30.0, testMeal.getFat());
        assertEquals(85.0, testMeal.getCarb());
    }

    @Test
    void testAddSingleIngredient() {
        assertEquals(4, testMeal.getIngredients().size());
        testMeal.addIngredient("Salt");
        assertEquals("Salt", testMeal.getIngredients().get(4));
        assertEquals(5, testMeal.getIngredients().size());
    }

    @Test
    void testAddMultipleIngredient() {
        assertEquals(4, testMeal.getIngredients().size());
        testMeal.addIngredient("Salt");
        testMeal.addIngredient("Pepper");
        testMeal.addIngredient("Thyme");
        assertEquals("Salt", testMeal.getIngredients().get(4));
        assertEquals("Pepper", testMeal.getIngredients().get(5));
        assertEquals("Thyme", testMeal.getIngredients().get(6));
        assertEquals(7, testMeal.getIngredients().size());
    }

    @Test
    void testRemoveSingleIngredient() {
        assertEquals(4, testMeal.getIngredients().size());
        testMeal.removeIngredient("Spaghetti");
        assertEquals(3, testMeal.getIngredients().size());
        assertEquals("Ground Beef", testMeal.getIngredients().get(0));
    }

    @Test
    void testRemoveMultipleIngredient() {
        assertEquals(4, testMeal.getIngredients().size());
        testMeal.removeIngredient("Spaghetti");
        testMeal.removeIngredient("Ground Beef");
        testMeal.removeIngredient("Tomato Sauce");
        testMeal.removeIngredient("Parmesan Cheese");
        assertEquals(0, testMeal.getIngredients().size());
    }

    @Test
    void testCalculateMethods() {
        assertEquals(2, testMeal.calculateCaloriesPerGram());
        assertEquals(0.07142857142857142, testMeal.calculateProteinPerGram());
        assertEquals(0.08571428571428572, testMeal.calculateFatPerGram());
        assertEquals(0.24285714285714285, testMeal.calculateCarbPerGram());
    }

    @Test
    void testSetQuantity() {
        testMeal.setQuantity(700.0);
        assertEquals(700.0, testMeal.getQuantity());
        assertEquals(1400, testMeal.getCalories());
        assertEquals(50.0, testMeal.getProtein());
        assertEquals(60.0, testMeal.getFat());
        assertEquals(170.0, testMeal.getCarb());
    }

    @Test
    void testSetterMethods() {
        testMeal.setName("Omelette");
        assertEquals("Omelette", testMeal.getName());
        testMeal.setType("Breakfast");
        assertEquals("Breakfast", testMeal.getType());
    }
}

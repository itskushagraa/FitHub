package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
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
                700.0,
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
        assertEquals(700.0, testMeal.getCalories());
        assertEquals(350.0, testMeal.getQuantity());
        assertEquals(25.0, testMeal.getProtein());
        assertEquals(30.0, testMeal.getFat());
        assertEquals(85.0, testMeal.getCarb());
        testMeal.mealInfoViewed();
    }

    @Test
    void testToJson() {
        JSONObject json = testMeal.toJson();
        assertEquals("Spaghetti Bolognese", json.getString("name"));
        assertEquals("Dinner", json.getString("type"));
        assertEquals("Spaghetti", json.getJSONArray("ingredients").getString(0));
        assertEquals("Ground Beef", json.getJSONArray("ingredients").getString(1));
        assertEquals("Tomato Sauce", json.getJSONArray("ingredients").getString(2));
        assertEquals("Parmesan Cheese", json.getJSONArray("ingredients").getString(3));
        assertEquals(700.0, json.getDouble("calories"));
        assertEquals(350.0, json.getDouble("quantity"));
        assertEquals(25.0, json.getDouble("protein"));
        assertEquals(30.0, json.getDouble("fat"));
        assertEquals(85.0, json.getDouble("carb"));
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
        testMeal.setCalories(10.0);
        assertEquals(10.0, testMeal.getCalories());
        testMeal.setProtein(10.0);
        assertEquals(10.0, testMeal.getProtein());
        testMeal.setFat(20.0);
        assertEquals(20.0, testMeal.getFat());
        testMeal.setCarb(30.0);
        assertEquals(30.0, testMeal.getCarb());
        testMeal.setIngredients(new ArrayList<>(Arrays.asList("A", "B", "C")));
        assertEquals(3, testMeal.getIngredients().size());
        assertEquals("A", testMeal.getIngredients().get(0));
        assertEquals("B", testMeal.getIngredients().get(1));
        assertEquals("C", testMeal.getIngredients().get(2));
    }

    @Test
    void testSetterMethodsSameValues() {
        testMeal.setName("Spaghetti Bolognese");
        assertEquals("Spaghetti Bolognese", testMeal.getName());
        testMeal.setCalories(700.0);
        assertEquals(700.0, testMeal.getCalories());
        testMeal.setQuantity(350.0);
        assertEquals(350.0, testMeal.getQuantity());
        testMeal.setProtein(25.0);
        assertEquals(25.0, testMeal.getProtein());
        testMeal.setFat(30.0);
        assertEquals(30.0, testMeal.getFat());
        testMeal.setCarb(85.0);
        assertEquals(85.0, testMeal.getCarb());
    }

    @Test
    void testSetIngredientsOnlyAdd() {
        List<String> newIngredients = Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese",
                "TESTA");
        testMeal.setIngredients(newIngredients);
        assertEquals(5, testMeal.getIngredients().size());
        assertEquals("TESTA", testMeal.getIngredients().get(4));
    }

    @Test
    void testSetIngredientsOnlyRemove() {
        List<String> newIngredients = Arrays.asList("Spaghetti", "Ground Beef");
        testMeal.setIngredients(newIngredients);
        assertEquals(2, testMeal.getIngredients().size());
        assertEquals("Ground Beef", testMeal.getIngredients().get(1));
    }

    @Test
    void testSetIngredientsNoChange() {
        List<String> newIngredients = Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese");
        testMeal.setIngredients(newIngredients);
        assertEquals(4, testMeal.getIngredients().size());
        assertEquals("Ground Beef", testMeal.getIngredients().get(1));
        testMeal.setIngredients(new ArrayList<>());
        testMeal.setIngredients(Arrays.asList(""));
    }

    @Test
    void testRemovedIngredients() {
        List<String> newIngredients = Arrays.asList("TESTA");
        List<String> removedIngredients = testMeal.getRemovedIngredients(newIngredients);
        assertEquals(4, removedIngredients.size());
        removedIngredients = testMeal
                .getRemovedIngredients(Arrays.asList("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese"));
        assertEquals(0, removedIngredients.size());
    }
}

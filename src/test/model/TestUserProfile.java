package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(2260, maintainUser.getTargetCalories());
    }

    @Test
    void testCalculateBMI() {
        assertEquals(22.0, maintainUser.getBmi());
        assertEquals(24.7, bulkUser.getBmi());
        assertEquals(21.5, cutUser.getBmi());
    }

    @Test
    void testCalculateTargetCalories() {
        assertEquals(2260, maintainUser.calculateTargetCalories());
        assertEquals(1654, cutUser.calculateTargetCalories());
        assertEquals(2785, bulkUser.calculateTargetCalories());
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
        maintainUser.setTargetCalories();

        assertEquals("Jesse", maintainUser.getName());
        assertEquals(185, maintainUser.getHeight());
        assertEquals(85, maintainUser.getWeight());
        assertEquals(24.8, maintainUser.getBmi());
        assertEquals(25, maintainUser.getAge());
        assertEquals(7, maintainUser.getIntensity());
        assertEquals("bulk", maintainUser.getGoal());
        assertEquals(3470, maintainUser.getTargetCalories());
    }
}

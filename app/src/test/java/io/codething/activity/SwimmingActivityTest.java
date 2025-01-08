package io.codething.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwimmingActivityTest {

    @Test
    void testCaloriesBurned() {
        SwimmingActivity activity = new SwimmingActivity();
        activity.setDistanceInMeters(1000);

        assertEquals(500, activity.getCaloriesBurned());
    }

    @Test
    void testCaloriesBurnedWithNullDistance() {
        SwimmingActivity activity = new SwimmingActivity();

        Exception exception = assertThrows(IllegalArgumentException.class, activity::getCaloriesBurned);
        assertEquals("Distance must be provided", exception.getMessage());
    }
}
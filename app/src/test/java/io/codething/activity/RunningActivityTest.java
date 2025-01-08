package io.codething.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunningActivityTest {

        @Test
        void testCaloriesBurned() {
            RunningActivity activity = new RunningActivity(60);
            activity.setDistanceInMeters(1000);

            assertEquals(60, activity.getCaloriesBurned());
        }

        @Test
        void testCaloriesBurnedWithNullDistance() {
            RunningActivity activity = new RunningActivity(60);

            Exception exception = assertThrows(IllegalArgumentException.class, activity::getCaloriesBurned);
            assertEquals("Distance must be provided", exception.getMessage());
        }

}
package io.codething.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalkingActivityTest {

        @Test
        void testCaloriesBurned() {
            WalkingActivity activity = new WalkingActivity(60);
            activity.setDistanceInMeters(1000)
                    .setElevationGain(100);

            assertEquals(660, activity.getCaloriesBurned());
        }

        @Test
        void testCaloriesBurnedWithNullDistance() {
            WalkingActivity activity = new WalkingActivity(60);

            Exception exception = assertThrows(IllegalArgumentException.class, activity::getCaloriesBurned);
            assertEquals("Distance and elevation gain must be provided", exception.getMessage());
        }

}
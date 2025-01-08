package io.codething.activity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractActivityTest {

    @Test
    void testGetDuration() {
        AbstractActivity activity = new SimpleActivity() {
        };
        activity.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0))
                .setEndDate(LocalDateTime.of(2025, 1, 1, 0, 10));

        assertEquals(600, activity.getDurationInSeconds());
    }


    private static class SimpleActivity extends AbstractActivity {
        @Override
        public Integer getCaloriesBurned() {
            return 0;
        }
    }
}
package io.codething.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Activities
 */
public interface Activity {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String getName();

    Integer getCaloriesBurned();

    Integer getAverageHeartRate();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    default long getDurationInSeconds() {
        if(getStartDate() == null || getEndDate() == null) {
            throw new IllegalArgumentException("Start and end date must be provided");
        }
        return java.time.Duration.between(getStartDate(), getEndDate()).toSeconds();
    }

    default void listData() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Activity Name: " + getName());
        System.out.println("----------------------------------------------------------");

        System.out.println("    Start Date: " + getStartDate().format(DATE_TIME_FORMATTER));
        System.out.println("    End Date: " + getEndDate().format(DATE_TIME_FORMATTER));
        System.out.println("    Average Heart Rate: " + getAverageHeartRate());
        System.out.println("    Duration: " + getDurationInSeconds() + " seconds");
        System.out.println("    Calories Burned: " + getCaloriesBurned());
    }
}

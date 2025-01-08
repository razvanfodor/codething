package io.codething.user;

import io.codething.activity.Activity;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class User {
    private Sex sex;
    private String name;
    private Integer age;
    private Integer weightInKg;
    private Integer heightInCm;

    @Setter(value = AccessLevel.NONE)
    private Map<LocalDate, List<Activity>> dailyActivities = new HashMap<>();

    public User addActivity(Activity activity) {
        LocalDate day = activity.getStartDate().toLocalDate();
        dailyActivities.computeIfAbsent(day, k -> new ArrayList<>()).add(activity);
        return this;
    }

    /**
     * Calculates the Basal Metabolic Rate (BMR) using the Mifflin-St Jeor Equation.
     *
     * @return the BMR as an integer representing the number of calories burned at rest per day
     * @throws IllegalArgumentException if height, weight, age, or sex is not provided
     */
    public Integer calculateBmr() {
        if (heightInCm == null || weightInKg == null || age == null || sex == null) {
            throw new IllegalArgumentException("Height, weight, age, and sex must be provided");
        }

        // Mifflin-St Jeor Equation
        double bmr;
        if (sex == Sex.MALE) {
            bmr = (10 * weightInKg) + (6.25 * heightInCm) - (5 * age) + 5;
        } else if (sex == Sex.FEMALE) {
            bmr = (10 * weightInKg) + (6.25 * heightInCm) - (5 * age) - 161;
        } else {
            throw new IllegalArgumentException("Invalid sex value");
        }

        // Return the BMR rounded to the nearest integer
        return (int) Math.round(bmr);
    }

    public int calculateTotalActiveCalories() {
        return dailyActivities.values().stream()
                .flatMap(List::stream)
                .map(Activity::getCaloriesBurned)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long calculateTotalActivityDurationInSeconds() {
        return dailyActivities.values().stream()
                .flatMap(List::stream)
                .map(Activity::getDurationInSeconds)
                .mapToLong(Long::longValue)
                .sum();
    }

    public long getActivitiesDurationInSeconds(LocalDate day) {
        return dailyActivities.get(day).stream()
                .map(Activity::getDurationInSeconds)
                .mapToLong(Long::longValue)
                .sum();
    }

    public long getTotalActiveCalories(LocalDate day) {
        return dailyActivities.get(day).stream()
                .map(Activity::getCaloriesBurned)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long getTotalCalories(LocalDate day) {
        return getTotalActiveCalories(day) + calculateBmr();
    }

    public int getTotalActivities() {
        return dailyActivities.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    public void listData() {
        System.out.println("\n\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("User Data: " + name);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Sex: " + sex);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weightInKg + " kg");
        System.out.println("Height: " + heightInCm + " cm");
        System.out.println("BMR: " + calculateBmr());
        System.out.println("Total Active Calories: " + calculateTotalActiveCalories());
        System.out.println("Total Duration: " + calculateTotalActivityDurationInSeconds() + " seconds");
        System.out.println("Daily Activities:");
        dailyActivities.forEach((date, activities) -> {
            System.out.println("  Date: " + date);
            System.out.println("  Calories burned in day: " + getTotalCalories(date));
            System.out.println("  Duration in day: " + getActivitiesDurationInSeconds(date) + " seconds");
            activities.forEach(Activity::listData);
        });
    }
}

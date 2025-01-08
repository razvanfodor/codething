/*
 * Tracker app for tracking activities and reporting calories
 */
package io.codething;

import io.codething.activity.Activity;
import io.codething.activity.RunningActivity;
import io.codething.activity.SwimmingActivity;
import io.codething.activity.WalkingActivity;
import io.codething.common.Constants;
import io.codething.user.Sex;
import io.codething.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrackerApp {
    private final List<User> userList = new ArrayList<>();

    public TrackerApp() {
        // Create users
        User user1 = new User().setName("John").setSex(Sex.MALE).setAge(30).setWeightInKg(70).setHeightInCm(175);
        User user2 = new User().setName("Merien").setSex(Sex.FEMALE).setAge(28).setWeightInKg(60).setHeightInCm(165);

        // Create activities
        Activity swim = new SwimmingActivity()
                .setDistanceInMeters(1000)
                .setAveragePace(2)
                .setTotalStrokes(500L)
                .setAverageStrokeRate(30)
                .setStartDate(LocalDateTime.now().minusHours(1))
                .setEndDate(LocalDateTime.now())
                .setAverageHeartRate(120);        // Create activities
        Activity run = new RunningActivity(user1.getWeightInKg())
                .setDistanceInMeters(1000)
                .setAveragePace(2)
                .setSteps(2000)
                .setElevationGain(10)
                .setAveragePower(200)
                .setAverageCadence(190)
                .setStartDate(LocalDateTime.now().minusHours(3))
                .setEndDate(LocalDateTime.now().minusHours(2))
                .setAverageHeartRate(120);

        Activity walk = new WalkingActivity(user2.getWeightInKg())
                .setDistanceInMeters(5000)
                .setAveragePace(5)
                .setSteps(7000)
                .setElevationGain(50)
                .setAveragePower(100)
                .setAverageCadence(80)
                .setAverageHeartRate(100)
                .setStartDate(LocalDateTime.now().minusHours(2))
                .setEndDate(LocalDateTime.now().minusHours(1));

        // Add activities to users
        user1.addActivity(swim).addActivity(run);
        user2.addActivity(walk);

        userList.addAll(List.of(user1, user2));
    }

    public TrackerApp addUser(User user) {
        userList.add(user);
        return this;
    }

    public int totalActivities() {
        return userList.stream().mapToInt(User::getTotalActivities).sum();
    }   
    
    public int totalActiveCalories() {
        return userList.stream().mapToInt(User::calculateTotalActiveCalories).sum();
    }

    public long totalDurationInSeconds() {
        return userList.stream().mapToLong(User::calculateTotalActivityDurationInSeconds).sum();
    }

    public void listData(){
        System.out.println(Constants.LOGO);
        System.out.println("Total users: " + userList.size());
        System.out.println("Total Activities: " + totalActivities());
        System.out.println("Total Active Calories: " + totalActiveCalories());
        System.out.println("Total Duration: " + totalDurationInSeconds() + " seconds");

        userList.forEach(User::listData);
    }

    public void clearData() {
        userList.clear();
    }
}

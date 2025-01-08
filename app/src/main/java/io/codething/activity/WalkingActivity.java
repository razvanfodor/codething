package io.codething.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WalkingActivity extends AbstractActivity {
    private final int userWeightInKg;
    private Integer averagePace;
    private Integer distanceInMeters;
    private Integer steps;
    private Integer elevationGain;
    private Integer averagePower;
    private Integer averageCadence;

    public WalkingActivity(int userWeightInKg) {
        this.userWeightInKg = userWeightInKg;
        setName("Walking");
    }

    @Override
    public Integer getCaloriesBurned() {
        if (distanceInMeters == null || elevationGain == null) {
            throw new IllegalArgumentException("Distance and elevation gain must be provided");
        }

        // Estimate calories burned per kilometer (1 calorie per kg per km)
        double caloriesPerKm = 1.0 * userWeightInKg;

        // Estimate additional calories burned per meter of elevation gain (0.1 calorie per kg per meter)
        double caloriesPerMeterElevation = 0.1 * userWeightInKg;

        // Calculate total calories burned
        double totalCalories = (caloriesPerKm * distanceInMeters / 1000) + (caloriesPerMeterElevation * elevationGain);

        return Math.toIntExact(Math.round(totalCalories));
    }

    @Override
    public void listData() {
        super.listData();
        System.out.println("    Average Pace: " + averagePace);
        System.out.println("    Distance in Meters: " + distanceInMeters);
        System.out.println("    Steps: " + steps);
        System.out.println("    Elevation Gain: " + elevationGain);
        System.out.println("    Average Power: " + averagePower);
        System.out.println("    Average Cadence: " + averageCadence);
    }
}

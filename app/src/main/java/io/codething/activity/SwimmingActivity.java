package io.codething.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SwimmingActivity extends AbstractActivity {
    private Integer averagePace;
    private Long totalStrokes;
    private Integer averageStrokeRate;
    private Integer distanceInMeters;

    public SwimmingActivity() {
        setName("Swimming");
    }

    @Override
    public Integer getCaloriesBurned() {
        if (distanceInMeters == null) {
            throw new IllegalArgumentException("Distance must be provided");
        }

        // Calculate total calories burned
        return Math.toIntExact(Math.round(0.5 * distanceInMeters));
    }

    @Override
    public void listData() {
        super.listData();
        System.out.println("    Average Pace: " + averagePace);
        System.out.println("    Total Strokes: " + totalStrokes);
        System.out.println("    Average Stroke Rate: " + averageStrokeRate);
        System.out.println("    Distance in Meters: " + distanceInMeters);
    }

}

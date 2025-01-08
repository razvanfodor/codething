package io.codething.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public abstract class AbstractActivity implements Activity {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer averageHeartRate;
}

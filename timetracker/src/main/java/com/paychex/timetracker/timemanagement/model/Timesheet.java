package com.paychex.timetracker.timemanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Timesheet {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isActive;

}

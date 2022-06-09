package com.paychex.timetracker.timemanagement.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Timesheet {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isActive;

}

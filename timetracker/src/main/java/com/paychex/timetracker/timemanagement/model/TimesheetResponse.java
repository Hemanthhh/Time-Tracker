package com.paychex.timetracker.timemanagement.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimesheetResponse {
    private String status;
}

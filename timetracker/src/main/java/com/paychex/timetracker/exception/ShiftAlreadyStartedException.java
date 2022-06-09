package com.paychex.timetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShiftAlreadyStartedException extends Exception {
    String message;
}

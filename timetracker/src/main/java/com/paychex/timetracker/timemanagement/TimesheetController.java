package com.paychex.timetracker.timemanagement;

import com.paychex.timetracker.exception.NoActiveShiftException;
import com.paychex.timetracker.exception.ShiftAlreadyStartedException;
import com.paychex.timetracker.exception.UserNotFoundException;
import com.paychex.timetracker.timemanagement.model.Timesheet;
import com.paychex.timetracker.timemanagement.model.TimesheetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/api/v1/timesheet")
@CrossOrigin(origins = "http://localhost:4200")
public class TimesheetController {

    @Autowired
    TimesheetServices timesheetServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "shifts/start")
    public TimesheetResponse startShift(@RequestHeader("user-name") String userName) throws UserNotFoundException, ShiftAlreadyStartedException {
        TimesheetResponse result = timesheetServices.startShift(userName);
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "shifts/end")
    public TimesheetResponse endShift(@RequestHeader("user-name") String userName) throws UserNotFoundException, NoActiveShiftException {
        TimesheetResponse result = timesheetServices.endShift(userName);
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "shifts/active")
    public Timesheet  getLastActiveShift(@RequestHeader("user-name") String userName) throws UserNotFoundException, NoActiveShiftException {
        Timesheet result = timesheetServices.getLastActiveShift(userName);
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "shifts")
    public List<Timesheet>  getAllShiftRecordsByUserName(@RequestHeader("user-name") String userName) throws UserNotFoundException {
        List<Timesheet> result = timesheetServices.getAllShiftRecordsByUserName(userName);
        return result;
    }

    @ExceptionHandler(ShiftAlreadyStartedException.class)
    public final ResponseEntity<Object> handleConflictsException(ShiftAlreadyStartedException e) {
        log.error("Exception at: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NoActiveShiftException.class, UserNotFoundException.class})
    public final ResponseEntity<Object> handleNotFoundException(Exception e) {
        log.error("Exception at: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleGenericException(Exception e) {
        log.error("Exception at: ", e);
        return new ResponseEntity<>("Server error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

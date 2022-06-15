package com.paychex.timetracker.timemanagement;

import com.paychex.timetracker.exception.NoActiveShiftException;
import com.paychex.timetracker.exception.ShiftAlreadyStartedException;
import com.paychex.timetracker.exception.UserNotFoundException;
import com.paychex.timetracker.timemanagement.model.Timesheet;
import com.paychex.timetracker.timemanagement.model.TimesheetEntity;
import com.paychex.timetracker.timemanagement.model.TimesheetResponse;
import com.paychex.timetracker.usermanagement.UserRepository;
import com.paychex.timetracker.usermanagement.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TimesheetServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    public TimesheetResponse startShift(String userName) throws ShiftAlreadyStartedException, UserNotFoundException {

        Optional<UserEntity> userEntityOptional = getUserEntity(userName);
        UserEntity userEntity = userEntityOptional.get();

        Optional<TimesheetEntity> timesheetEntityOptional = getActiveShiftByUser(userEntity);
        if(!timesheetEntityOptional.isEmpty()) {
            throw new ShiftAlreadyStartedException("User already in a active shift!");
        }

        TimesheetEntity timesheetEntity = TimesheetEntity.builder().userEntity(userEntity).startTime(LocalDateTime.now())
                .isActive(true)
                .build();
        saveTimesheet(timesheetEntity);

        return TimesheetResponse.builder().status("successfully checked-in").build();
    }

    public TimesheetResponse endShift(String userName) throws UserNotFoundException, NoActiveShiftException {
        Optional<UserEntity> userEntityOptional = getUserEntity(userName);
        UserEntity userEntity = userEntityOptional.get();

        Optional<TimesheetEntity> timesheetEntityOptional = getActiveShiftByUser(userEntity);
        if(timesheetEntityOptional.isEmpty()) {
            throw new NoActiveShiftException("No active shift for the User!");
        }

        TimesheetEntity timesheetEntity = timesheetEntityOptional.get();
        timesheetEntity.setIsActive(false);
        timesheetEntity.setEndTime(LocalDateTime.now());

        saveTimesheet(timesheetEntity);

        return TimesheetResponse.builder().status("successfully checked-out").build();
    }

    private void saveTimesheet(TimesheetEntity timesheetEntity) {
        timesheetRepository.save(timesheetEntity);
    }


    public List<Timesheet> getAllShiftRecordsByUserName(String userName) throws UserNotFoundException {
        List<Timesheet> timesheet = new ArrayList<>();
        Optional<UserEntity> userEntityOptional = getUserEntity(userName);
        UserEntity userEntity = userEntityOptional.get();

        Set<TimesheetEntity> timeSheetEntitySet = userEntity.getTimeSheetEntitySet();

        if(timeSheetEntitySet != null ) {
            timesheet =  timeSheetEntitySet.stream().map(this::timeSheetConverter).collect(Collectors.toList());
        }
        return timesheet;
    }

    public Timesheet getLastActiveShift(String userName) throws UserNotFoundException, NoActiveShiftException {
        Optional<UserEntity> userEntityOptional = getUserEntity(userName);
        UserEntity userEntity = userEntityOptional.get();

        Optional<TimesheetEntity> timesheetEntityOptional = getActiveShiftByUser(userEntity);
        if(timesheetEntityOptional.isEmpty()) {
            throw new NoActiveShiftException("No active shift for the User!");
        }

        return timeSheetConverter(timesheetEntityOptional.get());
    }

    private Optional<TimesheetEntity> getActiveShiftByUser(UserEntity userEntity) {
        if(userEntity.getTimeSheetEntitySet() == null || userEntity.getTimeSheetEntitySet().isEmpty()) {
            return Optional.empty();
        }
        return userEntity.getTimeSheetEntitySet().stream().filter(user -> user.getIsActive()).findFirst();
    }

    private Timesheet timeSheetConverter(TimesheetEntity timesheetEntity) {
        Timesheet timesheet = new Timesheet();
        BeanUtils.copyProperties(timesheetEntity, timesheet);
        return timesheet;
    }

    private Optional<UserEntity> getUserEntity(String userName) throws UserNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserName(userName);
        if (userEntityOptional.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return userEntityOptional;
    }


}

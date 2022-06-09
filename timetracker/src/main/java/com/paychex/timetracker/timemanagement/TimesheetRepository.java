package com.paychex.timetracker.timemanagement;

import com.paychex.timetracker.timemanagement.model.TimesheetEntity;
import com.paychex.timetracker.usermanagement.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimesheetRepository extends CrudRepository<TimesheetEntity, Long> {

    Optional<TimesheetEntity> findByUserEntityAndIsActive(UserEntity userEntity,Boolean isActive);
}
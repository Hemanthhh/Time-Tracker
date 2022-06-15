package com.paychex.timetracker.timemanagement;

import com.paychex.timetracker.timemanagement.model.TimesheetEntity;
import com.paychex.timetracker.usermanagement.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long> {

    Optional<TimesheetEntity> findByUserEntityAndIsActive(UserEntity userEntity,Boolean isActive);
}
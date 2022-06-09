package com.paychex.timetracker.timemanagement.model;

import com.paychex.timetracker.usermanagement.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIMESHEET")
public class TimesheetEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "START_TIME", nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", updatable = false)
    private LocalDateTime endTime;

    @Column(name = "IS_ACTIVE", updatable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="USER_ID", nullable = false)
    private UserEntity userEntity;

}

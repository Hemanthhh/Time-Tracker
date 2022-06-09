package com.paychex.timetracker.usermanagement.model;

import com.paychex.timetracker.timemanagement.model.TimesheetEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL", nullable=false)
    private String email;

    @Column(name = "FIRST_NAME", nullable=false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable=false)
    private String lastName;

    @Column(name = "USER_NAME")
    private String userName;

    @CreationTimestamp
    @Column(name = "CREATED_DATE",insertable = true,updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE",insertable = true,updatable = false)
    private Timestamp updatedDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TimesheetEntity> timeSheetEntitySet;

}

package com.paychex.timetracker.usermanagement;

import com.paychex.timetracker.usermanagement.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OnStartUp {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void addSampleUserInDB() {

        if(userRepository.findByUserName("john").isEmpty()) {
            UserEntity userEntity = UserEntity.builder()
                    .userName("john").firstName("john").lastName("j")
                    .email("john@paychex.com").createdBy("admin@paychex.com").build();
            userRepository.save(userEntity);
        }
    }
}

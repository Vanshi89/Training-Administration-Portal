package com.tap.mappers;

import com.tap.dto.UserCreationDto;
import com.tap.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreationDto userCreationDto) {
        User user = new User();
        user.setUsername(userCreationDto.getUsername());
        user.setEmail(userCreationDto.getEmail());
        user.setPassword(userCreationDto.getPassword());
        return user;
    }

}

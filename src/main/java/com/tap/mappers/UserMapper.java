package com.tap.mappers;

import com.tap.dto.UserCreationDto;
import com.tap.dto.UserDto;
import com.tap.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreationDto userCreationDto) {
        User user = new User();
        user.setUsername(userCreationDto.getUsername());
        user.setPassword(userCreationDto.getPassword());
        user.setEmail(userCreationDto.getEmail());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        if (user.getRole() != null) {
            dto.setRole(user.getRole().getRoleName().toString());
        }
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}

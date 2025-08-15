package com.tap.mappers;

import com.tap.dto.UserCreationDto;
import com.tap.dto.UserDto;
import com.tap.entities.Instructor;
import com.tap.entities.Student;
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
        dto.setCreatedAt(user.getCreatedAt());

        if (user instanceof Instructor) {
            dto.setRole("instructor");
        } else if (user instanceof Student) {
            dto.setRole("student");
        } else {
            dto.setRole("admin");
        }
        
        return dto;
    }
}

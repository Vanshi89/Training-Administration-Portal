package com.tap.services;

import com.tap.dto.UserCreationDto;
import com.tap.dto.UserDto;
import com.tap.entities.Role;
import com.tap.entities.User;
import com.tap.mappers.UserMapper;
import com.tap.repositories.RoleRepository;
import com.tap.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto createUser(UserCreationDto userCreationDto) {
        userRepository.findByUsername(userCreationDto.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("Username already exists");
        });
        userRepository.findByEmail(userCreationDto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });

        Role.RoleName roleName;
        try {
            roleName = Role.RoleName.valueOf(userCreationDto.getRole().toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role specified. Must be one of: " + Arrays.toString(Role.RoleName.values()));
        }

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        User user = userMapper.toUser(userCreationDto);
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}

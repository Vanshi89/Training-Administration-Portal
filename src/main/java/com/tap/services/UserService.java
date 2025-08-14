package com.tap.services;

import com.tap.dto.UserCreationDto;
import com.tap.entities.Role;
import com.tap.entities.User;
import com.tap.mappers.UserMapper;
import com.tap.repositories.RoleRepository;
import com.tap.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
//    private final RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(UserCreationDto userDto) {

        // first find the role

//        Role.RoleName roleNameEnum = Role.RoleName.valueOf(userDto.getRoleName().toLowerCase());
        //Role userRole = roleRepository.findByRoleName(userDto.getRoleName().toString());
        Role.RoleName roleNameEnum = Arrays.stream(Role.RoleName.values())
                .filter(r -> r.name().equalsIgnoreCase(String.valueOf(userDto.getRoleName())))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid role name: " + userDto.getRoleName()));

        Role userRole = roleRepository.findByRoleName(roleNameEnum);


        // map dto to user entity
        User user = userMapper.toEntity(userDto);

        // set role name from the database
        user.setRole(userRole);

        // encrypt the password
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save to database
        return userRepository.save(user);

    }

}

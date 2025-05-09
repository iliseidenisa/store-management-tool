package com.practice.store.service;

import com.practice.store.dto.CredentialsDto;
import com.practice.store.dto.UserDto;
import com.practice.store.dto.UserDtoCreate;
import com.practice.store.exception.ResourceNotFoundException;
import com.practice.store.exception.ValidationException;
import com.practice.store.mapper.UserMapper;
import com.practice.store.model.Role;
import com.practice.store.model.RoleEnum;
import com.practice.store.model.UserEntity;
import com.practice.store.repository.RoleRepository;
import com.practice.store.repository.UserRepository;
import com.practice.store.service.interfaces.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public UserDto registerUser(UserDtoCreate dto) throws ValidationException {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ValidationException("Error: Username is already taken!");
        }

        UserEntity user = UserEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .build();


        Set<String> strRoles = dto.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        switch (role) {
                            case "admin" -> {
                                Role adminRole = roleRepository
                                        .findByName(RoleEnum.ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);
                            }
                            case "manager" -> {
                                Role managerRole = roleRepository
                                        .findByName(RoleEnum.MANAGER)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(managerRole);
                            }
                            default -> {
                                Role userRole = roleRepository
                                        .findByName(RoleEnum.CUSTOMER)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                            }
                        }
                    });
        }

        user.setRoles(roles);
        return userMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDto authenticateUser(CredentialsDto credentialsDTO) {
        UserEntity user = userRepository.findByUsername(credentialsDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Authentication error"));

        if (encoder.matches(credentialsDTO.getPassword(), user.getPassword())) {
            return userMapper.convertToDto(user);
        }
        return null;
    }

}

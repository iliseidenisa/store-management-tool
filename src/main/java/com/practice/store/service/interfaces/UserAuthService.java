package com.practice.store.service.interfaces;

import com.practice.store.dto.CredentialsDto;
import com.practice.store.dto.UserDto;
import com.practice.store.dto.UserDtoCreate;
import com.practice.store.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthService {
    Object registerUser(UserDtoCreate dto) throws ValidationException;

    UserDto authenticateUser(CredentialsDto loginRequest);
}

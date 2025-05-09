package com.practice.store.controller;

import com.practice.store.dto.CredentialsDto;
import com.practice.store.dto.UserDtoCreate;
import com.practice.store.exception.ValidationException;
import com.practice.store.service.interfaces.UserAuthService;
import com.practice.store.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthService authenticationService;
    @PostMapping("/public/login")
    public ResponseEntity authentication(@RequestBody CredentialsDto loginRequest) {

        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }

    @PostMapping("/public/register")
    public ResponseEntity registerUser(@RequestBody UserDtoCreate dto) throws ValidationException {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, authenticationService.registerUser(dto)));
    }

}

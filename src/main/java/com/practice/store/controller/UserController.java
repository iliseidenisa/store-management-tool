package com.practice.store.controller;

import com.practice.store.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-management")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}

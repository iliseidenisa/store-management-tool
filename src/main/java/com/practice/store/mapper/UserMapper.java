package com.practice.store.mapper;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}

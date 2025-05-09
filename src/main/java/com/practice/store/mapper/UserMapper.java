package com.practice.store.mapper;

import com.practice.store.dto.UserDto;
import com.practice.store.model.UserEntity;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertToDto(UserEntity user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserEntity convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

}

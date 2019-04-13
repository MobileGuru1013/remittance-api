package com.voteva.remittance.api.v1.mapper;

import com.voteva.remittance.api.v1.dto.UserDto;
import com.voteva.remittance.entity.User;

public class UserMapper {

    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setBirthDate(user.getBirthDate());

        return userDto;
    }
}

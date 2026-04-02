package com.multigenesys.ecommerce.util;

import com.multigenesys.ecommerce.dto.UserDto;
import com.multigenesys.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDto toUserDto(User user){
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(String.valueOf(Role.USER))
                .build();
    }
}

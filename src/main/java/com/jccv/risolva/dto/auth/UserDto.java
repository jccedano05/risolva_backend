package com.jccv.risolva.dto.auth;


import com.jccv.risolva.model.auth.Role;
import com.jccv.risolva.model.auth.User;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String secretKey;
    private Role role;

    public UserDto convertUserToUserDto(User user) {

        UserDtoBuilder userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole());

        return userDto.build();
    }
}




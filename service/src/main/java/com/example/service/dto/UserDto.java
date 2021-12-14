package com.example.service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int roleId;
}

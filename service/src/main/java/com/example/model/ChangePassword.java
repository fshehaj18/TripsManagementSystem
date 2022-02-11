package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {

    private String oldPassword;
    private String password;
    private String confirmPassword;
}

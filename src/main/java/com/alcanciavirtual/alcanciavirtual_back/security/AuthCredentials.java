package com.alcanciavirtual.alcanciavirtual_back.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}

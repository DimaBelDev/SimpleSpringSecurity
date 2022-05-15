package net.defend.springsecurity.rest;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}

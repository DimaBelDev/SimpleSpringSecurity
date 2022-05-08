package net.defend.springsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
}

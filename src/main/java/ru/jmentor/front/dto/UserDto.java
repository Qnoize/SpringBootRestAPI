package ru.jmentor.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String token;
    private String userRole;
}

package com.legoray.MANAMovieStudios.entity;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String firstName;
    private String lastName;
    private String dob;
    private String password;

}

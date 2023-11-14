package com.legoray.MANAMovieStudios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class MovieWithUsernameDTO {

    private int id;
    private String movieName;
    private String moviePlot;
    private String movieDirector;
    private String movieActors;
    private int categoryId;
    private int userId;
    private String username;

}

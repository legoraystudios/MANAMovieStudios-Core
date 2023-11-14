package com.legoray.MANAMovieStudios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mana-reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "review_title", nullable = false)
    private String reviewTitle;
    @Column(name = "review_text", nullable = false)
    private String reviewText;
    @Column(name = "review_rating", nullable = false)
    private int reviewRating;
    @Column(name = "movie_id", nullable = false)
    private int movieId;

}

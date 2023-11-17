package com.legoray.MANAMovieStudios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "mana-movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "movie_name", nullable = false)
    private String movieName;
    @Column(name = "movie_plot", nullable = true)
    private String moviePlot;
    @Column(name = "movie_director", nullable = false)
    private String movieDirector;
    @Column(name = "movie_actors", nullable = false)
    private String movieActors;
    @Transient
    private int reviewsCount;
    @Transient
    private double overallRating;
    @Column(name = "category_id", nullable = false)
    private int categoryId;
    @Column(name = "user_id", nullable = false)
    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
    private Set<Reviews> manaReviews = new HashSet<>();

    public double getOverallRating() {
        double sum = 0.0;
        int count = 0;

        for (Reviews review : manaReviews) {
            sum += review.getReviewRating();
            count++;
        }

        return count > 0 ? sum / count : 0.0;
    }

    public int getReviewsCount() {
        int count = 0;

        for (Reviews review : manaReviews) {
            count++;
        }

        return count;
    }


}

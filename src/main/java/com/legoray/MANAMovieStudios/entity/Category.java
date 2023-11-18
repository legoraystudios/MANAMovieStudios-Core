package com.legoray.MANAMovieStudios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "mana-categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Movies> manaMovies = new HashSet<>();

}



package com.legoray.MANAMovieStudios.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "mana-accounts")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "dob", nullable = false)
	private String dob;
	@Column(name = "password", nullable = false,  length = 255)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Movies> manaMovies = new HashSet<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Reviews> manaReviews = new HashSet<>();

	public Set<Movies> getManaMovies() {
		return manaMovies;
	}

	public void setManaMovies(Set<Movies> manaMovies) {
		this.manaMovies = manaMovies;
	}

	public Set<Reviews> getManaReviews() {
		return manaReviews;
	}

	public void setManaReviews(Set<Reviews> manaReviews) {
		this.manaReviews = manaReviews;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		LocalDate formattedDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dob = formattedDate.toString();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

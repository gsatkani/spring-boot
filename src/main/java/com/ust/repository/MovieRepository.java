package com.ust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.enity.Movie;

// Programmer 1 -  Rahul  --> Pending
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}

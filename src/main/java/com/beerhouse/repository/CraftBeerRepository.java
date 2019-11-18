package com.beerhouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beerhouse.entity.Beers;

public interface CraftBeerRepository extends JpaRepository<Beers, Integer>{

	Optional<Beers> findById(String string);
	
}

package com.beerhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beers;
import com.beerhouse.exception.BeerAlreadyExistsException;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.repository.CraftBeerRepository;

@Service
public class CraftBeerService {
	
	private CraftBeerRepository craftBeerRepository;
	
	@Autowired
	private CraftBeerService(CraftBeerRepository craftBeerRepository) {
		this.craftBeerRepository = craftBeerRepository;
	}

	public List<Beers> findAll() {
		return craftBeerRepository.findAll();
	}
	
	public Beers post(Beers beer) {
		return craftBeerRepository.save(notExistsBeer(beer));
	}

	public Beers findById(String id){
		return existsBeer(id);
	}

	public void patch(String id, Beers newBeer) {
		Beers beer = existsBeer(id);
        beer.setName((newBeer.getName() == null) ? beer.getName() : newBeer.getName());
        beer.setIngredients((newBeer.getIngredients() == null) ? beer.getIngredients() : newBeer.getIngredients());
        beer.setAlcoholContent((newBeer.getAlcoholContent() == null) ? beer.getAlcoholContent() : newBeer.getAlcoholContent());
        beer.setPrice((newBeer.getPrice() == null) ? beer.getPrice() : newBeer.getPrice());
        beer.setCategory((newBeer.getCategory() == null) ? beer.getCategory() : newBeer.getCategory());
        craftBeerRepository.save(beer);
	}
	
	public void put(String id, Beers newBeer) {
		Beers beer = existsBeer(id);
		beer.setName(newBeer.getName());
        beer.setIngredients(newBeer.getIngredients());
        beer.setAlcoholContent(newBeer.getAlcoholContent());
        beer.setPrice(newBeer.getPrice());
        beer.setCategory(newBeer.getCategory());
		craftBeerRepository.save(beer);
	}
	
	public void delete(String id) {
		craftBeerRepository.delete(existsBeer(id));
	}
	
	private Beers notExistsBeer(Beers beer) {
		Optional<Beers> optionalBeer = craftBeerRepository.findById(beer.getId());
		if(!optionalBeer.isPresent()) {
			return beer;
		}else {
			throw new BeerAlreadyExistsException("Already exists a beer for id: " + beer.getId());
		}
	}
	
	private Beers existsBeer(String id) {
		Optional<Beers> optionalBeer = craftBeerRepository.findById(id);
		if(optionalBeer.isPresent()) {
			return optionalBeer.get();
		}else {
			throw new BeerNotFoundException("Beer not found for id: " + id);
		}
	}	
}

package com.beerhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beers;
import com.beerhouse.exception.BeerAlreadyExistsException;
import com.beerhouse.exception.BeerNotFoundExcetpion;
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
	
	public Beers save(Beers beer) {
		return craftBeerRepository.save(notExistsBeer(beer));
	}

	public Beers findById(Long id){
		return existsBeer(id);
	}

	public void put(Long id, Beers newBeer) {
		
		Beers beer = existsBeer(id);
        beer.setName((newBeer.getName() == null) ? beer.getName() : newBeer.getName());
        beer.setIngredients((newBeer.getIngredients() == null) ? beer.getIngredients() : newBeer.getIngredients());
        beer.setAlcoholContent((newBeer.getAlcoholContent() == null) ? beer.getAlcoholContent() : newBeer.getAlcoholContent());
        beer.setPrice((newBeer.getPrice() == null) ? beer.getPrice() : newBeer.getPrice());
        beer.setCategory((newBeer.getCategory() == null) ? beer.getCategory() : newBeer.getCategory());
        craftBeerRepository.save(beer);
	}
	
	public void patch(Long id, Beers beer) {
		// TODO Criar o servico de patch
		
	}
	
	public void delete(Long id) {
		craftBeerRepository.delete(existsBeer(id));
	}
	
	private Beers notExistsBeer(Beers beer) {
		Optional<Beers> optionalBeer = craftBeerRepository.findById(beer.getId());
		if(!optionalBeer.isPresent()) {
			return beer;
		}else {
			throw new BeerAlreadyExistsException();
		}
	}
	
	private Beers existsBeer(Long id) {
		Optional<Beers> optionalBeer = craftBeerRepository.findById(id);
		if(optionalBeer.isPresent()) {
			return optionalBeer.get();
		}else {
			throw new BeerNotFoundExcetpion();
		}
	}	
}

package com.beerhouse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.beerhouse.entity.Beers;
import com.beerhouse.service.CraftBeerService;

@RestController
@RequestMapping("/beers")
public class CraftBeerController {
	
	private CraftBeerService craftBeerService;
	
	@Autowired
	public CraftBeerController(CraftBeerService craftBeerService) {
		this.craftBeerService = craftBeerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Beers>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(craftBeerService.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Beers beer){
		craftBeerService.save(beer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(beer.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(craftBeerService.findById(id));
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Beers beer){
		craftBeerService.put(id, beer);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patch(@PathVariable("id") Long id, @RequestBody Beers beer){
		craftBeerService.patch(id, beer);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		craftBeerService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
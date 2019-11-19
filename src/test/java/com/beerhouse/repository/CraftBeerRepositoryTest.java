package com.beerhouse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.entity.Beers;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto = create"})
public class CraftBeerRepositoryTest {
	
	@Autowired
	private CraftBeerRepository craftBeerRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private Beers createABeer() {
		return craftBeerRepository.save(new Beers("Brahma", "Lupulo e cevada", "4.7%", 1.99, "PILSEN"));
	}
	
	@Test
	public void createShouldPersistDate() {
		Beers beer = createABeer();
		assertThat(beer.getName()).isEqualTo("Brahma");
		assertThat(beer.getIngredients()).isEqualTo("Lupulo e cevada");
		assertThat(beer.getAlcoholContent()).isEqualTo("4.7%");
		assertThat(beer.getPrice()).isEqualTo(1.99);
		assertThat(beer.getCategory()).isEqualTo("PILSEN");
	}
	
	@Test
	public void deleteShouldRemoveDate() {
		Beers beer = createABeer();
		this.craftBeerRepository.delete(beer);
		assertThat(craftBeerRepository.findById(beer.getId()).isPresent()).isFalse();
	}
	
	@Test
	public void updateShouldChangeAndPersistDate() {
		Beers beer = createABeer();
		beer.setName("Imperio");
		beer.setIngredients("Agua, cevada e malte");
		beer.setAlcoholContent("5%");
		beer.setPrice(2.09);
		beer.setCategory("PURO MALTE");
		Beers updatedBeer = craftBeerRepository.save(beer);
		assertThat(beer.getId()).isEqualTo(updatedBeer.getId());
		assertThat(beer.getName()).isEqualTo(updatedBeer.getName());
		assertThat(beer.getIngredients()).isEqualTo(updatedBeer.getIngredients());
		assertThat(beer.getAlcoholContent()).isEqualTo(updatedBeer.getAlcoholContent());
		assertThat(beer.getPrice()).isEqualTo(updatedBeer.getPrice());
		assertThat(beer.getCategory()).isEqualTo(updatedBeer.getCategory());
	}
}

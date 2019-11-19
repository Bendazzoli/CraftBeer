package com.beerhouse.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.beerhouse.entity.Beers;
import com.beerhouse.service.CraftBeerService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CraftBeerControllerTest {
	
	private static final String ID = "1";
	private static final String URI_COM_ID = "/craftbeer/v1/beers/1";
	private static final String URI = "/craftbeer/v1/beers";
	
	private static final String ANY_NAME = "AnyName";
	private static final String ANY_INGREDIENTS = "AnyIngredients";
	private static final String ANY_ALCOHOL_CONTENT = "AnyAlcoholContent";
	private static final Double ANY_PRICE = 1.99;
	private static final String ANY_CATEGORY = "AnyCategory";
	
	private static final String MAX_NAME_SIZE_50 = "ABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDE1";
	private static final String MAX_INGREDIENTS_SIZE_200 = "ABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDE1";
	private static final String MAX_ALCOHOL_CONTENT_SIZE_30 = "ABCDEABCDEABCDEABCDEABCDEABCDE1";
	private static final Double MIN_PRICE_NEGATIVE_VALUE = -1.00;
	private static final Double MAX_PRICE_POSITIVE_VALUE = 999999999.99;
	private static final String MAX_CATEGORY_SIZE_50 = "ABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDE1";
	
	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private CraftBeerService craftBeerService;
    
    private Beers createBeer() {
    	return new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, ANY_PRICE, ANY_CATEGORY);
    }
    
    // Success Tests
    
    @Test
    public void getAllSuccess() throws Exception {
    	List<Beers> beers = new ArrayList<>();
    	beers.add(createBeer());
    	
    	given(craftBeerService.findAll()).willReturn(beers);
        mockMvc.perform(MockMvcRequestBuilders.get(URI)
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(beers)));
    }
    
    @Test
    public void postSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createBeer())))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void getByIdSuccess() throws Exception {
    	Beers beer = createBeer();
    	
    	given(craftBeerService.findById(ID)).willReturn(beer);
        mockMvc.perform(MockMvcRequestBuilders.get(URI_COM_ID)
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(beer)));
    }
    
    @Test
    public void putSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(URI_COM_ID)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(new Gson().toJson(createBeer())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void patchSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(URI_COM_ID)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(new Gson().toJson(createBeer())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void deleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URI_COM_ID)
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    // Exceptions Tests: Required fields

    @Test
    public void postWithoutNameField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(null, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postWithoutIngredientsField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, null, ANY_ALCOHOL_CONTENT, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postWithoutAlcoholContentField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, null, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postWithoutPriceField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, null, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postWithoutCategoryField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, ANY_PRICE, null))))
                .andExpect(status().isBadRequest());
    }
    
    // Exceptions Tests: size fields
    
    @Test
    public void postMaxNameFieldValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(MAX_NAME_SIZE_50, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postMaxIngredientsFieldValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, MAX_INGREDIENTS_SIZE_200, ANY_ALCOHOL_CONTENT, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postMaxAlcoholContentFieldValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, MAX_ALCOHOL_CONTENT_SIZE_30, ANY_PRICE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postMinPriceFieldNegativeValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, MIN_PRICE_NEGATIVE_VALUE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postMaxPriceFieldValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, MAX_PRICE_POSITIVE_VALUE, ANY_CATEGORY))))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void postMaxCategoryFieldValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new Beers(ANY_NAME, ANY_INGREDIENTS, ANY_ALCOHOL_CONTENT, ANY_PRICE, MAX_CATEGORY_SIZE_50))))
                .andExpect(status().isBadRequest());
    }
}

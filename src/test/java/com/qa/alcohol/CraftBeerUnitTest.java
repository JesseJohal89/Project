package com.qa.alcohol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.alcohol.persistance.exceptions.CraftBeerNotFoundException;
import com.qa.alcohol.persistance.domain.CraftBeer;
import com.qa.alcohol.persistance.repository.CraftBeerRepo;
import com.qa.alcohol.service.CraftBeerService;

@SpringBootTest
public class CraftBeerUnitTest {

	@Autowired
	private CraftBeerService service;
	
	@MockBean
	private CraftBeerRepo repo;


	@Test
	void testCreate() {

	
	}

	@Test
	void testGetAll() {
		

		
	}


	@Test
	void testGet() {

		
	}

	

	@Test
	void testUpdate() throws CraftBeerNotFoundException {
		final long id = 1L;
		new CraftBeer().equals(new CraftBeer());
		CraftBeer testCraftBeer = new CraftBeer(1L, "Stoutzilla", "Imperial Stout", "Unbarred", 9, "Bottle", 750, true, false, 11);
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(new CraftBeer(1L, "Joosy", "Hazy Pale", "Unbarred", 5.5, "Can", 440, true, false, 4)));
		
		Mockito.when(this.repo.save(testCraftBeer)).thenReturn(testCraftBeer);
		
		assertEquals(testCraftBeer, this.service.updateCraftBeer(testCraftBeer, 1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(testCraftBeer);
	}


	@Test
	void testDelete() {
		final long id = 1L;
				
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.deleteCraftBeer(id));
		
	}

	
	
}

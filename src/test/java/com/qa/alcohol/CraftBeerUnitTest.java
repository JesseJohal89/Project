package com.qa.alcohol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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

		// GIVEN is our testing data - you can make this a final local variable if you
		// want, e.g.:
		final long id = 1L;
		final CraftBeer testCraftBeer = new CraftBeer(null, "Joosy", "Hazy Pale Ale", "Unbarred", 5.5, "can", 440, true,
				false, 4);
		final CraftBeer testSavedCraftBeer = new CraftBeer(id, "Joosy", "Hazy Pale Ale", "Unbarred", 5.5, "can", 440,
				true, false, 4);

		// WHEN
		Mockito.when(this.repo.save(testCraftBeer)).thenReturn(testSavedCraftBeer);

		// THEN
		Assertions.assertThat(this.service.createCraftBeer(testCraftBeer).equals(testSavedCraftBeer));

		// verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).save(testCraftBeer);

	}

	@Test
	void testGet() {
		final long id = 1L;
		CraftBeer testCraftBeer = new CraftBeer(id, "Joosy", "Hazy Pale Ale", "Unbarred", 5.5, "can", 440, true, false,
				4);
		testCraftBeer.setId(id);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testCraftBeer));
		assertEquals(testCraftBeer, this.service.getCraftBeer(id));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testUpdate() throws CraftBeerNotFoundException {
		final long id = 1L;
		CraftBeer testCraftBeer = new CraftBeer(id, "Joosy", "Hazy Pale", "Unbarred", 5.5, "Can", 440, true, false, 4);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional
				.of(new CraftBeer(id, "Stoutzilla", "Imperial Stout", "Unbarred", 9, "Bottle", 750, true, false, 11)));

		Mockito.when(this.repo.save(testCraftBeer)).thenReturn(testCraftBeer);

		assertEquals(testCraftBeer, this.service.updateCraftBeer(testCraftBeer, id));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(testCraftBeer);
	}

	@Test
	void testDelete() {
		final long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertTrue(this.service.deleteCraftBeer(id));

	}

}

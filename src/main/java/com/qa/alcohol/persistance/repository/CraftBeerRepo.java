package com.qa.alcohol.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.alcohol.persistance.domain.CraftBeer;

@Repository
public interface CraftBeerRepo extends JpaRepository<CraftBeer, Long> {

//CUSTOM QUERIES 

	// SELECT * from CraftBeer where name = '[name]';
	List<CraftBeer> findCraftBeerByName(String name);

	// SELECT * from CraftBeer where brewery = '[brewery]';
	List<CraftBeer> findCraftBeerByBrewery(String brewery);

	// SELECT * from CraftBeer where brewery = '[brewery]' and gultenFree = [true];
	List<CraftBeer> findCraftBeerByBreweryAndGlutenFree(String name, boolean gultenFree);

	// SELECT * from CraftBeer where name = '[name]' and Vegan = [true] LIMIT 1;
	CraftBeer findCraftBeerByBreweryAndVegan(String name, boolean Vegan);

	// SELECT * from CraftBeer where price > [price];
	List<CraftBeer> findCraftBeerByPriceGreaterThan(double price);

	// SELECT * from CraftBeer where price < [price];
	List<CraftBeer> findCraftBeerByPriceLessThan(double price);

}

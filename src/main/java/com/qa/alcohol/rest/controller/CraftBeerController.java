package com.qa.alcohol.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.alcohol.persistance.domain.CraftBeer;
import com.qa.alcohol.persistance.exceptions.CraftBeerNotFoundException;
import com.qa.alcohol.rest.dto.CraftBeerDTO;
import com.qa.alcohol.service.CraftBeerService;

@RestController
@RequestMapping("/craftbeer")
public class CraftBeerController {

	private CraftBeerService service;

	public CraftBeerController(CraftBeerService service) {
		super();
		this.service = service;
	}

// CRUD methods

	// CREATE
	@PostMapping("/create")
	public CraftBeerDTO createCraftBeer(@RequestBody CraftBeer craftBeer) {
		return this.service.createCraftBeer(craftBeer);
	}

	// READ
	@GetMapping("/getall")
	public List<CraftBeerDTO> getAllCraftBeer() {
		return this.service.getAllCraftBeer();
	}

	@GetMapping("/get/{id}")
	public CraftBeerDTO getCraftBeer(@PathVariable Long id) {
		return this.service.getCraftBeer(id);
	}

	// UPDATE
	@PutMapping("/update/{id}")
	public CraftBeerDTO updateCraftBeer(@RequestBody CraftBeer newCraftBeer, @PathVariable Long id)
			throws CraftBeerNotFoundException {
		return this.service.updateCraftBeer(newCraftBeer, id);
	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public void deleteCraftBeer(@PathVariable Long id) {
		this.service.deleteCraftBeer(id);
	}

	// TEST
	@GetMapping("/test")
	public String test() {
		return "Hello Drunken World! Want Some Beer :D";
	}

}

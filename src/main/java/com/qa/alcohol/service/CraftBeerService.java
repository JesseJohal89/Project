package com.qa.alcohol.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.alcohol.persistance.exceptions.CraftBeerNotFoundException;
import com.qa.alcohol.persistance.domain.CraftBeer;
import com.qa.alcohol.persistance.repository.CraftBeerRepo;
import java.util.Optional;
import org.modelmapper.ModelMapper;

@Service
public class CraftBeerService {

	@Autowired
	private CraftBeerRepo repo;

	//@Autowired
	//private ModelMapper mapper;

	public CraftBeerService(CraftBeerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		//this.mapper = mapper;
	}

	public CraftBeer createCraftBeer(CraftBeer craftBeer) {
		CraftBeer saved = this.repo.save(craftBeer);
		return saved;
	}

	public List<CraftBeer> getAllCraftBeer() {
		return this.repo.findAll();
	}

	public CraftBeer getCraftBeer(Long id) {
		CraftBeer updated = this.repo.findById(id).get();
		return updated;
	}

	public CraftBeer updateCraftBeer(CraftBeer newCraftBeer, Long id) throws CraftBeerNotFoundException {
		Optional<CraftBeer> existingOptional = this.repo.findById(id);
		CraftBeer existing = existingOptional.get();

		existing.setName(newCraftBeer.getName());
		existing.setBrewery(newCraftBeer.getBrewery());
		existing.setDescription(newCraftBeer.getDescription());
		existing.setAbv(newCraftBeer.getAbv());
		existing.setContainer(newCraftBeer.getContainer());
		existing.setSize(newCraftBeer.getSize());
		existing.setVegan(newCraftBeer.isVegan());
		existing.setGlutenFree(newCraftBeer.isGlutenFree());
		existing.setPrice(newCraftBeer.getPrice());

		CraftBeer updated = this.repo.save(existing);
		return updated;
	}

	public boolean deleteCraftBeer(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}

}

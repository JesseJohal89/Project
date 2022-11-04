package com.qa.alcohol.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.alcohol.persistance.exceptions.CraftBeerNotFoundException;
import com.qa.alcohol.persistance.domain.CraftBeer;
import com.qa.alcohol.persistance.repository.CraftBeerRepo;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import com.qa.alcohol.rest.dto.CraftBeerDTO;

@Service
public class CraftBeerService {

	@Autowired
	private CraftBeerRepo repo;

	@Autowired
	private ModelMapper mapper;

	public CraftBeerService(CraftBeerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	@SuppressWarnings("unused")
	private CraftBeerDTO mapToDTO(CraftBeer craftBeer) {
		return this.mapper.map(craftBeer, CraftBeerDTO.class);
	}

	public CraftBeerDTO createCraftBeer(CraftBeer craftBeer) {
		CraftBeer saved = this.repo.save(craftBeer);
		return this.mapToDTO(saved);
	}

	public List<CraftBeerDTO> getAllCraftBeer() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public CraftBeerDTO getCraftBeer(Long id) {
		CraftBeer updated = this.repo.findById(id).get();
		return this.mapToDTO(updated);
	}

	public CraftBeerDTO updateCraftBeer(CraftBeer newCraftBeer, Long id) throws CraftBeerNotFoundException {
		Optional<CraftBeer> existingOptional = this.repo.findById(id);
		CraftBeer existing = existingOptional.get();

		existing.setName(newCraftBeer.getName());
		existing.setBrewery(newCraftBeer.getBrewery());
		existing.setDescription(newCraftBeer.getDescription());
		existing.setAbv(newCraftBeer.getAbv());
		existing.setSize(newCraftBeer.getSize());
		existing.setVegan(newCraftBeer.isVegan());
		existing.setGlutenFree(newCraftBeer.isGlutenFree());
		existing.setPrice(newCraftBeer.getPrice());

		CraftBeer updated = this.repo.save(existing);
		return this.mapToDTO(updated);
	}

	public boolean deleteCraftBeer(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}

}

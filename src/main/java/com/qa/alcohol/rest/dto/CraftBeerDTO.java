package com.qa.alcohol.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CraftBeerDTO {

	private Long id;
	private String name;
	private String description;
	private String brewery;
	private double abv;
	private String container;
	private int size;
	private boolean vegan;
	private boolean glutenFree;
	private double price;

}

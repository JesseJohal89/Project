package com.qa.alcohol.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.qa.alcohol.persistance.enums.Container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CraftBeer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Description is mandatory (IPA / West Coast Pale / Hazy Pale etc.)")
	private String description;

	@NotBlank(message = "Brewery is mandatory")
	private String brewery;

	@Min(0)
	@Max(100)
	private double abv;

	//@Enumerated(EnumType.STRING)
	//private Container container;
	private String container;

	@Min(0)
	private int size;

	private boolean vegan;

	private boolean glutenFree;

	@Min(0)
	private double price;

}

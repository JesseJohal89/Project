package com.qa.alcohol.persistance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No delicious beer here :(")
public class CraftBeerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

}

package com.api.utils;

import java.io.File;
import java.io.IOException;

import com.api.models.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtilities {

	
	public static Pet getNewPet() {	
		
		Pet pet = null;
		try {
			File myPetJson = new File(System.getProperty("user.dir")+"/resources/pet.json");
			ObjectMapper mapper = new ObjectMapper();
			pet = mapper.readValue(myPetJson, Pet.class);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pet;
	}
}

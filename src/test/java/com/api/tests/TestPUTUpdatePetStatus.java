package com.api.tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.helpers.PetServiceHelpers;
import com.api.models.Pet;
import com.api.utils.CommonUtilities;

import io.restassured.response.Response;

public class TestPUTUpdatePetStatus {
	
	private PetServiceHelpers petServiceHelper = null;
	private Response response = null;
	private String status = "sold";
	private Pet modifiedPet = CommonUtilities.getNewPet();
	
	@BeforeClass
	public void init() {
		modifiedPet.setStatus(status);
		petServiceHelper = new PetServiceHelpers();
		response = petServiceHelper.updatePetStatus(modifiedPet);
	}
	
	@Test(priority = 1)
	public void validateStatusCodeToBE200() {
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test(priority = 2)
	public void validateResponseIDToEqualToRequestID() {
		Pet responsePetObject = response.getBody().as(Pet.class);
		Pet requestPetObject = CommonUtilities.getNewPet();
		assertEquals(responsePetObject.getId(), requestPetObject.getId());
	}
	
	@Test(priority=3)
	public void validateResponseStatusToBeSold() {
		Pet responsePetObject = response.getBody().as(Pet.class);
		assertEquals(responsePetObject.getStatus(), status);
	}

}

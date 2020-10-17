package com.api.helpers;

import com.api.constants.EndPoints;
import com.api.models.Pet;
import com.api.utils.CommonUtilities;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetServiceHelpers {

	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	private RequestSpecBuilder requestBuilder = null;
	private RequestSpecification httpRequestSpec = null;
	
	public PetServiceHelpers() {
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri(BASE_URL);
		httpRequestSpec = requestBuilder.build().log().all();
	}
	
	public Response getAllPetsByStatus(String status) {
		requestBuilder.setBasePath(EndPoints.GET_ALL_PET_BY_STATUS);
		requestBuilder.addParam("status", status);
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.spec(httpRequestSpec).request(Method.GET);
		return response;
	}
	
	public Response getPetByID(Long testID) {
		requestBuilder.addPathParam("id", testID);
		requestBuilder.setBasePath(EndPoints.GET_SINGLE_PET);
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.spec(httpRequestSpec).request(Method.GET);
		return response;
	}
	
	public Response createNewPet() {		
		requestBuilder.setBasePath(EndPoints.CREATE_PET);
		requestBuilder.addHeader("Content-Type", String.valueOf(ContentType.JSON));
		requestBuilder.setBody(CommonUtilities.getNewPet());
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.spec(httpRequestSpec).request(Method.POST);
		return response;
	}
	
	public Response updatePetStatus(Pet myPet) {
		requestBuilder.setBasePath(EndPoints.UPDATE_PET);
		requestBuilder.addHeader("Content-Type", String.valueOf(ContentType.JSON));
		requestBuilder.setBody(myPet);
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.spec(httpRequestSpec).request(Method.PUT);
		return response;
	}
	
	public Response deletePet(Long id) {
		requestBuilder.addPathParam("id", id);
		requestBuilder.setBasePath(String.valueOf(EndPoints.DELETE_PET));
		
		RequestSpecification httpRequest = RestAssured.given();
		// httpRequest.pathParam("id", id);
		Response response = httpRequest.spec(httpRequestSpec).request(Method.DELETE);
		return response;
	}
}

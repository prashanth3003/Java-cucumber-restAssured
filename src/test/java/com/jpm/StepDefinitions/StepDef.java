package com.jpm.StepDefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class StepDef {
	
	private Response response;
	private RequestSpecification requestSpec;
	private static String BASE_PATH = null;
	private static int size = -1;
	
	String title = "Adding a new post";
	String body = "The body of the post goes here";
	String userID = "1";
	
	@Given("The URL for the endpoint is already configured for {string}")
	public void the_endpoint_is_already_configured(String resource) {
		if (resource.equals("posts")) { BASE_PATH = "/posts";}
		else if (resource.equals("users")) { BASE_PATH = "/users";}
		else { BASE_PATH = "/comments";}
		requestSpec = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com").basePath(BASE_PATH).header("Content-Type",  "application/json");
	}
	
	@When("User must be able to add a post with UserID")
	public void user_must_be_able_to_add_a_post_with_UserID() {
		response = 	requestSpec.body("{ \"title\":\""  +  title  +  "\", \"body\":\""  +  body  +  "\", \"userID\":\""  +  userID  +  "\"}")
				  .post();
		response.then().log().all();
	}
	
	@Then("Status_code equals {string}")
	public void status_code_equals(String statusCode) {
		response.then().statusCode(Integer.parseInt(statusCode));
	}

	@Then("response contains ID equals {string}")
	public void response_contains_ID_equals(String id) {
		response.then().body("id", equalTo(Integer.parseInt(id)));
	}

	
	@When("I call the endpoint to get posts by the userID {string}")
	public void i_call_the_endpoint_to_get_posts_by_the_userID(String userId) {
		response = 	requestSpec.get("/" + userId);
		response.then().log().all();
	}

	@Then("the schema should match with the specification defined in {string}")
	public void the_schema_should_match_with_the_specification_defined_in(String format) {
		//format = "src\\main\\resources\\"+ format;
		response.then().assertThat().body(matchesJsonSchemaInClasspath(format));
		//if(format.equals("user-schema.json")){
		//format = "schema\\\\"+ format;
		//	response.then().assertThat().contentType(ContentType.JSON)
		//			.and()
		//	.body(matchesJsonSchemaInClasspath(format));
		}
	//}

	@Then("I should have the status code {string}")
	public void i_should_have_the_status_code(String statusCode) {
		response.then().statusCode(Integer.parseInt(statusCode));
	}	
	
	@Then("the body response content should be matched")
	public void the_body_response_content_should_be_matched(DataTable table) {

			List<List<String>> data = table.asLists();
		//	List<Map<String, String>> mapData =  table.asMaps();

			response.then().body("id", equalTo(Integer.parseInt(data.get(1).get(1))));

			for(int i = 2; i < data.size(); i++) {
				response.then().assertThat().body(data.get(i).get(0), equalTo(data.get(i).get(1)));
			}

	}
	
	@When("I call the endpoint to delete posts by the user {string}")
	public void i_call_the_endpoint_to_delete_posts_by_the_user(String userId) {
		response = 	requestSpec.delete("/" + userId);
		response.then().log().all();
	}
	
	@When("I input a valid userId {string}")
	public void i_input_a_valid_userId(String userId) {
		response = 	requestSpec.get("/" + userId);
		response.then().log().all();
	}
	
	@When("User calls the endpoint to get list of all users")
	public void user_calls_the_endpoint_to_get_list_of_all_users() {
		response = 	requestSpec.get();
		
		response.then().log().all();
	}

	@Then("all posts by the user should be returned")
	public void all_posts_by_the_user_should_be_returned() {
		
		size = response.jsonPath().getList("$").size();
		Assert.assertEquals(size, 15);
		//assertThat (response.body.size(), equalTo(10);
			
	}
	
	@Then("size of the response returned should be equal to {int}")
	public void size_of_the_response_returned_should_be_equal_to(int responseSize) {
		
		Assert.assertEquals(response.jsonPath().getList("$").size() , responseSize);
	}

	
	@When("user call the service to get comments for a post")
	public void user_call_the_service_to_get_comments_for_a_post() {
		response = 	requestSpec.get("?postId=1");
		response.then().log().all();
	}
	
	@When("user call the service to get comments for a post with postId {string}")
	public void user_call_the_service_to_get_comments_for_a_post_with_postId(String postID) {
		response = 	requestSpec.get("?postId="+postID);
		response.then().log().all();
	}
	
	@Then("all comments for the post should be returned")
	public void all_comments_for_the_post_should_be_returned() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("I call the endpoint to get posts by the user with invalid user id")
	public void i_call_the_endpoint_to_get_posts_by_the_user_with_invalid_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("empty response should be returned")
	public void empty_response_should_be_returned() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
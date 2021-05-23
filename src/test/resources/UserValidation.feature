#Author : PrashanthPB
Feature: Validate the User details

Background: Setup End-point for the API 
Given The URL for the endpoint is already configured for "users"

@test_users @Smoke
  Scenario: Fetch and Validate user data with a valid userId
    When I input a valid userId "2"
    Then I should have the status code "200"
    And the body response content should be matched
      | key        | value                  |
      | id         | 2                      |
      | name       | Ervin Howell           |
      | username   | Antonette              |
      | email      | Shanna@melissa.tv      |
      | phone      | 010-692-6593 x09125    |
      | website    | anastasia.net          |
  
 
 @test_users @regression
 Scenario: "Get user posts" response schema should match with specification
   When I call the endpoint to get posts by the userID "1"
   Then the schema should match with the specification defined in "user-schema.json"
  
  
@test_users
 Scenario: Verify the number of records returned by User
 	When User calls the endpoint to get list of all users
 	Then size of the response returned should be equal to 10
  
 @Test_User @negative
 Scenario: Enter an invalid UserId
	When I input a valid userId "88"
 	Then I should have the status code "404"

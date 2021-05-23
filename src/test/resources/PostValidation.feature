#Author : PrashanthPB
Feature: Validate that posts can be add-ed \ Fetch-ed \Delete-ed by an user

 Background: Setup End-point for the API 
    Given The URL for the endpoint is already configured for "posts"

@test_post @Smoke
Scenario: User should be able to add a post
    When User must be able to add a post with UserID
  	Then Status_code equals "201"    
    And  response contains ID equals "101"
 
 @test_post @regression
 Scenario: Response from posts should match with schema specification
   When I call the endpoint to get posts by the userID "1"
   Then the schema should match with the specification defined in "post_schema.json"

 @test_post
 Scenario: Should be able to retrieve user posts
   When I call the endpoint to get posts by the userID "2"
   Then I should have the status code "200"
   And the body response content should be matched
     | key        | value                  |
     | id         | 2                      |
     | title      | qui est esse           |
     
 @test_post 
 Scenario: Should be able to delete user posts
   When I call the endpoint to delete posts by the user "1"
   Then I should have the status code "200"

 @test_post @negative 
 Scenario: Error should be returned while searching with invalid userid
    When I call the endpoint to get posts by the userID "201"
    Then I should have the status code "404"
#Author : PrashanthPB

Feature: Get post comments
  As an admin
  I should be able to fetch comments of individual posts
  
  Background: Setup End-point for the API 
    Given The URL for the endpoint is already configured for "comments"

  Scenario: "Get post comments" response schema should match with specification
    When user call the service to get comments for a post with postId "1"
    And the schema should match with the specification defined in "comments_schema.json"

  Scenario: Should be able to get comments for a post
    When user call the service to get comments for a post with postId "2"
    Then size of the response returned should be equal to 5

  #Scenario: Emails in the comments should be in proper format
  #  Given a blog post by user "Samantha"
  #  When I retrieve comments for each post
  #  Then each comment should have an associated valid mailId

  Scenario: Empty response should be returned when searching for comments with non-existent postId
    When user call the service to get comments for a post with postId "210"
    Then size of the response returned should be equal to 0
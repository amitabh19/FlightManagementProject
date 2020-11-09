
Feature: Signup successful feature

  Scenario: Successful Signup 
    Given User is on Flight Management Signup Page
    When User logs in with valid credentials
    Then User should be shown welcome message and taken to login page

      
      

Feature: Signup sunuccessful feature

  Scenario: Unsuccessful Signup 
    Given User is currently on Flight Management Signup Page
    When User logs in with invalid credentials
    Then User should be shown signup error

      
      
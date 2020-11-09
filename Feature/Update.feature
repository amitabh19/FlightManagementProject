
Feature: Update User Details feature

  Scenario: Successful Update User Details
    Given User is on FlightManagement EditDetais Page
    When User enters new valid details 
    Then User should be shown user updated message

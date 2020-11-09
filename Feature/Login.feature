
Feature: Login feature

  
  Scenario Outline: Successful login 
    Given User is on FlightManagementLogin Page
    When User logs in with valid <email> and valid <password>
    Then User should be navigated to home page

    Examples: 
      | email             | password |  
      | amitabh@gmail.com | amitabhs | 
      | test@u.com        | testpass | 

      
      
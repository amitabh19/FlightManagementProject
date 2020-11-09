
Feature: Login unsuccessful feature

  
  Scenario Outline: Unsuccessful login 
    Given User is on Flight Management Login Page
    When User logs in with invalid <email> and invalid <password>
    Then User should be shown error

    Examples: 
      | email             | password |  
      | amitabh12@gmail.com | amitabhs | 
      | test@u.com        | testpass1 | 

      
      
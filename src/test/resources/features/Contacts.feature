Feature: Contacts page

  Scenario: Default page number
    Given the user is on the login page
    And the user enter the driver information
    When the user navigates "Customers" "Contacts"
    Then default page number should be 1

  Scenario: Verify Create Calendar Event
    Given the user is on the login page
    And the user enter sales manager information
    When the user navigates "Activities" "Calendar Events"


  Scenario: Menu Options
    Given the user logged in as "driver"
    Then the user should see following options
      | Fleet      |
      | Customers  |
      | Activities |
      | System     |


  Scenario: login as a given user
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | user1       |
      | password  | UserUser123 |
      | firstname | John        |
      | lastname  | Doe         |
    Then the user should be able to login


  Scenario: login as a given user
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | salesmanager101 |
      | password  | UserUser123     |
      | firstname | Peyton          |
      | lastname  | Harber          |
    Then the user should be able to login

  Scenario Outline: login as a given user <userType>
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | <userType>  |
      | password  | UserUser123 |
      | firstname | <firstName> |
      | lastname  | <lastName>  |
    Then the user should be able to login

    Examples:
      | userType        | firstName | lastName |
      | user1           | John      | Doe      |
      | salesmanager101 | Peyton    | Harber   |
      | storemanager85  | Marcella  | Huels    |
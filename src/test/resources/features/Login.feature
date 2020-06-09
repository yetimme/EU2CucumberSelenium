@login
Feature: Users should be able to login

  Background:
    Given the user is on the login page
    #we cannot use for then the user should be able to login because we have different condition
  #before this common condition of scenario
#in other words, you can use conditions here until the first difference

  @driver @smoke @VYT-123
  Scenario: Login as a driver
    When the user enter the driver information
    Then the user should be able to login

#you can use And keyword to add some additional info or when condition or other conditions as well
  @sales_manager @VYT-123
  Scenario: Login as a sales manager
    When the user enter sales manager information
    Then the user should be able to login

  @store_manager @smoke
  Scenario: Login as a store manager
    When the user enter store manager information
    Then the user should be able to login
@product
Feature: Delete product

  Scenario: Delete product by id
    When I delete a laptop with id is 156
    Then I want to verify body of delete api
    And I want to verify status code is 200
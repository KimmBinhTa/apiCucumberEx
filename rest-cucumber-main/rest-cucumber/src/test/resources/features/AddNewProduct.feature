@product
Feature: Add new product

  Scenario: Add new product
    When I add a new a laptop with id is 155
    Then I want to verify body is
      """
      {"BrandName":"Dell","Features":{"Feature":["8GB RAM","1TB Hard Drive"]},"Id":155,"LaptopName":"Latitude"}
      """
    And I want to verify status code is 200
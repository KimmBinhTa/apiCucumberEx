@product
Feature: Update product

  Scenario: Update product by id
    When I update a laptop with id is 157
    Then I want to verify body is
      """
      {"BrandName":"Dell","Features":{"Feature":["16GB RAM","1TB Hard Drive"]},"Id":157,"LaptopName":"Latitude"}
      """
    And I want to verify status code is 200
@product @delete_product
Feature: Delete product



  Scenario Outline: <testCaseID> - Delete product by id
    Given navigate to "add" api
    And the payload of request with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the ADD request to add new product
    Given navigate to "delete/{id}"
    When I perform the DELETE request to delete a laptop
    Then I want to verify body of delete api
    And I want to verify status code of deleting product is 200

    Examples:
      | testCaseID | brandName | feature | laptopName |
      | TC01       | Dell      | 8GB,HDD | Latitude   |
#      | TC02       | HP        | 4GB     | Envy       |
#      | TC03       | Lenovo    | 16GB    | Legion     |
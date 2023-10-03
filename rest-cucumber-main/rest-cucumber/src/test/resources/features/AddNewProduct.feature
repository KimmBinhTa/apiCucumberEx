@product @add_product
Feature: Add new product

  Scenario Outline: <testCaseID> - Add new product with valid data
    Given navigate to "add" api
    And the payload of request with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the ADD request to add new product
    Then The product add successfully with id
    And The product add successfully with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    And I want to verify status code for adding product is 200
    Examples:
      | testCaseID | brandName | feature | laptopName |
      | TC01       | Dell      | 8GB,SSD | Latitude   |
#      | TC02       | HP        | 4GB      | Envy       |
#      | TC03       | Lenovo    | 16GB     | Legion     |


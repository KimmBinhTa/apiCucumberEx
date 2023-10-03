@product @update_product
Feature: Update product




  Scenario Outline: <testCaseID> - Update product with "<brandNameUpdate>"
    Given navigate to "add" update api
    And the payload of request update with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the ADD request to update product
    Given navigate to "update" updating api
    And the payload of request update with UpdateBrandName as "<brandNameUpdate>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the PUT request to update a product
    Then The product update successfully with brandNameUpdate as "<brandNameUpdate>"
    And I want to verify status code for updating product is 200
    Examples:
      | testCaseID | brandName | feature | laptopName | brandNameUpdate |
#      | TC01       | Dell      | 8GB     | Latitude   | Asus|
      | TC02       | HP        | 4GB,HDD | Envy       | Macbook         |
#      | TC03       | Lenovo    | 16GB    | Legion     | LG
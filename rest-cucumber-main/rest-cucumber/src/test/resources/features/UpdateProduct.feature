@product @update_product
Feature: Update product

  Scenario Outline: <testCaseID> - Update product with "<brandNameUpdate>"
    Given navigate to "add" api
    And the payload of request with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the ADD request to add new product
    Given navigate to "update" api
    And the payload of request update with UpdateBrandName as "<brandNameUpdate>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the PUT request to update a product
    Then The product update successfully with brandNameUpdate as "<brandNameUpdate>"
    And I want to verify status code returning is 200
    Examples:
      | testCaseID                                  | brandName | feature  | laptopName | brandNameUpdate |
      | TC01-Happy case                             | Dell      | 8GB      | Latitude   | Asus            |
      | TC02-Updated brandName is empty             | HP        | 4GB,HDD  | Envy       |                 |
      | TC03-Updated brandName is null              | Lenovo    | 16GB     | Legion     | null            |
      | TC04-Updated brandName is special character | Lenovo    | 16GB     | Legion     | Envy$#@#@*#$#@  |
      | TC05-BrandName is empty                     |           | 16GB     | Legion     | null            |
      | TC06-Feature is empty                       | Lenovo    |          | Legion     | null            |
      | TC07-BrandName is empty                     | Lenovo    | 16GB     |            | null            |
      | TC08-BrandName is special character         | Dell$%#$@ | 8GB      | Latitude   | Asus            |
      | TC09-Feature is special character           | Dell      | 8GB,^%#$ | Latitude   | Asus            |

  Scenario: <testCaseID> - Update product with non exist id
    Given navigate to "add" api
    And the payload of request with BrandName as "Dell", Feature as "8GB", LaptopName as "Latitude"
    When I perform the ADD request to add new product
    Given navigate to "update" api
    And the payload of request update with UpdateBrandName as "Dell", Feature as "8GB", LaptopName as "Latitude"
    When I perform the PUT request to update a product
    Then The product update successfully with brandNameUpdate as "Asus"
    And I want to verify status code returning is 200
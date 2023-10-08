@product @add_product
Feature: Add new product

  Scenario Outline: <testCaseID> - Add new product
    Given navigate to "add" api
    And the payload of request with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    When I perform the ADD request to add new product
    Then The product add successfully with id
    And The product add successfully with BrandName as "<brandName>", Feature as "<feature>", LaptopName as "<laptopName>"
    And I want to verify status code for adding product is 200
    Examples:
      | testCaseID                                             | brandName | feature       | laptopName     |
      | TC01-Happy case                                        | Dell      | 8GB,SSD       | Latitude       |
      | TC02-BrandName & LaptopName with special character     | HP%323*() | 4GB           | Envy$#@#@*#$#@ |
      | TC03-Empty brandName                                   |           | 16GB          | Legion         |
      | TC04-Empty laptopName & feature with special character | HP        | 16GB,@#477893 |                |
      | TC05-Empty feature                                     | Lenovo    |               | Legion         |
      | TC06-Data of brandName is null                         | null      | 32GB,HDD      | Legion         |
      | TC07-Data of feature is null                           | Asus      |               | Legion         |
      | TC08-Data of laptopName is null                        | Dell      | 32GB,HDD      |                |

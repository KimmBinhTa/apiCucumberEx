package com.kms.api.tests;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.RestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;

public class UpdateProductSteps extends TestBase {

  Response responseBody;
  private Integer id;
  private String path = "";
  private Object requestPayloadAdd;
  private Object requestPayloadUpdate;
  private LaptopBag resUpdateLaptop;
  private LaptopBag resAddLaptop;
  private Response resAddProduct;

  private Response resUpdateProduct;

  @Given("navigate to {string} update api")
  public void navigate_to_update_api(String path) {
    this.path = path;
  }

  @Given(
      "the payload of request update with BrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void the_payload_of_request_update_with_brand_name_as_feature_as_laptop_name_as(
      String brandName, String feature, String laptopName) {
    String[] arr = feature.split(",");
    List<String> lstFeatures = Arrays.asList(arr);
    id = (int) (Math.random() * 10000);
    requestPayloadAdd = RequestBuilder.requestPayload(laptopName, brandName, id, lstFeatures);

  }


  @When("I perform the ADD request to update product")
  public void perform_the_add_request_to_update_product() {

    resAddProduct = RequestFactory.addProduct(this.path, (LaptopBag) requestPayloadAdd);
    resAddLaptop = RestUtil.mapRestResponseToPojo(resAddProduct, LaptopBag.class);
  }

  @Given(
      "the payload of request update with UpdateBrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void the_payload_of_request_update_with_update_brand_name_as_feature_as_laptop_name_as(
      String updatedBrandName, String feature, String laptopName) {
    String[] arr = feature.split(",");
    List<String> lstFeatures = Arrays.asList(arr);
    requestPayloadUpdate =
        RequestBuilder.requestPayload(laptopName, updatedBrandName, this.id, lstFeatures);


  }

  @When("I perform the PUT request to update a product")
  public void i_perform_the_put_request_to_update_a_product() {

    resUpdateProduct = RequestFactory.updateProduct(this.path, (LaptopBag) requestPayloadUpdate);
    resUpdateLaptop = RestUtil.mapRestResponseToPojo(resUpdateProduct, LaptopBag.class);

  }

  @Then("The product update successfully with brandNameUpdate as {string}")
  public void the_product_update_successfully_with_brand_name_as(String updateBrandName) {
    Assert.assertEquals(updateBrandName, resUpdateLaptop.getBrandName());
  }

  @Given("I want to verify status code for updating product is {int}")
  public void i_want_to_verify_status_code_for_updating_product_is(Integer expectedStatus) {
    Integer actualStatus = resUpdateProduct.getStatusCode();
    Assert.assertEquals(expectedStatus, actualStatus);
  }

  @Given("navigate to {string} updating api")
  public void navigate_to_updating_api(String path) {
    this.path = path;
  }
}

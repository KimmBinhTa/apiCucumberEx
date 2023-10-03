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

public class DeleteProductSteps extends TestBase {

  Response responseBody;
  private Integer id;
  private String path = "";
  private Object requestPayloadAdd;
  private Object requestPayloadDelete;
  private LaptopBag resDeleteLaptop;
  private LaptopBag resAddLaptop;

  private Response resDelete;
  private Response resAdd;

  @Given("navigate to {string}")
  public void navigate_to(String path) {
    this.path = path;
    System.out.println("Path:"+this.path);
  }

  @Given(
      "payload of request with BrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void payload_of_request_with_brand_name_as_feature_as_laptop_name_as(
      String brandName, String feature, String laptopName) {
    String[] arr = feature.split(",");
    List<String> lstFeatures = Arrays.asList(arr);
    id = (int) (Math.random() * 10000);
    requestPayloadAdd = RequestBuilder.requestPayload(laptopName, brandName, id, lstFeatures);
  }

  @When("perform the ADD request to add new product")
  public void perform_the_add_request_to_add_new_product() {
    resAdd = RequestFactory.addProduct(this.path, (LaptopBag) requestPayloadAdd);
    resAddLaptop = RestUtil.mapRestResponseToPojo(resAdd, LaptopBag.class);
  }

  @Given("navigate to {string} delete api")
  public void navigate_to_delete_api(String path) {
    this.path = path;
  }

  @When("I perform the DELETE request to delete a laptop")
  public void i_delete_a_laptop_with() {
    Integer delId = (Integer)AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    resDelete = RequestFactory.deleteProduct(this.path,delId.intValue());

  }

  @Given("I want to verify status code of deleting product is {int}")
  public void i_want_to_verify_status_code_of_deleting_product_is(Integer expectedStatus) {
    Integer actualStatus = resDelete.getStatusCode();
    Assert.assertEquals(expectedStatus, actualStatus);
  }

  @Then("I want to verify body of delete api")
  public void i_want_to_verify_body_of_delete_api() {
    // String body = res.getBody().asString();
    String body = String.valueOf(resDelete.getBody().prettyPrint());
    Integer delId = (Integer)AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    Assert.assertEquals(body, String.valueOf(delId));
  }
}

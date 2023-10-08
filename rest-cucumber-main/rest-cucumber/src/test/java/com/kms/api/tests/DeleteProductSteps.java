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


  @Given("navigate to {string} delete api")
  public void navigate_to_delete_api(String path) {
    this.path = path;
  }

  @When("I perform the DELETE request to delete a laptop")
  public void i_delete_a_laptop_with() {
    Integer delId = (Integer) AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    Integer delIDErr = 9918281;
    resDelete = RequestFactory.deleteProduct(this.path,delIDErr.intValue());

  }

  @Then("I want to verify body of delete api")
  public void i_want_to_verify_body_of_delete_api() {
    // String body = res.getBody().asString();
    String body = String.valueOf(resDelete.getBody().prettyPrint());
    Integer delId = (Integer)AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    Assert.assertEquals(body, String.valueOf(delId));
  }

  @Then("I want to verify status code for deleting product is {int}")
  public void i_want_to_verify_status_code_is(Integer expectedStatus) {
    Integer actualStatus = resDelete.getStatusCode();
    org.junit.Assert.assertEquals(expectedStatus, actualStatus);
  }
}

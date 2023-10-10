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
  private Object requestPayloadAdd;
  private Object requestPayloadDelete;
  private LaptopBag resDeleteLaptop;
  private LaptopBag resAddLaptop;

  private Response resDelete;
  private Response resAdd;


  @When("I perform the DELETE request to delete a laptop")
  public void i_delete_a_laptop_with() {
    Integer delId = (Integer) AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    Integer delIDErr = 9918281;
    resDelete = RequestFactory.deleteProduct(CommonSteps.path,delId.intValue());
    CommonSteps.productRes = resDelete;
  }

  @Then("I want to verify body of delete api")
  public void i_want_to_verify_body_of_delete_api() {
    // String body = res.getBody().asString();
    String body = String.valueOf(resDelete.getBody().prettyPrint());
    Integer delId = (Integer)AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    Assert.assertEquals(body, String.valueOf(delId));
  }
}

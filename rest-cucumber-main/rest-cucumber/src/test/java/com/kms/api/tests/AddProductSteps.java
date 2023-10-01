package com.kms.api.tests;

import static com.kms.api.util.RestUtil.mapRestResponseToPojo;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.requests.RestClient;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductSteps extends TestBase {

  Response responseBody;
  private String path = "";
  private Object requestPayload;
  private LaptopBag reqAddLaptop;
  private LaptopBag resAddLaptop;
  private Integer id;
  private Response res;

//  @Given("^the path \"([^\"]*)\" to the endpoint$")
//  public void thePathToAddTheProduct(String path) {
//    this.path = path;
//  }
//
//  @And(
//      "^the payload of the request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
//  public void thePayloadOfTheRequestWithBrandNameAsFeaturesAsLaptopNameAs(
//      String brandName, String feature, String laptopName) {
//    String[] array = feature.split(",");
//    List<String> lst = Arrays.asList(array);
//    id = (int) (Math.random() * 10000);
//    requestPayload = RequestBuilder.requestPayload(laptopName, brandName, id, lst);
//  }
//
//  @When("^I perform the request to add new product$")
//  public void iPerformTheRequestToApplication() {
//    reqAddLaptop = (LaptopBag) requestPayload;
//    res = RequestFactory.addProduct(path, reqAddLaptop);
//    resAddLaptop = mapRestResponseToPojo(res, LaptopBag.class);
//  }
//
//  @Then("^the status code \"([^\"]*)\" should return$")
//  public void theStatusCodeShouldReturn(String statusCode) {
//    ValidationUtil.validateStatusCode(res, Integer.parseInt(statusCode));
//  }
//
//  @And("^the product is added successfully with an integer Id$")
//  public void theProductIsAddedSuccessfullyWithAnIntegerId() {
//    ValidationUtil.validateStringEqual(resAddLaptop.getId(), id);
//  }

  @When("^I add a new a laptop with id is (\\d+)$")
  public void i_add_a_new_a_laptop_with_id_is(Integer id) {
    String requestPath = "http://localhost:8080/laptop-bag/webapi/secure/add/";
    ContentType contentType = ContentType.JSON;
    this.id = id;

    Map<String, Object> laptop = new HashMap<String, Object>();
    Map<String, List<String>> feature = new HashMap<>();

    feature.put("Feature", Arrays.asList("8GB RAM", "1TB Hard Drive"));

    laptop.put("BrandName", "Dell");
    laptop.put("Id", id);
    laptop.put("LaptopName", "Latitude");
    laptop.put("Features", feature);

    responseBody = RestClient.doPostRequestWithObject(requestPath, contentType, laptop);
  }
//  @Then("I want to verify body is")
//  public void i_want_to_verify_body_is(String expectedPayload) {
//    // Write code here that turns the phrase above into concrete actions
//    String body = responseBody.getBody().asString();
//
//    Assert.assertEquals(body, expectedPayload);
//  }
//  @Then("I want to verify status code is {int}")
//  public void i_want_to_verify_status_code_is(Integer expectedStatus) {
//    // Write code here that turns the phrase above into concrete actions
//    String body = responseBody.getBody().asString();
//    Integer actualStatus = responseBody.getStatusCode();
//    Assert.assertEquals(expectedStatus, actualStatus);
//  }
}

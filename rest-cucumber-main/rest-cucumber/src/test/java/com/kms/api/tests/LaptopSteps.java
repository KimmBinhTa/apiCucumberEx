package com.kms.api.tests;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RestClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopSteps extends TestBase {

  Response responseBody;
  private Integer id;


  @When("I update a laptop with id is {int}")
  public void i_update_a_laptop_with_id_is(Integer id) {
    // Write code here that turns the phrase above into concrete actions
    String requestPath = "http://localhost:8080/laptop-bag/webapi/secure/update";
    ContentType contentType = ContentType.JSON;
    this.id = id;

    Map<String, Object> laptop = new HashMap<String, Object>();
    Map<String, List<String>> feature = new HashMap<>();

    feature.put("Feature", Arrays.asList("16GB RAM","1TB Hard Drive"));

    laptop.put("BrandName", "Dell");
    laptop.put("Features", feature);
    laptop.put("Id",id);
    laptop.put("LaptopName", "Latitude");

    responseBody = RestClient.doPutRequestWithPayload(requestPath, contentType, laptop);
  }
  @Then("I want to verify body is")
  public void i_want_to_verify_body_is(String expectedPayload) {
    // Write code here that turns the phrase above into concrete actions
    String body = responseBody.getBody().asString();

    Assert.assertEquals(body, expectedPayload);
  }

  @Then("I want to verify status code is {int}")
  public void i_want_to_verify_status_code_is(Integer expectedStatus) {
    // Write code here that turns the phrase above into concrete actions
    String body = responseBody.getBody().asString();
    Integer actualStatus = responseBody.getStatusCode();
    Assert.assertEquals(expectedStatus, actualStatus);
  }

  @When("I delete a laptop with id is {int}")
  public void i_delete_a_laptop_with_id_is(Integer id) {
    // Write code here that turns the phrase above into concrete actions
    String requestPath = "http://localhost:8080/laptop-bag/webapi/secure/delete/{id}";
    this.id = id;
    responseBody = RestClient.doDeleteRequestWithParams(requestPath,id);
  }
  @Then("I want to verify body of delete api")
  public void i_want_to_verify_body_of_delete_api() {
    // Write code here that turns the phrase above into concrete actions
    String body = responseBody.getBody().asString();

    Assert.assertEquals(body, String.valueOf(this.id));
  }
}

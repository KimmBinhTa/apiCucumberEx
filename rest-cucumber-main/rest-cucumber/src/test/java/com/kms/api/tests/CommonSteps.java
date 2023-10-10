package com.kms.api.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class CommonSteps {
    static Response productRes;
    public static String path = "";

    @Then("I want to verify status code returning is {int}")
    public void i_want_to_verify_status_code_is(Integer expectedStatus) {
        Integer actualStatus = productRes.getStatusCode();
        org.junit.Assert.assertEquals(expectedStatus, actualStatus);
    }
    @Given("navigate to {string} api")
    public void navigateToApi(String path) {
        this.path = path;
    }

}

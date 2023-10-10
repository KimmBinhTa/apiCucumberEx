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


  @Given(
      "the payload of request update with UpdateBrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void the_payload_of_request_update_with_update_brand_name_as_feature_as_laptop_name_as(
      String updatedBrandName, String feature, String laptopName) {
    String[] arr = feature.split(",");
    List<String> lstFeatures = Arrays.asList(arr);
    int idUpdate = (int) AddProductSteps.contextAddProduct.get("LAPTOP_ID");
    int idUpdateErr = 99999;
    if(updatedBrandName.equals("null")){
      updatedBrandName = null;
    }
    requestPayloadUpdate =
        RequestBuilder.requestPayload(laptopName, updatedBrandName, idUpdate, lstFeatures);


  }

  @When("I perform the PUT request to update a product")
  public void i_perform_the_put_request_to_update_a_product() {

    resUpdateProduct = RequestFactory.updateProduct(this.path, (LaptopBag) requestPayloadUpdate);
    resUpdateLaptop = RestUtil.mapRestResponseToPojo(resUpdateProduct, LaptopBag.class);
    CommonSteps.productRes = resUpdateProduct;

  }

  @Then("The product update successfully with brandNameUpdate as {string}")
  public void the_product_update_successfully_with_brand_name_as(String updatedBrandName) {
    if(updatedBrandName.equals("null")){
      updatedBrandName = null;
    }
    Assert.assertEquals(updatedBrandName, resUpdateLaptop.getBrandName());
  }

  @Given("navigate to {string} updating api")
  public void navigate_to_updating_api(String path) {
    this.path = path;
  }
}

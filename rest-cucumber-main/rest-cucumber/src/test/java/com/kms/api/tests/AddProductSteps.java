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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

public class AddProductSteps extends TestBase {
  Response responseBody;
  private Object requestPayload;
  private LaptopBag reqAddLaptop;
  private LaptopBag resAddLaptop;
  private Integer id;
  private Response res;

  public static Map<String, Object> contextAddProduct = new HashMap<String, Object>();


  @Given(
      "the payload of request with BrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void the_payload_of_request_with_brand_name_as_feature_as_laptop_name_as(
      String brandName, String feature, String laptopName) {
    String[] arr = feature.split(",");
    List<String> lstFeatures = Arrays.asList(arr);
    id = (int) (Math.random() * 10000);
    contextAddProduct.put("LAPTOP_ID", id);

    if(brandName.equals("null")){
      brandName = null;
    }
    if(feature.equals("null")){
      feature = null;
    }
    if(laptopName.equals("null")){
      laptopName = null;
    }

    requestPayload = RequestBuilder.requestPayload(laptopName, brandName, id, lstFeatures);
  }

  @When("I perform the ADD request to add new product")
  public void i_perform_the_add_request_to_add_new_product() {
    res = RequestFactory.addProduct(CommonSteps.path, (LaptopBag) requestPayload);
    resAddLaptop = RestUtil.mapRestResponseToPojo(res, LaptopBag.class);
    CommonSteps.productRes = res;
  }

  @Then("The product add successfully with id")
  public void the_product_add_successfully_with_id() {

    Assert.assertEquals(this.id, resAddLaptop.getId());
  }

  @Then(
      "The product add successfully with BrandName as {string}, Feature as {string}, LaptopName as {string}")
  public void the_product_add_successfully_with_brand_name_as_feature_as_laptop_name_as(
      String brandName, String feature, String laptopName) {

    if(brandName.equals("null")){
      brandName = null;
    }
    if(feature.equals("null")){
      feature = null;
    }
    if(laptopName.equals("null")){
      laptopName = null;
    }
    Assert.assertEquals(brandName, resAddLaptop.getBrandName());
    Assert.assertEquals(laptopName, resAddLaptop.getLaptopName());
    String actualFeature = StringUtils.join(resAddLaptop.getFeatures().getFeature(),",");
    Assert.assertEquals(feature, actualFeature);

  }


}

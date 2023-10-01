package com.kms.api.requests;

import static io.restassured.RestAssured.given;

import com.kms.api.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

/**
 * Generic RestClient containing some Rest-Assured methods.
 *
 * @author vihoang Date 1-Jun-2022
 */
public class RestClient extends TestBase {

  public static Response doGetRequestWithParams(
          String requestPath, Map<String, String> params, ContentType contentType) {
    return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "welcome")
            .queryParams(params)
            .contentType(contentType)
            .accept(contentType)
            .when()
            .get(requestPath)
            .then()
            .log()
            .all()
            .extract()
            .response();
  }

  public static Response doPostRequestWithObject(
          String requestPath, ContentType contentType, Object body) {
    return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "welcome")
            .log()
            .everything()
            .accept(contentType)
            .contentType(contentType)
            .with()
            .accept(contentType)
            .body(body)
            .when()
            .post(requestPath)
            .then()
            .log()
            .all()
            .extract()
            .response();
  }

  public static Response doPostRequestWithPayload(String requestPath, ContentType contentType, String payload) {
    return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "welcome")
            .log()
            .everything()
            .contentType(contentType)
            .with()
            .accept(contentType)
            .body(payload)
            .when()
            .post(requestPath)
            .then()
            .log()
            .all()
            .extract()
            .response();
  }

  public static Response doPutRequestWithPayload(String requestPath, ContentType contentType, Object body) {
    return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "welcome")
            .log()
            .everything()
            .accept(contentType)
            .contentType(contentType)
            .with()
            .accept(contentType)
            .body(body)
            .when()
            .put(requestPath)
            .then()
            .log()
            .all()
            .extract()
            .response();
  }

  public static Response doDeleteRequestWithParams(String requestPath, int id) {
    return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "welcome")
            .log()
            .everything()
            .with()
            .when()
            .pathParam("id", id)
            .delete(requestPath)
            .then()
            .log()
            .all()
            .extract()
            .response();
  }
}

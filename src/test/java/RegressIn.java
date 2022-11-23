import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegressIn {
    @Test
    @DisplayName("POST")
    void createUser() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    @DisplayName("Get List")
    void getUser23() {
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .statusCode(404);
    }
    @Test
    @DisplayName("Unknown")
    void unknownList(){
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .statusCode(200);
    }
    @Test
    @DisplayName("Single user")
    void singleUser(){
        String dataSupport= "To keep ReqRes free, contributions towards server costs are appreciated!";
       given()
                .log().uri()
               .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(200)
               .body("data.id", is(2),"data.last_name",
                       is("Weaver"),"support.text", is(dataSupport));
    }
    @Test
    void putUpdate(){
        String data =  "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        given()
                .log().uri()
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(200);
    }

}

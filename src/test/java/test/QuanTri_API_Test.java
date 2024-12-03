package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class QuanTri_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/quantri";
    }

    @Test
    void testHelo() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("helo", equalTo("helo")); // Kiểm tra giá trị trả về có đúng là "helo"
    }

    @Test
    void testLoginSuccess() {
        String username = "admin"; // Thay thế bằng tên tài khoản hợp lệ
        String password = "password"; // Thay thế bằng mật khẩu hợp lệ
        given()
            .pathParam("tk", username)
            .pathParam("mk", password)
            .contentType(ContentType.JSON)
        .when()
            .get("/login/{tk}/{mk}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("username", equalTo(username)) // Kiểm tra tên tài khoản trả về đúng
            .body("password", equalTo(password)); // Kiểm tra mật khẩu trả về đúng
    }

    @Test
    void testLoginFailure() {
        String username = "wronguser"; // Tên tài khoản sai
        String password = "wrongpass"; // Mật khẩu sai
        given()
            .pathParam("tk", username)
            .pathParam("mk", password)
            .contentType(ContentType.JSON)
        .when()
            .get("/login/{tk}/{mk}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("Invalid credentials")); // Kiểm tra thông điệp lỗi nếu tài khoản sai
    }
}

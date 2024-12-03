package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class Thuoc_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080/Do_AN_Server_Web/rest"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/thuoc";
    }

    @Test
    void testSelectAll() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0)); // Kiểm tra có ít nhất một phần tử trong danh sách
    }

    @Test
    void testSelectName() {
    	String tenthuoc = "Terfuzol"; // Thay đổi tên thuốc để kiểm tra

        // Mã hóa tên thuốc trước khi truyền vào URL
    	String encodedName = URLEncoder.encode(tenthuoc, StandardCharsets.UTF_8);
    	 encodedName = encodedName.replace("+", "%20");
        System.out.println("mahoa: "+encodedName);
        given()
            .pathParam("name", encodedName) // Truyền tên thuốc đã mã hóa
            .contentType(ContentType.JSON)
        .when()
            .get("/selecttenthuoc/{name}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .log().body()
            .body("tenthuoc", hasItem("Terfuzol ")); // Kiểm tra tên thuốc có trong danh sách trả về
    }

    @Test
    void testSelectID() {
        Integer id = 1; // Thay đổi ID thuốc để kiểm tra
        given()
            .pathParam("id", id)
            .contentType(ContentType.JSON)
        .when()
            .get("/selectID/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(id)); // Kiểm tra ID thuốc trả về chính xác
    }

    @Test
    void testInsert() {
        String jsonRequest = "{\"name\":\"Thuoc B\",\"price\":1000,\"description\":\"Thuốc mới\"}"; // Thay đổi thông tin thuốc mới
        given()
            .contentType(ContentType.JSON)
            .body(jsonRequest)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("Thêm Mới Thành Công")); // Kiểm tra thông điệp trả về khi thêm mới thành công
    }

    @Test
    void testUpdate() {
        String jsonRequest = "{\"id\": 1, \"name\":\"Thuoc C\",\"price\":1200,\"description\":\"Thuốc cập nhật\"}"; // Thay đổi thông tin thuốc cần cập nhật
        given()
            .contentType(ContentType.JSON)
            .body(jsonRequest)
        .when()
            .post("/update")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("Cập Nhật Thành Công")); // Kiểm tra thông điệp trả về khi cập nhật thành công
    }

    @Test
    void testDelete() {
        Integer id = 1; // Thay đổi ID thuốc để kiểm tra
        given()
            .pathParam("id", id)
            .contentType(ContentType.JSON)
        .when()
            .get("/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("Xóa Thuốc Thành Công")); // Kiểm tra thông điệp trả về khi xóa thành công
    }
}

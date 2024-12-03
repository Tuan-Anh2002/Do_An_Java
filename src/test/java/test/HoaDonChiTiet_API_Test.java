package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class HoaDonChiTiet_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/HoaDonChiTiet";
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
            .body("$", not(empty())); // Kiểm tra phản hồi không rỗng
    }

    @Test
    void testSelectByID() {
        int testID = 1; // Thay thế bằng ID hợp lệ để kiểm tra
        given()
            .pathParam("id", testID)
            .contentType(ContentType.JSON)
        .when()
            .get("/selectID/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(testID)); // Kiểm tra ID trả về trùng với ID yêu cầu
    }

    @Test
    void testSelectMaHD() {
        int testMaHD = 101; // Thay thế bằng mã hóa đơn hợp lệ để kiểm tra
        given()
            .pathParam("mahd", testMaHD)
            .contentType(ContentType.JSON)
        .when()
            .get("/SelectMaHD/{mahd}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty())); // Kiểm tra phản hồi không rỗng
    }

    @Test
    void testInsertSuccess() {
        String newHoaDonChiTiet = """
        {
            "mahd": 1,
            "mathuoc": 2,
            "soluong": 10,
            "dongia": 50000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newHoaDonChiTiet)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc thêm thành công
    }

    @Test
    void testInsertFail() {
        String invalidHoaDonChiTiet = """
        {
            "mahd": null,
            "mathuoc": null,
            "soluong": -10,
            "dongia": -50000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidHoaDonChiTiet)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc thêm thất bại
    }

    @Test
    void testUpdateSuccess() {
        String updateHoaDonChiTiet = """
        {
            "id": 1,
            "mahd": 1,
            "mathuoc": 3,
            "soluong": 5,
            "dongia": 40000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updateHoaDonChiTiet)
        .when()
            .post("/update")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc cập nhật thành công
    }

    @Test
    void testUpdateFail() {
        String invalidUpdate = """
        {
            "id": 9999, // ID không tồn tại
            "mahd": 1,
            "mathuoc": 3,
            "soluong": -5,
            "dongia": -40000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidUpdate)
        .when()
            .post("/update")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc cập nhật thất bại
    }
}

package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class PhuongThucThanhToan_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/phuongthucthanhtoan";
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
    void testSelectID() {
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
    void testSelectName() {
        String name = "Thanh Toan A"; // Thay thế bằng tên hợp lệ để kiểm tra
        given()
            .pathParam("name", name)
            .contentType(ContentType.JSON)
        .when()
            .get("/selectname/{name}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty())); // Kiểm tra phản hồi không rỗng
    }

    @Test
    void testInsertSuccess() {
        String newPhuongThucThanhToan = """
        {
            "tenPhuongThuc": "Thanh Toan Online",
            "moTa": "Thanh toán qua cổng điện tử",
            "chiPhi": 500
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newPhuongThucThanhToan)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc thêm thành công
    }

    @Test
    void testInsertFail() {
        String invalidPhuongThucThanhToan = """
        {
            "tenPhuongThuc": "",
            "moTa": "Thanh toán qua cổng điện tử",
            "chiPhi": 500
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidPhuongThucThanhToan)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc thêm thất bại do tên phương thức thanh toán trống
    }

    @Test
    void testUpdateSuccess() {
        String updatePhuongThucThanhToan = """
        {
            "id": 1,
            "tenPhuongThuc": "Thanh Toan Qua Bank",
            "moTa": "Thanh toán qua ngân hàng",
            "chiPhi": 1000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updatePhuongThucThanhToan)
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
            "tenPhuongThuc": "Thanh Toan Qua Bank",
            "moTa": "Thanh toán qua ngân hàng",
            "chiPhi": 1000
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

    @Test
    void testDeleteSuccess() {
        int testID = 1; // Thay thế bằng ID hợp lệ để kiểm tra
        given()
            .pathParam("id", testID)
            .contentType(ContentType.JSON)
        .when()
            .get("/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc xóa thành công
    }

    @Test
    void testDeleteFail() {
        int invalidID = 9999; // ID không tồn tại để kiểm tra thất bại
        given()
            .pathParam("id", invalidID)
            .contentType(ContentType.JSON)
        .when()
            .get("/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc xóa thất bại
    }
}

package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class PhuongThucVanChuyen_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/phuongthucvanchuyen";
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
        String name = "Vận Chuyển A"; // Thay thế bằng tên hợp lệ để kiểm tra
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
        String newPhuongThucVanChuyen = """
        {
            "tenPhuongThuc": "Vận Chuyển Siêu Tốc",
            "moTa": "Vận chuyển nhanh trong 24h",
            "chiPhi": 1000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newPhuongThucVanChuyen)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc thêm thành công
    }

    @Test
    void testInsertFail() {
        String invalidPhuongThucVanChuyen = """
        {
            "tenPhuongThuc": "",
            "moTa": "Vận chuyển nhanh trong 24h",
            "chiPhi": 1000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidPhuongThucVanChuyen)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc thêm thất bại do tên phương thức vận chuyển trống
    }

    @Test
    void testUpdateSuccess() {
        String updatePhuongThucVanChuyen = """
        {
            "id": 1,
            "tenPhuongThuc": "Vận Chuyển Tốc Hành",
            "moTa": "Vận chuyển nhanh với chi phí hợp lý",
            "chiPhi": 1500
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updatePhuongThucVanChuyen)
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
            "tenPhuongThuc": "Vận Chuyển Tốc Hành",
            "moTa": "Vận chuyển nhanh với chi phí hợp lý",
            "chiPhi": 1500
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
            .get("/deletePTVC/{id}")
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
            .get("/deletePTVC/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc xóa thất bại
    }
}

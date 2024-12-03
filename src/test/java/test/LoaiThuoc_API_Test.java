package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class LoaiThuoc_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/loaithuoc";
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
        String name = "ThuocA"; // Thay thế bằng tên hợp lệ để kiểm tra
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
        String newLoaiThuoc = """
        {
            "tenLoai": "Thuoc B",
            "moTa": "Thuốc điều trị bệnh X",
            "donGia": 10000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newLoaiThuoc)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc thêm thành công
    }

    @Test
    void testInsertFail() {
        String invalidLoaiThuoc = """
        {
            "tenLoai": "",
            "moTa": "Thuốc điều trị bệnh X",
            "donGia": 10000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidLoaiThuoc)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc thêm thất bại do tên loại thuốc trống
    }

    @Test
    void testUpdateSuccess() {
        String updateLoaiThuoc = """
        {
            "id": 1,
            "tenLoai": "Thuoc C",
            "moTa": "Thuốc điều trị bệnh Y",
            "donGia": 15000
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updateLoaiThuoc)
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
            "tenLoai": "Thuoc D",
            "moTa": "Thuốc điều trị bệnh Z",
            "donGia": 20000
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

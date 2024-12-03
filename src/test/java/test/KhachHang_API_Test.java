package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class KhachHang_API_Test {

    @BeforeAll
    static void setup() {
        // Cấu hình URI cơ sở cho RestAssured
        RestAssured.baseURI = "http://localhost:8080"; // Thay đổi cổng và đường dẫn nếu cần thiết
        RestAssured.basePath = "/KhachHang";
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
        String name = "Nguyen"; // Thay thế bằng tên hợp lệ để kiểm tra
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
        String newKhachHang = """
        {
            "taikhoan": "user123",
            "matkhau": "password123",
            "hoten": "Nguyen Van A",
            "email": "nguyen@example.com",
            "sdt": "0123456789"
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newKhachHang)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)); // Kiểm tra việc thêm thành công
    }

    @Test
    void testInsertFail() {
        String invalidKhachHang = """
        {
            "taikhoan": "",
            "matkhau": "password123",
            "hoten": "Nguyen Van B",
            "email": "nguyenb@example.com",
            "sdt": "0123456789"
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidKhachHang)
        .when()
            .post("/insert")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)); // Kiểm tra việc thêm thất bại do thiếu tài khoản
    }

    @Test
    void testUpdateSuccess() {
        String updateKhachHang = """
        {
            "id": 1,
            "taikhoan": "user123",
            "matkhau": "newpassword123",
            "hoten": "Nguyen Van A",
            "email": "nguyen_a@example.com",
            "sdt": "0123456789"
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updateKhachHang)
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
            "taikhoan": "user123",
            "matkhau": "newpassword123",
            "hoten": "Nguyen Van A",
            "email": "nguyen_a@example.com",
            "sdt": "0123456789"
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
    void testLoginSuccess() {
        String loginData = """
        {
            "taikhoan": "user123",
            "matkhau": "newpassword123"
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(loginData)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)) // Kiểm tra đăng nhập thành công
            .body("message", equalTo("Đăng Nhập Thành Công"));
    }

    @Test
    void testLoginFail() {
        String loginData = """
        {
            "taikhoan": "invalidUser",
            "matkhau": "wrongpassword"
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(loginData)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)) // Kiểm tra đăng nhập thất bại
            .body("message", equalTo("Sai Tài Khoản Hoặc Mật Khẩu"));
    }

    @Test
    void testDelete() {
        int testID = 1; // Thay thế bằng ID hợp lệ để kiểm tra
        given()
            .pathParam("id", testID)
            .contentType(ContentType.JSON)
        .when()
            .get("/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(101)) // Kiểm tra việc xóa thành công
            .body("message", equalTo("Xóa Khách Hàng Thành Công"));
    }

    @Test
    void testDeleteFail() {
        int testID = 9999; // Thay thế bằng ID không tồn tại
        given()
            .pathParam("id", testID)
            .contentType(ContentType.JSON)
        .when()
            .get("/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("code", equalTo(104)) // Kiểm tra việc xóa thất bại
            .body("message", equalTo("Xóa Khách Hàng Thất Bại"));
    }
}

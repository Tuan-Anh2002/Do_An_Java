package test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import Entity.HinhAnh;
import Entity.MesssageBox;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HinhAnh_API_Test {

    private static final String BASE_URL = "http://localhost:8080/hinhanh";

    @Test
    public void testSelectAll() {
        // Gửi yêu cầu GET tới endpoint /hinhanh/
        Response response = given()
                .when()
                .get(BASE_URL + "/")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành mảng HinhAnh
        Gson gson = new Gson();
        HinhAnh[] hinhAnhs = gson.fromJson(response.asString(), HinhAnh[].class);

        // Kiểm tra kết quả
        System.out.println("Số lượng hình ảnh: " + hinhAnhs.length);
        assertEquals(true, hinhAnhs.length > 0); // Xác minh danh sách không rỗng
    }

    @Test
    public void testInsert() {
        // Dữ liệu gửi POST
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", null);
        requestBody.put("url", "https://example.com/image.jpg");
        requestBody.put("description", "Test image description");
        requestBody.put("thuoc", null);

        // Gửi yêu cầu POST tới endpoint /hinhanh/insert
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/insert")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành MesssageBox
        Gson gson = new Gson();
        MesssageBox msg = gson.fromJson(response.asString(), MesssageBox.class);

        // Kiểm tra kết quả
        assertEquals(101, msg.getMacode());
        System.out.println("Insert result: " + msg.getTenloi());
    }

    @Test
    public void testUpdate() {
        // Dữ liệu gửi POST để cập nhật
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1); // ID hợp lệ
        requestBody.put("url", "https://example.com/updated_image.jpg");
        requestBody.put("description", "Updated image description");
        requestBody.put("thuoc", null);

        // Gửi yêu cầu POST tới endpoint /hinhanh/update
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/update")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành MesssageBox
        Gson gson = new Gson();
        MesssageBox msg = gson.fromJson(response.asString(), MesssageBox.class);

        // Kiểm tra kết quả
        assertEquals(101, msg.getMacode());
        System.out.println("Update result: " + msg.getTenloi());
    }

    @Test
    public void testSelectById() {
        int id = 1; // ID hợp lệ cần kiểm tra

        // Gửi yêu cầu GET tới endpoint /hinhanh/selectID/{id}
        Response response = given()
                .pathParam("id", id)
                .when()
                .get(BASE_URL + "/selectID/{id}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành đối tượng HinhAnh
        Gson gson = new Gson();
        HinhAnh hinhAnh = gson.fromJson(response.asString(), HinhAnh.class);

        // Kiểm tra kết quả
        assertEquals(id, hinhAnh.getHAThuoc());
        System.out.println("Selected image description: " + hinhAnh.getTenfileanh());
    }

    @Test
    public void testSelectByName() {
        String name = "Test"; // Tên hợp lệ cần kiểm tra

        // Gửi yêu cầu GET tới endpoint /hinhanh/selectname/{name}
        Response response = given()
                .pathParam("name", name)
                .when()
                .get(BASE_URL + "/selectname/{name}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành mảng HinhAnh
        Gson gson = new Gson();
        HinhAnh[] hinhAnhs = gson.fromJson(response.asString(), HinhAnh[].class);

        // Kiểm tra kết quả
        assertEquals(true, hinhAnhs.length > 0);
        System.out.println("Images found with name " + name + ": " + hinhAnhs.length);
    }
}

package test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import Entity.HoaDon;
import Entity.MesssageBox;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HoaDon_API_Test {

    private static final String BASE_URL = "http://localhost:8080/hoadon";
    private final Gson gson = new Gson();

    @Test
    public void testSelectAll() {
        // Gửi yêu cầu GET tới endpoint /hoadon/
        Response response = given()
                .when()
                .get(BASE_URL + "/")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành danh sách HoaDon
        HoaDon[] hoaDons = gson.fromJson(response.asString(), HoaDon[].class);

        // Kiểm tra danh sách không rỗng
        assertNotNull(hoaDons);
        assertTrue(hoaDons.length > 0);
    }

    @Test
    public void testSelectByName() {
        String name = "Test Customer"; // Tên cần tìm

        // Gửi yêu cầu GET tới /hoadon/selectname/{name}
        Response response = given()
                .pathParam("name", name)
                .when()
                .get(BASE_URL + "/selectname/{name}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành danh sách HoaDon
        HoaDon[] hoaDons = gson.fromJson(response.asString(), HoaDon[].class);

        // Kiểm tra kết quả
        assertNotNull(hoaDons);
        assertTrue(hoaDons.length > 0);
    }

    @Test
    public void testSelectByID() {
        int id = 1; // ID hợp lệ

        // Gửi yêu cầu GET tới /hoadon/selectID/{id}
        Response response = given()
                .pathParam("id", id)
                .when()
                .get(BASE_URL + "/selectID/{id}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành đối tượng HoaDon
        HoaDon hoaDon = gson.fromJson(response.asString(), HoaDon.class);

        // Kiểm tra kết quả
        assertNotNull(hoaDon);
        assertEquals(id, hoaDon.getMahd());
    }

    @Test
    public void testInsert() {
        // Dữ liệu gửi POST để thêm mới
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("mahd", null);
        requestBody.put("khachHang", null);
        requestBody.put("lstHoaDonChiTiet", List.of());
        requestBody.put("phuongThucThanhToan", null);
        requestBody.put("phuongThucVanChuyen", null);

        // Gửi yêu cầu POST tới /hoadon/insert
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/insert")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành MesssageBox
        MesssageBox msg = gson.fromJson(response.asString(), MesssageBox.class);

        // Kiểm tra kết quả
        assertNotNull(msg);
        assertEquals(101, msg.getMacode());
    }

    @Test
    public void testUpdate() {
        // Dữ liệu gửi POST để cập nhật
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("mahd", 1); // ID hợp lệ cần cập nhật
        requestBody.put("khachHang", null);
        requestBody.put("lstHoaDonChiTiet", List.of());
        requestBody.put("phuongThucThanhToan", null);
        requestBody.put("phuongThucVanChuyen", null);

        // Gửi yêu cầu POST tới /hoadon/update
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/update")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành MesssageBox
        MesssageBox msg = gson.fromJson(response.asString(), MesssageBox.class);

        // Kiểm tra kết quả
        assertNotNull(msg);
        assertEquals(101, msg.getMacode());
    }

    @Test
    public void testDeleteHoaDon() {
        int mahd = 1; // ID hợp lệ cần xóa

        // Gửi yêu cầu GET tới /hoadon/Delete_Hoa_Don/{mahd}
        Response response = given()
                .pathParam("mahd", mahd)
                .when()
                .get(BASE_URL + "/Delete_Hoa_Don/{mahd}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành MesssageBox
        MesssageBox msg = gson.fromJson(response.asString(), MesssageBox.class);

        // Kiểm tra kết quả
        assertNotNull(msg);
        assertEquals(101, msg.getMacode());
    }

    @Test
    public void testSelectDaGiao() {
        // Gửi yêu cầu GET tới /hoadon/selectdagiao
        Response response = given()
                .when()
                .get(BASE_URL + "/selectdagiao")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành danh sách HoaDon
        HoaDon[] hoaDons = gson.fromJson(response.asString(), HoaDon[].class);

        // Kiểm tra danh sách không rỗng
        assertNotNull(hoaDons);
        assertTrue(hoaDons.length > 0);
    }

    @Test
    public void testSelectDangGiao() {
        // Gửi yêu cầu GET tới /hoadon/selectdanggiao
        Response response = given()
                .when()
                .get(BASE_URL + "/selectdanggiao")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Kiểm tra mã trạng thái HTTP
        assertEquals(200, response.getStatusCode());

        // Chuyển đổi JSON trả về thành danh sách HoaDon
        HoaDon[] hoaDons = gson.fromJson(response.asString(), HoaDon[].class);

        // Kiểm tra danh sách không rỗng
        assertNotNull(hoaDons);
        assertTrue(hoaDons.length > 0);
    }
}

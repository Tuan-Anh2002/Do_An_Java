package test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import Entity.GioHang;
import Entity.MesssageBox;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GioHang_API_Tets {

    private static final String BASE_URL = "http://localhost:8080/giohang";

    @Test
    public void testSelectAll() {
        Response response = given()
                .when()
                .get(BASE_URL + "/")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        Gson gson = new Gson();
        GioHang[] gioHangs = gson.fromJson(response.asString(), GioHang[].class);
        System.out.println("Số lượng giỏ hàng: " + gioHangs.length);
    }

    @Test
    public void testInsert() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("mathuoc", 1);
        requestBody.put("tenfileanh", "image.jpg");
        requestBody.put("giaban", 50.0);
        requestBody.put("tenthuoc", "Paracetamol");
        requestBody.put("soluong", 2);
        requestBody.put("khachHang", null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/insert")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        MesssageBox msg = new Gson().fromJson(response.asString(), MesssageBox.class);
        assertEquals(101, msg.getMacode());
        System.out.println(msg.getTenloi());
    }

    @Test
    public void testUpdate() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("magh", 1); // ID giỏ hàng cần update
        requestBody.put("mathuoc", 1);
        requestBody.put("tenfileanh", "updated_image.jpg");
        requestBody.put("giaban", 60.0);
        requestBody.put("tenthuoc", "Ibuprofen");
        requestBody.put("soluong", 3);
        requestBody.put("khachHang", null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/update")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        MesssageBox msg = new Gson().fromJson(response.asString(), MesssageBox.class);
        assertEquals(101, msg.getMacode());
        System.out.println(msg.getTenloi());
    }

    @Test
    public void testDeleteID() {
        int idToDelete = 1; // ID hợp lệ cần xóa

        Response response = given()
                .pathParam("id", idToDelete)
                .when()
                .get(BASE_URL + "/deleteID/{id}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        MesssageBox msg = new Gson().fromJson(response.asString(), MesssageBox.class);
        assertEquals(101, msg.getMacode());
        System.out.println(msg.getTenloi());
    }

    @Test
    public void testDeleteAll() {
        int customerID = 1; // ID khách hàng hợp lệ

        Response response = given()
                .pathParam("MaKH", customerID)
                .when()
                .get(BASE_URL + "/deleteall/{MaKH}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        MesssageBox msg = new Gson().fromJson(response.asString(), MesssageBox.class);
        assertEquals(101, msg.getMacode());
        System.out.println(msg.getTenloi());
    }

    @Test
    public void testSelectByCustomerID() {
        int customerID = 1; // ID khách hàng hợp lệ

        Response response = given()
                .pathParam("MaKH", customerID)
                .when()
                .get(BASE_URL + "/SelectMaKH/{MaKH}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        Gson gson = new Gson();
        GioHang[] gioHangs = gson.fromJson(response.asString(), GioHang[].class);
        System.out.println("Giỏ hàng của khách hàng ID " + customerID + ": " + gioHangs.length);
    }

    @Test
    public void testSelectByCartID() {
        int cartID = 1; // ID giỏ hàng hợp lệ

        Response response = given()
                .pathParam("ID", cartID)
                .when()
                .get(BASE_URL + "/SelectIDGH/{ID}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        Gson gson = new Gson();
        GioHang gioHang = gson.fromJson(response.asString(), GioHang.class);
        System.out.println("Chi tiết giỏ hàng ID " + cartID + ": " + gioHang.getTenthuoc());
    }
}

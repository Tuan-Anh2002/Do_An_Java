package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;

import Entity.GioHang;
import Entity.MesssageBox;
import Hieu_Thuoc_API.GioHang_API;
import Hieu_Thuoc_DAO.IGioHang;

class test_giohang_api {

    private GioHang_API gioHangAPI;

    @Mock
    private IGioHang mockGioHangDAO;

    private Gson gson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gioHangAPI = new GioHang_API();
        gson = new Gson();
    }

    @Test
    void testSelectAll() {
        // Arrange
        List<GioHang> mockList = new ArrayList<>();
        mockList.add(new GioHang(1,2,null,2,"Thuốc A", 100,null));
        mockList.add(new GioHang(2,3,null,2, "Thuốc B", 200,null));

        when(mockGioHangDAO.selectAll()).thenReturn(mockList);

        // Act
        String jsonResult = gioHangAPI.selectall();

        // Assert
        List<GioHang> resultList = gson.fromJson(jsonResult, List.class);
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        verify(mockGioHangDAO, times(1)).selectAll();
    }

    @Test
    void testInsertSuccess() {
        // Arrange
        GioHang newGioHang = new GioHang(2,3,null,2, "Thuốc D", 200,null);
        when(mockGioHangDAO.insert(any(GioHang.class))).thenReturn(true);

        String jsonInput = gson.toJson(newGioHang);

        // Act
        String jsonResult = gioHangAPI.insert(jsonInput);

        // Assert
        MesssageBox msg = gson.fromJson(jsonResult, MesssageBox.class);
        assertEquals(101, msg.getMacode());
        assertEquals("Thêm Vào Giỏ Hàng Thành Công", msg.getTenloi());
        verify(mockGioHangDAO, times(1)).insert(any(GioHang.class));
    }

    @Test
    void testInsertFailure() {
        // Arrange
        GioHang newGioHang = new GioHang(2,3,"abc",2, "Thuốc E", 200,null);
        when(mockGioHangDAO.insert(any(GioHang.class))).thenReturn(false);

        String jsonInput = gson.toJson(newGioHang);

        // Act
        String jsonResult = gioHangAPI.insert(jsonInput);

        // Assert
        MesssageBox msg = gson.fromJson(jsonResult, MesssageBox.class);
        assertEquals(104, msg.getMacode());
        assertEquals("Thêm Vào Giỏ Hàng Thất Bại", msg.getTenloi());
        verify(mockGioHangDAO, times(1)).insert(any(GioHang.class));
    }

    @Test
    void testDeleteByIdSuccess() {
        // Arrange
//    	 List<GioHang> mockList = new ArrayList<>();
//         mockList.add(new GioHang(1,2,null,2,"Thuốc A", 100,null));
//         mockList.add(new GioHang(2,3,null,2, "Thuốc B", 200,null));
        GioHang existingGioHang = new GioHang(1, 2, "abc", 2, "XMen", 200, null);
        when(mockGioHangDAO.selectIDGH(2)).thenReturn(existingGioHang); // Mock trả về đối tượng cần xóa
        when(mockGioHangDAO.deleteID(existingGioHang)).thenReturn(true); // Mock thành công khi xóa

        // Act
        String jsonResult = gioHangAPI.DeleteID(1); // Gọi API với ID

        // Assert
        MesssageBox msg = gson.fromJson(jsonResult, MesssageBox.class); // Chuyển đổi kết quả trả về
        assertEquals(101, msg.getMacode()); // Kiểm tra mã trả về
        assertEquals("Xóa Thành Công", msg.getTenloi()); // Kiểm tra thông báo trả về
        verify(mockGioHangDAO, times(1)).selectIDGH(1); // Đảm bảo phương thức selectIDGH được gọi
        verify(mockGioHangDAO, times(1)).deleteID(existingGioHang); // Đảm bảo phương thức deleteID được gọi
    }


    @Test
    void testDeleteByIdFailure() {
        // Arrange
        GioHang existingGioHang = new GioHang(1,1,null,2, "Thuốc G", 200,null);
        when(mockGioHangDAO.selectIDGH(1)).thenReturn(existingGioHang);
        when(mockGioHangDAO.deleteID(existingGioHang)).thenReturn(false);

        // Act
        String jsonResult = gioHangAPI.DeleteID(1);

        // Assert
        MesssageBox msg = gson.fromJson(jsonResult, MesssageBox.class);
        assertEquals(104, msg.getMacode());
        assertEquals("Xóa Thất Bại", msg.getTenloi());
        verify(mockGioHangDAO, times(1)).deleteID(existingGioHang);
    }
}

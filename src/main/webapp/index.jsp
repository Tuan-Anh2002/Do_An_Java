<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:url value="/" var="rootpath"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Tuan Anh</h1>
<a href="${rootpath}rest/KhachHang">Khach Hang ||  </a>
<a href="${rootpath}rest/hinhanh">  Hinh Anh  ||  </a>
<a href="${rootpath}rest/hoadon">  Hoa Don  ||  </a>
<a href="${rootpath}rest/HoaDonChiTiet">  Hoa Don Chi Tiet  ||  </a>
<a href="${rootpath}rest/loaithuoc">  Loai Thuoc  ||  </a>
<a href="${rootpath}rest/phuongthucvanchuyen">  Phuong Thuc Van Chuyen  ||  </a>
<a href="${rootpath}rest/phuongthucthanhtoan">  Phuong Thuc Thanh Toan  ||  </a>
<a href="${rootpath}rest/thuoc">  Thuoc  ||  </a>
</body>
</html>
package Hieu_Thuoc_API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Entity.GioHang;
import Entity.KhachHang;
import Entity.MesssageBox;
import Hieu_Thuoc_DAO.IGioHang;
import Hieu_Thuoc_DAO.IplmGioHang;

@Path("/giohang")
public class GioHang_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectall() {
		IGioHang gh=new IplmGioHang();
		List<GioHang> lstgh=gh.selectAll();
		for(int i=0;i<lstgh.size();i++) {
			lstgh.get(i).setKhachHang(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lstgh);
		return json;
	}
	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(String jsonOB) {
		Gson gson=new Gson();
		GioHang gh=gson.fromJson(jsonOB, GioHang.class);
		IGioHang igh=new IplmGioHang();
		boolean isOK=igh.insert(gh);
		MesssageBox msg;
		if(isOK) {
			msg=new MesssageBox(101, "Thêm Vào Giỏ Hàng Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Vào Giỏ Hàng Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String json) {
		Gson gson=new Gson();
		GioHang gh=gson.fromJson(json, GioHang.class);
		IGioHang igh=new IplmGioHang();
		boolean isOk=igh.update(gh);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Cập Nhật Thành Công");
		}else {
			msg=new MesssageBox(104, "Cập Nhật Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/deleteID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String DeleteID(@PathParam(value = "id")Integer ID) {
		IGioHang igh=new IplmGioHang();
		GioHang gh=igh.selectIDGH(ID);
		System.out.println("xoa gio hang");
		System.out.println(gh.getTenthuoc());
		System.out.println(gh.getKhachHang());
		boolean isok=igh.deleteID(gh);
		Gson gson=new Gson();
		MesssageBox msg;
		if(isok) {
			msg=new MesssageBox(101, "Xóa Thành Công");
			System.out.println("xoa thanh cong");
		}else {
			msg=new MesssageBox(104, "Xóa Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/deleteall/{MaKH}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAll(@PathParam(value = "MaKH")Integer MaKH) {
		IGioHang igh=new IplmGioHang();
		boolean isok=igh.deleteALL(MaKH);
		Gson gson=new Gson();
		MesssageBox msg;
		if(isok) {
			msg=new MesssageBox(101, "Xóa Thành Công");
		}else {
			msg=new MesssageBox(104, "Xóa Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/SelectMaKH/{MaKH}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "MaKH")Integer ID) {
		IGioHang gh=new IplmGioHang();
		List<GioHang> lstgh=gh.SelectID(ID);
		for(int i=0;i<lstgh.size();i++) {
			lstgh.get(i).setKhachHang(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lstgh);
		return json;
	}
	@Path("/SelectIDGH/{ID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectIDGH(@PathParam(value = "ID")Integer ID) {
		IGioHang Igh=new IplmGioHang();
		GioHang gh=new GioHang();
		gh=Igh.selectIDGH(ID);
		gh.setKhachHang(null);
		Gson gson=new Gson();
		String json=gson.toJson(gh);
		return json;
	}
}

package Hieu_Thuoc_API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Entity.HinhAnh;
import Entity.HoaDonChiTiet;
import Entity.LoaiThuoc;
import Entity.MesssageBox;
import Entity.Thuoc;
import Hieu_Thuoc_DAO.IThuoc;
import Hieu_Thuoc_DAO.IplmThuoc;

@Path("/thuoc")
public class Thuoc_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.SelectAll();
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
//		for (Thuoc t : lst) {
//			System.out.println(t.toString());
//		}
//		return "ahii";
	}
	@Path("/SanPhamChucNang")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectsanphamchucnang() {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.SelectSanPhamChucNang();
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectName(@PathParam(value = "name") String name) {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.SelectName(name);
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selecttenthuoc/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Select_Ten_Thuoc(@PathParam(value = "name") String name) {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.Select_Ten_Thuoc(name);
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		IThuoc ithuoc=new IplmThuoc();
		Thuoc thuoc=ithuoc.SelectID(ID);
		thuoc.setLstHoaDonChiTiet(null);
		LoaiThuoc lt=thuoc.getLoaiThuoc();
		lt.setLstThuoc(null);
		List<HinhAnh> lstha=thuoc.getLstHinhAnh();
		for(int i=0;i<lstha.size();i++) {
			lstha.get(i).setHAThuoc(null);
		}
		Gson gson=new Gson();
		return gson.toJson(thuoc);
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		Gson gson=new Gson();
		Thuoc thuoc=gson.fromJson(jsonOB, Thuoc.class);
		IThuoc ithuoc=new IplmThuoc();
		boolean isOk=ithuoc.Update(thuoc);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Cập Nhật Thành Công");
		}else {
			msg=new MesssageBox(104, "Cập Nhật Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Insert(String jsonOB) {
		Gson gson=new Gson();
		Thuoc thuoc=gson.fromJson(jsonOB, Thuoc.class);
		IThuoc ithuoc=new IplmThuoc();
		Integer isOk=ithuoc.Insert(thuoc);
		MesssageBox msg;
		if(isOk!=-1) {
			msg=new MesssageBox(isOk, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(-1, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam(value = "id")Integer ID) {
		Gson gson=new Gson();
		IThuoc ithuoc=new IplmThuoc();
		boolean isOk=ithuoc.Delete(ID);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Xóa Thuốc Thành Công");
		}else {
			msg=new MesssageBox(104, "Xóa Thuốc Thát Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/selectmathuoc/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectMaThuoc(@PathParam(value = "id")Integer ID) {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.SelectMT(ID);
		for (int i = 0; i < lst.size(); i++) {
			
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectduocmypham")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectDuocMyPham(Integer ID) {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.SelectDuocMyPham();
		for (int i = 0; i < lst.size(); i++) {
			
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectthuoc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectthuoc() {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.selectthuoc();
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectthuocnghiban")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectthuocnghiban() {
		IThuoc ithuoc=new IplmThuoc();
		List<Thuoc> lst=ithuoc.selectnghiban();
		for (int i = 0; i < lst.size(); i++) {
			List<HinhAnh>lstha =lst.get(i).getLstHinhAnh();
			for (int j = 0; j < lstha.size(); j++) {
				lstha.get(j).setHAThuoc(null);
			}
			lst.get(i).setLstHoaDonChiTiet(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
}

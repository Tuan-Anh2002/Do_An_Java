package Hieu_Thuoc_API;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Entity.KhachHang;
import Hieu_Thuoc_DAO.IKhachHang;
import Hieu_Thuoc_DAO.IplmKhachHang;
import Entity.*;
@Path("/KhachHang")
public class KhachHang_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {	
		IKhachHang ikh=new IplmKhachHang();
		List<KhachHang> lst=ikh.SelectAll();
		for(int i=0;i<lst.size();i++) {
			List<GioHang> lstgh=lst.get(i).getLstGioHang();
			for(int j=0;j<lstgh.size();j++) {
				lstgh.get(j).setKhachHang(null);
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		Gson gson=new Gson();
		KhachHang kh=gson.fromJson(jsonOB, KhachHang.class);
		IKhachHang ikh=new IplmKhachHang();
		boolean isOk=ikh.Update(kh);
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
	public String insert(String jsonOB) {
		Gson gson=new Gson();
		KhachHang kh=gson.fromJson(jsonOB, KhachHang.class);
		IKhachHang ikh=new IplmKhachHang();
		boolean isOk=ikh.Insert(kh);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		IKhachHang ikh=new IplmKhachHang();
		KhachHang kh=ikh.SelectID(ID);
		kh.setLstGioHang(null);
		Gson gson=new Gson();
		return gson.toJson(kh);
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectName(@PathParam(value = "name") String name) {
		IKhachHang ikh=new IplmKhachHang();
		List<KhachHang> lst=ikh.SelectName(name);
		for(int i=0;i<lst.size();i++) {
			List<GioHang> lstgh=lst.get(i).getLstGioHang();
			for(int j=0;j<lstgh.size();j++) {
				lstgh.get(j).setKhachHang(null);
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectuserpass/{user}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectUserPass(@PathParam(value = "user")String User,@PathParam(value = "password")String password) {
		IKhachHang ikh=new IplmKhachHang();
		KhachHang kh=ikh.SelectUserPass(User, password);
		Gson gson=new Gson();
		String json=gson.toJson(kh);
		return json;
	}
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login(String jsonOB) {
		Gson gson=new Gson();
		KhachHang kh=gson.fromJson(jsonOB, KhachHang.class);
		IKhachHang ikh=new IplmKhachHang();
		System.out.println(kh.getTaikhoan()+"     "+kh.getMatkhau() + kh.getLstGioHang());
		Integer isOk=ikh.login(kh.getTaikhoan(),kh.getMatkhau());
		MesssageBox msg;
		if(isOk==-2) {
			msg=new MesssageBox(104, "Đăng Nhập Thất  Bại");
		}else if(isOk==-1) {
			msg=new MesssageBox(104, "Sai Tài Khoản Hoặc Mật Khẩu");
		}else if(isOk==0) {
			msg=new MesssageBox(104, "Tài Khoản Này Đã Bị Khóa");
		}else {
			msg=new MesssageBox(isOk, "Đăng Nhập Thành Công");
		}
		return gson.toJson(msg);
	}
	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam(value = "id")Integer ID) {
		Gson gson=new Gson();
		IKhachHang ikh=new IplmKhachHang();
		boolean isOk=ikh.Delete(ID);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Xóa Khách Hàng Thành Công");
		}else {
			msg=new MesssageBox(104, "Xóa Khách Hàng Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/selecthoatdong")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectHoatDong() {
		IKhachHang ikh=new IplmKhachHang();
		List<KhachHang> lst=ikh.SelectHoatDong();
		for(int i=0;i<lst.size();i++) {
			List<GioHang> lstgh=lst.get(i).getLstGioHang();
			for(int j=0;j<lstgh.size();j++) {
				lstgh.get(j).setKhachHang(null);
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectdung")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Selectdung() {
		IKhachHang ikh=new IplmKhachHang();
		List<KhachHang> lst=ikh.SelectDung();
		for(int i=0;i<lst.size();i++) {
			List<GioHang> lstgh=lst.get(i).getLstGioHang();
			for(int j=0;j<lstgh.size();j++) {
				lstgh.get(j).setKhachHang(null);
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
}

package Hieu_Thuoc_API;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Entity.HinhAnh;
import Entity.MesssageBox;
import Entity.Thuoc;
import Hieu_Thuoc_DAO.IHinhAnh;
import Hieu_Thuoc_DAO.IplmHinhAnh;

@Path("/hinhanh")
public class HinhAnh_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IHinhAnh iha=new IplmHinhAnh();
		List<HinhAnh> lst =iha.SelectAll();
		for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setHAThuoc(null);
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
		
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOb) {
		Gson gson=new Gson();
		HinhAnh ha=gson.fromJson(jsonOb, HinhAnh.class);
		IHinhAnh iha=new IplmHinhAnh();
		boolean isOk=iha.Update(ha);
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
	public String Insert(String jsonOb) {
		Gson gson=new Gson();
		HinhAnh ha=gson.fromJson(jsonOb, HinhAnh.class);
		IHinhAnh iha=new IplmHinhAnh();
		boolean isOk=iha.Insert(ha);
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
		IHinhAnh iha=new IplmHinhAnh();
		HinhAnh ha=iha.SelectID(ID);
		Thuoc t=ha.getHAThuoc();
		t.setLstHinhAnh(null);
		t.setLoaiThuoc(null);
		t.setLstHoaDonChiTiet(null);
		Gson gson=new Gson();
		return gson.toJson(ha);
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "name") String name) {
		IHinhAnh iha=new IplmHinhAnh();
		List<HinhAnh> lst=iha.SelectName(name);
		for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setHAThuoc(null);
		}
		Gson gson=new Gson();
		return gson.toJson(lst);
	}
}

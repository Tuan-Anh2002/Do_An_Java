package Hieu_Thuoc_API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Entity.MesssageBox;
import Entity.PhuongThucVanChuyen;
import Hieu_Thuoc_DAO.IPhuongThucVanChuyen;
import Hieu_Thuoc_DAO.IplmPhuongThucVanChuyen;

@Path("/phuongthucvanchuyen")
public class PhuongThucVanChuyen_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		List<PhuongThucVanChuyen> lst=iptvc.SelectAll();
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		PhuongThucVanChuyen ptvc=iptvc.SelectID(ID);
		Gson gson=new Gson();
		return gson.toJson(ptvc);
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectName(@PathParam(value = "name") String name) {
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		List<PhuongThucVanChuyen> lst=iptvc.SelectName(name);
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		Gson gson=new Gson();
		PhuongThucVanChuyen ptvc=gson.fromJson(jsonOB, PhuongThucVanChuyen.class);
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		boolean isOk=iptvc.Update(ptvc);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Câp Nhật Thành Công");
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
		PhuongThucVanChuyen ptvc=gson.fromJson(jsonOB, PhuongThucVanChuyen.class);
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		boolean isOk=iptvc.Insert(ptvc);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/deletePTVC/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam(value = "id")Integer ID) {
		Gson gson=new Gson();
		IPhuongThucVanChuyen iptvc=new IplmPhuongThucVanChuyen();
		boolean isOk=iptvc.DeletePTVC(ID);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Xóa Phương Thức Vận Chuyển Thành Công");
		}else {
			msg=new MesssageBox(104, "Xóa Phương Thức Vận Chuyển Thất Bại");
		}
		return gson.toJson(msg);
	}
}

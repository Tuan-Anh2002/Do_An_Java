package Hieu_Thuoc_API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Entity.LoaiThuoc;
import Entity.MesssageBox;
import Hieu_Thuoc_DAO.ILoaiThuoc;
import Hieu_Thuoc_DAO.IplmLoaiThuoc;

@Path("/loaithuoc")
public class LoaiThuoc_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		ILoaiThuoc ilt=new IplmLoaiThuoc();
		List<LoaiThuoc> lst=ilt.SelectAll();
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		ILoaiThuoc ilt=new IplmLoaiThuoc();
		LoaiThuoc lt=ilt.SelectID(ID);
		Gson gson=new Gson();
		return gson.toJson(lt);
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "name") String name) {
		ILoaiThuoc ilt=new IplmLoaiThuoc();
		List<LoaiThuoc> lst=ilt.SelectName(name);
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		ILoaiThuoc ilt=new IplmLoaiThuoc();
		Gson gson=new Gson();
		LoaiThuoc lt=gson.fromJson(jsonOB, LoaiThuoc.class);
		boolean isOk=ilt.Update(lt);
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
		ILoaiThuoc ilt=new IplmLoaiThuoc();
		Gson gson=new Gson();
		LoaiThuoc lt=gson.fromJson(jsonOB, LoaiThuoc.class);
		boolean isOk=ilt.Insert(lt);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
}

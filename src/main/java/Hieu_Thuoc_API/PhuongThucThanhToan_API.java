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
import Entity.PhuongThucThanhToan;
import Hieu_Thuoc_DAO.IPhuongThucThanhToan;
import Hieu_Thuoc_DAO.IplmPhuongThucThanhToan;

@Path("/phuongthucthanhtoan")
public class PhuongThucThanhToan_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		List<PhuongThucThanhToan> lst=ipttt.SelectAll();
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		PhuongThucThanhToan pttt=ipttt.SelectID(ID);
		Gson gson=new Gson();
		String json=gson.toJson(pttt);
		return json;
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "name") String name) {
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		List<PhuongThucThanhToan> pttt=ipttt.SelectName(name);
		Gson gson=new Gson();
		String json=gson.toJson(pttt);
		return json;
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		Gson gson=new Gson();
		PhuongThucThanhToan pttt=gson.fromJson(jsonOB, PhuongThucThanhToan.class);
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		boolean isOk=ipttt.Update(pttt);
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
		PhuongThucThanhToan pttt=gson.fromJson(jsonOB, PhuongThucThanhToan.class);
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		boolean isOk=ipttt.Insert(pttt);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam(value = "id")Integer ID) {
		IPhuongThucThanhToan ipttt=new IplmPhuongThucThanhToan();
		boolean isOk=ipttt.DeletePTTT(ID);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Xóa Thành Công");
		}else {
			msg=new MesssageBox(104, "Xóa Thất Bại");
		}
		Gson gson=new Gson();
		return gson.toJson(msg);
	}
}

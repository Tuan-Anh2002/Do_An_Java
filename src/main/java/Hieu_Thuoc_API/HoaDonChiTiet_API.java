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
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import Entity.MesssageBox;
import Entity.Thuoc;
import Hieu_Thuoc_DAO.IHoaDonChiTiet;
import Hieu_Thuoc_DAO.IplmHoaDonChiTiet;

@Path("/HoaDonChiTiet")
public class HoaDonChiTiet_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IHoaDonChiTiet ihdct=new IplmHoaDonChiTiet();
		List<HoaDonChiTiet> lst=ihdct.SelectAll();
		for (int i = 0; i < lst.size(); i++) {
			HoaDon hd=lst.get(i).getHoaDon();
			hd.setLstHoaDonChiTiet(null);
			hd.setKhachHang(null);
			hd.setPhuongThucThanhToan(null);
			hd.setPhuongThucVanChuyen(null);
			Thuoc thuoc=lst.get(i).getThuoc();
			thuoc.setLstHoaDonChiTiet(null);
			thuoc.setLoaiThuoc(null);
			thuoc.setLstHinhAnh(null);
		}
		Gson gson=new Gson();
		String json =gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectID(@PathParam(value = "id") Integer ID) {
		IHoaDonChiTiet ihdct=new IplmHoaDonChiTiet();
		HoaDonChiTiet hdct=ihdct.SelectID(ID);
		HoaDon hd=hdct.getHoaDon();
		hd.setLstHoaDonChiTiet(null);
		hd.setKhachHang(null);
		hd.setPhuongThucThanhToan(null);
		hd.setPhuongThucVanChuyen(null);
		Thuoc thuoc=hdct.getThuoc();
		thuoc.setLstHoaDonChiTiet(null);
		thuoc.setLoaiThuoc(null);
		thuoc.setLstHinhAnh(null);
		System.out.println("tuananh");
		Gson gson=new Gson();
		return gson.toJson(hdct);
	}
	@Path("/SelectMaHD/{mahd}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectMaHD(@PathParam(value = "mahd")Integer ID) {
		IHoaDonChiTiet ihdct=new IplmHoaDonChiTiet();
		List<HoaDonChiTiet> lst=ihdct.selectMaHD(ID);
		for (int i = 0; i < lst.size(); i++) {
			HoaDon hd=lst.get(i).getHoaDon();
			hd.setLstHoaDonChiTiet(null);
			KhachHang kh=hd.getKhachHang();
			kh.setLstHoaDon(null);
			List<GioHang> gh=kh.getLstGioHang();
			for(int j=0;j<gh.size();j++) {
				gh.get(j).setKhachHang(null);
			}
			hd.setPhuongThucThanhToan(null);
			hd.setPhuongThucVanChuyen(null);
			Thuoc thuoc=lst.get(i).getThuoc();
			thuoc.setLstHoaDonChiTiet(null);
			thuoc.setLoaiThuoc(null);
			thuoc.setLstHinhAnh(null);
		}
		for (HoaDonChiTiet hd : lst) {
			System.out.println(hd.getMahd());
		}
		Gson gson=new Gson();
		String json =gson.toJson(lst);
		return json;
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		IHoaDonChiTiet ihdct=new IplmHoaDonChiTiet();
		Gson gson=new Gson();
		HoaDonChiTiet hdct=gson.fromJson(jsonOB, HoaDonChiTiet.class);
		boolean isOk=ihdct.Updata(hdct);
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
		IHoaDonChiTiet ihdct=new IplmHoaDonChiTiet();
		Gson gson=new Gson();
		HoaDonChiTiet hdct=gson.fromJson(jsonOB, HoaDonChiTiet.class);
		boolean isOk=ihdct.Insert(hdct);
		MesssageBox msg;
		if(isOk) {
			msg=new MesssageBox(101, "Cập Nhật Thành Công");
		}else {
			msg=new MesssageBox(104, "Cập Nhật Thất Bại");
		}
		return gson.toJson(msg);
	}
}

package Hieu_Thuoc_API;

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

import Entity.GioHang;
import Entity.HinhAnh;
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import Entity.MesssageBox;
import Entity.PhuongThucThanhToan;
import Entity.PhuongThucVanChuyen;
import Entity.Thuoc;
import Hieu_Thuoc_DAO.IHoaDon;
import Hieu_Thuoc_DAO.IplmHoaDon;

@Path("/hoadon")
public class HoaDon_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectAll() {
		IHoaDon ihd=new IplmHoaDon();
		List<HoaDon> lst=ihd.SelectAll();
		for (int i = 0; i < lst.size(); i++) {
			List<HoaDonChiTiet> hdct=lst.get(i).getLstHoaDonChiTiet();
			for (int j = 0; j < hdct.size(); j++) {
				hdct.get(j).setHoaDon(null);
//				hdct.get(j).setThuoc(null);
				Thuoc thuoc=hdct.get(j).getThuoc();
				thuoc.setLstHoaDonChiTiet(null);
				thuoc.setLoaiThuoc(null);
				thuoc.setLstHinhAnh(null);
				
			}
			KhachHang kh=lst.get(i).getKhachHang();
			kh.setLstHoaDon(null);
			List<GioHang> gh=kh.getLstGioHang();
			for(int j=0;j<gh.size();j++) {
				gh.get(j).setKhachHang(null);
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectname/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectName(@PathParam(value = "name") String name) {
		IHoaDon ihd=new IplmHoaDon();
		List<HoaDon> lst=ihd.SelectName(name);
		for (int i = 0; i < lst.size(); i++) {
			List<HoaDonChiTiet> hdct=lst.get(i).getLstHoaDonChiTiet();
			for (int j = 0; j < hdct.size(); j++) {
				hdct.get(j).setHoaDon(null);
				hdct.get(j).setThuoc(null);
			}
			lst.get(i).setKhachHang(null);
			
		}
		Gson gson=new Gson();
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectName(@PathParam(value = "id") Integer ID) {
		IHoaDon ihd=new IplmHoaDon();
		HoaDon hd=ihd.SelectID(ID);
		PhuongThucThanhToan pttt=hd.getPhuongThucThanhToan();
		pttt.setLstHoaDon(null);
		PhuongThucVanChuyen ptvc=hd.getPhuongThucVanChuyen();
		ptvc.setLstHoaDon(null);
		KhachHang kh=hd.getKhachHang();
		kh.setLstHoaDon(null);
		List<GioHang> gh=kh.getLstGioHang();
		for(int i=0;i<gh.size();i++) {
			gh.get(i).setKhachHang(null);
		}
		hd.setKhachHang(kh);
		hd.setPhuongThucVanChuyen(ptvc);
		hd.setPhuongThucThanhToan(pttt);
		List<HoaDonChiTiet> lsthdct=hd.getLstHoaDonChiTiet();
		for (int i = 0; i < lsthdct.size(); i++) {
			lsthdct.get(i).setHoaDon(null);
			lsthdct.get(i).setThuoc(null);
		}
		
		Gson gson=new Gson();
		return gson.toJson(hd);
	}
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String Update(String jsonOB) {
		IHoaDon ihd=new IplmHoaDon();
		Gson gson=new Gson();
		HoaDon hd=gson.fromJson(jsonOB, HoaDon.class);
		System.out.println(hd.getMahd());
		System.out.println(hd.getKhachHang());
		boolean isOk=ihd.Update(hd);
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
		IHoaDon ihd=new IplmHoaDon();
		Gson gson=new Gson();
		HoaDon hd=gson.fromJson(jsonOB, HoaDon.class);
		Integer isOk=ihd.Insert(hd);
		MesssageBox msg;
		if(isOk!=-1) {
			msg=new MesssageBox(isOk, "Thêm Mới Thành Công");
		}else {
			msg=new MesssageBox(104, "Thêm Mới Thất Bại");
		}
		return gson.toJson(msg);
	}
	@Path("/selectdagiao")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectDaGiao() {
		IHoaDon ihd=new IplmHoaDon();
		Gson gson=new Gson();
		List<HoaDon> lst=ihd.SelectDaGiao();
		for (int i = 0; i < lst.size(); i++) {
			List<HoaDonChiTiet> hdct=lst.get(i).getLstHoaDonChiTiet();
			for (int j = 0; j < hdct.size(); j++) {
				hdct.get(j).setHoaDon(null);
				hdct.get(j).setThuoc(null);
			}
			KhachHang kh=lst.get(i).getKhachHang();
			kh.setLstHoaDon(null);
			List<GioHang> gh=kh.getLstGioHang();
			for(int j=0;j<gh.size();j++) {
				gh.get(j).setKhachHang(null);
			}
		}
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/selectdanggiao")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String SelectDangGiao() {
		IHoaDon ihd=new IplmHoaDon();
		Gson gson=new Gson();
		List<HoaDon> lst=ihd.SelectDangGiao();
		for (int i = 0; i < lst.size(); i++) {
			List<HoaDonChiTiet> hdct=lst.get(i).getLstHoaDonChiTiet();
			for (int j = 0; j < hdct.size(); j++) {
				hdct.get(j).setHoaDon(null);
				hdct.get(j).setThuoc(null);
			}
			KhachHang kh=lst.get(i).getKhachHang();
			kh.setLstHoaDon(null);
			List<GioHang> gh=kh.getLstGioHang();
			for(int j=0;j<gh.size();j++) {
				gh.get(j).setKhachHang(null);
			}
			
		}
		String json=gson.toJson(lst);
		return json;
	}
	@Path("/Delete_Hoa_Don/{mahd}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete_HoaDon(@PathParam(value = "mahd") Integer mahd) {
		IHoaDon ihd=new IplmHoaDon();
		HoaDon hd=ihd.SelectID(mahd);
		boolean isOk=ihd.Delete_HoaDon(hd);
		MesssageBox msg=new MesssageBox();
		if(isOk) {
			msg=new MesssageBox(101, "Xóa Thành Công");
			System.out.println("xoa thanh cong");
		}else {
			msg=new MesssageBox(104, "Xóa Thất Bại");
		}
		Gson gson=new Gson();
		return gson.toJson(msg);
	}
	@Path("/capnhat/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String capnhat() {
		IHoaDon ihd=new IplmHoaDon();
		List<HoaDon> lst=ihd.CapNhat();
		Gson gson=new Gson();
		for(int i=0;i<lst.size();i++) {
			List<HoaDonChiTiet> hdct=lst.get(i).getLstHoaDonChiTiet();
			for (int j = 0; j < hdct.size(); j++) {
				hdct.get(j).setHoaDon(null);
				hdct.get(j).setThuoc(null);
			}
			KhachHang kh=lst.get(i).getKhachHang();
			kh.setLstHoaDon(null);
			List<GioHang> gh=kh.getLstGioHang();
			for(int j=0;j<gh.size();j++) {
				gh.get(j).setKhachHang(null);
			}
			lst.get(i).setThanhtoan("Thanh Toán VNPay");
			
		}
		for (HoaDon hd : lst) {
			String h=gson.toJson(hd);
			Update(h);
		}
		
		return "thanh cong";
	}
}

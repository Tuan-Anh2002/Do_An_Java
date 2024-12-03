package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.HoaDonChiTiet;

public interface IHoaDonChiTiet {
	List<HoaDonChiTiet> SelectAll();
	public boolean Updata(HoaDonChiTiet hdct);
	public boolean Insert(HoaDonChiTiet hdct);
	HoaDonChiTiet SelectID(Integer ID);
	List<HoaDonChiTiet> selectMaHD(Integer ID);
}

package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.GioHang;
import Entity.HoaDon;

public interface IHoaDon {
	List<HoaDon> SelectAll();
	public boolean Update(HoaDon hd);
	public Integer Insert(HoaDon hd);
	HoaDon SelectID(Integer ID);
	List<HoaDon> SelectName(String name);
	List<HoaDon> SelectDaGiao();
	List<HoaDon> SelectDangGiao();
	public boolean Delete_HoaDon(HoaDon hd);
	List<HoaDon> CapNhat();
}

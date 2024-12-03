package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.LoaiThuoc;

public interface ILoaiThuoc {
	List<LoaiThuoc> SelectAll();
	public boolean Update(LoaiThuoc lt);
	public boolean Insert(LoaiThuoc lt);
	List<LoaiThuoc> SelectName(String name);
	LoaiThuoc SelectID(Integer ID);
}

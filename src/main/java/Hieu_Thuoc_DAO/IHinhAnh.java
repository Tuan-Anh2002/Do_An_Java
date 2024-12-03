package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.HinhAnh;

public interface IHinhAnh {
	List<HinhAnh> SelectAll();
	public boolean Update(HinhAnh ha);
	public boolean Insert(HinhAnh ha);
	List<HinhAnh> SelectName(String name);
	HinhAnh SelectID(Integer ID);
}

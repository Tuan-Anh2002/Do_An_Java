package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.Thuoc;



public interface IThuoc {
	List<Thuoc> SelectAll();
	public boolean Update(Thuoc t);
	public Integer Insert(Thuoc t);
	List<Thuoc> SelectName(String name);
	Thuoc SelectID(Integer ID);
	List<Thuoc> SelectSanPhamChucNang();
	List<Thuoc> SelectMT(Integer ID);
	List<Thuoc> SelectDuocMyPham();
	public boolean Delete(Integer ID);
	List<Thuoc> selectthuoc();
	List<Thuoc> selectnghiban();
	List<Thuoc> Select_Ten_Thuoc(String name);
}

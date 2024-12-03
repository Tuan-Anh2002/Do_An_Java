package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.GioHang;

public interface IGioHang {
	List<GioHang> selectAll();
	public boolean update(GioHang gh);
	public boolean insert(GioHang gh);
	public boolean deleteALL(Integer MaKH);
	public boolean deleteID(GioHang giohang);
	public GioHang selectID(Integer mathuoc);
	public GioHang selectIDGH(Integer IDGH);
	List<GioHang> SelectID(Integer ID);
}

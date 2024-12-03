package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.PhuongThucThanhToan;

public interface IPhuongThucThanhToan {
	List<PhuongThucThanhToan> SelectAll();
	public boolean Update(PhuongThucThanhToan pttt);
	public boolean Insert(PhuongThucThanhToan pttt);
	List<PhuongThucThanhToan> SelectName(String name);
	PhuongThucThanhToan SelectID(Integer ID);
	public boolean DeletePTTT(Integer ID);
}

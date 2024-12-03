package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.PhuongThucVanChuyen;

public interface IPhuongThucVanChuyen {
	List<PhuongThucVanChuyen> SelectAll();
	public boolean Update(PhuongThucVanChuyen ptvc);
	public boolean  Insert(PhuongThucVanChuyen ptvc);
	List<PhuongThucVanChuyen> SelectName(String name);
	PhuongThucVanChuyen SelectID(Integer ID);
	public boolean DeletePTVC(Integer ID);
}

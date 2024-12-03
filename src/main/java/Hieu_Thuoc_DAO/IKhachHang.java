
package Hieu_Thuoc_DAO;

import java.util.List;

import Entity.KhachHang;

public interface IKhachHang {
	List<KhachHang> SelectAll();
	public boolean Update(KhachHang kh);
	public boolean Insert(KhachHang kh);
	KhachHang SelectID(Integer ID);
	List<KhachHang> SelectName(String name);
	KhachHang SelectUserPass(String user,String password);
	public Integer login(String user,String password);
	public boolean Delete(Integer ID);
	List<KhachHang> SelectHoatDong();
	List<KhachHang> SelectDung();
}

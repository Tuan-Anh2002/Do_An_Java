package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.GioHang;
import Entity.HinhAnh;
import Entity.Thuoc;

public class IplmGioHang implements IGioHang {
	SessionFactory sf;

	public IplmGioHang() {
		sf = Hibernate_Utils.getSessionFactory();
	}

	@Override
	public List<GioHang> selectAll() {
		Session ss = null;
		List<GioHang> lst = new ArrayList<>();
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			lst = ss.createNamedQuery("selectgiohang").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll giohang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean update(GioHang gh) {
		Session ss = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			ss.update(gh);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update giohang: " + e);
			return false;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean insert(GioHang gh) {
		Session ss = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			ss.save(gh);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi insert: " + e);
			return false;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean deleteALL(Integer MaKH) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss = sf.openSession();
			ss.beginTransaction();
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl
			ss.createQuery("delete from GioHang where makh=:MaKH").setParameter("MaKH", MaKH).executeUpdate();
			// xác nhận giao dịch/thay đổi trong csdl
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi Xóa dữ liệu   " + e.getMessage());
			ss.getTransaction().rollback(); // hoàn tác giao dịch/lệnh
			return false;
		} finally {
			if (ss != null) {
				ss.close();

			}
		}
		return true;
	}

	@Override
	public boolean deleteID(GioHang giohang) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss = sf.openSession();
			ss.beginTransaction();
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl

			ss.delete(giohang);
			// xác nhận giao dịch/thay đổi trong csdl
			ss.getTransaction().commit();

			ss.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi Xóa dữ liệu   " + e.getMessage());
			ss.getTransaction().rollback(); // hoàn tác giao dịch/lệnh
			return false;
		} finally {
			if (ss != null) {
				ss.close();

			}
		}
		return true;
	}

	@Override
	public GioHang selectID(Integer mathuoc) {
		Session ss = null;
		GioHang gh = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			gh = (GioHang) ss.createQuery("from GioHang where mathuoc=:name").setParameter("name", mathuoc)
					.uniqueResult();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname giohang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return gh;
	}

	@Override
	public List<GioHang> SelectID(Integer ID) {
		Session ss = null;
		List<GioHang> lst = new ArrayList<>();
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			lst = ss.createQuery("from GioHang where makh=:makh").setParameter("makh", ID).list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll giohang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public GioHang selectIDGH(Integer IDGH) {
		Session ss = null;
		GioHang gh = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			gh = (GioHang) ss.createQuery("from GioHang where magh=:ID").setParameter("ID", IDGH).uniqueResult();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname giohang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return gh;
	}

}

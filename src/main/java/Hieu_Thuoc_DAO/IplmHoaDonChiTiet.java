package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.HoaDonChiTiet;
import Entity.KhachHang;

public class IplmHoaDonChiTiet implements IHoaDonChiTiet {

	SessionFactory sf;
	public IplmHoaDonChiTiet() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	
	@Override
	public List<HoaDonChiTiet> SelectAll() {
		Session ss=null;
		List<HoaDonChiTiet> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_HoaDonChiTiet").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll HoaDonChiTiet: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Updata(HoaDonChiTiet hdct) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(hdct);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update HoaDonChiTiet: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(HoaDonChiTiet hdct) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(hdct);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update HoaDonChiTiet: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public HoaDonChiTiet SelectID(Integer ID) {
		Session ss=null;
		HoaDonChiTiet hdct=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			hdct=ss.get(HoaDonChiTiet.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update HoaDonChiTiet: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return hdct;
	}

	@Override
	public List<HoaDonChiTiet> selectMaHD(Integer ID) {
		Session ss = null;

		List<HoaDonChiTiet> lst = new ArrayList();
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			lst = ss.createQuery("from HoaDonChiTiet where mahd= :ID").setParameter("ID", ID).list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select ID Hoa Dơn: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return lst;
	}

}

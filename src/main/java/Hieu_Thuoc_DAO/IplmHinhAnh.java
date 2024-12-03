package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.HinhAnh;
import Entity.KhachHang;

public class IplmHinhAnh implements IHinhAnh {
	SessionFactory sf;
	public IplmHinhAnh() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	
	@Override
	public List<HinhAnh> SelectAll() {
		Session ss=null;
		List<HinhAnh> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_HinhAnh").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll HinhAnh: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(HinhAnh ha) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(ha);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi Update HinhAnh: "+e);
			ss.getTransaction().rollback();
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(HinhAnh ha) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(ha);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi Insert HinhAnh: "+e);
			ss.getTransaction().rollback();
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public List<HinhAnh> SelectName(String name) {
		Session ss=null;
		List<HinhAnh> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("From HinhAnh where tenfileanh like :name").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select Ten Hinh Anh: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public HinhAnh SelectID(Integer ID) {
		Session ss=null;
		HinhAnh ha=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ha=ss.get(HinhAnh.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select ID HinhAnh: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return ha;
	}

}

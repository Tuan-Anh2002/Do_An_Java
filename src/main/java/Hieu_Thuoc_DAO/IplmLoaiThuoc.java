package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.LoaiThuoc;

public class IplmLoaiThuoc implements ILoaiThuoc {

	SessionFactory sf;
	public IplmLoaiThuoc() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	@Override
	public List<LoaiThuoc> SelectAll() {
		Session ss=null;
		List<LoaiThuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_LoaiThuoc").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll LoaiThuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(LoaiThuoc lt) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(lt);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update LoaiThuoc: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(LoaiThuoc lt) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(lt);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Insert LoaiThuoc: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public List<LoaiThuoc> SelectName(String name) {
		Session ss=null;
		List<LoaiThuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from LoaiThuoc where tenloaithuoc like :name order by maloaithuoc").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectName LoaiThuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public LoaiThuoc SelectID(Integer ID) {
		Session ss=null;
		LoaiThuoc lt=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lt=ss.get(LoaiThuoc.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID LoaiThuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lt;
	}

}

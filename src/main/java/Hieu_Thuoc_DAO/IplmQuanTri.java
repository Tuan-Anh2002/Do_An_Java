package Hieu_Thuoc_DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.QuanTri;
import Entity.Thuoc;

public class IplmQuanTri implements IQuanTri {
	SessionFactory sf;
	public IplmQuanTri() {
		
		sf=Hibernate_Utils.getSessionFactory();
	}
	
	@Override
	public QuanTri Select(String tk, String mk) {
		Session ss=null;
		QuanTri qt=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			qt= (QuanTri)ss.createQuery("from QuanTri where taikhoan=:tk and matkhau=:mk ").setParameter("tk", tk).setParameter("mk", mk).uniqueResult();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return qt;
	}

}

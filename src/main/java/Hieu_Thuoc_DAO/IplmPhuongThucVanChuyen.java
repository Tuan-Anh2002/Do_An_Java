package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.PhuongThucVanChuyen;
import Entity.Thuoc;

public class IplmPhuongThucVanChuyen implements IPhuongThucVanChuyen {

	SessionFactory sf;
	public IplmPhuongThucVanChuyen() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	@Override
	public List<PhuongThucVanChuyen> SelectAll() {
		Session ss=null;
		List<PhuongThucVanChuyen> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_PhuongThucVanChuyen").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll PhuongThucVanChuyen: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(PhuongThucVanChuyen ptvc) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(ptvc);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID PhuongThucVanChuyen: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(PhuongThucVanChuyen ptvc) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(ptvc);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID PhuongThucVanChuyen: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public List<PhuongThucVanChuyen> SelectName(String name) {
		Session ss=null;
		List<PhuongThucVanChuyen> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from PhuongThucVanChuyen where tenphvc like :name order by maptvc").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname PhuongThucVanChuyen: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public PhuongThucVanChuyen SelectID(Integer ID) {
		Session ss=null;
		PhuongThucVanChuyen ptvc=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ptvc=ss.get(PhuongThucVanChuyen.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID PhuongThucVanChuyen: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return ptvc;
	}
	@Override
	public boolean DeletePTVC(Integer ID) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss=sf.openSession();
			ss.beginTransaction();
			PhuongThucVanChuyen ptvc=ss.get(PhuongThucVanChuyen.class, ID);			
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl
			ss.delete(ptvc);
			// xác nhận giao dịch/thay đổi trong csdl
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi Xóa dữ liệu   " + e.getMessage());
			ss.getTransaction().rollback(); // hoàn tác giao dịch/lệnh
			return false;
		} finally {
			if (ss !=null) {
				ss.close();
				
			}
		}
		return true;
	}

}

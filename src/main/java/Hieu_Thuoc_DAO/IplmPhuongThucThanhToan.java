package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.PhuongThucThanhToan;
import Entity.Thuoc;


public class IplmPhuongThucThanhToan implements IPhuongThucThanhToan{

	SessionFactory sf;
	public IplmPhuongThucThanhToan() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	@Override
	public List<PhuongThucThanhToan> SelectAll() {
		Session ss=null;
		List<PhuongThucThanhToan> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_PhuongThucThanhToan").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll PhuongThucThanhToan: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(PhuongThucThanhToan pttt) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(pttt);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update PhuongThucThanhToan: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(PhuongThucThanhToan pttt) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(pttt);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update PhuongThucThanhToan: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public List<PhuongThucThanhToan> SelectName(String name) {
		Session ss=null;
		List<PhuongThucThanhToan> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from PhuongThucThanhToan where tenpttt like :name order by mapttt").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectName PhuongThucThanhToan: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public PhuongThucThanhToan SelectID(Integer ID) {
		Session ss=null;
		PhuongThucThanhToan pttt=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			pttt=ss.get(PhuongThucThanhToan.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID PhuongThucThanhToan: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return pttt;
	}
	@Override
	public boolean DeletePTTT(Integer ID) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss=sf.openSession();
			ss.beginTransaction();
			PhuongThucThanhToan pttt=ss.get(PhuongThucThanhToan.class, ID);			
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl
			ss.delete(pttt);
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

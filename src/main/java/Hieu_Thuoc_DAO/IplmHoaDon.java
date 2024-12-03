package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.GioHang;
import Entity.HoaDon;
import Entity.Thuoc;

public class IplmHoaDon implements IHoaDon {

	SessionFactory sf;
	public IplmHoaDon() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	
	@Override
	public List<HoaDon> SelectAll() {
		Session ss=null;
		List<HoaDon> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_HoaDon").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll HoaDon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(HoaDon hd) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(hd);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Upadte HoaDon: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public Integer Insert(HoaDon hd) {
		Session ss=null;
		Integer NewHD=0;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(hd);
			ss.getTransaction().commit();
			NewHD=hd.getMahd();
			if(NewHD!=0) {
				return NewHD;
			}else {
				return -1;
			}
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Insert HoaDon: "+e);
			return -1;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
	}

	@Override
	public HoaDon SelectID(Integer ID) {
		Session ss=null;
		HoaDon hd=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			hd=ss.get(HoaDon.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID HoaDon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return hd;
	}

	@Override
	public List<HoaDon> SelectName(String name) {
		Session ss=null;
		List<HoaDon> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from HoaDon where hotennguoinhan like :name order by mahd").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll HoaDon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public List<HoaDon> SelectDaGiao() {
		Session ss=null;
		List<HoaDon> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from HoaDon where trangthai= 1").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Select Da Giao Hoadon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public List<HoaDon> SelectDangGiao() {
		Session ss=null;
		List<HoaDon> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from HoaDon where trangthai= 0").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Select Dang Giao Hoadon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public boolean Delete_HoaDon(HoaDon hd) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss=sf.openSession();
			ss.beginTransaction();		
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl
			ss.delete(hd);
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

	@Override
	public List<HoaDon> CapNhat() {
		Session ss=null;
		List<HoaDon> lst=new ArrayList<>();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from HoaDon where mapttt=2").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll HoaDon: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
}

package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.HoaDon;
import Entity.KhachHang;


public class IplmKhachHang implements IKhachHang {
	SessionFactory sf;

	public IplmKhachHang() {
		sf = Hibernate_Utils.getSessionFactory();
	}

	@Override
	public List<KhachHang> SelectAll() {
		Session ss = null;
		List<KhachHang> lst = new ArrayList();
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			lst = ss.createNamedQuery("SelectAll_KhachHang").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll KhachHang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(KhachHang kh) {
		Session ss = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			ss.update(kh);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi Insert KhachHang: " + e);
			ss.getTransaction().rollback();
			return false;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public boolean Insert(KhachHang kh) {
		Session ss = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			ss.save(kh);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi Insert KhachHang: " + e);
			ss.getTransaction().rollback();
			return false;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public KhachHang SelectID(Integer ID) {
		Session ss = null;
		KhachHang kh = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			kh = ss.get(KhachHang.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select ID Khach Hang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return kh;

	}

	@Override
	public List<KhachHang> SelectName(String name) {
		Session ss = null;

		List<KhachHang> lst = new ArrayList();
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			lst = ss.createQuery("from KhachHang where hoten like :name order by makh")
					.setParameter("name", "%" + name + "%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select ID Khach Hang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public KhachHang SelectUserPass(String user, String password) {
		Session ss = null;

		KhachHang kh = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			kh = (KhachHang) ss.createQuery("from KhachHang where taikhoan=:user and matkhau=:password")
					.setParameter("user", user).setParameter("password", password).uniqueResult();
			ss.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			ss.getTransaction().rollback();
			System.out.println("Loi Select ID Khach Hang: " + e);
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return kh;
	}

	@Override
	public Integer login(String user,String password) {
		Session ss = null;
		KhachHang kh1 = null;
		try {
			ss = sf.openSession();
			ss.beginTransaction();
			kh1 = (KhachHang) ss.createQuery("from KhachHang where taikhoan=:user and matkhau=:password")
					.setParameter("user", user).setParameter("password", password).uniqueResult();
			ss.getTransaction().commit();
			if (kh1==null) {
				return -1;
			} else if(kh1!=null && kh1.getTrangthai()!=null && kh1.getTrangthai()==1) {
				return kh1.getMakh();
			}else if(kh1!=null && kh1.getTrangthai()!=null && kh1.getTrangthai()==0){
				return 0;
			}else {
				return -2;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Loi Dang Nhap Khach Hang: " + e);
			return -2;
		} finally {
			if (ss != null) {
				ss.close();
			}

		}

	}

	@Override
	public boolean Delete(Integer ID) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss=sf.openSession();
			ss.beginTransaction();
			KhachHang pb=ss.get(KhachHang.class, ID);			
			// thực hiện lệnh/giao dịch để xóa đối tượng trong csdl
			ss.delete(pb);
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
	public List<KhachHang> SelectHoatDong() {
		Session ss=null;
		List<KhachHang> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from KhachHang where trangthai= 1").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Select TK hoạt Dong: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public List<KhachHang> SelectDung() {
		Session ss=null;
		List<KhachHang> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from KhachHang where trangthai= 0").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Select TK Dung: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
}

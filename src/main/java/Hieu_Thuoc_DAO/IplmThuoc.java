package Hieu_Thuoc_DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Entity.KhachHang;
import Entity.Thuoc;

public class IplmThuoc implements IThuoc {

	SessionFactory sf;
	public IplmThuoc() {
		sf=Hibernate_Utils.getSessionFactory();
	}
	@Override
	public List<Thuoc> SelectAll() {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("SelectAll_Thuoc").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

	@Override
	public boolean Update(Thuoc t) {
		Session ss=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.update(t);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Update Thuoc: "+e);
			return false;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return true;
	}

	@Override
	public Integer Insert(Thuoc t) {
		Session ss=null;
		Integer NewMathuoc=0;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			ss.save(t);
			ss.getTransaction().commit();
			NewMathuoc=t.getMathuoc();
			if(NewMathuoc!=0) {
				return NewMathuoc;
			}else {
				return -1;
			}
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Insert Thuoc: "+e);
			return -1;
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
	}

	@Override
	public List<Thuoc> SelectName(String name) {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where motathuoc like :name order by mathuoc").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}	

	@Override
	public Thuoc SelectID(Integer ID) {
		Session ss=null;
		Thuoc thuoc=null;
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			thuoc=ss.get(Thuoc.class, ID);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectID Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return thuoc;
	}
	@Override
	public List<Thuoc> SelectSanPhamChucNang() {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where LoaiThuoc.tenloaithuoc=:name and trangthai=1").setParameter("name", "Sản Phẩm Chức Năng").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public List<Thuoc> SelectMT(Integer ID) {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where LoaiThuoc.maloaithuoc=:id and trangthai=1").setParameter("id", ID).list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public List<Thuoc> SelectDuocMyPham() {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where LoaiThuoc.tenloaithuoc=:name and trangthai=1").setParameter("name", "Dược Mỹ Phẩm").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public boolean Delete(Integer ID) {
		Session ss = null;
		try {
			// mở phiên làm việc
			ss=sf.openSession();
			ss.beginTransaction();
			Thuoc pb=ss.get(Thuoc.class, ID);			
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
	public List<Thuoc> selectthuoc() {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createNamedQuery("Select_Thuoc").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi SelectAll Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public List<Thuoc> selectnghiban() {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where trangthai=0 order by mathuoc").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectnghiban Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}
	@Override
	public List<Thuoc> Select_Ten_Thuoc(String name) {
		Session ss=null;
		List<Thuoc> lst=new ArrayList();
		try {
			ss=sf.openSession();
			ss.beginTransaction();
			lst=ss.createQuery("from Thuoc where tenthuoc like :name order by mathuoc").setParameter("name", "%"+name+"%").list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Loi Selectname Thuoc: "+e);
		}finally {
			if(ss!=null ) {
				ss.close();
			}
		}
		return lst;
	}

}

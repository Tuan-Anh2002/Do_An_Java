package Entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "dbo.thuoc")
@NamedQuery(name = "SelectAll_Thuoc",query = "from Thuoc where trangthai=1 order by mathuoc desc")
@NamedQuery(name = "Select_Thuoc",query = "from Thuoc order by mathuoc desc")
public class Thuoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mathuoc")
	private int mathuoc;
	@Column(name = "tenthuoc")
	private String tenthuoc;
	@Column(name = "motathuoc")
	private String motathuoc;
	@Column(name = "gianhap")
	private float gianhap;
	@Column(name = "giaban")
	private float giaban;
	@Column(name = "ngaycapnhat")	
	private Date ngaycapnhat;
	@Column(name = "trangthai")
	private Short trangthai;
	@ManyToOne
	@JoinColumn(name = "maloaithuoc",referencedColumnName = "maloaithuoc")	
	private LoaiThuoc LoaiThuoc;
	@OneToMany(mappedBy = "HAThuoc",fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<HinhAnh> lstHinhAnh;
	@OneToMany(mappedBy = "Thuoc",fetch = FetchType.EAGER)	
	@Fetch(FetchMode.SUBSELECT)
	private List<HoaDonChiTiet> lstHoaDonChiTiet;

	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Thuoc(int mathuoc, String tenthuoc, String motathuoc, float gianhap, float giaban, Date ngaycapnhat,
			Short trangthai,LoaiThuoc loaiThuoc, List<HinhAnh> lstHinhAnh,
			List<HoaDonChiTiet> lstHoaDonChiTiet) {
		super();
		this.mathuoc = mathuoc;
		this.tenthuoc = tenthuoc;
		this.motathuoc = motathuoc;
		this.gianhap = gianhap;
		this.giaban = giaban;
		this.ngaycapnhat = ngaycapnhat;
		this.trangthai = trangthai;
		LoaiThuoc = loaiThuoc;
		this.lstHinhAnh = lstHinhAnh;
		this.lstHoaDonChiTiet = lstHoaDonChiTiet;
	}

	



	public int getMathuoc() {
		return mathuoc;
	}

	public void setMathuoc(int mathuoc) {
		this.mathuoc = mathuoc;
	}

	public String getTenthuoc() {
		return tenthuoc;
	}

	public void setTenthuoc(String tenthuoc) {
		this.tenthuoc = tenthuoc;
	}

	public String getMotathuoc() {
		return motathuoc;
	}

	public void setMotathuoc(String motathuoc) {
		this.motathuoc = motathuoc;
	}

	public float getGianhap() {
		return gianhap;
	}

	public void setGianhap(float gianhap) {
		this.gianhap = gianhap;
	}

	public float getGiaban() {
		return giaban;
	}

	public void setGiaban(float giaban) {
		this.giaban = giaban;
	}

	public Date getNgaycapnhat() {
		return ngaycapnhat;
	}

	public void setNgaycapnhat(Date ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}

	public Short getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Short trangthai) {
		this.trangthai = trangthai;
	}

	public LoaiThuoc getLoaiThuoc() {
		return LoaiThuoc;
	}

	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		LoaiThuoc = loaiThuoc;
	}

	public List<HinhAnh> getLstHinhAnh() {
		return lstHinhAnh;
	}

	public void setLstHinhAnh(List<HinhAnh> lstHinhAnh) {
		this.lstHinhAnh = lstHinhAnh;
	}

	public List<HoaDonChiTiet> getLstHoaDonChiTiet() {
		return lstHoaDonChiTiet;
	}

	public void setLstHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonChiTiet) {
		this.lstHoaDonChiTiet = lstHoaDonChiTiet;
	}

	@Override
	public String toString() {
		return "Thuoc [mathuoc=" + mathuoc + ", tenthuoc=" + tenthuoc + ", motathuoc=" + motathuoc + ", gianhap="
				+ gianhap + ", giaban=" + giaban + ", ngaycapnhat=" + ngaycapnhat + ", trangthai=" + trangthai
				+ ", LoaiThuoc=" + LoaiThuoc + "]";
	}
	
	
}

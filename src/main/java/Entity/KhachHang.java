package Entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "dbo.khachhang")
@NamedQuery(name = "SelectAll_KhachHang",query = "from KhachHang order by makh desc")
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "makh")
	private int makh;
	@Column(name = "hoten")
	private String hoten;
	@Column(name = "taikhoan")
	private String taikhoan;
	@Column(name = "matkhau")
	private String matkhau;
	@Column(name = "diachi")
	private String diachi;
	@Column(name = "dienthoai")
	private String dienthoai;
	@Column(name = "Email")
	private String Email;
	@Column(name = "ngaysinh")
	private Date ngaysinh;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;
	@Column(name = "gioitinh")
	private int gioitinh;
	@Column(name = "trangthai")
	private Short trangthai;
	@OneToMany(mappedBy = "KhachHang",fetch = FetchType.EAGER)
	@Transient
	private List<HoaDon> lstHoaDon;
	@OneToMany(mappedBy = "KhachHang",fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<GioHang> lstGioHang;
	
	
	

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(int makh, String hoten, String taikhoan, String matkhau, String diachi, String dienthoai,
			String email, Date ngaysinh, Date ngaycapnhat, int gioitinh, Short trangthai, List<HoaDon> lstHoaDon,
			List<GioHang> lstGioHang) {
		super();
		this.makh = makh;
		this.hoten = hoten;
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
		this.diachi = diachi;
		this.dienthoai = dienthoai;
		Email = email;
		this.ngaysinh = ngaysinh;
		this.ngaycapnhat = ngaycapnhat;
		this.gioitinh = gioitinh;
		this.trangthai = trangthai;
		this.lstHoaDon = lstHoaDon;
		this.lstGioHang = lstGioHang;
	}

	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getDienthoai() {
		return dienthoai;
	}
	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public Date getNgaycapnhat() {
		return ngaycapnhat;
	}
	public void setNgaycapnhat(Date ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}
	public int getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(int gioitinh) {
		this.gioitinh = gioitinh;
	}
	public Short getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(Short trangthai) {
		this.trangthai = trangthai;
	}
	public List<HoaDon> getLstHoaDon() {
		return lstHoaDon;
	}
	public void setLstHoaDon(List<HoaDon> lstHoaDon) {
		this.lstHoaDon = lstHoaDon;
	}
	@Override
	public String toString() {
		return "KhachHang [makh=" + makh + ", hoten=" + hoten + ", taikhoan=" + taikhoan + ", matkhau=" + matkhau
				+ ", diachi=" + diachi + ", dienthoai=" + dienthoai + ", Email=" + Email + ", ngaysinh=" + ngaysinh
				+ ", ngaycapnhat=" + ngaycapnhat + ", gioitinh=" + gioitinh + ", trangthai=" + trangthai
				+ ", lstHoaDon=" + lstHoaDon + "]";
	}
	public List<GioHang> getLstGioHang() {
		return lstGioHang;
	}
	public void setLstGioHang(List<GioHang> lstGioHang) {
		this.lstGioHang = lstGioHang;
	}
}

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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.gson.annotations.Expose;
import Entity.PhuongThucThanhToan;
import Entity.PhuongThucVanChuyen;
import Entity.KhachHang;
@Entity
@Table(name = "dbo.hoadon")
@NamedQuery(name = "SelectAll_HoaDon",query = "From HoaDon order by mahd desc")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahd")
	private int mahd;
	@Column(name = "ngayhd")
	private Date ngayhd;
	@Column(name = "hotennguoinhan")
	private String hotennguoinhan;
	@Column(name = "diachinguoinhan")
	private String diachinguoinhan;
	@Column(name = "dienthoainguoinhan")
	private String dienthoainguoinhan;
	@Column(name = "diachiemail")
	private String diachiemail;
	@Column(name = "ngaygiaohang")
	private Date ngaygiaohang;
	@Column(name = "trangthai")
	private Short trangthai;
	@Column(name = "thanhtoan")
	private String thanhtoan;
	@OneToMany(mappedBy = "HoaDon",fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<HoaDonChiTiet> lstHoaDonChiTiet;
	@ManyToOne
	@JoinColumn(name = "maptvc",referencedColumnName = "maptvc")
	private  PhuongThucVanChuyen PhuongThucVanChuyen;
	@ManyToOne
	@JoinColumn(name = "mapttt",referencedColumnName = "mapttt")
	private PhuongThucThanhToan PhuongThucThanhToan;
	@ManyToOne
	@JoinColumn(name = "makh",referencedColumnName = "makh")
	private KhachHang KhachHang;
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

	public HoaDon(int mahd, Date ngayhd, String hotennguoinhan, String diachinguoinhan, String dienthoainguoinhan,
			String diachiemail, Date ngaygiaohang, Short trangthai, String thanhtoan, List<HoaDonChiTiet> lstHoaDonChiTiet,
			PhuongThucVanChuyen phuongThucVanChuyen, PhuongThucThanhToan phuongThucThanhToan,
			KhachHang khachHang) {
		super();
		this.mahd = mahd;
		this.ngayhd = ngayhd;
		this.hotennguoinhan = hotennguoinhan;
		this.diachinguoinhan = diachinguoinhan;
		this.dienthoainguoinhan = dienthoainguoinhan;
		this.diachiemail = diachiemail;
		this.ngaygiaohang = ngaygiaohang;
		this.trangthai = trangthai;
		this.thanhtoan = thanhtoan;
		this.lstHoaDonChiTiet = lstHoaDonChiTiet;
		PhuongThucVanChuyen = phuongThucVanChuyen;
		PhuongThucThanhToan = phuongThucThanhToan;
		KhachHang = khachHang;
	}
	public String getThanhtoan() {
		return thanhtoan;
	}
	public void setThanhtoan(String thanhtoan) {
		this.thanhtoan = thanhtoan;
	}
	public int getMahd() {
		return mahd;
	}
	public void setMahd(int mahd) {
		this.mahd = mahd;
	}
	public Date getNgayhd() {
		return ngayhd;
	}
	public void setNgayhd(Date ngayhd) {
		this.ngayhd = ngayhd;
	}
	public String getHotennguoinhan() {
		return hotennguoinhan;
	}
	public void setHotennguoinhan(String hotennguoinhan) {
		this.hotennguoinhan = hotennguoinhan;
	}
	public String getDiachinguoinhan() {
		return diachinguoinhan;
	}
	public void setDiachinguoinhan(String diachinguoinhan) {
		this.diachinguoinhan = diachinguoinhan;
	}
	public String getDienthoainguoinhan() {
		return dienthoainguoinhan;
	}
	public void setDienthoainguoinhan(String dienthoainguoinhan) {
		this.dienthoainguoinhan = dienthoainguoinhan;
	}
	public String getDiachiemail() {
		return diachiemail;
	}
	public void setDiachiemail(String diachiemail) {
		this.diachiemail = diachiemail;
	}
	public Date getNgaygiaohang() {
		return ngaygiaohang;
	}
	public void setNgaygiaohang(Date ngaygiaohang) {
		this.ngaygiaohang = ngaygiaohang;
	}
	public Short getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(Short trangthai) {
		this.trangthai = trangthai;
	}
	
	public List<HoaDonChiTiet> getLstHoaDonChiTiet() {
		return lstHoaDonChiTiet;
	}
	public void setLstHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonChiTiet) {
		this.lstHoaDonChiTiet = lstHoaDonChiTiet;
	}
	public PhuongThucVanChuyen getPhuongThucVanChuyen() {
		return PhuongThucVanChuyen;
	}
	public void setPhuongThucVanChuyen(PhuongThucVanChuyen phuongThucVanChuyen) {
		PhuongThucVanChuyen = phuongThucVanChuyen;
	}
	public PhuongThucThanhToan getPhuongThucThanhToan() {
		return PhuongThucThanhToan;
	}
	public void setPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
		PhuongThucThanhToan = phuongThucThanhToan;
	}
	public KhachHang getKhachHang() {
		return KhachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		KhachHang = khachHang;
	}
	
}

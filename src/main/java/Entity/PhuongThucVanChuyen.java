package Entity;

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

@Entity
@Table(name = "dbo.phuongthucvanchuyen")
@NamedQuery(name = "SelectAll_PhuongThucVanChuyen",query = "from PhuongThucVanChuyen order by maptvc")
public class PhuongThucVanChuyen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maptvc")
	private int maptvc;
	@Column(name = "tenphvc")
	private String tenphvc;
	@Column(name = "dodai")
	private Float dodai;
	@Column(name = "dongia")
	private Float dongia;
	@Column(name = "trangthai")
	private Short trangthai;
	@OneToMany(mappedBy = "PhuongThucVanChuyen",fetch = FetchType.EAGER)
	@Transient
	private List<HoaDon> lstHoaDon;
	public PhuongThucVanChuyen() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhuongThucVanChuyen(int maptvc, String tenphvc, Float dodai, Float dongia, Short trangthai,
			List<HoaDon> lstHoaDon) {
		super();
		this.maptvc = maptvc;
		this.tenphvc = tenphvc;
		this.dodai = dodai;
		this.dongia = dongia;
		this.trangthai = trangthai;
		this.lstHoaDon = lstHoaDon;
	}
	public int getMaptvc() {
		return maptvc;
	}
	public void setMaptvc(int maptvc) {
		this.maptvc = maptvc;
	}
	public String getTenphvc() {
		return tenphvc;
	}
	public void setTenphvc(String tenphvc) {
		this.tenphvc = tenphvc;
	}
	public Float getDodai() {
		return dodai;
	}
	public void setDodai(Float dodai) {
		this.dodai = dodai;
	}
	public Float getDongia() {
		return dongia;
	}
	public void setDongia(Float dongia) {
		this.dongia = dongia;
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
	
}

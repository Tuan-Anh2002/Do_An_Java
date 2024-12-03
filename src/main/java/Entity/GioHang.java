package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.giohang")
@NamedQuery(name = "selectgiohang",query = "from GioHang")
public class GioHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "magh")
	private int magh;
	@Column(name = "mathuoc")
	private int mathuoc;
	@Column(name = "tenfileanh")
	private String tenfileanh;
	@Column(name = "giaban")
	private float giaban;
	@Column(name = "tenthuoc")
	private String tenthuoc;
	@Column(name = "soluong")
	private int soluong;
	@ManyToOne 
	@JoinColumn(name = "makh", referencedColumnName = "makh")
	private KhachHang KhachHang;
	
	public GioHang(int magh, int mathuoc, String tenfileanh, float giaban, String tenthuoc, int soluong,
			KhachHang khachHang) {
		super();
		this.magh = magh;
		this.mathuoc = mathuoc;
		this.tenfileanh = tenfileanh;
		this.giaban = giaban;
		this.tenthuoc = tenthuoc;
		this.soluong = soluong;
		KhachHang = khachHang;
	}
	
	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMagh() {
		return magh;
	}
	public void setMagh(int magh) {
		this.magh = magh;
	}
	public int getMathuoc() {
		return mathuoc;
	}
	public void setMathuoc(int mathuoc) {
		this.mathuoc = mathuoc;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getTenfileanh() {
		return tenfileanh;
	}
	public void setTenfileanh(String tenfileanh) {
		this.tenfileanh = tenfileanh;
	}
	public float getGiaban() {
		return giaban;
	}
	public void setGiaban(float giaban) {
		this.giaban = giaban;
	}
	public String getTenthuoc() {
		return tenthuoc;
	}
	public void setTenthuoc(String tenthuoc) {
		this.tenthuoc = tenthuoc;
	}
	public KhachHang getKhachHang() {
		return KhachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		KhachHang = khachHang;
	}
}

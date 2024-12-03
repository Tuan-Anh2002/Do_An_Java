package Entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dbo.hoadonchitiet")
@NamedQuery(name = "SelectAll_HoaDonChiTiet",query = "from HoaDonChiTiet order by macthd")
public class HoaDonChiTiet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "macthd")
	private int macthd;
	@Column(name = "mahd", insertable = false, updatable = false)
	private int mahd;
	@Column(name = "mathuoc",insertable = false, updatable = false)
	private int mathuoc;
	@Column(name = "soluongban")
	private int soluongban;
	@Column(name = "giaban")
	private Float giaban;
	@Column(name = "tralai")
	private int tralai;
	@ManyToOne
	@JoinColumn(name = "mahd",referencedColumnName = "mahd")
	private HoaDon HoaDon;
	@ManyToOne
	@JoinColumn(name = "mathuoc",referencedColumnName = "mathuoc")
	private Thuoc Thuoc;
	public HoaDonChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public HoaDonChiTiet(int macthd, int mahd, int soluongban, Float giaban, int tralai, HoaDon hoaDon,
			Thuoc thuoc) {
		super();
		this.macthd = macthd;
		this.mahd = mahd;
		this.soluongban = soluongban;
		this.giaban = giaban;
		this.tralai = tralai;
		HoaDon = hoaDon;
		Thuoc = thuoc;
	}


	public int getSoluongban() {
		return soluongban;
	}
	public void setSoluongban(int soluongban) {
		this.soluongban = soluongban;
	}
	public Float getGiaban() {
		return giaban;
	}
	public void setGiaban(Float giaban) {
		this.giaban = giaban;
	}
	public int getTralai() {
		return tralai;
	}
	public void setTralai(int tralai) {
		this.tralai = tralai;
	}
	public HoaDon getHoaDon() {
		return HoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		HoaDon = hoaDon;
	}
	public Thuoc getThuoc() {
		return Thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		Thuoc = thuoc;
	}
	public int getMahd() {
		return mahd;
	}

	public void setMahd(int mahd) {
		this.mahd = mahd;
	}

	
	
}

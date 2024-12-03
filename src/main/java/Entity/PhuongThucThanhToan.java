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
@Table(name = "dbo.phuongthucthanhtoan")
@NamedQuery(name = "SelectAll_PhuongThucThanhToan",query = "from PhuongThucThanhToan order by mapttt")
public class PhuongThucThanhToan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapttt")
	private int mapttt;
	@Column(name = "tenpttt")
	private String tenpttt;
	@Column(name = "trangthai")
	private Short trangthai;
	@OneToMany(mappedBy = "PhuongThucThanhToan",fetch = FetchType.EAGER)
	@Transient
	private List<HoaDon> lstHoaDon;
	public PhuongThucThanhToan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhuongThucThanhToan(int mapttt, String tenpttt, Short trangthai) {
		super();
		this.mapttt = mapttt;
		this.tenpttt = tenpttt;
		this.trangthai = trangthai;
	}
	public int getMapttt() {
		return mapttt;
	}
	public void setMapttt(int mapttt) {
		this.mapttt = mapttt;
	}
	public String getTenpttt() {
		return tenpttt;
	}
	public void setTenpttt(String tenpttt) {
		this.tenpttt = tenpttt;
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

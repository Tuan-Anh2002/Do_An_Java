package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "dbo.hinhanh")
@NamedQuery(name = "SelectAll_HinhAnh", query = "from HinhAnh order by maha")
public class HinhAnh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maha")
	private int maha;
	@Column(name = "tenfileanh")
	private String tenfileanh;
	@Column(name = "trangthai")
	private Short trangthai;
	//@Expose(deserialize = false,serialize = false)
	@ManyToOne
	@JoinColumn(name = "mathuoc",referencedColumnName = "mathuoc")
	private Thuoc HAThuoc;
	public HinhAnh() {
		super();
		// TODO Auto-generated constructor stub3232323
	}
	
	

	public HinhAnh(int maha, String tenfileanh, Short trangthai, Thuoc hAThuoc) {
		super();
		this.maha = maha;
		this.tenfileanh = tenfileanh;
		this.trangthai = trangthai;
		HAThuoc = hAThuoc;
	}



	public int getMaha() {
		return maha;
	}
	public void setMaha(int maha) {
		this.maha = maha;
	}
	public String getTenfileanh() {
		return tenfileanh;
	}
	public void setTenfileanh(String tenfileanh) {
		this.tenfileanh = tenfileanh;
	}
	public Short getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(Short trangthai) {
		this.trangthai = trangthai;
	}
	

	public Thuoc getHAThuoc() {
		return HAThuoc;
	}

	public void setHAThuoc(Thuoc hAThuoc) {
		HAThuoc = hAThuoc;
	}



	@Override
	public String toString() {
		return "HinhAnh [maha=" + maha + ", tenfileanh=" + tenfileanh + ", trangthai=" + trangthai + ", HAThuoc="
				+ HAThuoc + "]";
	}
	
	
}

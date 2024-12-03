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
@Table(name = "dbo.loaithuoc")
@NamedQuery(name = "SelectAll_LoaiThuoc",query = "from LoaiThuoc order by maloaithuoc")
public class LoaiThuoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maloaithuoc")
	private int maloaithuoc;
	@Column(name = "tenloaithuoc")
	private String tenloaithuoc;
	@Column(name = "Trang_thai")
	private Short Trang_thai;
	@OneToMany(mappedBy = "LoaiThuoc",fetch = FetchType.EAGER)
	@Transient
	private List<Thuoc> lstThuoc;
	public LoaiThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiThuoc(int maloaithuoc, String tenloaithuoc, Short trang_thai) {
		super();
		this.maloaithuoc = maloaithuoc;
		this.tenloaithuoc = tenloaithuoc;
		Trang_thai = trang_thai;
	}
	public int getMaloaithuoc() {
		return maloaithuoc;
	}
	public void setMaloaithuoc(int maloaithuoc) {
		this.maloaithuoc = maloaithuoc;
	}
	public String getTenloaithuoc() {
		return tenloaithuoc;
	}
	public void setTenloaithuoc(String tenloaithuoc) {
		this.tenloaithuoc = tenloaithuoc;
	}
	public Short getTrang_thai() {
		return Trang_thai;
	}
	public void setTrang_thai(Short trang_thai) {
		Trang_thai = trang_thai;
	}
	public List<Thuoc> getLstThuoc() {
		return lstThuoc;
	}
	public void setLstThuoc(List<Thuoc> lstThuoc) {
		this.lstThuoc = lstThuoc;
	}
	
}

package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "dbo.quantri")
public class QuanTri {
	@Id
	@Column(name = "taikhoan")
	private String taikhoan;
	@Column(name = "matkhau")
	private String matkhau;
	public QuanTri() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuanTri(String taikhoan, String matkhau) {
		super();
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
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
	public void setMatkhat(String matkhau) {
		this.matkhau = matkhau;
	}
	
}

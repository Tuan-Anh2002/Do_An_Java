package Entity;

public class MesssageBox {
	private int macode;
	private String Tenloi;
	public MesssageBox() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MesssageBox(int macode, String tenloi) {
		super();
		this.macode = macode;
		Tenloi = tenloi;
	}
	public int getMacode() {
		return macode;
	}
	public void setMacode(int macode) {
		this.macode = macode;
	}
	public String getTenloi() {
		return Tenloi;
	}
	public void setTenloi(String tenloi) {
		Tenloi = tenloi;
	}
	
}

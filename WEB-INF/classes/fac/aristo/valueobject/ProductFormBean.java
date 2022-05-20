package fac.aristo.valueobject;

import java.io.Serializable;

public class ProductFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String f_pcode;
	private String f_pname;
	private int pcode;
	private String pname;
	private String pack;
	private String division;
	private String tp;
	private String link;
	private String id;

		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getF_pcode() {
		return f_pcode;
	}
	public void setF_pcode(String f_pcode) {
		this.f_pcode = f_pcode;
	}
	public String getF_pname() {
		return f_pname;
	}
	public void setF_pname(String f_pname) {
		this.f_pname = f_pname;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}

	
}

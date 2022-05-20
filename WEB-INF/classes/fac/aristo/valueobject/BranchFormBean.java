package fac.aristo.valueobject;

import java.io.Serializable;

public class BranchFormBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private String compt_code;
	private String brn_id;
	private String brn_desc;
	private int depo_code;
	private String depo_name;
	
	public String getBrn_desc() {
		return brn_desc;
	}
	public void setBrn_desc(String brn_desc) {
		this.brn_desc = brn_desc;
	}
	public String getBrn_id() {
		return brn_id;
	}
	public void setBrn_id(String brn_id) {
		this.brn_id = brn_id;
	}
	public String getCompt_code() {
		return compt_code;
	}
	public void setCompt_code(String compt_code) {
		this.compt_code = compt_code;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public String getDepo_name() {
		return depo_name;
	}
	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}



	
}

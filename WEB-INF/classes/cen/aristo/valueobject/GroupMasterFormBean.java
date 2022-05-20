package cen.aristo.valueobject;
  
import java.io.Serializable;

public class GroupMasterFormBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int gp_code;
	private String gp_name;
	private String gp_type;
	private String slct;  
	
    /////////////////////////Getter and Setter method of FormBean//////////////////////////
	
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getGp_code() {
		return gp_code;
	}
	public void setGp_code(int gp_code) {
		this.gp_code = gp_code;
	}
	public String getGp_name() {
		return gp_name;
	}
	public void setGp_name(String gp_name) {
		this.gp_name = gp_name;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public String getGp_type() {
		return gp_type;
	}
	public void setGp_type(String gp_type) {
		this.gp_type = gp_type;
	}
	
	
}

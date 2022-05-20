package fac.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class LocationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comp_code;
	private String loc_id;
	private String loca_name;
	private String loc_type;
	private String loc_abbr;
	private List llist;
	
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public List getLlist() {
		return llist;
	}
	public void setLlist(List llist) {
		this.llist = llist;
	}
	public String getLoc_abbr() {
		return loc_abbr;
	}
	public void setLoc_abbr(String loc_abbr) {
		this.loc_abbr = loc_abbr;
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	public String getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}
	public String getLoca_name() {
		return loca_name;
	}
	public void setLoca_name(String loca_name) {
		this.loca_name = loca_name;
	}
	
	
}

package cen.aristo.form;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CentralRepo6Form extends ActionForm {

  
	private static final long serialVersionUID = 1L;
	private String sdate;
	private String edate;
	private String pcode;
	private String name;
	private String city;
	private String pack;
	private String bat_no;
	private String exp_dt;
	private int sqty;
	private int sfree;
	private List rlist;
	
	private List plist;
	private String pname;
	private int pval;
	private int code;
	private int choice;
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.sdate="";
	this.edate="";
	this.choice=1;
	}
	
	
	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getExp_dt() {
		return exp_dt;
	}
	public void setExp_dt(String exp_dt) {
		this.exp_dt = exp_dt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public List getPlist() {
		return plist;
	}
	public void setPlist(List plist) {
		this.plist = plist;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPval() {
		return pval;
	}
	public void setPval(int pval) {
		this.pval = pval;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getSfree() {
		return sfree;
	}
	public void setSfree(int sfree) {
		this.sfree = sfree;
	}
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	
}

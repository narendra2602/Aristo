package fac.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FacRepo4Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int quantity;

	private String sdate;
	private String edate;
	private List llist;
	
	private String sbranch;
	private List brlist;
	private String brval;
	private String brname;
	private int choice=1;
	
	private String loc;
	private List loclist;
	private String lcval;
	private String lcname;
	private String pchoice="P";
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.choice=1;
	this.sdate="";
	this.edate="";
	this.pchoice="P";
	}

	public List getBrlist() {
		return brlist;
	}

	public void setBrlist(List brlist) {
		this.brlist = brlist;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getBrval() {
		return brval;
	}

	public void setBrval(String brval) {
		this.brval = brval;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public List getLlist() {
		return llist;
	}

	public void setLlist(List llist) {
		this.llist = llist;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSbranch() {
		return sbranch;
	}

	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getLcname() {
		return lcname;
	}

	public void setLcname(String lcname) {
		this.lcname = lcname;
	}

	public String getLcval() {
		return lcval;
	}

	public void setLcval(String lcval) {
		this.lcval = lcval;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List getLoclist() {
		return loclist;
	}

	public void setLoclist(List loclist) {
		this.loclist = loclist;
	}

	public String getPchoice() {
		return pchoice;
	}

	public void setPchoice(String pchoice) {
		this.pchoice = pchoice;
	}

	
}

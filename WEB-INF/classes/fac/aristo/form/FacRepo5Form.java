package fac.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FacRepo5Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private int pcode;
	private String pname;
	private String pack;
	private String depo_name;
	private String batchno;
	private Date expdate;
	private int quantity;

	private String sdate;
	private String edate;
	private List llist;

	private int sbranch;
	private List brlist;
	private int brval;
	private String brname;
	private int choice=1;
	
	private String loc;
	private List loclist;
	private String lcval;
	private String lcname;
	private String pchoice="P";
	private String schoice="P";
	private Date lrdate;
	
	public Date getLrdate() {
		return lrdate;
	}

	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}

	public String getSchoice() {
		return schoice;
	}

	public void setSchoice(String schoice) {
		this.schoice = schoice;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.choice=1;
	this.sdate="";
	this.edate="";
	this.pchoice="P";
	this.schoice="P";
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

	public int getSbranch() {
		return sbranch;
	}

	public void setSbranch(int sbranch) {
		this.sbranch = sbranch;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
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

	public int getBrval() {
		return brval;
	}

	public void setBrval(int brval) {
		this.brval = brval;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getDepo_name() {
		return depo_name;
	}

	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
}

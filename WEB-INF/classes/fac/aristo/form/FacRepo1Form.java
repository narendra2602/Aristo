package fac.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FacRepo1Form extends ActionForm {


	private static final long serialVersionUID = 1L;
	private int sno;
	private int invoiceno;
	private Date despatchdate;
	private String depo_name;
	private double value;
	private String transporteid;
	private String lrno;
	private Date lrdate;
	private String vehicleno ;
	private String sdate;
	private String edate;
	private int depo_code;
	private List rlist;
	
	private String sbranch;
	private List brlist;
	private String brval;
	private String brname;
	private int choice=1;

	private String location;
	private String loc;	
	private List loclist;
	private String lcval;
	private String lcname;
	private String pchoice="P";
	private int boxes;
	

	public int getBoxes() {
		return boxes;
	}

	public void setBoxes(int boxes) {
		this.boxes = boxes;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.choice=1;
	this.sdate="";
	this.edate="";
	this.pchoice="P";
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
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
	public Date getDespatchdate() {
		return despatchdate;
	}
	public void setDespatchdate(Date despatchdate) {
		this.despatchdate = despatchdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(int invoiceno) {
		this.invoiceno = invoiceno;
	}
	public Date getLrdate() {
		return lrdate;
	}
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}
	public String getLrno() {
		return lrno;
	}
	public void setLrno(String lrno) {
		this.lrno = lrno;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransporteid() {
		return transporteid;
	}
	public void setTransporteid(String transporteid) {
		this.transporteid = transporteid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
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
	public String getSbranch() {
		return sbranch;
	}
	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}

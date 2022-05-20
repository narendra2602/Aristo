package fac.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FacRepo2Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String location;
	private String depo_name;
	private String productname;
	private int quantity;
	private int boxes;
	private String lrno;
	private Date lrdate;
	private String transporterid;
	private String vehicleno;
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
	
	private int lcase;
	private int ncase;
	private int invoice;


	
	public int getLcase() {
		return lcase;
	}

	public void setLcase(int lcase) {
		this.lcase = lcase;
	}

	public int getNcase() {
		return ncase;
	}

	public void setNcase(int ncase) {
		this.ncase = ncase;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.choice=1;
	this.sdate="";
	this.edate="";
	this.pchoice="P";
	}

	public int getBoxes() {
		return boxes;
	}

	public void setBoxes(int boxes) {
		this.boxes = boxes;
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

	public List getLlist() {
		return llist;
	}

	public void setLlist(List llist) {
		this.llist = llist;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getLrdate() {
		return lrdate;
	}

	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public String getTransporterid() {
		return transporterid;
	}

	public void setTransporterid(String transporterid) {
		this.transporterid = transporterid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
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

	public int getInvoice() {
		return invoice;
	}

	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}

	public String getLrno() {
		return lrno;
	}

	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	
	
}

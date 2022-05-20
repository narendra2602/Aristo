package cen.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class RoadMasterFormBeam implements Serializable 
{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int doc_no;
	private Date doc_date;
	private String recd_from_l;
	private String recd_from_d;

	private String letter_ref;
	private Date ref_date;
	private String permit_no;
	private Date vslid_upto;
	private int inv_no;
	private Date inv_date;
	private String lr_no;
	private Date lr_date;
	private String transport;
	private String vehical_no; 
    //////////////////////////////////////////Getter and Setter methods for All//////////////////////////////////////////////////////////////////
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public Date getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}
	public int getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public String getLetter_ref() {
		return letter_ref;
	}
	public void setLetter_ref(String letter_ref) {
		this.letter_ref = letter_ref;
	}
	public Date getLr_date() {
		return lr_date;
	}
	public void setLr_date(Date lr_date) {
		this.lr_date = lr_date;
	}
	public String getLr_no() {
		return lr_no;
	}
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}
	public String getPermit_no() {
		return permit_no;
	}
	public void setPermit_no(String permit_no) {
		this.permit_no = permit_no;
	}

	public String getRecd_from_d() {
		return recd_from_d;
	}
	public void setRecd_from_d(String recd_from_d) {
		this.recd_from_d = recd_from_d;
	}
	public String getRecd_from_l() {
		return recd_from_l;
	}
	public void setRecd_from_l(String recd_from_l) {
		this.recd_from_l = recd_from_l;
	}
	public Date getRef_date() {
		return ref_date;
	}
	public void setRef_date(Date ref_date) {
		this.ref_date = ref_date;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getVehical_no() {
		return vehical_no;
	}
	public void setVehical_no(String vehical_no) {
		this.vehical_no = vehical_no;
	}
	public Date getVslid_upto() {
		return vslid_upto;
	}
	public void setVslid_upto(Date vslid_upto) {
		this.vslid_upto = vslid_upto;
	}
  
	
}  

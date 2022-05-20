package cen.aristo.valueobject;

import java.util.Date;
import java.io.Serializable;

public class BatchMasterFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int bat_pcode;
	private String bat_no;
	private String bat_mfgdt;
	private String bat_expdt;
	private double bat_netrt;
	private double bat_netrt1;
	private double bat_trdrt;
	private double bat_trdrt1;     
	private double bat_mrprt;
	private double bat_mrprt1;
	private double bat_mfgrt;
	private double bat_hosrt;
	private double bat_excrt;
	private String bat_indct; 
	private int bat_opng;
	private int bat_clos;
	private double bat_purrt;
	private int bat_clos2;
	private String bat_tag;
	private String slct;
	private double ecess;
	private double weight_box;
	private Date bat_recdt;
	private int case_size;
	private int box;
	private Date first_issu;
	private Date last_issu;
	private Date expiry;
	

	private int pval;
	private String pname;
	
	 
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public int getCase_size() {
		return case_size;
	}
	public void setCase_size(int case_size) {
		this.case_size = case_size;
	}
 
	public int getBat_clos() {
		return bat_clos;
	}
	public void setBat_clos(int bat_clos) {
		this.bat_clos = bat_clos;
	}
	public int getBat_clos2() {
		return bat_clos2;
	}
	public void setBat_clos2(int bat_clos2) {
		this.bat_clos2 = bat_clos2;
	}
	public double getBat_excrt() {
		return bat_excrt;
	}
	public void setBat_excrt(double bat_excrt) {
		this.bat_excrt = bat_excrt;
	}
	public String getBat_expdt() {
		return bat_expdt;
	}
	public void setBat_expdt(String bat_expdt) {
		this.bat_expdt = bat_expdt;
	}
	public double getBat_hosrt() {
		return bat_hosrt;
	}
	public void setBat_hosrt(double bat_hosrt) {
		this.bat_hosrt = bat_hosrt;
	}
	public String getBat_indct() {
		return bat_indct;
	}
	public void setBat_indct(String bat_indct) {
		this.bat_indct = bat_indct;
	}
	public String getBat_mfgdt() {
		return bat_mfgdt;
	}
	public void setBat_mfgdt(String bat_mfgdt) {
		this.bat_mfgdt = bat_mfgdt;
	}
	public double getBat_mfgrt() {
		return bat_mfgrt;
	}
	public void setBat_mfgrt(double bat_mfgrt) {
		this.bat_mfgrt = bat_mfgrt;
	}
	public double getBat_mrprt() {
		return bat_mrprt;
	}
	public void setBat_mrprt(double bat_mrprt) {
		this.bat_mrprt = bat_mrprt;
	}
	public double getBat_mrprt1() {
		return bat_mrprt1;
	}
	public void setBat_mrprt1(double bat_mrprt1) {
		this.bat_mrprt1 = bat_mrprt1;
	}
	public double getBat_netrt() {
		return bat_netrt;
	}
	public void setBat_netrt(double bat_netrt) {
		this.bat_netrt = bat_netrt;
	}
	public double getBat_netrt1() {
		return bat_netrt1;
	}
	public void setBat_netrt1(double bat_netrt1) {
		this.bat_netrt1 = bat_netrt1;
	}
	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public int getBat_opng() {
		return bat_opng;
	}
	public void setBat_opng(int bat_opng) {
		this.bat_opng = bat_opng;
	}
	public int getBat_pcode() {
		return bat_pcode;
	}
	public void setBat_pcode(int bat_pcode) {
		this.bat_pcode = bat_pcode;
	}
	public double getBat_purrt() {
		return bat_purrt;
	}
	public void setBat_purrt(double bat_purrt) {
		this.bat_purrt = bat_purrt;
	}
 
	public String getBat_tag() {
		return bat_tag;
	}
	public void setBat_tag(String bat_tag) {
		this.bat_tag = bat_tag;
	}
	public double getBat_trdrt() {
		return bat_trdrt;
	}
	public void setBat_trdrt(double bat_trdrt) {
		this.bat_trdrt = bat_trdrt;
	}
	public double getBat_trdrt1() {
		return bat_trdrt1;
	}
	public void setBat_trdrt1(double bat_trdrt1) {
		this.bat_trdrt1 = bat_trdrt1;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public double getEcess() {
		return ecess;
	}
	public void setEcess(double ecess) {
		this.ecess = ecess;
	}
 
	public Date getBat_recdt() {
		return bat_recdt;
	}
	public void setBat_recdt(Date bat_recdt) {
		this.bat_recdt = bat_recdt;
	}
	public Date getFirst_issu() {
		return first_issu;
	}
	public void setFirst_issu(Date first_issu) {
		this.first_issu = first_issu;
	}
	public Date getLast_issu() {
		return last_issu;
	}
	public void setLast_issu(Date last_issu) {
		this.last_issu = last_issu;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
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
	public double getWeight_box() {
		return weight_box;
	}
	public void setWeight_box(double weight_box) {
		this.weight_box = weight_box;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
 
	
   	
	 
}

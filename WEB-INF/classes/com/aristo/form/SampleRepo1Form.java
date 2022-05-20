package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SampleRepo1Form extends ActionForm {


	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int opening;
	private int receipt;
	private int sample;
	private int fstaff;
	private int alloc;
	private int issue;
	private int adissue;
	private int seclot;
	private int trf;
	private int pending;
	private int pending1;
	private int written;
	private int close;
	private String sdate;
	private String edate;
	private String choice;	
	private List rlist ;
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.sdate="";
	this.edate="";
	this.choice="M";
	}
	public int getAdissue() {
		return adissue;
	}
	public void setAdissue(int adissue) {
		this.adissue = adissue;
	}
	public int getAlloc() {
		return alloc;
	}
	public void setAlloc(int alloc) {
		this.alloc = alloc;
	}
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getFstaff() {
		return fstaff;
	}
	public void setFstaff(int fstaff) {
		this.fstaff = fstaff;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getOpening() {
		return opening;
	}
	public void setOpening(int opening) {
		this.opening = opening;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public int getPending1() {
		return pending1;
	}
	public void setPending1(int pending1) {
		this.pending1 = pending1;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getReceipt() {
		return receipt;
	}
	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getSeclot() {
		return seclot;
	}
	public void setSeclot(int seclot) {
		this.seclot = seclot;
	}
	public int getTrf() {
		return trf;
	}
	public void setTrf(int trf) {
		this.trf = trf;
	}
	public int getWritten() {
		return written;
	}
	public void setWritten(int written) {
		this.written = written;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}


	
}

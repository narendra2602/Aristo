package com.aristo.valueobject;

import java.io.Serializable;

public class SampleRepo1FormBean implements Serializable {

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
	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
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
	
}

package com.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UploadForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private String file_name;
	private String file_path;
	private String upload;
	private String depo_name;
	private String typ;
	private String u_date;
	private String u_time;
	private String search;
	private List ulist;
	
	
	
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getU_date() {
		return u_date;
	}
	public void setU_date(String u_date) {
		this.u_date = u_date;
	}
	public String getU_time() {
		return u_time;
	}
	public void setU_time(String u_time) {
		this.u_time = u_time;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public List getUlist() {
		return ulist;
	}
	public void setUlist(List ulist) {
		this.ulist = ulist;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}

package com.aristo.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;




public class StrutsUploadAndSaveForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormFile testFile = null;
	private ArrayList<FormFile> formFiles = null; 
	private int index;
    private List flist;

	public List getFlist() {
		return flist;
	}

	public void setFlist(List flist) {
		this.flist = flist;
	}

	/**
	 * Constructor!
	 */
	public StrutsUploadAndSaveForm() {
		System.out.println("-------Inside constructor!!!!!!!");
		formFiles = new ArrayList<FormFile>();
		index = 0;
	
	}
	
	/**
	 * Get FormFiles
	 * @return ArrayList
	 */
	public ArrayList<FormFile> getFormFiles() {
			System.out.println("------ Inside the getFormFiles");
			return formFiles;
	}


	/**
	 * Get testFile
	 * @return FormFile
	 */
	public FormFile getTestFile(int in) {
		System.out.println("------ Inside the getTestFile");
		return testFile;
	}
	
	/**
	 * Set testFile
	 * @param <code>FormFile</code>
	 */
	public void setTestFile(int in,FormFile t) {
		try {
			System.out.println("---------Inside setTestFile  ::  index:  "  + index + "  ::  " + in);
			this.testFile =  t;
			setFormFiles(t);
			index++;
		}catch(Exception e) {
			System.out.println("Exception in setTestFile!" + e);
		}
		//System.out.println("---------Inside setTestFile"  + index);
		//this.testFile = t;
	}
	
	public void setFormFiles(FormFile t) {
		System.out.println("------ Inside the setFormFiles");
		this.formFiles.add(index,t);
	}
	

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		testFile = null;

	}
}  


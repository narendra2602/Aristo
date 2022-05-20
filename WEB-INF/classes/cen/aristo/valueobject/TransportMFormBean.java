package cen.aristo.valueobject;

import java.io.Serializable;

public class TransportMFormBean implements Serializable 
{

   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private int depo_code;
   private int tr_code;
   private String tr_name;
   private String tr_add1;
   private String tr_add2;
   private String tr_city; 
   private String telphone_no;
   private String tr_person;
   private String tin_no;
   
   public String getTin_no() {
	return tin_no;
}
   
public String getTelphone_no() {
	return telphone_no;
}

public void setTelphone_no(String telphone_no) {
	this.telphone_no = telphone_no;
}

public void setTin_no(String tin_no) {
	this.tin_no = tin_no;
}
////////////////////////Getter and Setter method of TrnaportFormBen///////////////////////////////////////////////////////////
public int getDepo_code() {
	return depo_code;
}
public void setDepo_code(int depo_code) {
	this.depo_code = depo_code;
}
public String getTr_add1() {
	return tr_add1;
}
public void setTr_add1(String tr_add1) {
	this.tr_add1 = tr_add1;
}
public String getTr_add2() {
	return tr_add2;
}
public void setTr_add2(String tr_add2) {
	this.tr_add2 = tr_add2;
}
public String getTr_city() {
	return tr_city;
}
public void setTr_city(String tr_city) {
	this.tr_city = tr_city;
}
public int getTr_code() {
	return tr_code;
}
public void setTr_code(int tr_code) {
	this.tr_code = tr_code;
}
public String getTr_name() {
	return tr_name;
}
public void setTr_name(String tr_name) {
	this.tr_name = tr_name;
}
public String getTr_person() {
	return tr_person;
}
public void setTr_person(String tr_person) {
	this.tr_person = tr_person;
}

   
   
	
	 
}

package com.aristo.valueobject;

import java.io.Serializable;

public class MktFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mcode;
	private String lupdate;
	private String mname;
	private String pack;
	private float rate;
	private String nm1[]; 
	private String nm2;
	private String nm3;
	private String nm4;
	private String nm5;
	private String nm6;
	private String nm7;
	private String nm8; 
	private String nm9[];
	private String nm10;
	
	private int qty0[];
	private int qty1[];
	private int qty2;
	private int qty3;
	private int qty4;
	private int qty5;
	private int qty6;
	private int qty7;
	private int qty8;
	private int qty9;
	private int qty10;
	private int qty11;
	private int qty12;
	private int qty13;
	private int uv;
	private int color;
	
	

	
    private float val1[];
    private double val2;
    private float val3;
    private float val4;
    private float val5;
    private float val6;
    private float val7;
    private float val8;
    private float val9;
    private float val10;
   
    private double dval0[];
    private double dval1;
    private double dval2;
    private double dval3;
    private double dval4;
    private double dval5;
    private double dval6;
    private double dval7;
    private double dval8;
    private double dval9;
    private double dval10;
    private double dval11;
    private double dval12;
    private double dval13;
    private double dval14;
    private double dval15;
    
    // Report8 Trend Variables
    private String name;
	private int no_of_mr;
 
    // End of Report8 Trend Variables
	
    public MktFormBean()
    {
    	nm1 = new String[50];
    	nm9 = new String[50];
    	qty1 = new int[50];
    	qty0 = new int[50];
    	val1 = new float[50];
    	dval0 = new double[50];
    }
    
    
	 


	public double[] getDval0() {
		return dval0;
	}

	public void setDval0(double[] dval0) {
		this.dval0 = dval0;
	}





	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
		
	public String getNm1(int index) {
		return nm1[index];
	}
	public void setNm1(int index, String value) {
		nm1[index] = value;
	}
	public String getNm10() {
		return nm10;
	}
	public void setNm10(String nm10) {
		this.nm10 = nm10;
	}
	public String getNm2() {
		return nm2;
	}
	public void setNm2(String nm2) {
		this.nm2 = nm2;
	}
	public String getNm3() {
		return nm3;
	}
	public void setNm3(String nm3) {
		this.nm3 = nm3;
	}
	public String getNm4() {
		return nm4;
	}
	public void setNm4(String nm4) {
		this.nm4 = nm4;
	}
	public String getNm5() {
		return nm5;
	}
	public void setNm5(String nm5) {
		this.nm5 = nm5;
	}
	public String getNm6() {
		return nm6;
	}
	public void setNm6(String nm6) {
		this.nm6 = nm6;
	}
	public String getNm7() {
		return nm7;
	}
	public void setNm7(String nm7) {
		this.nm7 = nm7;
	}
	public String getNm8() {
		return nm8;
	}
	public void setNm8(String nm8) {
		this.nm8 = nm8;
	}
	public String getNm9(int index) {
		return nm9[index];
	}
	public void setNm9(int index, String value) {
		nm9[index] = value;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	 public int getQty1(int index) { 
	        return qty1[index]; 
	    }
	    
	    public void setQty1(int index, int value) { 
	       qty1[index] = value; 
	    }
 
	 public int getQty0(int index) { 
	        return qty0[index]; 
	    }
		    
     public void setQty0(int index, int value) { 
	       qty0[index] = value; 
	    }
	    
	public int getQty10() {
		return qty10;
	}
	public void setQty10(int qty10) {
		this.qty10 = qty10;
	}
	public int getQty2() {
		return qty2;
	}
	public void setQty2(int qty2) {
		this.qty2 = qty2;
	}
	public int getQty3() {
		return qty3;
	}
	public void setQty3(int qty3) {
		this.qty3 = qty3;
	}
	public int getQty4() {
		return qty4;
	}
	public void setQty4(int qty4) {
		this.qty4 = qty4;
	}
	public int getQty5() {
		return qty5;
	}
	public void setQty5(int qty5) {
		this.qty5 = qty5;
	}
	public int getQty6() {
		return qty6;
	}
	public void setQty6(int qty6) {
		this.qty6 = qty6;
	}
	public int getQty7() {
		return qty7;
	}
	public void setQty7(int qty7) {
		this.qty7 = qty7;
	}
	public int getQty8() {
		return qty8;
	}
	public void setQty8(int qty8) {
		this.qty8 = qty8;
	}
	public int getQty9() {
		return qty9;
	}
	public void setQty9(int qty9) {
		this.qty9 = qty9;
	}
	
	public int getQty12() {
		return qty12;
	}

	public void setQty12(int qty12) {
		this.qty12 = qty12;
	}


	public int getQty13() {
		return qty13;
	}

	public void setQty13(int qty13) {
		this.qty13 = qty13;
	}



////////////Array ke liye//////////////


	public double getDval12() {
		return dval12;
	}





	public void setDval12(double dval12) {
		this.dval12 = dval12;
	}





	public double getDval13() {
		return dval13;
	}





	public void setDval13(double dval13) {
		this.dval13 = dval13;
	}





	public double getDval14() {
		return dval14;
	}





	public void setDval14(double dval14) {
		this.dval14 = dval14;
	}





	public double getDval15() {
		return dval15;
	}





	public void setDval15(double dval15) {
		this.dval15 = dval15;
	}





	public float getVal1(int index) {
		return val1[index];
	}

	public void setVal1(int index, float value) {
		val1[index] = value;
	} 

	public double getDval0(int index) {
		return dval0[index];
	}

	public void setDval0(int index, double value) {
		dval0[index] = value;
	} 
////////////////////////Close/////////////////	
	
	
	public float getVal10() {
		return val10;
	}
	public void setVal10(float val10) {
		this.val10 = val10;
	}
	public double getVal2() {
		return val2;
	}

	public void setVal2(double val2) {
		this.val2 = val2;
	}

	public float getVal3() {
		return val3;
	}
	public void setVal3(float val3) {
		this.val3 = val3;
	}
	public float getVal4() {
		return val4;
	}
	public void setVal4(float val4) {
		this.val4 = val4;
	}
	public float getVal5() {
		return val5;
	}
	public void setVal5(float val5) {
		this.val5 = val5;
	}
	public float getVal6() {
		return val6;
	}
	public void setVal6(float val6) {
		this.val6 = val6;
	}
	public float getVal7() {
		return val7;
	}
	public void setVal7(float val7) {
		this.val7 = val7;
	}
	public float getVal8() {
		return val8;
	}
	public void setVal8(float val8) {
		this.val8 = val8;
	}
	public float getVal9() {
		return val9;
	}
	public void setVal9(float val9) {
		this.val9 = val9;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo_of_mr() {
		return no_of_mr;
	}

	public void setNo_of_mr(int no_of_mr) {
		this.no_of_mr = no_of_mr;
	}

	public double getDval5() {
		return dval5;
	}

	public void setDval5(double dval5) {
		this.dval5 = dval5;
	}

	public double getDval6() {
		return dval6;
	}

	public void setDval6(double dval6) {
		this.dval6 = dval6;
	}

	public double getDval2() {
		return dval2;
	}

	public void setDval2(double dval2) {
		this.dval2 = dval2;
	}

	public double getDval3() {
		return dval3;
	}

	public void setDval3(double dval3) {
		this.dval3 = dval3;
	}

	public double getDval4() {
		return dval4;
	}

	public void setDval4(double dval4) {
		this.dval4 = dval4;
	}

	public double getDval10() {
		return dval10;
	}

	public void setDval10(double dval10) {
		this.dval10 = dval10;
	}

	public double getDval7() {
		return dval7;
	}

	public void setDval7(double dval7) {
		this.dval7 = dval7;
	}

	public double getDval8() {
		return dval8;
	}

	public void setDval8(double dval8) {
		this.dval8 = dval8;
	}

	public double getDval9() {
		return dval9;
	}

	public void setDval9(double dval9) {
		this.dval9 = dval9;
	}

	public double getDval1() {
		return dval1;
	}

	public void setDval1(double dval1) {
		this.dval1 = dval1;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getQty11() {
		return qty11;
	}

	public void setQty11(int qty11) {
		this.qty11 = qty11;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public double getDval11() {
		return dval11;
	}

	public void setDval11(double dval11) {
		this.dval11 = dval11;
	}

	public String getLupdate() {
		return lupdate;
	}

	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}

	
	
}

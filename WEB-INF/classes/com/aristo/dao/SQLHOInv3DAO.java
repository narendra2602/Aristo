package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Inv3FormBean;

public class SQLHOInv3DAO {

	public List getInv3(Connection con,  int emon, String tp,String typ,int code1,int loginid,int eyear ) { 
		 
		Inv3FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        
		List<Inv3FormBean> data = new ArrayList<Inv3FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String wname=null;
            String txt5=null;
            String txt2=null;

        	double grossqty=0.00;
        	double grossval=0.00;
        	double netqty=0.00;
        	double netval=0.00;
        	double salqty=0.00;
        	double salval=0.00;
        	double expqty=0.00;
        	double expval=0.00;
        	double brkqty=0.00;
        	double brkval=0.00;
        	double pdqty=0.00;
        	double pdval=0.00;
        	double insqty=0.00;
        	double insval=0.00;

        	double pgrossqty=0.00;
        	double pgrossval=0.00;
        	double pnetqty=0.00;
        	double pnetval=0.00;
        	double psalqty=0.00;
        	double psalval=0.00;
        	double pexpqty=0.00;
        	double pexpval=0.00;
        	double pbrkqty=0.00;
        	double pbrkval=0.00;
        	double ppdqty=0.00;
        	double ppdval=0.00;
        	double pinsqty=0.00;
        	double pinsval=0.00;

        	
        	double ggrossval=0.00;
        	double gnetval=0.00;
        	double gsalval=0.00;
        	double gexpval=0.00;
        	double gbrkval=0.00;
        	double gpdval=0.00;
        	double ginsval=0.00;
        	
        	tblnm3="user_branch08";
        	
        	tblnm=(tp+"_product08").toLowerCase();
        	tblnm1=(tp+"_stock08").toLowerCase();
        	tblnm2=(tp+"_branch08").toLowerCase();
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no from monthfl where mnth_ord=? and mkt_year=? order by mnth_ord";  
    		ms1 = con.prepareStatement(month);
    		ms1.setInt(1,emon);
    		ms1.setInt(2,eyear);
    	 	mrec = ms1.executeQuery();
            
			    if (mrec.next())
 			    {	
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 	                wname = mrec.getString(3);
 			    }  
 			    	
	 			ms1.close();
	 			mrec.close();
	 			
                txt2="     SALES ANALYSIS FOR THE MONTH OF  "+txt5;
                   
////////////////////////////Product Master Ki Query//////////////////////////////////

            String query1= "select p.pcode,p.pname,p.pack,p.trd_rt1,s.doc_type," +
           	" s.qty"+wname+",s.val"+wname+",s.opng"+wname+",s.clos"+wname+",u.depo_code," +
   			" t.ter_name from "+tblnm+" as p inner join "+tblnm1+" as s on p.pcode=s.item_code inner join " +
			" "+tblnm2+" as t on s.depo_code=t.depo_code inner join "+tblnm3+" as u on u.depo_code=t.depo_code " +
			" and u.user_id=? and u.status=? and s.mkt_year=? and p.mkt_year=? order by p.pname,t.ter_name,s.doc_type";
			ps1 = con.prepareStatement(query1); 
	        ps1.setInt(1,loginid); 
	        ps1.setString(2,"Y");
	        ps1.setInt(3,eyear);
	        ps1.setInt(4, eyear);
			rst1 = ps1.executeQuery();
            boolean flag = false;
            boolean first = false;
            boolean print =false;
            
            int pcode=0;
            String pname =null;
            int depo_code=0;
            String depo_name=null;
            
			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	
	        	
                if (!first)
                {
	        	 	  depo_code=rst1.getInt(10);
	        	 	  depo_name=rst1.getString(11);
	        	 	  pcode=rst1.getInt(1);
	        	 	  pname = rst1.getString(2);
                }
                
				if (first)
	        	 {
	        	  if (pcode!=rst1.getInt(1))
	        	  {
	        		  flag=true;
	        		  print=true;
	        	  }
	        	  if (depo_code!=rst1.getInt(10))
	        		  print=true;
	        	 }
	        	  
	        	      first=true;
	        	
                        if (rst1.getInt(5)==63)
                        {
                        	salqty=salqty+rst1.getDouble(6);
                        	salval=salval+rst1.getDouble(7);
                        	gsalval=gsalval+rst1.getDouble(7);
	
                        	psalqty=psalqty+rst1.getDouble(6);
                        	psalval=psalval+rst1.getDouble(7);

                        }
                        if (rst1.getInt(5)==64)
                        {
                        	brkqty=brkqty+rst1.getDouble(6);
                        	brkval=brkval+rst1.getDouble(7);
                        	gbrkval=gbrkval+rst1.getDouble(7);
                        	
                        	pbrkqty=pbrkqty+rst1.getDouble(6);
                        	pbrkval=pbrkval+rst1.getDouble(7);
                        	
                        }
                        if (rst1.getInt(5)==69)
                        {
                        	expqty=expqty+rst1.getDouble(6);
                        	expval=expval+rst1.getDouble(7);
                        	gexpval=gexpval+rst1.getDouble(7);
                        	
                        	pexpqty=pexpqty+rst1.getDouble(6);
                        	pexpval=pexpval+rst1.getDouble(7);
                        }
                        if (rst1.getInt(5)==71)
                        {
                        	pdqty=pdqty+rst1.getDouble(6);
                        	pdval=pdval+rst1.getDouble(7);
                        	gpdval=gpdval+rst1.getDouble(7);

                        	ppdqty=ppdqty+rst1.getDouble(6);
                        	ppdval=ppdval+rst1.getDouble(7);

                        }
                        if (rst1.getInt(5)==70)
                        {
                        	insqty=insqty+rst1.getDouble(8);
                        	insval=insval+rst1.getDouble(9);
                        	ginsval=ginsval+rst1.getDouble(9);

                        	pinsqty=pinsqty+rst1.getDouble(8);
                        	pinsval=pinsval+rst1.getDouble(9);

                        }
                        if (rst1.getInt(5)==80)
                        {
                        	grossqty=grossqty+rst1.getDouble(6);
                        	grossval=grossval+rst1.getDouble(7);
                        	ggrossval=ggrossval+rst1.getDouble(7);
                        	
                        	pgrossqty=pgrossqty+rst1.getDouble(6);
                        	pgrossval=pgrossval+rst1.getDouble(7);
                        }
			        
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
                	netqty=netqty+(grossqty-(salqty+expqty+brkqty+pdqty+insqty));
                	netval=netval+(grossval-(salval+expval+brkval+pdval+insval));
                	gnetval=gnetval+netval;
			        
                	pnetqty=pnetqty+(pgrossqty-(psalqty+pexpqty+pbrkqty+ppdqty+pinsqty));
                	pnetval=pnetval+(pgrossval-(psalval+pexpval+pbrkval+ppdval+pinsval));

                if (print)
                {
			      if (netqty!=0)
			      {
					rfb = new Inv3FormBean();
					rfb.setCode(pcode);
					rfb.setName(pname);
					rfb.setPack(rst1.getString(3));
					rfb.setBranch_code(depo_code);
					rfb.setBranch_name(depo_name);
			        rfb.setGrossqty(grossqty);
			        rfb.setGrossval(grossval);
			        rfb.setSalqty(salqty);
			        rfb.setSalval(salval);
			        rfb.setExpqty(expqty);
			        rfb.setExpval(expval);
			        rfb.setBrkqty(brkqty);
			        rfb.setBrkval(brkval);
			        rfb.setPdqty(pdqty);
			        rfb.setPdval(pdval);
			        rfb.setInsqty(insqty);
			        rfb.setInsval(insval);
			        rfb.setNetqty(netqty);
			        rfb.setNetval(netval);

		             rfb.setHead1(txt2);
 		             
		             data.add(rfb);
		             
		 			grossqty=0.00;
		        	grossval=0.00;
		        	netqty=0.00;
		        	netval=0.00;
		        	salqty=0.00;
		        	salval=0.00;
		        	expqty=0.00;
		        	expval=0.00;
		        	brkqty=0.00;
		        	brkval=0.00;
		        	pdqty=0.00;
		        	pdval=0.00;
		        	insqty=0.00;
		        	insval=0.00;
		            print=false;
	        	 	depo_code=rst1.getInt(10);
	        	 	depo_name=rst1.getString(11);
	        	 	pcode=rst1.getInt(1);
	        	 	pname = rst1.getString(2);

			      }
                }
				
	        	 if (flag)
	        	 {
			      if (pnetqty!=0)
			      {
					rfb = new Inv3FormBean();
					rfb.setCode(0);
					rfb.setName("PRODUCT TOTAL");
			        rfb.setGrossqty(pgrossqty);
			        rfb.setGrossval(pgrossval);
			        rfb.setSalqty(psalqty);
			        rfb.setSalval(psalval);
			        rfb.setExpqty(pexpqty);
			        rfb.setExpval(pexpval);
			        rfb.setBrkqty(pbrkqty);
			        rfb.setBrkval(pbrkval);
			        rfb.setPdqty(ppdqty);
			        rfb.setPdval(ppdval);
			        rfb.setInsqty(pinsqty);
			        rfb.setInsval(pinsval);
			        rfb.setNetqty(pnetqty);
			        rfb.setNetval(pnetval);
	                rfb.setHead1(txt2);
		            data.add(rfb);
		            
		        	pgrossqty=0.00;
		        	pgrossval=0.00;
		        	pnetqty=0.00;
		        	pnetval=0.00;
		        	psalqty=0.00;
		        	psalval=0.00;
		        	pexpqty=0.00;
		        	pexpval=0.00;
		        	pbrkqty=0.00;
		        	pbrkval=0.00;
		        	ppdqty=0.00;
		        	ppdval=0.00;
		        	pinsqty=0.00;
		        	pinsval=0.00;
		        	flag=false;
			      }
	        	 }
				
			 }  ////////////// Product Master Loop End here/////////////////////////
			 
				 rfb=new Inv3FormBean();
				 rfb.setCode(1);
				 rfb.setName("GRAND TOTAL"); 
		         rfb.setGrossval(ggrossval);
			     rfb.setSalval(gsalval);
			     rfb.setExpval(gexpval);
			     rfb.setBrkval(gbrkval);
			     rfb.setPdval(gpdval);
			     rfb.setInsval(ginsval);
		         rfb.setNetval(gnetval);
		             
	             data.add(rfb); 				
				
		        rst1.close();
		        ps1.close();

////////////////////////////////////////////////////////////////////////////
 	}          //////////////// try block end ///////////////////
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv3DAO.getInv3 " + e); 
		}
		  finally 
		{
			try
				{
		           if(mrec != null){mrec.close();}
		           if(ms1 != null){ ms1.close();}
		           if(rst1 != null){ rst1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLInv3DAO.Connection.close "+e);
					}
		}
		return data;
	} 
	
}
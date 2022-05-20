package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.Inv3FormBean;
import com.aristo.valueobject.SampleRepo3FormBean;

public class SQLInv3DAO {

	public List getInv3(Connection con,String branch, int depo, int emon,int eyear, String tp,int uid,String utype) { 
		 
		Inv3FormBean rfb;
		PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
		List<Inv3FormBean> data = new ArrayList<Inv3FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String wname=null;
            String query1=null;
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
        	
        	double ggrossval=0.00;
        	double gnetval=0.00;
        	double gsalval=0.00;
        	double gexpval=0.00;
        	double gbrkval=0.00;
        	double gpdval=0.00;
        	double ginsval=0.00;
        	
        	tblnm=(tp+"_product08").toLowerCase();
        	tblnm1=(tp+"_stock08").toLowerCase();
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord=? and mkt_year=? order by mnth_ord";  
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
                
/////////////////////// Stock  Master ki Query/////////////////////////////////////
	           
        	String query3 = "Select doc_type,qty"+wname+",val"+wname+",opng"+wname+",clos"+wname+" from "+tblnm1+" where  "+
            "  depo_code=? and item_code=? and mkt_year=? order by item_code"; 
	        ps3 = con.prepareStatement(query3); 
               
 ////////////////////////////Product Master Ki Query//////////////////////////////////
	        if (utype.equals("PMT"))
	        {
            query1 = "Select pcode,pname,pack,trd_rt1 from "+tblnm+" where mkt_year=? and " +
            " pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by pname";
	        }
	        else
	        {
            query1 = "Select pcode,pname,pack,trd_rt1 from "+tblnm+" where mkt_year=? order by pname";	        	
	        }
	        
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();

			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	
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
				
			    ps3.setInt(1,depo); 
			    ps3.setInt(2,rst1.getInt(1)); 
			    ps3.setInt(3,eyear);
			    rst3 = ps3.executeQuery(); 
			        while (rst3.next())  /////// Stock Master Start/////////////////////////
			        {
			        	
/*			        	   String que = "update "+tblnm1+ " set item_type =? where  item_code=? and depo_code=? ";
                           System.out.println(que);
					        PreparedStatement psu = con.prepareStatement(que); 
					        psu.setString(1,"X");
					        psu.setInt(2,rst1.getInt(1));
					        psu.setInt(3,depo);
					        psu.executeUpdate();
					        psu.close();
	*/				        
                        if (rst3.getInt(1)==63)
                        {
                        	salqty=salqty+rst3.getDouble(2);
                        	salval=salval+rst3.getDouble(3);
                        	gsalval=gsalval+rst3.getDouble(3);
	
                        }
                        if (rst3.getInt(1)==64)
                        {
                        	brkqty=brkqty+rst3.getDouble(2);
                        	brkval=brkval+rst3.getDouble(3);
                        	gbrkval=gbrkval+rst3.getDouble(3);
                        	
                        }
                        if (rst3.getInt(1)==69)
                        {
                        	expqty=expqty+rst3.getDouble(2);
                        	expval=expval+rst3.getDouble(3);
                        	gexpval=gexpval+rst3.getDouble(3);

                        }
                        if (rst3.getInt(1)==71)
                        {
                        	pdqty=pdqty+rst3.getDouble(2);
                        	pdval=pdval+rst3.getDouble(3);
                        	gpdval=gpdval+rst3.getDouble(3);

                        }
                        if (rst3.getInt(1)==70)
                        {
                        	insqty=insqty+rst3.getDouble(4);
                        	insval=insval+rst3.getDouble(5);
                        	ginsval=ginsval+rst3.getDouble(5);
                        }
                        if (rst3.getInt(1)==80)
                        {
                        	grossqty=grossqty+rst3.getDouble(2);
                        	grossval=grossval+rst3.getDouble(3);
                        	ggrossval=ggrossval+rst3.getDouble(3);	
                        }
                        
			        }	 /////////////////// Target Master End ///////////////////////
			        
			        rst3.close();
			        
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
                	netqty=netqty+(grossqty-(salqty+expqty+brkqty+pdqty+insqty));
                	netval=netval+(grossval-(salval+expval+brkval+pdval+insval));
                	gnetval=gnetval+netval;
			        
			      if (netqty!=0)
			      {
					rfb = new Inv3FormBean();
					rfb.setCode(rst1.getInt(1));
					rfb.setName(rst1.getString(2));
					rfb.setPack(rst1.getString(3));
					rfb.setBranch_code(depo);
					rfb.setBranch_name(branch);
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
			      }
				
			 }  ////////////// Product Master Loop End here/////////////////////////
			 
				 rfb=new Inv3FormBean();
				 rfb.setName("GRAND TOTAL"); 
		         rfb.setGrossval(ggrossval);
			     rfb.setSalval(gsalval);
			     rfb.setExpval(gexpval);
			     rfb.setBrkval(gbrkval);
			     rfb.setPdval(gpdval);
			     rfb.setInsval(ginsval);
		         rfb.setNetval(gnetval);
		             
	             data.add(rfb); 				
				
		        ps3.close();
		        rst1.close();
		        ps1.close();

////////////////////////////////////////////////////////////////////////////
		} 
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
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLInv3DAO.Connection.close "+e);
					}
		}
		return data;
	}
	
	
	public List getInv4(Connection con, String typ, Date sdate,int dp,int eyear ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            
           	tblnm1=(typ+"_product08").toLowerCase();
           	tblnm2=(typ+"_batch08").toLowerCase();

    		sdt= new java.sql.Date (sdate.getTime());
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());

	        long diffInMilleseconds;
    		long diffInSeconds;
    		long diffInMinutes;
    		long diffInHours;
    		long diffInDays;
    		
           	String query=" select b.bat_pcode,p.pname,p.pack,b.bat_no,b.expiry,b.bat_clos,b.bat_clos*b.bat_mfgrt,b.bat_mfgrt " +
		    " from "+tblnm1+" as p inner join "+tblnm2+" as b on p.pcode=b.bat_pcode " +
		    " where b.depo_code="+dp+" and TO_DAYS(b.expiry) - TO_DAYS('"+sdt+"') < 365 and b.bat_clos<>0 and p.mkt_year="+eyear +
    		" order by b.bat_pcode,b.expiry "; 
           	         
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
			    if (rst.getDate(5).getTime()>ndt.getTime())
			    {
					rfb = new SampleRepo3FormBean();
					rfb.setPcode(rst.getInt(1));
					rfb.setPname(rst.getString(2)+ "," + rst.getString(3));
					rfb.setBat_no(rst.getString(4));
				    rfb.setExpiry(rst.getDate(5));
				    rfb.setBat_netrt(rst.getDouble(8));
				    
		 
					diffInMilleseconds =  rst.getDate(5).getTime()-sdt.getTime();
			        diffInSeconds = diffInMilleseconds/1000;
			        diffInMinutes = diffInSeconds/60;
			        diffInHours = diffInMinutes/60;
			        diffInDays = diffInHours/24;
			        
			         if (diffInDays<0)
							rfb.setEstock(rst.getInt(6));
					
			         if ((diffInDays>=0) && (diffInDays<=180))
							rfb.setEstock90(rst.getInt(6));
					
			         if ((diffInDays>180) && (diffInDays<360))
							rfb.setEstock360(rst.getInt(6));
					
			         rfb.setTvalue(rst.getInt(7));

					data.add(rfb);
			    }
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv3DAO.getInv4 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInv3DAO.Inv3.Connection.close "+e);
			  }
		}		
		return data;
	}	
		
	
	public List getHOInv4(Connection con, String typ, Date sdate,int dp,int eyear,int pcode,int uid ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;            
            
           	tblnm1=(typ+"_product08").toLowerCase();
           	tblnm2=(typ+"_batch08").toLowerCase();
           	tblnm3=(typ+"_branch08").toLowerCase();

    		sdt= new java.sql.Date (sdate.getTime());
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());

	        long diffInMilleseconds;
    		long diffInSeconds;
    		long diffInMinutes;
    		long diffInHours;
    		long diffInDays;
    		
/*           	String query=" select br.ter_name,b.bat_no,b.expiry,b.bat_clos,b.bat_clos*b.bat_mfgrt,b.bat_mfgrt "+
       		" from "+tblnm1+" as p, "+tblnm2+" as b, "+tblnm3+" as br "+
       		" where  p.pcode=b.bat_pcode and b.depo_code in (select depo_code from user_branch08 where user_id="+uid+") "+ 
       		" and b.depo_code=br.depo_code and TO_DAYS(b.expiry) - TO_DAYS('"+sdt+"') < 365 "+
       		" and b.bat_clos<>0 and p.mkt_year="+eyear+" and p.pcode="+pcode+" and b.bat_pcode="+pcode+" "+
       		" order by b.depo_code,b.bat_pcode,b.expiry" ;
*/
          	String query=" select br.ter_name,b.bat_no,b.expiry,b.bat_clos,b.bat_clos*b.bat_mfgrt,b.bat_mfgrt "+
       		" from "+tblnm1+" as p, "+tblnm2+" as b, "+tblnm3+" as br "+
       		" where  p.pcode=b.bat_pcode and b.depo_code in (select depo_code from user_branch08 where user_id="+uid+") "+ 
       		" and b.depo_code=br.depo_code and TO_DAYS(b.expiry) - TO_DAYS('"+sdt+"') "+
       		" and b.bat_clos<>0 and p.mkt_year="+eyear+" and p.pcode="+pcode+" and b.bat_pcode="+pcode+" "+
       		" order by b.depo_code,b.bat_pcode,b.expiry" ;           	
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
			    if (rst.getDate(3).getTime()>ndt.getTime())
			    {
					rfb = new SampleRepo3FormBean();
					rfb.setPname(rst.getString(1));
					rfb.setBat_no(rst.getString(2));
				    rfb.setExpiry(rst.getDate(3));
				    rfb.setBat_netrt(rst.getDouble(6));
				    
					diffInMilleseconds =  rst.getDate(3).getTime()-sdt.getTime();
			        diffInSeconds = diffInMilleseconds/1000;
			        diffInMinutes = diffInSeconds/60;
			        diffInHours = diffInMinutes/60;
			        diffInDays = diffInHours/24;
			        
			         if (diffInDays<0)
							rfb.setEstock(rst.getInt(4));
					
			         if ((diffInDays>=0) && (diffInDays<90))
							rfb.setEstock90(rst.getInt(4));
			         
			         if ((diffInDays>=90) && (diffInDays<180))
							rfb.setEstock180(rst.getInt(4));
					
			         if (diffInDays>=180)
							rfb.setEstock360(rst.getInt(4));
					
			         rfb.setTvalue(rst.getInt(5));

					data.add(rfb);
			    }
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv3DAO.getInv4 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInv3DAO.Inv3.Connection.close "+e);
			  }
		}		
		return data;
	}		
	
	
	
}
  
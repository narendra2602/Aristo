package com.aristo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo6FormBean;

public class SQLHORepo6DAO {
	
///////////////////////////////////////Branch Coding Start Here/////////////////////////////////
	public List getAllBranch(Connection con, int code,int smon,int emon,String tp,String typ,int code1,int loginid,int eyear) { 
		 
		HORepo6FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null;  
        ResultSet rst3=null;
		
		String unm=null;
        String vnm=null;		
		List<HORepo6FormBean> data = new ArrayList<HORepo6FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
        
            double sqty=0.00;
        	double sval=0.00;
            double fqty=0.00;
        	double fval=0.00;
        	double slqty=0.00;
        	double slval=0.00;
        	double expqty=0.00;
        	double expval=0.00;
        	double netqty=0.00;
        	double netval=0.00;
            
            double gsqty=0.00;
        	double gsval=0.00;
            double gfqty=0.00;
        	double gfval=0.00;
        	double gslqty=0.00;
        	double gslval=0.00;
        	double gexpqty=0.00;
        	double gexpval=0.00;
        	double gnetqty=0.00;
        	double gnetval=0.00;
            
        	tblnm3="user_branch08";
        	
            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
            
            txt2=" Branch Wise Sales Detail for the Month of "; 
            
///////////////////////////////////////Product Master//////////////////////////////
            String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			ts1.setInt(2, eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="Product-> "+trec.getString(1)+","+trec.getString(2);
			trec.close();
			ts1.close();
    			
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name from "+tblnm3+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
	        ps1 = con.prepareStatement(query22);
	        ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        rst1 = ps1.executeQuery();
			
			while (rst1.next())  //////////////////Branch Master Loop Start//////////////////////// 
			{
				rfb = new HORepo6FormBean();
				rfb.setName(rst1.getString(2));
               		        
                    //////////////// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		 			sqty=0.00;
		        	sval=0.00;
		 			fqty=0.00;
		        	fval=0.00;
		        	slqty=0.00;
		        	slval=0.00;
		        	expqty=0.00;
		        	expval=0.00;
		        	netqty=0.00;
		        	netval=0.00;
		        	
		 			while (mrec.next())    ////////////////////Month Loop Start////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 		       
		 			    unm="qty"+mrec.getString(3);
		 	            vnm="val"+mrec.getString(3);
                    ///////////////////////HQ Detail Query///////////////////////////////////		 	            	
		        	String query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+"),sum(f"+unm+"),sum(f"+vnm+") from "+tblnm+
		            " where pr_code=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code"; 
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code);
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,eyear);
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())           /////////////////HQ Detail Start/////////////////////
			        {
			        		 sqty = sqty+rst3.getInt(1);
			        		 fqty = fqty+rst3.getInt(13);
			        		 slqty = slqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 sval = sval+rst3.getDouble(7);
			        		 fval = fval+rst3.getDouble(14);
			        		 slval=slval+rst3.getDouble(8);
			        		 expval=expval+(rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			        		 
			         }	    /////////////////HQ Detail End/////////////////////

			        }      ////////////////////Month Loop End////////////////////
		 			
		 			 netqty = sqty-slqty-expqty;
		 			 netval = sval-slval-expval;
		 			 rfb.setSqty(sqty);
		 			 rfb.setFqty(fqty);
		 			 rfb.setSlqty(slqty);
		 			 rfb.setExpqty(expqty);
		 			 rfb.setSlval(slval);
		 			 rfb.setSval(sval);
		 			 rfb.setFval(fval);
		 			 rfb.setExpval(expval);
		 			 rfb.setNetqty(netqty);
		 			 rfb.setNetval(netval);
		 			
 	 				 rfb.setHead1(txt1+txt2+txt5);
		 			 
		 			 gsqty = gsqty +sqty;
		 			 gfqty = gfqty +fqty;
 	 				 gslqty = gslqty+slqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsval = gsval+ sval;
	        		 gfval = gfval+ fval;
	        		 gslval = gslval + slval;
	        		 gexpval = gexpval + expval;
	        		 
		 			 gnetqty = gsqty-gslqty-gexpqty;
		 			 gnetval = gsval-gslval-gexpval;
	 				
	                 data.add(rfb); 				
				} 
					 rfb = new HORepo6FormBean();
 					 rfb.setName("TOTAL:");
		 			 rfb.setSqty(gsqty);
		 			 rfb.setFqty(gfqty);
		 			 rfb.setSlqty(gslqty);
		 			 rfb.setExpqty(gexpqty);
		 			 rfb.setSlval(gslval);
		 			 rfb.setSval(gsval);
		 			 rfb.setFval(gfval);
		 			 rfb.setExpval(gexpval);
		 			 rfb.setNetqty(gnetqty);
		 			 rfb.setNetval(gnetval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo6DAO.getAllBranch " + e); 
		}
		finally {
			try 
			{
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo6DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
///////////////////////////////////////Branch Coding End Here/////////////////////////////////


///////////////////////////////////////Selective Product HQ wise Coding Start Here/////////////////////////////////
	public List getAllHQ(Connection con, int code,int smon,int emon,String tp,String typ,int code1,int loginid,int eyear) { 
		 
		HORepo6FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
		
		String unm=null;
        String vnm=null;		
		List<HORepo6FormBean> data = new ArrayList<HORepo6FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
        
            double sqty=0.00;
        	double sval=0.00;
        	double slqty=0.00;
        	double slval=0.00;
        	double expqty=0.00;
        	double expval=0.00;
        	double netqty=0.00;
        	double netval=0.00;
            
            double bsqty=0.00;
        	double bsval=0.00;
        	double bslqty=0.00;
        	double bslval=0.00;
        	double bexpqty=0.00;
        	double bexpval=0.00;
        	double bnetqty=0.00;
        	double bnetval=0.00;

        	double gsqty=0.00;
        	double gsval=0.00;
        	double gslqty=0.00;
        	double gslval=0.00;
        	double gexpqty=0.00;
        	double gexpval=0.00;
        	double gnetqty=0.00;
        	double gnetval=0.00;
            
            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3="user_branch08";   	        
   	        tblnm4=(tp+"_branch08").toLowerCase();
            
            txt2=" Branch Wise Sales Detail for the Month of "; 
            
///////////////////////////////////////Product Master//////////////////////////////
            String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			ts1.setInt(2, eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
			trec.close();
			ts1.close();
    			
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select t.ter_code,t.ter_name,b.depo_code,b.ter_name from "+tblnm3+" as d, "+
            				  tblnm2+" as t, "+tblnm4+" as b where d.depo_code=t.depo_code and " +
            				  "d.depo_code=b.depo_code and b.depo_code=t.depo_code and d.user_id=? " +
            				  " and d.status=? and t.mkt_year=? ";
	        ps1 = con.prepareStatement(query22);
	        ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        ps1.setInt(3, eyear);
	        rst1 = ps1.executeQuery();
			int dcode=0;
			String bname=null;
			boolean flag=true;
			while (rst1.next())  //////////////////Branch Master Loop Start//////////////////////// 
			{
				
				if (flag)
				{
					dcode=rst1.getInt(3);
					bname=rst1.getString(4);
					flag=false;
				}
				
				if (dcode!=rst1.getInt(3))
				{
					 rfb = new HORepo6FormBean();
					 rfb.setColor(2);
					 rfb.setName(bname);
		 			 rfb.setSqty(bsqty);
		 			 rfb.setSlqty(bslqty);
		 			 rfb.setExpqty(bexpqty);
		 			 rfb.setSlval(bslval);
		 			 rfb.setSval(bsval);
		 			 rfb.setExpval(bexpval);
		 			 rfb.setNetqty(bnetqty);
		 			 rfb.setNetval(bnetval);
	                 data.add(rfb); 				
		 			 bsqty=0.00;
		        	 bsval=0.00;
		        	 bslqty=0.00;
		        	 bslval=0.00;
		        	 bexpqty=0.00;
		        	 bexpval=0.00;
		        	 bnetqty=0.00;
		        	 bnetval=0.00;
		        	 dcode=rst1.getInt(3);
					 bname=rst1.getString(4);
					
				}
				rfb = new HORepo6FormBean();
				rfb.setName(rst1.getString(2));
               		        
                    //////////////// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		 			sqty=0.00;
		        	sval=0.00;
		        	slqty=0.00;
		        	slval=0.00;
		        	expqty=0.00;
		        	expval=0.00;
		        	netqty=0.00;
		        	netval=0.00;
		        	
		 			while (mrec.next())    ////////////////////Month Loop Start////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 		       
		 			    unm="qty"+mrec.getString(3);
		 	            vnm="val"+mrec.getString(3);
                    ///////////////////////HQ Detail Query///////////////////////////////////		 	            	
		        	String query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
		            " where pr_code=?  and tr_cd=? and mkt_year=? and depo_code=? group by tr_cd order by depo_code,tr_cd"; 
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code);
			        ps3.setInt(2,rst1.getInt(1)); 
			        ps3.setInt(3,eyear);
			        ps3.setInt(4, rst1.getInt(3));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())           /////////////////HQ Detail Start/////////////////////
			        {
			        		 sqty = sqty+rst3.getInt(1);
			        		 slqty = slqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 sval = sval+rst3.getDouble(7);
			        		 slval=slval+rst3.getDouble(8);
			        		 expval=expval+(rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			        		 
			         }	    /////////////////HQ Detail End/////////////////////

			        }      ////////////////////Month Loop End////////////////////
		 			
		 			 netqty = sqty-slqty-expqty;
		 			 netval = sval-slval-expval;
		 			 rfb.setSqty(sqty);
		 			 rfb.setSlqty(slqty);
		 			 rfb.setExpqty(expqty);
		 			 rfb.setSlval(slval);
		 			 rfb.setSval(sval);
		 			 rfb.setExpval(expval);
		 			 rfb.setNetqty(netqty);
		 			 rfb.setNetval(netval);
		 			
 	 				 rfb.setHead1(txt1+txt2+txt5);

 	 				 
		 			 bsqty = bsqty +sqty;
 	 				 bslqty = bslqty+slqty;
	        		 bexpqty = bexpqty  + expqty;

	        		 bsval = bsval+ sval;
	        		 bslval = bslval + slval;
	        		 bexpval = bexpval + expval;
	        		 
		 			 bnetqty = bsqty-bslqty-bexpqty;
		 			 bnetval = bsval-bslval-bexpval;

		 			 
		 			 gsqty = gsqty +sqty;
 	 				 gslqty = gslqty+slqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsval = gsval+ sval;
	        		 gslval = gslval + slval;
	        		 gexpval = gexpval + expval;
	        		 
		 			 gnetqty = gsqty-gslqty-gexpqty;
		 			 gnetval = gsval-gslval-gexpval;
	 				
	                 data.add(rfb); 				
				} 
					 rfb = new HORepo6FormBean(); 
					 rfb.setColor(2);
					 rfb.setName(bname);
		 			 rfb.setSqty(bsqty);
		 			 rfb.setSlqty(bslqty);
		 			 rfb.setExpqty(bexpqty);
		 			 rfb.setSlval(bslval);
		 			 rfb.setSval(bsval);
		 			 rfb.setExpval(bexpval);
		 			 rfb.setNetqty(bnetqty);
		 			 rfb.setNetval(bnetval);
		             data.add(rfb); 				

             		 rfb = new HORepo6FormBean();
 					 rfb.setName("TOTAL:");
		 			 rfb.setSqty(gsqty);
		 			 rfb.setSlqty(gslqty);
		 			 rfb.setExpqty(gexpqty);
		 			 rfb.setSlval(gslval);
		 			 rfb.setSval(gsval);
		 			 rfb.setExpval(gexpval);
		 			 rfb.setNetqty(gnetqty);
		 			 rfb.setNetval(gnetval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo6DAO.getAllBranch " + e); 
		}
		finally {
			try 
			{
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo6DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
///////////////////////////////////////Branch Coding End Here/////////////////////////////////


}   
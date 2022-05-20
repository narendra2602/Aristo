package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo8DAO {

	public List getAllHQ(Connection con, int code,int uv,int emon,int stype,int eyear,int depo_code,String tp,int uid) { 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String wtxt=null;
            String query3=null;
            
            int index=emon+1;
            int k=0;
            int hqty =0;
            float hval=0.0f;
            int[] vqty ; 
            float[] vval;
            int fs=0;
            
        	tblnm=tp+"_HQDetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
            
            if (stype==1)
            	wtxt=" (GROSS) ";
            if (stype==2)
            	wtxt=" (CREDIT) ";
            if (stype==3)
            	wtxt=" (NET) ";
            
			if (uv==1)
               txt2="     H.Q. WISE UNIT SALES TREND "+wtxt+ "FROM "; 
			if (uv==2)
	           txt2="     H.Q. WISE VALUE SALES TREND "+wtxt+ "FROM "; 
			if (uv==3)
	           txt2="     H.Q. WISE UNIT/VALUE SALES TREND "+wtxt+ " FROM "; 
			 
		vqty  = new int[index]; 
		vval = new float[index];
			
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
		String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
		ps12 = con.prepareStatement(query12);
		ps12.setInt(1,depo_code);
		ps12.setString(2,"HQT09"); 
		ps12.setString(3,tp); 
		rst12 = ps12.executeQuery();
		
		if (rst12.next())
		txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);
		
		rst12.close();
		ps12.close();
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
	      String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
		  ts1 = con.prepareStatement(terrec);
		  ts1.setInt(1, code);
		  ts1.setInt(2, eyear);
		  trec = ts1.executeQuery();
		  if (trec.next())
	          txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
		   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		  ms1 = con.prepareStatement(month);
 		  ms1.setInt(1,emon);
 		  ms1.setInt(2,eyear);
  		  mrec = ms1.executeQuery();    
  		  StringBuffer noofrep= new StringBuffer("");
		  while (mrec.next())
		  {
			      if(mrec.getString(3).equals("DEC"))
			    	  noofrep.append("h.DECM");
			      else
			    	  noofrep.append("h."+mrec.getString(3)); 
			      if(!mrec.isLast())
			    	  noofrep.append("+");
		  }
  		  
		  String totalRep=noofrep.toString();
//////////////////////////////////////Head Quater Query///////////////////////////////////////////            
//          String query1 ="Select h.ter_code,h.ter_name,h.no_of_rep,u.depo_code from "+tblnm2+" as h,user_ter as u " +
//          " where h.depo_code=u.depo_code and h.ter_code=u.ter_code and user_id="+uid+" and ter_pt=? " +
//          " and h.mkt_year=? order by h.ter_code ";
  		  
          String query1 ="Select h.ter_code,h.ter_name,"+totalRep+",u.depo_code from "+tblnm2+" as h,user_ter as u " +
          " where h.depo_code=u.depo_code and h.ter_code=u.ter_code and user_id="+uid+" and ter_pt=? " +
          " and h.mkt_year=? order by h.ter_code ";

          
          ps1 = con.prepareStatement(query1); 
		  ps1.setString(1, tp);
		  ps1.setInt(2,eyear);
		  rst1 = ps1.executeQuery();
			  
			while (rst1.next()) ////////////////////HQ Loop Start/////////////////////   
			{
				rfb = new Repo2FormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
                rfb.setNo_of_mr(rst1.getInt(3));
                rfb.setQty2(uv);
                k=0;  
                hqty=0;
                hval=0.0f;
                fs=fs+rst1.getInt(3);
                
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			    
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			             txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
 	           
		 			        unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);
		if (stype==1)
		{
		query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		"group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		if (stype==2)
		{
		query3="Select sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
			         " sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
			         " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		if (stype==3)
		{
		query3="Select sum("+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
                     " sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
                     " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
                     " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
       	ps3 = con.prepareStatement(query3); 
        ps3.setInt(1,rst1.getInt(1)); 
        ps3.setInt(2,code);
        ps3.setInt(3,rst1.getInt(4));
        ps3.setInt(4,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
			        	 if ((uv==1) || (uv==3))
		 			     {	 
			        		if (stype==1)
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==2)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            hqty=hqty+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            vqty[k]=vqty[k]+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
			        		}

			        		if (stype==3)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6))));
                            hqty=hqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
                            vqty[k]=vqty[k]+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
			        		}
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
				        	if (stype==1)
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
                            
				        	if (stype==2)
				        	{
                            rfb.setVal1(k, (rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)));
                            hval=hval+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
                            vval[k]=vval[k]+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
				        	}
                            
				        	if (stype==3)
				        	{
                            rfb.setVal1(k, (rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            hval=hval+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            vval[k]=vval[k]+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
				        	}
		 			     }   
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb); 				
				} ///////////////////HQ Loop End/////////////////////////
			 		 rfb = new Repo2FormBean();
			 		 hqty=0;
			 		 hval=0;
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
 					 
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }

	                 data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLRepo8DAO.getAllHQrepo8 " + e); 
		}
		
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in HQ-SQLRepo8DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
} 
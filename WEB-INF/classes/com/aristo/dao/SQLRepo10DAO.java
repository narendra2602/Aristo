package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo10FormBean;

public class SQLRepo10DAO {

	public List getAllStk(Connection con, String code,int uv, int rep,int emon,int eyear,int depo_code,String tp) { 
		  
		Repo10FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		List<Repo10FormBean> data = new ArrayList<Repo10FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5 =null;
            String wtxt=null;
            String query1=null;
            int index=emon+1;
            double hqty =0.00;
            double hval=0.00;
            double[] vqty;
            double[] vval;
            int mno=0;
            int vno=0;
            
        	tblnm=(tp+"_stockiest08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_account08").toLowerCase();
            
            if (rep==1)
            	wtxt=" GROSS - ";
            if (rep==2)
            	wtxt=" CREDIT - ";
            if (rep==3)
            	wtxt=" NET - ";
            
			 if (uv==1)
                txt2="     "+wtxt+"    PRODUCT WISE UNIT TREND FROM "; 
			 if (uv==2)
	            txt2="     "+wtxt+"    PRODUCT WISE VALUE TREND FROM "; 
			 if (uv==3)
	            txt2="     "+wtxt+"    PRODUCT WISE UNIT/VALUE TREND FROM ";
			 
		vqty=new double[index]; 
		vval=new double[index];
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
        String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
        ps12 = con.prepareStatement(query12);
        ps12.setInt(1,depo_code);
        ps12.setString(2,"STK09"); 
        ps12.setString(3,tp); 
        rst12 = ps12.executeQuery();
        
        if (rst12.next())
        	txt3= rst12.getString(1)+", TIME: "+rst12.getString(2);
        
        rst12.close();
        ps12.close();
	   
////////////////////////////////////Account Master Query///////////////////////////////////////////                
        String terrec = "Select mac_code,mac_name from "+tblnm2+" where mac_code=? and mkt_year=? ";  
    	ts1 = con.prepareStatement(terrec);
    	ts1.setString(1,code);
    	ts1.setInt(2,eyear);
    	trec = ts1.executeQuery();
    	if (trec.next())
            txt1="STOCKIEST : " +trec.getString(2);
    	   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year,mnth_ord from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		ms1 = con.prepareStatement(month);
 		ms1.setInt(1,emon);
 		ms1.setInt(2,eyear);
  		mrec = ms1.executeQuery();    	   

		 if (rep==1)			        
 		 {
	  	 	query1= "SELECT p.pcode,p.pname," +
		 	" s.qtyoct,s.qtynov,s.qtydec,s.qtyjan,s.qtyfeb,s.qtymar," +
		 	" s.qtyapr,s.qtymay,s.qtyjun,s.qtyjul,s.qtyaug,s.qtysep, " +
		 	" s.valoct,s.valnov,s.valdec,s.valjan,s.valfeb,s.valmar," +
		 	" s.valapr,s.valmay,s.valjun,s.valjul,s.valaug,s.valsep " +
		 	" FROM "+tblnm1+" p,"+tblnm+" s WHERE p.MKT_YEAR = ? " +
		 	" and  s.mkt_year = ? and s.depo_code = ? and s.stk_code=? and 	p.pcode = s.pr_code " +
		 	" and  p.mkt_year = s.mkt_year " +
		 	" order by p.pname,s.pr_code";
 		 }
		 
		 if (rep==2)			        
 		 {
	  	 	query1= "SELECT p.pcode,p.pname," +
 		 	"s.eqtyoct+s.bqtyoct+s.rqtyoct+s.sqtyoct+s.pqtyoct cqoct,"+
 		 	"s.eqtynov+s.bqtynov+s.rqtynov+s.sqtynov+s.pqtynov cqnov,"+
 		 	"s.eqtydec+s.bqtydec+s.rqtydec+s.sqtydec+s.pqtydec cqdec,"+
 		 	"s.eqtyjan+s.bqtyjan+s.rqtyjan+s.sqtyjan+s.pqtyjan cqjan,"+
 		 	"s.eqtyfeb+s.bqtyfeb+s.rqtyfeb+s.sqtyfeb+s.pqtyfeb cqfeb,"+
 		 	"s.eqtymar+s.bqtymar+s.rqtymar+s.sqtymar+s.pqtymar cqmar,"+
 		 	"s.eqtyapr+s.bqtyapr+s.rqtyapr+s.sqtyapr+s.pqtyapr cqapr,"+
 		 	"s.eqtymay+s.bqtymay+s.rqtymay+s.sqtymay+s.pqtymay cqmay,"+
 		 	"s.eqtyjun+s.bqtyjun+s.rqtyjun+s.sqtyjun+s.pqtyjun cqjun,"+
 		 	"s.eqtyjul+s.bqtyjul+s.rqtyjul+s.sqtyjul+s.pqtyjul cqjul,"+
 		 	"s.eqtyaug+s.bqtyaug+s.rqtyaug+s.sqtyaug+s.pqtyaug cqaug,"+
 		 	"s.eqtysep+s.bqtysep+s.rqtysep+s.sqtysep+s.pqtysep cqsep, "+
 		 	"s.evaloct+s.bvaloct+s.rvaloct+s.svaloct+s.pvaloct cvoct,"+
 		 	"s.evalnov+s.bvalnov+s.rvalnov+s.svalnov+s.pvalnov cvnov,"+
 		 	"s.evaldec+s.bvaldec+s.rvaldec+s.svaldec+s.pvaldec cvdec,"+
 		 	"s.evaljan+s.bvaljan+s.rvaljan+s.svaljan+s.pvaljan cvjan,"+
 		 	"s.evalfeb+s.bvalfeb+s.rvalfeb+s.svalfeb+s.pvalfeb cvfeb,"+
 		 	"s.evalmar+s.bvalmar+s.rvalmar+s.svalmar+s.pvalmar cvmar,"+
 		 	"s.evalapr+s.bvalapr+s.rvalapr+s.svalapr+s.pvalapr cvapr,"+
 		 	"s.evalmay+s.bvalmay+s.rvalmay+s.svalmay+s.pvalmay cvmay,"+
 		 	"s.evaljun+s.bvaljun+s.rvaljun+s.svaljun+s.pvaljun cvjun,"+
 		 	"s.evaljul+s.bvaljul+s.rvaljul+s.svaljul+s.pvaljul cvjul,"+
 		 	"s.evalaug+s.bvalaug+s.rvalaug+s.svalaug+s.pvalaug cvaug,"+
 		 	"s.evalsep+s.bvalsep+s.rvalsep+s.svalsep+s.pvalsep cvsep "+
		 	" FROM "+tblnm1+" p,"+tblnm+" s WHERE p.MKT_YEAR = ? " +
		 	" and  s.mkt_year = ? and s.depo_code = ? and s.stk_code=? and 	p.pcode = s.pr_code " +
		 	" and  p.mkt_year = s.mkt_year " +
		 	" order by p.pname,s.pr_code";
 		 }
		 
		 if (rep==3)			        
 		 {
	  	 	query1= "SELECT p.pcode,p.pname," +
 		 	"qtyoct-(s.eqtyoct+s.bqtyoct+s.rqtyoct+s.sqtyoct+s.pqtyoct) cqoct,"+
 		 	"qtynov-(s.eqtynov+s.bqtynov+s.rqtynov+s.sqtynov+s.pqtynov) cqnov,"+
 		 	"qtydec-(s.eqtydec+s.bqtydec+s.rqtydec+s.sqtydec+s.pqtydec) cqdec,"+
 		 	"qtyjan-(s.eqtyjan+s.bqtyjan+s.rqtyjan+s.sqtyjan+s.pqtyjan) cqjan,"+
 		 	"qtyfeb-(s.eqtyfeb+s.bqtyfeb+s.rqtyfeb+s.sqtyfeb+s.pqtyfeb) cqfeb,"+
 		 	"qtymar-(s.eqtymar+s.bqtymar+s.rqtymar+s.sqtymar+s.pqtymar) cqmar,"+
 		 	"qtyapr-(s.eqtyapr+s.bqtyapr+s.rqtyapr+s.sqtyapr+s.pqtyapr) cqapr,"+
 		 	"qtymay-(s.eqtymay+s.bqtymay+s.rqtymay+s.sqtymay+s.pqtymay) cqmay,"+
 		 	"qtyjun-(s.eqtyjun+s.bqtyjun+s.rqtyjun+s.sqtyjun+s.pqtyjun) cqjun,"+
 		 	"qtyjul-(s.eqtyjul+s.bqtyjul+s.rqtyjul+s.sqtyjul+s.pqtyjul) cqjul,"+
 		 	"qtyaug-(s.eqtyaug+s.bqtyaug+s.rqtyaug+s.sqtyaug+s.pqtyaug) cqaug,"+
 		 	"qtysep-(s.eqtysep+s.bqtysep+s.rqtysep+s.sqtysep+s.pqtysep) cqsep,"+
 		 	"valoct-(s.evaloct+s.bvaloct+s.rvaloct+s.svaloct+s.pvaloct) cvoct,"+
 		 	"valnov-(s.evalnov+s.bvalnov+s.rvalnov+s.svalnov+s.pvalnov) cvnov,"+
 		 	"valdec-(s.evaldec+s.bvaldec+s.rvaldec+s.svaldec+s.pvaldec) cvdec,"+
 		 	"valjan-(s.evaljan+s.bvaljan+s.rvaljan+s.svaljan+s.pvaljan) cvjan,"+
 		 	"valfeb-(s.evalfeb+s.bvalfeb+s.rvalfeb+s.svalfeb+s.pvalfeb) cvfeb,"+
 		 	"valmar-(s.evalmar+s.bvalmar+s.rvalmar+s.svalmar+s.pvalmar) cvmar,"+
 		 	"valapr-(s.evalapr+s.bvalapr+s.rvalapr+s.svalapr+s.pvalapr) cvapr,"+
 		 	"valmay-(s.evalmay+s.bvalmay+s.rvalmay+s.svalmay+s.pvalmay) cvmay,"+
 		 	"valjun-(s.evaljun+s.bvaljun+s.rvaljun+s.svaljun+s.pvaljun) cvjun,"+
 		 	"valjul-(s.evaljul+s.bvaljul+s.rvaljul+s.svaljul+s.pvaljul) cvjul,"+
 		 	"valaug-(s.evalaug+s.bvalaug+s.rvalaug+s.svalaug+s.pvalaug) cvaug,"+
 		 	"valsep-(s.evalsep+s.bvalsep+s.rvalsep+s.svalsep+s.pvalsep) cvsep "+
		 	" FROM "+tblnm1+" p,"+tblnm+" s WHERE p.MKT_YEAR = ? " +
		 	" and  s.mkt_year = ? and s.depo_code = ? and s.stk_code=? and 	p.pcode = s.pr_code " +
		 	" and  p.mkt_year = s.mkt_year " +
		 	" order by p.pname,s.pr_code";
 		 }  		
		 
		 
//////////////////////////////////////Product Master Query///////////////////////////////////////////            
//        String query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mkt_year=?  group by mcode order by mname";
		ps1 = con.prepareStatement(query1);
        ps1.setInt(1,eyear);
        ps1.setInt(2,eyear);
        ps1.setInt(3,depo_code);
        ps1.setString(4,code);
        rst1 = ps1.executeQuery();
			  
		 while (rst1.next()) ////////////////////Main query  Loop Start/////////////////////   
			{
				rfb = new Repo10FormBean();
                int k=0;  
                hqty=0.00;
                hval=0.00;

 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			     if (mrec.isFirst())	
		 				     txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			             txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
			                mno=mrec.getInt(5)+2;	 			             
 			                vno=mrec.getInt(5)+14;	 			             
        		 			        
        
						rfb.setName(rst1.getString(2));
		                rfb.setMon(index);
		                rfb.setUv(uv);
		                
		 			     
		 			     if ((uv==1) || (uv==3))
		 			     {	 
		 			        rfb.setUhead(k, mrec.getString(3)+" UNITS");
                            rfb.setQty1(k, rst1.getDouble(mno));
                            hqty=hqty+rst1.getDouble(mno);
                            vqty[k]=vqty[k]+rst1.getDouble(mno);
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
		 			    	rfb.setVhead(k, mrec.getString(3)+" VALUE");
                            rfb.setVal1(k, rst1.getDouble(vno));
                            hval=hval+rst1.getDouble(vno);
                            vval[k]=vval[k]+rst1.getDouble(vno);
		 			     }
			         
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setUhead(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setVhead(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setHead1(txt1+txt2+txt5);
	 		         rfb.setLupdate(txt3);
	 			     if ((hqty!=0) || (hval!=0))
	 			    	 data.add(rfb); 				
				} ///////////////////Main query Loop End/////////////////////////
			 		 rfb = new Repo10FormBean();
 					 rfb.setName("TOTAL :");
 					 hqty=0.00;
 					 hval=0.00;
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
			
			System.out.println("========Exception in SQLRepo10DAO.getAllStockiest10 " + e); 
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
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo10DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
	
}
 
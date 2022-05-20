package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo18FormBean;

public class SQLRepo18DAO {
	
	public List getAllHQ(Connection con, int code,int uv,int rtype,int emon,int eyear,int depo_code,String tp) { 
		   
		Repo18FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps22=null; 
        ResultSet rst22=null;
		
        String wtxt=null;
		List<Repo18FormBean> data = new ArrayList<Repo18FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String query22=null;
            int index =emon+1;
            int k=0;
            double hqty=0.00;
            double hval=0.00;
            
            double thqty=0.00;
            double thval=0.00;
            double[] vqty;
            double[] vval;
            double[] tvqty;
            double[] tvval;
            double[] qty;
            double[] val;
            String[] head1;
            String[] head2;

            int a=0;
            int mno=0;
            int vno=0;
            int tercd=0;
            String ternm=null;
            boolean flag=true;
            
        	tblnm=(tp+"_stockiest08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
        	tblnm2=(tp+"_account08").toLowerCase();
        	tblnm3=(tp+"_hq_master08").toLowerCase();            	
            
            if (rtype==1)
            	wtxt= " (GROSS) ";
            if (rtype==2)
            	wtxt= " (CREDIT) ";
            if (rtype==3)
            	wtxt= " (NET) ";

            if (uv==1)
                txt2="     STOCKIEST WISE UNIT SALES TREND" + wtxt + "FROM "; 
			 if (uv==2)
	            txt2="     STOCKIEST WISE VALUE SALES TREND" + wtxt + "FROM "; 
			 if (uv==3)
	            txt2="     STOCKIEST WISE UNIT/VALUE SALES TREND" + wtxt + "FROM ";
			 
	
	      vqty = new double[index];
	      vval = new double[index];
	
	      tvqty = new double[index];
	      tvval = new double[index];
	
	      qty = new double[index];
	      val = new double[index];
	      head1 = new String[index];
	      head2 = new String[index];

//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
		  String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
		  ps12 = con.prepareStatement(query12);
		  ps12.setInt(1,depo_code);
		  ps12.setString(2,"STK09"); 
		  ps12.setString(3,tp); 
		  rst12 = ps12.executeQuery();
	 	
 	 	  if (rst12.next())
 		  txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);
		
		  rst12.close();
		  ps12.close();
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
          String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=? ";  
    	  ts1 = con.prepareStatement(terrec);
    	  ts1.setInt(1, code);
    	  ts1.setInt(2, eyear);
    	  trec = ts1.executeQuery();
    	  if (trec.next())
              txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);

/////////////////////////////////////Month File Query////////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year,mnth_ord from monthfl where mnth_ord<=? and mkt_year=?  order by mnth_ord";  
 		  ms1 = con.prepareStatement(month);
 		  ms1.setInt(1,emon);
 		  ms1.setInt(2,eyear);
  		  mrec = ms1.executeQuery();
  		  
 		 if (rtype==1)			        
 		 {
	  	 	query22= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
		 	" s.qtyoct,s.qtynov,s.qtydec,s.qtyjan,s.qtyfeb,s.qtymar," +
		 	" s.qtyapr,s.qtymay,s.qtyjun,s.qtyjul,s.qtyaug,s.qtysep, " +
		 	" s.valoct,s.valnov,s.valdec,s.valjan,s.valfeb,s.valmar," +
		 	" s.valapr,s.valmay,s.valjun,s.valjul,s.valaug,s.valsep " +
		 	" FROM "+tblnm2+" a, "+tblnm3+" h,"+tblnm+" s WHERE a.MKT_YEAR = ? " +
		 	" AND a.DEPO_CODE = ? 	and h.mkt_year = ? and h.depo_code = ? and a.depo_code = h.depo_code " +
		 	" and a.mterr_code = h.ter_code and s.mkt_year = ? and s.depo_code = ? and s.pr_code=? and 	a.mac_code = s.stk_code " +
		 	" and a.depo_code = s.depo_code and a.mkt_year = s.mkt_year and h.ter_code = s.tr_cd and h.mkt_year = s.mkt_year " +
		 	" order by a.mterr_code,a.mac_name";
 		 }
 		 
 	      if (rtype==2)
 	      {
 		 	query22= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
 		 	"sum(s.eqtyoct)+sum(s.bqtyoct)+sum(s.rqtyoct)+sum(s.sqtyoct)+sum(s.pqtyoct) cqoct,"+
 		 	"sum(s.eqtynov)+sum(s.bqtynov)+sum(s.rqtynov)+sum(s.sqtynov)+sum(s.pqtynov) cqnov,"+
 		 	"sum(s.eqtydec)+sum(s.bqtydec)+sum(s.rqtydec)+sum(s.sqtydec)+sum(s.pqtydec) cqdec,"+
 		 	"sum(s.eqtyjan)+sum(s.bqtyjan)+sum(s.rqtyjan)+sum(s.sqtyjan)+sum(s.pqtyjan) cqjan,"+
 		 	"sum(s.eqtyfeb)+sum(s.bqtyfeb)+sum(s.rqtyfeb)+sum(s.sqtyfeb)+sum(s.pqtyfeb) cqfeb,"+
 		 	"sum(s.eqtymar)+sum(s.bqtymar)+sum(s.rqtymar)+sum(s.sqtymar)+sum(s.pqtymar) cqmar,"+
 		 	"sum(s.eqtyapr)+sum(s.bqtyapr)+sum(s.rqtyapr)+sum(s.sqtyapr)+sum(s.pqtyapr) cqapr,"+
 		 	"sum(s.eqtymay)+sum(s.bqtymay)+sum(s.rqtymay)+sum(s.sqtymay)+sum(s.pqtymay) cqmay,"+
 		 	"sum(s.eqtyjun)+sum(s.bqtyjun)+sum(s.rqtyjun)+sum(s.sqtyjun)+sum(s.pqtyjun) cqjun,"+
 		 	"sum(s.eqtyjul)+sum(s.bqtyjul)+sum(s.rqtyjul)+sum(s.sqtyjul)+sum(s.pqtyjul) cqjul,"+
 		 	"sum(s.eqtyaug)+sum(s.bqtyaug)+sum(s.rqtyaug)+sum(s.sqtyaug)+sum(s.pqtyaug) cqaug,"+
 		 	"sum(s.eqtysep)+sum(s.bqtysep)+sum(s.rqtysep)+sum(s.sqtysep)+sum(s.pqtysep) cqsep, "+
 		 	"sum(s.evaloct)+sum(s.bvaloct)+sum(s.rvaloct)+sum(s.svaloct)+sum(s.pvaloct) cvoct,"+
 		 	"sum(s.evalnov)+sum(s.bvalnov)+sum(s.rvalnov)+sum(s.svalnov)+sum(s.pvalnov) cvnov,"+
 		 	"sum(s.evaldec)+sum(s.bvaldec)+sum(s.rvaldec)+sum(s.svaldec)+sum(s.pvaldec) cvdec,"+
 		 	"sum(s.evaljan)+sum(s.bvaljan)+sum(s.rvaljan)+sum(s.svaljan)+sum(s.pvaljan) cvjan,"+
 		 	"sum(s.evalfeb)+sum(s.bvalfeb)+sum(s.rvalfeb)+sum(s.svalfeb)+sum(s.pvalfeb) cvfeb,"+
 		 	"sum(s.evalmar)+sum(s.bvalmar)+sum(s.rvalmar)+sum(s.svalmar)+sum(s.pvalmar) cvmar,"+
 		 	"sum(s.evalapr)+sum(s.bvalapr)+sum(s.rvalapr)+sum(s.svalapr)+sum(s.pvalapr) cvapr,"+
 		 	"sum(s.evalmay)+sum(s.bvalmay)+sum(s.rvalmay)+sum(s.svalmay)+sum(s.pvalmay) cvmay,"+
 		 	"sum(s.evaljun)+sum(s.bvaljun)+sum(s.rvaljun)+sum(s.svaljun)+sum(s.pvaljun) cvjun,"+
 		 	"sum(s.evaljul)+sum(s.bvaljul)+sum(s.rvaljul)+sum(s.svaljul)+sum(s.pvaljul) cvjul,"+
 		 	"sum(s.evalaug)+sum(s.bvalaug)+sum(s.rvalaug)+sum(s.svalaug)+sum(s.pvalaug) cvaug,"+
 		 	"sum(s.evalsep)+sum(s.bvalsep)+sum(s.rvalsep)+sum(s.svalsep)+sum(s.pvalsep) cvsep "+
 		 	" FROM "+tblnm2+" a, "+tblnm3+" h,"+tblnm+" s WHERE a.MKT_YEAR = ? " +
 		 	" AND a.DEPO_CODE = ? 	and h.mkt_year = ? and h.depo_code = ? and a.depo_code = h.depo_code " +
 		 	" and a.mterr_code = h.ter_code and s.mkt_year = ? and s.depo_code = ? and s.pr_code=? and 	a.mac_code = s.stk_code " +
 		 	" and a.depo_code = s.depo_code and a.mkt_year = s.mkt_year and h.ter_code = s.tr_cd and h.mkt_year = s.mkt_year " +
 		 	" group by s.stk_code order by a.mterr_code,a.mac_name";
 	      } 	
 	      
 	      if (rtype==3)
 	      {
 		 	query22= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
 		 	"sum(qtyoct)-(sum(s.eqtyoct)+sum(s.bqtyoct)+sum(s.rqtyoct)+sum(s.sqtyoct)+sum(s.pqtyoct)) cqoct,"+
 		 	"sum(qtynov)-(sum(s.eqtynov)+sum(s.bqtynov)+sum(s.rqtynov)+sum(s.sqtynov)+sum(s.pqtynov)) cqnov,"+
 		 	"sum(qtydec)-(sum(s.eqtydec)+sum(s.bqtydec)+sum(s.rqtydec)+sum(s.sqtydec)+sum(s.pqtydec)) cqdec,"+
 		 	"sum(qtyjan)-(sum(s.eqtyjan)+sum(s.bqtyjan)+sum(s.rqtyjan)+sum(s.sqtyjan)+sum(s.pqtyjan)) cqjan,"+
 		 	"sum(qtyfeb)-(sum(s.eqtyfeb)+sum(s.bqtyfeb)+sum(s.rqtyfeb)+sum(s.sqtyfeb)+sum(s.pqtyfeb)) cqfeb,"+
 		 	"sum(qtymar)-(sum(s.eqtymar)+sum(s.bqtymar)+sum(s.rqtymar)+sum(s.sqtymar)+sum(s.pqtymar)) cqmar,"+
 		 	"sum(qtyapr)-(sum(s.eqtyapr)+sum(s.bqtyapr)+sum(s.rqtyapr)+sum(s.sqtyapr)+sum(s.pqtyapr)) cqapr,"+
 		 	"sum(qtymay)-(sum(s.eqtymay)+sum(s.bqtymay)+sum(s.rqtymay)+sum(s.sqtymay)+sum(s.pqtymay)) cqmay,"+
 		 	"sum(qtyjun)-(sum(s.eqtyjun)+sum(s.bqtyjun)+sum(s.rqtyjun)+sum(s.sqtyjun)+sum(s.pqtyjun)) cqjun,"+
 		 	"sum(qtyjul)-(sum(s.eqtyjul)+sum(s.bqtyjul)+sum(s.rqtyjul)+sum(s.sqtyjul)+sum(s.pqtyjul)) cqjul,"+
 		 	"sum(qtyaug)-(sum(s.eqtyaug)+sum(s.bqtyaug)+sum(s.rqtyaug)+sum(s.sqtyaug)+sum(s.pqtyaug)) cqaug,"+
 		 	"sum(qtysep)-(sum(s.eqtysep)+sum(s.bqtysep)+sum(s.rqtysep)+sum(s.sqtysep)+sum(s.pqtysep)) cqsep,"+
 		 	"sum(valoct)-(sum(s.evaloct)+sum(s.bvaloct)+sum(s.rvaloct)+sum(s.svaloct)+sum(s.pvaloct)) cvoct,"+
 		 	"sum(valnov)-(sum(s.evalnov)+sum(s.bvalnov)+sum(s.rvalnov)+sum(s.svalnov)+sum(s.pvalnov)) cvnov,"+
 		 	"sum(valdec)-(sum(s.evaldec)+sum(s.bvaldec)+sum(s.rvaldec)+sum(s.svaldec)+sum(s.pvaldec)) cvdec,"+
 		 	"sum(valjan)-(sum(s.evaljan)+sum(s.bvaljan)+sum(s.rvaljan)+sum(s.svaljan)+sum(s.pvaljan)) cvjan,"+
 		 	"sum(valfeb)-(sum(s.evalfeb)+sum(s.bvalfeb)+sum(s.rvalfeb)+sum(s.svalfeb)+sum(s.pvalfeb)) cvfeb,"+
 		 	"sum(valmar)-(sum(s.evalmar)+sum(s.bvalmar)+sum(s.rvalmar)+sum(s.svalmar)+sum(s.pvalmar)) cvmar,"+
 		 	"sum(valapr)-(sum(s.evalapr)+sum(s.bvalapr)+sum(s.rvalapr)+sum(s.svalapr)+sum(s.pvalapr)) cvapr,"+
 		 	"sum(valmay)-(sum(s.evalmay)+sum(s.bvalmay)+sum(s.rvalmay)+sum(s.svalmay)+sum(s.pvalmay)) cvmay,"+
 		 	"sum(valjun)-(sum(s.evaljun)+sum(s.bvaljun)+sum(s.rvaljun)+sum(s.svaljun)+sum(s.pvaljun)) cvjun,"+
 		 	"sum(valjul)-(sum(s.evaljul)+sum(s.bvaljul)+sum(s.rvaljul)+sum(s.svaljul)+sum(s.pvaljul)) cvjul,"+
 		 	"sum(valaug)-(sum(s.evalaug)+sum(s.bvalaug)+sum(s.rvalaug)+sum(s.svalaug)+sum(s.pvalaug)) cvaug,"+
 		 	"sum(valsep)-(sum(s.evalsep)+sum(s.bvalsep)+sum(s.rvalsep)+sum(s.svalsep)+sum(s.pvalsep)) cvsep "+
 		 	" FROM "+tblnm2+" a, "+tblnm3+" h,"+tblnm+" s WHERE a.MKT_YEAR = ? " +
 		 	" AND a.DEPO_CODE = ? 	and h.mkt_year = ? and h.depo_code = ? and a.depo_code = h.depo_code " +
 		 	" and a.mterr_code = h.ter_code and s.mkt_year = ? and s.depo_code = ? and s.pr_code=? and a.mac_code = s.stk_code " +
 		 	" and a.depo_code = s.depo_code and a.mkt_year = s.mkt_year and h.ter_code = s.tr_cd and h.mkt_year = s.mkt_year " +
 		 	" group by s.stk_code order by a.mterr_code,a.mac_name";
 	      }
 	      
//////////////////////////////Headquater ki Query/////////////////////////////////
//          String query22 = "Select ter_code,ter_name from "+tblnm3+" where depo_code=? and ter_pt=? and mkt_year=?  order by area_code,regn_code,ter_code";
	        ps22 = con.prepareStatement(query22);
	        ps22.setInt(1,eyear);
	        ps22.setInt(2,depo_code);
	        ps22.setInt(3,eyear);
	        ps22.setInt(4,depo_code);
	        ps22.setInt(5,eyear);
	        ps22.setInt(6,depo_code);
	        ps22.setInt(7,code);
	        rst22 = ps22.executeQuery();
  		  
/////////////////////////////////////Account Master Query////////////////////////////////////////
		  
          while (rst22.next()) ///// HQ Loop Start
	        {

		    	if (flag)
		    	{
		    		tercd=rst22.getInt(1);
		    		ternm=rst22.getString(2);
		    		flag=false;
		    	}

		    	if (tercd!=rst22.getInt(1))  // HQ Wise Total /////////////////////////
		    	{
		    		 a=0;
					 thqty=0.00;
					 thval=0.00;
					 rfb = new Repo18FormBean();
					 rfb.setPname(ternm);
		             rfb.setColor(2);
			 			for (a=0; a<index-1;a++)
			 			{
			 			     if ((uv==1) || (uv==3))
	 		 			     {
			   					rfb.setQty1(a, tvqty[a]);
			   					thqty=thqty+tvqty[a];
	 		 			     }
	 		 			     if ((uv==2) || (uv==3))
	 		 			     {
	 		   					rfb.setVal1(a, tvval[a]);
			   					thval=thval+tvval[a];
	 		 			     }

		                    tvqty[a]=0.00;
		                    tvval[a]=0.00;
			 			}
			 			
		 			     if ((uv==1) || (uv==3))
		 			     {	 
				 			 rfb.setUhead(a, "TOTAL UNITS");
		 			    	 rfb.setQty1(a, thqty);
		 			     }	 
		 			     if ((uv==2) || (uv==3))
		 			     {	 
				 			 rfb.setVhead(a, "TOTAL VALUE");
		 			    	 rfb.setVal1(a, thval);
		 			     }	 

		                 data.add(rfb);
				
        	  
		            thqty=0.00;
		            thval=0.00;
		    		tercd=rst22.getInt(1);
		    		ternm=rst22.getString(2);
		    	}
  		    
				k=0;  
                hqty=0.00;
                hval=0.00;
                
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			     if ((uv==1) || (uv==3))
		 			    	 head1[k]=mrec.getString(3)+" UNITS";
		 			     if ((uv==2) || (uv==3))
		 			    	 head2[k]=mrec.getString(3)+" VALUE";

		 			     if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			     	 				 	  
 			             if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
 			                mno=mrec.getInt(5)+4;	 			             
 			                vno=mrec.getInt(5)+16;	 			             

	 
	
		 			     if ((uv==1) || (uv==3))
		 			     {	 
		 			            qty[k]=rst22.getDouble(mno);
		 			    		hqty=hqty+qty[k];
		 			    		vqty[k]=vqty[k]+qty[k];
		 			    		thqty=thqty+qty[k];
		 			    		tvqty[k]=tvqty[k]+qty[k];
		 			     }
                         
		 			     if ((uv==2) || (uv==3))
		 			     {	 
		 			            val[k]=rst22.getDouble(vno);
		 			    		hval=hval+val[k];
		 			    		vval[k]=vval[k]+val[k];
		 			    		thval=thval+val[k];
		 			    		tvval[k]=tvval[k]+val[k];
		 			     }   
			        
				        
                    k++; 
			      }    //////////////End of Month loop////////////////////
		 			
		 			
				      a=0;
				if ((hqty!=0) || (hval!=0))
				{
					rfb = new Repo18FormBean();
		 			for (a=0; a<k;a++)
		 			{
						rfb.setPname(rst22.getString(3)+", "+rst22.getString(4));
		                rfb.setMon(index);
		                rfb.setUv(uv);
			 			rfb.setQty1(a, qty[a]);
	                    rfb.setVal1(a, val[a]);
	 			    	rfb.setUhead(a,head1[a]);
	 			    	rfb.setVhead(a,head2[a]);
		 			}
  		 			
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
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb);
				}
				
			
	     } ///////////////////HQ Loop End/////////////////////////
          
/////////////////////// HQ Total Printing /////////////////////////////////          
	         a=0;
	 		 thqty=0.00;
			 thval=0.00;
			 rfb = new Repo18FormBean();
			 rfb.setPname(ternm);
			 rfb.setColor(2);
	 			for (a=0; a<index-1;a++)
	 			{
	 			     if ((uv==1) || (uv==3))
	 			     {
	   					rfb.setQty1(a, tvqty[a]);
	   					thqty=thqty+tvqty[a];
	 			     }
	 			     if ((uv==2) || (uv==3))
	 			     {
	   					rfb.setVal1(a, tvval[a]);
	   					thval=thval+tvval[a];
	 			     }

                 tvqty[a]=0.00;
                 tvval[a]=0.00;
	 			}
	 			
			     if ((uv==1) || (uv==3))
			     {	 
		 			 rfb.setUhead(a, "TOTAL UNITS");
			    	 rfb.setQty1(a, thqty);
			     }	 
			     if ((uv==2) || (uv==3))
			     {	 
		 			 rfb.setVhead(a, "TOTAL VALUE");
			    	 rfb.setVal1(a, thval);
			     }	 

                 data.add(rfb);
			
/////////////////////// Grand Total Printing /////////////////////////////////          
          
			 		 rfb = new Repo18FormBean();
 					 rfb.setPname("TOTAL :");
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
			
			System.out.println("========Exception in SQLRepo18DAO.getAllHQrepo18 " + e); 
		}
		
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(rst22 != null){ rst22.close();}
	           if(ps22 != null){ps22.close();} 
	           if(con != null){con.close();}

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo18DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
} 
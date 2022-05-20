package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo11FormBean;

public class SQLRepo11DAO {

	
	public List getAllStk(Connection con,int smon,int emon,int st,int eyear,int depo_code,String tp,int uid) { 
		 
		Repo11FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
 
		List<Repo11FormBean> data = new ArrayList<Repo11FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt2=null;
            String txt3=null;
            String txt5 =null;
            String query2=null;
            int mno=0;
            int tercd=0;
            int z=0;
            int k=0;
            String ternm=null;
            boolean flag=true;
            double hval=0.0f;
            double[] vval;
            double[] tval;
            
            if (smon>emon)
            	emon=smon;
            int index =(emon-smon)+2;
            
            if (emon==12)
            	index=(emon-smon)+2;
            
            if (smon==12)
            	index=2;
            
        	tblnm=tp+"_stockiest08";
        	tblnm1=tp+"_hq_master08";
   	        tblnm2=tp+"_account08";
            
            if (st==1)
                txt2="     STOCKIEST WISE RUPEE WISE SALES WISE GROSS SALES TREND FROM "; 
            if (st==2)
                txt2="     STOCKIEST WISE RUPEE WISE CREDIT SALES TREND FROM "; 
            if (st==3)
                txt2="     STOCKIEST WISE RUPEE WISE NET SALES TREND FROM ";

        vval = new double[index];
        tval = new double[index];
   
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
            
 ///////////////////////////////////////Month File Query//////////////////////////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
        ms1 = con.prepareStatement(month);
        ms1.setInt(1,smon);
        ms1.setInt(2,emon);
        ms1.setInt(3,eyear);
	 	mrec = ms1.executeQuery();
 		 	
	 	
//////////////////////////////Main query for Headquater/Account/Stockiest /////////////////////////////////
      if (st==1)
      {
	 	query2="SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
	 	" sum(s.valoct),sum(s.valnov),sum(s.valdec),sum(s.valjan),sum(s.valfeb),sum(s.valmar), "+
	 	" sum(s.valapr),sum(s.valmay),sum(s.valjun),sum(s.valjul),sum(s.valaug),sum(s.valsep) "+
	 	" FROM "+tblnm2+" a, "+tblnm1+" h,"+tblnm+" s,user_ter as u WHERE a.MKT_YEAR="+eyear+" "+ 
	 	" AND a.depo_code=u.depo_code and h.mkt_year="+eyear+" and h.depo_code =u.depo_code and a.depo_code=h.depo_code "+
	 	" and s.mkt_year="+eyear+" and s.depo_code=u.depo_code and a.mac_code=s.stk_code and u.ter_code=h.ter_code "+
	 	" and u.ter_code=s.tr_cd and u.ter_code=a.mterr_code "+
	 	" and a.depo_code=s.depo_code and a.mkt_year=s.mkt_year and h.ter_code=s.tr_cd and h.mkt_year=s.mkt_year "+ 
	 	" and a.marea_code<>0 and a.mregion_Cd<>0 and a.mterr_code<>0 and u.user_id="+uid+" "+
	 	" group by s.stk_code order by a.mterr_code,a.mac_name";
      }
      
      if (st==2)
      {
	 	query2="select a.mterr_code,h.ter_name,a.mac_name,a.mcity,"+
	 	" sum(s.evaloct)+sum(s.bvaloct)+sum(s.rvaloct)+sum(s.svaloct)+sum(s.pvaloct) cvoct,"+
	 	" sum(s.evalnov)+sum(s.bvalnov)+sum(s.rvalnov)+sum(s.svalnov)+sum(s.pvalnov) cvnov,"+
	 	" sum(s.evaldec)+sum(s.bvaldec)+sum(s.rvaldec)+sum(s.svaldec)+sum(s.pvaldec) cvdec,"+
	 	" sum(s.evaljan)+sum(s.bvaljan)+sum(s.rvaljan)+sum(s.svaljan)+sum(s.pvaljan) cvjan,"+
	 	" sum(s.evalfeb)+sum(s.bvalfeb)+sum(s.rvalfeb)+sum(s.svalfeb)+sum(s.pvalfeb) cvfeb,"+
	 	" sum(s.evalmar)+sum(s.bvalmar)+sum(s.rvalmar)+sum(s.svalmar)+sum(s.pvalmar) cvmar,"+
	 	" sum(s.evalapr)+sum(s.bvalapr)+sum(s.rvalapr)+sum(s.svalapr)+sum(s.pvalapr) cvapr,"+
	 	" sum(s.evalmay)+sum(s.bvalmay)+sum(s.rvalmay)+sum(s.svalmay)+sum(s.pvalmay) cvmay,"+
	 	" sum(s.evaljun)+sum(s.bvaljun)+sum(s.rvaljun)+sum(s.svaljun)+sum(s.pvaljun) cvjun,"+
	 	" sum(s.evaljul)+sum(s.bvaljul)+sum(s.rvaljul)+sum(s.svaljul)+sum(s.pvaljul) cvjul,"+
	 	" sum(s.evalaug)+sum(s.bvalaug)+sum(s.rvalaug)+sum(s.svalaug)+sum(s.pvalaug) cvaug,"+
	 	" sum(s.evalsep)+sum(s.bvalsep)+sum(s.rvalsep)+sum(s.svalsep)+sum(s.pvalsep) cvsep "+
	 	" from "+tblnm2+" a, "+tblnm1+" h,"+tblnm+" s,user_ter u "+
	 	" where a.mkt_year="+eyear+" and s.mkt_year="+eyear+" and h.mkt_year="+eyear+" "+
	 	" and a.depo_code=u.depo_code and h.depo_code=u.depo_code and s.depo_code=u.depo_code"+ 
	 	" and a.depo_code=h.depo_code and a.depo_code=s.depo_code and a.mac_code=s.stk_code"+
	 	" and u.ter_code=a.mterr_code and u.ter_code=s.tr_cd and u.ter_code=h.ter_code "+
	 	" and h.ter_code=s.tr_cd and a.mterr_code=h.ter_code and u.user_id="+uid+" "+ 
	 	" group by s.stk_code order by a.mterr_code,a.mac_name ";
      }

      if (st==3)
      {
	 	query2= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
	 	" sum(valoct)-(sum(s.evaloct)+sum(s.bvaloct)+sum(s.rvaloct)+sum(s.svaloct)+sum(s.pvaloct)) cvoct,"+
	 	" sum(valnov)-(sum(s.evalnov)+sum(s.bvalnov)+sum(s.rvalnov)+sum(s.svalnov)+sum(s.pvalnov)) cvnov,"+
	 	" sum(valdec)-(sum(s.evaldec)+sum(s.bvaldec)+sum(s.rvaldec)+sum(s.svaldec)+sum(s.pvaldec)) cvdec,"+
	 	" sum(valjan)-(sum(s.evaljan)+sum(s.bvaljan)+sum(s.rvaljan)+sum(s.svaljan)+sum(s.pvaljan)) cvjan,"+
	 	" sum(valfeb)-(sum(s.evalfeb)+sum(s.bvalfeb)+sum(s.rvalfeb)+sum(s.svalfeb)+sum(s.pvalfeb)) cvfeb,"+
	 	" sum(valmar)-(sum(s.evalmar)+sum(s.bvalmar)+sum(s.rvalmar)+sum(s.svalmar)+sum(s.pvalmar)) cvmar,"+
	 	" sum(valapr)-(sum(s.evalapr)+sum(s.bvalapr)+sum(s.rvalapr)+sum(s.svalapr)+sum(s.pvalapr)) cvapr,"+
	 	" sum(valmay)-(sum(s.evalmay)+sum(s.bvalmay)+sum(s.rvalmay)+sum(s.svalmay)+sum(s.pvalmay)) cvmay,"+
	 	" sum(valjun)-(sum(s.evaljun)+sum(s.bvaljun)+sum(s.rvaljun)+sum(s.svaljun)+sum(s.pvaljun)) cvjun,"+
	 	" sum(valjul)-(sum(s.evaljul)+sum(s.bvaljul)+sum(s.rvaljul)+sum(s.svaljul)+sum(s.pvaljul)) cvjul,"+
	 	" sum(valaug)-(sum(s.evalaug)+sum(s.bvalaug)+sum(s.rvalaug)+sum(s.svalaug)+sum(s.pvalaug)) cvaug,"+
	 	" sum(valsep)-(sum(s.evalsep)+sum(s.bvalsep)+sum(s.rvalsep)+sum(s.svalsep)+sum(s.pvalsep)) cvsep "+
	 	" from "+tblnm2+" a, "+tblnm1+" h,"+tblnm+" s,user_ter u "+
	 	" where a.mkt_year="+eyear+" and s.mkt_year="+eyear+" and h.mkt_year="+eyear+" "+
	 	" and a.depo_code=u.depo_code and h.depo_code=u.depo_code and s.depo_code=u.depo_code"+ 
	 	" and a.depo_code=h.depo_code and a.depo_code=s.depo_code and a.mac_code=s.stk_code"+
	 	" and u.ter_code=a.mterr_code and u.ter_code=s.tr_cd and u.ter_code=h.ter_code "+
	 	" and h.ter_code=s.tr_cd and a.mterr_code=h.ter_code and u.user_id="+uid+" "+ 
	 	" group by s.stk_code order by a.mterr_code,a.mac_name ";
      }
        ps2 = con.prepareStatement(query2);
        rst2 = ps2.executeQuery();
          		 	
		    while (rst2.next()) ///// HQ Loop Start
	        {
			  
		    	if (flag)
		    	{
		    		tercd=rst2.getInt(1);
		    		ternm=rst2.getString(2);
		    		flag=false;
		    	}

		    	if (tercd!=rst2.getInt(1))
		    	{
		    		rfb = new Repo11FormBean();
					rfb.setName(ternm);
					rfb.setColor(2);
                    hval=0.00;
                    z=0;
					 for (z=0; z<index-1;z++)
	   				 {
		 					rfb.setVal1(z, tval[z]);
	                        hval=hval+tval[z];
	                        tval[z]=0.00;
	   				 }
					 		rfb.setVal1(z, hval);
	                        data.add(rfb); 				

		    		tercd=rst2.getInt(1);
		    		ternm=rst2.getString(2);
		    	}
				
                hval=0.00;
                k=0;  
				rfb = new Repo11FormBean();
				rfb.setName(rst2.getString(3)+", " + rst2.getString(4));
                rfb.setMon(index);
                rfb.setColor(1);
                
                     mrec.beforeFirst();
		 			while (mrec.next())  //// Month Loop Start////////////
		 			{	
		 			     rfb.setVhead(k, mrec.getString(3));
		 			 
		 			     if (mrec.isFirst())	
		 				     txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			         if (mrec.isLast())
		 			        txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
			 				
		 			     mno=mrec.getInt(5)+4;
			        	 rfb.setVal1(k, rst2.getDouble(mno));
                         hval=hval+rst2.getDouble(mno);
                         vval[k]=vval[k]+rst2.getDouble(mno);
                         tval[k]=tval[k]+rst2.getDouble(mno);
			        	 
                          k++; 
                    
                    
			        }////////////////End of Month loop///////////////////////////      

		 				 rfb.setVhead(k, "TOTAL");
	 			    	 rfb.setVal1(k, hval);
	 			    	 rfb.setHead1(txt2+txt5);
	 			    	 rfb.setLupdate(txt3);
	                     data.add(rfb); 	
	                     
			} /////////////////////////HQ Master Loop End here///////////////// 
			 
					mrec.close();
					ms1.close();

					rfb = new Repo11FormBean();
					rfb.setName(ternm);
					rfb.setColor(2);
                    hval=0.00;
                    z=0;
					 for (z=0; z<index-1;z++)
	   				 {
		 					rfb.setVal1(z, tval[z]);
	                        hval=hval+tval[z];
	                        tval[z]=0.00;
	   				 }
					 		rfb.setVal1(z, hval);
	                        data.add(rfb); 				
					
					 rfb = new Repo11FormBean();
 					 rfb.setName("GRAND TOTAL:");
 				   	 rfb.setColor(3);
                     hval=0.00;
                     z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 					rfb.setVal1(z, vval[z]);
 	                        hval=hval+vval[z];
	   				 }
 					 		rfb.setVal1(z, hval);
	                        data.add(rfb); 				
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLRepo11DAO.getAllSktRepo11 " + e); 
		}
		
		finally {
			try {
		           if(mrec != null){mrec.close();}
		           if(ms1 != null){ ms1.close();}
		           if(rst12 != null){ rst12.close();}
		           if(ps12 != null){ps12.close();}
		           if(rst2 != null){ rst2.close();}
		           if(ps2 != null){ ps2.close();}
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in HQ-SQLRepo11DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	
	
}
 
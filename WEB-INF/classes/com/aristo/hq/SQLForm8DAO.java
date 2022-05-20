package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm8DAO {

///////////////////////////////Headquater Coding Start Here//////////////////////////////////
	
	public List getAllHQ(Connection con, int code,int uv,int emon,int st,int eyear,int depo_code,String tp,int uid) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;

       	int mon=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String txt6=null;
            int trep=0;
            int grep=0;
            double pmr= 0.00;
            double montar =0.00;
            String query3=null;
            
            int index=emon+1;
            int hqty =0;
            int gqty=0;
            float hval=0.0f;
            float gval=0.0f;
            
            float[] vval;
            int[] vqty;

            
        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
            
			 if (uv==1)
                txt2="     H.Q. WISE UNIT SALES TREND FROM "; 
			 if (uv==2)
	            txt2="     H.Q. WISE VALUE SALES TREND FROM "; 
			 if (uv==3)
	            txt2="     H.Q. WISE UNIT/VALUE SALES TREND FROM "; 
                
            vval = new float[index];
            vqty = new int[index];
 
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
    		            
//////////////////////// Product Master Query/////////////////////////////////                
            String terrec = "Select mname,pack,mcode from "+tblnm1+" where pcode=? and mkt_year=? ";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		if (trec.next())
    		{
                txt1="PRODUCT -> "+trec.getString(1)+","+trec.getString(2);
                code=trec.getInt(3);
    		}
    		
    		trec.close();
    		ts1.close();
////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,emon);
	 		ms1.setInt(2,eyear);
	 		mrec = ms1.executeQuery();
    		
////////////////////////HQ  Master Query/////////////////////////////////            
            String query1="Select h.ter_code,h.ter_name,h.no_of_rep,u.depo_code from "+tblnm2+" as h, " +
            " user_ter as u where h.depo_code=u.depo_code and h.ter_pt=u.access_t and h.ter_code=u.ter_code " +
            " and h.mkt_year="+eyear+" and u.user_id="+uid+" order by h.ter_code ";            
			ps1 = con.prepareStatement(query1); 
			rst1 = ps1.executeQuery();
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
                rfb.setQty2(uv);
                rfb.setQty4(st);
                int k=0;  
                hqty=0;
                hval=0.0f;
                //////////// Month File Loop Starts to accumulate data

                	mrec.beforeFirst();
		 			while (mrec.next())///////////// Month Loop Start
		 			{	
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 				         if (mrec.isLast())
	 			            txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			     mon=mrec.getInt(4);
		 			        wname=mrec.getString(3);
		 			        if (wname.equals("DEC"))
		 			        	wname="DECM";

////////////////////////////////////HQ Master Query/////////////////////////////		    
	          String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? and ter_code=? and mkt_year=? order by ter_code";
	          psq = con.prepareStatement(queryq);
		      psq.setInt(1,rst1.getInt(4));
		      psq.setString(2,tp);
		 	  psq.setInt(3,rst1.getInt(1));
		 	  psq.setInt(4,eyear);
		 	  rstq = psq.executeQuery();
		 			 			        
		 			 	    if (rstq.next())
		 			 	    {
		 			 	    	trep=trep+rstq.getInt(1);		
		 			 	    	grep=grep+rstq.getInt(1);
		 			 	    }
		 			 		
		 		  rstq.close();
		 		  psq.close();		 			        

//////////////////////////////////// Target Master Ki Query/////////////////

		 	if (st==1) 	  
		 	{
		 	 query3 = "Select sum(ta"+mon+"),sum(ra"+mon+") from "+tblnm+" where tr_cd=? "+
             "  and pr_code=? and depo_code=? and mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd"; 
		 	}
		 	  
		 	if (st==2) 	  
		 	{		        
        	query3 = "Select sum(((tt"+mon+"*ttarget)/100)),sum(((tt"+mon+"*ttarget)/100)*tmrp),sum((budtar*budper)/100),sum((budval*budper)/100),max(budmnth) from "+tblnm+" where tr_cd=? "+
            "  and pr_code=? and depo_code=? and mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd"; 
		 	}
		 	
		    ps3 = con.prepareStatement(query3); 
			ps3.setInt(1,rst1.getInt(1)); 
			ps3.setInt(2,code);
			ps3.setInt(3,rst1.getInt(4));
			ps3.setInt(4,mrec.getInt(5));
			rst3 = ps3.executeQuery(); 
			
			   if(rst3.next())/////////////// Target Master Start/////////////
			        {
		 			     if ((uv==1) || (uv==3))
		 			     {	 
                           if (st==1)
                           {
		 			    	rfb.setQty1(k, (int) rst3.getDouble(1));
                            hqty=hqty+(int) rst3.getDouble(1);
                            gqty=gqty+(int) rst3.getDouble(1);
                           }
                           if (st==2)
                           {
                        	   
      				    	 if (mrec.getInt(6)>=rst3.getInt(5))
    				    	 {
    				    		 montar = rst3.getDouble(1)+rst3.getDouble(3);
    				    	 }			            
      				    	 else
      				    	 {
    				    		 montar = rst3.getDouble(1);
      				    	 }
      				    	 
    		 			    	rfb.setQty1(k, (int) (montar+.50));
                                hqty=hqty+(int) (montar+.50);
                                gqty=gqty+(int) (montar+.50);
                                
//    	 			    	  rfb.setQty1(k, (int) (rst3.getDouble(1)+.50));
//                            hqty=hqty+(int) (rst3.getDouble(1)+.50);
//                            gqty=gqty+(int) (rst3.getDouble(1)+.50);
                           }
                            vqty[k]=vqty[k]+(int) (montar+.50);
                         }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
                           if (st==2)
                           {
		 			    	 if (mrec.getInt(6)>=rst3.getInt(5))
    				    	 {
    				    		 montar = rst3.getDouble(2)+rst3.getDouble(4);
    				    	 }			            
      				    	 else
      				    	 {
    				    		 montar = rst3.getDouble(2);
      				    	 }
		 			    	 
                           if (montar<0)
                           {
		 			    	rfb.setVal1(k, (int)(((montar*-1)+.50)*-1));
                            hval=hval+(int)(((montar*-1)+.50)*-1);
                            gval=gval+(int)(((montar*-1)+.50)*-1);
                           }
                           else
                           {
   		 			    	rfb.setVal1(k, (int)(montar+.50));
                            hval=hval+(int)(montar+.50);
                            gval=gval+(int)(montar+.50);
                           }
		 			    	 
                           }
                           

                       if (st==1)
                       {
	 			    	rfb.setVal1(k, (int) (rst3.getDouble(2)+.50));
                       // hval=hval+(int) rst3.getDouble(2);
                        gval=gval+(int) (rst3.getDouble(2)+.50);
			    		 montar = rst3.getDouble(2);
                         hval=hval+(int)(montar+.50);
			    		 
                       }
                           
                           vval[k]=vval[k]+(int)(montar+.50);
                         }   
			        }/////////////Target Master End/////////////////	 
			        	 
			   		rst3.close();
			   		ps3.close();
			   
                    k++; 
			        }//End of Month loop///////////////////////     
		 			
		 			   vqty[k]= (int) gqty;
                       vval[k]= (int)gval;
		 			
		 			rfb.setNo_of_mr(trep);
	 			    	 			    
		 			if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "Total Units");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "Total Value");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 
                     k++;
                     
                   if (st==1)
                   {	   
                     if (trep!=0)
		 		     {	 
     		   		  if ((uv==1) || (uv==3))
     		   		  {
	                       if (hqty<0)
	   		 		    	pmr = ((((hqty/trep)*-1)+.50)*-1);
	                       else
			 		    	pmr = ((hqty/trep)+.50);
			            	rfb.setQty5((int)pmr);
     		   		  }
     		   		  if ((uv==2) || (uv==3))
     		   		  {
	                       if (hval<0)
	   		 		    	pmr = ((((hval/trep)*-1)+.50)*-1);
	                       else
			 		    	pmr = ((hval/trep)+.50);
			            	rfb.setQty6((int)pmr);
     		   		  }
		 		     }
                   }
	 			     trep=0;
	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				 data.add(rfb); 
	 				 hqty=0;
	 				 hval=0.00f;
				} 
			 	mrec.close();
			 	ms1.close();
			 	rst1.close();
			 	ps1.close();
			 	
			         rfb = new MktFormBean();
 					 rfb.setName("TOTAL :");
 		 			 rfb.setNo_of_mr(grep);

 					 for (int z=0; z<index;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {	 
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		 			    	rfb.setVal1(z, vval[z]);
 		 			        hval=vval[z];
 		 			     }
 		 			     
	   				 }
 					  if (st==1)
 	                   {	   
 	                     if (grep!=0)
 			 		     {	 
 	     		   		  if ((uv==1) || (uv==3))
 	     		   		  {
 		                       if (hqty<0)
 		   		 		    	pmr = ((((hqty/grep)*-1)+.50)*-1);
 		                       else
 				 		    	pmr = ((hqty/grep)+.50);
 				            	rfb.setQty5((int)pmr);
 	     		   		  }
 	     		   		  if ((uv==2) || (uv==3))
 	     		   		  {
 		                       if (hval<0)
 		   		 		    	pmr = ((((hval/grep)*-1)+.50)*-1);
 		                       else
 				 		    	pmr = ((hval/grep)+.50);
 				            	rfb.setQty6((int)pmr);
 	     		   		  }
 			 		     }
 	                   }

	                 data.add(rfb); 				
		} catch (Exception e) 
		      {
			  System.out.println("===Exception in HQ-SQLForm8DAO.getAllHQ " + e); 
		      }
		  finally 
			{
				try
				{
	            if(rst12 != null){ rst12.close();}
	            if(ps12 != null){ ps12.close();}
	            if(trec != null){ trec.close();}
	            if(ts1 != null){ ts1.close();}
	            if(mrec != null){mrec.close();}
	            if(ms1 != null){ ms1.close();}
	            if(rst1 != null){ rst1.close();}
	            if(ps1 != null){ ps1.close();}
	            if(rstq != null){ rstq.close();}
	            if(psq != null){ psq.close();}	            
	            if(rst3 != null){ rst3.close();}
	            if(ps3 != null){ps3.close();}
	            if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in HQ-SQLForm8DAO.Connection.close "+e);
				}
			}
		return data;
	}
	

}
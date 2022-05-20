package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HOForm4FormBean;

 
public class SQLHOForm4DAO {

/////////////////////////////// Branch Coding Start Here//////////////////////////////////
	
	public List getAllBranch(Connection con, int code,int uv,int emon,int st,String tp,String typ,int code1,int loginid, int eyear ) { 
		 
		HOForm4FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps33=null; 
        ResultSet rst33=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;
        
       	int mon=0;
      	String wname=null;
      	String qname="";
      	String qname1="";
		List<HOForm4FormBean> data = new ArrayList<HOForm4FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            int trep=0;
            int grep=0;
            double pmr= 0.00;
            double montar=0.00;
            String query3=null;
            
            int index = emon+1;
            int hqty =0;
            int gqty=0;
            int[] vqty = new int[index];
            
            double hval=0.00;
            float gval=0.0f;
            float[] vval = new float[index];
            boolean chk1=false;
            boolean chk2=false;
            
            tblnm4="user_branch08";
            
        	tblnm=(tp+"_target08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_report").toLowerCase();
//   	        tblnm3=tp+"_hq_master08";
   	        tblnm3=(tp+"_BR_PMR").toLowerCase();
            
 			 if (uv==1)
                txt2="     Branch Wise Unit Sales Trend for the Month of "; 
			 if (uv==2)
	                txt2="     Branch Wise Value Sales Trend for the Month of "; 
			 if (uv==3)
	                txt2="     Branch Wise Unit/Value Sales Trend for the Month of "; 
                
//////////////////////// Product Master Query/////////////////////////////////                
            String terrec = "Select pname,pack,mcode from "+tblnm1+" where pcode=? and mkt_year=?";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		if (trec.next())
    		{
                txt1="Product-> "+trec.getString(1)+","+trec.getString(2);
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
            int k=0;  
			rfb = new HOForm4FormBean();

        	mrec.beforeFirst();
 			while (mrec.next())///////////// Month Loop Start
 			{	
 			     if ((uv==1) || (uv==3))
 			    	 rfb.setUhead(k, mrec.getString(3)+" Units");
 			     if ((uv==2) || (uv==3))
 			    	 rfb.setVhead(k, mrec.getString(3)+" Value");

 			     if (mrec.isFirst())	
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			    
			         if (mrec.isLast())
			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
 			    
			        mon=mrec.getInt(4);
 			        wname=mrec.getString(3);
 			       if (wname.equals("DEC"))
			        	wname="DECM";

 			       if ((uv==1) || (uv==3))
 			       {
	 			       if (st==1)
	                   {
	   			        qname = qname+",sum(ta"+mon+") "+wname; 
	  			        qname1 = qname1+",c."+wname;
	  			        chk1=true;
	                   }
	                   if (st==2)
	                   {
	   			        qname = qname+",sum(tq"+mon+") "+wname; 
	  			        qname1 = qname1+",c."+wname; 
	  			        chk1=true;
	                   }
 			       }   
 			       if ((uv==2) || (uv==3))
 			       {
	 			       if (st==1)
	                   {
	   			        qname = qname+",sum(ra"+mon+") "+wname+"1"; 
	  			        qname1 = qname1+",c."+wname+"1"; 
	  			        chk2=true;
	                   }
	                   if (st==2)
	                   {
	   			        qname = qname+",sum(tt"+mon+") "+wname+"1"; 
	   			        qname1 = qname1+",c."+wname+"1"; 
	  			        chk2=true;
	                   }
 			       }   

// 			       qname1 = qname1+",c."+wname; 
			      k++;    
	        }//End of Month loop///////////////////////     
 					qname1 = qname1+",d.rep"; 
		 			if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setUhead(k, "Total Units");
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setVhead(k, "Total Value");
	 			     }	 
                     k++;
 			       
//////////////////////////////User Branch Master ki Query/////////////////////////////////
//            String query22 = "Select d.depo_code,t.ter_name from "+tblnm4+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
            String query22 = "select c.depo_code,c.br_name"+qname1+" from "+ 
            "(select depo_code,br_name" +qname+" from "+tblnm2+" where mkt_year = ? and mcode=? "+
            "and depo_CODE IN (SELECT DEPO_CODE FROM USER_BRANCH08 WHERE USER_ID = ? AND STATUS = 'Y')  "+
            "group by depo_code,br_name) c, "+
            "(SELECT MKT_YEAR,DEPO_CODE,SUM(REP) REP FROM "+tblnm3+" WHERE MKT_YEAR = ?  "+
            "AND MNTH_CODE <= ? GROUP BY MKT_YEAR,DEPO_CODE) D "+
            "WHERE  C.DEPO_CODE = D.DEPO_CODE "; 
            ps1 = con.prepareStatement(query22);
	        ps1.setInt(1,eyear); 
	        ps1.setInt(2,code); 
	        ps1.setInt(3,loginid); 
	        ps1.setInt(4,eyear); 
	        ps1.setInt(5,emon); 
	        rst1 = ps1.executeQuery();
			   
			while (rst1.next())   ////////////////Branch Loop Start/////////////////
			{
				rfb.setName(rst1.getString(2));
                rfb.setBr(index);
                rfb.setUv(uv);
                rfb.setSt(st);
                int i=k-1;
                hqty=0;
                hval=0.00;
                    ///// Month File Loop Starts to accumulate data

	              for(k=0;k<i;k++)
	              {
			 			     if ((uv==1) || (uv==3))
			 			     {	 
	                           if (st==1)
	                           {
	                        	   if((chk1) && (chk2))
	                        		   montar = rst1.getDouble(k+k+3);
	                        	   else
	                        		   montar = rst1.getDouble(k+3);
	                        		   
		   		 			    	rfb.setQty1(k, (int) (montar));
		                            hqty=hqty+(int) (montar);
		                            gqty=gqty+(int) (montar);
	                           }
	                           if (st==2)
	                           {
	                        	   if((chk1) && (chk2))
	                        		   montar = rst1.getDouble(k+k+3);
	                        	   else
	                        		   montar = rst1.getDouble(k+3);
		   		 			    	rfb.setQty1(k, (int) (montar+.50));
		                            hqty=hqty+(int) (montar+.50);
		                            gqty=gqty+(int) (montar+.50);
	                           }
	                            vqty[k]=vqty[k]+(int) (montar+.50);
	                         }   

			 			     if ((uv==2) || (uv==3))
			 			     {	 
		                           if (st==2)
		                           {
		                        	   if((chk1) && (chk2))
		                        		   montar = rst1.getDouble(k+k+4);
		                        	   else
		                        		   montar = rst1.getDouble(k+3);

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
	                        	   if((chk1) && (chk2))
	                        		   montar = rst1.getDouble(k+k+4);
	                        	   else
	                        		   montar = rst1.getDouble(k+3);

		                        	   rfb.setVal1(k, (int) montar);
		                           // hval=hval+(int) rst3.getDouble(2);
		                        	    gval=gval+(int) montar;
			                            hval=hval+(int)(montar+.50);
					    		 
	                           }
	                           
	                          
	                           vval[k]=vval[k]+(int)(montar+.50);
	                         }   
		 			        
                    //k++; 
	             }  // end of for loop
	              
		 			   vqty[k]= (int) gqty;
                       vval[k]= gval;
                	   if((chk1) && (chk2))
                		   trep=rst1.getInt(k+k-1+4);
                	   else
                		   trep=rst1.getInt(k+3);
                		   
		 			rfb.setNo_of_mr(trep);
		 						    
		 			if ((uv==1) || (uv==3))
	 			     {	 
//			 			 rfb.setUhead(k, "Total Units");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
	//		 			 rfb.setVhead(k, "Total Value");
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
			            	rfb.setPmr_u((int)pmr);
     		   		  }
     		   		  if ((uv==2) || (uv==3))
     		   		  {
	                       if (hval<0)
	   		 		    	pmr = ((((hval/trep)*-1)+.50)*-1);
	                       else
			 		    	pmr = ((hval/trep)+.50);
			            	rfb.setPmr_v((int)pmr);
     		   		  }
		 		     }
                   }
	 			     trep=0;
	 			     rfb.setHead1(txt1+txt2+txt5);
	 				 data.add(rfb); 
	 				 hqty=0;
	 				 hval=0.00;
	 				rfb = new HOForm4FormBean();

			} 
			
			 	mrec.close();
			 	ms1.close();
			 	rst1.close();
			 	ps1.close();
			 	
			         rfb = new HOForm4FormBean();
 					 rfb.setName("TOTAL:");
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
 				            	rfb.setPmr_u((int)pmr);
 	     		   		  }
 	     		   		  if ((uv==2) || (uv==3))
 	     		   		  {
 		                       if (hval<0)
 		   		 		    	pmr = ((((hval/grep)*-1)+.50)*-1);
 		                       else
 				 		    	pmr = ((hval/grep)+.50);
 				            	rfb.setPmr_v((int)pmr);
 	     		   		  }
 			 		     }
 	                   }

	                 data.add(rfb); 				
			
		} catch (Exception e) 
		      {
			    e.printStackTrace();
			  System.out.println("===Exception in SQLHOForm4DAO.getAllBranch " + e); 
		      }
		  finally 
			{
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
		           if(rst33 != null){ rst33.close();}
		           if(ps33 != null){ps33.close();}
		           if(rstq != null){ rstq.close();}
		           if(psq != null){psq.close();}
		           if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////// Branch Coding End Here//////////////////////////////////	
} 
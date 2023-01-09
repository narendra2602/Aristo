package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo1FormBean;

public class SQLHORepo1DAO {

////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getAllBranch(Connection con, int uv, int smon, int emon, int saletp,int div_code,int utype,int depo_code,int loginid,int eyear, String sr,int rep_type) { 
		 
		HORepo1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        PreparedStatement ps1=null;
        ResultSet rs1=null;

        
        CallableStatement cs=null;
        ResultSet rst1=null;
        
		List<HORepo1FormBean> data = new ArrayList<HORepo1FormBean>();
		try {     
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null; 
            String txt4=null;
            String txt5 =null;
            double salqty=0.00;
            double salval=0.00;
            double gval[];
            double grval[];
            double gsalqty=0.00;
            double gsalval=0.00;
            int nrep[];
            int depo[];
            String depo_name[];
            int tuv=uv;
            String procedureWithParameters="{call web_report_gross(?,?,?,?,?,?,?,?,?)}";
            
            //utype=4;
            //loginid=360;
            
            tblnm3="user_branch08";
            int doctp=saletp;
            if(utype==4)
            	tblnm3="user_ter";

            if(depo_code>0 &&  utype==3 )
            	utype=31;
            else if(depo_code>0 && (utype==2 || utype==3 || utype==5))
            	utype=1;


            
            if (smon>emon)
            	emon=smon;

       	    
           if (uv==1)
            {
            	txt3="Product/UnitWise ";
            }
            
            if (uv==2)
            {
            	txt3=rep_type==1?" Product/ValueWise ":" Group/ValueWise ";
            }
            
            if(saletp==1)
            	txt4="Gross Sale For the Month of ";
            if(saletp==2)
            	txt4="PI Sale For the Month of ";
            if(saletp==3)
            {
            	txt4="Net Sale For the Month of ";
            	procedureWithParameters="{call web_report_net(?,?,?,?,?,?,?,?)}";
            }

            if(saletp==4)
            {
            	txt4="Target For the Month of ";
            	procedureWithParameters="{call web_report_target(?,?,?,?,?,?,?,?,?)}";
            	doctp=10;
            }

            if(saletp==5)
            {
            	txt4="Last Year Sale For the Month of ";
            	procedureWithParameters="{call web_report_target(?,?,?,?,?,?,?,?,?)}";
            	doctp=20;
            }

            if(saletp==6)
            {
            	txt4="Achievment For the Month of ";
            	procedureWithParameters="{call web_report_ach(?,?,?,?,?,?,?,?)}";
            	tuv=saletp+uv;
            }

            if(saletp==7)
            {
            	txt4="Growth For the Month of ";
            	procedureWithParameters="{call web_report_gth(?,?,?,?,?,?,?,?,?)}";
            	doctp=20;
            }

            if(saletp==8)
            {
            	txt4="PMR For the Month of ";
            	procedureWithParameters="{call web_report_net(?,?,?,?,?,?,?,?)}";
            	tuv=saletp+uv;
            }

            if(saletp==9)
            {
            	txt4="SUR/DEF For the Month of ";
            	procedureWithParameters="{call web_report_gth(?,?,?,?,?,?,?,?,?)}";
            	doctp=10;
            }
            
            if(saletp==10)
            {
            	txt4="Saleable Return For the Month of ";
            	procedureWithParameters="{call web_report_credit(?,?,?,?,?,?,?,?,?)}";
            }
            if(saletp==11)
            {
            	txt4="Expiry Return For the Month of ";
            	procedureWithParameters="{call web_report_credit(?,?,?,?,?,?,?,?,?)}";
            }
            if(saletp==12)
            {
            	txt4="Breakage Return For the Month of ";
            	procedureWithParameters="{call web_report_credit(?,?,?,?,?,?,?,?,?)}";
            }

//                txt2="Product Wise/"; 
//                txt1="Branch Wise/";
                txt2=""; 
                txt1="";
			
            int t=0;
            int w=0;

            System.out.println("valu eof tuv "+tuv);
            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            ps1=con.prepareStatement(branchname);
            ps1.setInt(1, depo_code);
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
            	txt1=rs1.getString(1)+" Branch";
            }
            else
            	txt1="All India";
           
            rs1.close();
            ps1.close();
             
            
//////////////////////////////////////// Branch Master Count/////////////////////////////////// 
            String terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=? ";
            if(div_code>=12 && div_code<=16)
                 terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=? and depo_code=32";
            else if(div_code!=3)
            	terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=? and depo_code<>32";

            if(utype==4 && depo_code>0) 
            	terrec = "Select count(*) from "+tblnm3+" where user_id=? and depo_code=? and status=?";
            else if(utype==1 || utype==31)
            	terrec = "Select count(*)  from hqmast  where mkt_year=? and div_code=? and depo_code=? and ifnull(del_tag,'N')<>'D'";
 			ts1 = con.prepareStatement(terrec);
 			if(utype==4 && depo_code>0)
 			{
	 			ts1.setInt(1,loginid);
 				ts1.setInt(2,depo_code);
	 			ts1.setString(3,"Y");

 			}
 			else if(utype==1 || utype==31)
 			{
 				ts1.setInt(1,eyear);
 				ts1.setInt(2,div_code);
 				ts1.setInt(3,depo_code);

 			}
 			else
 			{
	 			ts1.setInt(1,loginid);
	 			ts1.setString(2,"Y");
 			}
			trec = ts1.executeQuery();
			if (trec.next())
			{
				t = trec.getInt(1)+1;
				w= trec.getInt(1);
			}
			
			System.out.println("value of t and w "+t+"  "+w+" utype "+utype);
			
				gval = new double[t]; 
				grval = new double[t]; 
				depo = new int[w];
				depo_name = new String[w];
				nrep = new int[w];
            	
            trec.close();
            ts1.close();
            
            String branchrec = "Select b.depo_code,b.depo_name,sum(m.qty) from branch_comp b,mkt_master m "+ 
							   "where m.mkt_year=? and m.div_code=? and m.depo_code=b.depo_code and m.doc_type=30 "+
							   "and m.mnth_code between (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+ 
							   "and (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+
							   "and b.depo_code in  (Select depo_code from user_branch08 where user_id=? and status='Y') "+ 
							   "group by m.div_code,m.depo_code " ;

            if(div_code>=12 && div_code<=16)
                branchrec = "Select b.depo_code,b.depo_name,sum(m.qty) from branch_comp b,mkt_master m "+ 
    							   "where m.mkt_year=? and m.div_code=? and m.depo_code=b.depo_code and m.doc_type=30 "+
    							   "and m.mnth_code between (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+ 
    							   "and (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+
    							   "and b.depo_code in  (Select depo_code from user_branch08 where user_id=? and status='Y' and depo_code=32) "+ 
    							   "group by m.div_code,m.depo_code " ;
            else if(div_code!=3)
                branchrec = "Select b.depo_code,b.depo_name,sum(m.qty) from branch_comp b,mkt_master m "+ 
    							   "where m.mkt_year=? and m.div_code=? and m.depo_code=b.depo_code and m.doc_type=30 "+
    							   "and m.mnth_code between (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+ 
    							   "and (select mnth_code from monthfl where mkt_year=? and mnth_ord=?) "+
    							   "and b.depo_code in  (Select depo_code from user_branch08 where user_id=? and status='Y' and depo_code<>32) "+ 
    							   "group by m.div_code,m.depo_code " ;
            	

            if(utype==4 && depo_code>0)
            		branchrec = "Select ter_code,ter_name,0 nrep from hqmast  where mkt_year=? and div_code=? and depo_code=? and (depo_code,ter_code) in "+ 
 				   "(Select depo_code,ter_code from user_ter where user_id=? and status='Y') order by depo_code,ter_code ";
            else if(utype==4)
        		branchrec = "Select ter_code,ter_name,0 nrep from hqmast  where mkt_year=? and div_code=? and (depo_code,ter_code) in "+ 
				   "(Select depo_code,ter_code from user_ter where user_id=? and status='Y') order by depo_code,ter_code ";
            else if(utype==1 || utype==31)
        		branchrec = "Select h.ter_code,h.ter_name,sum(m.qty) from hqmast h,mkt_master m "+ 
							"where h.mkt_year=? and h.div_code=? and h.depo_code=? and ifnull(h.del_tag,'N')<>'D' "+
							"and m.mkt_year=? and m.div_code=? and m.depo_code=? and m.hq_code=h.ter_code and m.doc_type=30 "+
							"and m.mnth_code between (select mnth_code from monthfl where mkt_year=? and mnth_ord=?)  and " +
							"(select mnth_code from monthfl where mkt_year=? and mnth_ord=?)  group by m.div_code,m.depo_code,m.hq_code";

            System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid+" saletype "+saletp+" doctype "+doctp);
            
 			ps = con.prepareStatement(branchrec);
            if(utype==4 && depo_code>0)
            {
            	ps.setInt(1,eyear);
            	ps.setInt(2,div_code);
            	ps.setInt(3,depo_code);
            	ps.setInt(4,loginid);
            }
            else if(utype==4)
            {
            	ps.setInt(1,eyear);
            	ps.setInt(2,div_code);
            	ps.setInt(3,loginid);
            }
            else if(utype==1 || utype==31)
            {
            	ps.setInt(1,eyear);
            	ps.setInt(2,div_code);
            	ps.setInt(3,depo_code);
            	ps.setInt(4,eyear);
            	ps.setInt(5,div_code);
            	ps.setInt(6,depo_code);
            	ps.setInt(7,eyear);
                ps.setInt(8, smon);
            	ps.setInt(9,eyear);
                ps.setInt(10, emon);

            }
            else
            {
            	ps.setInt(1,eyear);
            	ps.setInt(2,div_code);
            	ps.setInt(3,eyear);
                ps.setInt(4, smon);
            	ps.setInt(5,eyear);
                ps.setInt(6, emon);
            	ps.setInt(7,loginid);
            }

			rs = ps.executeQuery();
			int i=0;
			while (rs.next())
			{
				depo[i] = rs.getInt(1);
				depo_name[i] = rs.getString(2);
				nrep[i]=rs.getInt(3);
				i++;
			}
			rs.close();
			ps.close();
/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;
            

           cs  = con.prepareCall(procedureWithParameters);
           cs.setInt(1, eyear);
           cs.setInt(2, div_code);
           cs.setInt(3, depo_code);
           cs.setInt(4, smon);
           cs.setInt(5, emon);
           cs.setInt(6, utype);
           cs.setInt(7, loginid);
           cs.setInt(8, rep_type);
           if(saletp==1 || saletp==2) // Gross Sale/PI        	   
        	   cs.setInt(9, saletp);
           else if(saletp==7 || saletp==9 || saletp==4 || saletp==5) //  Gth/Sur-def       	   
        	   cs.setInt(9, doctp);
           else if(saletp==10 || saletp==11 || saletp==12) //  Saleable/Expiry/Breakage       	   
        	   cs.setInt(9, saletp);
          
           rst1 = cs.executeQuery();   
           int k=0;  
           gsalqty=0.00;
           gsalval=0.00;
           
           boolean hrprint = true;	
           boolean hprint = true;	
           int pcode=0;
           String name=null;
           String pack=null;
           int gcode=0;
           String gname=null;
           rfb = new HORepo1FormBean();
           
			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
				  pcode=rst1.getInt(7);
				  name = rst1.getString(10);
				  pack=rst1.getString(11);
				  gcode=rst1.getInt(8);
				  gname = rst1.getString(18);
				  
				  hrprint=false;
				  txt5= rst1.getString(16)+" TO "+rst1.getString(17);
				}
				
				if (pcode!=rst1.getInt(7))
				{
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
					rfb.setBr(t);
					rfb.setUv(tuv);
					

					for(int q=k;q<w;q++)
					{
						if (hprint)
						{
							if ((uv==1) || (uv==3))
							{
								rfb.setUhead(k,(depo_name[q]));
							}
							if ((uv==2) || (uv==3)) 
							{
								rfb.setVhead(k,(depo_name[q]));
							}
						}
						rfb.setQty1(k,0);
						rfb.setDval0(k,0.00);
						k++;

					}



					if(k==w)
					{	                
						if ((uv==1) || (uv==3))
						{	 
							rfb.setUhead(k,"TOTAL ");
							rfb.setQty1(k,gsalqty);
						}		

						if ((uv==2) || (uv==3))
						{	 
							rfb.setVhead(k,"TOTAL ");
							rfb.setDval0(k,gsalval);
						}		
						gval[k]=gval[k]+gsalval;
					}
					k=0;
					gsalqty=0;
					gsalval=0.00;
					hprint=false;
					data.add(rfb);
					pcode=rst1.getInt(7);
					name = rst1.getString(10);
					pack=rst1.getString(11);
					rfb = new HORepo1FormBean();

				}// endif pcode
				
				
				if (gcode!=rst1.getInt(8))
				{
					rfb.setPcode(gcode);
					rfb.setPname(gname);
					rfb.setPack("");
					rfb.setBr(t);
					rfb.setUv(tuv);
					rfb.setColor(3);

					for(int q=k;q<w;q++)
					{
						rfb.setQty1(q,grval[q]);
						rfb.setDval0(q,grval[q]);
						gsalval+=grval[q];
						k++;

					}



					if(k==w)
					{	                
						if ((uv==1) || (uv==3))
						{	 
							rfb.setUhead(k,"TOTAL VALUE");
							rfb.setQty1(k,gsalval);
						}		

						if ((uv==2) || (uv==3))
						{	 
							rfb.setVhead(k,"TOTAL VALUE");
							rfb.setDval0(k,gsalval);
						}		
						gval[k]=gval[k]+gsalval;
					}
					k=0;
					gsalqty=0;
					gsalval=0.00;
					hprint=false;
					data.add(rfb);
					gcode=rst1.getInt(8);
					gname = rst1.getString(18);
					grval = new double[t]; 
					rfb = new HORepo1FormBean();

				} // endif gpcode

	 			
	 			for(int q=k;q<w;q++)
	 			{
	 				if(depo[q]==rst1.getInt(5))
	 					break;
	 				else
	 				{
		                 if (hprint)
		                 {
				        	if ((uv==1) || (uv==3))
				        	{
				        		  rfb.setUhead(k,(depo_name[q]));
				        	}
				        	if ((uv==2) || (uv==3)) 
				        	{
					        		rfb.setVhead(k,(depo_name[q]));
				        	}
		                 }
		        		 rfb.setQty1(k,0);
		        		 rfb.setDval0(k,0.00);
		        		 k++;
	 					
	 				}
	 			}
	 			
                 if (hprint)
                 {
		        	if ((uv==1) || (uv==3))
		        	{
		        		  rfb.setUhead(k,(rst1.getString(6)));
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{
		        			rfb.setVhead(k,(rst1.getString(6)));
		        	}
                 }


					   salqty=0.00;
			           salval=0.00;
          

			         if (saletp==1)
			         {	 
			        	salqty = rst1.getDouble(12);
 		        		salval=rst1.getDouble(15);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
			        	 salqty = rst1.getDouble(12);;
			        	 salval=rst1.getDouble(15);;
			         }	 

			         if (saletp==3)
			         {	 
			        	 salqty=rst1.getDouble(12);;
			        	 salval=rst1.getDouble(15);;
			         }

			         
		        	 salqty=rst1.getDouble(12);;
		        	 salval=rst1.getDouble(15);;
		        	 
		        	 
			         if (saletp==8)
			         {	 
			        	 if(nrep[k]!=0)
			        	 {
			        		 salqty=rst1.getDouble(12)/nrep[k];;
			        		 salval=rst1.getDouble(15)/nrep[k];;
			        	 }
			         }

			        
		        	 
						if(k!=w)
						{	
			             gval[k] = gval[k]+salval;
			             grval[k] = grval[k]+salval;
						}

			        
		 			
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 
		      
		        	     rfb.setHead1(txt1+txt2+txt3+txt4+txt5);
		        	     k++;

				          
			        	 
				} //////////////////////// End of Query Loop///////////////////////
			
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
		            rfb.setBr(t);
		            rfb.setUv(tuv);
		            
		            if(k==w)
		            {	                
		            	if ((uv==1) || (uv==3))
		            	{	 
		            		rfb.setUhead(k,"TOTAL UNIT");
		            		rfb.setQty1(k,gsalqty);
		            	}		

		            	if ((uv==2) || (uv==3))
		            	{	 
		            		rfb.setVhead(k,"TOTAL VALUE");
		            		rfb.setDval0(k,gsalval);
		            	}		
		            	gval[k]=gval[k]+gsalval;
		            }
			        data.add(rfb); 	

			        // Group total
			        k=0;
            	    rfb = new HORepo1FormBean();
			        rfb.setPcode(gcode);
					rfb.setPname(gname);
					rfb.setPack("");
					rfb.setBr(t);
					rfb.setUv(tuv);
					rfb.setColor(3);

					for(int q=k;q<w;q++)
					{
						rfb.setQty1(q,grval[q]);
						rfb.setDval0(q,grval[q]);
						gsalval+=grval[q];
						k++;

					}

					if(k==w)
					{	                
						if ((uv==1) || (uv==3))
						{	 
							rfb.setUhead(k,"TOTAL VALUE");
							rfb.setQty1(k,gsalval);
						}		

						if ((uv==2) || (uv==3))
						{	 
							rfb.setVhead(k,"TOTAL VALUE");
							rfb.setDval0(k,gsalval);
						}		
						gval[k]=gval[k]+gsalval;
					}
					data.add(rfb);
			        
			        
			        
			        
			        
 			        
            	   rfb = new HORepo1FormBean();
			
    			   rfb.setPname("Total :");

			       for (int z=0; z<t;z++)
			       {
		   			  if (uv==1)
			   		  	 rfb.setQty1(z,(int) gval[z]);
		   			  else
		   			  	 rfb.setDval0(z,gval[z]); 
			       }
			        data.add(rfb); 				
			    
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("========Exception in SQLHORepo1DAO.getAllBranch " + e);
		}
		
		finally
		{
			try 
				{
		           if(trec != null){ trec.close();}
		           if(ts1 != null){ ts1.close();}
		           if(rst1 != null){ rst1.close();}
		           if(cs != null){ cs.close();}
		           if(rs != null){ rs.close();}
		           if(ps != null){ ps.close();}
		           if(rs1 != null){ rs1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				}
				catch (SQLException e) 
					{
						System.out.println("-------Exception in SQLHORepo1DAO.Connection.close "+e);
					}
		}		
		return data; 
	}

	
	
	
	public List getAllBranchOld(Connection con, int uv, int smon, int emon, int saletp,String tp,String typ,int code1,int loginid,int eyear, String sr) { 
		 
		HORepo1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
		
		List<HORepo1FormBean> data = new ArrayList<HORepo1FormBean>();
		try {     
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String tblnm5=null;   
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null; 
            String txt4=null;
            String txt5 =null;
            double salqty=0.00;
            double salval=0.00;
            double gval[];
            double gsalqty=0.00;
            double gsalval=0.00;

            if (smon>emon)
            	emon=smon;

   	        tblnm3="user_branch08";
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
   	        tblnm4=(tp+"_brsale").toLowerCase();
   	        tblnm5="pmt_group";
       	    
           if (uv==1)
            {
            	txt3="UnitWise/";
            }
            
            if (uv==2)
            {
            	txt3="ValueWise/";
            }
            if (uv==3)
            {
            	txt3="Unit-ValueWise/";
            }
            
            if(saletp==1)
            	txt4="Gross Sale For the Month of ";
            if(saletp==2)
            	txt4="Credit Note Sale For the Month of ";
            if(saletp==3)
            	txt4="Net Sale For the Month of ";
            
                txt2="Product Wise/"; 
                txt1="Branch Wise/";
			
            int t=0;
            int w=0;

//////////////////////////////////////// Branch Master Count/////////////////////////////////// 
            String terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=?";
 			ts1 = con.prepareStatement(terrec);
 			ts1.setInt(1,loginid);
 			ts1.setString(2,"Y");
			trec = ts1.executeQuery();
			if (trec.next())
			{
				t = trec.getInt(1)+1;
				w= trec.getInt(1);
			}
				gval = new double[t]; 
            	
            trec.close();
            ts1.close();
 
/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

           if (typ.equals("PMT"))
		   {
        	 if (sr==null)
  	         {        	   
			   query1="Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0), " +
			   " ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) " +
			   " from (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name " +
			   " from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=? and p.pd_group in " +
			   " (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?)) " +
			   " prd left join (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) " +
			   " cr_u,sum(net_units) net_u, sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) " +
			   " net_v from "+tblnm4+" where month>=? and month<=? and mkt_year=? and pr_code<>0 " +
			   " and grp_cd in (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?) " +
			   " group by depo_code,grp_cd,pr_code) sale on prd.prd_code = sale.pr_code" +
			   " and prd.br_code = sale.depo_code inner join "+tblnm3+" u on u.depo_code=prd.br_code " +
			   " where u.user_id=? and u.status=? order by prd_code,br_code ";
  	         }
        	 else
        	 {
  			   query1="Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0), " +
			   " ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) " +
			   " from (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name " +
			   " from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=? and p.pd_group in " +
			   " (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?)) " +
			   " prd left join (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) " +
			   " cr_u,sum(net_units) net_u, sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) " +
			   " net_v from "+tblnm4+" where month>=? and month<=? and mkt_year=? and pr_code<>0 " +
			   " and grp_cd in (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?) " +
			   " group by depo_code,grp_cd,pr_code) sale on prd.prd_code = sale.pr_code" +
			   " and prd.br_code = sale.depo_code inner join "+tblnm3+" u on u.depo_code=prd.br_code " +
			   " where u.user_id=? and u.status=? and prd.prd_name like '"+sr+"%' order by prd_code,br_code ";
        	 }
		   }
           
		   else
		   {
   			    if (sr==null)
	  	        {        	   
				query1 = "select * from (Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0)," +
		   		" ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) from " +
		   		" (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name" +
		   		" from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=? and b.depo_code in " +
		   		" (select  depo_code from user_branch08 where  user_id = ? and status = ?)) prd left join " +
		   		" (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) cr_u,sum(net_units) net_u," +
		   		" sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) net_v from "+tblnm4+
		   		" where month>=? and month<=? and mkt_year=? and pr_code<>0 group by depo_code,pr_code) sale on " +
		   		" prd.prd_code = sale.pr_code and prd.br_code = sale.depo_code ) a order by a.prd_code,a.br_code" ;
	  	        }
   			    else
   			    {
				query1 = "select * from (Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0)," +
		   		" ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) from " +
		   		" (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name" +
		   		" from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=? and p.pname like '"+sr+"%' and b.depo_code in " +
		   		" (select  depo_code from user_branch08 where  user_id = ? and status = ?)) prd left join " +
		   		" (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) cr_u,sum(net_units) net_u," +
		   		" sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) net_v from "+tblnm4+
		   		" where month>=? and month<=? and mkt_year=? and pr_code<>0 group by depo_code,pr_code) sale on " +
		   		" prd.prd_code = sale.pr_code and prd.br_code = sale.depo_code ) a order by a.prd_code,a.br_code" ;
   			    }
		   }
           ps1 = con.prepareStatement(query1);
		   if (typ.equals("PMT"))
		   {
           ps1.setInt(1,eyear);
	       ps1.setInt(2,loginid); 
	       ps1.setString(3, "Y");
           ps1.setInt(4,smon);
           ps1.setInt(5,emon);
           ps1.setInt(6,eyear);
	       ps1.setInt(7,loginid); 
	       ps1.setString(8, "Y");
	       ps1.setInt(9,loginid); 
	       ps1.setString(10, "Y");
		   }
		   else
		   {
           ps1.setInt(1,eyear);
	       ps1.setInt(2,loginid); 
	       ps1.setString(3, "Y");
           ps1.setInt(4,smon);
           ps1.setInt(5,emon);
           ps1.setInt(6,eyear);
		   }
		   rst1 = ps1.executeQuery();   
           int k=0;  
           gsalqty=0.00;
           gsalval=0.00;
           
           boolean hrprint = true;	
           boolean hprint = true;	
           int pcode=0;
           String name=null;
           String pack=null;
           rfb = new HORepo1FormBean();
           
			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
				  pcode=rst1.getInt(1);
				  name = rst1.getString(2);
				  pack=rst1.getString(3);
				  hrprint=false;
				}
	 			if (pcode!=rst1.getInt(1))
	 			{
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
	                rfb.setBr(t);
	                rfb.setUv(uv);
	                
			        if(k==w)
			        {	                
		            if ((uv==1) || (uv==3))
	        	 	{	 
	            		rfb.setUhead(k,"TOTAL UNIT");
	            		rfb.setQty1(k,gsalqty);
	        	 	}		
	                  
	        	 	if ((uv==2) || (uv==3))
	        	 	{	 
	            		rfb.setVhead(k,"TOTAL VALUE");
	            		rfb.setDval0(k,gsalval);
	        	 	}		
	                gval[k]=gval[k]+gsalval;
			        }
		        	k=0;
		        	gsalqty=0;
		        	gsalval=0.00;
		        	hprint=false;
		        	data.add(rfb);
					pcode=rst1.getInt(1);
					name = rst1.getString(2);
					pack=rst1.getString(3);
		        	rfb = new HORepo1FormBean();
	                
	 			}
                 if (hprint)
                 {
		        	if ((uv==1) || (uv==3))
		        	{	
		        	  rfb.setUhead(k,(rst1.getString(5)+" Units"));
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{	
		        	  rfb.setVhead(k,(rst1.getString(5)+" Value"));
		        	}
                 }


		   salqty=0.00;
           salval=0.00;
          

			        	 
			         if (saletp==1)
			         {	 
			        	salqty = rst1.getDouble(6);
 		        		salval=rst1.getDouble(9);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
			        	 salqty = rst1.getDouble(7);;
			        	 salval=rst1.getDouble(10);;
			         }	 

			         if (saletp==3)
			         {	 
			        	 salqty=rst1.getDouble(8);;
			        	 salval=rst1.getDouble(11);;
			         }	 

			             gval[k] = gval[k]+salval;

			        
		 			
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 
		      
		        	     rfb.setHead1(txt1+txt2+txt3+txt4+txt5);
		        	     k++;
				          
			        	 
				} //////////////////////// End of Query Loop///////////////////////
			
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
		            rfb.setBr(t);
		            rfb.setUv(uv);
		            
			        if(k==w)
			        {	                
		            if ((uv==1) || (uv==3))
		    	 	{	 
		        		rfb.setUhead(k,"TOTAL UNIT");
		        		rfb.setQty1(k,gsalqty);
		    	 	}		
		              
		    	 	if ((uv==2) || (uv==3))
		    	 	{	 
		        		rfb.setVhead(k,"TOTAL VALUE");
		        		rfb.setDval0(k,gsalval);
		    	 	}		
		            gval[k]=gval[k]+gsalval;
			        }
			        data.add(rfb); 	
					
            	   rfb = new HORepo1FormBean();
			
    			   rfb.setPname("Total :");

			       for (int z=0; z<t;z++)
			       {
		   			  if (uv==1)
			   		  	 rfb.setQty1(z,(int) gval[z]);
		   			  else
		   			  	 rfb.setDval0(z,gval[z]); 
			       }
			        data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo1DAO.getAllBranch " + e);
		}
		
		finally
		{
			try 
				{
		           if(trec != null){ trec.close();}
		           if(ts1 != null){ ts1.close();}
		           if(rst1 != null){ rst1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				}
				catch (SQLException e) 
					{
						System.out.println("-------Exception in SQLHORepo1DAO.Connection.close "+e);
					}
		}		
		return data; 
	}
	
/////////////////////////////////////////Branch Close here//////////////////////////////////////////////////////	
	
}   
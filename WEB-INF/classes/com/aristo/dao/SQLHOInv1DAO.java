package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo1FormBean;
import com.aristo.valueobject.Inv1FormBean;

public class SQLHOInv1DAO {

	public List getHOInv1(Connection con, int emon, String tp,String typ,int code1, int loginid,int eyear,int xcode) { 
		 
		Inv1FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        

        
        List<Inv1FormBean> data = new ArrayList<Inv1FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String wname=null;
            
            String txt5=null;
            String txt2=null;

        	double opnbal=0.00;
        	double dambal=0.00;
        	double badbal=0.00;
        	double othbal=0.00;
        	double bplbal=0.00;
        	double salqty=0.00;
        	double freqty=0.00;
        	double brkqty=0.00;
        	double expqty=0.00;
        	double sales=0.00;
        	double fissue=0.00;
        	double salsamp=0.00;
        	double woff=0.00;
        	double branches=0.00;
        	double cnf=0.00;
        	double closbal=0.00;
        	double mfgval=0.00;

        	double gopnbal=0.00;
        	double gdambal=0.00;
        	double gbadbal=0.00;
        	double gothbal=0.00;
        	double gbplbal=0.00;
        	double gsalqty=0.00;
        	double gfreqty=0.00;
        	double gbrkqty=0.00;
        	double gexpqty=0.00;
        	double gsales=0.00;
        	double gfissue=0.00;
        	double gsalsamp=0.00;
        	double gwoff=0.00;
        	double gbranches=0.00;
        	double gcnf=0.00;
        	double gclosbal=0.00;
        	double gmfgval=0.00;
        	double rate=0.00;
        	
        	tblnm3="user_branch08";
        	
        	tblnm=(tp+"_product08").toLowerCase();
        	tblnm1=(tp+"_stock08").toLowerCase();
        	tblnm2=(tp+"_branch08").toLowerCase();
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no from monthfl where mnth_ord=? and mkt_year=? order by mnth_ord";  
    		ms1 = con.prepareStatement(month);
    		ms1.setInt(1, emon);
    		ms1.setInt(2,eyear);
    	 	mrec = ms1.executeQuery();
            
			    if (mrec.next())
 			    {	
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 	                wname = mrec.getString(3);
 			    }  
 			    	
	 			ms1.close();
	 			mrec.close();
	 			
	 		 String product ="Select pname,pack,trd_rt1 from "+tblnm+" where pcode=? and mkt_year=? ";
	    	 ms1 = con.prepareStatement(product);
	    	 ms1.setInt(1, xcode);
	    	 ms1.setInt(2,eyear);
	    	 mrec = ms1.executeQuery();
	    	 if (mrec.next())
	    	 {
	    		 
		 			txt2=mrec.getString(1)+" "+mrec.getString(2)+"--> STOCK & SALES FOR THE MONTH OF  "+txt5;
		 			rate=mrec.getDouble(3);
	    	 }
	 		 mrec.close();
	 		 ms1.close();
	    	 
 ////////////////////////////Product Master Ki Query//////////////////////////////////
            String query1="select s.depo_code,b.ter_name,s.doc_type,"+
       		"sum(s.opng"+wname+"),sum(s.clos"+wname+"),sum(s.qty"+wname+"),sum(s.free"+wname+"),sum(s.val"+wname+"),sum(s.fval"+wname+
            ") from "+tblnm1+" as s,"+tblnm3+" as u,"+tblnm2+" as b"+
            " where u.depo_code=s.depo_code  and  u.depo_code=b.depo_code "+
            " and  s.depo_code=b.depo_code  and  u.user_id=? and u.status=? and s.mkt_year=? " +
            " and  s.item_code=? group by s.depo_code,s.doc_type order by s.depo_code,s.doc_type";                
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        ps1.setInt(3,eyear);
	        ps1.setInt(4, xcode);
			rst1 = ps1.executeQuery();
      
            boolean first = false;
            boolean print =false;
            int pcode=0;
            String pname =null;

			
			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	
				
				
                if (!first)
                {
        	 	  pcode=rst1.getInt(1);
        	 	  pname = rst1.getString(2);
                }
				if (first)
	        	 {
	        	  if (pcode!=rst1.getInt(1))
	        		  print=true;
	        	 }
	        	 
                if (print)
                {
	    			 rfb = new Inv1FormBean();
	    			 rfb.setProduct(pname);
					 rfb.setOpnbal(opnbal);
					 rfb.setDambal(dambal);
					 rfb.setBadbal(badbal);
					 rfb.setOthbal(othbal);
					 rfb.setBplbal(bplbal);
					 rfb.setSalqty(salqty);
					 rfb.setFreqty(freqty);
					 rfb.setExpqty(expqty);
					 rfb.setBrkqty(brkqty);
					 rfb.setSales(sales);
					 rfb.setFissue(fissue);
					 rfb.setSalsamp(salsamp);
					 rfb.setWoff(woff);
					 rfb.setBranches(branches);
					 rfb.setCnf(cnf);
					 rfb.setClosbal(closbal);
					 rfb.setMfgval(mfgval);

		             rfb.setHead1(txt2);
 		             
		             data.add(rfb);
					opnbal=0.00;
		        	dambal=0.00;
		        	badbal=0.00;
		        	othbal=0.00;
		        	bplbal=0.00;
		        	salqty=0.00;
		        	freqty=0.00;
		        	brkqty=0.00;
		        	expqty=0.00;
		        	sales=0.00;
		        	fissue=0.00;
		        	salsamp=0.00;
		        	woff=0.00;
		        	branches=0.00;
		        	cnf=0.00;
		        	closbal=0.00;
		        	mfgval=0.00; 
		        	
	        	 	  pcode=rst1.getInt(1);
	        	 	  pname = rst1.getString(2);
	        	 	  print=false;

                }				
	        	      first=true;
	        	      
                        if (rst1.getInt(3)==60)
                        {
                        	dambal=rst1.getDouble(6)+rst1.getDouble(7);
                    	    gdambal=gdambal+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==72)
                        {
                        	badbal=rst1.getDouble(6)+rst1.getDouble(7);
                    	    gbadbal=gbadbal+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==61)
                        {
                        	othbal=rst1.getDouble(6)+rst1.getDouble(7);
                    	    gothbal=gothbal+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==62)
                        {
                        	bplbal=rst1.getDouble(6)+rst1.getDouble(7);
                    	    gbplbal=gbplbal+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==63)
                        {
                        	salqty=rst1.getDouble(6);
                        	freqty=rst1.getDouble(7);
                        	gsalqty=gsalqty+rst1.getDouble(8);
                        	gfreqty=gfreqty+rst1.getDouble(9);
                        }
                        
                        if (rst1.getInt(3)==64)
                        {
                        	brkqty=rst1.getDouble(6);
                        	gbrkqty=gbrkqty+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==69)
                        {
                        	expqty=rst1.getDouble(6);
                        	gexpqty=gexpqty+rst1.getDouble(8);
                        }
                        
                        if (rst1.getInt(3)==80)
                        {
                        	sales=rst1.getDouble(6); 
                        	fissue=rst1.getDouble(7);
                        	opnbal= rst1.getDouble(4);
                        	closbal=  closbal + rst1.getDouble(5);
                        	mfgval =  (rst1.getDouble(5)*rate);
                        	
                        	gsales=gsales+rst1.getDouble(8);
                        	gfissue=gfissue+rst1.getDouble(9);
                        	gopnbal=gopnbal +(rst1.getDouble(4)*rate);
                        	gclosbal=gclosbal+(rst1.getDouble(5)*rate);
                        	gmfgval=gmfgval+(rst1.getDouble(5)*rate);
                        }
                        
                        if (rst1.getInt(3)==65)
                        {
                        	salsamp=rst1.getDouble(6);
                    	    gsalsamp=gsalsamp+rst1.getDouble(8);  
                        }
                        if (rst1.getInt(3)==66)
                        {
                        	woff=rst1.getDouble(6);
                    	    gwoff=gwoff+rst1.getDouble(8);
                        }
                        if (rst1.getInt(3)==67)
                        {
                        	branches=rst1.getDouble(6)+rst1.getDouble(7); 
                        	gbranches=gbranches+rst1.getDouble(8);
                        	closbal=  closbal-rst1.getDouble(7);
                        }
                        if (rst1.getInt(3)==68)
                        {
                        	cnf=rst1.getDouble(6);
                        	gcnf=gcnf+rst1.getDouble(8);
                        }
			      

  
                        
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
				
			 }  ////////////// Product Master Loop End here/////////////////////////

			
			 rfb = new Inv1FormBean();
			 rfb.setProduct(pname);
			 rfb.setOpnbal(opnbal);
			 rfb.setDambal(dambal);
			 rfb.setBadbal(badbal);
			 rfb.setOthbal(othbal);
			 rfb.setBplbal(bplbal);
			 rfb.setSalqty(salqty);
			 rfb.setFreqty(freqty);
			 rfb.setExpqty(expqty);
			 rfb.setBrkqty(brkqty);
			 rfb.setSales(sales);
			 rfb.setFissue(fissue);
			 rfb.setSalsamp(salsamp);
			 rfb.setWoff(woff);
			 rfb.setBranches(branches);
			 rfb.setCnf(cnf);
			 rfb.setClosbal(closbal);
			 rfb.setMfgval(mfgval);

             rfb.setHead1(txt2);
	             
             data.add(rfb);
			opnbal=0.00;
        	dambal=0.00;
        	badbal=0.00;
        	othbal=0.00;
        	bplbal=0.00;
        	salqty=0.00;
        	freqty=0.00;
        	brkqty=0.00;
        	expqty=0.00;
        	sales=0.00;
        	fissue=0.00;
        	salsamp=0.00;
        	woff=0.00;
        	branches=0.00;
        	cnf=0.00;
        	closbal=0.00;
        	mfgval=0.00; 
			
				 rfb=new Inv1FormBean();
				 rfb.setProduct("TOTAL VALUE"); 
				 rfb.setOpnbal(gopnbal);
				 rfb.setDambal(gdambal);
				 rfb.setBadbal(gbadbal);
				 rfb.setOthbal(gothbal);
				 rfb.setBplbal(gbplbal);
				 rfb.setSalqty(gsalqty);
				 rfb.setFreqty(gfreqty);
				 rfb.setExpqty(gexpqty);
				 rfb.setBrkqty(gbrkqty);
				 rfb.setSales(gsales);
				 rfb.setFissue(gfissue);
				 rfb.setSalsamp(gsalsamp);
				 rfb.setWoff(gwoff);
				 rfb.setBranches(gbranches);
				 rfb.setCnf(gcnf);
				 rfb.setClosbal(gclosbal);
				 rfb.setMfgval(gmfgval);
		             
	             data.add(rfb); 				
				
		        rst1.close();
		        ps1.close();

////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLHOInv1DAO.getInv1 " + e); 
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
						System.out.println("-------------Exception in SQLHOInv1DAO.Connection.close "+e);
					}
		}
		return data;
	}

	

	public List getHOInv2(Connection con, int emon, String tp,String typ,int code1, int loginid,int eyear,int xcode) { 
		 
		Inv1FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        CallableStatement cs=null;
        ResultSet rs=null;


        
        List<Inv1FormBean> data = new ArrayList<Inv1FormBean>();
		try {     
            String wname=null;
            
            String txt5=null;
            String txt2=null;

        	double closbal=0.00;
        	double mfgval=0.00;

        	double gopnbal=0.00;
        	double gdambal=0.00;
        	double gbadbal=0.00;
        	double gothbal=0.00;
        	double gbplbal=0.00;
        	double gsalqty=0.00;
        	double gfreqty=0.00;
        	double gbrkqty=0.00;
        	double gexpqty=0.00;
        	double gsales=0.00;
        	double gfissue=0.00;
        	double gsalsamp=0.00;
        	double gwoff=0.00;
        	double gbranches=0.00;
        	double gcnf=0.00;
        	double gclosbal=0.00;
        	double gmfgval=0.00;
        	
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no from monthfl where mnth_ord=? and mkt_year=? order by mnth_ord";  
    		ms1 = con.prepareStatement(month);
    		ms1.setInt(1, emon);
    		ms1.setInt(2,eyear);
    	 	mrec = ms1.executeQuery();
            
			    if (mrec.next())
 			    {	
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 	                wname = mrec.getString(3);
 			    }  
 			    	
	 			ms1.close();
	 			mrec.close();
	 			
	    	 System.out.println("year "+eyear+" emon "+emon+" tp "+tp+" xcode "+xcode );
			
            cs = con.prepareCall("{call sales_stock(?,?,?,?,?,?)}");
			cs.setInt(1, eyear); 
			cs.setInt(2, emon);
			cs.setString(3,tp);
			cs.setInt(4, xcode);
			cs.setInt(5, 0);
			cs.setInt(6, 0);
            boolean result = cs.execute();
            if(result)
            {
            	rs = cs.getResultSet();

            	while (rs.next())  ////////////Product Master Loop Starts/////////////////////////   
            	{	
            			rfb = new Inv1FormBean();
            			rfb.setProduct(rs.getString(4));
            			rfb.setOpnbal(rs.getDouble(5));
            			rfb.setDambal(rs.getDouble(6));
            			rfb.setBadbal(rs.getDouble(7));
            			rfb.setOthbal(rs.getDouble(8));
            			rfb.setBplbal(rs.getDouble(9));
            			rfb.setSalqty(rs.getDouble(10));
            			rfb.setFreqty(rs.getDouble(11));
            			rfb.setExpqty(rs.getDouble(12));
            			rfb.setBrkqty(rs.getDouble(13));
            			rfb.setSales(rs.getDouble(14));
            			rfb.setFissue(rs.getDouble(15));
            			rfb.setSalsamp(rs.getDouble(16));
            			rfb.setWoff(rs.getDouble(17));
            			rfb.setBranches(rs.getDouble(18)); 
            			rfb.setCnf(rs.getDouble(19));
            			
            			closbal=(rs.getDouble(5)+rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(8)+rs.getDouble(9)-(rs.getDouble(10)+rs.getDouble(11)+rs.getDouble(12)+rs.getDouble(13)+rs.getDouble(18)));
            			rfb.setClosbal(rs.getDouble(20));
            			rfb.setMfgval(rs.getDouble(21));
            			rfb.setHead1(rs.getString(2)+","+rs.getString(3)+"--> STOCK & SALES FOR THE MONTH OF  "+txt5);
            			data.add(rfb);

            	}  ////////////// Product Master Loop End here/////////////////////////
            }
			
				 rfb=new Inv1FormBean();
				 rfb.setProduct("TOTAL VALUE"); 
				 rfb.setOpnbal(gopnbal);
				 rfb.setDambal(gdambal);
				 rfb.setBadbal(gbadbal);
				 rfb.setOthbal(gothbal);
				 rfb.setBplbal(gbplbal);
				 rfb.setSalqty(gsalqty);
				 rfb.setFreqty(gfreqty);
				 rfb.setExpqty(gexpqty);
				 rfb.setBrkqty(gbrkqty);
				 rfb.setSales(gsales);
				 rfb.setFissue(gfissue);
				 rfb.setSalsamp(gsalsamp);
				 rfb.setWoff(gwoff);
				 rfb.setBranches(gbranches);
				 rfb.setCnf(gcnf);
				 rfb.setClosbal(gclosbal);
				 rfb.setMfgval(gmfgval);
		             
	             data.add(rfb); 				
				

////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLHOInv1DAO.getInv1 " + e); 
			e.printStackTrace();
		}
		  finally 
		{
			try
				{
		           if(mrec != null){mrec.close();}
		           if(ms1 != null){ ms1.close();}
		           if(rs != null){rs.close();}
		           if(cs != null){ cs.close();}
		           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLHOInv1DAO.Connection.close "+e);
					}
		}
		return data;
	}

	
	
	
	public List getAllBranch(Connection con, int emon, String tp,int eyear,int loginid,String sr) { 
		 
		HORepo1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        CallableStatement cs=null;
        ResultSet rs=null;
        PreparedStatement ps1=null; 
        ResultSet rs1=null;
        
        
		
		List<HORepo1FormBean> data = new ArrayList<HORepo1FormBean>();
		try {     
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null; 
            String txt4=null;
            String txt5 =null;
            double salqty=0.00;
            double gval[];
            double gsalqty=0.00;
            double gsalval=0.00;

             

            tblnm3="user_branch08";

            txt3="UnitWise/";
            txt4="Sample Stock Statement For the Month of ";

            txt2="Product Wise/"; 
            txt1="Branch Wise/";

            int t=0;
            int w=0;

            String monrec = "select mnth_name,mnth_year from monthfl where mkt_year=? and mnth_ord=?";
 			ps1 = con.prepareStatement(monrec);
 			ps1.setInt(1,eyear);
 			ps1.setInt(2,emon);
			rs1 = ps1.executeQuery();
			if (rs1.next())
			{
				txt5=rs1.getString(1)+"-"+rs1.getInt(2);
			}
				
            
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

           
            cs = con.prepareCall("{call sample_stock(?,?,?,?)}");
			cs.setInt(1, eyear); 
			cs.setInt(2, emon);
			cs.setString(3,tp);
			cs.setString(4,sr); //search string
           
           
           int k=0;  
           gsalqty=0.00;
           gsalval=0.00;
           
           boolean hrprint = true;	
           boolean hprint = true;	
           int pcode=0;
           String name=null;
           String pack=null;
           rfb = new HORepo1FormBean();
           
           boolean result = cs.execute();
			if(result)
			{
				rs = cs.getResultSet();
			while (rs.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
				  pcode=rs.getInt(3);
				  name = rs.getString(4);
				  pack=rs.getString(5);
				  hrprint=false;
				}
	 			if (pcode!=rs.getInt(3))
	 			{
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
	                rfb.setBr(t);
	                rfb.setUv(1);
	                
	                if(k==w)
	                {	                
	                	rfb.setUhead(k,"TOTAL UNIT");
	                	rfb.setQty1(k,gsalqty);
	                	gval[k]=gval[k]+gsalval;
	                }
	                k=0;
		        	gsalqty=0;
		        	hprint=false;
		        	data.add(rfb);
		        	pcode=rs.getInt(3);
		        	name = rs.getString(4);
		        	pack=rs.getString(5);
		        	rfb = new HORepo1FormBean();
	                
	 			}
                 if (hprint)
                 {
		        	  rfb.setUhead(k,(rs.getString(2)+" Units"));
                 }


                 salqty=0.00;
                 salqty = rs.getDouble(6);
                 rfb.setQty1(k,salqty);
                 gsalqty=gsalqty+salqty;
                 rfb.setHead1(txt1+txt2+txt3+txt4+txt5);
                 k++;


				} //////////////////////// End of Query Loop///////////////////////
			}
			
					rfb.setPcode(pcode);
					rfb.setPname(name);
					rfb.setPack(pack);
		            rfb.setBr(t);
		            rfb.setUv(1);
		            
		            if(k==w)
		            {	                
		            	rfb.setUhead(k,"TOTAL UNIT");
		            	rfb.setQty1(k,gsalqty);
		            	gval[k]=gval[k]+gsalval;
		            }
			        data.add(rfb); 	
					
            	   rfb = new HORepo1FormBean();
			
    			   rfb.setPname("Total :");

			       for (int z=0; z<t;z++)
			       {
			   		  	 rfb.setQty1(z,(int) gval[z]);
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
		           if(rs != null){ rs.close();}
		           if(cs != null){ cs.close();}
		           if(con != null){con.close();}
				}
				catch (SQLException e) 
					{
						System.out.println("-------Exception in SQLHORepo1DAO.Connection.close "+e);
					}
		}		
		return data; 
	}

	
	
}

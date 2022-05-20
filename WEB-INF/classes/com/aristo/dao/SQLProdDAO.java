package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.ProdFormBean;

public class SQLProdDAO {
	
	 public int updateProd(String typ,List pr, Connection con)
	   {
	    int i=0;
	    ProdFormBean p=null;
	    PreparedStatement ps =null;
	    PreparedStatement ps1 = null; 
	    PreparedStatement ps2 = null;
	    PreparedStatement ps3=null;
	    ResultSet rs =null;
  		try {
            String tblnm=null;
            tblnm=(typ+"_budget").toLowerCase();
	        String query3 =null;
	        String query1 ="select depo_code from "+tblnm+" where MKT_YEAR=? and DEPO_CODE=? and TR_CD=? and " +
	        " ttemp=?  order by mkt_year,depo_code,tr_cd,ttemp";
	        ps1 = con.prepareStatement(query1);

		String query = "update " + tblnm + " set pr_code=?,grp_cd=?,TMRP=?," +
	    "ta10=?,ta11=?,ta12=?,ta1=?,ta2=?,ta3=?,ta4=?,ta5=?,ta6=?,ta7=?,ta8=?,ta9=?," +
	    "ttarget=?,tvalue=?," +
	    "ra10=?,ra11=?,ra12=?,ra1=?,ra2=?,ra3=?,ra4=?,ra5=?,ra6=?,ra7=?,ra8=?,ra9=?,tnd=? " +
	    " where MKT_YEAR=? AND DEPO_CODE=? and TR_CD=? and ttemp=? ";
        ps = con.prepareStatement(query);

		String query2 ="insert into " + tblnm + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
		",?,?,?,?,?,?,?,?)";
		ps2 = con.prepareStatement(query2);
           
     	int bud=0;
    	boolean first=false;
    	con.setAutoCommit(false);
      	Iterator it = pr.iterator();
        if (it.hasNext())
        {
		p = (ProdFormBean) it.next();
  	    bud=p.getBudmnth();
  	    if (bud==0)
  	    {
  			query3 = "update " + tblnm + " set " +
  		    "ta10=0,ta11=0,ta12=0,ta1=0,ta2=0,ta3=0,ta4=0,ta5=0,ta6=0,ta7=0,ta8=0,ta9=0," +
  		    "ttarget=0,tvalue=0," +
  		    "ra10=0,ra11=0,ra12=0,ra1=0,ra2=0,ra3=0,ra4=0,ra5=0,ra6=0,ra7=0,ra8=0,ra9=0 " +
  		    " where MKT_YEAR=? AND DEPO_CODE=? ";
		ps3 = con.prepareStatement(query3);
		ps3.setInt(1,p.getMkt_year());
  	    ps3.setInt(2,p.getDepo_code());
		i= i + ps3.executeUpdate();
		ps3.close();
  	    }
  	    
		
        }
        
      	while(it.hasNext()) {
      		 if (first)
      		 {
    			p = (ProdFormBean) it.next();
      		 }
      		    first=true; 
				ps1.setInt(1, p.getMkt_year());
    			ps1.setInt(2,p.getDepo_code());
				ps1.setInt(3, p.getTr_cd());
				ps1.setInt(4, p.getTtemp());
  
				rs = ps1.executeQuery();
				if (rs.next())
				{
					System.out.println("Budget File Update Me a gaye");
					
					ps.setInt(1,p.getPr_code());                                                                                                   
					ps.setInt(2,p.getGrp_cd());			                                                                     
					ps.setFloat(3,p.getTmrp());                                                           
					ps.setInt(4,p.getTa10());                                                                                      
					ps.setInt(5,p.getTa11());                                                                                  
					ps.setInt(6,p.getTa12());                                                                                     
					ps.setInt(7,p.getTa1());                                                                         
					ps.setInt(8,p.getTa2());                                                                       
					ps.setInt(9,p.getTa3());                                                     
					ps.setInt(10,p.getTa4());                                                                                   
					ps.setInt(11,p.getTa5());                                              		                               	
					ps.setInt(12,p.getTa6());                                                	                                
					ps.setInt(13,p.getTa7());                                            		 
					ps.setInt(14,p.getTa8());                                            		 
					ps.setInt(15,p.getTa9());                                                       
					ps.setInt(16,p.getTtarget());																						                              		 
					ps.setFloat(17,p.getTvalue());																						                                      					                                                                                                       	 
					ps.setFloat(18,p.getRa10());																						                                
					ps.setFloat(19,p.getRa11());										
					ps.setFloat(20,p.getRa12());																						 
					ps.setFloat(21,p.getRa1());
					ps.setFloat(22,p.getRa2());
					ps.setFloat(23,p.getRa3());
					ps.setFloat(24,p.getRa4());
					ps.setFloat(25,p.getRa5());
					ps.setFloat(26,p.getRa6());
					ps.setFloat(27,p.getRa7());
					ps.setFloat(28,p.getRa8());
					ps.setFloat(29,p.getRa9());
					ps.setString(30, p.getTnd());
					ps.setInt(31,p.getMkt_year());
					ps.setInt(32,p.getDepo_code());
					ps.setInt(33,p.getTr_cd());                             
					ps.setInt(34,p.getTtemp());
					i= i + ps.executeUpdate();
				}
				else 
				{   
					System.out.println("Budget File Insert Me a gaye");

					ps2.setInt(1,p.getDepo_code());
					ps2.setInt(2,p.getPr_code());                                                                                                   
					ps2.setInt(3,p.getTr_cd());                             
					ps2.setInt(4,p.getGrp_cd());			                                                                     
					ps2.setFloat(5,p.getTmrp());                                                           
					ps2.setInt(6,p.getTa10());                                                                                      
					ps2.setInt(7,p.getTa11());                                                                                  
					ps2.setInt(8,p.getTa12());                                                                                     
					ps2.setInt(9,p.getTa1());                                                                         
					ps2.setInt(10,p.getTa2());                                                                       
					ps2.setInt(11,p.getTa3());                                                     
					ps2.setInt(12,p.getTa4());                                                                                   
					ps2.setInt(13,p.getTa5());                                              		                               	
					ps2.setInt(14,p.getTa6());                                                	                                
					ps2.setInt(15,p.getTa7());                                            		 
					ps2.setInt(16,p.getTa8());                                            		 
					ps2.setInt(17,p.getTa9());                                                       
					ps2.setInt(18,p.getTtarget());																						                              		 
					ps2.setFloat(19,p.getTvalue());																						                                      					                                                                                                       	 
					ps2.setFloat(20,p.getRa10());																						                                
					ps2.setFloat(21,p.getRa11());										
					ps2.setFloat(22,p.getRa12());																						 
					ps2.setFloat(23,p.getRa1());
					ps2.setFloat(24,p.getRa2());
					ps2.setFloat(25,p.getRa3());
					ps2.setFloat(26,p.getRa4());
					ps2.setFloat(27,p.getRa5());
					ps2.setFloat(28,p.getRa6());
					ps2.setFloat(29,p.getRa7());
					ps2.setFloat(30,p.getRa8());
					ps2.setFloat(31,p.getRa9());
					ps2.setInt(32,p.getTtemp());
					ps2.setInt(33,p.getMkt_year());
					ps2.setString(34, p.getTnd());

					
					i= i + ps2.executeUpdate();
				}
				rs.close();
      	}	////////////End of while loop
      	
		con.commit();
   		con.setAutoCommit(true);
   		ps.close();
   		ps1.close();
   		ps2.close();
   		ps3.close();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("-------------Exception in SQLProdDAOBudget.update " + ex);
			i=-1;
		}
		finally {
			try {
				   System.out.println("No. of Records Update/Insert : "+i);
		             if(rs != null){ rs.close();}
		             if(ps != null){ ps.close();}
		             if(ps1 != null){ps1.close();}
		             if(ps2 != null){ps2.close();}
		             if(ps3 != null){ps3.close();}		             
		    //         if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProdDAO.Connection.close "+e);
			  }
		}
			
			return i;
		   
	   }


	 public int updateBud(String typ,List pr, Connection con)
	   {
	    int i=0;
	    ProdFormBean p=null;
	    PreparedStatement ps =null;
	    PreparedStatement ps1 = null; 
	    PreparedStatement ps2 = null;
	    PreparedStatement ps3 = null;	    
	    ResultSet rs =null;
	    String query3=null;
		try 
		{
		String tblnm=null;
		tblnm=(typ+"_lastyear").toLowerCase();
          
        String query1 ="select depo_code from "+tblnm+" where MKT_YEAR=? and DEPO_CODE=? and TR_CD=? and " +
        " ttemp=?  order by mkt_year,depo_code,tr_cd,ttemp";
		ps1 = con.prepareStatement(query1);

		String query = "update " + tblnm + " set pr_code=?,grp_cd=?," +
	    "la10=?,la11=?,la12=?,la1=?,la2=?,la3=?,la4=?,la5=?,la6=?,la7=?,la8=?,la9=?," +
	    "rl10=?,rl11=?,rl12=?,rl1=?,rl2=?,rl3=?,rl4=?,rl5=?,rl6=?,rl7=?,rl8=?,rl9=? " +
	    " where MKT_YEAR=? AND DEPO_CODE=? and TR_CD=? and ttemp=? ";
		ps = con.prepareStatement(query);

		String query2 ="insert into "+tblnm+" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps2 = con.prepareStatement(query2);
        
    	boolean first=false;
    	con.setAutoCommit(false);
      	Iterator it = pr.iterator(); 
        if (it.hasNext())
        {
		p = (ProdFormBean) it.next();
		query3 = "update " + tblnm + " set " +
	    "la10=0,la11=0,la12=0,la1=0,la2=0,la3=0,la4=0,la5=0,la6=0,la7=0,la8=0,la9=0," +
	    "rl10=0,rl11=0,rl12=0,rl1=0,rl2=0,rl3=0,rl4=0,rl5=0,rl6=0,rl7=0,rl8=0,rl9=0 " +
	    " where MKT_YEAR=? AND DEPO_CODE=? ";
		ps3 = con.prepareStatement(query3);
		ps3.setInt(1,p.getMkt_year());
  	    ps3.setInt(2,p.getDepo_code());
		i= i + ps3.executeUpdate();
		ps3.close();
  	    }
  	    
		query3=null;		
   	    while(it.hasNext()) 
   	    {
     		 if (first)
      		 {
    			p = (ProdFormBean) it.next();
      		 }
      		    first=true; 
				ps1.setInt(1, p.getMkt_year());
    			ps1.setInt(2,p.getDepo_code());
				ps1.setInt(3, p.getTr_cd());
				ps1.setInt(4, p.getTtemp());
				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					System.out.println("Last Year File Update Me a gaye");
					ps.setInt(1,p.getPr_code());                                                                                                   
					ps.setInt(2,p.getGrp_cd());			                                                                     
					ps.setInt(3,p.getLa10());                                                                                      
					ps.setInt(4,p.getLa11());                                                                                  
					ps.setInt(5,p.getLa12());                                                                                     
					ps.setInt(6,p.getLa1());                                                                         
					ps.setInt(7,p.getLa2());                                                                       
					ps.setInt(8,p.getLa3());                                                     
					ps.setInt(9,p.getLa4());                                                                                   
					ps.setInt(10,p.getLa5());                                              		                               	
					ps.setInt(11,p.getLa6());                                                	                                
					ps.setInt(12,p.getLa7());                                            		 
					ps.setInt(13,p.getLa8());                                            		 
					ps.setInt(14,p.getLa9());                                                       
					ps.setFloat(15,p.getRl10());																						                                
					ps.setFloat(16,p.getRl11());										
					ps.setFloat(17,p.getRl12());																						 
					ps.setFloat(18,p.getRl1());
					ps.setFloat(19,p.getRl2());
					ps.setFloat(20,p.getRl3());
					ps.setFloat(21,p.getRl4());
					ps.setFloat(22,p.getRl5());
					ps.setFloat(23,p.getRl6());
					ps.setFloat(24,p.getRl7());
					ps.setFloat(25,p.getRl8());
					ps.setFloat(26,p.getRl9());
					ps.setInt(27,p.getMkt_year());
					ps.setInt(28,p.getDepo_code());
					ps.setInt(29,p.getTr_cd());                             
					ps.setInt(30,p.getTtemp());

					i= i + ps.executeUpdate();

				}
				else 
				{   
					System.out.println("Last Year File Insert Me a gaye");
					ps2.setInt(1,p.getDepo_code());
					ps2.setInt(2,p.getPr_code());                                                                                                   
					ps2.setInt(3,p.getTr_cd());                             
					ps2.setInt(4,p.getGrp_cd());			                                                                     
					ps2.setInt(5,p.getLa10());                                                                                      
					ps2.setInt(6,p.getLa11());                                                                                  
					ps2.setInt(7,p.getLa12());                                                                                     
					ps2.setInt(8,p.getLa1());                                                                         
					ps2.setInt(9,p.getLa2());                                                                       
					ps2.setInt(10,p.getLa3());                                                     
					ps2.setInt(11,p.getLa4());                                                                                   
					ps2.setInt(12,p.getLa5());                                              		                               	
					ps2.setInt(13,p.getLa6());                                                	                                
					ps2.setInt(14,p.getLa7());                                            		 
					ps2.setInt(15,p.getLa8());                                            		 
					ps2.setInt(16,p.getLa9());                                                       
					ps2.setFloat(17,p.getRl10());																						                                
					ps2.setFloat(18,p.getRl11());										
					ps2.setFloat(19,p.getRl12());																						 
					ps2.setFloat(20,p.getRl1());
					ps2.setFloat(21,p.getRl2());
					ps2.setFloat(22,p.getRl3());
					ps2.setFloat(23,p.getRl4());
					ps2.setFloat(24,p.getRl5());
					ps2.setFloat(25,p.getRl6());
					ps2.setFloat(26,p.getRl7());
					ps2.setFloat(27,p.getRl8());
					ps2.setFloat(28,p.getRl9());
					ps2.setInt(29,p.getTtemp());
					ps2.setInt(30,p.getMkt_year());

					i= i + ps2.executeUpdate();
				}
				rs.close();
    	}	////////////End of while loop
    	
		con.commit();
 		con.setAutoCommit(true);
 		ps.close();
 		ps1.close();
 		ps2.close();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("-------------Exception in SQLProdDAO.LastYearupdate " + ex);
			i=-1;
		}
		finally {
			try {
				   System.out.println("No. of Records Update/Insert : "+i);
		             if(rs != null){ rs.close();}
		             if(ps != null){ ps.close();}
		             if(ps1 != null){ps1.close();}
		             if(ps2 != null){ps2.close();}
		    //         if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProdDAO BUd.Connection.close "+e);
			  }
		}
			
			return i;
		   
	   }
	 
	 
	 
///// Update Target Only////////////////////////////
	 
	 public int updateBud1(String typ,List pr, Connection con)
	   {
	    int i=0;
	    ProdFormBean p=null;
	    PreparedStatement ps =null;
	    PreparedStatement ps1 = null; 
	    PreparedStatement ps2 = null;
	    PreparedStatement ps3 = null;	    
	    ResultSet rs =null;
	    String query3=null;
		try 
		{
		String tblnm=null;
		tblnm=(typ+"_target").toLowerCase();
        
      String query1 ="select depo_code from "+tblnm+" where MKT_YEAR=? and DEPO_CODE=? and TR_CD=? and " +
      " ttemp=? and budmnth=? and tnd=? ";
		ps1 = con.prepareStatement(query1);

		String query = "update "+tblnm+" set st_cd=?,ar_cd=?,rg_cd=?,mr_cd=?,grp_cd=?," +
	    "TMRP=?,tt10=?,tt11=?,tt12=?,tt1=?,tt2=?,tt3=?,tt4=?,tt5=?,tt6=?,tt7=?,tt8=?,tt9=?" +
	    ",ttarget=?,tvalue=? " +
	    " where MKT_YEAR=? and DEPO_CODE=? and TR_CD=? and ttemp=? and budmnth=? and  tnd=?";
		ps = con.prepareStatement(query);

		String query2 ="insert into "+tblnm+" (depo_code,pr_code,tr_cd,st_cd,ar_cd,rg_cd,mr_cd,grp_cd,TMRP," +
		" tt10,tt11,tt12,tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,ttarget,tvalue,budmnth,mkt_year,ttemp,tnd) "+
		" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps2 = con.prepareStatement(query2);
      
  	boolean first=false;
  	con.setAutoCommit(false);
    	Iterator it = pr.iterator();
      if (it.hasNext())
      {
		p = (ProdFormBean) it.next();
		query3 = "update " + tblnm + " set tt10=?,tt11=?,tt12=?,tt1=?,tt2=?,tt3=?,tt4=?,tt5=?," +
		" tt6=?,tt7=?,tt8=?,tt9=?,ttarget=?,tvalue=? " +
		" where DEPO_CODE=? and MKT_YEAR=?  ";
		ps3 = con.prepareStatement(query3);
	    ps3.setFloat(1,0);
	    ps3.setFloat(2,0);
	    ps3.setFloat(3,0);
	    ps3.setFloat(4,0);
	    ps3.setFloat(5,0);
	    ps3.setFloat(6,0);
	    ps3.setFloat(7,0);
	    ps3.setFloat(8,0);
	    ps3.setFloat(9,0);
	    ps3.setFloat(10,0);
	    ps3.setFloat(11,0);
	    ps3.setFloat(12,0);
		ps3.setInt(13,0);
		ps3.setFloat(14,0);
	    ps3.setInt(15,p.getDepo_code());
		ps3.setInt(16,p.getMkt_year());
		i= i + ps3.executeUpdate();
		ps3.close();
	    }
	    
		query3=null;		
 	    while(it.hasNext()) 
 	    {
   		 if (first)
    		 {
  			p = (ProdFormBean) it.next();
    		 }
    		    first=true; 
				ps1.setInt(1, p.getMkt_year());
			    ps1.setInt(2,p.getDepo_code());
				ps1.setInt(3, p.getTr_cd());
				ps1.setInt(4, p.getTtemp());				
				ps1.setInt(5, p.getBudmnth());
				ps1.setString(6,p.getTnd());
				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					System.out.println("Update Me a gaye");
					ps.setInt(1,p.getSt_cd());
					ps.setInt(2,p.getAr_cd());
					ps.setInt(3,p.getRg_cd());
					ps.setInt(4,p.getMr_cd());
					ps.setInt(5,p.getGrp_cd());
					ps.setFloat(6,p.getTmrp());
					ps.setFloat(7,p.getTt10());
					ps.setFloat(8,p.getTt11());
					ps.setFloat(9,p.getTt12());
					ps.setFloat(10,p.getTt1());
					ps.setFloat(11,p.getTt2());
					ps.setFloat(12,p.getTt3());
					ps.setFloat(13,p.getTt4());
					ps.setFloat(14,p.getTt5());
					ps.setFloat(15,p.getTt6());
					ps.setFloat(16,p.getTt7());
					ps.setFloat(17,p.getTt8());
					ps.setFloat(18,p.getTt9());
					ps.setInt(19,p.getTtarget());
					ps.setFloat(20,p.getTvalue());
					ps.setInt(21,p.getMkt_year());
					ps.setInt(22,p.getDepo_code());
					ps.setInt(23,p.getTr_cd());
					ps.setInt(24,p.getTtemp());	
					ps.setInt(25,p.getBudmnth());
					ps.setString(26,p.getTnd());
					i= i + ps.executeUpdate();

				}
				else 
				{   
					System.out.println("Insert Me a gaye");
					ps2.setInt(1,p.getDepo_code());
					ps2.setInt(2,p.getPr_code());
					ps2.setInt(3,p.getTr_cd());
					ps2.setInt(4,p.getSt_cd());
					ps2.setInt(5,p.getAr_cd());
					ps2.setInt(6,p.getRg_cd());
					ps2.setInt(7,p.getMr_cd());
					ps2.setInt(8,p.getGrp_cd());
					ps2.setFloat(9,p.getTmrp());
					ps2.setFloat(10,p.getTt10());
					ps2.setFloat(11,p.getTt11());
					ps2.setFloat(12,p.getTt12());
					ps2.setFloat(13,p.getTt1());
					ps2.setFloat(14,p.getTt2());
					ps2.setFloat(15,p.getTt3());
					ps2.setFloat(16,p.getTt4());
					ps2.setFloat(17,p.getTt5());
					ps2.setFloat(18,p.getTt6());
					ps2.setFloat(19,p.getTt7());
					ps2.setFloat(20,p.getTt8());
					ps2.setFloat(21,p.getTt9());
					ps2.setInt(22,p.getTtarget());
					ps2.setFloat(23,p.getTvalue());
					ps2.setInt(24,p.getBudmnth());
					ps2.setInt(25,p.getMkt_year());
					ps2.setInt(26,p.getTtemp());	
					ps2.setString(27,p.getTnd());
					i= i + ps2.executeUpdate();
				}
				rs.close();
  	}	////////////End of while loop
  	
		con.commit();
		con.setAutoCommit(true);
		ps.close();
		ps1.close();
		ps2.close();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("-------------Exception in SQLProdDAO.update " + ex);
			i=-1;
		}
		finally {
			try {
				   System.out.println("No. of Records Update/Insert : "+i);
		             if(rs != null){ rs.close();}
		             if(ps != null){ ps.close();}
		             if(ps1 != null){ps1.close();}
		             if(ps2 != null){ps2.close();}
		    //         if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProdDAO BUd.Connection.close "+e);
			  }
		}
			
			return i;
		   
	   }
	 
	 
	 	 

 ///// Update Last Year Sale ///////////////////////////////
 public int updateProd1(String typ,List pr, Connection con)
   {
    int i=0;
    ProdFormBean p=null;
    PreparedStatement ps =null;
    PreparedStatement ps1 = null; 
    PreparedStatement ps2 = null;
    PreparedStatement ps3=null;
    ResultSet rs =null;
	try 
	{
    String tblnm=null;
    tblnm=(typ+"_lysale").toLowerCase();
    String query3 =null;
      
    String query1 ="select depo_code from "+tblnm+" where MKT_YEAR=? and DEPO_CODE=? and TR_CD=? and ttemp=?";
	ps1 = con.prepareStatement(query1);

	String query = "update " + tblnm + " set st_cd=?,ar_cd=?,rg_cd=?,mr_cd=?,grp_cd=?," +
    "la10=?,la11=?,la12=?,la1=?,la2=?,la3=?,la4=?,la5=?,la6=?,la7=?,la8=?,la9=?," +
    "rl10=?,rl11=?,rl12=?,rl1=?,rl2=?,rl3=?,rl4=?,rl5=?,rl6=?,rl7=?,rl8=?,rl9=?, "+
    " where MKT_YEAR=? AND DEPO_CODE=? and TR_CD=? and ttemp=?";
    ps = con.prepareStatement(query);

	String query2 ="insert into " + tblnm + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	ps2 = con.prepareStatement(query2);
         
   	String mon=null;
   	int bud=0;
  	boolean first=false;
  	con.setAutoCommit(false);
    Iterator it = pr.iterator();
      if (it.hasNext())
      {
		p = (ProdFormBean) it.next();
	    mon=p.getMonth();
	    bud=p.getBudmnth();
	    if (bud==0)
	    {
	    query3 = "update " + tblnm + " set la"+mon+"=0, rl"+mon+"=0 where mkt_year=? and DEPO_CODE=? ";
		ps3 = con.prepareStatement(query3);
		ps3.setInt(1,p.getMkt_year());
		ps3.setInt(2,p.getDepo_code());
		i= i + ps3.executeUpdate();
		ps3.close();
	    }
	    
		query3=null;
		
	    query3 = "update " + tblnm + " set pr_code=?, st_cd=?,ar_cd=?,rg_cd=?, mr_cd=?, grp_cd=?, la"+mon+"=?, rl"+mon+"=? " +
		" where MKT_YEAR=? AND DEPO_CODE=? and TR_CD=? and ttemp=?";  
		ps3 = con.prepareStatement(query3);
	  }
      
    	while(it.hasNext()) {
    	 if (first)
    	 {
  			p = (ProdFormBean) it.next();
    	 }
    		    first=true;
    		    ps1.setInt(1, p.getMkt_year());    		    
  			    ps1.setInt(2,p.getDepo_code());
			    ps1.setInt(3, p.getTr_cd());
				ps1.setInt(4, p.getTtemp());
				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					if (p.getCount()>=2)
					{	
					System.out.println("Update Me a gaye");
					ps.setInt(1,p.getPr_code());
					ps.setInt(2,p.getSt_cd());
					ps.setInt(3,p.getAr_cd());
					ps.setInt(4,p.getRg_cd());
					ps.setInt(5,p.getMr_cd());
					ps.setInt(6,p.getGrp_cd());
					ps.setInt(7,p.getLa10());
					ps.setInt(8,p.getLa11());
					ps.setInt(9,p.getLa12());
					ps.setInt(10,p.getLa1());
					ps.setInt(11,p.getLa2());
					ps.setInt(12,p.getLa3());
					ps.setInt(13,p.getLa4());
					ps.setInt(14,p.getLa5());
					ps.setInt(15,p.getLa6());
					ps.setInt(16,p.getLa7());
					ps.setInt(17,p.getLa8());
					ps.setInt(18,p.getLa9());
					ps.setFloat(19,p.getRl10()); 
                    ps.setFloat(20,p.getRl11());
                    ps.setFloat(21,p.getRl12());					
					ps.setFloat(22,p.getRl1());
                    ps.setFloat(23,p.getRl2());
                    ps.setFloat(24,p.getRl3());
                    ps.setFloat(25,p.getRl4());
                    ps.setFloat(26,p.getRl5());
                    ps.setFloat(27,p.getRl6());
                    ps.setFloat(28,p.getRl7());
                    ps.setFloat(29,p.getRl8());
                    ps.setFloat(30,p.getRl9());
                    ps.setInt(31,p.getMkt_year());
                    ps.setInt(32,p.getDepo_code());
                    ps.setInt(33,p.getTr_cd());
                    ps.setInt(34,p.getTtemp());
					i= i + ps.executeUpdate();
					}
					else
					{
						System.out.println("Monthly Record Updated =====>Target Master<====== depo= "+p.getDepo_code());
						int la=0;
						float rl=0.0f;
						if (mon.equals("10"))
						{
						   la=p.getLa10();
						   rl=p.getRl10();
						}

						if (mon.equals("11"))
						{
						   la=p.getLa11();
						   rl=p.getRl11();
						}
						if (mon.equals("12"))
						{
						   la=p.getLa12();
						   rl=p.getRl12();
						}
						if (mon.equals("1"))
						{
						   la=p.getLa1();
						   rl=p.getRl1();
						}
						if (mon.equals("2"))
						{
						   la=p.getLa2();
						   rl=p.getRl2();
						}
						if (mon.equals("3"))
						{
						   la=p.getLa3();
						   rl=p.getRl3();
						}
						if (mon.equals("4"))
						{
						   la=p.getLa4();
						   rl=p.getRl4();
						}
						if (mon.equals("5"))
						{
						   la=p.getLa5();
						   rl=p.getRl5();
						}
						if (mon.equals("6"))
						{
						   la=p.getLa6();
						   rl=p.getRl6();
						}
						if (mon.equals("7"))
						{
						   la=p.getLa7();
						   rl=p.getRl7();
						}
						if (mon.equals("8"))
						{
						   la=p.getLa8();
						   rl=p.getRl8();
						}
						if (mon.equals("9"))
						{
						   la=p.getLa9();
						   rl=p.getRl9();
						}
						ps3.setInt(1,p.getPr_code());
						ps3.setInt(2,p.getSt_cd());
						ps3.setInt(3,p.getAr_cd());
						ps3.setInt(4,p.getRg_cd());
						ps3.setInt(5,p.getMr_cd());
						ps3.setInt(6,p.getGrp_cd());
						ps3.setInt(7,la);
						ps3.setFloat(8,rl);
						ps3.setInt(9,p.getMkt_year());						
						ps3.setInt(10,p.getDepo_code());
						ps3.setInt(11,p.getTr_cd());
						ps3.setInt(12,p.getTtemp());
						i= i + ps3.executeUpdate();
					}
				}
				else 
				{   
					System.out.println("Insert Me a gaye");
					ps2.setInt(1,p.getDepo_code());
					ps2.setInt(2,p.getPr_code());
					ps2.setInt(3,p.getTr_cd());
					ps2.setInt(4,p.getSt_cd());
					ps2.setInt(5,p.getAr_cd());
					ps2.setInt(6,p.getRg_cd());
					ps2.setInt(7,p.getMr_cd());
					ps2.setInt(8,p.getGrp_cd());
					ps2.setInt(9,p.getLa10());
					ps2.setInt(10,p.getLa11());
					ps2.setInt(11,p.getLa12());
					ps2.setInt(12,p.getLa1());
					ps2.setInt(13,p.getLa2());
					ps2.setInt(14,p.getLa3());
					ps2.setInt(15,p.getLa4());
					ps2.setInt(16,p.getLa5());
					ps2.setInt(17,p.getLa6());
					ps2.setInt(18,p.getLa7());
					ps2.setInt(19,p.getLa8());
					ps2.setInt(20,p.getLa9());
					ps2.setFloat(21,p.getRl10()); 
                    ps2.setFloat(22,p.getRl11());
                    ps2.setFloat(23,p.getRl12());					
					ps2.setFloat(24,p.getRl1());
                    ps2.setFloat(25,p.getRl2());
                    ps2.setFloat(26,p.getRl3());
                    ps2.setFloat(27,p.getRl4());
                    ps2.setFloat(28,p.getRl5());
                    ps2.setFloat(29,p.getRl6());
                    ps2.setFloat(30,p.getRl7());
                    ps2.setFloat(31,p.getRl8());
                    ps2.setFloat(32,p.getRl9());
                    ps2.setInt(33,p.getTtemp());
                    ps2.setInt(34,p.getMkt_year());
                    i= i + ps2.executeUpdate();
				}
				rs.close();
    	}	////////////End of while loop
    	
		con.commit();
 		con.setAutoCommit(true);
 		ps.close();
 		ps1.close();
 		ps2.close();
 		ps3.close();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("-------------Exception in SQLProdDAO.update " + ex);
			i=-1;
		}
		finally {
			try {
				   System.out.println("No. of Records Update/Insert : "+i);
		             if(rs != null){ rs.close();}
		             if(ps != null){ ps.close();}
		             if(ps1 != null){ps1.close();}
		             if(ps2 != null){ps2.close();}
		             if(ps3 != null){ps3.close();}		             
		    //         if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProdDAO.Connection.close "+e);
			  }
		}
			
			return i;
		   
	   }
	 
	 
}

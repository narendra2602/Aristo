package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.aristo.valueobject.TerFormBean;

public class SQLTerDAO implements TerDAO {
	
	public List getAllter(Connection con, int depo, String typ,int eyear) { 
		 
		TerFormBean tfb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		List<TerFormBean> data = new ArrayList<TerFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
        	tblnm=(typ+"_hq_master08").toLowerCase();
        	tblnm1=(typ+"_region_master08").toLowerCase();
        	tblnm2=(typ+"_area_master08").toLowerCase();

			String query = "select t.ter_code,t.ter_name, a.area_name,r.name,t.oct,t.nov,t.decm,t.jan, "+
	        "t.feb,t.mar,t.apr,t.may,t.jun,t.jul,t.aug,t.sep from " + tblnm+ " as t, "+
	        tblnm2+" as a, "+tblnm1+" as r where t.area_code=a.area_cd and " +
	        " t.regn_code=r.reg_code and t.depo_code=a.depo_code "+
	        " and t.depo_code=r.depo_code and t.depo_code=? and t.mkt_year=? and a.mkt_year=? and r.mkt_year=?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, depo);
			ps.setInt(2, eyear);
			ps.setInt(3, eyear);
			ps.setInt(4, eyear);
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				tfb = new TerFormBean();
				tfb.setTer_code(rst.getInt(1));
				tfb.setTer_name(rst.getString(2));
				tfb.setTxt(rst.getString(3));
				tfb.setSlct(rst.getString(4));
				tfb.setOct(rst.getInt(5));
				tfb.setNov(rst.getInt(6));
				tfb.setDec(rst.getInt(7));
				tfb.setJan(rst.getInt(8));
				tfb.setFeb(rst.getInt(9));
				tfb.setMar(rst.getInt(10));
				tfb.setApr(rst.getInt(11));
				tfb.setMay(rst.getInt(12));
				tfb.setJun(rst.getInt(13));
				tfb.setJuly(rst.getInt(14));
				tfb.setAug(rst.getInt(15));
				tfb.setSep(rst.getInt(16));
				data.add(tfb);
			}
			rst.close();
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLTerDAO.getAllTer " + e);
		}
		finally {
			try {
	             if(rst != null){ rst.close();}
	             if(ps != null){ps.close();}		             
	             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	 public int updateTer(String typ,List ter,Connection con)
	   {
	    int i=0;
	    int depo=0;
	    int year=0;
	    TerFormBean t;
	   
	    PreparedStatement ps = null;
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    ResultSet rs = null;
	    try {
			String tblnm=null;
			String tblnm1=null;
			tblnm=typ+"_hq_master08";
            tblnm1=typ+"_branch08";
	            
        String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and TER_CODE=? and mkt_year=?";
		ps1 = con.prepareStatement(query1);
		
		String query ="update "+tblnm+" set state_code=?,area_code=?,regn_code=?,ter_name=?,no_of_rep=?," +
	    "rep_cd1=?,rep_cd2=?,rep_cd3=?,rep_cd4=?,rep_cd5=?,rep_cd6=?,txt=?,ter_pt=?," +
	    "slct=?,oct=?,nov=?,decm=?,jan=?,feb=?,mar=?,apr=?,may=?,jun=?," +
	    "jul=?,aug=?,sep=? where depo_code=? and ter_code=? and mkt_year=? ";  
        ps = con.prepareStatement(query);  

		String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps2 = con.prepareStatement(query2);
		con.setAutoCommit(false);
		int rep=0;
        Iterator it = ter.iterator();
       	while(it.hasNext()) 
           	 {
       			t = (TerFormBean) it.next();        
				ps1.setInt(1,t.getDepo_code());
				ps1.setInt(2,t.getTer_code());
				ps1.setInt(3,t.getMkt_year());
				rs = ps1.executeQuery(); 
				depo=t.getDepo_code();
				year=t.getMkt_year();
				if (rs.next())
				{ 
					ps.setInt(1, t.getState_code());
					ps.setInt(2, t.getArea_code()); 
					ps.setInt(3,t.getRegn_code());
					ps.setString(4, t.getTer_name());
					ps.setInt(5, t.getNo_of_rep());
					ps.setInt(6, t.getRep_cd1());
					ps.setInt(7, t.getRep_cd2());
					ps.setInt(8, t.getRep_cd3());
					ps.setInt(9, t.getRep_cd4()); 
					ps.setInt(10, t.getRep_cd5());
					ps.setInt(11, t.getRep_cd6());
					ps.setString(12,t.getTxt()); 
					ps.setString(13, t.getTer_pt());
					ps.setString(14, t.getSlct());
					ps.setInt(15, t.getOct());  
					ps.setInt(16, t.getNov());  
					ps.setInt(17,t.getDec());  
					ps.setInt(18,t.getJan());
					ps.setInt(19,t.getFeb());
					ps.setInt(20,t.getMar());
					ps.setInt(21,t.getApr());
					ps.setInt(22,t.getMay());
					ps.setInt(23,t.getJun());
					ps.setInt(24,t.getJuly());
					ps.setInt(25,t.getAug());
					ps.setInt(26,t.getSep());
					ps.setInt(27, t.getDepo_code());   
					ps.setInt(28, t.getTer_code());
					ps.setInt(29, t.getMkt_year());
					i= i + ps.executeUpdate();
				}
				else
				{
					ps2.setInt(1,t.getDepo_code());
					ps2.setInt(2, t.getTer_code());
					ps2.setInt(3,t.getState_code());
					ps2.setInt(4,t.getArea_code());
					ps2.setInt(5, t.getRegn_code());
					ps2.setString(6, t.getTer_name());
					ps2.setInt(7, t.getNo_of_rep());
					ps2.setInt(8, t.getRep_cd1());
					ps2.setInt(9, t.getRep_cd2());
					ps2.setInt(10, t.getRep_cd3());
					ps2.setInt(11, t.getRep_cd4());
					ps2.setInt(12, t.getRep_cd5());
					ps2.setInt(13, t.getRep_cd6());
					ps2.setString(14,t.getTxt());
					ps2.setString(15, t.getTer_pt());
					ps2.setString(16, t.getSlct());
					ps2.setInt(17, t.getOct());
					ps2.setInt(18, t.getNov());
					ps2.setInt(19,t.getDec());
					ps2.setInt(20,t.getJan());
					ps2.setInt(21,t.getFeb());
					ps2.setInt(22,t.getMar());
					ps2.setInt(23,t.getApr());
					ps2.setInt(24,t.getMay());
					ps2.setInt(25,t.getJun());
					ps2.setInt(26,t.getJuly());
					ps2.setInt(27,t.getAug());
					ps2.setInt(28,t.getSep());
					ps2.setInt(29,t.getMkt_year());
					i= i + ps2.executeUpdate();
				}
				rs.close(); 

       	 }     ///////////////////End of Loop While/////////////////////////
       	
       			ps.close();
       			ps1.close();
       			ps2.close();
       	

		 String query3 =" select sum(no_of_rep) from "+tblnm+" where depo_Code=? and mkt_year=?";
         PreparedStatement ps3 = con.prepareStatement(query3);  
  		 ps3.setInt(1,depo);
  		 ps3.setInt(2,year);
  		 ResultSet rst3 = ps3.executeQuery();
  		 if (rst3.next())
  			rep=rst3.getInt(1);
  		
	      rst3.close();
	      ps3.close();
	      		  
       			
	   	  query3 ="update "+tblnm1+" set no_of_rep=? where depo_code=? and mkt_year=? ";

          ps3 = con.prepareStatement(query3);  
		  ps3.setInt(1,rep);
		  ps3.setInt(2,depo);
		  ps3.setInt(3,year);
		  ps3.executeUpdate();
		  
		  con.commit();
   		  con.setAutoCommit(true);

		  ps.close();
		  ps3.close();
			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLTerDAO.update " + ex);
				i=-1;
			}
			finally {
				try {
					     System.out.println("No. of Records Update/Insert : "+i);
					     if(rs != null){ rs.close();}
					     if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
		   
	   }
//////////////////////////////////////////////////////////////////////////////////////////////
		public List getAllDgm(Connection con, String typ) { 
			 
			TerFormBean tfb;
			PreparedStatement ps=null;
			ResultSet rst =null;			
			List<TerFormBean> data = new ArrayList<TerFormBean>();
			try { 
	            String tblnm=null;
	            if (typ.equals("A"))
	            {	
	            	tblnm="a_dgm_master08";
	            }	
	            if (typ.equals("T"))
	            {	
	            	tblnm="t_dgm_master08";
	            }	
	            if (typ.equals("G"))
	            {	
	            	tblnm="g_dgm_master08";
	            }	
	            
	            String query = "select * from "+ tblnm ;
				ps = con.prepareStatement(query);
				rst = ps.executeQuery();
				 
				while (rst.next())
				{
					tfb = new TerFormBean();
					tfb.setLink1("Edit");
					tfb.setDgm_code(rst.getInt(1));
					tfb.setDgm_name(rst.getString(2));
					tfb.setBranches(rst.getString(3));
						
					data.add(tfb);
				}
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAllDGM " + e);
			}
			finally {
				try 
				{
			     if(rst != null){ rst.close();}
			     if(ps != null){ ps.close();}
	             if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return data;
		}
///////////////////////////////////////////////////////////////////////////////////////////////////		
	 
//////////////////////////////////////////////////////////////////////////////////////////////
		public int AddDgm (TerFormBean tfb, Connection con, String typ) { 
			 
 			int i=0;
			PreparedStatement ps=null;
			try { 
	            String tblnm=null;
	            if (typ.equals("A"))
	            {	
	            	tblnm="a_dgm_master08";
	            }	
	            if (typ.equals("T"))
	            {	
	            	tblnm="t_dgm_master08";
	            }	
	            if (typ.equals("G"))
	            {	
	            	tblnm="g_dgm_master08";
	            }	
	            
	            String query = "insert into "+ tblnm+" values(?,?,?)";
				ps = con.prepareStatement(query);
				 
                    ps.setInt(1, tfb.getDgm_code());
                    ps.setString(2, tfb.getDgm_name());
                    ps.setString(3, tfb.getBranches());
                    
					i=ps.executeUpdate();	
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAllDGM " + e);
				i=-1;
			}
			finally {
				try {
				     if(ps != null){ ps.close();}
		             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return i;
		}
///////////////////////////////////////////////////////////////////////////////////////////////////				
	
		public TerFormBean EditDgm(TerFormBean tfb, Connection con, String typ) { 
			 
 			int i=0;
 			TerFormBean fb=null;
			PreparedStatement ps=null;
			ResultSet rs =null;			
 			
			try { 
	            String tblnm=null;
	            if (typ.equals("A"))
	            {	
	            	tblnm="a_dgm_master08";
	            }	
	            if (typ.equals("T"))
	            {	
	            	tblnm="t_dgm_master08";
	            }	
	            if (typ.equals("G"))
	            {	
	            	tblnm="g_dgm_master08";
	            }	

	            String query = "select * from "+ tblnm+" where dgm_code=? ";
				ps = con.prepareStatement(query);
				ps.setInt(1, tfb.getDgm_code());
				rs = ps.executeQuery();

				if (rs.next())
				{
	              fb = new TerFormBean();
	              fb.setDgm_name(rs.getString(2));
	              fb.setBranches(rs.getString(3));
				}
				rs.close();
				ps.close();
                  
				System.out.println(i);
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.EditDGM " + e);
				i=-1;
			}
			finally {
				try 
				{
				 if(rs != null){rs.close();}
			     if(ps != null){ps.close();}
	             if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return fb;
		}		
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////
		public List getAllZm(Connection con, String typ) { 
			 
			TerFormBean tfb;
			PreparedStatement ps=null;
			ResultSet rst =null;			
			
			List<TerFormBean> data = new ArrayList<TerFormBean>();
			try { 
	            String tblnm=null;
	            if (typ.equals("A"))
	            {	
	            	tblnm="a_zm_master08";
	            }	
	            if (typ.equals("T"))
	            {	
	            	tblnm="t_zm_master08";
	            }	
	            if (typ.equals("G"))
	            {	
	            	tblnm="g_zm_master08";
	            }	
	            
	            String query = "select * from "+ tblnm ;
				ps = con.prepareStatement(query);
				rst = ps.executeQuery();
				 
				while (rst.next())
				{
					tfb = new TerFormBean();
					tfb.setZm_code(rst.getInt(1));
					tfb.setZm_name(rst.getString(2));
					tfb.setDgm_code(rst.getInt(3));
					tfb.setDgm_name(rst.getString(4));
					tfb.setBranches(rst.getString(5));
					tfb.setLink2("Edit");
						
					data.add(tfb);
				}
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAllZM " + e);
			}
			finally {
				try 
				{
				 if(rst != null){rst.close();}
			     if(ps != null){ps.close();}
	             if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return data;
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
//////////////////////////////////////////////////////////////////////////////////////////////
		public int AddZm (TerFormBean tfb, Connection con, String typ) { 
			 
 			int i=0;
			PreparedStatement ps=null;
			try { 
	            String tblnm=null;
	            if (typ.equals("A"))
	            {	
	            	tblnm="a_zm_master08";
	            }	
	            if (typ.equals("T"))
	            {	
	            	tblnm="t_zm_master08";
	            }	
	            if (typ.equals("G"))
	            {	
	            	tblnm="g_zm_master08";
	            }	
	            
	            String query = "insert into "+ tblnm+" values(?,?,?,?,?)";
				ps = con.prepareStatement(query);
				 
                    ps.setInt(1, tfb.getZm_code());
                    ps.setString(2, tfb.getZm_name());
                    ps.setInt(3, tfb.getDgm_code());
                    ps.setString(4, tfb.getDgm_name());
                    ps.setString(5, tfb.getBranches());
                    
					i=ps.executeUpdate();	
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAddZm " + e);
				i=-1;
			}
			finally {
				try 
				{
				     if(ps != null){ps.close();}
		             if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return i;
		}
		
		
		public List getAllBranch(Connection con, String typ) { 
			 
			TerFormBean tfb;
			PreparedStatement ps =null;
			ResultSet rst =null;
			List<TerFormBean> data = new ArrayList<TerFormBean>();
			try { 
	            String tblnm=null;
	            String query=null;	            
	           	tblnm=typ+"_branch08";
	           	query="select depo_code,ter_name,no_of_rep from " +tblnm+" order by ter_name ";
				ps = con.prepareStatement(query);
				rst = ps.executeQuery();
				while (rst.next())
				{
					tfb = new TerFormBean();
					tfb.setDepo_code(rst.getInt(1));
					tfb.setTer_name(rst.getString(2));
					tfb.setNo_of_rep(rst.getInt(3));
					tfb.setLink2("Edit");
					data.add(tfb);
				}
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAllbranch " + e);
			}
			finally {
				try 
				{
					if(ps != null){ps.close();} 
		            if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}
			return data;
		}
		

		public int AddBranch (TerFormBean tfb, Connection con, String typ) { 
			 
 			int i=0;
			PreparedStatement ps =null;
			try { 
	            String tblnm=null;
            	tblnm=typ+"_branch08";
	            String query = "insert into "+ tblnm+" (typ,depo_code,ter_name,no_of_rep) values(?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1,typ);
				ps.setInt(2,tfb.getDepo_code());
                ps.setString(3,tfb.getTer_name());
                ps.setInt(4,tfb.getNo_of_rep());
    			i=ps.executeUpdate();	
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLTerDAO.getAddBranch " + e);
				i=-1;
			}
			finally {
				try 
				{
					if(ps != null){ps.close();} 
		            if(con != null){con.close();}
		        }
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
			    }
			}
			return i;
		}
		
		
		public TerFormBean EditBranch(Connection con,int depo,String typ)
		{
			TerFormBean lf = null;
			PreparedStatement ps =null;
			ResultSet rs =null;		
			try {
				String tblnm=null;
				tblnm=typ+"_branch08";
				ps = con.prepareStatement("Select depo_code,ter_name,no_of_rep from "+tblnm+" where depo_code=? ");
				ps.setInt(1,depo);
				rs = ps.executeQuery();   
				if (rs.next())
				{
	                 lf = new TerFormBean();
	            	 lf.setDepo_code(rs.getInt(1));
	            	 lf.setTer_name(rs.getString(2));
	            	 lf.setNo_of_rep(rs.getInt(3));
				}
				rs.close();
				ps.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally {
				try 
				{
			           if(ps != null){ps.close();}
			           if(rs != null){ rs.close();}
			           if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
				  }
			}	
			  
			return lf ; 
			
		}
		
		public int UpdateBranch(Connection con,TerFormBean tf, String typ)
		{
			PreparedStatement ps =null;
			int i=0;
			try {
				String tblnm=null;
				tblnm=typ+"_branch08";
				ps = con.prepareStatement("update "+tblnm+" set ter_name=?,no_of_rep=? where depo_code=? ");
				ps.setString(1,tf.getTer_name());
				ps.setInt(2,tf.getNo_of_rep());
				ps.setInt(3,tf.getDepo_code());				
				i=ps.executeUpdate();
				ps.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try 
				{
			           if(ps != null){ps.close();}
			           if(con != null){con.close();}
				}
				catch (SQLException e) {
					System.out.println("-------------Exception in SQLterDAO.Connection.close "+e);
				  }
			}	
			  
			return i ; 
			
		}
		
		
		
}

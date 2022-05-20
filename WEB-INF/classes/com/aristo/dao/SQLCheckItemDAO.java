package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.CheckItemFormBean;

public class SQLCheckItemDAO {
	
	public List getWrongItem(Connection con,String tp) { 
		 
		CheckItemFormBean rfb;
		PreparedStatement ps1 =null;
		ResultSet rst1=null;

		PreparedStatement ps2 =null;
		ResultSet rst2 = null;

		PreparedStatement ps3 =null;
		ResultSet rst3 = null;

		List<CheckItemFormBean> data = new ArrayList<CheckItemFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            
            if (tp.equals("A"))
            {	
            	tblnm="a_hqdetail08";
            	tblnm1="a_branch08";
            	tblnm2="a_product08";
            }	

            if (tp.equals("T"))
            {
            	tblnm="t_hqdetail08";
            	tblnm1="t_branch08";
            	tblnm2="t_product08";
            }
            
            if (tp.equals("G"))
            {	
            	tblnm="g_hqdetail08";
            	tblnm1="g_branch08";
            	tblnm2="g_product08";
            }   
            
//////////////////////////////Product Master ki Query/////////////////////////////////
            String query1 = "Select pcode,mcode from "+tblnm2+ " where mcode=? order by pcode";
            ps1 = con.prepareStatement(query1);
                
//////////////////////////////HQ Detail ki Query/////////////////////////////////
          	String query3 = "Select Distinct(pr_code),mpr_code,depo_code from "+tblnm+ 
    	     " where depo_code=? group by pr_code order by pr_code";
   		    ps3 = con.prepareStatement(query3); 
       		    
//////////////////////////////Branch Master ki Query/////////////////////////////////
            String query2 = "Select depo_code,ter_name from "+tblnm1+" where typ=? order by ter_name";
	        ps2 = con.prepareStatement(query2);
	        ps2.setString(1,tp); 
	        rst2 = ps2.executeQuery();
	        
	        while (rst2.next()) ///// Branch Master Loop Start
	        {
            
/////////////////////////////////////////HQ Detail Query//////////////////////////////////////////////////
 			        ps3.setInt(1, rst2.getInt(1));
			        rst3 = ps3.executeQuery();
			        
			        rst3.beforeFirst();
			        while (rst3.next())  //////// HQ Detail Loop Starts /////////////////
			        {
			        	
//			             ps1.setInt(1, rst3.getInt(1));
			             ps1.setInt(1, rst3.getInt(2));
			             
			             rst1 = ps1.executeQuery();
			             if (!rst1.next())  ///// Product Master Start /////////////
			             {
			 			   rfb = new CheckItemFormBean();
			 			   rfb.setBranch_name(rst2.getString(2));
			               rfb.setPcode(rst3.getInt(1));	 
			               rfb.setMcode(rst3.getInt(2));
			               data.add(rfb);
			            	 
			             } ////////////// Product Master End ////////////////////////
			        } /////////////// End of HQ Detail loop///////////////////
	        } ////////////////End of Branch Master Loop//////////////////

				        rst1.close();
				        ps1.close();
				        rst3.close();
				        ps3.close();
				        rst2.close();
				        ps2.close();
				        
				        rst1=null;
				        ps1=null;
				        rst2=null;
				        ps2=null;
				        rst3=null;
				        ps3=null;
				        
	        
		} catch (Exception e)
		
		    {
			System.out.println("========Exception in SQLCheckItemDAO.getWrongItem " + e); 
		    }
		
		finally 
				{
			        try 
						{
	   		             if(rst1 != null){ rst1.close();}
			             if(ps1 != null){ ps1.close();}
	   		             if(rst2 != null){ rst2.close();}
			             if(ps2 != null){ ps2.close();}
	   		             if(rst3 != null){ rst3.close();}
			             if(ps3 != null){ ps3.close();}
    		             if(con != null){con.close();}
					    } 
			        catch (SQLException e) 
					    {
						System.out.println("-------------Exception in SQLCheckItemDAO.Connection.close "+e);
					    }
		        }		
		return data;
	}


	

}

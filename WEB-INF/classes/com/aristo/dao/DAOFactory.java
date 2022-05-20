package com.aristo.dao;

public class DAOFactory {
	public static AreaDAO getArea(){
    
		return new SQLAreaDAO();
  }
	public static TerDAO getTer(){
	    
		return new SQLTerDAO();
  }
	public static Repo1DAO getRepo1(){
	    
		return new SQLRepo1DAO();
  }
	public static LoginDAO getLoginDAO(){
	    
		return new SQLLoginDAO();
  }
	
	public static GroupMktDAO getGroupMktDAO(){
	    
		return new SQLGroupMktDAO();
  }	
	
	public static GroupSalesDAO getGroupSalesDAO(){
	    
		return new SQLGroupSalesDAO();
  }	
	public static ProductDAO getProductDAO(){
	    
		return new SQLProductDAO();
  }		
	
   public static RegionDAO getRegionDAO(){
	    
		return new SQLRegionDAO();
  }		
   
   public static AccountDAO getAccountDAO(){
	    
		return new SQLAccountDAO();
 }   
   
   
}
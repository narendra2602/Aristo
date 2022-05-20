package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

 

public interface StockiestDAO {
	 public int updateStockiest(String typ,List area,Connection con); 

//	public int updateStockiest(StockiestFormBean s,Connection con);
}

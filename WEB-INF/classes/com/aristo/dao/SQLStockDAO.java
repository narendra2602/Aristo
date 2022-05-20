package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.StockFormBean;

public class SQLStockDAO {

	
 public int updateStock(String typ,List stock,Connection con) 
	  {
	  int i=0;
	  StockFormBean s=null;
	   
	  PreparedStatement ps3=null;
	  PreparedStatement ps2=null;
	  PreparedStatement ps1=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  try {
	      String tblnm=null;
	      tblnm=(typ+"_stock08").toLowerCase(); 
          String query3 =null;
          
      String query1 ="select depo_code from "+tblnm+" where depo_code=? and item_code=? and doc_type=? and mkt_year=?";
	  ps1 = con.prepareStatement(query1);
	  
      String query = "update " + tblnm + " set zm_code=?,dgm_code=?,item_type=?,grp=?,mgrp=?,dum_code=?," +
      "opngoct=?,opngnov=?,opngdec=?,opngjan=?,opngfeb=?,opngmar=?,opngapr=?,opngmay=?,opngjun=?,opngjul=?,opngaug=?,opngsep=?,"+
      "closoct=?,closnov=?,closdec=?,closjan=?,closfeb=?,closmar=?,closapr=?,closmay=?,closjun=?,closjul=?,closaug=?,clossep=?,"+	
      "qtyoct=?,qtynov=?,qtydec=?,qtyjan=?,qtyfeb=?,qtymar=?,qtyapr=?,qtymay=?,qtyjun=?,qtyjul=?,qtyaug=?,qtysep=?," +
      "freeoct=?,freenov=?,freedec=?,freejan=?,freefeb=?,freemar=?,freeapr=?,freemay=?,freejun=?,freejul=?,freeaug=?,freesep=?,"+							
      "valoct=?,valnov=?,valdec=?,valjan=?,valfeb=?,valmar=?,valapr=?,valmay=?,valjun=?,valjul=?,valaug=?,valsep=?, " +
      "fvaloct=?,fvalnov=?,fvaldec=?,fvaljan=?,fvalfeb=?,fvalmar=?,fvalapr=?,fvalmay=?,fvaljun=?,fvaljul=?,fvalaug=?,fvalsep=? "+ 
      " where depo_code=? and item_code=? and doc_type=? and mkt_year=? ";
	  ps = con.prepareStatement(query);
      
  	  String query2 ="insert into " + tblnm + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
	  "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
	  "?,?,?,?,?,?,?)";
  	  ps2 = con.prepareStatement(query2);
	  
  	  
	  String mon=null;
	  boolean first=false;

	  con.setAutoCommit(false);
	  Iterator it = stock.iterator();
      if (it.hasNext())
      {
	    s=(StockFormBean) it.next();
	    mon=s.getMonth();
		query3 = "update " + tblnm + " set zm_code=?,dgm_code=?,item_type=?,grp=?,mgrp=?,dum_code=?," + 
		"opng"+mon+"=?," + 
		"clos"+mon+"=?," +
		"qty"+mon+"=?," +
		"free"+mon+"=?," +
		"val"+mon+"=?," +
		"fval"+mon+"=? " +
		" where depo_code=? and item_code=? and doc_type=? and mkt_year=? ";  
		ps3 = con.prepareStatement(query3);
      }
	  while(it.hasNext()) {
		     if (first)
		     {
   			    s = (StockFormBean) it.next();
		     }
		        first=true;
				ps1.setInt(1,s.getDepo_code());
				ps1.setInt(2, s.getItem_code());
				ps1.setInt(3, s.getDoc_type());
				ps1.setInt(4, s.getMkt_year());
				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					if (s.getCount()>=12)
					{
					System.out.println("Record Updated =====>Stock File<======= depo= "+s.getDepo_code());
					ps.setInt(1,0);
					ps.setInt(2,0);
					ps.setString(3,s.getItem_type());
					ps.setInt(4,s.getGrp());
					ps.setInt(5,s.getMgrp());
					ps.setInt(6,s.getDum_code());
					
					ps.setInt(7,s.getOpngoct());
					ps.setInt(8,s.getOpngnov());
					ps.setInt(9,s.getOpngdec());
					ps.setInt(10,s.getOpngjan());
					ps.setInt(11,s.getOpngfeb());
					ps.setInt(12,s.getOpngmar());
					ps.setInt(13,s.getOpngapr());
					ps.setInt(14,s.getOpngmay());
					ps.setInt(15,s.getOpngjun());
					ps.setInt(16,s.getOpngjul());
					ps.setInt(17,s.getOpngaug());
					ps.setInt(18,s.getOpngsep());
					
					ps.setInt(19,s.getClosoct());
					ps.setInt(20,s.getClosnov());
					ps.setInt(21,s.getClosdec());
					ps.setInt(22,s.getClosjan());
					ps.setInt(23,s.getClosfeb());
					ps.setInt(24,s.getClosmar());
					ps.setInt(25,s.getClosapr());
					ps.setInt(26,s.getClosmay());
					ps.setInt(27,s.getClosjun());
					ps.setInt(28,s.getClosjul());
					ps.setInt(29,s.getClosaug());
					ps.setInt(30,s.getClossep());

					ps.setInt(31,s.getQtyoct());
					ps.setInt(32,s.getQtynov());
					ps.setInt(33,s.getQtydec());
					ps.setInt(34,s.getQtyjan());
					ps.setInt(35,s.getQtyfeb());
					ps.setInt(36,s.getQtymar());
					ps.setInt(37,s.getQtyapr());
					ps.setInt(38,s.getQtymay());
					ps.setInt(39,s.getQtyjun());
					ps.setInt(40,s.getQtyjul());
					ps.setInt(41,s.getQtyaug());
					ps.setInt(42,s.getQtysep());

					ps.setInt(43,s.getFreeoct());
					ps.setInt(44,s.getFreenov());
					ps.setInt(45,s.getFreedec());
					ps.setInt(46,s.getFreejan());
					ps.setInt(47,s.getFreefeb());
					ps.setInt(48,s.getFreemar());
					ps.setInt(49,s.getFreeapr());
					ps.setInt(50,s.getFreemay());
					ps.setInt(51,s.getFreejun());
					ps.setInt(52,s.getFreejul());
					ps.setInt(53,s.getFreeaug());
					ps.setInt(54,s.getFreesep());
					
					ps.setFloat(55,s.getValoct());
					ps.setFloat(56,s.getValnov());
					ps.setFloat(57,s.getValdec());
					ps.setFloat(58,s.getValjan());
					ps.setFloat(59,s.getValfeb());
					ps.setFloat(60,s.getValmar());
					ps.setFloat(61,s.getValapr());
					ps.setFloat(62,s.getValmay());
					ps.setFloat(63,s.getValjun());
					ps.setFloat(64,s.getValjul());
					ps.setFloat(65,s.getValaug());
					ps.setFloat(66,s.getValsep());
					
					ps.setFloat(67,s.getFvaloct());
					ps.setFloat(68,s.getFvalnov());
					ps.setFloat(69,s.getFvaldec());
					ps.setFloat(70,s.getFvaljan());
					ps.setFloat(71,s.getFvalfeb());
					ps.setFloat(72,s.getFvalmar());
					ps.setFloat(73,s.getFvalapr());
					ps.setFloat(74,s.getFvalmay());
					ps.setFloat(75,s.getFvaljun());
					ps.setFloat(76,s.getFvaljul());
					ps.setFloat(77,s.getFvalaug());
					ps.setFloat(78,s.getFvalsep());
					
					ps.setInt(79,s.getDepo_code());
					ps.setInt(80,s.getItem_code());
					ps.setInt(81,s.getDoc_type());
					ps.setInt(82,s.getMkt_year());
					i= i+ps.executeUpdate();
					}else
					{
						System.out.println("Monthly Record Updated =====>Stock File<======= depo= "+s.getDepo_code());
						
						int opng=0, clos=0, qty=0, free=0;
						float val=0.0f,fval=0.0f;
						
						if (mon.equals("oct"))
						{
						   opng=s.getOpngoct();
						   clos=s.getClosoct();
						   qty=s.getQtyoct();
						   free=s.getFreeoct();
						   val=s.getValoct();
						   fval=s.getFvaloct();
						}
						if (mon.equals("nov"))
						{
						   opng=s.getOpngnov();
						   clos=s.getClosnov();
						   qty=s.getQtynov();
						   free=s.getFreenov();
						   val=s.getValnov();
						   fval=s.getFvalnov();
						}
						if (mon.equals("dec"))
						{
						   opng=s.getOpngdec();
						   clos=s.getClosdec();
						   qty=s.getQtydec();
						   free=s.getFreedec();
						   val=s.getValdec();
						   fval=s.getFvaldec();
						}
						
						if (mon.equals("jan"))
						{
						   opng=s.getOpngjan();
						   clos=s.getClosjan();
						   qty=s.getQtyjan();
						   free=s.getFreejan();
						   val=s.getValjan();
						   fval=s.getFvaljan();
						}
						if (mon.equals("feb"))
						{
						   opng=s.getOpngfeb();
						   clos=s.getClosfeb();
						   qty=s.getQtyfeb();
						   free=s.getFreefeb();
						   val=s.getValfeb();
						   fval=s.getFvalfeb();
						}
						if (mon.equals("mar"))
						{
						   opng=s.getOpngmar();
						   clos=s.getClosmar();
						   qty=s.getQtymar();
						   free=s.getFreemar();
						   val=s.getValmar();
						   fval=s.getFvalmar();
						}
						if (mon.equals("apr"))
						{
						   opng=s.getOpngapr();
						   clos=s.getClosapr();
						   qty=s.getQtyapr();
						   free=s.getFreeapr();
						   val=s.getValapr();
						   fval=s.getFvalapr();
						}
						if (mon.equals("may"))
						{
						   opng=s.getOpngmay();
						   clos=s.getClosmay();
						   qty=s.getQtymay();
						   free=s.getFreemay();
						   val=s.getValmay();
						   fval=s.getFvalmay();
						}
						if (mon.equals("jun"))
						{
						   opng=s.getOpngjun();
						   clos=s.getClosjun();
						   qty=s.getQtyjun();
						   free=s.getFreejun();
						   val=s.getValjun();
						   fval=s.getFvaljun();
						}

						if (mon.equals("jul"))
						{
						   opng=s.getOpngjul();
						   clos=s.getClosjul();
						   qty=s.getQtyjul();
						   free=s.getFreejul();
						   val=s.getValjul();
						   fval=s.getFvaljul();
						}

						if (mon.equals("aug"))
						{
						   opng=s.getOpngaug();
						   clos=s.getClosaug();
						   qty=s.getQtyaug();
						   free=s.getFreeaug();
						   val=s.getValaug();
						   fval=s.getFvalaug();
						}
						if (mon.equals("sep"))
						{
						   opng=s.getOpngsep();
						   clos=s.getClossep();
						   qty=s.getQtysep();
						   free=s.getFreesep();
						   val=s.getValsep();
						   fval=s.getFvalsep();
						}
						ps3.setInt(1,0);
						ps3.setInt(2,0);
						ps3.setString(3,s.getItem_type());
						ps3.setInt(4,s.getGrp());
						ps3.setInt(5,s.getMgrp());
						ps3.setInt(6,s.getDum_code());
						ps3.setInt(7,opng);
						ps3.setInt(8,clos);
						ps3.setInt(9,qty);
						ps3.setInt(10,free);
						ps3.setFloat(11,val);
						ps3.setFloat(12,fval);
						ps3.setInt(13,s.getDepo_code());
						ps3.setInt(14,s.getItem_code());
						ps3.setInt(15,s.getDoc_type());
						ps3.setInt(16,s.getMkt_year());
						i= i + ps3.executeUpdate();
					}
			  	  
				}
				else 
				{   System.out.println("Insert Me a gaye");

					ps2.setInt(1,s.getDepo_code());
					ps2.setInt(2,0);
					ps2.setInt(3,0);
					ps2.setInt(4,s.getDoc_type());
					ps2.setInt(5,s.getItem_code());
					ps2.setString(6,s.getItem_type());
					ps2.setInt(7,s.getGrp());
					ps2.setInt(8,s.getMgrp());
					ps2.setInt(9,s.getDum_code());
					
					ps2.setInt(10,s.getOpngoct());
					ps2.setInt(11,s.getOpngnov());
					ps2.setInt(12,s.getOpngdec());
					ps2.setInt(13,s.getOpngjan());
					ps2.setInt(14,s.getOpngfeb());
					ps2.setInt(15,s.getOpngmar());
					ps2.setInt(16,s.getOpngapr());
					ps2.setInt(17,s.getOpngmay());
					ps2.setInt(18,s.getOpngjun());
					ps2.setInt(19,s.getOpngjul());
					ps2.setInt(20,s.getOpngaug());
					ps2.setInt(21,s.getOpngsep());
					
					ps2.setInt(22,s.getClosoct());
					ps2.setInt(23,s.getClosnov());
					ps2.setInt(24,s.getClosdec());
					ps2.setInt(25,s.getClosjan());
					ps2.setInt(26,s.getClosfeb());
					ps2.setInt(27,s.getClosmar());
					ps2.setInt(28,s.getClosapr());
					ps2.setInt(29,s.getClosmay());
					ps2.setInt(30,s.getClosjun());
					ps2.setInt(31,s.getClosjul());
					ps2.setInt(32,s.getClosaug());
					ps2.setInt(33,s.getClossep());

					ps2.setInt(34,s.getQtyoct());
					ps2.setInt(35,s.getQtynov());
					ps2.setInt(36,s.getQtydec());
					ps2.setInt(37,s.getQtyjan());
					ps2.setInt(38,s.getQtyfeb());
					ps2.setInt(39,s.getQtymar());
					ps2.setInt(40,s.getQtyapr());
					ps2.setInt(41,s.getQtymay());
					ps2.setInt(42,s.getQtyjun());
					ps2.setInt(43,s.getQtyjul());
					ps2.setInt(44,s.getQtyaug());
					ps2.setInt(45,s.getQtysep());

					ps2.setInt(46,s.getFreeoct());
					ps2.setInt(47,s.getFreenov());
					ps2.setInt(48,s.getFreedec());
					ps2.setInt(49,s.getFreejan());
					ps2.setInt(50,s.getFreefeb());
					ps2.setInt(51,s.getFreemar());
					ps2.setInt(52,s.getFreeapr());
					ps2.setInt(53,s.getFreemay());
					ps2.setInt(54,s.getFreejun());
					ps2.setInt(55,s.getFreejul());
					ps2.setInt(56,s.getFreeaug());
					ps2.setInt(57,s.getFreesep());
					
					ps2.setFloat(58,s.getValoct());
					ps2.setFloat(59,s.getValnov());
					ps2.setFloat(60,s.getValdec());
					ps2.setFloat(61,s.getValjan());
					ps2.setFloat(62,s.getValfeb());
					ps2.setFloat(63,s.getValmar());
					ps2.setFloat(64,s.getValapr());
					ps2.setFloat(65,s.getValmay());
					ps2.setFloat(66,s.getValjun());
					ps2.setFloat(67,s.getValjul());
					ps2.setFloat(68,s.getValaug());
					ps2.setFloat(69,s.getValsep());
					
					ps2.setFloat(70,s.getFvaloct());
					ps2.setFloat(71,s.getFvalnov());
					ps2.setFloat(72,s.getFvaldec());
					ps2.setFloat(73,s.getFvaljan());
					ps2.setFloat(74,s.getFvalfeb());
					ps2.setFloat(75,s.getFvalmar());
					ps2.setFloat(76,s.getFvalapr());
					ps2.setFloat(77,s.getFvalmay());
					ps2.setFloat(78,s.getFvaljun());
					ps2.setFloat(79,s.getFvaljul());
					ps2.setFloat(80,s.getFvalaug());
					ps2.setFloat(81,s.getFvalsep());
					ps2.setInt(82,s.getMkt_year());
					i= i + ps2.executeUpdate();
				} 
				rs.close();
				
    	}///// End of While Loop ////////////////		

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
			System.out.println("-------------Exception in SQLStockDAO.update " + ex);
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
		   //          if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLStockDAO.Connection.close "+e);
			  }
		}
			return i;
	   }
	
}

package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


//import javax.sql.DataSource;

import com.aristo.valueobject.StockiestFormBean;

public class SQLStockiestDAO implements StockiestDAO{ 
 
	 public int updateStockiest(String typ,List area,Connection con) 
	   {
	   int i=0;
	   StockiestFormBean s=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1 =null;
       PreparedStatement ps2 =null;	   
	   PreparedStatement ps3=null;
	   ResultSet rs =null; 
	   try {
  
	    String tblnm=null;
      	tblnm=(typ+"_Stockiest08").toLowerCase(); 
      	String query3 =null;
          
        String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and PR_CODE=? and STK_CODE=? and MKT_YEAR=? and TR_CD=?" +
        " order by mkt_year,depo_code,tr_cd,stk_code,pr_code";
        ps1 = con.prepareStatement(query1);
    		
		String query = "update " + tblnm + " set mpr_code=?,pr_type=?,st_cd=?,ar_cd=?,rg_cd=?," +
		"mr_cd=?,grp_cd=?,mgrp_cd=?,qtyoct=?,qtynov=?,qtydec=?,qtyjan=?,qtyfeb=?," +
		"qtymar=?,qtyapr=?,qtymay=?,qtyjun=?,qtyjul=?,qtyaug=?,qtysep=?," +
		"  valoct=?,   valnov=?,   valdec=?,   valjan=?,   valfeb=?,   valmar=?,valapr=?,valmay=?," +
		"  valjun=?,   valjul=?,   valaug=?,   valsep=?,   fqtyoct=?,  fqtynov=?,fqtydec=?,  " +
		"  fqtyjan=?,  fqtyfeb=?,  fqtymar=?,  fqtyapr=?,  fqtymay=?,  fqtyjun=?,fqtyjul=?," +
		"  fqtyaug=?,  fqtysep=?,  fvaloct=?,  fvalnov=?,  fvaldec=?,  fvaljan=?,fvalfeb=?," +
		"  fvalmar=?,  fvalapr=?,  fvalmay=?,  fvaljun=?,  fvaljul=?,  fvalaug=?,fvalsep=?," +
		"  eqtyoct=?,  eqtynov=?,  eqtydec=?,  eqtyjan=?,  eqtyfeb=?,  eqtymar=?,eqtyapr=?," +
		"  eqtymay=?,  eqtyjun=?,  eqtyjul=?,  eqtyaug=?,  eqtysep=?,  evaloct=?,evalnov=?," +
		"  evaldec=?,  evaljan=?,  evalfeb=?,  evalmar=?,  evalapr=?,  evalmay=?,evaljun=?," +
		"  evaljul=?,  evalaug=?,  evalsep=?,  bqtyoct=?,  bqtynov=?,  bqtydec=?,bqtyjan=?," +
		"  bqtyfeb=?,  bqtymar=?,  bqtyapr=?,  bqtymay=?,  bqtyjun=?,  bqtyjul=?,bqtyaug=?, " +
		"  bqtysep=?,  bvaloct=?,  bvalnov=?,  bvaldec=?,  bvaljan=?,  bvalfeb=?,bvalmar=?," +
		"  bvalapr=?,  bvalmay=?,  bvaljun=?,  bvaljul=?,  bvalaug=?,  bvalsep=?,rqtyoct=?," +
		"  rqtynov=?,  rqtydec=?,  rqtyjan=?,  rqtyfeb=?,  rqtymar=?,  rqtyapr=?,rqtymay=?," +
		"  rqtyjun=?,  rqtyjul=?,  rqtyaug=?,  rqtysep=?,  rvaloct=?,  rvalnov=?,rvaldec=?," +
		"  rvaljan=?,  rvalfeb=?,  rvalmar=?,  rvalapr=?,  rvalmay=?,  rvaljun=?,rvaljul=?," +
		"  rvalaug=?,  rvalsep=?,  sqtyoct=?,  sqtynov=?,  sqtydec=?,  sqtyjan=?,sqtyfeb=?," +
		"  sqtymar=?,  sqtyapr=?,  sqtymay=?,  sqtyjun=?,  sqtyjul=?,  sqtyaug=?,sqtysep=?," +
		"  svaloct=?,  svalnov=?,  svaldec=?,  svaljan=?,  svalfeb=?,  svalmar=?,svalapr=?," +
		"  svalmay=?,  svaljun=?,  svaljul=?,  svalaug=?,  svalsep=?,  pqtyoct=?,pqtynov=?," +
		"  pqtydec=?,  pqtyjan=?,  pqtyfeb=?,  pqtymar=?,  pqtyapr=?,  pqtymay=?,pqtyjun=?," +
		"  pqtyjul=?,  pqtyaug=?,  pqtysep=?,  pvaloct=?,  pvalnov=?,  pvaldec=?,pvaljan=?," +
		"  pvalfeb=?,  pvalmar=?,  pvalapr=?,  pvalmay=?,  pvaljun=?,  pvaljul=?,pvalaug=?," +
		"  pvalsep=?,  dgm_code=?, zm_code=?  where DEPO_CODE=? and PR_CODE=? and STK_CODE=? and MKT_YEAR=? and TR_CD=? ";


          ps = con.prepareStatement(query);

			String query2 ="insert into " + tblnm + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
			",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	       ps2 = con.prepareStatement(query2); 
		
		
		
		String mon=null;
		boolean first=false;
		con.setAutoCommit(false);

		
        Iterator it = area.iterator();
        if (it.hasNext())
        {
		    s = (StockiestFormBean) it.next();
	  	    mon=s.getMonth();
			query3 = "update " + tblnm + "  set qty"+mon+"=0," + 
			"val"+mon+"=0," +
			"fqty"+mon+"=0," +
			"fval"+mon+"=0," +
			"eqty"+mon+"=0," +
			"eval"+mon+"=0," +
			"bqty"+mon+"=0," +
			"bval"+mon+"=0," +
			"rqty"+mon+"=0," +
			"rval"+mon+"=0," +
			"sqty"+mon+"=0," +
			"sval"+mon+"=0," +
			"pqty"+mon+"=0," +
			"pval"+mon+"=0" +
			" where DEPO_CODE=? and MKT_YEAR=? ";  
 		    ps3 = con.prepareStatement(query3);
			ps3.setInt(1,s.getDepo_code());
			ps3.setInt(2,s.getMkt_year());
		    i= i + ps3.executeUpdate();
			ps3.close();
			query3=null;
			 
			query3 = "update " + tblnm + "  set mpr_code=?,pr_type=?,st_cd=?,ar_cd=?,rg_cd=?," + 
			"mr_cd=?,grp_cd=?,mgrp_cd=?,qty"+mon+"=?," + 
			"val"+mon+"=?," +
			"fqty"+mon+"=?," +
			"fval"+mon+"=?," +
			"eqty"+mon+"=?," +
			"eval"+mon+"=?," +
			"bqty"+mon+"=?," +
			"bval"+mon+"=?," +
			"rqty"+mon+"=?," +
			"rval"+mon+"=?," +
			"sqty"+mon+"=?," +
			"sval"+mon+"=?," +
			"pqty"+mon+"=?," +
			"pval"+mon+"=?," +
			"dgm_code=?, zm_code=? " +
			" where DEPO_CODE=? and PR_CODE=? and STK_CODE=? and MKT_YEAR=? and TR_CD=? ";  
			 ps3 = con.prepareStatement(query3);

	  		
        }
        
 	    while(it.hasNext()) {
		     if (first)
		     {
		    	s = (StockiestFormBean) it.next();
		     }
		        first=true;
		    	
				ps1.setInt(1,s.getDepo_code());
				ps1.setInt(2, s.getPr_code());
				ps1.setString( 3, s.getStk_code());  
				ps1.setInt(4, s.getMkt_year());  
				ps1.setInt(5, s.getTr_cd());				

				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					if (s.getCount()>=2)
					{	
					  System.out.println("Record Updated =====>Stockiest File<======= depo= "+s.getDepo_code());					
					
					ps.setInt(1,s.getMpr_code());
					ps.setString(2,s.getPr_type());
					ps.setInt(3,s.getSt_cd());
					ps.setInt(4,s.getAr_cd());
					ps.setInt(5,s.getRg_cd());
					ps.setInt(6,s.getMr_cd());
					ps.setInt(7,s.getGrp_cd());
					ps.setInt(8,s.getMgrp_cd());
					ps.setInt(9,s.getQtyoct());
					ps.setInt(10,s.getQtynov());
					ps.setInt(11,s.getQtydec());
					ps.setInt(12,s.getQtyjan());
					ps.setInt(13,s.getQtyfeb());
					ps.setInt(14,s.getQtymar());
					ps.setInt(15,s.getQtyapr());
					ps.setInt(16,s.getQtymay());
					ps.setInt(17,s.getQtyjun());
					ps.setInt(18,s.getQtyjul());
					ps.setInt(19,s.getQtyaug());
					ps.setInt(20,s.getQtysep());
					ps.setFloat(21,s.getValoct());
					ps.setFloat(22,s.getValnov());
					ps.setFloat(23,s.getValdec());
					ps.setFloat(24,s.getValjan());
					ps.setFloat(25,s.getValfeb());
					ps.setFloat(26,s.getValmar());
					ps.setFloat(27,s.getValapr());
					ps.setFloat(28,s.getValmay());
					ps.setFloat(29,s.getValjun());
					ps.setFloat(30,s.getValjul());
					ps.setFloat(31,s.getValaug());
					ps.setFloat(32,s.getValsep());
					ps.setInt(33,s.getFqtyoct());
					ps.setInt(34,s.getFqtynov());
					ps.setInt(35,s.getFqtydec());
					ps.setInt(36,s.getFqtyjan());
					ps.setInt(37,s.getFqtyfeb());
					ps.setInt(38,s.getFqtymar());
					ps.setInt(39,s.getFqtyapr());
					ps.setInt(40,s.getFqtymay());
					ps.setInt(41,s.getFqtyjun());
					ps.setInt(42,s.getFqtyjul());
					ps.setInt(43,s.getFqtyaug());
					ps.setInt(44,s.getFqtysep());
					ps.setFloat(45,s.getFvaloct());
					ps.setFloat(46,s.getFvalnov());
					ps.setFloat(47,s.getFvaldec());
					ps.setFloat(48,s.getFvaljan());
					ps.setFloat(49,s.getFvalfeb());
					ps.setFloat(50,s.getFvalmar());
					ps.setFloat(51,s.getFvalapr());
					ps.setFloat(52,s.getFvalmay());
					ps.setFloat(53,s.getFvaljun());
					ps.setFloat(54,s.getFvaljul());
					ps.setFloat(55,s.getFvalaug());
					ps.setFloat(56,s.getFvalsep());
					ps.setInt(57,s.getEqtyoct());
					ps.setInt(58,s.getEqtynov());
					ps.setInt(59,s.getEqtydec());
					ps.setInt(60,s.getEqtyjan());
					ps.setInt(61,s.getEqtyfeb());
					ps.setInt(62,s.getEqtymar());
					ps.setInt(63,s.getEqtyapr());
					ps.setInt(64,s.getEqtymay());
					ps.setInt(65,s.getEqtyjun());
					ps.setInt(66,s.getEqtyjul());
					ps.setInt(67,s.getEqtyaug());
					ps.setInt(68,s.getEqtysep());
					ps.setFloat(69,s.getEvaloct());
					ps.setFloat(70,s.getEvalnov());
					ps.setFloat(71,s.getEvaldec());
					ps.setFloat(72,s.getEvaljan());
					ps.setFloat(73,s.getEvalfeb());
					ps.setFloat(74,s.getEvalmar());
					ps.setFloat(75,s.getEvalapr());
					ps.setFloat(76,s.getEvalmay());
					ps.setFloat(77,s.getEvaljun());
					ps.setFloat(78,s.getEvaljul());
					ps.setFloat(79,s.getEvalaug());
					ps.setFloat(80,s.getEvalsep());
					ps.setInt(81,s.getBqtyoct());
					ps.setInt(82,s.getBqtynov());
					ps.setInt(83,s.getBqtydec());
					ps.setInt(84,s.getBqtyjan());
					ps.setInt(85,s.getBqtyfeb());
					ps.setInt(86,s.getBqtymar());
					ps.setInt(87,s.getBqtyapr());
					ps.setInt(88,s.getBqtymay());
					ps.setInt(89,s.getBqtyjun());
					ps.setInt(90,s.getBqtyjul());
					ps.setInt(91,s.getBqtyaug());
					ps.setInt(92,s.getBqtysep());
					ps.setFloat(93,s.getBvaloct());
					ps.setFloat(94,s.getBvalnov());
					ps.setFloat(95,s.getBvaldec());
					ps.setFloat(96,s.getBvaljan());
					ps.setFloat(97,s.getBvalfeb());
					ps.setFloat(98,s.getBvalmar());
					ps.setFloat(99,s.getBvalapr());
					ps.setFloat(100,s.getBvalmay());
					ps.setFloat(101,s.getBvaljun());
					ps.setFloat(102,s.getBvaljul());
					ps.setFloat(103,s.getBvalaug());
					ps.setFloat(104,s.getBvalsep());
					ps.setInt(105,s.getRqtyoct());
					ps.setInt(106,s.getRqtynov());
					ps.setInt(107,s.getRqtydec());
					ps.setInt(108,s.getRqtyjan());
					ps.setInt(109,s.getRqtyfeb());
					ps.setInt(110,s.getRqtymar());
					ps.setInt(111,s.getRqtyapr());
					ps.setInt(112,s.getRqtymay());
					ps.setInt(113,s.getRqtyjun());
					ps.setInt(114,s.getRqtyjul());
					ps.setInt(115,s.getRqtyaug());
					ps.setInt(116,s.getRqtysep());
					ps.setFloat(117,s.getRvaloct());
					ps.setFloat(118,s.getRvalnov());
					ps.setFloat(119,s.getRvaldec());
					ps.setFloat(120,s.getRvaljan());
					ps.setFloat(121,s.getRvalfeb());
					ps.setFloat(122,s.getRvalmar());
					ps.setFloat(123,s.getRvalapr());
					ps.setFloat(124,s.getRvalmay());
					ps.setFloat(125,s.getRvaljun());
					ps.setFloat(126,s.getRvaljul());
					ps.setFloat(127,s.getRvalaug());
					ps.setFloat(128,s.getRvalsep());
					ps.setInt(129,s.getSqtyoct());
					ps.setInt(130,s.getSqtynov());
					ps.setInt(131,s.getSqtydec());
					ps.setInt(132,s.getSqtyjan());
					ps.setInt(133,s.getSqtyfeb());
					ps.setInt(134,s.getSqtymar());
					ps.setInt(135,s.getSqtyapr());
					ps.setInt(136,s.getSqtymay());
					ps.setInt(137,s.getSqtyjun());
					ps.setInt(138,s.getSqtyjul());
					ps.setInt(139,s.getSqtyaug());
					ps.setInt(140,s.getSqtysep());
					ps.setFloat(141,s.getSvaloct());
					ps.setFloat(142,s.getSvalnov());
					ps.setFloat(143,s.getSvaldec());
					ps.setFloat(144,s.getSvaljan());
					ps.setFloat(145,s.getSvalfeb());
					ps.setFloat(146,s.getSvalmar());
					ps.setFloat(147,s.getSvalapr());
					ps.setFloat(148,s.getSvalmay());
					ps.setFloat(149,s.getSvaljun());
					ps.setFloat(150,s.getSvaljul());
					ps.setFloat(151,s.getSvalaug());
					ps.setFloat(152,s.getSvalsep());
					ps.setInt(153,s.getPqtyoct());
					ps.setInt(154,s.getPqtynov());
					ps.setInt(155,s.getPqtydec());
					ps.setInt(156,s.getPqtyjan());
					ps.setInt(157,s.getPqtyfeb());
					ps.setInt(158,s.getPqtymar());
					ps.setInt(159,s.getPqtyapr());
					ps.setInt(160,s.getPqtymay());
					ps.setInt(161,s.getPqtyjun());
					ps.setInt(162,s.getPqtyjul());
					ps.setInt(163,s.getPqtyaug());
					ps.setInt(164,s.getPqtysep());
					ps.setFloat(165,s.getPvaloct());
					ps.setFloat(166,s.getPvalnov());
					ps.setFloat(167,s.getPvaldec());
					ps.setFloat(168,s.getPvaljan());
					ps.setFloat(169,s.getPvalfeb());
					ps.setFloat(170,s.getPvalmar());
					ps.setFloat(171,s.getPvalapr());
					ps.setFloat(172,s.getPvalmay());
					ps.setFloat(173,s.getPvaljun());
					ps.setFloat(174,s.getPvaljul());
					ps.setFloat(175,s.getPvalaug());
					ps.setFloat(176,s.getPvalsep());
					ps.setInt(177,0);
					ps.setInt(178,0);
					ps.setInt(179,s.getDepo_code());
					ps.setInt(180,s.getPr_code());
					ps.setString(181,s.getStk_code());
					ps.setInt(182,s.getMkt_year());
					ps.setInt(183,s.getTr_cd());
					
					i= i+ps.executeUpdate();
					}else
					{
						System.out.println("Monthly Record Updated =====>Stockiest<======= depo= "+s.getDepo_code());						
						int qty=0;
						int fqty=0, eqty=0, bqty=0, rqty=0, sqty=0, pqty=0;
						float val=0.0f;
						float fval=0.0f, eval=0.0f, bval=0.0f, rval=0.0f, sval=0.0f, pval=0.0f;
						
						if (mon.equals("oct"))
						{
						   qty=s.getQtyoct();
						   fqty=s.getFqtyoct();
						   eqty=s.getEqtyoct();
						   bqty=s.getBqtyoct();
						   rqty=s.getRqtyoct();
						   sqty=s.getSqtyoct();
						   pqty=s.getPqtyoct();
						   
						   val= s.getValoct();
						   fval= s.getFvaloct();
						   eval= s.getEvaloct();
						   bval= s.getBvaloct();
						   rval= s.getRvaloct();
						   sval= s.getSvaloct();
						   pval= s.getPvaloct();
						}

						if (mon.equals("nov"))
						{
						   qty=s.getQtynov();
						   fqty=s.getFqtynov();
						   eqty=s.getEqtynov();
						   bqty=s.getBqtynov();
						   rqty=s.getRqtynov();
						   sqty=s.getSqtynov();
						   pqty=s.getPqtynov();
						   
						   val= s.getValnov();
						   fval= s.getFvalnov();
						   eval= s.getEvalnov();
						   bval= s.getBvalnov();
						   rval= s.getRvalnov();
						   sval= s.getSvalnov();
						   pval= s.getPvalnov();
						}
						

						if (mon.equals("dec"))
						{
						   qty=s.getQtydec();
						   fqty=s.getFqtydec();
						   eqty=s.getEqtydec();
						   bqty=s.getBqtydec();
						   rqty=s.getRqtydec();
						   sqty=s.getSqtydec();
						   pqty=s.getPqtydec();
						   
						   val= s.getValdec();
						   fval= s.getFvaldec();
						   eval= s.getEvaldec();
						   bval= s.getBvaldec();
						   rval= s.getRvaldec();
						   sval= s.getSvaldec();
						   pval= s.getPvaldec();
						}
						

						if (mon.equals("jan"))
						{
						   qty=s.getQtyjan();
						   fqty=s.getFqtyjan();
						   eqty=s.getEqtyjan();
						   bqty=s.getBqtyjan();
						   rqty=s.getRqtyjan();
						   sqty=s.getSqtyjan();
						   pqty=s.getPqtyjan();
						   
						   val= s.getValjan();
						   fval= s.getFvaljan();
						   eval= s.getEvaljan();
						   bval= s.getBvaljan();
						   rval= s.getRvaljan();
						   sval= s.getSvaljan();
						   pval= s.getPvaljan();
						}
						

						if (mon.equals("feb"))
						{
						   qty=s.getQtyfeb();
						   fqty=s.getFqtyfeb();
						   eqty=s.getEqtyfeb();
						   bqty=s.getBqtyfeb();
						   rqty=s.getRqtyfeb();
						   sqty=s.getSqtyfeb();
						   pqty=s.getPqtyfeb();
						   
						   val= s.getValfeb();
						   fval= s.getFvalfeb();
						   eval= s.getEvalfeb();
						   bval= s.getBvalfeb();
						   rval= s.getRvalfeb();
						   sval= s.getSvalfeb();
						   pval= s.getPvalfeb();
						}
						

						if (mon.equals("mar"))
						{
						   qty=s.getQtymar();
						   fqty=s.getFqtymar();
						   eqty=s.getEqtymar();
						   bqty=s.getBqtymar();
						   rqty=s.getRqtymar();
						   sqty=s.getSqtymar();
						   pqty=s.getPqtymar();
						   
						   val= s.getValmar();
						   fval= s.getFvalmar();
						   eval= s.getEvalmar();
						   bval= s.getBvalmar();
						   rval= s.getRvalmar();
						   sval= s.getSvalmar();
						   pval= s.getPvalmar();
						}
						

						if (mon.equals("apr"))
						{
						   qty=s.getQtyapr();
						   fqty=s.getFqtyapr();
						   eqty=s.getEqtyapr();
						   bqty=s.getBqtyapr();
						   rqty=s.getRqtyapr();
						   sqty=s.getSqtyapr();
						   pqty=s.getPqtyapr();
						   
						   val= s.getValapr();
						   fval= s.getFvalapr();
						   eval= s.getEvalapr();
						   bval= s.getBvalapr();
						   rval= s.getRvalapr();
						   sval= s.getSvalapr();
						   pval= s.getPvalapr();
						}
						

						if (mon.equals("may"))
						{
						   qty=s.getQtymay();
						   fqty=s.getFqtymay();
						   eqty=s.getEqtymay();
						   bqty=s.getBqtymay();
						   rqty=s.getRqtymay();
						   sqty=s.getSqtymay();
						   pqty=s.getPqtymay();
						   
						   val= s.getValmay();
						   fval= s.getFvalmay();
						   eval= s.getEvalmay();
						   bval= s.getBvalmay();
						   rval= s.getRvalmay();
						   sval= s.getSvalmay();
						   pval= s.getPvalmay();
						}
						

						if (mon.equals("jun"))
						{
						   qty=s.getQtyjun();
						   fqty=s.getFqtyjun();
						   eqty=s.getEqtyjun();
						   bqty=s.getBqtyjun();
						   rqty=s.getRqtyjun();
						   sqty=s.getSqtyjun();
						   pqty=s.getPqtyjun();
						   
						   val= s.getValjun();
						   fval= s.getFvaljun();
						   eval= s.getEvaljun();
						   bval= s.getBvaljun();
						   rval= s.getRvaljun();
						   sval= s.getSvaljun();
						   pval= s.getPvaljun();
						}
						

						if (mon.equals("jul"))
						{
						   qty=s.getQtyjul();
						   fqty=s.getFqtyjul();
						   eqty=s.getEqtyjul();
						   bqty=s.getBqtyjul();
						   rqty=s.getRqtyjul();
						   sqty=s.getSqtyjul();
						   pqty=s.getPqtyjul();
						   
						   val= s.getValjul();
						   fval= s.getFvaljul();
						   eval= s.getEvaljul();
						   bval= s.getBvaljul();
						   rval= s.getRvaljul();
						   sval= s.getSvaljul();
						   pval= s.getPvaljul();
						}
						

						if (mon.equals("aug"))
						{
						   qty=s.getQtyaug();
						   fqty=s.getFqtyaug();
						   eqty=s.getEqtyaug();
						   bqty=s.getBqtyaug();
						   rqty=s.getRqtyaug();
						   sqty=s.getSqtyaug();
						   pqty=s.getPqtyaug();
						   
						   val= s.getValaug();
						   fval= s.getFvalaug();
						   eval= s.getEvalaug();
						   bval= s.getBvalaug();
						   rval= s.getRvalaug();
						   sval= s.getSvalaug();
						   pval= s.getPvalaug();
						}
						

						if (mon.equals("sep"))
						{
						   qty=s.getQtysep();
						   fqty=s.getFqtysep();
						   eqty=s.getEqtysep();
						   bqty=s.getBqtysep();
						   rqty=s.getRqtysep();
						   sqty=s.getSqtysep();
						   pqty=s.getPqtysep();
						   
						   val= s.getValsep();
						   fval= s.getFvalsep();
						   eval= s.getEvalsep();
						   bval= s.getBvalsep();
						   rval= s.getRvalsep();
						   sval= s.getSvalsep();
						   pval= s.getPvalsep();
						}

						ps3.setInt(1,s.getMpr_code());
						ps3.setString(2,s.getPr_type());
						ps3.setInt(3,s.getSt_cd());
						ps3.setInt(4,s.getAr_cd());
						ps3.setInt(5,s.getRg_cd());
						ps3.setInt(6,s.getMr_cd());
						ps3.setInt(7,s.getGrp_cd());
						ps3.setInt(8,s.getMgrp_cd());
						ps3.setInt(9,qty);
						ps3.setFloat(10,val);
						ps3.setInt(11,fqty);
						ps3.setFloat(12,fval);
						ps3.setInt(13,eqty);
						ps3.setFloat(14,eval);
						ps3.setInt(15,bqty);
						ps3.setFloat(16,bval);
						ps3.setInt(17,rqty);
						ps3.setFloat(18,rval);
						ps3.setInt(19,sqty);
						ps3.setFloat(20,sval);
						ps3.setInt(21,pqty);
						ps3.setFloat(22,pval);
     					ps3.setInt(23,0);
						ps3.setInt(24,0);
						ps3.setInt(25,s.getDepo_code());
						ps3.setInt(26,s.getPr_code());
						ps3.setString(27,s.getStk_code());
						ps3.setInt(28,s.getMkt_year());
						ps3.setInt(29,s.getTr_cd());
						i= i + ps3.executeUpdate();
						

					}
			  	  
				}
				else 
				{   
					System.out.println("Record Insert =====>Stockiest<======= depo= "+s.getDepo_code());						
				
					
     				ps2.setInt(1,0);
					ps2.setInt(2,0);
					ps2.setInt(3,s.getDepo_code());
					ps2.setString(4,s.getStk_code());
					ps2.setInt(5,s.getPr_code());
					ps2.setInt(6,s.getMpr_code());
					ps2.setString(7,s.getPr_type());
					ps2.setInt(8,s.getTr_cd());
					ps2.setInt(9,s.getSt_cd());
					ps2.setInt(10,s.getAr_cd());
					ps2.setInt(11,s.getRg_cd());
					ps2.setInt(12,s.getMr_cd());
					ps2.setInt(13,s.getGrp_cd());
					ps2.setInt(14,s.getMgrp_cd());
					ps2.setInt(15,s.getQtyoct());
					ps2.setInt(16,s.getQtynov());
					ps2.setInt(17,s.getQtydec());
					ps2.setInt(18,s.getQtyjan());
					ps2.setInt(19,s.getQtyfeb());
					ps2.setInt(20,s.getQtymar());
					ps2.setInt(21,s.getQtyapr());
					ps2.setInt(22,s.getQtymay());
					ps2.setInt(23,s.getQtyjun());
					ps2.setInt(24,s.getQtyjul());
					ps2.setInt(25,s.getQtyaug());
					ps2.setInt(26,s.getQtysep());
					ps2.setFloat(27,s.getValoct());
					ps2.setFloat(28,s.getValnov());
					ps2.setFloat(29,s.getValdec());
					ps2.setFloat(30,s.getValjan());
					ps2.setFloat(31,s.getValfeb());
					ps2.setFloat(32,s.getValmar());
					ps2.setFloat(33,s.getValapr());
					ps2.setFloat(34,s.getValmay());
					ps2.setFloat(35,s.getValjun());
					ps2.setFloat(36,s.getValjul());
					ps2.setFloat(37,s.getValaug());
					ps2.setFloat(38,s.getValsep());
					ps2.setInt(39,s.getFqtyoct());
					ps2.setInt(40,s.getFqtynov());
					ps2.setInt(41,s.getFqtydec());
					ps2.setInt(42,s.getFqtyjan());
					ps2.setInt(43,s.getFqtyfeb());
					ps2.setInt(44,s.getFqtymar());
					ps2.setInt(45,s.getFqtyapr());
					ps2.setInt(46,s.getFqtymay());
					ps2.setInt(47,s.getFqtyjun());
					ps2.setInt(48,s.getFqtyjul());
					ps2.setInt(49,s.getFqtyaug());
					ps2.setInt(50,s.getFqtysep());
					ps2.setFloat(51,s.getFvaloct());
					ps2.setFloat(52,s.getFvalnov());
					ps2.setFloat(53,s.getFvaldec());
					ps2.setFloat(54,s.getFvaljan());
					ps2.setFloat(55,s.getFvalfeb());
					ps2.setFloat(56,s.getFvalmar());
					ps2.setFloat(57,s.getFvalapr());
					ps2.setFloat(58,s.getFvalmay());
					ps2.setFloat(59,s.getFvaljun());
					ps2.setFloat(60,s.getFvaljul());
					ps2.setFloat(61,s.getFvalaug());
					ps2.setFloat(62,s.getFvalsep());
					ps2.setInt(63,s.getEqtyoct());
					ps2.setInt(64,s.getEqtynov());
					ps2.setInt(65,s.getEqtydec());
					ps2.setInt(66,s.getEqtyjan());
					ps2.setInt(67,s.getEqtyfeb());
					ps2.setInt(68,s.getEqtymar());
					ps2.setInt(69,s.getEqtyapr());
					ps2.setInt(70,s.getEqtymay());
					ps2.setInt(71,s.getEqtyjun());
					ps2.setInt(72,s.getEqtyjul());
					ps2.setInt(73,s.getEqtyaug());
					ps2.setInt(74,s.getEqtysep());
					ps2.setFloat(75,s.getEvaloct());
					ps2.setFloat(76,s.getEvalnov());
					ps2.setFloat(77,s.getEvaldec());
					ps2.setFloat(78,s.getEvaljan());
					ps2.setFloat(79,s.getEvalfeb());
					ps2.setFloat(80,s.getEvalmar());
					ps2.setFloat(81,s.getEvalapr());
					ps2.setFloat(82,s.getEvalmay());
					ps2.setFloat(83,s.getEvaljun());
					ps2.setFloat(84,s.getEvaljul());
					ps2.setFloat(85,s.getEvalaug());
					ps2.setFloat(86,s.getEvalsep());
					ps2.setInt(87,s.getBqtyoct());
					ps2.setInt(88,s.getBqtynov());
					ps2.setInt(89,s.getBqtydec());
					ps2.setInt(90,s.getBqtyjan());
					ps2.setInt(91,s.getBqtyfeb());
					ps2.setInt(92,s.getBqtymar());
					ps2.setInt(93,s.getBqtyapr());
					ps2.setInt(94,s.getBqtymay());
					ps2.setInt(95,s.getBqtyjun());
					ps2.setInt(96,s.getBqtyjul());
					ps2.setInt(97,s.getBqtyaug());
					ps2.setInt(98,s.getBqtysep());
					ps2.setFloat(99,s.getBvaloct());
					ps2.setFloat(100,s.getBvalnov());
					ps2.setFloat(101,s.getBvaldec());
					ps2.setFloat(102,s.getBvaljan());
					ps2.setFloat(103,s.getBvalfeb());
					ps2.setFloat(104,s.getBvalmar());
					ps2.setFloat(105,s.getBvalapr());
					ps2.setFloat(106,s.getBvalmay());
					ps2.setFloat(107,s.getBvaljun());
					ps2.setFloat(108,s.getBvaljul());
					ps2.setFloat(109,s.getBvalaug());
					ps2.setFloat(110,s.getBvalsep());
					ps2.setInt(111,s.getRqtyoct());
					ps2.setInt(112,s.getRqtynov());
					ps2.setInt(113,s.getRqtydec());
					ps2.setInt(114,s.getRqtyjan());
					ps2.setInt(115,s.getRqtyfeb());
					ps2.setInt(116,s.getRqtymar());
					ps2.setInt(117,s.getRqtyapr());
					ps2.setInt(118,s.getRqtymay());
					ps2.setInt(119,s.getRqtyjun());
					ps2.setInt(120,s.getRqtyjul());
					ps2.setInt(121,s.getRqtyaug());
					ps2.setInt(122,s.getRqtysep());
					ps2.setFloat(123,s.getRvaloct());
					ps2.setFloat(124,s.getRvalnov());
					ps2.setFloat(125,s.getRvaldec());
					ps2.setFloat(126,s.getRvaljan());
					ps2.setFloat(127,s.getRvalfeb());
					ps2.setFloat(128,s.getRvalmar());
					ps2.setFloat(129,s.getRvalapr());
					ps2.setFloat(130,s.getRvalmay());
					ps2.setFloat(131,s.getRvaljun());
					ps2.setFloat(132,s.getRvaljul());
					ps2.setFloat(133,s.getRvalaug());
					ps2.setFloat(134,s.getRvalsep());
					ps2.setInt(135,s.getSqtyoct());
					ps2.setInt(136,s.getSqtynov());
					ps2.setInt(137,s.getSqtydec());
					ps2.setInt(138,s.getSqtyjan());
					ps2.setInt(139,s.getSqtyfeb());
					ps2.setInt(140,s.getSqtymar());
					ps2.setInt(141,s.getSqtyapr());
					ps2.setInt(142,s.getSqtymay());
					ps2.setInt(143,s.getSqtyjun());
					ps2.setInt(144,s.getSqtyjul());
					ps2.setInt(145,s.getSqtyaug());
					ps2.setInt(146,s.getSqtysep());
					ps2.setFloat(147,s.getSvaloct());
					ps2.setFloat(148,s.getSvalnov());
					ps2.setFloat(149,s.getSvaldec());
					ps2.setFloat(150,s.getSvaljan());
					ps2.setFloat(151,s.getSvalfeb());
					ps2.setFloat(152,s.getSvalmar());
					ps2.setFloat(153,s.getSvalapr());
					ps2.setFloat(154,s.getSvalmay());
					ps2.setFloat(155,s.getSvaljun());
					ps2.setFloat(156,s.getSvaljul());
					ps2.setFloat(157,s.getSvalaug());
					ps2.setFloat(158,s.getSvalsep());
					ps2.setInt(159,s.getPqtyoct());
					ps2.setInt(160,s.getPqtynov());
					ps2.setInt(161,s.getPqtydec());
					ps2.setInt(162,s.getPqtyjan());
					ps2.setInt(163,s.getPqtyfeb());
					ps2.setInt(164,s.getPqtymar());
					ps2.setInt(165,s.getPqtyapr());
					ps2.setInt(166,s.getPqtymay());
					ps2.setInt(167,s.getPqtyjun());
					ps2.setInt(168,s.getPqtyjul());
					ps2.setInt(169,s.getPqtyaug());
					ps2.setInt(170,s.getPqtysep());
					ps2.setFloat(171,s.getPvaloct());
					ps2.setFloat(172,s.getPvalnov());
					ps2.setFloat(173,s.getPvaldec());
					ps2.setFloat(174,s.getPvaljan());
					ps2.setFloat(175,s.getPvalfeb());
					ps2.setFloat(176,s.getPvalmar());
					ps2.setFloat(177,s.getPvalapr());
					ps2.setFloat(178,s.getPvalmay());
					ps2.setFloat(179,s.getPvaljun());
					ps2.setFloat(180,s.getPvaljul());
					ps2.setFloat(181,s.getPvalaug());
					ps2.setFloat(182,s.getPvalsep());
					ps2.setInt(183,s.getMkt_year());

					i= i + ps2.executeUpdate();
	
					
				} 
				
				rs.close();
 	}		// End of while Loop////////
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
			System.out.println("-------------Exception in SQLStockiestDAO.update " + ex);
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
		           //  if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLStockiestDAO.Connection.close "+e);
			  }
		}
			
			 
			
			return i;
		   
		   
	   }



}

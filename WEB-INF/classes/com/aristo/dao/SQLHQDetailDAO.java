package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.HQDetailFormBean;
  

public class SQLHQDetailDAO implements HQDetailDAO{

	 public int updateHQDetail(String typ, List hq, Connection con)
	   {
		int i=0;
	    HQDetailFormBean h=null;
	    PreparedStatement ps=null;
	    PreparedStatement ps1=null;
	    PreparedStatement ps2 =null;
	    PreparedStatement ps3=null;
	    ResultSet rs=null;
	    try {
          
          String tblnm=null;
          tblnm=(typ+"_hqdetail08").toLowerCase();
          String query3 =null;
          
       String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and PR_CODE=? and TR_CD=? and MKT_YEAR=? order by depo_code,ar_cd,rg_cd,tr_cd,pr_code";
	   ps1 = con.prepareStatement(query1); 

  	   String query = "update " + tblnm + "  set mpr_code=?,pr_type=?,st_cd=?,ar_cd=?,rg_cd=?," + 
		"mr_cd=?,grp_cd=?,mgrp_cd=?,qtyoct=?,qtynov=?,qtydec=?,qtyjan=?,qtyfeb=?," + 
		"qtymar=?,qtyapr=?,qtymay=?,qtyjun=?,qtyjul=?,qtyaug=?,qtysep=?," + 
		"valoct=?,valnov=?,valdec=?,valjan=?,valfeb=?,valmar=?,valapr=?,valmay=?," +
		"valjun=?,valjul=?,valaug=?,valsep=?,fqtyoct=?,fqtynov=?,fqtydec=?,  " +
		"fqtyjan=?,fqtyfeb=?,fqtymar=?,fqtyapr=?,fqtymay=?,fqtyjun=?,fqtyjul=?," +
		" fqtyaug=?,fqtysep=?,fvaloct=?,fvalnov=?,fvaldec=?,fvaljan=?,fvalfeb=?," +
		"  fvalmar=?,fvalapr=?,fvalmay=?,fvaljun=?,fvaljul=?,fvalaug=?,fvalsep=?," +
		"  eqtyoct=?,eqtynov=?,eqtydec=?,eqtyjan=?,eqtyfeb=?,eqtymar=?,eqtyapr=?," +
		"  eqtymay=?,eqtyjun=?,eqtyjul=?,eqtyaug=?,eqtysep=?,evaloct=?,evalnov=?," +
		"  evaldec=?,evaljan=?,evalfeb=?,evalmar=?,evalapr=?,evalmay=?,evaljun=?," +
		"  evaljul=?,evalaug=?,evalsep=?,bqtyoct=?,bqtynov=?,bqtydec=?,bqtyjan=?," +
		"  bqtyfeb=?,bqtymar=?,bqtyapr=?,bqtymay=?,bqtyjun=?,bqtyjul=?,bqtyaug=?, " +
		" bqtysep=?,bvaloct=?,bvalnov=?,bvaldec=?,bvaljan=?,bvalfeb=?,bvalmar=?," +
		"  bvalapr=?,bvalmay=?,bvaljun=?,bvaljul=?,bvalaug=?,bvalsep=?,rqtyoct=?," +
		"  rqtynov=?,rqtydec=?,rqtyjan=?,rqtyfeb=?,rqtymar=?,rqtyapr=?,rqtymay=?," +
		"  rqtyjun=?,rqtyjul=?,rqtyaug=?,rqtysep=?,rvaloct=?,rvalnov=?,rvaldec=?," +
		"  rvaljan=?,rvalfeb=?,rvalmar=?,rvalapr=?,rvalmay=?,rvaljun=?,rvaljul=?," +
		"  rvalaug=?,rvalsep=?,sqtyoct=?,sqtynov=?,sqtydec=?,sqtyjan=?,sqtyfeb=?," +
		"  sqtymar=?,sqtyapr=?,sqtymay=?,sqtyjun=?,sqtyjul=?,sqtyaug=?,sqtysep=?," +
		"  svaloct=?,svalnov=?,svaldec=?,svaljan=?,svalfeb=?,svalmar=?,svalapr=?," +
		"  svalmay=?,svaljun=?,svaljul=?,svalaug=?,svalsep=?,pqtyoct=?,pqtynov=?," +
		"  pqtydec=?,pqtyjan=?,pqtyfeb=?,pqtymar=?,pqtyapr=?,pqtymay=?,pqtyjun=?," +
		"  pqtyjul=?,pqtyaug=?,pqtysep=?,pvaloct=?,pvalnov=?,pvaldec=?,pvaljan=?," +
		"  pvalfeb=?,pvalmar=?,pvalapr=?,pvalmay=?,pvaljun=?,pvaljul=?,pvalaug=?," +
		"  pvalsep=?,dgm_code=?,zm_code=?  where DEPO_CODE=? and PR_CODE=? and TR_CD=? and MKT_YEAR=?";  
        ps = con.prepareStatement(query);

        
		String query2 ="insert into " + tblnm + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
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
  	   Iterator it = hq.iterator();
       if (it.hasNext())
       {
		h = (HQDetailFormBean) it.next();        
 	    mon=h.getMonth();
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
		" where DEPO_CODE=? and MKT_YEAR=?";  
        ps3 = con.prepareStatement(query3);
        ps3.setInt(1,h.getDepo_code());
		ps3.setInt(2,h.getMkt_year());
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
		"dgm_code=?, zm_code=?   where DEPO_CODE=? and PR_CODE=? and TR_CD=? and MKT_YEAR=?";  
        ps3 = con.prepareStatement(query3);        
        
        
       }
       System.out.println(it.hasNext());
     	while(it.hasNext()) {
     		  if (first)
     		  {
    			h = (HQDetailFormBean) it.next();
     		  }
     		    first=true;
				ps1.setInt(1,h.getDepo_code());
				ps1.setInt(2, h.getPr_code());
				ps1.setInt( 3, h.getTr_cd());
				ps1.setInt( 4, h.getMkt_year());
				rs = ps1.executeQuery();
				if (rs.next()) 
				{
				if (h.getCount()>=2)
				{	
					ps.setInt(1,h.getMpr_code());
					ps.setString(2,h.getPr_type());
					ps.setInt(3,h.getSt_cd());
					ps.setInt(4,h.getAr_cd());
					ps.setInt(5,h.getRg_cd());
					ps.setInt(6,h.getMr_cd());
					ps.setInt(7,h.getGrp_cd());
					ps.setInt(8,h.getMgrp_cd());
					ps.setInt(9,h.getQtyoct());
					ps.setInt(10,h.getQtynov());
					ps.setInt(11,h.getQtydec());
					ps.setInt(12,h.getQtyjan());
					ps.setInt(13,h.getQtyfeb());
					ps.setInt(14,h.getQtymar());
					ps.setInt(15,h.getQtyapr());
					ps.setInt(16,h.getQtymay());
					ps.setInt(17,h.getQtyjun());
					ps.setInt(18,h.getQtyjul());
					ps.setInt(19,h.getQtyaug());
					ps.setInt(20,h.getQtysep());
					ps.setFloat(21,h.getValoct());
					ps.setFloat(22,h.getValnov());
					ps.setFloat(23,h.getValdec());
					ps.setFloat(24,h.getValjan());
					ps.setFloat(25,h.getValfeb());
					ps.setFloat(26,h.getValmar());
					ps.setFloat(27,h.getValapr());
					ps.setFloat(28,h.getValmay());
					ps.setFloat(29,h.getValjun());
					ps.setFloat(30,h.getValjul());
					ps.setFloat(31,h.getValaug());
					ps.setFloat(32,h.getValsep());
					ps.setInt(33,h.getFqtyoct());
					ps.setInt(34,h.getFqtynov());
					ps.setInt(35,h.getFqtydec());
					ps.setInt(36,h.getFqtyjan());
					ps.setInt(37,h.getFqtyfeb());
					ps.setInt(38,h.getFqtymar());
					ps.setInt(39,h.getFqtyapr());
					ps.setInt(40,h.getFqtymay());
					ps.setInt(41,h.getFqtyjun());
					ps.setInt(42,h.getFqtyjul());
					ps.setInt(43,h.getFqtyaug());
					ps.setInt(44,h.getFqtysep());
					ps.setFloat(45,h.getFvaloct());
					ps.setFloat(46,h.getFvalnov());
					ps.setFloat(47,h.getFvaldec());
					ps.setFloat(48,h.getFvaljan());
					ps.setFloat(49,h.getFvalfeb());
					ps.setFloat(50,h.getFvalmar());
					ps.setFloat(51,h.getFvalapr());
					ps.setFloat(52,h.getFvalmay());
					ps.setFloat(53,h.getFvaljun());
					ps.setFloat(54,h.getFvaljul());
					ps.setFloat(55,h.getFvalaug());
					ps.setFloat(56,h.getFvalsep());
					ps.setInt(57,h.getEqtyoct());
					ps.setInt(58,h.getEqtynov());
					ps.setInt(59,h.getEqtydec());
					ps.setInt(60,h.getEqtyjan());
					ps.setInt(61,h.getEqtyfeb());
					ps.setInt(62,h.getEqtymar());
					ps.setInt(63,h.getEqtyapr());
					ps.setInt(64,h.getEqtymay());
					ps.setInt(65,h.getEqtyjun());
					ps.setInt(66,h.getEqtyjul());
					ps.setInt(67,h.getEqtyaug());
					ps.setInt(68,h.getEqtysep());
					ps.setFloat(69,h.getEvaloct());
					ps.setFloat(70,h.getEvalnov());
					ps.setFloat(71,h.getEvaldec());
					ps.setFloat(72,h.getEvaljan());
					ps.setFloat(73,h.getEvalfeb());
					ps.setFloat(74,h.getEvalmar());
					ps.setFloat(75,h.getEvalapr());
					ps.setFloat(76,h.getEvalmay());
					ps.setFloat(77,h.getEvaljun());
					ps.setFloat(78,h.getEvaljul());
					ps.setFloat(79,h.getEvalaug());
					ps.setFloat(80,h.getEvalsep());
					ps.setInt(81,h.getBqtyoct());
					ps.setInt(82,h.getBqtynov());
					ps.setInt(83,h.getBqtydec());
					ps.setInt(84,h.getBqtyjan());
					ps.setInt(85,h.getBqtyfeb());
					ps.setInt(86,h.getBqtymar());
					ps.setInt(87,h.getBqtyapr());
					ps.setInt(88,h.getBqtymay());
					ps.setInt(89,h.getBqtyjun());
					ps.setInt(90,h.getBqtyjul());
					ps.setInt(91,h.getBqtyaug());
					ps.setInt(92,h.getBqtysep());
					ps.setFloat(93,h.getBvaloct());
					ps.setFloat(94,h.getBvalnov());
					ps.setFloat(95,h.getBvaldec());
					ps.setFloat(96,h.getBvaljan());
					ps.setFloat(97,h.getBvalfeb());
					ps.setFloat(98,h.getBvalmar());
					ps.setFloat(99,h.getBvalapr());
					ps.setFloat(100,h.getBvalmay());
					ps.setFloat(101,h.getBvaljun());
					ps.setFloat(102,h.getBvaljul());
					ps.setFloat(103,h.getBvalaug());
					ps.setFloat(104,h.getBvalsep());
					ps.setInt(105,h.getRqtyoct());
					ps.setInt(106,h.getRqtynov());
					ps.setInt(107,h.getRqtydec());
					ps.setInt(108,h.getRqtyjan());
					ps.setInt(109,h.getRqtyfeb());
					ps.setInt(110,h.getRqtymar());
					ps.setInt(111,h.getRqtyapr());
					ps.setInt(112,h.getRqtymay());
					ps.setInt(113,h.getRqtyjun());
					ps.setInt(114,h.getRqtyjul());
					ps.setInt(115,h.getRqtyaug());
					ps.setInt(116,h.getRqtysep());
					ps.setFloat(117,h.getRvaloct());
					ps.setFloat(118,h.getRvalnov());
					ps.setFloat(119,h.getRvaldec());
					ps.setFloat(120,h.getRvaljan());
					ps.setFloat(121,h.getRvalfeb());
					ps.setFloat(122,h.getRvalmar());
					ps.setFloat(123,h.getRvalapr());
					ps.setFloat(124,h.getRvalmay());
					ps.setFloat(125,h.getRvaljun());
					ps.setFloat(126,h.getRvaljul());
					ps.setFloat(127,h.getRvalaug());
					ps.setFloat(128,h.getRvalsep());
					ps.setInt(129,h.getSqtyoct());
					ps.setInt(130,h.getSqtynov());
					ps.setInt(131,h.getSqtydec());
					ps.setInt(132,h.getSqtyjan());
					ps.setInt(133,h.getSqtyfeb());
					ps.setInt(134,h.getSqtymar());
					ps.setInt(135,h.getSqtyapr());
					ps.setInt(136,h.getSqtymay());
					ps.setInt(137,h.getSqtyjun());
					ps.setInt(138,h.getSqtyjul());
					ps.setInt(139,h.getSqtyaug());
					ps.setInt(140,h.getSqtysep());
					ps.setFloat(141,h.getSvaloct());
					ps.setFloat(142,h.getSvalnov());
					ps.setFloat(143,h.getSvaldec());
					ps.setFloat(144,h.getSvaljan());
					ps.setFloat(145,h.getSvalfeb());
					ps.setFloat(146,h.getSvalmar());
					ps.setFloat(147,h.getSvalapr());
					ps.setFloat(148,h.getSvalmay());
					ps.setFloat(149,h.getSvaljun());
					ps.setFloat(150,h.getSvaljul());
					ps.setFloat(151,h.getSvalaug());
					ps.setFloat(152,h.getSvalsep());
					ps.setInt(153,h.getPqtyoct());
					ps.setInt(154,h.getPqtynov());
					ps.setInt(155,h.getPqtydec());
					ps.setInt(156,h.getPqtyjan());
					ps.setInt(157,h.getPqtyfeb());
					ps.setInt(158,h.getPqtymar());
					ps.setInt(159,h.getPqtyapr());
					ps.setInt(160,h.getPqtymay());
					ps.setInt(161,h.getPqtyjun());
					ps.setInt(162,h.getPqtyjul());
					ps.setInt(163,h.getPqtyaug());
					ps.setInt(164,h.getPqtysep());
					ps.setFloat(165,h.getPvaloct());
					ps.setFloat(166,h.getPvalnov());
					ps.setFloat(167,h.getPvaldec());
					ps.setFloat(168,h.getPvaljan());
					ps.setFloat(169,h.getPvalfeb());
					ps.setFloat(170,h.getPvalmar());
					ps.setFloat(171,h.getPvalapr());
					ps.setFloat(172,h.getPvalmay());
					ps.setFloat(173,h.getPvaljun());
					ps.setFloat(174,h.getPvaljul());
					ps.setFloat(175,h.getPvalaug());
					ps.setFloat(176,h.getPvalsep());
					ps.setInt(177,0);
					ps.setInt(178,0);
					ps.setInt(179,h.getDepo_code());
					ps.setInt(180,h.getPr_code());
					ps.setInt(181,h.getTr_cd());
					ps.setInt(182,h.getMkt_year());
					i= i + ps.executeUpdate();
				}
				else
				{
					System.out.println("Monthly Record Updated =====>HQDetail<======= depo= "+h.getDepo_code());
					int qty=0;
					int fqty=0, eqty=0, bqty=0, rqty=0, sqty=0, pqty=0;
					float val=0.0f;
					float fval=0.0f, eval=0.0f, bval=0.0f, rval=0.0f, sval=0.0f, pval=0.0f;
					
					if (mon.equals("oct"))
					{
					   qty=h.getQtyoct();
					   fqty=h.getFqtyoct();
					   eqty=h.getEqtyoct();
					   bqty=h.getBqtyoct();
					   rqty=h.getRqtyoct();
					   sqty=h.getSqtyoct();
					   pqty=h.getPqtyoct();
					   
					   val= h.getValoct();
					   fval= h.getFvaloct();
					   eval= h.getEvaloct();
					   bval= h.getBvaloct();
					   rval= h.getRvaloct();
					   sval= h.getSvaloct();
					   pval= h.getPvaloct();
					}

					if (mon.equals("nov"))
					{
					   qty=h.getQtynov();
					   fqty=h.getFqtynov();
					   eqty=h.getEqtynov();
					   bqty=h.getBqtynov();
					   rqty=h.getRqtynov();
					   sqty=h.getSqtynov();
					   pqty=h.getPqtynov();
					   
					   val= h.getValnov();
					   fval= h.getFvalnov();
					   eval= h.getEvalnov();
					   bval= h.getBvalnov();
					   rval= h.getRvalnov();
					   sval= h.getSvalnov();
					   pval= h.getPvalnov();
					}
					

					if (mon.equals("dec"))
					{
					   qty=h.getQtydec();
					   fqty=h.getFqtydec();
					   eqty=h.getEqtydec();
					   bqty=h.getBqtydec();
					   rqty=h.getRqtydec();
					   sqty=h.getSqtydec();
					   pqty=h.getPqtydec();
					   
					   val= h.getValdec();
					   fval= h.getFvaldec();
					   eval= h.getEvaldec();
					   bval= h.getBvaldec();
					   rval= h.getRvaldec();
					   sval= h.getSvaldec();
					   pval= h.getPvaldec();
					}
					

					if (mon.equals("jan"))
					{
					   qty=h.getQtyjan();
					   fqty=h.getFqtyjan();
					   eqty=h.getEqtyjan();
					   bqty=h.getBqtyjan();
					   rqty=h.getRqtyjan();
					   sqty=h.getSqtyjan();
					   pqty=h.getPqtyjan();
					   
					   val= h.getValjan();
					   fval= h.getFvaljan();
					   eval= h.getEvaljan();
					   bval= h.getBvaljan();
					   rval= h.getRvaljan();
					   sval= h.getSvaljan();
					   pval= h.getPvaljan();
					}
					

					if (mon.equals("feb"))
					{
					   qty=h.getQtyfeb();
					   fqty=h.getFqtyfeb();
					   eqty=h.getEqtyfeb();
					   bqty=h.getBqtyfeb();
					   rqty=h.getRqtyfeb();
					   sqty=h.getSqtyfeb();
					   pqty=h.getPqtyfeb();
					   
					   val= h.getValfeb();
					   fval= h.getFvalfeb();
					   eval= h.getEvalfeb();
					   bval= h.getBvalfeb();
					   rval= h.getRvalfeb();
					   sval= h.getSvalfeb();
					   pval= h.getPvalfeb();
					}
					

					if (mon.equals("mar"))
					{
					   qty=h.getQtymar();
					   fqty=h.getFqtymar();
					   eqty=h.getEqtymar();
					   bqty=h.getBqtymar();
					   rqty=h.getRqtymar();
					   sqty=h.getSqtymar();
					   pqty=h.getPqtymar();
					   
					   val= h.getValmar();
					   fval= h.getFvalmar();
					   eval= h.getEvalmar();
					   bval= h.getBvalmar();
					   rval= h.getRvalmar();
					   sval= h.getSvalmar();
					   pval= h.getPvalmar();
					}
					

					if (mon.equals("apr"))
					{
					   qty=h.getQtyapr();
					   fqty=h.getFqtyapr();
					   eqty=h.getEqtyapr();
					   bqty=h.getBqtyapr();
					   rqty=h.getRqtyapr();
					   sqty=h.getSqtyapr();
					   pqty=h.getPqtyapr();
					   
					   val= h.getValapr();
					   fval= h.getFvalapr();
					   eval= h.getEvalapr();
					   bval= h.getBvalapr();
					   rval= h.getRvalapr();
					   sval= h.getSvalapr();
					   pval= h.getPvalapr();
					}
					

					if (mon.equals("may"))
					{
					   qty=h.getQtymay();
					   fqty=h.getFqtymay();
					   eqty=h.getEqtymay();
					   bqty=h.getBqtymay();
					   rqty=h.getRqtymay();
					   sqty=h.getSqtymay();
					   pqty=h.getPqtymay();
					   
					   val= h.getValmay();
					   fval= h.getFvalmay();
					   eval= h.getEvalmay();
					   bval= h.getBvalmay();
					   rval= h.getRvalmay();
					   sval= h.getSvalmay();
					   pval= h.getPvalmay();
					}
					

					if (mon.equals("jun"))
					{
					   qty=h.getQtyjun();
					   fqty=h.getFqtyjun();
					   eqty=h.getEqtyjun();
					   bqty=h.getBqtyjun();
					   rqty=h.getRqtyjun();
					   sqty=h.getSqtyjun();
					   pqty=h.getPqtyjun();
					   
					   val= h.getValjun();
					   fval= h.getFvaljun();
					   eval= h.getEvaljun();
					   bval= h.getBvaljun();
					   rval= h.getRvaljun();
					   sval= h.getSvaljun();
					   pval= h.getPvaljun();
					}
					

					if (mon.equals("jul"))
					{
					   qty=h.getQtyjul();
					   fqty=h.getFqtyjul();
					   eqty=h.getEqtyjul();
					   bqty=h.getBqtyjul();
					   rqty=h.getRqtyjul();
					   sqty=h.getSqtyjul();
					   pqty=h.getPqtyjul();
					   
					   val= h.getValjul();
					   fval= h.getFvaljul();
					   eval= h.getEvaljul();
					   bval= h.getBvaljul();
					   rval= h.getRvaljul();
					   sval= h.getSvaljul();
					   pval= h.getPvaljul();
					}
					

					if (mon.equals("aug"))
					{
					   qty=h.getQtyaug();
					   fqty=h.getFqtyaug();
					   eqty=h.getEqtyaug();
					   bqty=h.getBqtyaug();
					   rqty=h.getRqtyaug();
					   sqty=h.getSqtyaug();
					   pqty=h.getPqtyaug();
					   
					   val= h.getValaug();
					   fval= h.getFvalaug();
					   eval= h.getEvalaug();
					   bval= h.getBvalaug();
					   rval= h.getRvalaug();
					   sval= h.getSvalaug();
					   pval= h.getPvalaug();
					}
					

					if (mon.equals("sep"))
					{
					   qty=h.getQtysep();
					   fqty=h.getFqtysep();
					   eqty=h.getEqtysep();
					   bqty=h.getBqtysep();
					   rqty=h.getRqtysep();
					   sqty=h.getSqtysep();
					   pqty=h.getPqtysep();
					   
					   val= h.getValsep();
					   fval= h.getFvalsep();
					   eval= h.getEvalsep();
					   bval= h.getBvalsep();
					   rval= h.getRvalsep();
					   sval= h.getSvalsep();
					   pval= h.getPvalsep();
					}
					
					ps3.setInt(1,h.getMpr_code());
					ps3.setString(2,h.getPr_type());
					ps3.setInt(3,h.getSt_cd());
					ps3.setInt(4,h.getAr_cd());
					ps3.setInt(5,h.getRg_cd());
					ps3.setInt(6,h.getMr_cd());
					ps3.setInt(7,h.getGrp_cd());
					ps3.setInt(8,h.getMgrp_cd());
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
					ps3.setInt(25,h.getDepo_code());
					ps3.setInt(26,h.getPr_code());
					ps3.setInt(27,h.getTr_cd());
					ps3.setInt(28,h.getMkt_year());
					i= i + ps3.executeUpdate();
				}
				}   
				else 
				{  
					System.out.println("Insert Me a gaye");
					ps2.setInt(1,0);
					ps2.setInt(2,0);
					ps2.setInt(3,h.getDepo_code());
					ps2.setInt(4,h.getPr_code());
					ps2.setInt(5,h.getMpr_code());
					ps2.setString(6,h.getPr_type());
					ps2.setInt(7,h.getTr_cd());
					ps2.setInt(8,h.getSt_cd());
					ps2.setInt(9,h.getAr_cd());
					ps2.setInt(10,h.getRg_cd());
					ps2.setInt(11,h.getMr_cd());
					ps2.setInt(12,h.getGrp_cd());
					ps2.setInt(13,h.getMgrp_cd());
					ps2.setInt(14,h.getQtyoct());
					ps2.setInt(15,h.getQtynov());
					ps2.setInt(16,h.getQtydec());
					ps2.setInt(17,h.getQtyjan());
					ps2.setInt(18,h.getQtyfeb());
					ps2.setInt(19,h.getQtymar());
					ps2.setInt(20,h.getQtyapr());
					ps2.setInt(21,h.getQtymay());
					ps2.setInt(22,h.getQtyjun());
					ps2.setInt(23,h.getQtyjul());
					ps2.setInt(24,h.getQtyaug());
					ps2.setInt(25,h.getQtysep());
					ps2.setFloat(26,h.getValoct());
					ps2.setFloat(27,h.getValnov());
					ps2.setFloat(28,h.getValdec());
					ps2.setFloat(29,h.getValjan());
					ps2.setFloat(30,h.getValfeb());
					ps2.setFloat(31,h.getValmar());
					ps2.setFloat(32,h.getValapr());
					ps2.setFloat(33,h.getValmay());
					ps2.setFloat(34,h.getValjun());
					ps2.setFloat(35,h.getValjul());
					ps2.setFloat(36,h.getValaug());
					ps2.setFloat(37,h.getValsep());
					ps2.setInt(38,h.getFqtyoct());
					ps2.setInt(39,h.getFqtynov());
					ps2.setInt(40,h.getFqtydec());
					ps2.setInt(41,h.getFqtyjan());
					ps2.setInt(42,h.getFqtyfeb());
					ps2.setInt(43,h.getFqtymar());
					ps2.setInt(44,h.getFqtyapr());
					ps2.setInt(45,h.getFqtymay());
					ps2.setInt(46,h.getFqtyjun());
					ps2.setInt(47,h.getFqtyjul());
					ps2.setInt(48,h.getFqtyaug());
					ps2.setInt(49,h.getFqtysep());
					ps2.setFloat(50,h.getFvaloct());
					ps2.setFloat(51,h.getFvalnov());
					ps2.setFloat(52,h.getFvaldec());
					ps2.setFloat(53,h.getFvaljan());
					ps2.setFloat(54,h.getFvalfeb());
					ps2.setFloat(55,h.getFvalmar());
					ps2.setFloat(56,h.getFvalapr());
					ps2.setFloat(57,h.getFvalmay());
					ps2.setFloat(58,h.getFvaljun());
					ps2.setFloat(59,h.getFvaljul());
					ps2.setFloat(60,h.getFvalaug());
					ps2.setFloat(61,h.getFvalsep());
					ps2.setInt(62,h.getEqtyoct());
					ps2.setInt(63,h.getEqtynov());
					ps2.setInt(64,h.getEqtydec());
					ps2.setInt(65,h.getEqtyjan());
					ps2.setInt(66,h.getEqtyfeb());
					ps2.setInt(67,h.getEqtymar());
					ps2.setInt(68,h.getEqtyapr());
					ps2.setInt(69,h.getEqtymay());
					ps2.setInt(70,h.getEqtyjun());
					ps2.setInt(71,h.getEqtyjul());
					ps2.setInt(72,h.getEqtyaug());
					ps2.setInt(73,h.getEqtysep());
					ps2.setFloat(74,h.getEvaloct());
					ps2.setFloat(75,h.getEvalnov());
					ps2.setFloat(76,h.getEvaldec());
					ps2.setFloat(77,h.getEvaljan());
					ps2.setFloat(78,h.getEvalfeb());
					ps2.setFloat(79,h.getEvalmar());
					ps2.setFloat(80,h.getEvalapr());
					ps2.setFloat(81,h.getEvalmay());
					ps2.setFloat(82,h.getEvaljun());
					ps2.setFloat(83,h.getEvaljul());
					ps2.setFloat(84,h.getEvalaug());
					ps2.setFloat(85,h.getEvalsep());
					ps2.setInt(86,h.getBqtyoct());
					ps2.setInt(87,h.getBqtynov());
					ps2.setInt(88,h.getBqtydec());
					ps2.setInt(89,h.getBqtyjan());
					ps2.setInt(90,h.getBqtyfeb());
					ps2.setInt(91,h.getBqtymar());
					ps2.setInt(92,h.getBqtyapr());
					ps2.setInt(93,h.getBqtymay());
					ps2.setInt(94,h.getBqtyjun());
					ps2.setInt(95,h.getBqtyjul());
					ps2.setInt(96,h.getBqtyaug());
					ps2.setInt(97,h.getBqtysep());
					ps2.setFloat(98,h.getBvaloct());
					ps2.setFloat(99,h.getBvalnov());
					ps2.setFloat(100,h.getBvaldec());
					ps2.setFloat(101,h.getBvaljan());
					ps2.setFloat(102,h.getBvalfeb());
					ps2.setFloat(103,h.getBvalmar());
					ps2.setFloat(104,h.getBvalapr());
					ps2.setFloat(105,h.getBvalmay());
					ps2.setFloat(106,h.getBvaljun());
					ps2.setFloat(107,h.getBvaljul());
					ps2.setFloat(108,h.getBvalaug());
					ps2.setFloat(109,h.getBvalsep());
					ps2.setInt(110,h.getRqtyoct());
					ps2.setInt(111,h.getRqtynov());
					ps2.setInt(112,h.getRqtydec());
					ps2.setInt(113,h.getRqtyjan());
					ps2.setInt(114,h.getRqtyfeb());
					ps2.setInt(115,h.getRqtymar());
					ps2.setInt(116,h.getRqtyapr());
					ps2.setInt(117,h.getRqtymay());
					ps2.setInt(118,h.getRqtyjun());
					ps2.setInt(119,h.getRqtyjul());
					ps2.setInt(120,h.getRqtyaug());
					ps2.setInt(121,h.getRqtysep());
					ps2.setFloat(122,h.getRvaloct());
					ps2.setFloat(123,h.getRvalnov());
					ps2.setFloat(124,h.getRvaldec());
					ps2.setFloat(125,h.getRvaljan());
					ps2.setFloat(126,h.getRvalfeb());
					ps2.setFloat(127,h.getRvalmar());
					ps2.setFloat(128,h.getRvalapr());
					ps2.setFloat(129,h.getRvalmay());
					ps2.setFloat(130,h.getRvaljun());
					ps2.setFloat(131,h.getRvaljul());
					ps2.setFloat(132,h.getRvalaug());
					ps2.setFloat(133,h.getRvalsep());
					ps2.setInt(134,h.getSqtyoct());
					ps2.setInt(135,h.getSqtynov());
					ps2.setInt(136,h.getSqtydec());
					ps2.setInt(137,h.getSqtyjan());
					ps2.setInt(138,h.getSqtyfeb());
					ps2.setInt(139,h.getSqtymar());
					ps2.setInt(140,h.getSqtyapr());
					ps2.setInt(141,h.getSqtymay());
					ps2.setInt(142,h.getSqtyjun());
					ps2.setInt(143,h.getSqtyjul());
					ps2.setInt(144,h.getSqtyaug());
					ps2.setInt(145,h.getSqtysep());
					ps2.setFloat(146,h.getSvaloct());
					ps2.setFloat(147,h.getSvalnov());
					ps2.setFloat(148,h.getSvaldec());
					ps2.setFloat(149,h.getSvaljan());
					ps2.setFloat(150,h.getSvalfeb());
					ps2.setFloat(151,h.getSvalmar());
					ps2.setFloat(152,h.getSvalapr());
					ps2.setFloat(153,h.getSvalmay());
					ps2.setFloat(154,h.getSvaljun());
					ps2.setFloat(155,h.getSvaljul());
					ps2.setFloat(156,h.getSvalaug());
					ps2.setFloat(157,h.getSvalsep());
					ps2.setInt(158,h.getPqtyoct());
					ps2.setInt(159,h.getPqtynov());
					ps2.setInt(160,h.getPqtydec());
					ps2.setInt(161,h.getPqtyjan());
					ps2.setInt(162,h.getPqtyfeb());
					ps2.setInt(163,h.getPqtymar());
					ps2.setInt(164,h.getPqtyapr());
					ps2.setInt(165,h.getPqtymay());
					ps2.setInt(166,h.getPqtyjun());
					ps2.setInt(167,h.getPqtyjul());
					ps2.setInt(168,h.getPqtyaug());
					ps2.setInt(169,h.getPqtysep());
					ps2.setFloat(170,h.getPvaloct());
					ps2.setFloat(171,h.getPvalnov());
					ps2.setFloat(172,h.getPvaldec());
					ps2.setFloat(173,h.getPvaljan());
					ps2.setFloat(174,h.getPvalfeb());
					ps2.setFloat(175,h.getPvalmar());
					ps2.setFloat(176,h.getPvalapr());
					ps2.setFloat(177,h.getPvalmay());
					ps2.setFloat(178,h.getPvaljun());
					ps2.setFloat(179,h.getPvaljul());
					ps2.setFloat(180,h.getPvalaug());
					ps2.setFloat(181,h.getPvalsep());
					ps2.setInt(182,h.getMkt_year());
					i= i + ps2.executeUpdate();
				}
				rs.close();
      	}  /// End of While Loop
				
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
			System.out.println("-------------Exception in SQLHQDetailDAO.update " + ex);
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
		     //        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLHQDetailDAO.Connection.close "+e);
			  }
		}
			
	return i;
	}


}

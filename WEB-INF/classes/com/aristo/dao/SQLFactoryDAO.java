package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.FactoryFormBean;

public class SQLFactoryDAO {

	
	public int updateFactory(List inv,Connection con)
	{
		    int i=0;
		    FactoryFormBean ifb;
		    PreparedStatement ps = null;
		    PreparedStatement ps1 = null;
		    PreparedStatement ps11 = null;
		    PreparedStatement ps111 = null;
		    PreparedStatement ps2 = null;
		    ResultSet rs1 =null;
		    ResultSet rs11 =null;
//		    ResultSet rs =null;
			try 
			{
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            tblnm="f_trans";
            tblnm1="f_branch";
            tblnm2="f_product";

 
            
//            String query1 ="select documentno from "+tblnm+" where documentno=? and invoiceno=? ";
//            ps1 = con.prepareStatement(query1);

            String query1 ="select depo_code from "+tblnm1+" where brn_id=? ";
            ps1 = con.prepareStatement(query1);

            String query11 ="select pcode,division from "+tblnm2+" where f_pcode=? ";
            ps11 = con.prepareStatement(query11);
            
            String query111 ="insert into "+tblnm2+" (f_pcode,f_pname) values (?,?)";
            ps111 = con.prepareStatement(query111);
            
            String query="delete from "+tblnm+" where documentno=? and invoiceno=? AND locationid=?";
            ps = con.prepareStatement(query);
            
/*			String query ="update "+tblnm+" set preparationdate=?,preparationhours=?," +
			"preparationmin=?,despatchdate=?,despatchhours=?,despatchmin=?,transporteid=?,vehicleno=?," +
			"freightstatus=?,dcdate=?,dctype=?,foodyn=?,medicineyn=?,branchid=?,lrno=?,lrdate=?,formno=?," +
			"comminvoiceyn=?,comminvoiceno=?,remarks=?,locationid=?,companyid=?,salestaxpercent=?,othertaxpercent=?," +
			"boxes=?,weight=?,value=?,controlsampleyn=?,roadpermno=?,exported=?,productid=?,unitid=?,unit=?," +
			"batchno=?,batchsize=?,mfgdate=?,expdate=?,lotid=?,percaseqty=?,noofcase=?,quantity=?,detailremarks=?," +
			"arno=?,grossweight=?,slipno=?,percaselooseqty=?,noofloosecase=?,loosequantity=?,totalqty=?,mrp=?," +
			"stockistinclusiveexcise=?,stockistexclusiveexcise=?,invvalue=?,discountamt=?,discountqty=?,freeqty=?," +
			"mrpinclusiveexcise=?,mrpexclusiveexcise=?,retailinclexcise=?,retailexclexcise=?,retaildiscount=?," +
			"comminclexcise=?,commenclexcise=?,excisepercent=?,abatementpercent=?,assessablevalue=?," +
			"totalassessablevalue=?,totalexciseduty=?,totalquantity=?,abatementamount=?,mrprateperunit=?," +
			"dci_prl_free_qty=?,invoicecessrate=?,totalinvoicecesspay=?,invpercent=?,cesspercent=?,location=?," +
			"company=?,productname=? where documentno=? and invoiceno=? ";   
	
			ps = con.prepareStatement(query);  
*/               
			String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
			" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
			" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,curdate())"; 
			ps2 = con.prepareStatement(query2);
			con.setAutoCommit(false);
			Date javadt = new Date();
          	Iterator it = inv.iterator();
          	
          	String docNo="";
          	int invno=0;
          	boolean del=true;
       	    while(it.hasNext()) 
           	 {
       			ifb = (FactoryFormBean) it.next();
       			
//       			if(!docNo.equals(ifb.getDocumentno()) && invno!=ifb.getInvoiceno())
         		if(!docNo.equals(ifb.getDocumentno()) )
       			{
           			docNo=ifb.getDocumentno();
           			invno=ifb.getInvoiceno();
           			del=true;
       			}
       			
       			
       			if (del)
       			{
       			ps.setString(1,ifb.getDocumentno()); 
       			ps.setInt(2,ifb.getInvoiceno());
       			ps.setString(3,ifb.getLocationid());
				i=ps.executeUpdate();
				del=false;
       			}
       			
       			
/*				if (rs.next())
				{ 
				javadt = ifb.getPreparationdate();
				ps.setDate(1,new java.sql.Date (javadt.getTime()));
       			
       			ps.setInt(2,ifb.getPreparationhours());
       			ps.setInt(3,ifb.getPreparationmin());

				javadt = ifb.getDespatchdate();
				ps.setDate(4,new java.sql.Date (javadt.getTime()));

       			ps.setInt(5,ifb.getDespatchhours());
       			ps.setInt(6,ifb.getDespatchmin());
       			ps.setString(7,ifb.getTransporteid());
       			ps.setString(8,ifb.getVehicleno());
       			ps.setString(9,ifb.getFreightstatus());
       			
				javadt = ifb.getDcdate();
				ps.setDate(10,new java.sql.Date (javadt.getTime()));

       			ps.setInt(11,ifb.getDctype());
       			ps.setInt(12,ifb.getFoodyn());
       			ps.setInt(13,ifb.getMedicineyn());
       			ps.setString(14,ifb.getBranchid());
       			ps.setInt(15,ifb.getLrno());

       			javadt = ifb.getLrdate();
				ps.setDate(16,new java.sql.Date (javadt.getTime()));

       			ps.setInt(17,ifb.getFormno());
       			ps.setInt(18,ifb.getComminvoiceyn());
       			ps.setInt(19,ifb.getComminvoiceno());
       			ps.setString(20,ifb.getRemarks());
       			ps.setString(21,ifb.getLocationid());
       			ps.setString(22,ifb.getCompanyid());
       			ps.setInt(23,ifb.getSalestaxpercent());
       			ps.setInt(24,ifb.getOthertaxpercent());
       			ps.setInt(25,ifb.getBoxes());
       			ps.setDouble(26,ifb.getWeight());
       			ps.setDouble(27,ifb.getValue());
       			ps.setString(28,ifb.getControlsampleyn());
       			ps.setInt(29,ifb.getRoadpermno());
       			ps.setString(30,ifb.getExported());
       			ps.setString(31,ifb.getProductid());
       			ps.setString(32,ifb.getUnitid());
       			ps.setInt(33,ifb.getUnit());
       			ps.setString(34,ifb.getBatchno());
       			ps.setInt(35,ifb.getBatchsize());
       			
       			javadt = ifb.getMfgdate();
				ps.setDate(36,new java.sql.Date (javadt.getTime()));

				javadt = ifb.getExpdate();
				ps.setDate(37,new java.sql.Date (javadt.getTime()));
       			
       			ps.setInt(38,ifb.getLotid());
       			ps.setInt(39,ifb.getPercaseqty());
       			ps.setInt(40,ifb.getNoofcase());
       			ps.setInt(41,ifb.getQuantity());
       			ps.setString(42,ifb.getDetailremarks());
       			ps.setString(43,ifb.getArno());
       			ps.setDouble(44,ifb.getGrossweight());
       			ps.setString(45,ifb.getSlipno());
       			ps.setInt(46,ifb.getPercaselooseqty());
       			ps.setInt(47,ifb.getNoofloosecase());
       			ps.setInt(48,ifb.getLoosequantity());
       			ps.setInt(49,ifb.getTotalqty());
       			ps.setDouble(50,ifb.getMrp());
       			ps.setDouble(51,ifb.getStockistinclusiveexcise());
       			ps.setDouble(52,ifb.getStockistexclusiveexcise());
       			ps.setDouble(53,ifb.getInvvalue());
       			ps.setDouble(54,ifb.getDiscountamt());
       			ps.setInt(55,ifb.getDiscountqty());
       			ps.setInt(56,ifb.getFreeqty());
       			ps.setDouble(57,ifb.getMrpinclusiveexcise());
       			ps.setDouble(58,ifb.getMrpexclusiveexcise());
       			ps.setDouble(59,ifb.getRetailinclexcise());
       			ps.setDouble(60,ifb.getRetailexclexcise());
       			ps.setDouble(61,ifb.getRetaildiscount());
       			ps.setDouble(62,ifb.getComminclexcise());
       			ps.setDouble(63,ifb.getCommenclexcise());
       			ps.setDouble(64,ifb.getExcisepercent());
       			ps.setDouble(65,ifb.getAbatementpercent());
       			ps.setDouble(66,ifb.getAssessablevalue());
       			ps.setDouble(67,ifb.getTotalassessablevalue());
       			ps.setDouble(68,ifb.getTotalexciseduty());
       			ps.setInt(69,ifb.getTotalquantity());
       			ps.setDouble(70,ifb.getAbatementamount());
       			ps.setDouble(71,ifb.getMrprateperunit());
       			ps.setInt(72,ifb.getDci_prl_free_qty());
       			ps.setDouble(73,ifb.getInvoicecessrate());
       			ps.setDouble(74,ifb.getTotalinvoicecesspay());
       			ps.setInt(75,ifb.getInvpercent());
       			ps.setInt(76,ifb.getCesspercent());
       			ps.setString(77,ifb.getLocation());
       			ps.setString(78,ifb.getCompany());
       			ps.setString(79,ifb.getProductname());
       			ps.setString(80,ifb.getDocumentno());
       			ps.setInt(81,ifb.getInvoiceno());

       			i= i + ps.executeUpdate();
				}
				else
				{ 
*/       			ps2.setString(1,ifb.getDocumentno());
       			ps2.setInt(2,ifb.getInvoiceno());

				javadt = ifb.getPreparationdate();
				ps2.setDate(3,new java.sql.Date (javadt.getTime()));
       			
       			ps2.setInt(4,ifb.getPreparationhours());
       			ps2.setInt(5,ifb.getPreparationmin());

				javadt = ifb.getDespatchdate();
				ps2.setDate(6,new java.sql.Date (javadt.getTime()));

       			ps2.setInt(7,ifb.getDespatchhours());
       			ps2.setInt(8,ifb.getDespatchmin());
       			ps2.setString(9,ifb.getTransporteid());
       			ps2.setString(10,ifb.getVehicleno());
       			ps2.setString(11,ifb.getFreightstatus());
       			
				javadt = ifb.getDcdate();
				ps2.setDate(12,new java.sql.Date (javadt.getTime()));

       			ps2.setInt(13,ifb.getDctype());
       			ps2.setInt(14,ifb.getFoodyn());
       			ps2.setInt(15,ifb.getMedicineyn());
       			ps2.setString(16,ifb.getBranchid());
       			ps2.setString(17,ifb.getLrno());
       			
       			if (ifb.getLrdate()!=null)
       			{
       			javadt = ifb.getLrdate();
       			ps2.setDate(18,new java.sql.Date (javadt.getTime()));
       			}
       			else 
       			ps2.setDate(18,null);

       			ps2.setInt(19,ifb.getFormno());
       			ps2.setInt(20,ifb.getComminvoiceyn());
       			ps2.setInt(21,ifb.getComminvoiceno());
       			ps2.setString(22,ifb.getRemarks());
       			ps2.setString(23,ifb.getLocationid());
       			ps2.setString(24,ifb.getCompanyid());
       			ps2.setInt(25,ifb.getSalestaxpercent());
       			ps2.setInt(26,ifb.getOthertaxpercent());
       			ps2.setInt(27,ifb.getBoxes());
       			ps2.setDouble(28,ifb.getWeight());
       			ps2.setDouble(29,ifb.getValue());
       			ps2.setString(30,ifb.getControlsampleyn());
       			ps2.setString(31,ifb.getRoadpermno());
       			ps2.setString(32,ifb.getExported());
       			ps2.setString(33,ifb.getProductid());
       			ps2.setString(34,ifb.getUnitid());
       			ps2.setInt(35,ifb.getUnit());
       			ps2.setString(36,ifb.getBatchno());
       			ps2.setInt(37,ifb.getBatchsize());
       			
       			javadt = ifb.getMfgdate();
				ps2.setDate(38,new java.sql.Date (javadt.getTime()));

				javadt = ifb.getExpdate();
				ps2.setDate(39,new java.sql.Date (javadt.getTime()));
       			
       			ps2.setString(40,ifb.getLotid());
       			ps2.setInt(41,ifb.getPercaseqty());
       			ps2.setInt(42,ifb.getNoofcase());
       			ps2.setInt(43,ifb.getQuantity());
       			ps2.setString(44,ifb.getDetailremarks());
       			ps2.setString(45,ifb.getArno());
       			ps2.setDouble(46,ifb.getGrossweight());
       			ps2.setString(47,ifb.getSlipno());
       			ps2.setInt(48,ifb.getPercaselooseqty());
       			ps2.setInt(49,ifb.getNoofloosecase());
       			ps2.setInt(50,ifb.getLoosequantity());
       			ps2.setInt(51,ifb.getTotalqty());
       			ps2.setDouble(52,ifb.getMrp());
       			ps2.setDouble(53,ifb.getStockistinclusiveexcise());
       			ps2.setDouble(54,ifb.getStockistexclusiveexcise());
       			ps2.setDouble(55,ifb.getInvvalue());
       			ps2.setDouble(56,ifb.getDiscountamt());
       			ps2.setInt(57,ifb.getDiscountqty());
       			ps2.setInt(58,ifb.getFreeqty());
       			ps2.setDouble(59,ifb.getMrpinclusiveexcise());
       			ps2.setDouble(60,ifb.getMrpexclusiveexcise());
       			ps2.setDouble(61,ifb.getRetailinclexcise());
       			ps2.setDouble(62,ifb.getRetailexclexcise());
       			ps2.setDouble(63,ifb.getRetaildiscount());
       			ps2.setDouble(64,ifb.getComminclexcise());
       			ps2.setDouble(65,ifb.getCommenclexcise());
       			ps2.setDouble(66,ifb.getExcisepercent());
       			ps2.setDouble(67,ifb.getAbatementpercent());
       			ps2.setDouble(68,ifb.getAssessablevalue());
       			ps2.setDouble(69,ifb.getTotalassessablevalue());
       			ps2.setDouble(70,ifb.getTotalexciseduty());
       			ps2.setInt(71,ifb.getTotalquantity());
       			ps2.setDouble(72,ifb.getAbatementamount());
       			ps2.setDouble(73,ifb.getMrprateperunit());
       			ps2.setInt(74,ifb.getDci_prl_free_qty());
       			ps2.setDouble(75,ifb.getInvoicecessrate());
       			ps2.setDouble(76,ifb.getTotalinvoicecesspay());
       			ps2.setInt(77,ifb.getInvpercent());
       			ps2.setInt(78,ifb.getCesspercent());
       			ps2.setString(79,ifb.getLocation());
       			ps2.setString(80,ifb.getCompany());
       			ps2.setString(81,ifb.getProductname());
       			
       			ps1.setString(1, ifb.getBranchid());
       			rs1=ps1.executeQuery();
       			if (rs1.next())
       			  ps2.setInt(82,rs1.getInt(1));
       			else
          		  ps2.setInt(82,0);
       				
       			ps11.setString(1, ifb.getProductid());
       			rs11=ps11.executeQuery();
       			if (rs11.next())
       			{
       			  ps2.setInt(83,rs11.getInt(1));
       			  ps2.setString(84,rs11.getString(2));
       			}
       			else
       			{
          		  ps2.setInt(83,0);
       			  ps2.setString(84,"");
       			  ps111.setString(1, ifb.getProductid());
       			  ps111.setString(2, ifb.getProductname());
       			  ps111.executeUpdate();
       			}

				i= i + ps2.executeUpdate();
				
		 }	
		       	con.commit();
		        con.setAutoCommit(true);

       			ps.close();
       			ps1.close();
       			ps2.close();
       			ps=null;
       			ps1=null;
       			ps2=null; 
//       			rs=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in (SQLFACTORYDAO).update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
//			             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLFACTORYDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
	} 
}

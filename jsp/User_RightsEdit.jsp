<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>"/> 

<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

<SCRIPT LANGUAGE="JavaScript">

function checkAll(checkbox2)
{
for (i = 0; i < checkbox2.length; i++)
	checkbox2[i].checked = true ;
}

function uncheckAll(checkbox2)
{
for (i = 0; i < checkbox2.length; i++)
	checkbox2[i].checked = false ;
}

</script>

<%

String a = (String) request.getAttribute("uname");
String typ = (String) request.getAttribute("typ");
%>




</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
    <html:form action="UpdateUserRights.do?actionRequested=UpdateUserRights">
        <p>&nbsp;</p>
						<input type="hidden" name="tp" value="<%=typ%>"/>
            <table border="0"  cellpadding="0" cellspacing="0" class="tablereg">
              <th colspan="4">    User Rights Update <br/> Login ID: <%=a%>   </th>
		      <tr>
                <td colspan="4"><strong>
				<br/>
				<input type="button" value="Select All" onClick="checkAll(checkbox2)">
				<input type="button" value="Clear All" onClick="uncheckAll(checkbox2)">
				<br/>				
				<br/>				
				MASTERS</strong><hr></td>
              </tr>
		      <tr>
                <td width="33">&nbsp;</td>
                <td width="14"><html:multibox property="checkbox2" value="MS01"/> </td>
                <td>Account Master</td>
                <td width="4">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS02"/></td>
                <td>Product Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS03"/></td>
                <td>Group Master (Sales)</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS04"/></td>
                <td>Group Master (MKT)</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS05"/></td>
                <td>Area Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS06"/></td>
                <td>Region Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS07"/></td>
                <td>H.Q. Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS08"/></td>
                <td>Field Staff Master</td>
                <td>&nbsp;</td>
              </tr>

              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">STOCKIEST<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST01"/></td>
                <td>Selective Stockiest Product Wise Credit Note Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST02"/> </td>
                <td>Selective Stockiest Prod Wise Gross/Credit/Net Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST03"/> </td>
                <td>Stockiest Wise Rs.-Wise Trend Gross/Credit/Net</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST04"/> </td>
                <td>Selective Stockiest Wise Product Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST05"/> </td>
                <td>Selective Stockiest Wise Group Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST06"/> </td>
                <td>Stockiest Rupees Wise Salable/Exp/Brk</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST07"/> </td>
                <td>Selective Stockiest Group Wise Credit Note Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST08"/> </td>
                <td>Selective Stockiest Group Wise Gross/Credit/Net Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST09"/> </td>
                <td>Selective Product Wise Stockiest Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="ST10"/> </td>
                <td>Selective Product Stockiest Wise Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">BRANCH MIS<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI01"/> </td>
                <td>Product Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI02"/> </td>
                <td>Group Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI03"/> </td>
                <td>Rupeeswise Salable/Expiry/Breakage</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI04"/> </td>
                <td>Selective HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI05"/> </td>
                <td>Rupeeswise Gross/Credit/Net Sale</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI06"/> </td>
                <td>Selective Product - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI07"/> </td>
                <td>Selective Group - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BI08"/> </td>
                <td>Selective Product - Sales Trend (Gross)</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">BRANCH MKT<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM01"/> </td>
                <td>Selective Product-Rupeewise Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM02"/> </td>
                <td>Selective Group-Rupeewise    Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM03"/> </td>
                <td>Rupeewise Target/Sale/Ach/Growth/PMR</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM04"/> </td>
                <td>Selective HQ/Reg/Are/Brn/Prd Wise Detail</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM05"/> </td>
                <td>Product Wise This Year/Last Year</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM06"/> </td>
                <td>Target Allocation</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM07"/> </td>
                <td>Product Wise Contribution %</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM08"/> </td>
                <td>Selective Product - Sales/Target Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM09"/> </td>
                <td>Product Wise PMR in Units</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM10"/> </td>
                <td>Product Wise Target/Sale Comparison</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM11"/> </td>
                <td>Rupees Wise Sales Analysis Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM12"/> </td>
                <td>Selective Group - Rupees Wise Sales    Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM13"/> </td>
                <td>Selective Prod- Unit/ValueWise Sales    Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BM14"/> </td>
                <td>Branch Wise -Rupees Wise Net Sales Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">INVENTORY<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY01"/> </td>
                <td>Stock and Sales Statement</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY02"/> </td>
                <td>Item Wise Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY03"/> </td>
                <td>Sales Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY04"/> </td>
                <td>Near Expiry List</td>
                <td>&nbsp;</td>
              </tr>
			  <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY05"/> </td>
                <td>Stock Analysis as on</td>
                <td>&nbsp;</td>
              </tr>              
			  <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IY06"/> </td>
                <td>Factory Wise Stock Position as on</td>
                <td>&nbsp;</td>
              </tr>              
			  
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">HO MIS<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI01"/> </td>
                <td width="434">HO-Prod Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI02"/> </td>
                <td>HO-Group Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI03"/> </td>
                <td>HO-Rupeeswise Salable/Expiry/Breakage</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI04"/> </td>
                <td>HO-Rupeeswise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI05"/> </td>
                <td>HO-Selective Product Branch Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI06"/> </td>
                <td>HO-Selective Group Branch Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI07"/> </td>
                <td>HO-Selective Prod- Sales Trend Gross</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI08"/> </td>
                <td>HO-Branch Wise -Rupees Wise Sales Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI09"/> </td>
                <td>HO-Selective Product HQ Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI10"/> </td>
                <td>HO-HQ Wise Rupee Value (Net-Trend)</td>
                <td>&nbsp;</td>
              </tr>
			  <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI11"/> </td>
                <td>HO-Selective Product HQ-Wise Rupee Value (Net-Trend)</td>
                <td>&nbsp;</td>
              </tr>
			  <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI12"/> </td>
                <td>HO-Selective Group HQ-Wise Rupee Value (Net-Trend)</td>
                <td>&nbsp;</td>
              </tr>

			  <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HI13"/> </td>
                <td>HO-Selective Group Stockiest wise Trend</td>
                <td>&nbsp;</td>
              </tr>



              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">HO MARKETING<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM01"/> </td>
                <td>HO-Selective Prod-Rupeewise Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM02"/> </td>
                <td>HO-Selective Group-Rupeewise Target/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM03"/> </td>
                <td>HO-Rupeewise Target/Sale/Ach/Growth/PMR</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM04"/> </td>
                <td>HO-Selective Product - Sales/Target Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM05"/> </td>
                <td>HO-Branch Wise-Rupees Wise Net Sales Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HM06"/> </td>
                <td>HO-Groupwise/Rupeewise Target/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">HO INVENTORY<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HY01"/> </td>
                <td>HO-Stock and Sales Statement</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HY02"/> </td>
                <td>HO-Item Wise Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HY03"/> </td>
                <td>HO-Sales Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HY04"/> </td>
                <td>HO-Near Expiry List</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HY05"/> </td>
                <td>HO-Stock Analysis as on</td>
                <td>&nbsp;</td>
              </tr>



              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
			  
              <tr>
                <td colspan="4">SAMPLE<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM01"/> </td>
                <td>Stock & Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM02"/> </td>
                <td>Stock Ledger</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM03"/> </td>
                <td>Near Expiry List</td>
                <td>&nbsp;</td>
              </tr>			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM04"/> </td>
                <td>Item Wise Sale</td>
                <td>&nbsp;</td>
              </tr>			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM05"/> </td>
                <td>Party Wise/Item Wise</td>
                <td>&nbsp;</td>
              </tr>			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM06"/> </td>
                <td>Transfer Register</td>
                <td>&nbsp;</td>
              </tr>			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM07"/> </td>
                <td>Sample Register</td>
                <td>&nbsp;</td>
              </tr>			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="SM08"/> </td>
                <td>Pending Report</td>
                <td>&nbsp;</td>
              </tr>	
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
			  		  
              <tr>
                <td colspan="4">CENTRAL<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS01"/> </td>
                <td>Dispatch Register</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS02"/> </td>
                <td>Daily Dispatch Report</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS03"/> </td>
                <td>Goods in Transit</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS04"/> </td>
                <td>Dispatch Summery</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS05"/> </td>
                <td>Product Wise Dispatch</td>
                <td>&nbsp;</td>
              </tr>
			  <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>			  

              <tr>
                <td colspan="4">FACTORY<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="FT01"/> </td>
                <td>Dispatch Register</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="FT02"/> </td>
                <td>Daily Dispatch Report</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="FT03"/> </td>
                <td>Goods in Transit</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="FT04"/> </td>
                <td>Dispatch Summery</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="FT05"/> </td>
                <td>Product Wise Dispatch</td>
                <td>&nbsp;</td>
              </tr>
			  <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>			  

              <tr>
                <td colspan="4">BILLING<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BL01"/> </td>
                <td>Daily Entry</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BL02"/> </td>
                <td>Daily Report</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BL03"/> </td>
                <td>Budget Entry</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="BL04"/> </td>
                <td>Collection Entry</td>
                <td>&nbsp;</td>
              </tr>
			   <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>			  
			  			  
			  
			  
              <tr>
                <td colspan="4">TOOLS<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL01"/> </td>
                <td>Updation Status</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL02"/> </td>
                <td>Change Password</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL03"/> </td>
                <td>Check List (Item Code)</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL04"/> </td>
                <td>Switch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL05"/> </td>
                <td>Sample Updation</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL06"/> </td>
                <td>CheckList (HQT/STK/TAR)</td>
                <td>&nbsp;</td>
              </tr>

			  
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr>
                <td colspan="4"><div align="center">
				<html:submit value="Update" styleClass="button"/>
                </div> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
			
	<p>&nbsp;</p>
   </html:form>
  </body>
 <jsp:include page="<%=foot%>"/> 
</html:html>
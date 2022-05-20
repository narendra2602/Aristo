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
            <table border="0" cellpadding="0" cellspacing="0" class="tablereg">
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
                <td width="434">Account Master</td>
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
                <td><html:multibox property="checkbox2" value="HST01"/></td>
                <td>Selective Stockiest Product Wise Credit Note Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST02"/> </td>
                <td>Selective Stockiest Prod Wise Gross/Credit/Net Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST03"/> </td>
                <td>Stockiest Wise Rs.-Wise Trend Gross/Credit/Net</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST04"/> </td>
                <td>Selective Stockiest Wise Product Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST05"/> </td>
                <td>Selective Stockiest Wise Group Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST06"/> </td>
                <td>Stockiest Rupees Wise Salable/Exp/Brk</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST07"/> </td>
                <td>Selective Stockiest Group Wise Credit Note Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST08"/> </td>
                <td>Selective Stockiest Group Wise Gross/Credit/Net Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST09"/> </td>
                <td>Selective Product Wise Stockiest Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HST10"/> </td>
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
                <td><html:multibox property="checkbox2" value="HBI01"/> </td>
                <td>Product Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI02"/> </td>
                <td>Group Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI03"/> </td>
                <td>Rupeeswise Salable/Expiry/Breakage</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI04"/> </td>
                <td>Selective HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
             
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI06"/> </td>
                <td>Selective Product - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI07"/> </td>
                <td>Selective Group - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBI08"/> </td>
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
                <td><html:multibox property="checkbox2" value="HBM04"/> </td>
                <td>Selective HQ/Reg/Are/Brn/Prd Wise Detail</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM05"/> </td>
                <td>Product Wise This Year/Last Year</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM06"/> </td>
                <td>Target Allocation</td>
                <td>&nbsp;</td>
              </tr>
             
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM08"/> </td>
                <td>Selective Product - Sales/Target Trend</td>
                <td>&nbsp;</td>
              </tr>
               
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM10"/> </td>
                <td>Product Wise Target/Sale Comparison</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM11"/> </td>
                <td>Rupees Wise Sales Analysis Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM12"/> </td>
                <td>Selective Group - Rupees Wise Sales    Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="HBM13"/> </td>
                <td>Selective Prod- Unit/ValueWise Sales    Analysis</td>
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
                <td><html:multibox property="checkbox2" value="TL02"/> </td>
                <td>Change Password</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL04"/> </td>
                <td>Switch</td>
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
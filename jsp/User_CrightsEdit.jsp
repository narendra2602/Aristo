<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

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
                <td>Branch Master</td>
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
                <td>Group Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS04"/></td>
                <td>Batch Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS05"/></td>
                <td>Transporter Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS06"/></td>
                <td>Destination Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="MS07"/></td>
                <td>Road Permit Master</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>

              <tr>
                <td colspan="4">TRANSACTION<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR01"/></td>
                <td>Inward Invoice</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR02"/> </td>
                <td>Outward Invoice</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR03"/> </td>
                <td>Sales Invoice</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR04"/> </td>
                <td>Nepal Invoice</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR05"/> </td>
                <td>Order Entry</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR06"/> </td>
                <td>Delivery Challan</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TR07"/> </td>
                <td>Packing List</td>
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
                <td><html:multibox property="checkbox2" value="IN01"/> </td>
                <td>Stock & Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN02"/> </td>
                <td>Near Expiry List</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN03"/> </td>
                <td>Non-Moving Items</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN04"/> </td>
                <td>Product/Batch Wise Stock</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN05"/> </td>
                <td>Stock Ledger</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN06"/> </td>
                <td>Location Wise Reciept</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN07"/> </td>
                <td>Inward Register</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN08"/> </td>
                <td>Inward Register Branch Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN09"/> </td>
                <td>Date Wise Stock Value</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="IN10"/> </td>
                <td>Allocation V/S Dispatch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>

              <tr>
                <td colspan="4">DISPATCH<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="DS01"/> </td>
                <td>Transfer Register</td>
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
                <td>Location Wise Dispatch</td>
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
                <td colspan="4">TOOLS<hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL01"/> </td>
                <td>Upload XML Files</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL02"/> </td>
                <td>Upload Status</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL03"/> </td>
                <td>Change Password</td>
                <td>&nbsp;</td>
              </tr>
			  
              <tr>
                <td>&nbsp;</td>
                <td><html:multibox property="checkbox2" value="TL04"/> </td>
                <td>Switch (MF/TF/Genetica)</td>
                <td>&nbsp;</td>
              </tr>
			  
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr>
                <td colspan="4"><div align="center">
				<html:submit value="Submit" styleClass="button"/>
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
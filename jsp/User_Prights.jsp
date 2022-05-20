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


</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
    <html:form action="addUserRights.do?actionRequested=addUserRights">
        <p>&nbsp;</p>
		
		<%
		int uid= (Integer) request.getAttribute("uid");
		String typ= (String) request.getAttribute("typ");
		String tp1= (String) request.getAttribute("tp");
		%>
		
		<input type="hidden" name="id" value="<%=uid%>"/>
		<input type="hidden" name="tp" value="<%=typ%>"/>				
		<input type="hidden" name="tp1" value="<%=tp1%>"/>				

		
            <table border="0"  cellpadding="0" cellspacing="0" class="tablereg">
              <th colspan="4">    User Rights      </th>
		      <tr>
                <td colspan="4"><strong>
				<br/>
				<input type="button" value="Select All" onClick="checkAll(checkbox2)">
				<input type="button" value="Clear All" onClick="uncheckAll(checkbox2)">
				<br/>
                </strong>

          		<br/>
                STOCKIEST <hr></td>
              </tr>
              <tr>
                <td width="33">&nbsp;</td>
                <td width="14"><html:checkbox property="checkbox2" value="ST06"/>                </td>
                <td width="434">Stockiest Rupees Wise Salable/Exp/Brk</td>
                <td width="4">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="ST09"/>                </td>
                <td>Selective Product Wise Stockiest Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="ST10"/>                </td>
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
                <td colspan="4">BRANCH MIS
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI01"/>                </td>
                <td>Product Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI02"/>                </td>
                <td>Group Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI03"/>                </td>
                <td>Rupeeswise Salable/Expiry/Breakage</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI04"/>                </td>
                <td>Selective HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI05"/>                </td>
                <td>Rupeeswise Gross/Credit/Net Sale</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI06"/>                </td>
                <td>Selective Product - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI07"/>                </td>
                <td>Selective Group - HQ/Region/Area/Branch</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BI08"/>                </td>
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
                <td colspan="4">BRANCH MKT
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM01"/>                </td>
                <td>Selective Product-Rupeewise Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM02"/>                </td>
                <td>Selective Group-Rupeewise Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM04"/>                </td>
                <td>Selective HQ/Reg/Are/Brn/Prd Wise Detail</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM05"/>                </td>
                <td>Product Wise This Year/Last Year</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM06"/>                </td>
                <td>Target Allocation</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM07"/>                </td>
                <td>Product Wise Contribution %</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM08"/>                </td>
                <td>Selective Product - Sales/Target Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM09"/>                </td>
                <td>Product Wise PMR in Units</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM10"/>                </td>
                <td>Product Wise Target/Sale Comparison</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM12"/>                </td>
                <td>Selective Group - Rupees Wise Sales  Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="BM13"/>                </td>
                <td>Selective Prod- Unit/ValueWise Sales  Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">INVENTORY
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="IY01"/>                </td>
                <td>Stock and Sales Statement</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="IY02"/>                </td>
                <td>Item Wise Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="IY03"/>                </td>
                <td>Sales Analysis</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">HO MIS
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI01"/>                </td>
                <td>HO-Prod Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI02"/>                </td>
                <td>HO-Group Wise Gross/Credit/Net Sales</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI03"/>                </td>
                <td>HO-Rupeeswise Salable/Expiry/Breakage</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI05"/>                </td>
                <td>HO-Selective Product Branch Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI06"/>                </td>
                <td>HO-Selective Group Branch Wise</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HI07"/>                </td>
                <td>HO-Selective Prod- Sales Trend Gross</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">HO MARKETING
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HM01"/>                </td>
                <td>HO-Selective Prod-Rupeewise Tgt/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HM02"/>                </td>
                <td>HO-Selective Group-Rupeewise Target/Sale/Ach/Gr/Pmr</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="HM04"/>                </td>
                <td>HO-Selective Product - Sales/Target Trend</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4">CENTRAL
                  <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="DS01"/>                </td>
                <td>Dispatch Register</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="DS02"/>                </td>
                <td>Daily Dispatch Report</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="DS03"/>                </td>
                <td>Goods in Transit</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="DS04"/>                </td>
                <td>Dispatch Summery</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="DS05"/>                </td>
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
                <td colspan="4">TOOLS
                    <hr></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="TL01"/>                </td>
                <td>Updation Status</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="TL02"/>                </td>
                <td>Change Password</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html:checkbox property="checkbox2" value="TL04"/>                </td>
                <td>Switch</td>
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
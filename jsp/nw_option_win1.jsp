<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");   
%>
 <jsp:include page="<%=toop%>"/> 
<jsp:include page="head-scripts.jsp" />

<head>
 
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link rel="stylesheet" type="text/css" href="css/<%=css1%>"  />

<script language="javascript"> 

document.getElementById("unitSelect").disabled=true;

function disableRadio() 
{
	document.getElementById("unitSelect").disabled=true;
	document.getElementById("valueSelect").checked=true;
} 

function enableRadio() 
{
	document.getElementById("unitSelect").disabled=false;
	document.getElementById("unitSelect").checked=true;
} 

</script>

</head>

<body id="target" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

  <html:form action="NWListRepo1.do?actionRequested=NWListRepo1">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="7">Product/Group Wise Report</th>
                  


                  <tr>
                    <td>&nbsp;</td>
                    <td><html:radio property="rep_type"  value="1" onclick="enableRadio();"/>Productwise </td>
                    <td><html:radio property="rep_type"  value="2" onclick="disableRadio();"/>Groupwise </td>
                    <td>&nbsp;</td>
			      </tr>


				  
                  <tr>
                    <td>&nbsp;</td>
                    <td><html:radio property="uv"  styleId="unitSelect" value="1"/>Unit </td>
                    <td><html:radio property="uv"  styleId="valueSelect" value="2"/>Value </td>
                    <td>&nbsp;</td>
			      </tr>

				  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>


                  <tr>

					<td colspan="4" style="padding-left: 68px;text-align: left; width: 25%;">
						 Report Option						 
						 <html:select property="sale_type"  style="width: 220px;">
								<html:option value="1">Gross Sale</html:option>
								<html:option value="10">Saleable</html:option>
								<html:option value="11">Expiry</html:option>
								<html:option value="12">Breakage</html:option>
								<html:option value="3">Net Sale</html:option>
								<html:option value="4">Target</html:option>
								<html:option value="5">Lys Sales</html:option>
								<html:option value="6">Ach.</html:option>
								<html:option value="7">Gth.</html:option>
								<html:option value="8">PMR</html:option>
								<html:option value="2">PI Sale</html:option>
						</html:select>
					</td>

                  </tr>

                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

                  <tr>
				    <td>&nbsp;</td>
                    <td colspan="4" style="padding-left: 22px;text-align: left; width: 60%;">
				  From
                  <html:select property="smon" >
                        <html:option value="1">Oct</html:option>
                        <html:option value="2">Nov</html:option>
                        <html:option value="3">Dec</html:option>
                        <html:option value="4">Jan</html:option>
                        <html:option value="5">Feb</html:option>
                        <html:option value="6">Mar</html:option>
                        <html:option value="7">Apr</html:option>
                        <html:option value="8">May</html:option>
                        <html:option value="9">Jun</html:option>
                        <html:option value="10">Jul</html:option>
                        <html:option value="11">Aug</html:option>
                        <html:option value="12">Sep</html:option>
                </html:select>
				
				   To
				
                  <html:select property="emon">
                        <html:option value="1">Oct</html:option>
                        <html:option value="2">Nov</html:option>
                        <html:option value="3">Dec</html:option>
                        <html:option value="4">Jan</html:option>
                        <html:option value="5">Feb</html:option>
                        <html:option value="6">Mar</html:option>
                        <html:option value="7">Apr</html:option>
                        <html:option value="8">May</html:option>
                        <html:option value="9">Jun</html:option>
                        <html:option value="10">Jul</html:option>
                        <html:option value="11">Aug</html:option>
                        <html:option value="12">Sep</html:option>
                  </html:select> Mkt Year
				<html:select property="eyear">
		                <html:optionsCollection name="HORepo1Form" property="ylist" label="yname" value="yval" />
        		</html:select>	
					
					
					</td> 
                  </tr>
                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

 	            <tr>
					<td colspan="4" style="padding-left: 70px;text-align: left; width: 55%;"> Branch
						<html:select property="depo_code" value="0" styleId="mySelect">
						  <html:optionsCollection name="HORepo1Form" property="branchlist" label="dname" value="dcode" />
						</html:select>
					</td>
				  </tr>


                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

				  <td>&nbsp;</td>
				  <td>&nbsp;</td>
                    <td colspan="5" align="center">
                        <html:submit value="Submit" styleClass="button" styleId="loader4"/>
                      &nbsp;
                      <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/>
                    </div></td>
                  </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
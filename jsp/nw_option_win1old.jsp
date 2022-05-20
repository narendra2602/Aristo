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

</head>

<body id="target" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

  <html:form action="NWListRepo1.do?actionRequested=NWListRepo1">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="7">HO-Product Wise Gross/Credit/Net Sales</th>
                  
                 <tr>
                    <td>&nbsp;</td>
                    <td><html:radio property="uv"  value="1"/>Unit &nbsp;</td>
                    <td><html:radio property="uv"  value="2"/>Value &nbsp;&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
			      </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td><html:radio property="sale_type"  value="1"/>Gross</td>
                    <td><html:radio property="sale_type"  value="2"/>Credit &nbsp; </td>
                    <td><html:radio property="sale_type"  value="3"/>Net&nbsp;&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

				<tr> 
							  
                  <td><html:radio  property="sale_type" value="4" />Target &nbsp;</td>
                  <td><html:radio  property="sale_type" value="5" />Lys Sales &nbsp;</td>
				  <td><html:radio property="sale_type"  value="6" />Ach. &nbsp;</td>	
				  <td><html:radio property="sale_type"  value="7"/>Gth.  &nbsp; </td>				
				  <td><html:radio property="sale_type"  value="8"/>PMR &nbsp; </td>	
				  <td><html:radio property="sale_type"  value="9"/>SUR/DEF </td>	
				 
                </tr> 

                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

                  <tr>
				    <td>&nbsp;</td>
                    <td colspan="4" style="padding-left: 25px;text-align: left; width: 60%;">
				  From
                  <html:select property="smon">
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
				  <td>&nbsp;</td>
					<td style="padding-left: 75px;text-align: left;">Branch</td>
					<td colspan="4" style="text-align: left;">
					  <div id="results">
						<html:select property="depo_code" styleId="mySelect">
						  <html:optionsCollection name="HORepo1Form" property="branchlist" label="dname" value="dcode" />
						</html:select>
					  </div>
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
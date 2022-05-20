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
<link rel="stylesheet" type="text/css" href="css/<%=css1%>"  />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

  <html:form action="PmtListRepo1.do?actionRequested=PmtListRepo1">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="usertable">
				  <th colspan="5">1 # PMT-Product Wise Gross/Credit/Net Sales</th>
                  
                  <tr>
                    <td>&nbsp;</td>
                    <td><html:radio property="uv"  value="1"/>Unit &nbsp;</td>
                    <td><html:radio property="uv"  value="2"/>Value &nbsp;&nbsp;</td>
                    <td><html:radio property="uv"  value="3"/>Both</td>
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
                    <td colspan="5">
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
                    <td colspan="5" align="center">
                      <html:submit value="Submit" styleClass="button"/>
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
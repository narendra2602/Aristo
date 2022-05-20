<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
%>
 <jsp:include page="<%=toop%>"/> 


<head>

<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link rel="stylesheet" type="text/css" href="css/OMF1.css"  />

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

  <html:form action="ListRepo2.do?actionRequested=ListRepo2">
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0">

				  <th colspan="5">Product Wise Gross/Credit/Net Sales</th>


                  <tr>
                    <td height="34" align="center" valign="bottom"><div align="left"></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7">
                        <html:radio property="uv"  value="1"/>
                    Unit</span></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7">
                        <html:radio property="uv"  value="2"/>
                    Value</span></div></td>
                    <td align="center" valign="bottom"><div align="left"></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7"></span></div></td>
                  </tr>
                  <tr>
                    <td height="34" align="center" valign="bottom"><div align="left"></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7">
                        <html:radio property="sale_type"  value="1"/>
                    Gross </span></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7">
                        <html:radio property="sale_type"  value="2"/>
                    Credit </span></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7">
                        <html:radio property="sale_type"  value="3"/>
                    Net </span></div></td>
                    <td align="center" valign="bottom"><div align="left"><span class="style7"></span></div></td>
                  </tr>
                  <tr>
                    <td height="34" align="center" valign="bottom">&nbsp;</td>
                    <td align="center" valign="bottom"><span class="style7"> Select Month</span></td>
                    <td align="center" valign="bottom"><span class="style7">From
                      <html:select property="mnth">
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
                    </span></td>
                    <td align="center" valign="bottom"><span class="style7">To
                      <html:select property="mnth1">
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
                    </span></td>
                    <td align="center" valign="bottom"><span class="style7"></span></td>
                  </tr>
                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>
                  <tr>
                    <td height="34" colspan="5" align="center" valign="bottom"><div align="center" class="style3">
                        <html:submit value="Submit"/>
                      &nbsp;&nbsp;
                      <html:reset value="Cancel"/>
                    </div></td>
                  </tr>
                  <tr>
                    <td height="26" colspan="5" align="center" valign="bottom">&nbsp;</td>
                  </tr>
    </table>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>



  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
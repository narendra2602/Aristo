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
   <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >
        
       <html:form action="UpdateProduct.do?actionRequested=UpdateProduct" >
                <p>&nbsp;</p>
				<p>&nbsp;</p>				
                <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable1">
                        <th colspan="6">Product Master </th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="29%">&nbsp;</td>
                    <td width="50%">&nbsp;</td>
                    <td width="10%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Code </td>
                    <td><html:text property="pcode" readonly="true"/></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Name </td>
                    <td><html:text property="pname" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Packing</td>
                    <td><html:text property="pack" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Group </td>
					<td><html:text property="pd_group" /></td>
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
                    <td colspan="3"><u>Marketing</u></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Code </td>
                    <td><html:text property="mcode" /></td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Name </td>
                    <td><html:text property="mname" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Group Code</td>
                    <td><html:text property="mgrp" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Target/Non-Target</td>
                    <td><html:radio property="tn" value="T" />Targeted 
  					    <html:radio property="tn" value="N" />Non-Targeted 
					</td>
                    <td>&nbsp;</td>
                  </tr>
				  
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td colspan="4">
	                <div align="center">
 				    <html:submit  value="Submit" styleClass="button"/>&nbsp;&nbsp;
					<html:reset  value="Cancel" styleClass="button"/>                    
                    </div>
					</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>

			<p>&nbsp;</p>
	   </html:form>
	</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>
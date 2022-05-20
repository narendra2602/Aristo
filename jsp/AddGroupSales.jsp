<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>
 

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");

%>
 <jsp:include page="<%=toop%>"/> 



<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


              
              <html:form action="AddGroupSales.do?actionRequested=AddGroupSales" >
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
				<br/>
                <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable1">
                      <th colspan="6">Group Master Sales </th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="39%">&nbsp;</td>
                    <td width="38%">&nbsp;</td>
                    <td width="12%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Group Code</td>
                    <td><html:text property="gp_code" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Group Name </td>
                    <td><html:text property="gp_name" /></td>
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
                                    </div>					 </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>

				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<br/>
          </html:form>
		  
		  
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

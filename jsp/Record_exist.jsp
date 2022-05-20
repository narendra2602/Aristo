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


              
           
   
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <table width="43%" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable1">
                      <th colspan="6">
                  Record Exist </th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="39%">&nbsp;</td>
                    <td width="38%">&nbsp;</td>
                    <td width="12%">&nbsp;</td>
                  </tr>
				 <tr>
                    <td colspan="4">&nbsp;</td>
                  </tr>				  
                  <tr>
                    <td colspan="4"><div align="center">Record Already Exist</div></td>
                  </tr>
                  <tr>
                    <td colspan="4">&nbsp;</td>
                  </tr>
                  
                  <tr>
                    <td colspan="4">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <br/>

		  
		  
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<head>

<jsp:include page="F_top.jsp"/> 

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >

	<%
		String a= (String) request.getAttribute("data");
	%>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>

<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0" class="table_opt">
  
  <tr>
	<td>&nbsp;</td>
  </tr>
  <tr>
	<td>&nbsp;
	  <div align="center"> <%=a%> </div></td>
  </tr>
  <tr>
	<td>&nbsp;</td>
  </tr>
  <tr>
  <td><div align="center"><a href="/Aristo/UserHomeForward1.do?actionRequested=UserHomeForward1">Click here to Continue</a></div></td>
  </tr>
  <tr>
	<td>&nbsp;</td>
  </tr>
</table>
                
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
	<br/>

  </body>
 <jsp:include page="F_footer.jsp"/> 
</html:html>

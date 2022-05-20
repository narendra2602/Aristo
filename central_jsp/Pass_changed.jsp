<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>



<html:html>

<% 
	String top = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>

 <jsp:include page="<%=top%>"/> 


<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


  			    <%
					String a= (String) request.getAttribute("data");
			    %>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>

<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0" class="upload">
  
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
	<td>&nbsp;</td>
  </tr>

  <tr>
  <td><div align="center"><a href="/Aristo/UserHomeForward1.do?actionRequested=UserHomeForward1">Click here to Continue</a></div></td>
  </tr>
  <tr>
	<td>&nbsp;</td>
  </tr>
  <tr>
	<td>&nbsp;</td>
  </tr>
  
</table>
                
                
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>


  </body>
 <jsp:include page="<%=footer%>"/> 
</html:html>

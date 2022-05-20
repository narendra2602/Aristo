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

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<div class="usertable1" style="color:black; text-align: center;padding: 20px 0px; width: 30%; left: 35%; top: 25%;">
 

    <div >No Record Found</div>
  
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<html>
	<% 
	String toop  = (String) session.getAttribute("top");
	String foot = (String) session.getAttribute("footer");
	String css1 = (String) session.getAttribute("css");
	%>

	<jsp:include page="<%=toop%>"/> 

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
	</head>

<body>
	<% 
	response.setHeader("Cache-Control","no-cache");  
	response.setHeader("Cache-Control","no-store");  
	%> 
   
     <br/>
	 <br/>
	 
     <display:table id="data" name="sessionScope.CheckItemForm.rlist" requestURI="./CheckItemForward.do?actionRequested=CheckItemForward" pagesize="22" export="true">
		<display:caption media="html">  <strong><br/> LIST OF WRONG ITEM CODES </strong>  </display:caption>
		<display:caption media="excel rtf pdf"> LIST OF WRONG ITEM CODES </display:caption>
		<display:column property="branch_name" title="Branch Name" />
		<display:column property="pcode" title="Product Code"/>			
		<display:column property="mcode" title="Marketing Code"/>
	 </display:table>

</body>
	<br/>
	<br/>
	<br/>
    <jsp:include page="<%=foot%>"/> 
</html>
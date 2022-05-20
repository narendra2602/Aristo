<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<jsp:include page="F_top.jsp"/> 

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


<%
    String fmsg=(String) session.getAttribute("facmsg");		
%>
	
<p style="height:380px;">&nbsp;</p>
<div width="100%" align="left" style="padding:4px 10px 4px 4px; border:dashed 1px; font-family:Tahoma; font-size:13px; font-weight:bold">
LAST INVOICE NO.: <%=fmsg%> 
</div>

<jsp:include page="F_footer.jsp"/>

</body>
</html>
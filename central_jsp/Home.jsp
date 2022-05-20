<html>
<head>

<title>|| Aristo Pharmaceutical Pvt. Ltd. ||</title>
<% 
	String top  = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>

<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 

</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<p>&nbsp;</p>
<p align="center"> <img src="images/png.png" width="334" height="381"> </p>

<jsp:include page="<%=footer%>"/> 

</body>
</html>
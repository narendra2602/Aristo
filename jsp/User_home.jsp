<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<%@ page import="com.aristo.valueobject.LoginFormBean" %> 
<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>


<% 
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	String ldate = lfb.getLast_ldate();
	String ltime = lfb.getLast_ltime();
	String fname = lfb.getF_name();
	String lname = lfb.getL_name();
	String tp = lfb.getType();
%>

<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

<link href="css/styles.css" rel="stylesheet" type="text/css" />
<jsp:include page="head-scripts.jsp" />




</head>

<body>
		
		<jsp:include page="<%=toop%>"/> 
	 

			
	<div id="content">
	    


    <%	  if ((tp.equals("Branch")) || (tp.equals("Both")))
         {  %>
	  <div> <IMG src="/Aristo/GraphGen.do?actionRequested=GraphGen"> </div>
	 <%  }
	     
	  %>


 </div><!-- / content -->


  
 <jsp:include page="footer.jsp" />
 
</body>

</html>

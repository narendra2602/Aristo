<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  

	<% 
		LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
		String typ = lfb.getType();
		String x = lfb.getBranch_name();
		String y = lfb.getAccess_t(); 
		String z = lfb.getD_type();
		String topimg=null;        
		String css=null;
		String menu=null;
			   x = x + " "+y;
			   

		if (z.equals("A"))
		{
		topimg="images/topbas.jpg";
		css="css/MF.css";	
		menu="css/menu.css";					  
		} 
		if (z.equals("T"))
		{
		topimg="images/tf_top.jpg";
		css="css/TF.css";			  			 
		menu="css/tfmenu.css";					  		
		}  
		if (z.equals("G"))
		{  
		topimg="images/genetica_top.jpg";
		css="css/Genetica.css";			  
		menu="css/gmenu.css";					  		
		}	   
	%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link rel="stylesheet" type="text/css" href="<%=menu%>" />
<link rel="stylesheet" type="text/css" href="<%=css%>" />

<script type="text/javascript" src="./js/dropdowntabs.js"> </script>
<script type="text/javascript" src="./js/TransparentPng.js"></script>

<style type="text/css">
<!--
body,td,th 
{
	font-family: Tahoma;
	font-size: 12px;
}
.style8 
{
	color: #000000;
	font-size: 16px;
	font-family: "Book Antiqua";
}

-->
</style>


</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >
<img src="<%=topimg%>" width="100%" height="85">

<div id="colortab" class="ddcolortabs">
<ul>
<li><a href="/Aristo/UserHomeForward1.do?actionRequested=UserHomeForward1"><span><strong> &nbsp; &nbsp; Home &nbsp; &nbsp; </strong> </span></a></li>	
		<li><a href="#" rel="dropmenu1_a"><span> <strong> &nbsp; &nbsp; Masters &nbsp; &nbsp; </strong> </span></a></li>
		<li><a href="#" rel="dropmenu2_a"><span> <strong> &nbsp; &nbsp; Dispatch &nbsp; &nbsp; </strong> </span></a></li>
		<li><a href="#" rel="dropmenu3_a"><span> <strong> &nbsp; &nbsp; Tools &nbsp; &nbsp; </strong> </span></a></li>
		<li><a href="/Aristo/LogoutUserForward.do?actionRequested=LogoutUserForward"><span> <strong> &nbsp; &nbsp; Logout &nbsp; &nbsp; </strong> </span></a></li>
	</ul>
</div>

<div class="ddcolortabsline">&nbsp;</div>
	
	<div id="dropmenu1_a" class="dropmenudiv_a" style="width:150px;">
		<a href="/Aristo/ListFBranch.do?actionRequested=ListFBranch">Branch Master</a>
		<a href="/Aristo/ListFProduct.do?actionRequested=ListFProduct">Product Master</a>
		<a href="/Aristo/ListFLocation.do?actionRequested=ListFLocation">Location Master</a>
	</div>

	<div id="dropmenu2_a" class="dropmenudiv_a" style="width:150px;">
	  	<a href="/Aristo/FactoryOptRepo1.do?actionRequested=FactoryOptRepo1">Transfer Register</a>
		<a href="/Aristo/FactoryOptRepo2.do?actionRequested=FactoryOptRepo2">Daily Dispatch Report</a>
		<a href="/Aristo/FactoryOptRepo3.do?actionRequested=FactoryOptRepo3">Location Wise Dispatch</a>
		<a href="/Aristo/FactoryOptRepo4.do?actionRequested=FactoryOptRepo4">Dispatch Summery</a>
		<a href="/Aristo/FactoryOptRepo5.do?actionRequested=FactoryOptRepo5">Product Wise Dispatch</a>
	</div>

	<div id="dropmenu3_a" class="dropmenudiv_a" style="width:150px;">
		<a href="#">Upload Status</a>
		<a href="/Aristo/ChangepassForward1.do?actionRequested=ChangepassForward1">Change Password</a>
<%
if (y.equals("All"))
{
%>
<a href="/Aristo/switchUserForward.do?actionRequested=switchUserForward">Switch</a>	
<%} %>


	</div>

	
	
	<script type="text/javascript">
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	tabdropdown.init("colortab", 3)
	</script>
	
<%
String type=(String) session.getAttribute("type");
String name=(String) session.getAttribute("nm");	
System.out.println("Tpe is "+type);
%>

<table width="100%" align="right" cellpadding="0" cellspacing="0" class="datetable">
  <tr>
  
	<th class="corner1" width="46%"> <span class="style8">FACTORY DISPATCH DETAILS</span> </th>	   
	<th width="3%" class="corner">&nbsp;</th>		
	<th align="center" valign="bottom" scope="col">WELCOME: <%=name%> || ACCESS: <%=type%></th>
  </tr>
</table><br/>

</body>
</html>
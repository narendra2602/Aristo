<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=jsp/session_exp.jsp">

<html>
<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<script type="text/javascript" src="./js/dropdowntabs.js">  </script>
<script type="text/javascript" src="./js/TransparentPng.js"></script>
<link rel="stylesheet" type="text/css" href="./css/menu.css" />

<style type="text/css">
<!--
.style3 {font-family: Tahoma; font-size: 12px; }
.style5 {
	font-family: Tahoma;
	font-size: 12px;
	font-weight: bold;
	color: #FFFFFF;
}
.style7 {font-family: Tahoma; font-size: 12px; font-weight: bold; }
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
-->
</style>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" onLoad="goforit()">

<img src="./images/topbas.jpg" width="100%" height="85">
<%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
      String x = lfb.getBranch_name();
      String y = lfb.getAccess_t();         
             x = x + " "+y;

%>


<div id="colortab" class="ddcolortabs">
<ul>
<li><a href="/Aristo/UserHomeForward.do?actionRequested=UserHomeForward"><span>&nbsp;<strong>Home</strong>&nbsp;</span></a></li>
<li><a href="#" rel="dropmenu0_a"><span> &nbsp; <strong>Masters</strong> &nbsp; </span></a></li>
<li><a href="#" rel="dropmenu1_a"><span> &nbsp; <strong>User Management</strong> &nbsp; </span></a></li>
<li><a href="#" rel="dropmenu3_a"><span> &nbsp; <strong>administration</strong> &nbsp; </span></a></li>	
<li><a href="/Aristo/switchUserForwardAdmin.do?actionRequested=switchUserForwardAdmin"><span><strong>Switch</strong></span></a></li>	
</ul>
</div>
<div class="ddcolortabsline">&nbsp;</div>

	<!--Master -->                                                   
	<div id="dropmenu0_a" class="dropmenudiv_a">
	<a href="/Aristo/AdminListProduct.do?actionRequested=AdminListProduct">Product Master</a>
	<a href="/Aristo/ListProduct1.do?actionRequested=ListProduct1">Product Master (C.W.H)</a>
	<a href="/Aristo/AdminListGroupSales.do?actionRequested=AdminListGroupSales">Group Master (Sales)</a>
	<a href="/Aristo/AdminListGroupMkt.do?actionRequested=AdminListGroupMkt">Group Master (MKT)</a>
	<a href="/Aristo/ListBranch.do?actionRequested=ListBranch">Branch Master</a>
	</div>
	
	<!--User Manage -->                                                   
	<div id="dropmenu1_a" class="dropmenudiv_a">
	<a href="/Aristo/addUsertypeForward.do?actionRequested=addUsertypeForward">Add New User</a>
	<a href="/Aristo/UserList.do?actionRequested=UserList">Edit User Info</a>
	<a href="/Aristo/UserRightsList.do?actionRequested=UserRightsList">Edit User Rights</a>	
	<a href="/Aristo/PmtRightsList.do?actionRequested=PmtRightsList">PMT Group Rights</a>		
    <a href="/Aristo/UploadStatus.do?actionRequested=UploadStatus">Updation Status</a>	
    <a href="/Aristo/UserStatus.do?actionRequested=UserStatus">User Login Details</a>		
	</div>
		
	<!--Reports -->                                                
	<div id="dropmenu2_a" class="dropmenudiv_a" style="width: 260px;">
	<a href="/Aristo/OptRepo2.do?actionRequested=OptRepo2">1 Product Wise Gross/Credit/Net Sales</a>
	<a href="/Aristo/OptRepo2a.do?actionRequested=OptRepo2a">2 Group Wise Gross/Credit/Net Sales</a>
	<a href="/Aristo/OptRepo3.do?actionRequested=OptRepo3">3 Rupeeswise Salable/Expiry/Breakage</a>
	<a href="/Aristo/OptRepo4.do?actionRequested=OptRepo4">4 Selective HQ/Region/Area/Branch</a>
	<a href="/Aristo/OptRepo5.do?actionRequested=OptRepo5">5 Rupeeswise Gross/Credit/Net Sale</a>
	<a href="/Aristo/OptRepo6.do?actionRequested=OptRepo6">6 Selective Product - HQ/Region/Area/Branch</a>
	<a href="/Aristo/OptRepo7.do?actionRequested=OptRepo7">7 Selective Group - HQ/Region/Area/Branch</a>
	<a href="/Aristo/OptRepo8.do?actionRequested=OptRepo8">8 Selective Product - Sales Trend &nbsp;(Gross)</a>
	</div>

	<!--Administration-->                                                
	<div id="dropmenu3_a" class="dropmenudiv_a" style="width: 150px;">
	<a href="/Aristo/ChangepassForward.do?actionRequested=ChangepassForward">Change Password</a>
	</div>
	
	
	<script type="text/javascript">
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	tabdropdown.init("colortab", 3)
	</script>
	<table width="100%" align="right" cellpadding="0" cellspacing="0" class="datetable">
      <tr>
        <th class="corner1" width="46%"> </th>
        <th width="3%" class="corner">&nbsp;</th>
        <th width="16%"><strong><img src="images/users.png" width="14" height="17">&nbsp; <%=x%></strong></th>
        <th width="24%"><script>

/*
Live Date Script- 
&copy; Dynamic Drive (www.dynamicdrive.com)
For full source code, installation instructions, 100's more DHTML scripts, and Terms Of Use,
visit http://www.dynamicdrive.com
*/


var dayarray=new Array("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
var montharray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")

function getthedate(){
var mydate=new Date()
var year=mydate.getYear()
if (year < 1000)
year+=1900
var day=mydate.getDay()
var month=mydate.getMonth()
var daym=mydate.getDate()
if (daym<10)
daym="0"+daym
var hours=mydate.getHours()
var minutes=mydate.getMinutes()
var seconds=mydate.getSeconds()
var dn="AM"
if (hours>=12)
dn="PM"
if (hours>12){
hours=hours-12
}
if (hours==0)
hours=12
if (minutes<=9)
minutes="0"+minutes
if (seconds<=9)
seconds="0"+seconds
//change font size here
var cdate="<b>"+montharray[month]+" "+daym+", "+year+" | "+hours+":"+minutes+":"+seconds+" "+dn
+"</b>"
if (document.all)
document.all.clock.innerHTML=cdate
else if (document.getElementById)
document.getElementById("clock").innerHTML=cdate
else
document.write(cdate)
}
if (!document.all&&!document.getElementById)
getthedate()
function goforit(){
if (document.all||document.getElementById)
setInterval("getthedate()",1000)
}

    </script>
            <img src="images/clock.png" width="17" height="17"> <span id="clock"></span></th>
        <th width="11%" align="center" valign="bottom" scope="col"> <img src="images/logout.png" width="17" height="17"> <a href="/Aristo/LogoutUserForward.do?actionRequested=LogoutUserForward">Logout</a></th>
      </tr>
    </table>
</body>

</html>
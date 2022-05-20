<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=jsp/session_exp.jsp">

<% 
String css2 = (String) session.getAttribute("menucss");
String css1 = (String) session.getAttribute("css");
%>

<html>
<style type="text/css">
<!--
.style8 {
	color: #000000;
	font-size: 16px;
	font-family: "Book Antiqua";
}
-->
</style>
<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript" src="./js/dropdowntabs.js"> </script>
<link href="css/<%=css2%>" rel="stylesheet" type="text/css" />
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/TransparentPng.js"></script>

<style type="text/css">
<!--
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
-->
</style>


</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" onLoad="goforit()">
  
		<% 
			LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
			String typ = lfb.getType();
			String x = lfb.getBranch_name();
			String y = lfb.getAccess_t(); 
			String z = lfb.getD_type();
			String msg = lfb.getMsg();			
			String topimg=null;        
				   x = x + " "+y;
				  topimg="images/topbas.jpg";   
			if (z.equals("A"))
			  topimg="images/topbas.jpg";
			  
			if (z.equals("T"))
			  topimg="images/tf_top.jpg";
			  
			if (z.equals("G"))
			  topimg="images/genetica_top.jpg";
				   
		%>
  
       <img src="<%=topimg%>" width="100%" height="85">

        <%

			List rlist = (List) session.getAttribute("repolist");
			Iterator itr = rlist.iterator();
			int rindex = rlist.size();
			String disp[]=new String [rindex];
			String link[]=new String [rindex];
			int ri=0;
			int k=0;
			boolean flag=false;

            List tlist = (List) session.getAttribute("tablist");
		    LoginFormBean l = null;
			Iterator it = tlist.iterator();
			int index = tlist.size();
			int i=0;
	        String a[] = new String[index];
			String a1[] = new String[index];
	        int a0[] = new int[index];
	        int t2[] = new int[index];
			while (it.hasNext())
			{
				l = (LoginFormBean) it.next();
				a0[i]=l.getTab_id();
				t2[i]=l.getTab_width();
				a[i]=l.getTab_name();
				a1[i]=l.getTab_link();
			    i++;   
			}
	  
       %>

<div id="colortab" class="ddcolortabs">
<ul>
<li><a href="/Aristo/UserHomeForward.do?actionRequested=UserHomeForward"><span><strong> Home </strong></span></a></li>


   <%
   for (k=0;k<index;k++)
   { %>
   <li><a href="#" rel="<%=a1[k]%>"><span><strong><%=a[k]%></strong></span></a></li>
   <% 
   }	
  %>


	
</ul>
</div>

<div class="ddcolortabsline">&nbsp;</div>


<!--
<div id="dropmenu9_a" class="dropmenudiv_a" style="width:150px;">
<a href="#"><span>Stock & Sales</span></a>
<a href="#"><span>Stock Ledger</span></a>
<a href="#"><span>Near Expiry List</span></a>
<a href="#"><span>Item Wise Sales</span></a>
<a href="#"><span>PartyWise/ItemWise Sale</span></a>
<a href="#"><span>Transfer Register</span></a>
<a href="#"><span>Sample Register</span></a>
<a href="#"><span>Pending Report</span></a>
</div>

-->


 <%
	l = null;
   for (int n=0;n<index;n++)
   {
%>
                                                  
   	       <div id="<%=a1[n]%>" class="dropmenudiv_a" style="width:<%=t2[n]%>px;">
 	
 <%
			ri=0;
            if (flag)
			{ 
			  		disp[0]=l.getRepo_name();
					link[0]=l.getRepo_link();

			  ri=1;
			 }
		   			
			while (itr.hasNext())
			{
			   
				l = (LoginFormBean) itr.next();
			    
				if (a0[n]==l.getTab_id())
				{
					disp[ri]=l.getRepo_name();
					link[ri]=l.getRepo_link();
					ri++;   
				}
				else
				{
				
/**
   ///////////////// Goods In Transit Report direct ki hai///////////////////////				    
					if (a0[n]==5)
					{
						disp[ri]="Goods  in Transit";
						link[ri]="/Aristo/OptInv4.do?actionRequested=OptInv4";
						ri++;
					}	
   ///////////////// Goods In Condition Report direct ki hai///////////////////////				    
*/

					flag=true;
				    break;
				}
			}

			for (k=0;k<ri;k++)
			 {
%>
		       <a href="<%=link[k]%>"> <span> <%=k+1%> - <%=disp[k]%> </span> </a> 
<% 
			 }	
%>
	
		  </div>
<%

    }
 %>
	
	
	<script type="text/javascript">
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	tabdropdown.init("colortab", 3)
	</script>
	<table width="100%" align="right" cellpadding="0" cellspacing="0" class="datetable">
       <tr>
        <th class="corner1" width="46%"> <marquee> <span class="style8"> <%=msg%> </span> </marquee> </th>	   
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
        <th width="11%" align="center" valign="bottom" scope="col">
		<img src="images/logout.png" width="17" height="17">
		<a href="/Aristo/LogoutUserForward.do?actionRequested=LogoutUserForward">Logout</a></th>
      </tr>
</table>
</body>
</html>
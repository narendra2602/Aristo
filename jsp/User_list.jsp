<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<html>
<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>"/> 

<head>
 <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
 
 
 <script language="javascript">
function Clickheretoprint()
{ 
  var disp_setting="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
      disp_setting+="scrollbars=yes,width=1000, height=600, left=0, top=50"; 
  var content_vlue = document.getElementById("print_content").innerHTML; 
  
  var docprint=window.open("","",disp_setting); 
   docprint.document.open(); 
   docprint.document.write('<html><head><title>OPAL Technology Indore</title>'); 
   docprint.document.write('<link href="css/MF.css" rel="stylesheet" type="text/css" />');
   docprint.document.write('</head><body onLoad="self.print()"><center>');          
   docprint.document.write(content_vlue);          
   docprint.document.write('</center></body></html>'); 
   docprint.document.close(); 
   docprint.focus(); 
}
</script>

 
 
</head>
<body>

	<% 
		response.setHeader("Cache-Control","no-cache");  
		response.setHeader("Cache-Control","no-store");  
	%> 
<br/> <br/>

   	<html:form action="UserList.do?actionRequested=UserList" >    	
	<table class="search">
      <tr>
	    <td>
			<html:text property="search" />
			<html:submit value="Search" styleClass="button"/> 
	    </td>
	  </tr>
	</table>
   </html:form>		
	
	<%    
		String whead=" ";
		LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
		String x = lfb.getBranch_name();
		String y = lfb.getAccess_t();         
		x = x + " Branch "+y;
		whead="["+x+"]" +" " + "USER LIST";
	%>
	
<div id="results">	
    <div align="right"><a href="javascript:Clickheretoprint()"> Print</a>    </div>
    <div class="style3" id="print_content">

    <display:table id="data" name="sessionScope.UserForm.ulist" requestURI="./UserList.do?actionRequested=UserList" pagesize="20" export="true">

	
	<display:caption media="html">     <strong><br/>USER LIST </strong>	    </display:caption>
	<display:caption media="excel rtf pdf"><%=whead%></display:caption>
	<display:column property="id" title="User ID" />         
	<display:column property="login_name" title="Login Name" />
	<display:column property="password" title="Password"/>				
	<display:column property="f_name" title="First Name"/>			
	<display:column property="l_name" title="Last Name"/>
	<display:column property="department" title="Department"/>			
	<display:column property="location" title="Location"/>
	<display:column property="status" title="Status"/>
	<display:column property="link1" title="Action" href="UserEdit.do?actionRequested=UserEdit" paramId="id" paramProperty="id" />			
    </display:table>
    </div>	
     </div>
   </body>
  <br/><br/><br/>
 <jsp:include page="<%=foot%>"/> 
</html>
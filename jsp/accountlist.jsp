<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/tags/alphanavbar" prefix="nav" %>

<html>
	<% 
	String toop  = (String) session.getAttribute("top");
	String foot = (String) session.getAttribute("footer");
	String css1 = (String) session.getAttribute("css");
	%>
    <jsp:include page="<%=toop%>"/> 

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Area List</title>
		<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
	</head>
	
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
   
	   
        <%    
            String whead=" ";
            LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            String x = lfb.getBranch_name();
            String y = lfb.getAccess_t();         
            x = x + " Branch "+y;
            whead="["+x+"]        " +"             " + "Account Master";
        %>
<br/>
<br/>

 <table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="mac_name" name="sessionScope.AccountForm.rlist" />
 </td>
 </tr>
 </table>

 <display:table name="requestScope.alphabeticalsublist" pagesize="20" export="true">


     <br/> <br/>
   
      <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
      <display:caption media="excel rtf pdf "><%=whead%></display:caption>
		            
      <display:column property="mac_code" title="CODE"  />
      <display:column property="mac_name" title="PARTY NAME"/>
      <display:column property="mcity" title="CITY" />
      <display:column property="madd1" title="HEADQUATER"/>
      <display:column property="madd2" title="MR. NAME" />
	  
      </display:table>
	  
    </body>
	
		<br/><br/>


 <jsp:include page="<%=foot%>"/> 

</html>

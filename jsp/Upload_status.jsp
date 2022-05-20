<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
		whead="["+x+"] " +" " + "User List";
    %>
<br/>
<br/>
 <table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="depo_name" name="sessionScope.UploadForm.ulist" />
 </td>
 </tr>
 </table>

 <display:table name="requestScope.alphabeticalsublist" pagesize="10" export="true">

	        <display:caption media="excel rtf pdf html"><br/>Upload Status</display:caption>			
            <display:column property="file_name" title="File Name" />
            <display:column property="file_path" title="File Upload"/>			
            <display:column property="upload" title="Upload Status"/>
            <display:column property="depo_name" title="Depo Name"/>
        	<display:column property="u_date" title="Date"/>			
            <display:column property="u_time" title="Time"/>
			
  </display:table>
		
</body>
<p>&nbsp;</p>				
<jsp:include page="<%=foot%>"/> 
</html>
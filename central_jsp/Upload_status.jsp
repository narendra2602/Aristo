<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  

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
</head>

<body>

	<% 
	   response.setHeader("Cache-Control","no-cache");  
	   response.setHeader("Cache-Control","no-store");  
	%> 
 
    
    <p>&nbsp;  </p>
	<div class="div1">	  

   <display:table id="data" name="requestScope.UploadForm.ulist" pagesize="24" requestURI="./CUploadStatus.do?actionRequested=CUploadStatus" >
            <display:caption media="html">      <strong><br/>UPLOAD STATUS</strong>    </display:caption>
			
            <display:column property="file_name" title="FILE NAME" />
            <display:column property="file_path" title="FILE UPLOAD"/>			
            <display:column property="upload" title="UPLOAD STATUS"/>
        	<display:column property="u_date" title="DATE"/>			
            <display:column property="u_time" title="TIME"/>
        </display:table>
	  </div> <br/>	
    </body>
 <jsp:include page="<%=foot%>"/> 

</html>

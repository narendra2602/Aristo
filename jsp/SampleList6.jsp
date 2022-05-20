<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.SampleRepo6FormBean" %>  
<%@ page import="org.displaytag.sample.*" %> 
 
<html>

<head>
<% 
String top  = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>

<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 
</head>
	
<body>
	<% 
	  response.setHeader("Cache-Control","no-cache");  
	  response.setHeader("Cache-Control","no-store");  

	  String whead = (String) request.getAttribute("whead");
	%> 
 
    
 <p>&nbsp;  </p>
 <div class="div1">	 
 <display:table id="data" name="requestScope.SampleRepo6Form.rlist" requestURI="./SampleListRepo6.do?actionRequested=SampleListRepo6" pagesize="20" export="true" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="inv_no" title="INV NO." headerClass="r" class="r"  />						
            <display:column property="inv_dt" title="INV DATE" format="{0,date,dd/MM/yyyy}" class="r" />
            <display:column property="name" title="NAME"  />			
			<display:column property="city" title="CITY" />			
            <display:column property="lr_no" title="LR NO." headerClass="r" class="r" />
            <display:column property="lr_dt" title="LR DATE" format="{0,date,dd/MM/yyyy}" />
            <display:column property="transport" title="TRANSPORTER"/>						
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
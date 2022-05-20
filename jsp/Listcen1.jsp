<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.Cen1FormBean" %>  
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
 <display:table id="data" name="requestScope.Cen1Form.rlist" requestURI="./ListCen1.do?actionRequested=ListCen1" pagesize="20" export="true" >
 
            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>
			
            <display:column property="sno" title="S NO." />
            <display:column property="inv_no" title="INV NO."/>
            <display:column property="inv_date" title="INV DATE" format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="branch" title="BRANCH" /> 
			<display:column property="amount" title="AMOUNT" format="{0,number,0.00}" headerClass="r" class="r" total="true"/> 
			<display:column property="transporter" title="TRANSPORTER" /> 			
            <display:column property="lr_no" title="LR NO." /> 
            <display:column property="lr_date" title="LR DATE" format="{0,date,dd-MM-yyyy}" /> 
            <display:column property="truck_no" title="TRUCK NO." /> 	
						
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
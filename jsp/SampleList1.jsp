<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.SampleRepo2FormBean" %>  
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
	  String whead = (String) request.getAttribute("head");
	%> 
 
    
 <p>&nbsp;  </p>
 <div class="div1">	 
 <display:table id="data" name="requestScope.SampleRepo2Form.rlist" requestURI="./SampleListRepo1.do?actionRequested=SampleListRepo1" pagesize="20" export="true" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>

			<display:column property="pname" title="PRODUCT " href="SampleListRepo2a.do?actionRequested=SampleListRepo2a"
			paramId="code" paramProperty="code" media="html" />
		    <display:column property="pname" title="PRODUCT " media="excel" />						
            <display:column property="pack" title="PACK"/>
            <display:column property="oqty" title="OPENING BALANCE" headerClass="r" class="r" />			
            <display:column property="damanqty" title="RECEIPT" headerClass="r" class="r" />			
            <display:column property="iqty" title="ISSUE" headerClass="r" class="r" />			
			<display:column property="tqty" title="TRF" headerClass="r" class="r" />			
			<display:column property="cqty" title="CLOSING BALANCE" headerClass="r" class="r" /> 
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
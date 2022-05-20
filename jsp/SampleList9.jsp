<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.SampleRepo9FormBean" %>  
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
 <display:table id="data" name="requestScope.SampleRepo9Form.rlist" requestURI="./SampleListRepo9.do?actionRequested=SampleListRepo9" pagesize="20" export="true" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pname" title="PRODUCT " />						
            <display:column property="pack" title="PACK"/>
            <display:column property="fs" title="F.S." headerClass="r" class="r" />			
            <display:column property="allot" title="ALLOC. PER MAN" headerClass="r" class="r" />			
			<display:column property="issue" title="ISSUE QTY" headerClass="r" class="r" />			
			<display:column property="pending" title="PENDING" headerClass="r" class="r" /> 
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
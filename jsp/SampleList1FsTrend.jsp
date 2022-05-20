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

			<display:column property="pname" title="GROUP " href="SampleListRepo1a.do?actionRequested=SampleListRepo1a"
			paramId="code" paramProperty="code" media="html" />
		    <display:column property="pname" title="GROUP " media="excel" />						
            <display:column property="octval" title="OCT" headerClass="r" class="r" />			
            <display:column property="novval" title="NOV" headerClass="r" class="r" />			
            <display:column property="decval" title="DEC" headerClass="r" class="r" />			
            <display:column property="janval" title="JAN" headerClass="r" class="r" />			
            <display:column property="febval" title="FEB" headerClass="r" class="r" />			
            <display:column property="marval" title="MAR" headerClass="r" class="r" />			
            <display:column property="aprval" title="APR" headerClass="r" class="r" />			
            <display:column property="mayval" title="MAY" headerClass="r" class="r" />			
            <display:column property="junval" title="JUN" headerClass="r" class="r" />			
            <display:column property="julval" title="JUL" headerClass="r" class="r" />			
            <display:column property="augval" title="AUG" headerClass="r" class="r" />			
            <display:column property="sepval" title="SEP" headerClass="r" class="r" />			
            <display:column property="tvalue" title="TOTAL" headerClass="r" class="r" />			
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
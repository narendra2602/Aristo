<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.Cen4FormBean" %>  
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
 <display:table id="data" name="requestScope.Cen4Form.rlist" requestURI="./ListCen4.do?actionRequested=ListCen4" pagesize="20" export="true"  >
 
            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>
 
            <display:column property="pname" title="DESCRIPTION" />
            <display:column property="pack" title="PACK" style="width:100px" />
            <display:column property="qty" title="QUANTITY" class="r" style="width:100px" /> 
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
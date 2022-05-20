<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="cen.aristo.valueobject.CentralRepo5FormBean" %>  
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
	<jsp:scriptlet>
	request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
	{
		public String addRowClass()
		{
		  int data = ((CentralRepo5FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "money";
		  else
			if (data==3)
				 return "vbad";
			else
				if (data==4)
				   return "good";
				else 
					   return "abc";
				 
		}
	   
	});
 </jsp:scriptlet>

   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.CentralRepo5Form.rlist" requestURI="./CentralListRepo5.do?actionRequested=CentralListRepo5" pagesize="20" export="true" decorator="dyndecorator" >

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
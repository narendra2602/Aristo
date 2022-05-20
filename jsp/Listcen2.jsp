<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.Cen2FormBean" %>  
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
		  int data = ((Cen2FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "vbad";
		  else
			if (data==3)
				 return "vbad";
			else
				if (data==4)
				   return "money";
				else 
					   return "abc";
				 
		}
	   
	});
 </jsp:scriptlet>
 
 
    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.Cen2Form.rlist" requestURI="./ListCen2.do?actionRequested=ListCen2" pagesize="20" export="true" decorator="dyndecorator" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pname" title="DESCRIPTION" />
            <display:column property="qty" title="QTY" headerClass="r" class="r" /> 
            <display:column property="cases" title="CASES" /> 
			<display:column property="mtr_no" title="LR NO./BATCH"  /> 
            <display:column property="mtr_dt" title="LR DATE" format="{0,date,dd/MM/yyyy}"  /> 
			<display:column property="transport" title="TRANSPORTER/MFG" /> 			
            <display:column property="vehicle_no" title="VEHICLE NO." /> 									
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
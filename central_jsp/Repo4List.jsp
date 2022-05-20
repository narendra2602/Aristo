<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="cen.aristo.valueobject.CentralRepo4FormBean" %>  
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
		  int data = ((CentralRepo4FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "money";
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
 <display:table id="data" name="requestScope.CentralRepo4Form.rlist" requestURI="./CentralListRepo4.do?actionRequested=CentralListRepo4" pagesize="20" export="true" decorator="dyndecorator" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>
            <display:column property="pcode" title="CODE" />
            <display:column property="name" title="DESCRIPTION" />						

            <display:column property="pack" title="PACK"/>
            <display:column property="sbatch_no" title="BATCH NO."/>			
			<display:column property="sinv_no" title="BILL NO"/>			
			<display:column property="sinv_dt" title="DATE" format="{0,date,dd/MM/yyyy}"  /> 
            <display:column property="qty" title="QTY" headerClass="r" class="r" /> 
            <display:column property="mrp" title="MRP" headerClass="r" class="r" />
            <display:column property="expiry_dt" title="EXPIRY" /> 			 
			<display:column property="mtr_no" title="LR NO."  /> 
            <display:column property="mtr_dt" title="LR DATE" format="{0,date,dd/MM/yyyy}"  /> 
			<display:column property="transport" title="TRANSPORTER" /> 			
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
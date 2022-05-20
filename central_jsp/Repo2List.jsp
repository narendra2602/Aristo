<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="cen.aristo.valueobject.CentralRepo2FormBean" %>  
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
		  int data = ((CentralRepo2FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "bad";
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

<display:table id="data" name="sessionScope.CentralRepo2Form.rlist" requestURI="./CentralListRepo2.do?actionRequested=CentralListRepo2" pagesize="20" export="true" decorator="dyndecorator" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
		    
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="product" title="PRODUCT" />
            <display:column property="packing" title="PACK"/>
            <display:column property="batch_no" title="BATCH NO." style="width:60px"/> 
            <display:column property="qty" title="QUANTITY" class="r" style="width:60px"/> 
			<display:column property="mrp" title="M.R.P." class="r" style="width:50px"/> 
			<display:column property="expiry" title="EXPIRY" style="width:70px" /> 			
            <display:column property="qtypc" title="QUANTITY PER CASE" class="r" style="width:65px" /> 
            <display:column property="gross_wt" title="GROSS WEIGHT" class="r" style="width:55px"/> 
            <display:column property="remark" title="REMARK" style="width:250px" /> 									
		
        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
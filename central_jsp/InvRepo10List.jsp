<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
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
//String  opng = (String) request.getAttribute("opng");
%> 
 

    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.InventoryRepo9Form.rlist" requestURI="./CentralInventoryRepo10.do?actionRequested=CentralInventoryRepo10" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>
 
            <display:column property="inv_dt" title="DATE" format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="total" title="TOTAL" format="{0,number,0.00}"  /> 

        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
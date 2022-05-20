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
%> 
 

    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.InventoryRepo8Form.rlist" requestURI="./CentralInventoryRepo8.do?actionRequested=CentralInventoryRepo8" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="name" title="PARTY NAME" />
            <display:column property="city" title="CITY"/>
            <display:column property="inv_no" title="INV NO."  /> 
            <display:column property="inv_dt" title="INV DATE" format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="inv_val" title="INV VALUE" format="{0,number,0.00}" headerClass="r" class="r" /> 
            <display:column property="cst_amt" title="CST AMOUNT" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="total" title="TOTAL" format="{0,number,0.00}" headerClass="r" class="r" /> 
            <display:column property="cases" title="CASES" /> 
            <display:column property="lr_no" title="LR NO." /> 			
            <display:column property="lr_dt" title="LR DATE" format="{0,date,dd/MM/yyyy}" /> 						
			<display:column property="transport" title="TRANSPORTER" /> 						
			<display:column property="vehicle_no" title="VEHICLE NO." /> 									
        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
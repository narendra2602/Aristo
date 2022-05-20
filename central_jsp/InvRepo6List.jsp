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
 <display:table id="data" name="sessionScope.InventoryRepo6Form.rlist" requestURI="./CentralInventoryRepo6.do?actionRequested=CentralInventoryRepo6" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pname" title="PRODUCT" />
            <display:column property="pack" title="PACK"/>
            <display:column property="sqty" title="QTY" headerClass="r" class="r" /> 
            <display:column property="sinv_no" title="DOC NO." headerClass="r" class="r" /> 
            <display:column property="sinv_dt" title="DATE"  format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="mtr_no" title="LR NO." /> 
            <display:column property="mtr_dt" title="LR DATE" format="{0,date,dd/MM/yyyy}"  /> 
            <display:column property="transport" title="TRANSPORTER" /> 
            <display:column property="pinv_no" title="FGRN NO." /> 
            <display:column property="pinv_date" title="DATE"  format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="gval" title="GOODS VALUE" format="{0,number,0.00}" headerClass="r" class="r" /> 
            <display:column property="vehicle_no" title="VEHICLE NO."  /> 			
        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
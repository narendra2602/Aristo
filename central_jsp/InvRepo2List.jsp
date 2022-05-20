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
String  opng = (String) request.getAttribute("opng");
%> 
 

    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="sessionScope.InventoryRepo2Form.rlist" requestURI="./CentralInventoryRepo2.do?actionRequested=CentralInventoryRepo2" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pcode" title="CODE" />
            <display:column property="pname" title="PRODUCT NAME" />			
            <display:column property="bat_no" title="BATCH NO." headerClass="r" class="r" /> 
            <display:column property="bat_netrt" title="NET RATE" format="{0,number,0.00}" headerClass="r" class="r" /> 
            <display:column property="expiry" title="EXPIRY DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r" /> 
            <display:column property="estock" title="EXPIRED STOCK" headerClass="r" class="r" /> 
            <display:column property="estock90" title="EXPIRY 0-180 DAYS" headerClass="r" class="r" /> 
            <display:column property="estock360" title="EXPIRY 181-360 DAYS"   headerClass="r" class="r" /> 
            <display:column property="tvalue" title="TOTAL VALUE" format="{0,number,0.00}" headerClass="r" class="r" /> 			

        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
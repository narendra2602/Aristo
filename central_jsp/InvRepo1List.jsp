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
 <display:table id="data" name="sessionScope.InventoryRepo1Form.rlist" requestURI="./CentralInventoryRepo1.do?actionRequested=CentralInventoryRepo1" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>
 
            <display:column property="pname" title="PRODUCT" />
            <display:column property="pack" title="PACK"/>
            <display:column property="opng" title="OPENING" headerClass="r" class="r" /> 
            <display:column property="recp" title="RECEIPTS" headerClass="r" class="r" /> 
            <display:column property="total" title="TOTAL" headerClass="r" class="r" /> 
            <display:column property="issu" title="DISPATCHES" headerClass="r" class="r" /> 
            <display:column property="clos" title="CLOSING STOCK" headerClass="r" class="r" /> 
            <display:column property="closval" title="CLOSING VALUE" format="{0,number,0.00}" headerClass="r" class="r" /> 

        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="footer.jsp"/> 	
</html>
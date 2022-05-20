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
 <display:table id="data" name="requestScope.SampleRepo3Form.rlist" requestURI="./ListInv6.do?actionRequested=ListInv6" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pcode" title="CODE" />
            <display:column property="pname" title="PRODUCT NAME" />			
            <display:column property="pack" title="PACK" />						
            <display:column property="bat_no" title="BATCH NO." headerClass="r" /> 
            <display:column property="expiry" title="EXPIRY DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r" /> 
            <display:column property="stock" title="STOCK" headerClass="r" class="r" /> 
            <display:column property="fac_name" title="MANUFACTURER"  /> 
            <display:column property="gift_name" title="GIFT"  /> 
            <display:column property="remark" title="REMARK"    /> 

        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
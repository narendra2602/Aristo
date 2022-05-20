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
 

    
   <br/><br/>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.SampleRepo3Form.rlist" requestURI="./HOListInv5.do?actionRequested=HOListInv5" pagesize="20" export="true" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>


            <display:column property="pname" title="BRANCH NAME" />			
            <display:column property="bat_no" title="BATCH NO." headerClass="r" /> 
            <display:column property="rcpdate" title="RECEIVED DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r" /> 
            <display:column property="expiry" title="EXPIRY DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r" /> 
            <display:column property="stock" title="STOCK" headerClass="r" class="r" /> 
            <display:column property="estock" title="LYING DAYS 60" headerClass="r" class="r" /> 
            <display:column property="estock90" title="60-90 DAYS" headerClass="r" class="r" /> 
            <display:column property="estock360" title="ABOVE 90 DAYS"   headerClass="r" class="r" /> 

        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
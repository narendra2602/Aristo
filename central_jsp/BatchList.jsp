<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html>

<% 
String toop  = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>
<jsp:include page="<%=toop%>"/> 

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/<%=css%>" rel="stylesheet" type="text/css" />
</head> 
	
<body>
	<% 
	response.setHeader("Cache-Control","no-cache");  
	response.setHeader("Cache-Control","no-store");  
	String xhead=(String) request.getAttribute("name");
	Integer abc=(Integer) request.getAttribute("code");
    int i = xhead.indexOf(',');
	int len = xhead.length()-1;
	String whead = xhead.substring(0,i); 
	String pack = xhead.substring(i+1,len+1);  
	%> 

   <p>&nbsp;  </p>
   <div class="div1">	 

    <display:table id="data" name="requestScope.BatchMasterForm.blist" requestURI="./ListBatch.do?actionRequested=ListBatch" pagesize="20" export="true" >
   
            <display:caption media="html"><strong><br/>BATCH MASTER<br/>PRODUCT CODE: <%=abc%> <br/>PRODUCT NAME: <%=whead%> <br/>PACKING : <%=pack%><br/></strong></display:caption>
	        <display:caption media="excel rtf pdf">BATCH MASTER, <%=whead%></display:caption>
			 
            <display:column property="bat_tag" title="BR" headerClass="r" class="r" />
            <display:column property="bat_no" title="BATCH NO." />
            <display:column property="bat_expdt" title="EXP DT" />			
            <display:column property="bat_netrt1" title="TRF RATE" headerClass="r" class="r" />						
            <display:column property="bat_netrt" title="NET INCL" headerClass="r" class="r" />			
            <display:column property="bat_mfgrt" title="NET EXCL" headerClass="r" class="r" />						
            <display:column property="bat_trdrt" title="RET INCL" headerClass="r" class="r" />			
            <display:column property="bat_trdrt1" title="RET EXCL" headerClass="r" class="r" />			
            <display:column property="bat_mrprt" title="MRP INCL" headerClass="r" class="r" />			
            <display:column property="bat_mrprt1" title="MRP EXCL" headerClass="r" class="r" />									
            <display:column property="bat_excrt" title="EXC RATE" headerClass="r" class="r" />						
            <display:column property="ecess" title="ECESS" headerClass="r" class="r" />						
            <display:column property="bat_clos" title="CLOSING" headerClass="r" class="r" />						
		</display:table>
     </div>		<br/>
    </body>

 <jsp:include page="<%=footer%>"/> 
</html>
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
	%> 

   <p>&nbsp;  </p>
   <div class="div1">	 
	
    <display:table id="data" name="requestScope.AccountMasterForm.blist" requestURI="./ListAccount1.do?actionRequested=ListAccount1" pagesize="20" export="true" >
   
            <display:caption media="html"><strong><br/> ACCOUNT MASTER </strong></display:caption>
	        <display:caption media="excel rtf pdf">ACCOUNT MASTER</display:caption>
			 
            <display:column property="mac_code" title="CODE"  />
            <display:column property="mac_name" title="PARTY NAME" />
            <display:column property="madd1" title="ADDRESS-1" />			
            <display:column property="madd2" title="ADDRESS-2"  />						
            <display:column property="mcity" title="CITY"  />			
            <display:column property="mpin" title="PIN CODE"  />						
		</display:table>
     </div>	
	 <br/>	
    </body>
 <jsp:include page="<%=footer%>"/> 
</html>
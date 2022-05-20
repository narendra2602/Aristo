<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>
 <jsp:include page="<%=toop%>"/> 



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area List</title>
             <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

    </head> 
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 


    <br/> <br/>
        <display:table id="data" name="requestScope.GroupSalesForm.gpsales" requestURI="./ListGroupSales1.do?actionRequested=ListGroupSales1" pagesize="20" export="true" >
            <display:caption media="html">
		      <strong><br/>Group Master<br/></strong>
		    </display:caption>
		    
            <display:caption media="excel rtf pdf">Group Master</display:caption>
            <display:column property="gp_code" title="Group Code"    />
            <display:column property="gp_name" title="Group Name"    />
        </display:table>
    </body>
		<br/> 
 <jsp:include page="<%=foot%>"/> 
</html>

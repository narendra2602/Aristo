<%@ page import="com.aristo.valueobject.LoginFormBean" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/tags/alphanavbar" prefix="nav" %>
<html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>
 <jsp:include page="<%=toop%>"/> 

	<head>
         <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    </head>
    
	<body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
    
        <%    
            String whead=" ";
            LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            String x = lfb.getBranch_name();
            String y = lfb.getAccess_t();         
            x = x + " Branch "+y;
            whead="["+x+"]        " +"             " + "GroupMarketing Master";
        %>
    
    
    
     <br> <br/>
<table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="gp_name" name="sessionScope.GroupMktForm.gplist" />
 </td>
 </tr>
 </table>

 <display:table name="requestScope.alphabeticalsublist" pagesize="20" export="true">
	 
		<display:caption media="html"><strong><br/><%=whead%><br/></strong></display:caption>
		<display:caption media="excel rtf pdf"><%=whead%></display:caption>
		<display:column property="gp_code" title="Group Code" />
		<display:column property="gp_name" title="Group Name" />
	</display:table>

	<br/>
   </body>
 <jsp:include page="<%=foot%>"/> 
</html>
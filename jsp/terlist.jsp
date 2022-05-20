<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
     %> 

      <%    
            String whead=" ";
            LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            String x = lfb.getBranch_name();
            String y = lfb.getAccess_t();         
            x = x + " Branch "+y;
            whead="["+x+"]        " +"             " + "H.Q. Master";
             

        %>
 
    <br/> <br/> 
<table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="ter_name" name="sessionScope.TerForm.tlist" />
 </td>
 </tr>
 </table>

 <display:table name="requestScope.alphabeticalsublist" pagesize="20" export="true">
	
	<display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
	<display:caption media="excel rtf pdf"><%=whead%></display:caption>
	<display:column property="ter_code" title="HQ Code" />
	<display:column property="ter_name" title="HQ Name"  />
	<display:column property="txt" title="Area Name"  />
	<display:column property="slct" title="Region Name" />
	<display:column property="oct" title="OCT FS" />
	<display:column property="nov" title="NOV FS" />
	<display:column property="dec" title="DEC FS" />
	<display:column property="jan" title="JAN FS" />
	<display:column property="feb" title="FEB FS" />
	<display:column property="mar" title="MAR FS" />
	<display:column property="apr" title="APR FS" />
	<display:column property="may" title="MAY FS" />
	<display:column property="jun" title="JUN FS" />
	<display:column property="july" title="JUL FS" />
	<display:column property="aug" title="AUG FS" />
	<display:column property="sep" title="SEP FS" />
	</display:table>
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
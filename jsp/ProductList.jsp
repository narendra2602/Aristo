<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/tags/alphanavbar" prefix="nav" %>

<html>

<% 
String toop  = (String) session.getAttribute("top");
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
            whead="["+x+"]        " +"             " + "Product Master";
             

        %>
 <br/>  <br/>
 <table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="pname" name="sessionScope.ProductForm.plist" />
 </td>
 </tr>
 </table>

 <display:table name="requestScope.alphabeticalsublist" pagesize="20" export="true">

		<display:caption media="html"> <strong><br/><%=whead%> <br/> </strong> </display:caption>
		<display:caption media="excel rtf pdf"><%=whead%></display:caption>
		<display:column property="pcode" title="Product Code" />
		<display:column property="pname" title="Product Name"/>
		<display:column property="pack" title="Pack"/>
		<display:column property="pd_group" title="Product Group" /> 
		<display:column property="mname" title="Group Name" />
		<display:column property="mcode" title="Mkt. Code"  headerClass="r" class="r"/>
		<display:column property="mgrp" title="Mkt. Group"   headerClass="r" class="r"/>
	</display:table>
		
    </body>
	<br/>
 <jsp:include page="<%=foot%>"/> 
</html>

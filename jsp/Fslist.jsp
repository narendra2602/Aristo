<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

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
            whead="["+x+"]        " +" " + "Field Staff Master ";
        %>

     <br/> <br/>
 <display:table id="data" name="requestScope.AccountForm.rlist" requestURI="./ListFS.do?actionRequested=ListFS" pagesize="24" export="true"  >
   
      <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
      <display:caption media="excel rtf pdf "><%=whead%></display:caption>
      <display:column property="mac_code" title="EMP CODE"  />
      <display:column property="mac_name" title="EMP NAME"/>
      <display:column property="mbanker" title="DESIGNATION"/>
      <display:column property="mphone" title="PHONE" />
      <display:column property="mcontct" title="MOBILE" />
      <display:column property="madd1" title="HEADQUATER"/>
      </display:table>
	  
    </body>
    <br/> <br/>
 <jsp:include page="<%=foot%>"/> 
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.aristo.valueobject.LoginFormBean" %>  

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
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
   
	   
      <%    
            String whead=" ";
            LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            String x = lfb.getBranch_name();
            String y = lfb.getAccess_t();         
            x = x + " Branch "+y;
            whead="["+x+"]        " +"             " + "Area Master";
             

        %>

 
     <br/> <br/>
        <display:table id="data" name="sessionScope.AreaForm.alist" requestURI="./ListArea.do?actionRequested=ListArea" pagesize="10" export="true" >
            <display:caption media="html">
		      <strong><br/><%=whead%></strong>
		    </display:caption>
		    
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>
		            
            <display:column property="area_cd" title="Area Cd" />
            <display:column property="area_name" title="Area Name"/>
        </display:table>
    </body>
	
		<br/><br/><br/><br/><br/><br/><br/><br/>


 <jsp:include page="<%=foot%>"/> 

</html>

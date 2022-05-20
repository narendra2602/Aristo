<%@ page import="com.aristo.valueobject.LoginFormBean" %>  


<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=session_exp.jsp">
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
            whead="["+x+"]        " +"             " + "Region Master";
             

        %>
 

    
     <br/> <br/>
        <display:table id="data" name="sessionScope.RegionForm.rlist" requestURI="./ListRegion.do?actionRequested=ListRegion" pagesize="20" export="true" >
            <display:caption media="html"> 
		      <strong><br/><%=whead%><br/></strong>
		    </display:caption>

            <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="reg_code" title="REGION CODE"/>
            <display:column property="name" title="REGION NAME"/>
            <display:column property="txt" title="AREA NAME" /> 
        </display:table>
		
    </body>
<br/>
 <jsp:include page="<%=foot%>"/> 	
</html>

<%@ page import="com.aristo.valueobject.LoginFormBean" %> 

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>
 <jsp:include page="<%=toop%>"/> 


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>H.Q. List</title>

             <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />


    </head>
    <body><br/> 
    
    <% 
        response.setHeader("Cache-Control","no-cache");  
     %> 

      <%    
            String whead=" ";
            LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            String x = lfb.getBranch_name();
            String y = lfb.getAccess_t();         
            x = x + " "+y;
            whead="["+x+"]        " +"             " + "ZM Master";
             

        %>
 
    <br/>
        <display:table id="data" name="sessionScope.TerForm.tlist" requestURI="./ListZm.do?actionRequested=ListZm" pagesize="21" export="true" >
            <display:caption media="html"> 
		      <strong><br/><%=whead%></strong> 
		    </display:caption>
        
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>
           
	<display:column property="zm_code" title="ZM CODE" />
	<display:column property="zm_name" title="ZM NAME"  />
	<display:column property="dgm_code" title="DGM CODE" />
	<display:column property="dgm_name" title="DGM NAME"  />
	<display:column property="branches" title="BRANCHE'S" />
	<display:column property="link2" title="Action" href="install.do?actionRequested=install" paramId="zm_code" paramProperty="zm_code" />			
</display:table>
		
			
			
        <html:form action="AddZmForward.do?actionRequested=AddZmForward">
  	      <div align="center">
  	        <html:submit  value="Add ZM" styleClass="button"/>
  	        &nbsp;&nbsp;
            </div>
        </html:form>
			
			
			
    </body>
	
	
	
 <jsp:include page="<%=foot%>"/> 
</html:html>

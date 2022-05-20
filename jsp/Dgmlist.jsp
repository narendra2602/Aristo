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
	whead="["+x+"]        " +"             " + "DGM Master";
   %>
 
 
   <br/>
  <display:table id="data" name="sessionScope.TerForm.tlist" requestURI="./ListDgm.do?actionRequested=ListDgm" pagesize="21" export="true" >
	<display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
	<display:caption media="excel rtf pdf"><%=whead%></display:caption>
   
	<display:column property="dgm_code" title="DGM CODE" />
	<display:column property="dgm_name" title="DGM NAME"  />
	<display:column property="branches" title="BRANCHE'S" />
	<display:column property="link1" title="Action" href="install.do?actionRequested=install" paramId="dgm_code" paramProperty="dgm_code" />			
</display:table>
		
        <html:form action="AddDgmForward.do?actionRequested=AddDgmForward">
  	      <div align="center">
  	        <html:submit  value="Add DGM" styleClass="button"/>
  	        &nbsp;&nbsp;
            </div>
        </html:form>
		
    </body>
	
	
 <jsp:include page="<%=foot%>"/> 
</html:html>

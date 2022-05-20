<%@ page import="com.aristo.valueobject.LoginFormBean" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

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
    %> 
 
    <br/><br/> 
        <display:table id="data" name="sessionScope.TerForm.tlist" requestURI="./ListBranch.do?actionRequested=ListBranch" pagesize="15" export="true" >
        <display:caption media="html"><strong><br/>BRANCH MASTER</strong></display:caption>
        <display:caption media="excel rtf pdf">BRANCH MASTER</display:caption>
        <display:column property="depo_code" title="BRANCH CODE" style="Width:12%"  />
        <display:column property="ter_name" title="BRANCH NAME"  />
        <display:column property="no_of_rep" title="F.S."  />
<display:column property="link2" title="Action" href="EditBranch.do?actionRequested=EditBranch" paramId="depo_code" paramProperty="depo_code" style="Width:10%"  />
</display:table>

    <html:form action="AddBranchForward.do?actionRequested=AddBranchForward">
		<div align="center">
		  <html:submit  value="Add Branch" styleClass="button"/>
		</div>
    </html:form>

    </body>
 <jsp:include page="<%=foot%>"/> 
</html:html>
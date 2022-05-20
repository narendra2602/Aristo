<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

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
   
    <br/> <br/>
   	<html:form action="PmtRightsList.do?actionRequested=PmtRightsList" >    	
	<table class="search">
      <tr>
	    <td>
			<html:text property="search" />
			<html:submit value="Search" styleClass="button"/> 
	    </td>
	  </tr>
	</table>
   </html:form>		
 
    <display:table id="data" name="requestScope.LoginForm.ulist" requestURI="./PmtRightsList.do?actionRequested=PmtRightsList" pagesize="20" export="true">
	<display:caption media="html"> <strong><br/>PMT USER RIGHTS LIST </strong> </display:caption>
	<display:caption media="excel rtf pdf">PMT USER RIGHTS LIST</display:caption>
	<display:column property="id" title="User ID" />         
	<display:column property="login_name" title="Login Name" />
	<display:column property="f_name" title="First Name"/>			
	<display:column property="l_name" title="Last Name"/>
	<display:column property="link1" title="Action" href="PmtRightsEdit.do?actionRequested=PmtRightsEdit" paramId="id" paramProperty="id" />			

    </display:table>
    </body>
	
		<br/><br/><br/>


 <jsp:include page="<%=foot%>"/> 

</html>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
	   
    <%    
		String whead=" ";
		LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
		String x = lfb.getBranch_name();
		String y = lfb.getAccess_t();         
		x = x + " Branch "+y;
		whead="["+x+"] " +" " + "User List";
    %>
<br/><br/>

   	<html:form action="UserStatus.do?actionRequested=UserStatus" >    	
	<table class="search">
      <tr>
	    <td> Status :
			<html:select property="status" >
			    <html:option value="Yes">Active</html:option>
			    <html:option value="No">In-Active</html:option>
			</html:select>				
			
			<html:text property="search" />
			<html:submit value="Search" styleClass="button"/> 
	    </td>
	  </tr>
	</table>
   </html:form>		
<display:table name="requestScope.LoginForm.ulist" requestURI="./UserStatus.do?actionRequested=UserStatus" pagesize="15" export="true"> 

       <display:caption media="excel rtf pdf html"> USER LOGIN DETAILS</display:caption>			
       <display:caption media="html"><br/> USER LOGIN DETAILS</display:caption>			
            <display:column property="login_name" title="Login Name" />
            <display:column property="f_name" title="Name"/>			
            <display:column property="l_name" title="Last Name"/>
            <display:column property="department" title="Department"/>
        	<display:column property="location" title="Location"/>			
            <display:column property="access_t" title="Access Type"/>
            <display:column property="status" title="Status"/>			
            <display:column property="last_ldate" title="Last Login"/>
  </display:table>
		
</body>
<p>&nbsp;</p>				
<jsp:include page="<%=foot%>"/> 
</html>

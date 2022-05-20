<%@ page import="com.aristo.valueobject.LoginFormBean" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tags/alphanavbar" prefix="nav" %>

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
           response.setHeader("Cache-Control","no-store");  
        %> 

<br/>
<br/>
 <table class="abded">
 <tr>
 <td>
 <nav:alphanavbar keycolumn="gp_name" name="sessionScope.GroupSalesForm.gpsales" />
 </td>
 </tr>
 </table>
 <display:table name="requestScope.alphabeticalsublist" pagesize="15" export="true">

 <display:caption media="html"><strong><br/>GROUP SALES MASTER<br/></strong></display:caption>
 <display:caption media="excel rtf pdf">GROUP SALES MASTER</display:caption>
 <display:column property="gp_code" title="Group Code" style="width: 10%" />
 <display:column property="gp_name" title="Group Name" />
 <display:column property="link1" title="Action" href="EditGrpSales.do?actionRequested=EditGrpSales" paramId="gp_code" paramProperty="gp_code" style="width: 15%" />						
        </display:table>

     <html:form action="AddGroupSalesForward.do?actionRequested=AddGroupSalesForward">
  	        <div align="center">
  	           <html:submit  value="Add Group" styleClass="button"/>
  	        </div>
     </html:form>

    </body>
   
 <jsp:include page="<%=foot%>"/> 
</html:html>
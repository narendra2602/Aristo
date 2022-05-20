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
    
     <br/><br/>
	 <table class="abded">
	 <tr>
	 <td>
	 <nav:alphanavbar keycolumn="pname" name="sessionScope.ProductForm.plist" />
	 </td>
	 </tr>
	 </table>
	 
	 <display:table name="requestScope.alphabeticalsublist" pagesize="15" export="true">

        <display:caption media="html"> <strong><br/>PRODUCT LIST <br/> </strong></display:caption>
        <display:caption media="excel rtf pdf">PRODUCT LIST</display:caption>
		<display:column property="pcode" title="Product Code" />
		<display:column property="pname" title="Product Name"/>
		<display:column property="pack" title="Pack"/>
		<display:column property="pd_group" title="Product Group" /> 
		<display:column property="mname" title="Group Name" />
		<display:column property="mcode" title="Mkt. Code"  headerClass="r" class="r"/>
		<display:column property="mgrp" title="Mkt. Group"   headerClass="r" class="r"/>
	    <display:column property="link1" title="Action" href="EditProduct.do?actionRequested=EditProduct" paramId="pcode" paramProperty="pcode" />			
		</display:table>

    <html:form action="AddProductForward.do?actionRequested=AddProductForward">
  	      <div align="center">
  	        <html:submit  value="Add Product" styleClass="button"/>
  	        &nbsp;&nbsp;
            </div>
    </html:form>

    </body>

 <jsp:include page="<%=foot%>"/> 
</html:html>

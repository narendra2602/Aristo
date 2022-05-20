<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>
<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>
 <jsp:include page="<%=toop%>"/> 


<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


  <html:form action="OptForm6.do?actionRequested=OptForm6">
              
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
	
		<br/>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="usertable">
	      <th colspan="3">Target Allocation </th>
	  <tr>
		<td>&nbsp;</td>
		<td><html:radio  property="rep_type" value="1" />H.Q.&nbsp; &nbsp; &nbsp;</td>
		<td>&nbsp;</td>
	  </tr>
	  <tr>
		<td>&nbsp;</td>
		<td><html:radio property="rep_type"  value="2" />Region</td>
		<td>&nbsp;</td>
	  </tr>
	  <tr>
		<td>&nbsp;</td>
		<td><html:radio property="rep_type"  value="3" />Area &nbsp; &nbsp; </td>
		<td>&nbsp;</td>
	  </tr>
	  <tr>
		<td>&nbsp;</td>
		<td><html:radio property="rep_type"  value="4"/>Branch</td>
		<td>&nbsp;</td>
	  </tr>
	  <tr>
		<td colspan="3"><html:submit  value="Submit" styleClass="button"/></td>
	  </tr>
		</table>    
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<br/>
  </html:form>
              
</body>

 <jsp:include page="<%=foot%>"/> 

</html:html>

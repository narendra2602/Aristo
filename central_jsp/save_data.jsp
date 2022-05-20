<%@ page import="java.util.*" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<html:html>

<head>
<% 
	String top  = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>
<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 

<style type="text/css">
<!--
.style1 {font-size: 14px}
-->
</style>
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
 
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


<html:form action="FileUpdateCentral.do">

<table cellpadding="0" cellspacing="0" class="upload">
  <tr>
    <th> <div align="center">Upload XML Files </div></th>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td>
      <div align="center">
        <%
     List flist = (List) request.getAttribute("fdata");
     session.setAttribute("ffdata",flist);
     %>
        <span class="style1">  File has been Uploaded  Successfully</span> </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td> <div align="center">
      <html:submit style="font-weight:bolder; font-size:16px; height:40; width:330px; " value="Click here to Update Data on Server"  onclick='this.disabled=true;this.form.submit();' />
    </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<blockquote>&nbsp;</blockquote>
</html:form>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<br/>
</body>
<jsp:include page="<%=footer%>"/> 


</html:html>

<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>
<%
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>


<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link href="./css/<%=css%>" rel="stylesheet" type="text/css" />

</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<img src="images/head1.gif" width="100%" height="87">
  <html:form action="UserForward.do?actionRequested=UserForward">
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
	  <center>
<table cellpadding="0" cellspacing="0" class="switch">
         <tr>
           <th colspan="2">Select Type</th>
         </tr>
         <tr>
           <td width="60">&nbsp;</td>
           <td width="50"> <html:radio property="access_t" value="MF" /> MF </td> 
         </tr>
         <tr>
           <td>&nbsp;</td>
           <td> <html:radio property="access_t" value="TF" /> TF </td> 
		 </tr>
         <tr>
           <td>&nbsp;</td>
           <td> <html:radio property="access_t" value="Genetica"/> Genetica </td> 
		 </tr>
         <tr>
           <td colspan="2"> <div align="center"> <html:submit property="Submit" value="Submit" styleClass="button"/> </div></td>
         </tr>
      </table>
	  </center>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
	  <br/>
	  
  </html:form>
  </body>
 <jsp:include page="<%=footer%>" /> 
</html:html>
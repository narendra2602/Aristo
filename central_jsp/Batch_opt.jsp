<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<head>
<% 
	String top = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>

<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 

</head>
	
<body>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="ListBatch.do?actionRequested=ListBatch">
  
  <table cellpadding="0" cellspacing="0" class="upload">
    <tr>
      <th colspan="4">Batch Master</th>
    </tr>
    
	<tr>
	  <td colspan="4">&nbsp;</td>
	  </tr>
	<tr>
      <td colspan="4">&nbsp;</td>
    </tr>

    <tr>
      <td width="10">&nbsp;</td>
      <td width="18">&nbsp;</td>
      <td width="93">Select Product</td>
      <td width="96">
	
	  <html:select property="man">
	        <html:optionsCollection name="BatchMasterForm" property="plist" label="pname" value="pval" />   
	  </html:select>	  </td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>

    <tr>
      <td colspan="4">
	  <div align="center"> 
	  <html:submit styleClass="button" value="Submit" /> 
	  &nbsp;
	  <html:reset styleClass="button" value="Cancel" />
	  </div>	  </td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
  </table>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
  </html:form>
</body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
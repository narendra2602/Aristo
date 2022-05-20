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

<script src="js/calendar.js" type="text/javascript" language="javascript"></script>
<link href="css/calendar.css" rel="stylesheet" type="text/css" />
<script src="js/DateValid.js" type="text/javascript" language="javascript"></script>

<jsp:include page="<%=top%>"/> 
 </head>
	
<body>

	<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="CentralInventoryRepo10.do?actionRequested=CentralInventoryRepo10" onsubmit="return validateDate()">
  
  <table cellpadding="0" cellspacing="0" class="upload">
    <tr>
      <th colspan="4">Date  Wise Stock Value</th>
    </tr>
	
	<tr>
      <td colspan="4">&nbsp;</td>
    </tr>

    <tr>
      <td width="10">&nbsp;</td>
      <td width="18">&nbsp;</td>
      <td width="73">Start Date:</td>
      <td width="96"><html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>End Date:</td>
	  <td><html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" /> </td>
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
	  </div>
	  </td>
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
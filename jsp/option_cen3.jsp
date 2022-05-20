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
<script src="js/DateValid.js" type="text/javascript" language="javascript"></script>

<jsp:include page="<%=top%>"/> 

 

</head>
	
<body>

	<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="ListCen3.do?actionRequested=ListCen3" onsubmit="return validateDate()">
  
  <center>
  <table align="center" cellpadding="0" cellspacing="0" class="usertable">
    <tr>
      <th colspan="4">GOODS IN TRANSIT </th>
    </tr>
    
	<tr>
      <td colspan="4">&nbsp;</td>
    </tr>
	



    <tr>
      <td width="10">&nbsp;</td>
      <td width="18">&nbsp;</td>
      <td width="73"><div align="left">Start Date:</div></td>
      <td width="96"><html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
    </tr>
	
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td width="73"><div align="left">End Date:</div></td>
	  <td width="96"> <html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" /> </td>
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
  </center>
  </br>  </br>   
    <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
  </html:form>
</body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
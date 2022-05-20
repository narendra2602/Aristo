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

<jsp:include page="<%=top%>"/> 
<script type="text/javascript">
function validateDate()
    {
    var SDate = document.forms[0].sdate.value;    	
    var startDate= new Date(SDate);
    var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
 
    if(SDate == '')	
    {
        alert("Please enter Date");
		document.forms[0].sdate.focus();
        return false;
    }

    if (!validformat.test(SDate))
	{
    alert("Invalid Date Format. Please correct and submit again.")
	return false;
    }
}
</script>

</head>
	
<body>

	<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>

  <br/>
<html:form action="SampleListRepo3.do?actionRequested=SampleListRepo3" onsubmit="return validateDate()">
  
 <center>
 <table align="center" cellpadding="0" cellspacing="0" class="usertable">
    <tr>
      <th>Near Expiry List</th>
    </tr>
	


    <tr>
      
      <td> Select Date: &nbsp;&nbsp;&nbsp;
        <html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
      </tr>	

    <tr>
      <td>
	  <div align="center"> 
	  <html:submit styleClass="button" value="Submit" /> 
	  &nbsp;
	  <html:reset styleClass="button"  onclick="window.location.href='./jsp/Home.jsp'" value="Cancel" />
	  </div>	  </td>
    </tr>
  </table>
  </center>
  
  </html:form>
</body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
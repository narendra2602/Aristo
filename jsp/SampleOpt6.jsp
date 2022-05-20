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
    var EDate = document.forms[0].edate.value;    	
    var alertReason1 =  'End Date must be greater than or equal to  Start Date.' 
    var alertReason2 =  'End Date can not be less than Current Date.';
    var endDate = new Date(EDate);    	
    var startDate= new Date(SDate);
    var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
 
    if(SDate == '')	
    {
        alert("Please enter Start Date");
        return false;
    }
    else if(EDate == '')	
    {
        alert("Please enter End Date");
        return false;
    }	    
    if ((!validformat.test(SDate)) || (!validformat.test(EDate)))
	{
    alert("Invalid Date Format. Please correct and submit again.")
	return false;
    }
	if(SDate != '' && EDate != '' && startDate > endDate)
    {
	    alert(alertReason1);
		document.forms[0].edate.value='';
		document.forms[0].edate.focus();
	    return false;
    }
}
</script>
</head>
	
<body>

<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="SampleListRepo6.do?actionRequested=SampleListRepo6" onsubmit="return validateDate()">
  
  <center>
  <table align="center" cellpadding="0" cellspacing="0" class="usertable">
    <tr>
      <th>Transfer Register</th>
    </tr>
    
    <tr>
      <td>Start Date: &nbsp;&nbsp;&nbsp;
      <html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
    </tr>
	
    <tr>
      
      <td>End Date: &nbsp;&nbsp;&nbsp;&nbsp; <html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" /> </td>
    </tr>	

    <tr>
      <td>
	  <div align="center"> 
	  <html:submit styleClass="button" value="Submit" /> 
	  &nbsp;
	  <html:reset styleClass="button" value="Cancel" />
	  </div>	  
	  </td>
    </tr>
    
  </table>
  </center>
   </html:form>
 </body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
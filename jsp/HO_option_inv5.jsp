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
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="HOListInv5.do?actionRequested=HOListInv5" onsubmit="return validateDate()">
  <table align="center" cellpadding="0" cellspacing="0" class="usertable">
    <tr>
      <th colspan="2">Stock Analysis As On</th>
    </tr>
	
    
    <tr>
     
      <td><div align="left" style="padding-left: 120px;">Product:</div></td>
      <td><div align="left">
          <html:select property="code" styleId="mySelect">
            <html:optionsCollection name="SampleRepo3Form" property="rlist" label="pname" value="pcode" />
          </html:select>
      </div></td>
    </tr>
    <tr>
     
      <td><div align="left" style="padding-left: 120px;">Select Date:</div></td>
      <td><div align="left">
          <html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />
      </div></td>
    </tr>
    <tr>
      <td colspan="2"><div align="center">
          <html:submit styleClass="button" value="Submit" />
        &nbsp;
        <html:reset styleClass="button" value="Cancel" />
      </div></td>
    </tr>
  </table>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
  </html:form>
</body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
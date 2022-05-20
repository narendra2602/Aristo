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
<script type="text/javascript">
function makeDisable(){
    var x=document.getElementById("mySelect")
    x.disabled=true
}
function makeEnable(){
    var x=document.getElementById("mySelect")
    x.disabled=false
}
</script>
 

</head>
	
<body>

	<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="ListCen5.do?actionRequested=ListCen5" onsubmit="return validateDate()">
  
  <center>
  <table align="center" cellpadding="0" cellspacing="0" class="usertable">
    <tr>
      <th colspan="4">PRODUCT WISE DISPTACH </th>
    </tr>

      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><div align="left">Selection:</div></td>
        <td><div align="left"> 
				<html:radio property="choice" value="1" onclick="makeEnable()" /> Selective   &nbsp;
				<html:radio property="choice" value="2" onclick="makeDisable()" /> All
		   </div> 
		</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><div align="left">Product</div></td>
        <td><div align="left">
		      <html:select property="code" styleId="mySelect">
	              <html:optionsCollection name="Cen5Form" property="plist" label="pname" value="pval" />   
              </html:select>
		    </div>
		</td>
      </tr>
      <tr>
      <td width="10">&nbsp;</td>
      <td width="18">&nbsp;</td>
      <td width="73"><div align="left">Start Date:</div></td>
      <td width="96"><div align="left">
        <html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  
      </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><div align="left">End Date:</div></td>
	  <td><div align="left">
	    <html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" /> 
	    </div></td>
    </tr>	


    <tr>
      <td colspan="4">
	  <div align="center"> 
	  <html:submit styleClass="button" value="Submit" /> 
	  &nbsp;
	  <html:reset styleClass="button" value="Cancel" />
	  </div>	  </td>
    </tr>
  </table>
  </center>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
  </html:form>
</body>
<jsp:include page="<%=footer%>"/> 	
</html:html>
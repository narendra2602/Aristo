<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>
<head>

<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<script language="javascript">
function check()
{

var v1 = document.forms[0].login_name.value;
var v2 = document.forms[0].password.value;

if (v1=="")
{
alert ("Login Name can not be Blank");
document.forms[0].login_name.focus();
return false;
}

if (v2=="")
{
alert ("Password can not be Blank");
document.forms[0].password.focus();
return false;
}

return true;
}
</script>

<script type="text/javascript" src="./js/TransparentPng.js"></script>	


<link href="./css/login.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>

<div id="wrapper">

  <div id="main" class="clearfix">
    <div id="header">

		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="100%" height="85">
			  <param name="movie" value="intro.swf">
			  <param name="quality" value="high">
			  <embed src="intro.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100%" height="85"></embed>
			</object>
			</td>
		  </tr>
		</table>
   
   </div> <!-- header -->

<div id="content">

  <html:form  action="authenticateUser.do?actionRequested=authenticateUser" onsubmit="return check();">
    <table width="450" height="355" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
	<td height="266" background="images/back.gif"><table width="66%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="7%">&nbsp;</td>
        <td width="38%">&nbsp;</td>
        <td width="40%">&nbsp;</td>
        <td width="15%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td height="25">&nbsp;</td>
  <td>
<html:text style="border: 2px solid #DB261B; background-color:#E6C1BB; color:#000000; width:150px;" property="login_name" size="20" />        
</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><div align="left">
    <html:password style="border: 2px solid #DB261B; background-color:#E6C1BB; color:#000000; width:150px;" property="password"  size="20" value=""/>
        </div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td> <div align="right">
          <html:submit value="Login" styleClass="button"/>   
        </div></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
	  </tr>
  </table>
  </html:form>
  </div>
    </div><!-- / main content -->
</div><!-- /wrapper -->
	
<div id="footer">
<%@include file="log_footer.jsp" %>  
 </div><!-- / footer -->
  
</body>

</html:html>

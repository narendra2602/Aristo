<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link href="./css/multibox.css" rel="stylesheet" type="text/css" />
<link href="./css/body.css" rel="stylesheet" type="text/css" />

<script language="javascript" src="./js/mootools.js"></script>
<script language="javascript" src="./js/multibox.js"></script>

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

<style type="text/css">
<!--
body {
	background-image: url(./images/backimage.jpg);
	background-repeat: no-repeat;
}
-->
</style>

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<img src="./images/head1.jpg" width="100%" height="85" />

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<br/>
<html:form action="authenticateUser.do?actionRequested=authenticateUser" onsubmit="return check();">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="log">
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="41%" height="26">&nbsp;</td>
    <td width="8%">&nbsp;</td>
    <td width="15%">&nbsp;</td>
    <td width="36%">&nbsp;</td>
  </tr>
  <tr>
    <td height="34" align="left" valign="middle">&nbsp;</td>
    <td align="left" valign="middle">&nbsp;</td>
    <td colspan="2" align="left" valign="middle"><html:text property="login_name" /> </td>
    </tr>
  <tr>
    <td height="30" align="left" valign="middle">&nbsp;</td>
    <td align="left" valign="middle">&nbsp;</td>
    <td colspan="2" align="left" valign="middle"><html:password property="password" /></td>
    </tr>
    <tr>
    <td height="19">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="baseline"><div align="right">&nbsp;<html:submit value="Login" styleClass="button"/>
      </div></td>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<br/>
<jsp:include page="footer.jsp"/> 	
</html:form>
</body>
</html:html>
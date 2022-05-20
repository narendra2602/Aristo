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

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFEFEC;
}
.style3 {
	color: #FFFFFF;
	font-weight: bold;
	font-family: Poppins, sans-serif;;
	font-size: 12px;
}
.style14 {font-family: Poppins, sans-serif;; font-size: 12px; }
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
-->
</style>

<link href="./css/login.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="100%" height="85">
      <param name="movie" value="intro.swf">
      <param name="quality" value="high">
      <embed src="intro.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100%" height="85"></embed>
    </object></td>
  </tr>
</table>
  

    <html:form  action="authenticateUser.do?actionRequested=authenticateUser" onsubmit="return check();">
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <br>
              <table width="37%" height="131" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border: 1px solid">
                <tr bgcolor="#ECE9D8">
                  <td height="25" colspan="2" bgcolor="#DB261B"><div align="center" class="style3">Login</div></td>
                </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4">&nbsp;</td>
                  <td bgcolor="#FFDAD4">&nbsp;</td>
                </tr>
                <tr>
                  <td width="47%" height="19" bgcolor="#FFDAD4"><span class="style14"> &nbsp; &nbsp; &nbsp; &nbsp; Login Name </span></td>
                  <td width="53%" bgcolor="#FFDAD4"><html:text property="login_name"   size="20" /></td>
                </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4"><span class="style14"> &nbsp; &nbsp; &nbsp; &nbsp; Password</span></td>
                  <td bgcolor="#FFDAD4"><html:password property="password"  size="20"/></td>
                </tr>
                <tr>
                  <td height="44" colspan="2" valign="middle" bgcolor="#FFDAD4"><div align="center" class="style14">
               
                      <html:submit value="Submit" styleClass="button"/>
                      <html:reset value="Cancel" styleClass="button"/>
                    </div>
                    <div align="center"></div>
                  </div>
                  <div align="center" class="style14"></div></td>
                </tr>
      </table>

<table height="150">
<tr>
<td width="10">&nbsp;</td>
</tr>
</table>





    </html:form>
	
</body>
<jsp:include page="log_footer.jsp"/>
</html:html>

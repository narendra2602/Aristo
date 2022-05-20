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
}
.style3 {
	color: #FFFFFF;
	font-weight: bold;
	font-family: Poppins, sans-serif;;
	font-size: 12px;
}
.style14 {font-family: Poppins, sans-serif;; font-size: 12px; }
.style15 {
	color: #000000;
	font-size: 11px;
}
.style23 {font-family: Poppins, sans-serif;; font-size: 11px; font-weight: bold; }
.style24 {font-size: 11px}
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="3"><img src="./images/topbas.jpg" width="100%" height="85"></td>
  </tr>
  <tr>
    <td width="22%" align="left" valign="top"><img src="./images/leftcorve.jpg" width="200" height="324"></td>
    <td width="58%">
    <html:form  action="authenticateUser.do?actionRequested=authenticateUser" onsubmit="return check();">
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <br>
      <table width="95%" height="167" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div align="center"></div>            <div align="right">
              <table width="85%" height="153" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr bgcolor="#ECE9D8">
                  <td height="25" colspan="2" bgcolor="#E25B58"><div align="center" class="style3">Login</div></td>
                </tr>
                <tr>
                  <td width="47%" bgcolor="#FFBAB0"><span class="style23">&nbsp; &nbsp; &nbsp; Login Name </span></td>
                  <td width="53%" bgcolor="#FFBAB0"><html:text property="login_name"   size="20" /></td>
                </tr>
                <tr>
                  <td bgcolor="#FFBAB0"><span class="style23">&nbsp; &nbsp; &nbsp; Password</span></td>
                  <td bgcolor="#FFBAB0"><html:password property="password"  size="20"/></td>
                </tr>
                <tr>
                  <td bgcolor="#FFBAB0"><span class="style23"> &nbsp; &nbsp; &nbsp; Login As </span></td>
                  <td bgcolor="#FFBAB0"><span class="style14">
                    <html:select property="type" >
                      <html:option value="Branch">Branch</html:option>
                      <html:option value="Zonal">Zonal </html:option>
                      <html:option value="All">All Branch</html:option>
                      <html:option value="Admin">Administrator</html:option>
                      </html:select>
                    </span> </td>
                </tr>
                <tr>
                  <td height="44" colspan="2" valign="middle" bgcolor="#FFBAB0"><div align="center" class="style14">
               
                      <html:submit value="Submit"/>
                      <html:reset value="Cancel"/>
                    </div>
                    <div align="center"></div>
                  </div>
                    <div align="center" class="style14"></div></td>
                </tr>
                </table>
            </div></td>
          </tr>
      </table>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
    </html:form></td>
    <td width="22%" valign="bottom"><div align="right"><img src="./images/scorve.jpg" width="190" height="324"></div></td>
  </tr>
</table>
</body>
<jsp:include page="footer.jsp"/>
</html:html>

<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>
<head>

<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

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
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
}
.style14 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; }
.style15 {
	color: #000000;
	font-size: 11px;
}
.style23 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 11px; font-weight: bold; }
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
    <td width="20%" align="left" valign="top">&nbsp;</td>
    <td width="58%">
    <html:form  action="authenticateUser.do?actionRequested=authenticateUser">
     <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <br>
      <table width="95%" height="163" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="155"><div align="center"></div>            <div align="right">
              <table width="75%" height="126" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border: 1px solid">
                
                <tr>
                  <td height="25" bgcolor="#FFDAD4">&nbsp;</td>
                  </tr>
                <tr>
                  <td bgcolor="#FFDAD4" class="style14"><div align="center"><strong>Your Session Has  Expired </strong></div></td>
                  </tr>
                <tr>
                  <td height="51" bgcolor="#FFDAD4" class="style14"><div align="center"><a href="/Aristo/LoginUserForward.do?actionRequested=LoginUserForward">Click Here to Login Again</a> </div></td>
                  </tr>
                <tr>
                  <td valign="middle" bgcolor="#FFDAD4">&nbsp;</td>
                </tr>
                </table>
            </div></td>
          </tr>
      </table>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
    </html:form></td>
    <td width="22%" valign="bottom"><div align="right"></div></td>
  </tr>
</table>
</body>
<jsp:include page="log_footer.jsp"/>

</html:html>

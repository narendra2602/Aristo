<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>
<head>

<link href="../css/ddsmoothmenu.css" rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
body {
	background-color: #F5F5F1;
	background-image: url(../images/logback.jpg);
	background-repeat: repeat;
}
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
-->
</style>



</head>

<body topmargin="0" leftmargin="0" rightmargin="0" marginheight="0" marginwidth="0" bottommargin="0">
<p>&nbsp;  </p>
  <html:form  action="authenticateUser.do?actionRequested=authenticateUser" >

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="3"><div align="center"><strong>Login</strong></div>        <div align="center"></div></td>
    </tr>
    <tr>
      <td width="14%">&nbsp;</td>
      <td width="30%">&nbsp;</td>
      <td width="56%">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Login Name: </strong></td>
 	  <td><html:text property="login_name" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Password:</strong></td>
      <td><html:password property="password" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><html:submit value="Login" styleClass="button"/></td>
    </tr>
  </table>
</html:form>
</body>
</html:html>
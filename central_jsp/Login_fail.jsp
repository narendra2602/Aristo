<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="./css/body.css" rel="stylesheet" type="text/css" />
</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<img src="./images/head1.jpg" width="100%" height="85" />

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table class="upload" cellpadding="0" cellspacing="0">
    <tr>
      <th>Your login failed.</th>
    </tr>
    <tr>
      <td><br/>Incorrect Username / Incorrect password</td>
    </tr>
    <tr>
      <td><a href="/Central/LoginUserForward.do?actionRequested=LoginUserForward">Click Here to Login Again</a></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;	</p>
  <p>&nbsp;</p>
</body>
<jsp:include page="footer.jsp"/>
</html:html>
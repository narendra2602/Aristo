<%@ page import="java.util.*" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<html:html>

<head>
<title>Success</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"><style type="text/css">
<!--
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
.style3 {font-size: 20px}
.style1 {	color: #FFFFFF;
	font-weight: bold;
	font-size: 14px;
}
.style5 {
	font-size: 14px;
	font-weight: bold;
}
body {
	background-color: #FFEFEC;
}
-->
</style>
<script language="javascript">
function abc()
{
window.close();
}
</script>



</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
 
<p><img src="./images/topbas.jpg" width="100%" height="85"></p>
<p>&nbsp;</p>
<p>&nbsp;</p>

 <html:form action="FileUpdate.do">

<p>&nbsp;</p>
<table width="45%" height="178" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border: 1px solid">
  <tr>
    <th height="31" bgcolor="#DB261B" scope="col"><span class="style1">Upload XML Files</span></th>
  </tr>
  <tr>
    <th height="24" bgcolor="#FFDAD4" scope="col"><div align="center"></div></th>
  </tr>
  <tr>
    <td bgcolor="#FFDAD4"><div align="center"><font size="5" color="#000080">
    <%
     List flist = (List) request.getAttribute("fdata");
     session.setAttribute("ffdata",flist);
   %>
</font><font color="#000080">    <span class="style3">File has been Uploaded  Successfully</span></font></div></td>
  </tr>
  <tr>
    <td height="19" bgcolor="#FFDAD4"><div align="center"></div></td>
  </tr>
  
  <tr>
    <td height="19" bgcolor="#FFDAD4"></td>
  </tr>
  <tr>
    <td height="18" bgcolor="#FFDAD4"><div align="center" class="style5">
      <html:submit style="font-weight:bolder; font-size:16px; height:40; width:330px; " value="Click here to Update Data on Server"  onclick='this.disabled=true;this.form.submit();abc()' />
    </div></td>
  </tr>
  <tr>
    <td bgcolor="#FFDAD4">&nbsp;</td>
  </tr>
</table>
</html:form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<br/>
</body>
<jsp:include page="log_footer.jsp"/>

</html:html>

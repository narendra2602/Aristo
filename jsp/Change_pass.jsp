<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");

%>
 <jsp:include page="<%=toop%>"/> 



<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

<script language="javascript">
function check()
{

var v1=document.forms[0].password.value;
var v2=document.forms[0].n_password.value;
var v3=document.forms[0].c_password.value;

if (v1=="")
{
alert ("Old Password Cannot be Blank");
document.forms[0].password.focus();
return false;
}

if (v2=="")
{
alert ("New Password Cannot be Blank");
document.forms[0].n_password.focus();
return false;
}

if (v3=="")
{
alert ("Confirm Password Cannot be Blank");
document.forms[0].c_password.focus();
return false;
}


return true;
}
</script>


</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


              
              <html:form action="Changepass.do?actionRequested=Changepass" onsubmit="return check();">
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
				<br/>
                <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable1">
                  <th colspan="6">
                  Change Password</th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="39%">&nbsp;</td>
                    <td width="38%">&nbsp;</td>
                    <td width="12%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Old Password </td>
                    <td><html:password property="password" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>New Password</td>
                    <td><html:password property="n_password" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Confirm Password</td>
                    <td><html:password property="c_password" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="4">
					                <div align="center">
									  <html:submit  value="Submit" styleClass="button"/>&nbsp;&nbsp;
									  <html:reset  value="Cancel" styleClass="button"/>                    
                                    </div>					 </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>

				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<br/>
          </html:form>
		  
		  
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

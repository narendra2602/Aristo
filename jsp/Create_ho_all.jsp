<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


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

var v1=document.forms[0].f_name.value;
var v2=document.forms[0].l_name.value;
var v3=document.forms[0].login_name.value;
var v4=document.forms[0].password.value;
var v5=document.forms[0].c_password.value;
var v6=document.forms[0].desig.value;

if (v1=="")
{
alert ("First Name Cannot be Blank");
document.forms[0].f_name.focus();
return false;
}

if (v2=="")
{
alert ("Last Name Cannot be Blank");
document.forms[0].l_name.focus();
return false;
}

if (v3=="")
{
alert ("Login Id Cannot be Blank");
document.forms[0].login_name.focus();
return false;
}

if (v4=="")
{
alert ("Password Cannot be Blank");
document.forms[0].password.focus();
return false;
}

if (v5=="")
{
alert ("Confirm Password Cannot be Blank");
document.forms[0].c_password.focus();
return false;
}

if (v6=="")
{
alert ("Please Select Designation");
document.forms[0].desig.focus();
return false;
}

if (v4 != v5 )
{
alert ("Password & Confirm Password Does Not Match");
document.forms[0].password.focus();
return false;
}

return true;
}

</script>

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


              
              <html:form action="addUser.do?actionRequested=addUser" onsubmit="return check();">
   
                <p>&nbsp;</p>
                <br/><br/>
                <table align="center" border="0" cellspacing="0" cellpadding="0" class="usertable1">
                <th colspan="6">
                  Create User                  </th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="39%">&nbsp;</td>
                    <td width="38%">&nbsp;</td>
                    <td width="12%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>First Name</td>
                    <td><html:text property="f_name" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Last Name</td>
                    <td><html:text property="l_name" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Login Id </div></td>
                    <td><html:text property="login_name" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Password</td>
                    <td><html:password property="password" /></td>
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
                    <td>Designation</td>
                    <td><html:text property="desig" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="4"><div align="center">
                      <html:submit value="Submit" styleClass="button"/>
                                   
                      <html:reset value="Cancel" styleClass="button"/>                    
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
				<br/>
          </html:form>
		  
		  
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

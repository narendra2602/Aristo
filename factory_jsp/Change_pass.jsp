<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

 <jsp:include page="F_top.jsp"/> 

<head>


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


              
  <html:form action="Changepass1.do?actionRequested=Changepass1" onsubmit="return check();">
    <p>&nbsp;</p> 
    <p>&nbsp;</p>
	<br/>
	<center>
    <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_opt">
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
    </table>
    </center>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<br/>
    </html:form>
		  
		  
</body>
 <jsp:include page="F_footer.jsp"/> 
</html:html>

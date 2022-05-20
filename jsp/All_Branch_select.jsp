<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>
<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>




<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<style type="text/css">

.menu3 {padding:0 0 0 32px; margin:0; list-style:none; height:35px; background:#fff url(three_0.gif); position:relative; border:1px solid #000; border-width:0 1px; border-bottom:1px solid #444;}
.menu3 li {float:left;}
.menu3 li a {display:block; float:left; height:35px; line-height:35px; color:#aaa; text-decoration:none; font-size:11px; font-family:Poppins, sans-serif; font-weight:bold; text-align:center; padding:0 0 0 8px; cursor:pointer;}
.menu3 li a b {float:left; display:block; padding:0 16px 0 8px;}
.menu3 li.current a {color:#fff; background:url(three_2.gif);}
.menu3 li.current a b {background:url(three_2.gif) no-repeat right top;}
.menu3 li a:hover {color:#fff; background:#000 url(three_1.gif);}
.menu3 li a:hover b {background:url(three_1.gif) no-repeat right top;}
.menu3 li.current a:hover {color:#fff; background:#000 url(three_2.gif); cursor:default;}
.menu3 li.current a:hover b {background:url(three_2.gif) no-repeat right top;}

</style>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFEFEC;
}
.style32 {font-size: 15px; color: #FFFFFF; font-weight: bold; font-family: Poppins, sans-serif;}
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
.style36 {font-size: 12px}
-->
</style>
<link href="./css/login.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="519" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="85" colspan="3"><img src="./images/topbas.jpg" width="100%" height="85"></td>
  </tr>
  <tr>
    <td width="20%" align="left" valign="top">&nbsp;</td>
    <td width="58%"><html:form action="AllBranchSelect.do?actionRequested=AllBranchSelect">
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="95%" height="167" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div align="center"></div>            <div align="right">
              <table width="57%" height="96" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border:1px solid">
                <tr bgcolor="#ECE9D8">
                  <td height="20" bgcolor="#DB261B"><div align="center" class="style32 style36">Select Branch </div></td>
                </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4">
						    <div align="center">				  
				            <html:select property="code" size="10" style="width:150px">
   					        <html:optionsCollection name="LoginForm" property="tlist" label="dname" value="dcode" />  
				  </html:select> </div>				  </td> 
                  </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="19" bgcolor="#FFDAD4"><div align="center"> <html:submit  value="Submit" styleClass="button"/>
                      <html:reset value=" Back " onclick="window.location.href='./jsp/switch.jsp'" styleClass="button"/>
				   </div> </td>
                </tr>
                <tr>
                  <td bgcolor="#FFDAD4">&nbsp;</td>
                </tr>
                </table>
            </div></td>
          </tr>
      </table>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      </html:form></td>
    <td width="22%" valign="bottom">&nbsp;</td>
  </tr>
</table>
</body><br/><br/>
 <jsp:include page="log_footer.jsp"/> 
</html:html>

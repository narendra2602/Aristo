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
.menu3 li a {display:block; float:left; height:35px; line-height:35px; color:#aaa; text-decoration:none; font-size:11px; font-family:arial, verdana, sans-serif; font-weight:bold; text-align:center; padding:0 0 0 8px; cursor:pointer;}
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
.style28 {font-size: 14px; font-weight: bold; }
.style29 {font-size: 14px; font-family: Poppins, sans-serif;; font-weight: bold; }
.style32 {font-size: 15px; color: #FFFFFF; font-weight: bold; font-family: Arial, Helvetica, sans-serif;}
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
.style33 {color: #FF0000; font-weight: bold; font-family: Poppins, sans-serif;; font-size: 14px; }
.style34 {color: #4E7526; font-weight: bold; font-family: Poppins, sans-serif;; font-size: 14px; }
.style35 {color: #872980; font-weight: bold; font-family: Poppins, sans-serif;; font-size: 14px; }
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="./css/login.css" rel="stylesheet" type="text/css" />

</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="519" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="85" colspan="3"><img src="./images/topbas.jpg" width="100%" height="85"></td>
  </tr>
  <tr>
    <td width="20%" align="left" valign="top">&nbsp;</td>
    <td width="58%"><html:form action="UserForwardDGM.do?actionRequested=UserForwardDGM">
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="95%" height="167" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div align="center"></div>            <div align="right">
              <table width="57%" height="158" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border:1px solid">
                <tr bgcolor="#ECE9D8">
                  <td height="20" colspan="2" bgcolor="#DB261B"><div align="center" class="style32">Select</div></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#FFDAD4"><div align="center" class="style28">
                    <html:radio property="access_t" value="MF" />
                   </div></td> 
                  <td width="66%" bgcolor="#FFDAD4" class="style33">MF</td>
                </tr>
                <tr>
                  <td bgcolor="#FFDAD4" ><div align="center" class="style28">
                    <html:radio property="access_t" value="TF"/>
                  </div></td>
                  <td bgcolor="#FFDAD4" class="style35">TF</td>
                </tr>
                <tr>
                  <td bgcolor="#FFDAD4"><div align="center" >
                    <html:radio property="access_t" value="Genetica"/>
                  </div></td>
                  <td bgcolor="#FFDAD4" class="style34">Genetica</td>
                </tr>
                <tr>
                  <td height="30" colspan="2" valign="middle" bgcolor="#FFDAD4"><div align="center" >                     
                    <html:submit property="Submit" value="Submit" styleClass="button"/>
                    <div align="center"></div>
                  </div>
                    <div align="center" class="style29"></div></td>
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

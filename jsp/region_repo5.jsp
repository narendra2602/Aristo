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
</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="ListRepo5HQ?actionRequested=ListRepo5HQ">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align="center" cellpadding="0" cellspacing="0" class="usertable" >
  <tr>
    <th>Select Region </th>
  </tr>
  
  <tr>
    <td><html:select property="code" size="7" style="width:150px">
          <html:optionsCollection name="Repo2Form" property="xlist" label="nm2" value="qty2" />
        </html:select>    </td>
  </tr>
  
  <tr>
    <td style="text-align: center;">
      <html:submit  value="Submit" styleClass="button"/> &nbsp;&nbsp;&nbsp;
 
 
      <html:reset value=" Back " onclick="window.location.href='./jsp/Home.jsp'"  styleClass="button"/>    </td>
  </tr>
</table>
 <p>&nbsp;</p>
</html:form>
</body><br/><br/><br/>
 <jsp:include page="<%=foot%>"/> 

</html:html>

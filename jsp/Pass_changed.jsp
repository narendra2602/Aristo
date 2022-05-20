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
<link href="css/OMF1.css" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


  			    <%
					String a= (String) request.getAttribute("data");
			    %>
               <p>&nbsp;</p>
               <p>&nbsp;</p>
               <p>&nbsp;</p>

				<br/>

               <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0" class="usertable">
                  
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;
					  <div align="center"> <%=a%> </div></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
     <td><div align="center"><a href="/Aristo/UserHomeForward.do?actionRequested=UserHomeForward"><strong>Click here to Continue</strong></a><strong> </strong></div></td>
	
					
					
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                
                
               <p>&nbsp;</p>
               <p>&nbsp;</p>
               <p>&nbsp;</p>
               <br/><br/>


  </body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>
 
	<% 
	String toop  = (String) session.getAttribute("top");
	String foot = (String) session.getAttribute("footer");
	String css1 = (String) session.getAttribute("css");
	%>
	
    <jsp:include page="<%=toop%>"/> 



	<head>
	  <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
	</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >

              <html:form action="UpdateBranch.do?actionRequested=UpdateBranch" >
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>				
                <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable1">
                  <th colspan="6">Branch Master </th>
                  <tr>
                    <td width="11%">&nbsp;</td>
                    <td width="35%">&nbsp;</td>
                    <td width="42%">&nbsp;</td>
                    <td width="12%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Branch Code </td>
                    <td><html:text property="depo_code" readonly="true" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Branch Name </td>
                    <td><html:text property="ter_name" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Field Staff</td>
                    <td><html:text property="no_of_rep" /></td>
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
                    </div>					</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
				
          </html:form>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>		 
        <p>&nbsp;</p>		 		
      </body>
   <jsp:include page="<%=foot%>"/> 
</html:html>
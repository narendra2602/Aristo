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
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >


  

              
  <html:form action="OptUserType.do?actionRequested=OptUserType">
   
              
               <br/>
               <br/></br><br>
               <p>&nbsp;</p>
               <p>&nbsp;</p>
<table align="center" width="61%" border="0" cellspacing="0" cellpadding="0" class="usertable">
                  <th colspan="10">Select  User Type</th>
                  
                  <tr>
                    <td width="9%"></td>
                    <td width="31%"><div align="left">
                      <html:radio property="opt" value="1"/>
                    Branch </div></td>
                    <td width="34%"><div align="left">
                      <html:radio property="opt" value="2"/>
                    DGM</div></td>
                    <td width="26%"><div align="left">
                      <html:radio property="opt" value="3"/>
                    HO</div></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td><div align="left">
                      <html:radio property="opt" value="4"/>
                    ZM</div></td>
                    <td><div align="left">
                      <html:radio property="opt" value="5"/>
                    All Branch</div></td>
                    <td><div align="left"></div></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td colspan="4">Access Type 
					<html:select property="access_t">

						  <html:option value="MF">MF</html:option>
						  <html:option value="TF">TF</html:option>
						  <html:option value="Genetica">Genetica</html:option>
						  <html:option value="All">All</html:option>
					</html:select>
					</td>
	              </tr>
<tr>
                    <td colspan="4"><html:submit  value="Submit" styleClass="button"/></td>
                  </tr>
                </table>
                
                
               <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
              </html:form>

        </body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

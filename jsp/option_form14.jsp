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




</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

               <html:form action="ListRepo.do?actionRequested=ListRepo">
                 <p>&nbsp;</p>
				 <p>&nbsp;</p>
				 <p>&nbsp;</p>
				
				 <p><br/>
			       <br/>
				   
			     </p>
				 <table align="center" cellpadding="0" cellspacing="0" class="usertable">
                     <th colspan="5">Branch Wise -Rupees Wise Net Sales Trend</th>

                   
                   <tr>
                  <td colspan="5">
				
					Mkt Year 
                    <html:select property="eyear">
                          <html:optionsCollection name="Repo1Form" property="ylist" label="yname" value="yval" />
                    </html:select>                 </td>
				</tr>
                 
                  <tr>
                    <td colspan="5">
                      <div align="center">
                        <html:submit value="Submit"  styleClass="button"/>
                        &nbsp;&nbsp; 
                        <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/> 
                      </div></td>
                  </tr>
                </table>
          </html:form>  
          
        
         
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
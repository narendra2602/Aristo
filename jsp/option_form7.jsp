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

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

               <html:form action="ListForm7.do?actionRequested=ListForm7">
                 <p>&nbsp;</p>
		
				 <p>&nbsp;</p>
				 <p><br/><br/>
			     </p>
				 <p>&nbsp;</p>
				 <table align="center" cellpadding="0" cellspacing="0" class="usertable">
  			    <tr> 
                       <th colspan="8">Product Wise Contribution % </th>
			   </tr> 
  			    <tr> 
				  <td>&nbsp;</td>
				  <td>&nbsp;</td>								  
				  <td>&nbsp;</td>	
                  <td><html:radio property="rep_type" value="1" />H.Q. &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;</td>
				  <td><html:radio property="rep_type"  value="2" />Region &nbsp; &nbsp;&nbsp;&nbsp;</td>	
				  <td><html:radio property="rep_type"  value="3"/>Area  &nbsp; &nbsp; </td>				
				  <td>&nbsp;</td>	
				  <td>&nbsp;</td>	
                  </tr> 

                <tr>
                  <td colspan="8">
 										From
                  <html:select property="mnth">
                        <html:option value="1">Oct</html:option>
                        <html:option value="2">Nov</html:option>
                        <html:option value="3">Dec</html:option>
                        <html:option value="4">Jan</html:option>
                        <html:option value="5">Feb</html:option>
                        <html:option value="6">Mar</html:option>
                        <html:option value="7">Apr</html:option>
                        <html:option value="8">May</html:option>
                        <html:option value="9">Jun</html:option>
                        <html:option value="10">Jul</html:option>
                        <html:option value="11">Aug</html:option>
                        <html:option value="12">Sep</html:option>
                </html:select>
				
				   To
				
                  <html:select property="mnth1">
                        <html:option value="1">Oct</html:option>
                        <html:option value="2">Nov</html:option>
                        <html:option value="3">Dec</html:option>
                        <html:option value="4">Jan</html:option>
                        <html:option value="5">Feb</html:option>
                        <html:option value="6">Mar</html:option>
                        <html:option value="7">Apr</html:option>
                        <html:option value="8">May</html:option>
                        <html:option value="9">Jun</html:option>
                        <html:option value="10">Jul</html:option>
                        <html:option value="11">Aug</html:option>
                        <html:option value="12">Sep</html:option>
                  </html:select> Mkt Year
				<html:select property="eyear">
		                <html:optionsCollection name="MktForm" property="ylist" label="yname" value="yval" />
        		</html:select>					  
					

								
				  </td>
				</tr>
                 
                  <tr>
                    <td colspan="8">
                      <div align="center">
                        <html:submit value="Submit" styleClass="button"/>
                        &nbsp;&nbsp; 
                        <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/> 
                      </div></td>
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
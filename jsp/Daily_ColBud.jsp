<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

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


<html:form action="AddBudCol.do?actionRequested=AddBudCol" >
   
<p>&nbsp;</p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="434" border="0" align="center" cellpadding="0" cellspacing="0" class="usertable">
                  <th colspan="6">Collection Budget Entry</th>
                <tr>
                  <td width="36">&nbsp;</td>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="left">Select Division </div></td>
                  <td><div align="left">
				  <html:select property="access_t" style="width:120px">
						  <html:option value="A">MF</html:option>
						  <html:option value="T">TF</html:option>
						  <html:option value="G">Genetica</html:option>
                  </html:select>				  
				  </div>
				  
				  </td>
			  </tr>
			  <tr> 
                  <td>&nbsp;</td>			  
                  <td><div align="left">Month</div></td>
                  <td><div align="left">
			  		<html:select property="mnth">
							<html:option value="OCT">Oct</html:option>
							<html:option value="NOV">Nov</html:option>
							<html:option value="DEC">Dec</html:option>
							<html:option value="JAN">Jan</html:option>
							<html:option value="FEB">Feb</html:option>
							<html:option value="MAR">Mar</html:option>
							<html:option value="APR">Apr</html:option>
							<html:option value="MAY">May</html:option>
							<html:option value="JUN">Jun</html:option>
							<html:option value="JUL">Jul</html:option>
							<html:option value="AUG">Aug</html:option>
							<html:option value="SEP">Sep</html:option>
                      </html:select>&nbsp;				
					  
				<html:select property="eyear">
		                <html:optionsCollection name="LoginForm" property="ylist" label="yname" value="yval" />
        		</html:select>	
				
					</div>
			    </td>
                </tr>
				
			
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="left">Branch </div></td>
                  <td><div align="left">
				  
			  <html:select property="code" style="width:120px">
				 <html:optionsCollection name="LoginForm" property="tlist" label="dname" value="dcode" />  
			  </html:select>		
			  
			  	</div>
			  
		  		  </td>
  		    </tr>
			<tr>
			      <td>&nbsp;</td>				  
                  <td><div align="left">Collection Budget </div></td>
                  <td><div align="left"><html:text property="collc" /></div></td>
            </tr>
				

                <tr>
                   <td colspan="5"><div align="center">
				   <html:submit value="Submit"  styleClass="button" />
				   <html:reset value="Reset"  styleClass="button" />				   
                   </div></td>
                </tr>
	</table>


    <p>&nbsp; 	</p>
  </html:form>
<p>&nbsp;</p>
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

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

               <html:form action="HOListInv2.do?actionRequested=HOListInv2">
                 <br/>     <br/><br/>     <br/>
				 <table align="center" cellpadding="0" cellspacing="0" class="usertable" style="position:absolute;">
						<tr>
								<th  colspan="4">Item Wise Analysis </th>
							</tr>
							<tr>
						<td><div align="right">
							<html:radio property="ttype"  value="60"/>
						</div></td>
						<td width="230" ><div align="left">Receipt from Factory</div></td>
				 
						<td><div align="right">
							<html:radio property="ttype"  value="63"/>
						</div></td>
						<td ><div align="left">Stockiest Salable Analysis </div></td>
					</tr>
					<tr>
						<td><div align="right">
							<html:radio property="ttype"  value="64"/>
						</div></td>
						<td ><div align="left">Stockiest Un-Salable Analysis</div></td>
					
						<td><div align="right">
							<html:radio property="ttype"  value="69"/>
						</div></td>
						<td ><div align="left">Stockiest (Expiry) </div></td>
					</tr>
					<tr>
						<td><div align="right">
							<html:radio property="ttype"  value="71"/>
						</div></td>
						<td ><div align="left">Stockiest (Price Diff)</div></td>
			 
						<td><div align="right">
							<html:radio property="ttype"  value="65"/>
						</div></td>
						<td ><div align="left">Sales to Sample </div></td>
					</tr>
					<tr>
						<td><div align="right">
							<html:radio property="ttype"  value="66"/>
						</div></td>
						<td ><div align="left">Written Off </div></td>
				 
						<td><div align="right">
							<html:radio property="ttype"  value="80"/>
						</div></td>
						<td><div align="left">Free Issue </div></td>
					</tr>
					<tr>
						<td><div align="right">
										<html:radio property="ttype"  value="70"/>
								</div></td>
						<td><div align="left">Institutional Supply </div></td>
				 
						 <td><div align="right">
							 <html:radio property="ttype"  value="67"/>
						 </div></td> 
						 <td><div align="left">Transfer from Branches </div></td>
	 </tr> 

					 <tr>
						 <td colspan="4">
						 From
						 <html:select property="smon">
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
	 
						 <html:select property="emon">
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
							 <html:optionsCollection name="Inv2Form" property="ylist" label="yname" value="yval" />
			 </html:select>		
		 
		 
		 
		 
				</td>
					</tr>
						
						 <tr>
							 <td colspan="4">
								 
								 <div align="center">
									 <html:submit value="Submit" styleClass="button"/>
									 &nbsp;&nbsp;
									 <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/> 
								 </div><div align="center"></div></td>
							</tr>
					 </table>
		 </html:form>  
        
         <p>&nbsp;</p>
  </body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");   
%>
 <jsp:include page="<%=toop%>"/> 

<head>
  <link rel="stylesheet" type="text/css" href="css/<%=css1%>"  />
  <script src="./js/search.js"></script> 
  
<script language="javascript"> 
function toggle() 
{
	var ele = document.getElementById("results");
		ele.style.display = "none";
		
} 

function toggle1() 
{
	var ele = document.getElementById("results");
		ele.style.display = "block";
} 

</script>
  
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

<html:form action="ListRepo2.do?actionRequested=ListRepo2">
<p>&nbsp;</p>
<p>&nbsp;</p>
              <table border="0" align="center" cellpadding="0" cellspacing="0" class="usertable">
				  <th colspan="4">Product Wise Gross/Credit/Net Sales</th>
                  <tr>
                    <td width="81">&nbsp;</td>
                    <td width="221">
						<div align="left">
<html:radio property="rep_type"  value="1" onclick="toggle1();showCustomer(this.value,'OptRepo2.do?actionRequested=OptRepo2')"/>H.Q. <br/>
<html:radio property="rep_type"  value="2" onclick="toggle1();showCustomer(this.value,'OptRepo2.do?actionRequested=OptRepo2')"/>Region <br/>
<html:radio property="rep_type"  value="3" onclick="toggle1();showCustomer(this.value,'OptRepo2.do?actionRequested=OptRepo2')" />Area <br/>
<html:radio property="rep_type"  value="4" onclick="toggle()" />Branch	
						</div>				    
					</td>
                    
					<td colspan="2">
					<div id="results">
						<html:select property="code" styleId="abc" size="5" style="width:180px">
						  <html:optionsCollection name="Repo2Form" property="xlist" label="nm2" value="qty2" />
						</html:select>					
					</div>
						
						</td>
                  </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><div align="left">
                      <html:radio property="uv"  value="1"/>
                    Unit &nbsp;</div></td>
                    <td width="296"><div align="left">
                      <html:radio property="uv"  value="2"/>
                    Value &nbsp;&nbsp;</div></td>
                    <td width="184"><div align="left">
                      <html:radio property="uv"  value="3"/>
                    Both</div></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td><div align="left">
                      <html:radio property="sale_type"  value="1"/>
                    Gross</div></td>
                    <td><div align="left">
                      <html:radio property="sale_type"  value="2"/>
                    Credit &nbsp; </div></td>
                    <td><div align="left">
                      <html:radio property="sale_type"  value="3"/>
                    Net&nbsp;&nbsp;</div></td>
                  </tr>
                  <tr>
                    <td colspan="4">
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
		                <html:optionsCollection name="Repo2Form" property="ylist" label="yname" value="yval" />
        		</html:select>					</td>
                  </tr>
                  <tr>
                    <td colspan="4" align="center" valign="bottom"></td>
                  </tr>
                  <tr>
                    <td colspan="4" align="center">
                        <html:submit value="Submit" styleClass="button"/>
                      &nbsp;
                      <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/>
                    </div></td>
                  </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
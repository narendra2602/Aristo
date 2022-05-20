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
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

<html:form action="HQListRepo2.do?actionRequested=HQListRepo2">
<p>&nbsp;</p>
<p>&nbsp;</p>
			<center>
              <table border="0" align="center" cellpadding="0" cellspacing="0" class="usertable">
				  <th colspan="3">Product Wise Gross/Credit/Net Sales</th>

				   


				    <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">
					<div align="left">
                      <html:radio property="opt"  value="1"/> Selective  &nbsp;  &nbsp;  &nbsp;  &nbsp; 
                      <html:radio property="opt"  value="2"/> All  &nbsp;  &nbsp;  &nbsp;  &nbsp; 
                    </div>
					</td>
					
                  </tr>

                  <tr>
                    <td width="164">&nbsp;</td>
                    <td width="618">
					<div align="left">					
						<html:select property="code" styleId="abc" size="5" style="width:180px">
							  <html:optionsCollection name="Repo2Form" property="xlist" label="nm2" value="qty2" />
						</html:select>					
					</div>
					</td>
                  </tr>

					
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">
					<div align="left">
                      <html:radio property="sale_type"  value="1"/> Gross  &nbsp;  &nbsp;  &nbsp;  &nbsp; 
                      <html:radio property="sale_type"  value="2"/> Credit  &nbsp;  &nbsp;  &nbsp;  &nbsp; 
                      <html:radio property="sale_type"  value="3"/> Net
                    </div
					
					
					><br/>
					<div align="left">
						<html:radio property="uv"  value="1"/> Unit  &nbsp;  &nbsp;  &nbsp; &nbsp; &nbsp;
						<html:radio property="uv"  value="2"/> Value &nbsp; &nbsp;  &nbsp;  &nbsp;&nbsp;
						<html:radio property="uv"  value="3"/> Both	
					</div>
					</td>
					
					
                  </tr>
                  <tr>
                    <td colspan="3">
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
                    <td colspan="3" align="center" valign="bottom"></td>
                  </tr>
                  <tr>
                    <td colspan="3" align="center">
                        <html:submit value="Submit" styleClass="button"/>
                      &nbsp;
                      <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/>
                    </div></td>
                  </tr>
	</table>
</center>

    <p>&nbsp;</p>
    <p>&nbsp;</p>

  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
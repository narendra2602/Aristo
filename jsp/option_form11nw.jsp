<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");  
   String css2 = (String) session.getAttribute("menucss");
%>


<jsp:include page="<%=toop%>"/>

<head>

<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link rel="stylesheet" type="text/css" href="css/<%=css1%>"  />
<link href="./css/new/<%=css2%>" rel="stylesheet" type="text/css" />

<link href="./css/new/styles.css" rel="stylesheet">





</script>

<jsp:include page="head-scripts.jsp" />


</head> 
 
<body id="target">

  <html:form action="NEWListForm11.do?actionRequested=NEWListForm11">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="5">H.Q. WISE RUPEES WISE SALES ANALYSIS</th>
                  

 	             <tr>
					<td colspan="4" style="padding-left: 90px;">Branch &nbsp;&nbsp;
						<html:select property="code" styleId="mySelect" >
						  <html:optionsCollection name="MktForm" property="blist" label="dname" value="dcode" />
						</html:select>
                    </td>

				  </tr>


                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

				  
				  <tr>
                    <td colspan="5" style="padding-left: 85px;text-align: left; width: 60%;">
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
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

                    <td colspan="5" style="padding-left: 205px;" align="center">
                        <html:submit value="Submit" styleClass="button" styleId="loader4"/>
                      &nbsp;
                      <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/>
                    </div></td>
                  </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
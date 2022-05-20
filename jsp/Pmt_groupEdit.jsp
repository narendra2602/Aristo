<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="java.util.*" %>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  

<html:html>
<% 
String toop = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>"/> 

<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

<SCRIPT LANGUAGE="JavaScript">
function checkAll(checkbox2)
{
for (i = 0; i < checkbox2.length; i++)
	checkbox2[i].checked = true ;
}

function uncheckAll(checkbox2)
{
for (i = 0; i < checkbox2.length; i++)
	checkbox2[i].checked = false ;
}
</script>

</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
    <html:form action="UpdatePmtRights.do?actionRequested=UpdatePmtRights">
        <p>&nbsp;</p>
		

		<%
        List pmt = (List) request.getAttribute("pmt");
        LoginFormBean lf = new LoginFormBean();  
		String tp = (String) request.getAttribute("tp");
		int uid= (Integer) request.getAttribute("uid");
		%>
		<input type="hidden" name="tp" value="<%=tp%>"/>
		<input type="hidden" name="uid" value="<%=uid%>"/>		
		
        <table border="0" align="center" cellpadding="0" cellspacing="0" class="tablereg">
            <th colspan="4"> Group Selection   </th>
		      <tr>
                <td colspan="4">
				<br/>
				<input type="button" value="Select All" onClick="checkAll(checkbox2)">
				<input type="button" value="Clear All" onClick="uncheckAll(checkbox2)">
				<br/>
                <br/> 
                GROUP 
				</td>
              </tr>



              <tr>
                <td colspan="4"><hr></td>
              </tr>
			  <%
					Iterator it =  pmt.iterator();
					String gcode="";
					while(it.hasNext())
					{
					 lf=(LoginFormBean)it.next();    
					 gcode="";
					 gcode=gcode+lf.getDcode();
			  %>	 
  
              <tr>
                <td width="33">&nbsp;</td>
                <td width="14"><html:multibox property="checkbox2" value="<%=gcode%>"/> </td>
                <td width="434"><%=lf.getDname()%></td>
                <td width="4">&nbsp;</td>
              </tr>
			  <%
			         }
			  %>
			  
			  
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr>
                <td colspan="4"><div align="center">
				<html:submit value="Submit" styleClass="button"/>
                </div> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
			
	<p>&nbsp;</p>
   </html:form>
  </body>
 <jsp:include page="<%=foot%>"/> 
</html:html>
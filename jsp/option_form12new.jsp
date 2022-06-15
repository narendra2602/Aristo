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



<script src="./js/search.js"></script> 
<script language="javascript"> 

document.getElementById("hqSelect").disabled=false;
document.getElementById("mySelect").value="0"
function toggle1() 
{
    if(document.getElementById("mySelect").value=="0")
	{
		document.getElementById('brCheck').checked=true;
	}


	var ele = document.getElementById("results");
		ele.style.display = "block";

	if(document.getElementById('brCheck').checked) 
		setTimeout(enableCombo, 850);    
} 

function disableCombo() 
{
	document.getElementById("hqSelect").disabled=false;
} 

function enableCombo() 
{
	document.getElementById("hqSelect").disabled=true;
} 


</script>

<jsp:include page="head-scripts.jsp" />


</head> 
 
<body id="target">

  <html:form action="NWListForm12.do?actionRequested=NWListForm12">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="5">Sales Review SP-1</th>
                  
                  <tr>
                    <td style="padding-left: 85px;"><html:radio property="sale_type"  value="1" styleId="brCheck" onclick="enableCombo();"/>Branch</td>
                    <td><html:radio property="sale_type"  value="2" onclick="disableCombo();"/>HQ &nbsp; </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>


                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>
  
 	             <tr>  
					<td colspan="4" style="padding-left: 90px;">Branch &nbsp;&nbsp;
						<html:select property="code" value="0" styleId="mySelect" onchange="toggle1();showCustomer(this.value,'NWOptForm12.do?actionRequested=NWOptForm12')">
						  <html:optionsCollection name="MktForm" property="blist" label="dname" value="dcode" />
						</html:select>
                    </td>
					<td>
						<div id="results">
					    HQ 
							<html:select property="no_of_mr" disabled="true" styleId="hqSelect" style="width:180px">
							  <html:optionsCollection name="MktForm" property="alist" label="nm2" value="qty2" />
							</html:select>
						</div>
					</td>

				  </tr>


                  <tr>
                    <td colspan="5" align="center" valign="bottom"></td>
                  </tr>

				  
				  <tr>
                    <td colspan="5" style="padding-left: 85px;text-align: left; width: 60%;">
					 Mkt Year
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
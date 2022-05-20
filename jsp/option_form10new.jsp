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
document.getElementById("mySelect").selectedIndex = "0";
document.getElementById("grpSelect").selectedIndex = "1";
function toggle1() 
{

    if(document.getElementById("mySelect").value=="0")
	{
//		alert("Hello! I am an alert box!!");
		document.getElementById('brCheck').checked=true;
	//	document.getElementById("hqSelect").disabled=true;
//		enableCombo();
	}


	var ele = document.getElementById("results");
		ele.style.display = "block";

	if(document.getElementById('brCheck').checked) 
		setTimeout(enableCombo, 750);    

      //alert(document.getElementById("mySelect").value);

} 


function disableCombo() 
{
	document.getElementById("hqSelect").disabled=false;
	document.getElementById("mySelect").selectedIndex = "1";

} 

function enableCombo() 
{
	document.getElementById("hqSelect").disabled=true;
} 





</script>

<jsp:include page="head-scripts.jsp" />


</head> 
 
<body id="target">

  <html:form action="NWListForm10.do?actionRequested=NWListForm10">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="5">Group Wise-Product Wise Trend</th>
              
				 <tr>
					<td style="padding-left: 85px;"><html:radio property="rep_type"  value="1" styleId="brCheck" onclick="enableCombo();"/>Branch </td>
                    <td><html:radio property="rep_type"  value="2" onclick="disableCombo();"/>HQ &nbsp;&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>

			      </tr>


 	              <tr>
					<td colspan="4" style="padding-left: 90px;">Branch &nbsp;&nbsp;
						<html:select property="code" styleId="mySelect" onchange="toggle1();showCustomer(this.value,'NWOptForm10.do?actionRequested=NWOptForm10')">
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
					<td colspan="4" style="padding-left: 90px;">Group  &nbsp;&nbsp;
						<html:select property="gp_code"  styleId="grpSelect" >
						  <html:optionsCollection name="MktForm" property="glist" label="nm2" value="qty2"  />
						</html:select>
                    </td>

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
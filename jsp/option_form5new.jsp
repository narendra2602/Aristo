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
//	showCustomer(1,'NWOptForm4.do?actionRequested=NWOptForm4');
//	toggle1();

} 

function enableCombo() 
{
	document.getElementById("hqSelect").disabled=true;
//	document.getElementById("mySelect").value="10"
	// document.getElementById("mySelect").selectedIndex = "0";
} 


document.getElementById("unitSelect").disabled=true;

function disableRadio() 
{
	document.getElementById("unitSelect").disabled=true;
	document.getElementById("valueSelect").checked=true;
} 

function enableRadio() 
{
	document.getElementById("unitSelect").disabled=false;
	document.getElementById("unitSelect").checked=true;
} 



</script>

<jsp:include page="head-scripts.jsp" />





</head> 
 
<body id="target">

  <html:form action="NWListForm5.do?actionRequested=NWListForm5">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="5">Product Wise Sales/Target Trend</th>
                  
                  <tr>
                    <td style="padding-left: 85px;"><html:radio property="pg"  value="1" onclick="enableRadio();"/>Productwise </td>
                    <td><html:radio property="pg"  value="2" onclick="disableRadio();"/>Groupwise </td>
                    <td>&nbsp;</td>
			      </tr>



                 <tr>
					<td style="padding-left: 85px;"><html:radio property="uv" styleId="unitSelect"  value="1"/>Unit </td>
                    <td><html:radio property="uv"  styleId="valueSelect" value="2"/>Value &nbsp;&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>

			      </tr>


				 <tr>
					<td style="padding-left: 85px;"><html:radio property="rep_type"  value="1"/>Sales </td>
                    <td><html:radio property="rep_type"  value="2"/>Target &nbsp;&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>

			      </tr>

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
						<html:select property="code" styleId="mySelect" onchange="toggle1();showCustomer(this.value,'NWOptForm5.do?actionRequested=NWOptForm5')">
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
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



<script language="javascript"> 

document.getElementById("grpSelect").disabled=false;

function disableCombo() 
{
	document.getElementById("grpSelect").disabled=true;
} 

function enableCombo() 
{
	document.getElementById("grpSelect").disabled=false;
} 

function disableBranchCombo() 
{
	document.getElementById("mySelect").disabled=true;
} 

function enableBranchCombo() 
{
	document.getElementById("mySelect").disabled=false;
} 



</script>

<jsp:include page="head-scripts.jsp" />


</head> 
 
<body id="target">

  <html:form action="NWListForm3.do?actionRequested=NWListForm3">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
				  <th colspan="5">Group Wise Top Branch/HQ On PMR/%ACH</th>
                  
                  <tr>
                    <td style="padding-left: 85px; "><html:radio property="pg"  value="1" styleId="brCheck" onclick="enableCombo();"/>Groupwise</td>
                    <td><html:radio property="pg"  value="2" onclick="disableCombo();"/>All &nbsp; </td>
                  </tr>


                  <tr>
                    <td style="padding-left: 85px;"><html:radio property="sale_type"  value="1" />PMR Rank</td>
                    <td><html:radio property="sale_type"  value="2" />%ACH Rank &nbsp; </td>
                  </tr>


                  <tr>
                    <td style="padding-left: 85px;"><html:radio property="r_type"  value="1" onclick="enableBranchCombo();" />Branch</td>
                    <td> <html:radio property="r_type"  value="2" onclick="disableBranchCombo();" />Top 25 HQ </td>
                  </tr>



 	             <tr>
					<td colspan="4" style="padding-left: 90px;">Branch &nbsp;&nbsp;
						<html:select property="depo_code" value="0" styleId="mySelect" >
						  <html:optionsCollection name="HOForm3Form" property="blist" label="dname" value="dcode" />
						</html:select>
                    </td>
					<td>
						<div id="results">
					    Group 
							<html:select property="grp_code"  styleId="grpSelect" style="width:180px">
							  <html:optionsCollection name="HOForm3Form" property="alist" label="nm2" value="qty2" />
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
		                <html:optionsCollection name="HOForm3Form" property="ylist" label="yname" value="yval" />
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
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
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

<SCRIPT LANGUAGE="JavaScript">

function checkAll(checkbox1)
{
for (i = 0; i < checkbox1.length; i++)
	checkbox1[i].checked = true ;
}

function uncheckAll(checkbox1)
{
for (i = 0; i < checkbox1.length; i++)
	checkbox1[i].checked = false ;
}

</script>

<script language="javascript">
function check()
{

var v1=document.forms[0].f_name.value;
var v2=document.forms[0].l_name.value;
var v3=document.forms[0].login_name.value;
var v4=document.forms[0].password.value;
var v5=document.forms[0].c_password.value;
var v6=document.forms[0].desig.value;

if (v1=="")
{
alert ("First Name Cannot be Blank");
document.forms[0].f_name.focus();
return false;
}

if (v2=="")
{
alert ("Last Name Cannot be Blank");
document.forms[0].l_name.focus();
return false;
}

if (v3=="")
{
alert ("Login Id Cannot be Blank");
document.forms[0].login_name.focus();
return false;
}

if (v4=="")
{
alert ("Password Cannot be Blank");
document.forms[0].password.focus();
return false;
}

if (v5=="")
{
alert ("Confirm Password Cannot be Blank");
document.forms[0].c_password.focus();
return false;
}

if (v6=="")
{
alert ("Please Select Designation");
document.forms[0].desig.focus();
return false;
}


if (v4 != v5 )
{
alert ("Password & Confirm Password Does Not Match");
document.forms[0].password.focus();
return false;
}

return true;
}


</script>

<script language="javascript">
function Clickheretoprint()
{ 
  var disp_setting="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
      disp_setting+="scrollbars=yes,width=800, height=600, left=0, top=50"; 
  var content_vlue = document.getElementById("print_content").innerHTML; 
  
  var docprint=window.open("","",disp_setting); 
   docprint.document.open(); 
   docprint.document.write('<html><head><title>OPAL Technology Indore</title>'); 
   docprint.document.write('<link href="css/MF.css" rel="stylesheet" type="text/css" />');
   docprint.document.write('</head><body onLoad="self.print()"><center>');          
   docprint.document.write(content_vlue);          
   docprint.document.write('</center></body></html>'); 
   docprint.document.close(); 
   docprint.focus(); 
}
</script>






</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >

<html:form action="UpdateUser.do?actionRequested=UpdateUser" >
   
                <p>&nbsp;</p>
		<%
		  String tp= (String) request.getAttribute("typ");
		  boolean tag=true;
		%>

    <div align="right"><a href="javascript:Clickheretoprint()"> Print</a>    </div>
    <div class="style3" id="print_content">

		
<table width="434" border="0" cellpadding="0" cellspacing="0" class="tablereg">
                    <th colspan="6">Edit User </th>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td width="36">&nbsp;</td>
                    <td colspan="4"><strong><u>Personal Information </u></strong> </td>
      </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>Title:</td>
                  <td><html:select property="title">
						  <html:option value="MR.">Mr.</html:option>
						  <html:option value="MISS.">Miss.</html:option>
						  <html:option value="MRS.">Mrs.</html:option>
					 </html:select>				  </td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                    <td width="97"><div align="left">First Name:</div></td>
                    <td width="39">                      
                      <div align="left" > 
                        <html:text property="f_name" />                    
                      </div></td>
                    <td width="115">Last Name:</td>
                    <td width="108"><div align="left">
                      <html:text property="l_name" />
                  </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="left">Designation:</div></td>
                  <td><div align="left">
                      <html:text property="desig" />
                  </div></td>
                  <td>Location:</td>
                  <td><html:text property="location" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>Department: </td>
                  <td><html:text property="department" /></td>
                  <td>Company Code:</td>
                  <td><html:text value="AP" readonly="true" property="comp_code" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>Email Id: </td>
                  <td><html:text property="email" /></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="5"><div align="left"><hr></div></td>
      			</tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="4"><strong><u>Login Information </u></strong></td>
     			</tr>
                <tr>
                  <td>&nbsp;</td>
                    <td><div align="left">Login Id: </div></td>
                    <td>                      
                      <div align="left">
                        <html:text property="login_name" readonly="true" />                    
                      </div></td>
                    <td>&nbsp;</td>
                    <td><div align="left"></div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                    <td>Division: </td>
                    <td><html:select property="access_t">
						  <html:option value="MF">MF</html:option>
						  <html:option value="TF">TF</html:option>
						  <html:option value="Genetica">Genetica</html:option>
  						  <html:option value="All">All</html:option>
					 </html:select>					</td>
                    <td>Status Active: </td>
                    <td><html:select property="status">
                        <html:option value="Yes">Yes</html:option>
                        <html:option value="No">No</html:option>
                      </html:select>                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>User Type:</td>
                    <td colspan="3"> <html:text property="type" readonly="true" /></td>
                </tr>

		<% 
		if ((tp.equals("Central")) || (tp.equals("Factory")))
		 {
		        tag=false;    
	  	 }
		
		 if (tag)
		 {
		%>

                <tr>
                    <td colspan="5"><hr></td>
      			</tr>
                
				
				<tr>
                  <td>&nbsp;</td>
                  <td colspan="4"><strong><u>Branches </u> </strong><br/><br/>
					<input type="button" value="Select All" onClick="checkAll(checkbox1)">
					<input type="button" onClick="uncheckAll(checkbox1)" value="Clear All">				  
				  </td>
                </tr>



                <tr>
                 <td colspan="5">
				   <table width="414" border="0" align="left" cellpadding="0" cellspacing="0" class="tablebranch"> 
                  <tr>
                    <td width="10">&nbsp;</td>
                    <td width="11">&nbsp;</td>
                     <td width="109"><html:multibox property="checkbox1" value="10"/>
                     Ahmedabad</td>
                     <td width="97"><html:multibox property="checkbox1" value="55"/>
                       Ambala</td>
                     <td width="95"><html:multibox property="checkbox1" value="71"/>
                     Banglore</td>
                     <td width="92"><html:multibox property="checkbox1" value="40"/>
                       Calcutta</td> 
                  </tr>   
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                     <td><html:multibox property="checkbox1" value="58"/>
                       Chandigarh</td>
                     <td><html:multibox property="checkbox1" value="60"/>
                       Chennai</td>
                     <td><html:multibox property="checkbox1" value="61"/>
                       Cochin</td>
                     <td><html:multibox property="checkbox1" value="45"/>
                     Cuttack</td> 
                  </tr>   
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                     <td><html:multibox property="checkbox1" value="82"/>
                       Dehradun</td>
                     <td><html:multibox property="checkbox1" value="50"/>
                       Delhi</td>
                     <td><html:multibox property="checkbox1" value="81"/>
                       Ghaziabad</td>
                     <td><html:multibox property="checkbox1" value="42"/>
                       Guwahati</td> 
                  </tr>   
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                     <td><html:multibox property="checkbox1" value="70"/>
                       Hyderabad</td>
                     <td><html:multibox property="checkbox1" value="21"/>
                       Indore</td>
                     <td><html:multibox property="checkbox1" value="51"/>
                       Jaipur</td>
                     <td><html:multibox property="checkbox1" value="80"/>
                       Lucknow</td> 
                  </tr>   
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                     <td><html:multibox property="checkbox1" value="20"/>
                       Mumbai</td>
                     <td><html:multibox property="checkbox1" value="30"/>
                       Patna</td>
                     <td><html:multibox property="checkbox1" value="25"/>
                       Pune</td>
                     <td><html:multibox property="checkbox1" value="15"/>
                       Raipur</td> 
                  </tr>   
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                     <td><html:multibox property="checkbox1" value="31"/>
                       Ranchi</td>
                     <td><html:multibox property="checkbox1" value="72"/>
                       Vijaywada</td>
                     <td><html:multibox property="checkbox1" value="59"/>
                       Zirakpur</td>
                     <td><html:multibox property="checkbox1" value="32"/>
					   Nepal</td>
                  </tr> 
				 </table>				</td>
                </tr>
				
	         <% } %>				
				
                <tr>
                  <td colspan="5"><hr></td>
                </tr>
                <tr>
                   <td colspan="5"><div align="center">
				   <html:submit value="Update" styleClass="button"/>
                   </div></td>
      </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
	</table>
</div>

    <p>&nbsp; 	</p>
  </html:form>
</body>
 <jsp:include page="<%=foot%>"/> 
</html:html>

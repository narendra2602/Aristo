<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<head>

<jsp:include page="F_top.jsp"/> 

<script src="js/calendar.js" type="text/javascript" language="javascript"></script>
<script src="js/DateValid.js" type="text/javascript" language="javascript"></script>

<script type="text/javascript">
function makeSelect(){
document.getElementById('selectNo').checked = false;
document.getElementById('selectYes').checked = true;
}


function Select()
{
document.getElementById('select').checked = true;
}




function makeDisable(){
    var x=document.getElementById("mySelect")
    x.disabled=true
}
function makeEnable(){
    var x=document.getElementById("mySelect")
    x.disabled=false
}
</script>

<script src="./js/selectgroup.js"></script> 

</head>
	
<body >

	<div id="calendarDiv"> </div>

  <p>&nbsp;</p>
  <p>&nbsp;</p>
<html:form action="FactoryListRepo5.do?actionRequested=FactoryListRepo5" onsubmit="return validateDate()">
  <center>
  <table cellpadding="0" cellspacing="0" class="table_opt">
    <tr>
      <th colspan="4">Dispatch Summery</th>
    </tr>
    

    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Select Factory:</td>
      <td>
   <div id="abc">	  
     <html:select styleId="location" property="loc" style="width:190px"  onchange="showCustomer1(this.value,'FactoryAjxRepo5b.do?actionRequested=FactoryAjxRepo5b'); Select() " >
	        <html:optionsCollection name="FacRepo5Form" property="loclist" label="lcname" value="lcval" />   
	  </html:select>
   </div>	  
	  </td>
    </tr>	
	
	<tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Sale/Samples:</td>
      <td>
      <html:radio property="schoice" styleId="select" value="P"  onclick="showCustomer2(this.value,'FactoryAjxRepo5a.do?actionRequested=FactoryAjxRepo5a'); makeSelect()" /> Sales  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
	  <html:radio property="schoice" value="S"  onclick="showCustomer2(this.value,'FactoryAjxRepo5a.do?actionRequested=FactoryAjxRepo5a'); makeSelect()"  /> Sample
	  </td>	  
    </tr>

	<tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Selection:</td>
      <td>
      <html:radio property="choice" value="1" styleId="selectYes" onclick="makeEnable()" /> Selective  &nbsp;  &nbsp; &nbsp; 
	  <html:radio property="choice" value="2" styleId="selectNo" onclick="makeDisable()"  /> All
	  </td>	  
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Select Product:</td>
      <td>
   <div id="abcd">	  
      <html:select property="sbranch" style="width:300px" styleId="mySelect" >
	        <html:optionsCollection name="FacRepo5Form" property="brlist" label="brname" value="brval" />   
	  </html:select>	
   </div>	  
	  </td>
    </tr>



    <tr>
      <td width="4">&nbsp;</td>
      <td width="6">&nbsp;</td>
      <td width="110">Start Date:</td>
      <td width="110"><html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>End Date:</td>
	  <td><html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" /> </td>
    </tr>	

    <tr>
      <td colspan="4">
	  <div align="center"> 
	  <html:submit styleClass="button" value="Submit" /> 
	  &nbsp;
	  <html:reset styleClass="button" value="Cancel" />
	  </div>	  </td>
    </tr>
  </table>
</center>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>&nbsp;</p><br/>
  </html:form>
</body>
<jsp:include page="F_footer.jsp"/> 	
</html:html>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

<head>

<jsp:include page="F_top.jsp"/> 

<script src="js/calendar.js" type="text/javascript" language="javascript"></script>
<script src="js/DateValid.js" type="text/javascript" language="javascript"></script>

<script type="text/javascript">
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
<html:form action="FactoryListRepo2.do?actionRequested=FactoryListRepo2" onsubmit="return validateDate()">
  <center>
  <table cellpadding="0" cellspacing="0" class="table_opt">
    <tr>
      <th colspan="4">Branch Wise Dispatch Detail</th>
    </tr>
    
	<tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Pharma/Lab</td>
      <td>
      <html:radio property="pchoice" value="P" onclick="showCustomer(this.value,'FactoryAjxRepo2.do?actionRequested=FactoryAjxRepo2')" /> Pharma &nbsp; &nbsp; &nbsp; &nbsp; 
	  <html:radio property="pchoice" value="L" onclick="showCustomer(this.value,'FactoryAjxRepo2.do?actionRequested=FactoryAjxRepo2')" /> Lab
	  </td>	  
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Select Factory:</td>
      <td>
   <div id="abc">	  
     <html:select property="loc" style="width:190px" >
	        <html:optionsCollection name="FacRepo2Form" property="loclist" label="lcname" value="lcval" />   
	  </html:select>
   </div>	  
	  </td>
    </tr>	
	
	<tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Selection:</td>
      <td>
      <html:radio property="choice" value="1" onclick="makeEnable()" /> Selective  &nbsp;  &nbsp; &nbsp; 
	  <html:radio property="choice" value="2" onclick="makeDisable()"  /> All
	  </td>	  
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Select Branch:</td>
      <td>
      <html:select property="sbranch" style="width:190px" styleId="mySelect" >
	        <html:optionsCollection name="FacRepo2Form" property="brlist" label="brname" value="brval" />   
	  </html:select>	
	  
	  </td>
    </tr>



    <tr>
      <td width="10">&nbsp;</td>
      <td width="18">&nbsp;</td>
      <td width="73">Start Date:</td>
      <td width="96"><html:text property="sdate" styleClass="calendarSelectDate" style="text-align:center" />  </td>
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
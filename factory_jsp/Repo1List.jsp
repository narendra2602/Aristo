<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
 
<html>

<head>

<jsp:include page="F_top.jsp"/> 

</head>
	
<body>
	<% 
	  response.setHeader("Cache-Control","no-cache");  
	  response.setHeader("Cache-Control","no-store");  

	  String whead = (String) request.getAttribute("whead");
	%> 
 
 
    
	<br/>
<div class="div1">	 

 <display:table id="data" name="requestScope.FacRepo1Form.rlist" requestURI="./FactoryListRepo1.do?actionRequested=FactoryListRepo1" pagesize="20" export="true" decorator="org.displaytag.decorator.TotalTableDecorator">
 
            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
		    
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="sno" title="S NO." />
            <display:column property="invoiceno" title="INV NO."/>
            <display:column property="despatchdate" title="INV DATE" format="{0,date,dd/MM/yyyy}" /> 
            <display:column property="depo_name" title="BRANCH" /> 
			<display:column property="value" title="AMOUNT" format="{0,number,0.00}" headerClass="r" class="r" total="true"/> 
			<display:column property="boxes" title="BOXES" headerClass="r" class="r" /> 			
			<display:column property="transporteid" title="TRANSPORTER" /> 			
            <display:column property="lrno" title="LR NO." /> 
            <display:column property="lrdate" title="LR DATE" format="{0,date,dd-MM-yyyy}" /> 
            <display:column property="vehicleno" title="TRUCK NO." /> 									
			
        </display:table>
</div>		
</body>
<br/>
<jsp:include page="F_footer.jsp"/> 	
</html>
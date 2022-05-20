<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="fac.aristo.valueobject.FacRepo3FormBean" %>  

<html>

<head>
<% 
String top = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>
<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 


</head>
	
<body> <br/>
	<% 
	  response.setHeader("Cache-Control","no-cache");  
	  response.setHeader("Cache-Control","no-store");  

	  String whead = (String) request.getAttribute("whead");
	%> 
 
 <br/>
  	<jsp:scriptlet>
	request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
	{
		public String addRowClass()
		{
		  int data = ((FacRepo3FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "vbad";
		  else
			if (data==3)
				 return "vbad";
			else
				if (data==4)
				   return "good";
				else 
					   return "abc";
				 
		}
	   
	});
 </jsp:scriptlet>

 
 
 
 
 
    
<div class="div1">	 

 <display:table id="data" name="requestScope.FacRepo3Form.llist" requestURI="./BranchFactoryListRepo3.do?actionRequested=BranchFactoryListRepo3" pagesize="20" export="true" decorator="dyndecorator">
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pcode" title="CODE"/>
            <display:column property="location" title="DESCRIPTION"/>
			<display:column property="batchno" title="BATCH NO"/>			
			<display:column property="invoiceno" title="INVOICE NO"/>			
			<display:column property="despatchdate" title="DISPATCH DATE" format="{0,date,dd-MM-yyyy}" /> 
			<display:column property="quantity" title="QUANTITY" headerClass="r" class="r" /> 
            <display:column property="mrp" title="MRP" format="{0,number,0.00}" headerClass="r" class="r" /> 
			<display:column property="expdate" title="EXP DATE" format="{0,date,MM-yyyy}" /> 			
            <display:column property="lrno" title="LR NO." /> 
            <display:column property="lrdate" title="LR DATE" format="{0,date,dd-MM-yyyy}" /> 
			<display:column property="transportid" title="TRANS PORTER" style="width: 50px;"/> 			

        </display:table>
</div>		
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="cen.aristo.valueobject.InventoryRepo5FormBean" %>  
<%@ page import="org.displaytag.sample.*" %> 

<html>
<head>
<% 
	String top  = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>

<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 
</head>
	
<body>
<% 
response.setHeader("Cache-Control","no-cache");  
response.setHeader("Cache-Control","no-store");  
String whead = (String) request.getAttribute("whead");
%> 


    <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((InventoryRepo5FormBean)getCurrentRowObject()).getColour();
			  
			  if (data==1)
			    return ((InventoryRepo5FormBean)getCurrentRowObject()).getRqty() > 0 ? "money" : "mon";

			  if (data==2)
			     return "bad";
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


    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.InventoryRepo5Form.rlist" requestURI="./CentralInventoryRepo5.do?actionRequested=CentralInventoryRepo5" pagesize="20" export="true" decorator="dyndecorator" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>
            <display:column property="pcode" title="CODE"   />
            <display:column property="name" title="DESCRIPTION" />
            <display:column property="city" title="CITY"/>
            <display:column property="pack" title="PACKING"  /> 
            <display:column property="bat_no" title="BATCH NO."  /> 
            <display:column property="exp_dt" title="EXPIRY DT"  /> 
            <display:column property="inv_no" title="DOC NO."  /> 
            <display:column property="inv_dt" title="DATE" format="{0,date,dd/MM/yyyy}" /> 			
            <display:column property="rqty" title="RECEIPTS QTY" class="r"/> 
            <display:column property="sqty" title="ISSUE QTY"  headerClass="r" class="r" />
			<display:column property="fqty" title="FREE QTY"  headerClass="r" class="r" />			 
            <display:column property="rate" title="RATE" format="{0,number,0.00}" headerClass="r" class="r"/> 			
            <display:column property="tvalue" title="TOTAL VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 						
        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="fac.aristo.valueobject.FacRepo5FormBean" %>  

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
  	<jsp:scriptlet>
	request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
	{
		public String addRowClass()
		{
		  int data = ((FacRepo5FormBean)getCurrentRowObject()).getColour();
		  if (data==2)
			 return "money";
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

 <display:table id="data" name="requestScope.FacRepo5Form.llist" requestURI="./FactoryListRepo5.do?actionRequested=FactoryListRepo5" pagesize="20" export="true" decorator="dyndecorator">
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
            <display:caption media="excel rtf pdf"><%=whead%></display:caption>

            <display:column property="pcode" title="CODE"/>
            <display:column property="pname" title="DESCRIPTION"/>
            <display:column property="depo_name" title="BRANCH"/>
			<display:column property="pack" title="PACK"/>		
            <display:column property="lrdate" title="LR DATE" format="{0,date,dd-MM-yyyy}"/>
            <display:column property="batchno" title="BATCH NO"/>
            <display:column property="expdate" title="EXP. DATE" format="{0,date,MM-yyyy}"/>
			<display:column property="quantity" title="QUANTITY" headerClass="r" class="r" /> 
			
        </display:table>
</div>		
</body>
<br/>
<jsp:include page="F_footer.jsp"/> 	
</html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="cen.aristo.valueobject.CentralRepo6FormBean" %>  
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
              int data = ((CentralRepo6FormBean)getCurrentRowObject()).getColour();
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


    
   <p>&nbsp;  </p>
	 <div class="div1">	 
 <display:table id="data" name="requestScope.CentralRepo6Form.rlist" requestURI="./CentralListRepo6.do?actionRequested=CentralListRepo6" pagesize="20" export="true" decorator="dyndecorator" >

            <display:caption media="html">   <strong><br/><%=whead%></strong>    </display:caption>
	        <display:caption media="excel rtf pdf"><%=whead%></display:caption>
            <display:column property="pcode" title="CODE"  style="width:60px" />
            <display:column property="name" title="DESCRIPTION" />
            <display:column property="city" title="BRANCH"/>
            <display:column property="pack" title="PACKING" style="width:70px" class="c" /> 
            <display:column property="bat_no" title="BATCH NO." style="width:80px" class="c" /> 
            <display:column property="exp_dt" title="EXPIRY DT" style="width:80px" class="c" /> 
            <display:column property="sqty" title="SALE QTY"  style="width:60px" class="r" /> 
            <display:column property="sfree" title="FREE QTY" style="width:60px" class="r" /> 
        </display:table>
	</div>	
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	 
</html>
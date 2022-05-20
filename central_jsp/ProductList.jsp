<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>
 <jsp:include page="<%=toop%>"/> 

   
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>

             <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

  </head>
 
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
    
    
    
    <br/>  <br/>
        <display:table id="data" name="requestScope.ProductForm.plist" requestURI="./ListProduct1.do?actionRequested=ListProduct1"         pagesize="20" export="true" >

            <display:caption media="html">     <strong><br/>Product Master<br/> </strong>    </display:caption>
            <display:caption media="excel rtf pdf">Product Master</display:caption>
        
            <display:column property="pcode" title="Product Code" />
            <display:column property="pname" title="Product Name"/>
            <display:column property="pack" title="Pack"/>
            <display:column property="pd_group" title="Product Group" /> 
            <display:column property="mname" title="Group Name" />
            <display:column property="trd_rt1" title="Trade Rate" format="{0,number,0.00}"  headerClass="r" class="r"/>
            <display:column property="net_rt1" title="Net Rate"   format="{0,number,0.00}" headerClass="r" class="r"/>
            <display:column property="mrp_rt1" title="MRP Rate"   format="{0,number,0.00}" headerClass="r" class="r"/>
        </display:table>
		
		


		
		
		
    </body>
	<br/>
 <jsp:include page="<%=foot%>"/> 
</html>

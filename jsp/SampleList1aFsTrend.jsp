<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.SampleRepo2FormBean" %>  
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
	  String whead = (String) request.getAttribute("head");
	%> 
 
    
 <p>&nbsp;  </p>
 <div class="div1">	 
 <display:table id="data" name="requestScope.SampleRepo2Form.rlist" requestURI="./SampleListRepo1a.do?actionRequested=SampleListRepo1a" pagesize="20" export="true" >
 
            <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
		    <display:caption media="excel rtf pdf"><%=whead%></display:caption>
 
		    <display:column property="pname" title="PRODUCT "  />						
            <display:column property="octqty" title="OCT QTY"  style="width:25px;"/>			
            <display:column property="octval" title="OCT VAL"  format="{0,number,0.00}"  style="width:50px;" />			
            <display:column property="novqty" title="NOV QTY" style="width:25px;"/>			
            <display:column property="novval" title="NOV VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="decqty" title="DEC QTY" style="width:25px;"/>			
            <display:column property="decval" title="DEC VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="janqty" title="JAN QTY" style="width:25px;"/>			
            <display:column property="janval" title="JAN VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="febqty" title="FEB QTY" style="width:25px;"/>			
            <display:column property="febval" title="FEB VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="marqty" title="MAR QTY" style="width:25px;"/>			
            <display:column property="marval" title="MAR VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="aprqty" title="APR QTY" style="width:25px;"/>			
            <display:column property="aprval" title="APR VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="mayqty" title="MAY QTY" style="width:25px;"/>			
            <display:column property="mayval" title="MAY VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="junqty" title="JUN QTY" style="width:25px;"/>			
            <display:column property="junval" title="JUN VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="julqty" title="JUL QTY" style="width:25px;"/>			
            <display:column property="julval" title="JUL VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="augqty" title="AUG QTY" style="width:25px;"/>			
            <display:column property="augval" title="AUG VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="sepqty" title="SEP QTY" style="width:25px;"/>			
            <display:column property="sepval" title="SEP VAL"  format="{0,number,0.00}" style="width:50px;"/>			
            <display:column property="tqty" title="TOTAL QTY" style="width:25px;"/>			
            <display:column property="tvalue" title="TOTAL VAL"  format="{0,number,0.00}" style="width:50px;"/>			
			
        </display:table>
	</div>
</body>
<br/>
<jsp:include page="<%=footer%>"/> 	
</html>
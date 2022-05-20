<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<%
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" flush="true"/> 

<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    </head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
  
    <br>   
	
	       <%
		   LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
           String ak = lfb.getBranch_name();
           String aki = lfb.getAccess_t();         
                  ak = ak + " "+aki;
           String whead=" ";
           Repo2FormBean rf = new Repo2FormBean();  
           List rl=(List) request.getAttribute("rlist"); 
           Iterator it =  rl.iterator();
           	if(it.hasNext())
             	{
             	 rf=(Repo2FormBean)it.next();    
             	 whead=rf.getNm3();
                }  
				 
  	     String lupdate = rf.getLupdate();	  
		 
         org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
         pageContext.setAttribute("totals", totals);
         %>  

    <br/>
<div id="main">		
 <display:table id="data"  class="simple nocol" name="requestScope.rlist" requestURI="./ListRepo3.do?actionRequested=ListRepo3" pagesize="25"  export="true" >
        <display:caption media="html"><br/>
		
		<table cellspacing="0" cellpadding="0" class="status" >
		  <tr>
			<td><div align="left">BRANCH NAME: <%=ak%> </div></td>
			<td><div align="right">LAST UPDATE: <%=lupdate%> </div></td>
		  </tr>
		  <tr>
			<td colspan="2"><div align="center"> <%=whead%> </div></td>
		  </tr>
		</table>
		
        </display:caption>
        <display:caption media=" excel rtf pdf">BRANCH NAME: <%=ak%>, <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>
		<display:column property="nm2" title="NAME"  /> 
		<display:column property="val2" title="SALABLE" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="val3" title="EXPIRY" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="val4" title="BREAKAGE" format="{0,number,0.00}" headerClass="r" class="r" />
		<display:column property="val5" title="TOTAL" format="{0,number,0.00}" headerClass="r" class="r" />
	</display:table>
</div>	
<br>
<br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
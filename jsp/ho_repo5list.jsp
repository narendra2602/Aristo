<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo5FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");

%>
 <jsp:include page="<%=toop%>" flush="true"/> 



<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aristo Pharmaceuticals Pvt. Ltd.</title>
             <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
 
    </head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
  
    <br>   
              <%
                String whead=" ";
                             
                HORepo5FormBean rf = new HORepo5FormBean();  
             	List rl=(List) request.getAttribute("rlist"); 
             	Iterator it =  rl.iterator();
             	if(it.hasNext())
             	{
             	  rf=(HORepo5FormBean)it.next();    
             	  whead=rf.getHead1();
                }  
              
              %> 
   
    
               <br/>    
 <display:table id="data"  class="simple nocol" name="requestScope.rlist" requestURI="./HOListRepo5.do?actionRequested=HOListRepo5" pagesize="25"  export="true">
    
               

		  
		    <display:caption media="html"><strong><br/><%=whead%></strong></display:caption>
		   
		    <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
            
        
            <display:column property="name" title="NAME"  /> 
            <display:column property="budget" title="BUDGET" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="gross" title="GROSS" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="credit" title="CREDIT" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="net" title="NET" format="{0,number,0.00}" headerClass="r" class="r" />
            <display:column property="ach" title="ACH %" format="{0,number,0.00}" headerClass="r" class="r" />			
            <display:column property="surp" title="SUR/DEF" format="{0,number,0.00}" headerClass="r" class="r" />
            
        </display:table> 
<br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>

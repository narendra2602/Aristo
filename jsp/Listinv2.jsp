<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Inv2FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ page import="org.displaytag.decorator.TableDecorator" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


	<% 
	String toop  = (String) session.getAttribute("top");
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

       <%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " Branch "+y;
       %>


     <%     
      String whead=" ";

            Inv2FormBean rf = new Inv2FormBean();  
        	List l = (List) request.getAttribute("rlist") ;  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Inv2FormBean)it.next();    
             	 whead="["+x+"] " +"  " + rf.getHead1();
                }  
      %>

     <br/>
   <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./ListInv2.do?actionRequested=ListInv2" pagesize="25" export="true" >
    
		    <display:caption media="html">   <strong><br/><%=whead%></strong>   </display:caption>
		    <display:caption media=" excel rtf pdf"><%=whead%></display:caption>
			
           <display:column property="code" title="CODE"/> 
           <display:column property="name" title="NAME"/> 
           <display:column property="pack" title="PACK" headerClass="r"  class="r"/> 
           <display:column property="tmonqty" title="THIS MONTH QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="tmonval" title="THIS MONTH VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="ymonqty" title="YTD QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="ymonval" title="YTD VAL"  format="{0,number,0.00}" headerClass="r" class="r"/> 
        </display:table> 
  
      <br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
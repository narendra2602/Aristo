<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Inv1FormBean" %>  
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

            Inv1FormBean rf = new Inv1FormBean();  
        	List l = (List) request.getAttribute("rlist") ;  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Inv1FormBean)it.next();    
             	 whead="["+x+"] " +"  " + rf.getHead1();
                }  
      %>

     <br/>
   <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./ListInv1.do?actionRequested=ListInv1" pagesize="25" export="true" >
    
		   <display:caption media="html">   <strong><br/><%=whead%></strong>   </display:caption>
		   <display:caption media=" excel rtf pdf"><%=whead%></display:caption>
        
           <display:column property="product" title="NAME"/> 
           <display:column property="pack" title="PACK" headerClass="r"  class="r"/> 
           <display:column property="opnbal" title="OPENING"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="dambal" title="DAMAN" format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="badbal" title="BADDI" format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="othbal" title="OTHERS"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="bplbal" title="BHOPAL"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="salqty" title="SALABLE QTY" format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="freqty" title="FREE QTY" format="{0,number,0}" headerClass="r" class="r" /> 
           <display:column property="expqty" title="EXP." format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="brkqty" title="BRK." format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="sales" title="SALES" format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="fissue" title="FREE ISSSUE" format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="salsamp" title="SALES TO SAMPLE" format="{0,number,0}" headerClass="r" class="r" />			
           <display:column property="woff" title="WRITTEN OFF" format="{0,number,0}" headerClass="r" class="r" />
           <display:column property="branches" title="BRANCHES" format="{0,number,0}" headerClass="r" class="r" />			           <display:column property="cnf" title="C&F" format="{0,number,0}" headerClass="r" class="r" />			
           <display:column property="closbal" title="CLOSING" format="{0,number,0}" headerClass="r" class="r" />			
           <display:column property="mfgval" title="MFG VAL" format="{0,number,0.00}" headerClass="r" class="r" />
</display:table> 
  
      <br><br> 
</body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
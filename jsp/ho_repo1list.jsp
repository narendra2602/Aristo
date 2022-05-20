<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo1FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 

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
		    <br/>    <br/> 
   	<html:form action="HOListRepo1.do?actionRequested=HOListRepo1" >    	
	<table class="search">
      <tr>
	    <td>
			<html:text property="search" />
			<html:submit  value="Search" styleClass="button"/> 
	    </td>
	  </tr>
	</table>
   </html:form>		

  
       <%
       String a[] = new String[50];
       String b[] = new String[50];
       String whead=" ";
       int x=0; 
       int v=0;
                
            HORepo1FormBean rf = new HORepo1FormBean();  
            List rl=(List) request.getAttribute("rlist"); 
            Iterator it =  rl.iterator();
            if(it.hasNext())
            {
             rf=(HORepo1FormBean)it.next();    
              x=rf.getBr();
              v=rf.getUv();
              whead=rf.getHead1();
             	
             	 for(int j=0;j<x;j++)
             	   {
                      a[j]=rf.getUhead(j);  
                      b[j]=rf.getVhead(j);  
                   } 
             }  
              
        %> 
    
    
  <display:table id="data"  name="requestScope.rlist" requestURI="./HOListRepo1.do?actionRequested=HOListRepo1" pagesize="25"  export="true" > 
    
	  <display:caption media="html"> <strong><br/><%=whead%></strong> </display:caption>
	  <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
             
      <display:column property="pcode" title="CODE" /> 
      <display:column property="pname" title="PRODUCT NAME" />
      <display:column property="pack" title="PACK" />
        
          <%
 	      for(int j=0;j<x;j++)
           {
             	if ((v==1)|| (v==3))
             	 {
                %>	     
       <display:column property='<%="qty1["+j+"]"%>' title="<%=a[j]%>" format="{0,number,0}" headerClass="r" class="r"/>
                <%   
                 }
				 
             	 if ((v==2)|| (v==3))
                 {
                 %>        
       <display:column property='<%="dval0["+j+"]"%>' title="<%=b[j]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
                 <% 
				 }
            } 
           %> 
        
        </display:table> 
        <br><br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
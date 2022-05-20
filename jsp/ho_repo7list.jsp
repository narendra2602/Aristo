<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo7FormBean" %>  
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
                             
/*              Repo2FormBean rf = new Repo2FormBean();  
             	List rl=(List) request.getAttribute("rlist"); 
             	Iterator it =  rl.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Repo2FormBean)it.next();    
             	  whead=rf.getNm3();
             	   
           	
                 }  
 */             
              %> 

       <%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " Branch "+y;

        %>

   
<%     
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      for (int j=1; j<=k; j++) 
        {
            HORepo7FormBean rf = new HORepo7FormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo7FormBean)it.next();    
             	  whead= rf.getHead1();
             	   
           	
                 }  
        	
             session.setAttribute("list",l);        	 
             iid = iid+j;
%>

  
    
    
    <br/>
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./HOListRepo7.do?actionRequested=HOListRepo7" pagesize="25"  export="true" >
    
            
		  
		    <display:caption media="html">
		      <strong><br/><%=whead%></strong>
		    </display:caption>
		   
		    <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
            
            <display:column property="name" title="NAME"  /> 
            <display:column property="sval" title="SALE VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="slval" title="SALABLE VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="expval" title="E/B/S/PD VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
            <display:column property="netval" title="NET VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
            
            
        </display:table> 
 <%
   }
 %>
         
        
<br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
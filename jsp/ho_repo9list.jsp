<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo9FormBean" %>  
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
		     LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " "+y;
         %>
  
 
 
 
      <%     
	  
	  String whead=" ";
      String b[] = new String[13];
      int z=0;
      int i=0;
            HORepo9FormBean rf = new HORepo9FormBean();  
           	List l=(List) request.getAttribute("rlist"); 

             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo9FormBean)it.next();    
           	     whead=rf.getHead1();
             	 z=rf.getMon();
				 for (i=0;i<z;i++)
  				 b[i]=rf.getVhead(i);  
				}  
      %> 
 

         <br/>
  <display:table id="data"  name="requestScope.rlist" requestURI="./HOListRepo9.do?actionRequested=HOListRepo9" pagesize="25"  export="true" >
	  
        <display:caption media="html"><br/> <%=whead%> </display:caption>
        <display:caption media=" excel rtf pdf"> <%=whead%> </display:caption>
        <display:column property="name" title="NAME" />
        <%  for(int p=0;p<z;p++) { %>	     
        <display:column property='<%="val1["+p+"]"%>' title="<%=b[p]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
        <% }  %>       
		 
	</display:table> 
	<br>
	<br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo8FormBean" %>  
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
   
         <%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " Branch "+y;

        %>
  
 
 
 
<%     
	  String whead=" ";
	  String a[] = new String[13];
	  String b[] = new String[13];
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      int z=0;
      int i=0;
      int uv=0;
      for (int j=1; j<=k; j++) 
        {
            HORepo8FormBean rf = new HORepo8FormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo8FormBean)it.next();    
             	  whead= rf.getHead1();
             	  z=rf.getMon();
             	  uv=rf.getUv();   
             	   for(i=0;i<z;i++)
             	     {
						a[i]=rf.getUhead(i);  
						b[i]=rf.getVhead(i);  
                     } 
           	
                 }  
        	
             session.setAttribute("list",l);        	 
             iid = iid+j;
%> 
 
 
 
    <br/>
      <display:table id="<%=iid%>"  name="sessionScope.list" requestURI="./HOListRepo8.do?actionRequested=HOListRepo8" pagesize="25"  export="true" > 
    
            
		  
		   <display:caption media="html"><strong><br/><%=whead%></strong></display:caption>
		   <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
           
           <display:column property="name" title="NAME" />
           <display:column property="no_of_mr" title="F.S." />

           <%
             	   for(int p=0;p<z;p++)
             	     {
             	        if ((uv==1) || (uv==3))
           	            {
             %>	     
           <display:column property='<%="qty1["+p+"]"%>' title="<%=a[p]%>" format="{0,number,0}" headerClass="r" class="r"/>
             <%        
                        }

              	        if ((uv==2) || (uv==3))
             	        {
             %>	        
           <display:column property='<%="val1["+p+"]"%>' title="<%=b[p]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
             <%
                        }
                     }
           %>        

        </display:table> 
 <%
   }
 %>
<br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.HORepo8FormBean" %>  
<%@ page import="java.util.*" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="org.displaytag.sample.*" %> 


<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" flush="true"/> 

<html> 

    <head>
       <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    </head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
<br/>
      <%     
	  String whead=null;
	  String b[] = new String[13];
      int z=0;
      int i=0;
      
      HORepo8FormBean rf = new HORepo8FormBean();  
	  whead = (String) request.getAttribute("head");
      List l = (List) request.getAttribute("rlist");  
	  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo8FormBean)it.next();    
             	  z=rf.getMon();
             	   for(i=0;i<z;i++)
             	     {
						b[i]=rf.getVhead(i);  
                     } 
           	
                 }  
    %> 
 
    <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((HORepo8FormBean)getCurrentRowObject()).getColor();
			  if (data==2)
			     return "bad";
			  else
			    if (data==3)
 				     return "vbad";
				else
				    if (data==4)
				       return "good";
				    else 
    				       return "abc";
				  	 
            }
           
        });
  </jsp:scriptlet>
 
 
    <br/>
      <display:table id="data"  name="requestScope.rlist" requestURI="./HOListRepo11.do?actionRequested=HOListRepo11" pagesize="25"  export="true" decorator="dyndecorator"> 
    
		   <display:caption media="html"><strong><br/><%=whead%></strong></display:caption>
		   <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
           
           <display:column property="name" title="NAME" />

                    <%
             	   for(int p=0;p<z;p++)
             	     {
				    %>	 
           <display:column property='<%="val1["+p+"]"%>' title="<%=b[p]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
                    <%
                     }
                    %>        

        </display:table> 

      <br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
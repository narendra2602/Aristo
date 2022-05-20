<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo6FormBean" %>  
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
            HORepo6FormBean rf = new HORepo6FormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo6FormBean)it.next();    
             	  whead= rf.getHead1();
             	   
           	
                 }  
        	
             session.setAttribute("list",l);        	 
             iid = iid+j;
%>

  
   <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((HORepo6FormBean)getCurrentRowObject()).getColor();
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
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./HOListRepo10.do?actionRequested=HOListRepo10" pagesize="25"  export="true" decorator="dyndecorator" >
		    <display:caption media="html"><strong><br/><%=whead%></strong></display:caption>
		    <display:caption media=" excel pdf rtf"><%=whead%></display:caption>
            <display:column property="name" title="NAME"  /> 
            <display:column property="sqty" title="SALE QTY" format="{0,number,0}" headerClass="r" class="r"/> 
            <display:column property="sval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="slqty" title="SALABLE QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
            <display:column property="slval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="expqty" title="E/B/S/PD QTY" format="{0,number,0}"  headerClass="r" class="r"/> 
            <display:column property="expval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
            <display:column property="netqty" title="NET"  format="{0,number,0}" headerClass="r" class="r" /> 
            <display:column property="netval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
  </display:table> 
 <%
   }
 %>
         
        
     <br><br> 
   </body>
 <jsp:include page="<%=foot%>"/> 
</html> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
%>
 <jsp:include page="<%=toop%>" flush="true"/> 



<html> 
  



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aristo Pharmaceuticals Pvt. Ltd.</title>
         <%  
             String colour = session.getAttribute("access").toString();

             //out.println(coloour);
            // String colour= "MF";

           if (colour.equals("MF"))
            {
           %>
           <link href="css/MF.css" rel="stylesheet" type="text/css" />
            
           <% } if (colour.equals("TF"))
              {
            %>
             <link href="css/TF.css" rel="stylesheet" type="text/css" />
             
           <% } if (colour.equals("Genetica"))
              {
            %>
             <link href="css/Genetica.css" rel="stylesheet" type="text/css" />
            <%}%>
 

 
    </head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
   
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
     org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
     //totals.setTotalLabel("Total");
     pageContext.setAttribute("totals", totals);
  %>   
    

<%     
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      for (int j=1; j<=k; j++) 
        {
            Repo2FormBean rf = new Repo2FormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Repo2FormBean)it.next();    
             	  whead="["+x+"]        " +"             " + rf.getNm3();
             	   
           	
                 }  
        	
             session.setAttribute("list",l);        	 
             iid = iid+j;
%>

      <display:table id="<%=iid%>"  class="simple nocol" name="sessionScope.list" requestURI="./ListRepo4.do?actionRequested=ListRepo4" pagesize="25"  export="true" decorator="totals">
    
            
		   
		    <display:caption media="html">
		      <strong><%=whead%></strong>
		    </display:caption>
		   
		    <display:caption media=" excel rtf pdf"><%=whead%></display:caption>
            
        
            <display:column property="mcode" title="CODE"  /> 
            <display:column property="mname" title="NAME"  /> 
            <display:column property="pack" title="PACK"  /> 
            <display:column property="rate" title="RATE"  /> 
            <display:column property="qty2" title="SALE QTY"  /> 
            <display:column property="val2" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"  total="true" /> 
            <display:column property="qty3" title="SALABLE QTY"  /> 
            <display:column property="val3" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" total="true" /> 
            <display:column property="qty4" title="E/B/S/PD QTY"  /> 
            <display:column property="val4" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" total="true"  />
            <display:column property="qty5" title="NET"  /> 
            <display:column property="val5" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" total="true"  />
            
            
        </display:table> 

 <%
   }
 %>

<br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
 





























 
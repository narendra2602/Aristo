<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="fac.aristo.valueobject.FacRepo1FormBean" %>  

<%@page import="java.util.*"%>

<html>
<body>
   <% List list=(List)request.getAttribute("Loclist");
//      String op = (String) request.getAttribute("opt");
      Iterator it = list.iterator();
  	  FacRepo1FormBean r=null;
	  
   %>
	  <select name="loc" style="width:190px" > 
   <%
 
	  while (it.hasNext())
	  {
         r = (FacRepo1FormBean) (it.next());
         out.println("<option value='"+r.getLcval()+"'>"+r.getLcname()+"</option>") ;
	  }

    %>
	
    </select>

								   
   
</body>
</html>
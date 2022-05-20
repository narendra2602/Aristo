<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="fac.aristo.valueobject.FacRepo5FormBean" %>  

<%@page import="java.util.*"%>

<html>
<body>
   <% List list=(List)request.getAttribute("Loclist");
//      String op = (String) request.getAttribute("opt");
      Iterator it = list.iterator();
  	  FacRepo5FormBean r=null;
	  
   %>
	  <select name="sbranch" style="width:300px" id="mySelect"> 
   <%
 
	  while (it.hasNext())
	  {
         r = (FacRepo5FormBean) (it.next());
         out.println("<option value='"+r.getBrval()+"'>"+r.getBrname()+"</option>") ;
	  }

    %>
	
    </select>

								   
   
</body>
</html>
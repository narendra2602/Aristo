<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
<%@page import="java.util.*"%>

<html>

<body>

   <% List list=(List)request.getAttribute("rlist");
      Iterator it = list.iterator();
  	  Repo2FormBean r=null;
   %>
	  <select name="code" id="abc" style="width:180px" size="5" >  
<%	  
	  while (it.hasNext())
	  {
           r = (Repo2FormBean) (it.next());
            
         out.println("<option value='"+r.getQty2()+"'>"+r.getNm2()+"</option>") ;
	  }
%>	  

      </select>  

  
   
</body>
</html>
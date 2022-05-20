<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
<%@ page import="com.aristo.valueobject.HORepo7FormBean" %>  
<%@ page import="com.aristo.valueobject.MktFormBean" %>  
<%@ page import="com.aristo.valueobject.HOForm2FormBean" %>  

<%@page import="java.util.*"%>

<html>
<body>
   <% List list=(List)request.getAttribute("rlist");
      String op = (String) request.getAttribute("opt");
      Iterator it = list.iterator();
  	  Repo2FormBean r=null;
	  HORepo7FormBean hr=null;
	  MktFormBean mr=null;
	  HOForm2FormBean hmr=null;
   %>
	  <select name="codes" style="width:300px" size="7" id="available" onChange="doUpdate( false,true );"> 
   <%
 

   if (op.equals("Opt2"))
   {
	  while (it.hasNext())
	  {
           mr = (MktFormBean) (it.next());
            
         out.println("<option value='"+mr.getQty2()+"'>"+mr.getNm2()+"</option>") ;
	  }
   }
  

   if (op.equals("HOpt2"))
   {
	  while (it.hasNext())
	  {
           hmr = (HOForm2FormBean) (it.next());
            
         out.println("<option value='"+hmr.getPcode()+"'>"+hmr.getPname()+"</option>") ;
	  }
   }



   if (op.equals("Opt7"))
   {
	  while (it.hasNext())
	  {
           r = (Repo2FormBean) (it.next());
            
         out.println("<option value='"+r.getQty2()+"'>"+r.getNm2()+"</option>") ;
	  }
   }

	   if (op.equals("HOpt7"))
	   {
		  while (it.hasNext())
		  {
			   hr = (HORepo7FormBean) (it.next());
				
			 out.println("<option value='"+hr.getGcode()+"'>"+hr.getGname()+"</option>") ;
		  }
	   }



    %>
      </select>

								   
   
</body>
</html>
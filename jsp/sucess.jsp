<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<%@ page import="com.aristo.valueobject.TerFormBean" %>
 

<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.util.Iterator" %>
<%@ page import= "java.util.List" %>

<%

       List l= new ArrayList(); 
       l=(List)request.getAttribute("d");
        List  data = new ArrayList(); 
            Iterator it =l.iterator();
            TerFormBean ef = new TerFormBean();

        	   while(it.hasNext())
        	   
        		{
        		    ef=(TerFormBean)it.next();
        		   System.out.println(ef.getDepo_code());
         		    System.out.println(ef.getTer_name());
         		    data.add(ef.getTer_name());
         		    
        		}             
       
                    out.println(data);                 
      
 %>
 
 
   
   <% List list=(List)request.getAttribute("d");
                                   
      //out.println(list.get(0)) ; %>                            

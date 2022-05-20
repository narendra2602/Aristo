<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.MktFormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" flush="true"/> 

<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        String ak = lfb.getBranch_name();
        String aki = lfb.getAccess_t();         
                ak = ak + " "+aki;
        
       org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
       pageContext.setAttribute("totals", totals);
       %>   

      <%     
      int tl=0;	
      String a[] = new String[13];
	 

            MktFormBean rf = new MktFormBean();  
        	List l = (List) request.getAttribute("rlist");
           	Iterator it =  l.iterator();
           	if(it.hasNext())
           	{
             	  rf=(MktFormBean)it.next();    
            	  whead=rf.getNm3();
            	  tl=rf.getUv();
				  for (int e=0; e<13; e++)
				  {
						  a[e]=rf.getNm9(e);  
				   }	  
				  
             }  
 	 	     String lupdate = rf.getLupdate();	                
       %>

      <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((MktFormBean)getCurrentRowObject()).getColor();
			  if (data==2)
			     return "bad";
			  else
			    if (data==3)
 				     return "good";
				  else 
    				       return "abc";
				  	 
            }
        });
  </jsp:scriptlet>
    
    <br/>
  <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./NEWListForm11.do?actionRequested=NEWListForm11" pagesize="25" export="true" decorator="dyndecorator">

		<display:caption media="html"> <div style="font-size:18px" align="left"><strong><br/><%=whead%></strong></div> </display:caption>
        <display:caption media="excel"><%=whead%></display:caption>

            <display:column property="mname" title="NAME"  /> 
	        <display:column property='<%="nm1[0]"%>' title="TYPE" />

	            <%
             	   for(int g=0;g<13;g++)
           	     {
				    if (tl==1) 
					 {
                %> 	     
                     <display:column property='<%="qty0["+g+"]"%>' title="<%=a[g]%>" headerClass="r" class="r"/>
					 <%}
					   else if (tl==2) 
					   {
					 
					 %>
					 
					 <display:column property='<%="dval0["+g+"]"%>' title="<%=a[g]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
				     
					 
             <%  
			            }
			  }
             %> 
</display:table>
         
        
<br><br> 
</body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
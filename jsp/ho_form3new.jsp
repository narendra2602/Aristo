<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.HOForm3FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ page import="org.displaytag.decorator.TableDecorator" %> 


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
                             
              %> 

       <%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " Branch "+y;

        %>


<%     
      double per=0.00;
      String head=null;
      String head1=null;
      String head2=null;
      String head3=null;
	  int rankorder=1;
            HOForm3FormBean rf = new HOForm3FormBean();  
        	List l = (List) request.getAttribute("rlist") ;  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HOForm3FormBean)it.next();    
             	  whead=rf.getHead1();
				  per = rf.getPer();
				  head = " MTH TGT "+rf.getNm2();
				  head1 = " MONTHLY   SALES     "+rf.getNm2();
				  head2 = "CUM TGT "+rf.getNm4();
             	  head3 = "CUM SALES "+rf.getNm4(); 
				  rankorder=Integer.parseInt(rf.getNm4());
                 }  
        	
%>

  
    
  
    <br/>
 <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./NWListForm3.do?actionRequested=NWListForm3" pagesize="25" export="true">
    
            
		  
		   
            <display:caption media="html"> <div style="font-size:18px" align="left"><strong><br/><%=whead%></strong></div> </display:caption>
		    <display:caption media="excel"><%=whead%></display:caption>
            
            <display:column property="rank" title="RANK" /> 
            <display:column property="name" title="NAME" /> 
            <display:column property="fs" title="FS" /> 
			<% if(rankorder==1)
			   { 
             %>

            <display:column property="cumach" title="ACH%" format="{0,number,0.0}" headerClass="r" class="r" /> 
            <display:column property="cumgth" title="GTH%" format="{0,number,0.0}" headerClass="r" class="r" />
            <display:column property="pmr" title="P.M.R." format="{0,number,0}" headerClass="r" class="r" style="background-color: rgb(105, 105, 105);color: rgb(255, 255, 255); font-weight:normal"  />
			<% }
			   else
			   { 
             %>
            <display:column property="cumach" title="ACH%" format="{0,number,0.0}" headerClass="r" class="r" style="background-color: rgb(105, 105, 105);color: rgb(255, 255, 255); font-weight:normal"/> 
            <display:column property="cumgth" title="GTH%" format="{0,number,0.0}" headerClass="r" class="r" />
            <display:column property="pmr" title="P.M.R." format="{0,number,0}" headerClass="r" class="r"   />
			<%
			   }
			%>
				 
                       
        </display:table> 
  
         
        
<br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
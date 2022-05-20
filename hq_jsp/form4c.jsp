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
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      for (int j=1; j<=k; j++) 
        {
            MktFormBean rf = new MktFormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(MktFormBean)it.next();    
             	  whead=rf.getNm3();
                 }  
 	 	     String lupdate = rf.getLupdate();	                
             session.setAttribute("list",l);        	 
             iid = iid+j;
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
	  <div id="main">
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./HQListForm4.do?actionRequested=HQListForm4" pagesize="25"  export="true" decorator="dyndecorator">
	  
        <display:caption media="html"><br/>
		
		<table cellspacing="0" cellpadding="0" class="status" >
		  <tr>
			<td><div align="left">BRANCH NAME: <%=ak%> </div></td>
			<td><div align="right">LAST UPDATE: <%=lupdate%> </div></td>
		  </tr>
		  <tr>
			<td colspan="2"><div align="center"> <%=whead%> </div></td>
		  </tr>
		</table>

        </display:caption>
        <display:caption media=" excel rtf pdf">BRANCH NAME: <%=ak%>, <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>

		    <display:column property="mname" title="BRAND NAME" />
            <display:column property="dval0[0]" title="LY PMR" headerClass="r" class="r"  format="{0,number,0.00}"/> 
            <display:column property="dval0[1]" title="TY AVG/MTH TGT" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[2]" title="OCT" headerClass="r" class="r" format="{0,number,0.00}" /> 
            <display:column property="dval0[3]" title="NOV" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[4]" title="DEC" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[5]" title="JAN" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[6]" title="FEB" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[7]" title="MAR" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[8]" title="APR" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[9]" title="MAY" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[10]" title="JUN" headerClass="r" class="r" format="{0,number,0.00}"/> 	
            <display:column property="dval0[11]" title="JUL" headerClass="r" class="r" format="{0,number,0.00}"/> 	
            <display:column property="dval0[12]" title="AUG" headerClass="r" class="r" format="{0,number,0.00}"/> 	
            <display:column property="dval0[13]" title="SEP" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval0[14]" title="CUM TOTAL" headerClass="r" class="r" format="{0,number,0.00}"/> 	
 			
</display:table> 
</div>
 <%
   }
 %>
         
        
 
   <jsp:include page="<%=foot%>"/> 
  </body>
 
</html>
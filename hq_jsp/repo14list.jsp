<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo14FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

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
           LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
           String x = lfb.getBranch_name();
           String y = lfb.getAccess_t();         
           x = x + " "+y;
        %>

       <br>   
              <%
                String whead=" ";
                Repo14FormBean rf = new Repo14FormBean();  
             	List rl=(List) request.getAttribute("rlist"); 
             	Iterator it =  rl.iterator();
             	if(it.hasNext())
             	{
             	  rf=(Repo14FormBean)it.next();    
             	  whead=rf.getHead1();
                }  
    	     String lupdate = rf.getLupdate();	 
              %> 
			  
   <jsp:scriptlet>
	request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
	{
		public String addRowClass()
		{
		  int data = ((Repo14FormBean)getCurrentRowObject()).getColor();
		  if (data==2)
			 return "vbad";
		  else
			if (data==3)
				 return "good";
			else
				if (data==4)
				   return "good";
				else 
					   return "abc";
				 
		}
	   
	});
</jsp:scriptlet>

    <br/>
<div id="main">	
 <display:table id="data"  class="simple nocol" name="requestScope.rlist" requestURI="./HQListRepo14.do?actionRequested=HQListRepo14" pagesize="25"  export="true" decorator="dyndecorator" >
   <display:caption media="html"><br/>
		<table cellspacing="0" cellpadding="0" class="status" >
		  <tr>
			<td><div align="left">BRANCH NAME: <%=x%> </div></td>
			<td><div align="right">LAST UPDATE: <%=lupdate%> </div></td>
		  </tr>
		  <tr>
			<td colspan="2"><div align="center"> <%=whead%> </div></td>
		  </tr>
		</table>
   </display:caption>
   <display:caption media=" excel rtf pdf">BRANCH NAME: <%=x%>, <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>
   
            <display:column property="name" title="NAME"  /> 
            <display:column property="salval" title="SALABLE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="expval" title="EXPIRY" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="brkval" title="BREAKAGE" format="{0,number,0.00}" headerClass="r" class="r" />
            <display:column property="total" title="TOTAL" format="{0,number,0.00}" headerClass="r" class="r" />
    </display:table> 
</div>	
      <br>
      <br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" flush="true"/> 

<html> 
    <head>
<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
             <style type="text/css">
<!--
.style4 {font-family: "Book Antiqua";
	color: #000000;
}
.style5 {color: #000000; }
.style6 {font-size: 15px}
-->
             </style>
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
             	  whead=rf.getNm3();
             	   
           	
                 }  
 	 	     String lupdate = rf.getLupdate();	                
             session.setAttribute("list",l);        	 
             iid = iid+j;
   %>

    <br/>
	<div id="main">	
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./HQListRepo6.do?actionRequested=HQListRepo6" pagesize="30"  export="true" decorator="totals">
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

		
		
            <display:column property="mname" title="NAME"  /> 
            <display:column property="qty2" title="SALE QTY"  headerClass="r" class="r"/> 
            <display:column property="val2" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="qty3" title="SALABLE QTY"  headerClass="r" class="r"/> 
            <display:column property="val3" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="qty4" title="E/B/S/PD QTY"  headerClass="r" class="r"/> 
            <display:column property="val4" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
            <display:column property="qty5" title="NET" headerClass="r" class="r" /> 
            <display:column property="val5" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
	</display:table> 
</div>	
 <%
   }
 %>
         
        
<br><br> 
</body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
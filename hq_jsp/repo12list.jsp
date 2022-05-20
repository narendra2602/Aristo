<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo12FormBean" %>  
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
        %> 
   
       <br>   
        <%
        String whead=" ";
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        String x = lfb.getBranch_name();
        String y = lfb.getAccess_t();         
        x = x + " "+y;

       org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
       pageContext.setAttribute("totals", totals);

      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
	  String is=null;
	  Integer e;
      for (int j=1; j<=k; j++) 
        {
            Repo12FormBean rf = new Repo12FormBean();  
			e = new Integer(j);
			is = e.toString();
        	List l = (List) map.get(is);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Repo12FormBean)it.next();    
             	  whead=rf.getHead1();
             	   
           	
                 }  
      	     String lupdate = rf.getLupdate();	 
             session.setAttribute("list",l);        	 
             iid = iid+j;
      %>

    
    <br/>
	<div id="main">	
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./HQListRepo12.do?actionRequested=HQListRepo12" pagesize="25"  export="true" decorator="totals">

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
		
		
		
        <display:column property="mcode" title="CODE"  /> 
            <display:column property="mname" title="NAME"  /> 
            <display:column property="pack" title="PACK"  /> 
            <display:column property="rate" title="RATE" format="{0,number,0.00}" headerClass="r" class="r" /> 
            <display:column property="sqty" title="SALE QTY" format="{0,number,0}" headerClass="r" class="r" /> 
            <display:column property="sval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="slqty" title="SALABLE QTY" format="{0,number,0}" headerClass="r" class="r" /> 
            <display:column property="slval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/> 
            <display:column property="pqty" title="E/B/S/PD QTY" format="{0,number,0}" headerClass="r" class="r" /> 
            <display:column property="ppval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/>
            <display:column property="netqty" title="NET"  format="{0,number,0}" headerClass="r" class="r"/> 
            <display:column property="netval" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r"/>
	</display:table>
</div>	
 <%
   }
 %>
         
        
<br><br> 
</body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
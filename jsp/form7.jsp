<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.MktFormBean" %>  
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
		   LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
           String ak = lfb.getBranch_name();
           String aki = lfb.getAccess_t();         
                  ak = ak + " "+aki;
           %>

              <%
              String a[] = new String[50];
              float v1[] = new float[50];
              String whead=" ";
              int x=0; 
              int v=0;
                
              
             	MktFormBean rf = new MktFormBean();  
             //	List rl= new ArrayList();
             	List rl=(List) request.getAttribute("rlist"); 
             	Iterator it =  rl.iterator();
             	if(it.hasNext())
             	{
             	 rf=(MktFormBean)it.next();    
             	  x=rf.getQty2();
             	 
             	  whead=rf.getNm3();
             	  
             	
             	   for(int j=0;j<x;j++)
             	     {
                          a[j]=rf.getNm1(j);  
                     } 
                 }  

 	 	     String lupdate = rf.getLupdate();	                
              %> 
   <br/>
<div id="main">	    
   <display:table id="data" name="requestScope.rlist" requestURI="./ListForm7.do?actionRequested=ListForm7" pagesize="25" export="true">
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

			<display:column property="mcode" title="CODE" /> 
            <display:column property="mname" title="PRODUCT NAME" />
            <display:column property="pack" title="PACK" />

             <%
              for(int j=0;j<x;j++)
              {
             %>	     
            <display:column property='<%="dval0["+j+"]"%>' title="<%=a[j]%>" headerClass="r" class="r" format="{0,number,0.00}"/>

             <% 
 	           }
             %> 
            <display:column property="qty3" title="TOTAL" headerClass="r" class="r" /> 
	</display:table> 
</div>	
<br>
<br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
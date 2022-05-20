<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo2FormBean" %>  
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

 	  String whead=" ";
	  String a[] = new String[13];
	  String b[] = new String[13];
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      int z=0;
      int i=0;
      int uv=0;
      for (int j=1; j<=k; j++) 
        {
            Repo2FormBean rf = new Repo2FormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Repo2FormBean)it.next();    
             	  whead=rf.getNm3();
             	  z=rf.getMcode();
             	  uv=rf.getQty2();   
             	   for(i=0;i<z;i++)
             	     {
						a[i]=rf.getNm1(i);  
						b[i]=rf.getNm9(i);  
                     } 
           	
                 }  
 	 	     String lupdate = rf.getLupdate();	                
             session.setAttribute("list",l);        	 
             iid = iid+j;
      %> 
 
    <br/>
	<div id="main">	
      <display:table id="<%=iid%>"  name="sessionScope.list" requestURI="./ListRepo8.do?actionRequested=ListRepo8" pagesize="25"  export="true" >
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

              <display:column property="name" title="NAME" />
              <display:column property="no_of_mr" title="F.S." />
        
             <%
             	   for(int p=0;p<z;p++)
             	     {
             	        if ((uv==1) || (uv==3))
             	        {
             %>	     
            <display:column property='<%="qty1["+p+"]"%>' title="<%=a[p]%>" headerClass="r" class="r"/>
              <%        
                         }

              	        if ((uv==2) || (uv==3))
             	        {
               %>	        
            <display:column property='<%="val1["+p+"]"%>' title="<%=b[p]%>" format="{0,number,0.00}" headerClass="r" class="r"/>
              <%
                        }
                     }
              %>        
	</display:table> 
</div>	
 <%
   }
 %>
<br><br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
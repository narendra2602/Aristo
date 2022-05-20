<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo10FormBean" %>  
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
   String x = lfb.getBranch_name();
   String y = lfb.getAccess_t();         
          x = x + " - "+ y;

	  String whead=" ";
	  String a[] = new String[13];
	  String b[] = new String[13];
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
      int z=0;
      int i=0;
      int uv=0;
  	  String is=null;
	  Integer e;

      for (int j=1; j<=k; j++) 
        {
            Repo10FormBean rf = new Repo10FormBean();  
			e = new Integer(j);
			is = e.toString();

        	List l = (List) map.get(is);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Repo10FormBean)it.next();    
             	  whead=rf.getHead1();
             	  z=rf.getMon();
             	  uv=rf.getUv();   
             	   for(i=0;i<z;i++)
             	     {
						a[i]=rf.getUhead(i);  
						b[i]=rf.getVhead(i);  
                     } 
           	
                 }  
       	     String lupdate = rf.getLupdate();	  
             session.setAttribute("list",l);        	 
             iid = iid+j;
      %> 
 
    <br/>
<div id="main">	
   <display:table id="<%=iid%>"  name="sessionScope.list" requestURI="./HQListRepo10.do?actionRequested=HQListRepo10" pagesize="25"  export="true" > 
		  
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
   <display:caption media=" excel rtf pdf">BRANCH NAME: <%=x%>,  <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>
           
		    <display:column property="name" title="NAME" />
            
             <%
             	   for(int p=0;p<z;p++)
             	     {
             	        if ((uv==1) || (uv==3))
             	        {
             %>	     
        <display:column property='<%="qty1["+p+"]"%>' title="<%=a[p]%>" format="{0,number,0}" headerClass="r" class="r"/>
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
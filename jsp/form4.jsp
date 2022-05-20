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
      <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./ListForm4.do?actionRequested=ListForm4" pagesize="25"  export="true" decorator="dyndecorator">
	  
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

            <display:column property="mcode" title="CODE"  /> 
            <display:column property="mname" title="NAME"  /> 
            <display:column property="pack" title="PACK"  /> 
            <display:column property="qty2" title="TGT" headerClass="r" class="r" /> 
            <display:column property="qty3" title="SALE" headerClass="r" class="r" /> 
            <display:column property="qty4" title="ACH %" headerClass="r" class="r" /> 
            <display:column property="qty5" title="S/D +/-" headerClass="r" class="r"/> 
            <display:column property="qty6" title="CUMM TGT" headerClass="r" class="r"/> 
            <display:column property="qty7" title="CUMM SALE" headerClass="r" class="r"/> 
            <display:column property="qty8" title="CUMM ACH %" headerClass="r" class="r"/> 
            <display:column property="qty9" title="CUMM S/D +/-" headerClass="r" class="r"/> 
            <display:column property="qty10" title="LYR" headerClass="r" class="r"/> 	
            <display:column property="dval1" title="GR %" headerClass="r" class="r" format="{0,number,0.0}"/> 			
            <display:column property="qty11" title="PMR" headerClass="r" class="r"/> 	
	</display:table>
</div>	
 <%
   }
 %>
         
        
   <br><br> 
  </body>
 <jsp:include page="<%=foot%>"/> 
</html>
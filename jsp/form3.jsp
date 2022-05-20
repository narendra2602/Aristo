<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.MktFormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ page import="org.displaytag.decorator.TableDecorator" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


<% 
String toop = (String) session.getAttribute("top");
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
      double per=0.00;
      String head=null;
      String head1=null;
      String head2=null;
      String head3=null;

            MktFormBean rf = new MktFormBean();  
        	List l = (List) request.getAttribute("rlist") ;  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(MktFormBean)it.next();    
             	  whead=rf.getNm3();
				  per = rf.getDval1();
				  head = " MTH TGT "+per+" % "+rf.getNm2();
				  head1 = " MONTHLY   SALES     "+rf.getNm2();
				  head2 = "CUM TGT "+rf.getNm4();
             	  head3 = "CUM SALES "+rf.getNm4(); 
           	
                 }  
 	 	     String lupdate = rf.getLupdate();	                
				 
        	
      %>

   <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((MktFormBean)getCurrentRowObject()).getMcode();
			  if (data==2)
			     return "bad";
			  else
			    if (data==3)
 				     return "vbad";
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
      <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./ListForm3.do?actionRequested=ListForm3" pagesize="25"  export="true" decorator="dyndecorator">
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
		
		
            <display:column property="mname" title="NAME" /> 
            <display:column property="qty2" title="<%=head%>" style="width: 64px;" headerClass="r"  class="r"/> 
            <display:column property="qty3" title="<%=head1%>" style="width: 64px;" headerClass="r" class="r"/> 
            <display:column property="dval4" title="MTH ACH%" format="{0,number,0.0}" headerClass="r" class="r"/> 
            <display:column property="qty5" title="<%=head2%>" style="width: 74px;" headerClass="r" class="r"/> 
            <display:column property="qty6" title="<%=head3%>" style="width: 74px;" headerClass="r" class="r"/> 
            <display:column property="qty7" title="LAST YEAR" headerClass="r" class="r" />
            <display:column property="dval8" title="CUM ACH%" format="{0,number,0.0}" headerClass="r" class="r" /> 
            <display:column property="dval9" title="CUM GTH%" format="{0,number,0.0}" headerClass="r" class="r" />
            <display:column property="dval10" title="P.M.R." format="{0,number,0}" headerClass="r" class="r" />
            <display:column property="qty8" title="SUR/DEF." headerClass="r" class="r" />
  </display:table> 
</div>	
<br>
<br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
 
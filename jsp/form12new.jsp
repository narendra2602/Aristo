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
            MktFormBean rf = new MktFormBean();  
        	List l = (List) request.getAttribute("rlist");
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(MktFormBean)it.next();    
             	  whead=rf.getNm3();
                 }  
 	 	     String lupdate = rf.getLupdate();	                
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
      <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./NWListForm12.do?actionRequested=NWListForm12" pagesize="25"  export="true" decorator="dyndecorator">
	  
		<display:caption media="html"> <div style="font-size:18px" align="left"><strong><br/><%=whead%></strong></div> </display:caption>
        <display:caption media="excel">BRANCH NAME: <%=ak%>, <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>

           
            <display:column property="mname" title="MONTH"  /> 
			<display:column property="no_of_mr" title="FS" headerClass="r" class="r" /> 
            <display:column property="dval3" title="MONTH TGT" headerClass="r" class="r"  format="{0,number,0.00}" /> 
            <display:column property="dval4" title="MONTH SALE" headerClass="r" class="r" format="{0,number,0.00}" /> 
			<display:column property="dval5" title="MONTH LYS" headerClass="r" class="r" format="{0,number,0.00}" /> 
            <display:column property="dval6" title="MONTH ACH %" headerClass="r" class="r" format="{0,number,0.00} " /> 
			<display:column property="dval2" title="MONTH GR %" headerClass="r" class="r" format="{0,number,0.00}"/> 
			<display:column property="dval15" title="MONTH PMR" headerClass="r" class="r" format="{0,number,0.00}"/> 	
            <display:column property="dval7" title="MONTH S/D +/-" headerClass="r" class="r"  format="{0,number,0.00}"/> 
            <display:column property="dval8" title="CUMM TGT" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval9" title="CUMM SALE" headerClass="r" class="r"  format="{0,number,0.00}"/> 
		    <display:column property="dval12" title="CUMM LYR" headerClass="r" class="r" format="{0,number,0.00}"/> 	
            <display:column property="dval10" title="CUMM ACH %" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval1" title="CUMM GR %" headerClass="r" class="r" format="{0,number,0.00}"/> 
            <display:column property="dval14" title="CUMM PMR" headerClass="r" class="r" format="{0,number,0.00}"/> 	
			<display:column property="mcode" title="CUMM FS" headerClass="r" class="r" /> 
			<display:column property="dval11" title="CUMM S/D +/-" headerClass="r" class="r"  format="{0,number,0.00}"/> 
			<display:column property="dval13" title="CUMM INCREMENTAL SALE" headerClass="r" class="r" format="{0,number,0.00}"/> 
          
	</display:table>
</div>	
         
        
   <br><br> 
  </body>
 <jsp:include page="<%=foot%>"/> 
</html>
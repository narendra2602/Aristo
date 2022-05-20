<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Inv3FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ page import="org.displaytag.decorator.TableDecorator" %> 
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aristo Pharmaceuticals Pvt. Ltd.</title>
        <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    </head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
   
    <br>   

       <%    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
             String x = lfb.getBranch_name();
             String y = lfb.getAccess_t();         
             x = x + " Branch "+y;
       %>


     <%     
      String whead=" ";

            Inv3FormBean rf = new Inv3FormBean();  
        	List l = (List) request.getAttribute("rlist") ;  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(Inv3FormBean)it.next();    
             	 whead=rf.getHead1();
                }  
      %>

   <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((Inv3FormBean)getCurrentRowObject()).getCode();
			  if (data==0)
			     return "bad";
			  else
			    if (data==1)
 				     return "good";
				else
				    if (data==2)
				       return "good";
				    else 
    				       return "abc";
				  	 
            }
           
        });
  </jsp:scriptlet>





     <br/>
   <display:table id="data" class="simple nocol" name="requestScope.rlist" requestURI="./HOListInv3.do?actionRequested=HOListInv3" pagesize="25" export="true"  decorator="dyndecorator">
    
		   <display:caption media="html">   <strong><br/><%=whead%></strong>   </display:caption>
	       <display:caption media=" excel rtf pdf"><%=whead%></display:caption>
		   
           <display:column property="code" title="CODE"/> 
           <display:column property="name" title="NAME"/> 
		   <display:column property="pack" title="PACK" headerClass="r"  class="r"/> 
		   <display:column property="branch_code" title="BRANCH CODE"/> 
           <display:column property="branch_name" title="BRANCH NAME"/> 
           <display:column property="grossqty" title="GROSS QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="grossval" title="GROSS VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="salqty" title="SALABLE QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="salval" title="SALABLE VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="expqty" title="EXP. QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="expval" title="EXP. VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="brkqty" title="BRK. QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="brkval" title="BRK. VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="pdqty" title="PD. QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="pdval" title="PD. VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="insqty" title="INST. QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="insval" title="INST. VAL" format="{0,number,0.00}" headerClass="r" class="r"/> 
           <display:column property="netqty" title="NET QTY"  format="{0,number,0}" headerClass="r" class="r"/> 
           <display:column property="netval" title="NET VAL"  format="{0,number,0.00}" headerClass="r" class="r"/> 
		   
      </display:table> 
  
      <br><br> 
    </body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
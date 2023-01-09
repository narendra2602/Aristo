<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.Repo13FormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<%
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<style type="text/css">
tr.verybad td 
{
color: #000000;
background-color:rgb(255, 255, 0);
}
</style>

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
		   String cyear="";
		   String lyear="";
		   String vcyear="";
		   String vlyear="";
		   String mcyear="";
		   String mlyear="";
		   String gcyear="";
		   String glyear="";
           Repo13FormBean rf = new Repo13FormBean();  
           List rl=(List) request.getAttribute("rlist"); 
           Iterator it =  rl.iterator();
           	if(it.hasNext())
             	{
             	 rf=(Repo13FormBean)it.next();    
             	 whead=rf.getNm3();
             	 cyear="Rank CY "+rf.getCyear();
             	 lyear="Rank LY "+rf.getLyear();
             	 vcyear="VAL IN LACS CY "+rf.getCyear();
             	 vlyear="VAL IN LACS LY "+rf.getLyear();
             	 mcyear="MS% CY "+rf.getCyear();
             	 mlyear="MS% LY "+rf.getLyear();
             	 gcyear="GTH% CY "+rf.getCyear();
             	 glyear="GTH% LY "+rf.getLyear();
                }  
				 
  	    // String lupdate = rf.getLupdate();	  
		 
         org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
         pageContext.setAttribute("totals", totals);
         %>  

 <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((Repo13FormBean)getCurrentRowObject()).getColor();
			  if (data==2)
			     return "verybad";
			  else
			    if (data==1) 
 				     return "good";
				  else 
    				       return "abc";
				  	 
            }
        });
  </jsp:scriptlet>


    <br/>
<div id="main">		    
 <display:table id="data"  class="simple nocol" name="requestScope.rlist" requestURI="./NWListForm13.do?actionRequested=NWListForm13" pagesize="25"  export="true"  decorator="dyndecorator">

       <display:caption media="html"> <div style="font-size:18px" align="left"><strong><br/><%=whead%></strong></div> 
	   <table  border-"1" ><tr  ><th >Mat Sept-2021</th><th >Mat Sept-2021</th><th >Mat Sept-2021</th><th >Mat Sept-2021</th><th >Mat Sept-2021</th><th >Mat Sept-2021</th><th >Mat Sept-2021</th></tr></table>
	   </display:caption>
        <display:caption media="excel"><%=whead%></display:caption>
		
		<display:column property="rl" title="<%=lyear%>" headerClass="r" class="r" /> 
		<display:column property="rc" title="<%=cyear%>" headerClass="r" class="r" /> 
		<display:column property="product" title="Product"  /> 
		<display:column property="company" title="Company" />
		<display:column property="launch" title="Launch" headerClass="r" class="c" /> 

		<display:column property="vall" title="<%=vlyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="valc" title="<%=vcyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msl" title="<%=mlyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msc" title="<%=mcyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthl" title="<%=glyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthc" title="<%=gcyear%>" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="valql" title="VAL In LACS Q1" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="valq2" title="VAL In LACS Q2" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="valq3" title="VAL In LACS Q3" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="valq4" title="VAL In LACS Q4" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msq1" title="MS% Q1" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msq2" title="MS% Q2" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msq3" title="MS% Q3" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="msq4" title="MS% Q4" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthql" title="GTH% Q1" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthq2" title="GTH% Q2" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthq3" title="GTH% Q3" format="{0,number,0.00}" headerClass="r" class="r"/> 
		<display:column property="gthq4" title="GTH% Q4" format="{0,number,0.00}" headerClass="r" class="r"/> 

	</display:table>
</div>	
<br>
<br> 
</body>
<jsp:include page="<%=foot%>"/> 
</html>
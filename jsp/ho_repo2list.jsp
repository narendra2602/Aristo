<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>
<%@ page import="com.aristo.valueobject.HORepo2FormBean" %>
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>



<html>

<% 
	String toop  = (String) session.getAttribute("top");
	String foot = (String) session.getAttribute("footer");
	String css1 = (String) session.getAttribute("css");
	%>
<jsp:include page="<%=toop%>" flush="true" />

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
   <%
              String a[] = new String[50];
              String whead=" ";
              int x=0; 
                
             	HORepo2FormBean rf = new HORepo2FormBean();  
             //	List rl= new ArrayList();
             	List rl=(List) request.getAttribute("rlist"); 
             	Iterator it =  rl.iterator();
             	if(it.hasNext())
             	{
             	 rf=(HORepo2FormBean)it.next();    
             	  x=rf.getBr();
             	  whead=rf.getHead1();
             	  
             	
             	   for(int j=0;j<x;j++)
             	     {
                          a[j]=rf.getVhead(j);  
                     } 
                 }   
              
              %>

   <br />
   <display:table id="data" name="requestScope.rlist" requestURI="./HOListRepo2.do?actionRequested=HOListRepo2"
      pagesize="25" export="true">

      <display:caption media="html"><br />
         <table cellspacing="0" cellpadding="0" class="status" style="height:20px;">

            <tr>
               <td style="text-align: center;"> <%=whead%> </td>
            </tr>
         </table>
      </display:caption>


      <display:caption media=" excel pdf rtf"><%=whead%></display:caption>

      <display:column property="gcode" title="CODE" />
      <display:column property="gname" title="GROUP NAME" />

      <%
         for(int j=0;j<x;j++)
          {
            %>
      <display:column property='<%="val1["+j+"]"%>' title="<%=a[j]%>" format="{0,number,0.00}" headerClass="r"
         class="r" />
      <% 
	      }
        %>


   </display:table>
   <br><br>
</body>
<jsp:include page="<%=foot%>" />

</html>
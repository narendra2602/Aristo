<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
String whead = (String) request.getAttribute("head");
%>

<jsp:include page="<%=toop%>"/> 

<html> 

<head>
  <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
		
      <br/><br>
	  
  <display:table id="data" name="sessionScope.Repo1Form.rlist" requestURI="./ListCheck.do?actionRequested=ListCheck" pagesize="24"  export="true"  >
	  <display:caption media="html"></br> <%=whead%> </display:caption>
	  <display:caption media=" excel rtf pdf"><%=whead%></display:caption>                       
	  
            <display:column property="br_name" title="BRANCH" />
            <display:column property="pool" title="REP" />			
            <display:column property="oct" title="OCT" />
            <display:column property="nov" title="NOV"/>
            <display:column property="dec" title="DEC"/>
            <display:column property="jan" title="JAN"/>
            <display:column property="feb" title="FEB"/> 
            <display:column property="mar" title="MAR"/>
            <display:column property="apr" title="APR"/>
            <display:column property="may" title="MAY"/>
            <display:column property="jun" title="JUN"/>
            <display:column property="jul" title="JUL"/>
            <display:column property="aug" title="AUG"/>
            <display:column property="sep" title="SEP"/>
        </display:table>
		
      <br><br>
   </body>
 <jsp:include page="<%=foot%>"/> 
</html>
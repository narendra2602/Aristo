<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="com.aristo.valueobject.SampleRepo2FormBean" %>
<%@ page import="org.displaytag.sample.*" %>

<html>

<head>
  <% 
String top  = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>

  <link href="css/<%=css%>" rel="stylesheet" type="text/css" />
  <jsp:include page="<%=top%>" />
</head>

<body>
  <% 
response.setHeader("Cache-Control","no-cache");  
response.setHeader("Cache-Control","no-store");  
String whead = (String) request.getAttribute("whead");
%>


  <jsp:scriptlet>
    request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
    {
    public String addRowClass()
    {
    int data = ((SampleRepo2FormBean)getCurrentRowObject()).getColour();

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



  
  <div class="div1">
    <display:table id="data" name="requestScope.SampleRepo2Form.rlist"
      requestURI="./SampleListRepo4.do?actionRequested=SampleListRepo4" pagesize="20" export="true"
      decorator="dyndecorator">

      <display:caption media="html"> <strong><br /><%=whead%></strong> </display:caption>
      <display:caption media="excel rtf pdf"><%=whead%></display:caption>

      <display:column property="brname" title="BRANCH NAME" />
      <display:column property="hq_name" title="HQ" />
      <display:column property="pname" title="PRODUCT NAME" />
      <display:column property="pack" title="PACK" />
      <display:column property="inv_no" title="INV NO." />
      <display:column property="inv_dt" title="DATE" format="{0,date,dd/MM/yyyy}" />
      <display:column property="name" title="PARTY NAME" />
      <display:column property="city" title="CITY" />
      <display:column property="iqty" title="ISSUE" headerClass="r" class="r" />
      <display:column property="rate" title="RATE"  format="{0,number,0.00}" headerClass="r" class="r" />
      <display:column property="tvalue" title="VALUE" format="{0,number,0.00}" headerClass="r" class="r" />
    </display:table>
  </div>
</body>
<br />
<jsp:include page="<%=footer%>" />

</html>
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
  <script>
    $(window).bind("pageshow", function (event) {
      if (event.originalEvent.persisted) {
        window.location.reload();
      }
    });
  </script>



</head>

<body>
  <% 
response.setHeader("Cache-Control","no-cache");  
response.setHeader("Cache-Control","no-store");  
String whead = (String) session.getAttribute("whead");
 
 
 
%>


  <jsp:scriptlet>
    request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
    {
    public String addRowClass()
    {
    int data = ((SampleRepo2FormBean)getCurrentRowObject()).getColour();

    if (data==1)
    return ((SampleRepo2FormBean)getCurrentRowObject()).getRqty() > 0 ? "money" : "mon";

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
      requestURI="./SampleListRepo2.do?actionRequested=SampleListRepo2" export="true" decorator="dyndecorator">
      </br>
      <display:caption media="html"><br>
        <table cellspacing="0" cellpadding="0" class="status" style="height:20px;">

          <tr>
            <td style="text-align: center;"> <%=whead%> </td>
          </tr>
        </table>
      </display:caption>

      <display:caption media="excel rtf pdf"><%=whead%> </display:caption>
      <display:column property="name" title="BRANCH" href="SampleListRepo2a.do?actionRequested=SampleListRepo2a"
        paramId="depo_code" paramProperty="depo_code" media="html" />
      <display:column  property="name" title="BRANCH" media="excel" />
      <display:column  property="name" title="BRANCH" media="csv" />
      <display:column  property="name" title="BRANCH" media="pdf" />
      <display:column  property="name" title="BRANCH" media="rtf" />

      <display:column property="oqty" title="OPENING QTY" class="r" />
      <display:column property="rqty" title="RECEIPTS QTY" class="r" />
      <display:column property="iqty" title="ISSUE QTY" headerClass="r" class="r" />
      <display:column property="tqty" title="TRANSFER QTY" class="r" />
      <display:column property="cqty" title="CLOSING QTY" class="r" />
    </display:table>
  </div>
</body>
<br />
<jsp:include page="<%=footer%>" />

</html>
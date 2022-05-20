<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>

  <head>
    <% 
String top = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>

    <link href="css/<%=css%>" rel="stylesheet" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="./js/selectgroup.js"></script>

    <jsp:include page="<%=top%>" />

    <script type="text/javascript">

      $(document).ready(function () {
        var d = new Date();
        var month = d.getMonth() + 1;
        $("#month").val(month);
      });


      function selectDepo() {
        var url = 'SampleOptRepo5aAjax.do?actionRequested=SampleOptRepo5aAjax';
        var div = document.forms[0].dcode.value;
        var depo = document.forms[0].code.value;
        var year = document.forms[0].myear.value;
        var str = "1&div=" + div + "&depo=" + depo + "&myear=" + year;

        showProducts(str, url);

      }

    </script>

  </head>

  <body>



    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <html:form action="SampleListRepo5.do?actionRequested=SampleListRepo5">
      <center>
        <table align="center" cellpadding="0" cellspacing="0" class="usertable">
          <tr>
            <th colspan="2">FS/Itemwise Issue</th>
          </tr>



          <tr>
            <td style="padding-left: 75px; text-align: left;">Division:</td>
            <td style="text-align: left;">
              <html:select property="dcode" onchange="selectDepo()">
                <html:option value="A">MF</html:option>
                <html:option value="T">TF</html:option>
                <html:option value="G">Genetica</html:option>
                <html:option value="M">MF2</html:option>
                <html:option value="B">MF3</html:option>
              </html:select>
            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left; width: 40%;">Category</td>
            <td style="text-align: left;">
              <html:select property="ccode" styleId="mySelect">
                <html:optionsCollection name="SampleRepo2Form" property="catlist" label="catname" value="cat_code" />
              </html:select>
            </td>
          </tr>

          <tr>
            <td style="padding-left: 75px;text-align: left;">Branch</td>
            <td style="text-align: left;">
              <html:select property="code" styleId="mySelect" onchange="selectDepo()">
                <html:optionsCollection name="SampleRepo2Form" property="divlist" label="dname" value="dcode" />
              </html:select>
            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left;">Mkt Year</td>
            <td style="text-align: left;">
              <html:select property="myear">
                <html:optionsCollection name="SampleRepo2Form" property="ylist" label="yname" value="yval" />
              </html:select>
            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left;">FS</td>
            <td style="text-align: left;">
              <div id="results">
                <html:select property="pcode" styleId="mySelect">
                  <html:optionsCollection name="SampleRepo2Form" property="plist" label="pname" value="pval" />
                </html:select>
              </div>
            </td>
          </tr>
          <!--
          <tr>
            <td style="padding-left: 75px;text-align: left;">Select Month:</td>
            <td style="text-align: left;">
              <html:select property="monno" styleId="month">
                <html:option value="10">Oct</html:option>
                <html:option value="11">Nov</html:option>
                <html:option value="12">Dec</html:option>
                <html:option value="1">Jan</html:option>
                <html:option value="2">Feb</html:option>
                <html:option value="3">Mar</html:option>
                <html:option value="4">Apr</html:option>
                <html:option value="5">May</html:option>
                <html:option value="6">Jun</html:option>
                <html:option value="7">Jul</html:option>
                <html:option value="8">Aug</html:option>
                <html:option value="9">Sep</html:option>
              </html:select>
            </td>
          </tr>
-->
          <tr>
            <td style="padding-left: 75px;text-align: left;">Select Month:</td>
            <td style="text-align: left;">
              <html:select property="monno" styleId="month">
                <html:option value="10">Oct</html:option>
                <html:option value="11">Nov</html:option>
                <html:option value="12">Dec</html:option>
                <html:option value="1">Jan</html:option>
                <html:option value="2">Feb</html:option>
                <html:option value="3">Mar</html:option>
                <html:option value="4">Apr</html:option>
                <html:option value="5">May</html:option>
                <html:option value="6">Jun</html:option>
                <html:option value="7">Jul</html:option>
                <html:option value="8">Aug</html:option>
                <html:option value="9">Sep</html:option>
              </html:select>
              &nbsp; &nbsp; &nbsp;
              <html:radio property="choice" value="1" onclick="makeEnable()" />
              Monthly &nbsp; &nbsp; &nbsp;
              <html:radio property="choice" value="2" onclick="makeDisable()" />
              Yearly

            </td>
          </tr>



          <tr>
            <td colspan="2">
              <div align="center">
                <html:submit styleClass="button" value="Submit" />
                &nbsp;
                <html:reset styleClass="button" value="Cancel" />
              </div>
            </td>
          </tr>

        </table>
      </center>

    </html:form>
  </body>
  <jsp:include page="<%=footer%>" />
</html:html>
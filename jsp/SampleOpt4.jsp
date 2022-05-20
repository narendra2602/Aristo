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



      

      
    </script>

  </head>

  <body>

 

    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <html:form action="SampleListRepo4.do?actionRequested=SampleListRepo4" >
      <center>
        <table align="center" cellpadding="0" cellspacing="0" class="usertable">
          <tr>
            <th colspan="2">Itemwise Issue</th>
          </tr>



          <tr>

            <td style="padding-left: 75px; text-align: left;">Division:</td>
            <td style="text-align: left;">
 
              <html:select property="dcode" >
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
              <html:select property="ccode" styleId="mySelect" >
                <html:optionsCollection name="SampleRepo2Form" property="catlist" label="catname" value="cat_code" />
              </html:select>
            </td>
          </tr>

          <tr>
            <td style="padding-left: 75px;text-align: left;">Branch</td>
            <td style="text-align: left;">
              <div id="results">
                <html:select property="code" styleId="mySelect">
                  <html:optionsCollection name="SampleRepo2Form" property="divlist" label="dname" value="dcode" />
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left;">Mkt Year</td>
            <td style="text-align: left;">
              <html:select property="myear" >
                <html:optionsCollection name="SampleRepo2Form" property="ylist" label="yname" value="yval" />
              </html:select>
            </td>
          </tr>
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
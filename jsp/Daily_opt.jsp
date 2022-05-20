<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<html:html>

  <head>
    <% 
      String top = (String) session.getAttribute("top");
      String footer = (String) session.getAttribute("footer");
      String css = (String) session.getAttribute("css");

      System.out.println("balue of top is "+top);
    %>
    <jsp:include page="<%=top%>" />

    <link href="css/<%=css%>" rel="stylesheet" type="text/css" />

    <script src="js/calendar.js" type="text/javascript" language="javascript"></script>
    <script src="./js/selectgroup.js"></script>

    <script type="text/javascript">

      window.onload = function () {
        init();
      }

      function validateDate() {
        var SDate = document.forms[0].edate.value;
        var startDate = new Date(SDate);
        var validformat = /^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity

        if (SDate == '') {
          alert("Please enter Date");
          document.forms[0].edate.focus();
          return false;
        }

        if (!validformat.test(SDate)) {
          alert("Invalid Date Format. Please correct and submit again.")
          return false;
        }

        var mon = document.forms[0].mnth.value;
        var ndate = document.forms[0].ndate.value;
        if (mon != parseInt(ndate.substring(3, 5))) {
          alert("Billing Date does not match Billing Month ");
          return false;
        }

      }



      function init() {

        if (!document.getElementById) return false;
        var f = document.getElementById('auto_off');

        f.setAttribute("autocomplete", "off");


      }

      function selectMon(mon) {


        var d = new Date();
        var month = d.getMonth() + 1;
        var dd = d.getDate();
        var mm = d.getMonth() + 1;
        var yyyy = d.getFullYear();

        if (mon != month) {
          d.setMonth(mon);
          d.setDate(0);
          dd = d.getDate();
          mm = d.getMonth() + 1;
          yyyy = d.getFullYear();
          if (dd < 10) {
            dd = '0' + dd;
          }

          if (mm < 10) {
            mm = '0' + mm;
          }
         /* if(mm>9)
          {
            yyyy=yyyy-1;
          }*/
          d = dd + '/' + mm + '/' + yyyy;
          document.forms[0].ndate.value = d;

        }
        else {
          if (dd < 10) {
            dd = '0' + dd;
          }

          if (mm < 10) {
            mm = '0' + mm;
          }

          /*if(mm>9)
          {
            yyyy=yyyy-1;
          }*/

          d = dd + '/' + mm + '/' + yyyy;
          document.forms[0].ndate.value = d;
        }



      }

      $(document).ready(function () {
        var d = new Date();
        var month = d.getMonth() + 1;
        $("#month").val(month);

      });


      function selectDiv() {

        //alert("in select div");  
        var url = 'DailyForwardAjax.do?actionRequested=DailyForwardAjax';
        var str = "1";
        //showCustomer(str, url);

      }

    </script>

  </head>

  <body class="entry-main-section">
    <br><br><br /> <br><br><br /><br>
    <div id="calendarDiv"> </div>

    <html:form styleId="auto_off" action="DailyList.do?actionRequested=DailyList" onsubmit="return validateDate()">
      <div class="usertable-div">
        <table class="usertable1">
          <tr>
            <th colspan="2">DAILY BILL </th>
          </tr>
          <tr>
            <td colspan="2">
              <html:radio property="opt" value="1" onclick="visibleDiv()" />HO Report &nbsp;&nbsp;&nbsp;
              <html:radio property="opt" value="2" onclick="selectDiv()" />Branch Report &nbsp;&nbsp;&nbsp;
              <html:radio property="opt" value="3" />Updation Status
            </td>
          </tr>

          <tr>

            <td style="width:50%; text-align: right">Select Division</td>
            <td style="text-align: left; ">
              <div id="abc">
                <html:select property="type" style="width: 120px;">
                  <html:optionsCollection name="LoginForm" property="divlist" label="dname" value="d_type" />
                </html:select>
              </div>
            </td>
          </tr>

          <tr>

            <td style="text-align: right;  ">Billing Month</td>
            <td style="text-align: left;">
              <html:select property="mnth" styleId="month" onchange="selectMon(this.value)">
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
            <td style="text-align: right;  ">
              Billing Date:
            </td>
            <td style="text-align: left;">
              <html:text property="edate" styleClass="calendarSelectDate" style="width: 120px;" styleId="ndate"
                readonly="true" />
            </td>
          </tr>

          <tr>
            <td colspan="2" style="text-align:center">

              <html:submit styleClass="button" value="Submit" />
              <html:reset styleClass="button" value="Cancel" />

            </td>
          </tr>
        </table>
      </div>
    </html:form>
  </body>
  <jsp:include page="<%=footer%>" />
</html:html>
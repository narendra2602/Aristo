<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<%@ page import="com.aristo.valueobject.LoginFormBean" %>

<html:html>

  <% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
String s = (String) request.getParameter("sucess");
System.out.println("Value of s is "+s);
   if(s!=null && s.equals("1"))
   {
   out.write("<script>");
   out.write("alert(\"Record Saved Successfully! " + "\")");
   out.write("</script>");
   //request.setParameter("sucess",0);
   }
   else if(s!=null && !s.equals("1"))
   {
    out.write("<script>");
      out.write("alert(\"Record Not Saved." + "\")");
      out.write("</script>");

   }
  
%>

  <jsp:include page="<%=toop%>" />
  <jsp:include page="head-scripts" />

  <head>
    <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    <link href="css/new/styles.css" rel="stylesheet" type="text/css" />
    <script src="js/calendar.js" type="text/javascript" language="javascript"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="./js/selectgroup.js"></script>

    <script type="text/javascript">

      function checksale() {
          var v1 = document.forms[0].trd_sale.value;
          if (v1 == 0) {
            alert("Net Sale Cannot be 0 ");
            return false;
          }
          var mon = document.forms[0].mnth.value;
          var ndate = document.forms[0].ndate.value;
          if (mon!=parseInt(ndate.substring(3, 5)))
          {
            alert("Billing Date does not match Billing Month ");
            return false;
          }

          return true;
        }

        function stopRKey(evt) {
          var evt = (evt) ? evt : ((event) ? event : null);
          var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
          if ((evt.keyCode == 13) && (node.type == "text")) { return false; }
        } 
          document.onkeypress = stopRKey;

      function validateDate() {
        var SDate = document.forms[0].sdate.value;
        var EDate = document.forms[0].edate.value;
        var alertReason1 = 'End Date must be greater than or equal to  Start Date.'
        var alertReason2 = 'End Date can not be less than Current Date.';
        var endDate = new Date(EDate);
        var startDate = new Date(SDate);
        var validformat = /^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity

        if (SDate == '') {
          alert("Please enter Start Date");
          return false;
        }
        else if (EDate == '') {
          alert("Please enter End Date");
          return false;
        }
        if ((!validformat.test(SDate)) || (!validformat.test(EDate))) {
          alert("Invalid Date Format. Please correct and submit again.")
          return false;
        }
        if (SDate != '' && EDate != '' && startDate > endDate) {
          alert(alertReason1);
          document.forms[0].edate.value = '';
          document.forms[0].edate.focus();
          return false;
        }
      }

      function selectYear(year) {
        var url = 'EntryForwardAjax.do?actionRequested=EntryForwardAjax';
        var divnm = document.forms[0].access_t.value;
        var mon = document.forms[0].mnth.value;
        var edate = document.forms[0].edate.value;

        var div = 1;
        if (divnm == "A")
          div = 1;
        if (divnm == "T")
          div = 2;
        if (divnm == "G")
          div = 3;
        if (divnm == "M")
          div = 10;
        if (divnm == "B")
          div = 20;
        if (divnm == "F")
          div = 30;


        var str = "1&year=" + year + "&div=" + div + "&mon=" + mon+ "&edate=" + edate;

        showCustomer(str, url);

      }

      function selectDiv(divnm) {
        var url = 'EntryForwardAjax.do?actionRequested=EntryForwardAjax';
        var year = document.forms[0].eyear.value;
        var mon = document.forms[0].mnth.value;
        var edate = document.forms[0].edate.value;

        var div = 1;
        if (divnm == "A")
          div = 1;
        if (divnm == "T")
          div = 2;
        if (divnm == "G")
          div = 3;
        if (divnm == "M")
          div = 10;
        if (divnm == "B")
          div = 20;
        if (divnm == "F")
          div = 30;

        var str = "1&year=" + year + "&div=" + div + "&mon=" + mon+ "&edate=" + edate;

        showCustomer(str, url);

      }

      function selectMon(mon) {
        var url = 'EntryForwardAjax.do?actionRequested=EntryForwardAjax';
        var year = document.forms[0].eyear.value;
        var divnm = document.forms[0].access_t.value;
        

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
          d = dd + '/' + mm + '/' + yyyy;
          document.forms[0].ndate.value=d;

        }
        else {
          if (dd < 10) {
            dd = '0' + dd;
          }

          if (mm < 10) {
            mm = '0' + mm;
          }
          d = dd + '/' + mm + '/' + yyyy;
          document.forms[0].ndate.value=d;
        }


        var edate = document.forms[0].edate.value;
        var div = 1;
        if (divnm == "A")
          div = 1;
        if (divnm == "T")
          div = 2;
        if (divnm == "G")
          div = 3;
        if (divnm == "M")
          div = 10;
        if (divnm == "B")
          div = 20;
        if (divnm == "F")
          div = 30;

        var str = "1&year=" + year + "&div=" + div + "&mon=" + mon + "&edate=" + edate;

        showCustomer(str, url);


      }


      $(document).ready(function () {
        var d = new Date();
        var month = d.getMonth() + 1;
        $("#month").val(month);
        selectDiv("A");
      });


    </script>


  </head>

  <body>
    <div id="calendarDiv"> </div>

    <div>

      <html:form action="AddEntry.do?actionRequested=AddEntry" styleId="myForm" method="Post"
        onsubmit="return checksale();">


        <br><br><br>

        <table class="daily-billing-table" style="position: relative;">
          <th colspan="4">Daily Billing &amp; Collection Entry</th>

          <tr>
            <td>Select Division
              <html:select property="access_t" onchange="selectDiv(this.value)">
                <html:option value="A">MF</html:option>
                <html:option value="T">TF</html:option>
                <html:option value="G">Genetica</html:option>
                <html:option value="M">MF2</html:option>
                <html:option value="B">MF3</html:option>
                <html:option value="F">MF4</html:option>
              </html:select>

            </td>


            <td>
              Mkt Year
              <html:select property="eyear" onchange="selectYear(this.value)">
                <html:optionsCollection name="LoginForm" property="ylist" label="yname" value="yval" />
              </html:select>
            </td>

            <td>
              Billing Month
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


            <td>Billing Date
               
                <html:text property="edate" styleClass="calendarSelectDate" style="text-align:center" styleId="ndate" readonly="true" />
              

            </td>
          </tr>
          <tr>
            <td colspan="4" style="text-align: center;">
              Name
              <html:select property="branch_name">
                <html:optionsCollection name="LoginForm" property="brList" label="dname" value="dname" />
              </html:select>
            </td>


          </tr>


          <tr>
            <td style="text-align: center;  border-top-style: solid;   border-color: grey; border-top-width: 1px;"
              colspan="2"><u>Billing</u></td>
            <td style="text-align: center;  border-top-style: solid;   border-color: grey; border-top-width: 1px;"
              colspan="2"><u>Collection</u></td>
          </tr>


          <tr>
            <td colspan="4" id="abc"></td>
          </tr>


          <tr>
            <td colspan="4"
              style="text-align: center;  border-top-style: solid;   border-color: grey; border-top-width: 1px;">
              <html:submit value="Submit" styleClass="button" />
              <html:reset value="Reset" styleClass="button" />
            </td>
          </tr>

        </table>
    </div>
    <br><br><br><br>
    </html:form>
  </body>
  <jsp:include page="<%=foot%>" />
</html:html>
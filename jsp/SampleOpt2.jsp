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
    <link href="css/search.css" rel="stylesheet" type="text/css" />


    <script src="./js/selectgroup.js"></script>

    <jsp:include page="top.jsp" />
    <!-- Script -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/js/select2.min.js"></script>

    <!-- CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet" />
    <link href="css/select2-bootstrap.css" rel="stylesheet" type="text/css" />


    <style>
      .select2-results__options {
        color: #000000;
        background-color: white;
        font-size: 12px;
      }
    </style>

    <script type="text/javascript">

      var str = null;
      var url = null;
      $(document).ready(function () {
        var d = new Date();
        var month = d.getMonth() + 1;
        $("#month").val(month);
        // selectDiv("A");
        // Initialize select2

        selectDiv();
        //     selectProduct();


        //  alert("before select user");
        // $("#results").select2();

        //  $("#mySelect").select2();

        //$('#mySelect2').select2({
        //dropdownParent: $('#abc')

        //});





      });






      function selectDiv() {
        url = 'SampleOptRepo2Ajax.do?actionRequested=SampleOptRepo2Ajax';
        var divnm = document.forms[0].dcode.value;
        var cat = document.forms[0].ccode.value;
        var grp = 0;
        // var prd = document.forms[0].code.value;
        try {
          grp = document.forms[0].gcode.value;
        } catch (error) {
          grp = 0;
        }


        var div = 5;
        if (divnm == "A")
          div = 5;
        if (divnm == "T")
          div = 6;
        if (divnm == "G")
          div = 7;
        if (divnm == "M")
          div = 11;
        if (divnm == "B")
          div = 21;

        str = "1&cat=" + cat + "&div=" + div + "&grp=" + grp;
        //alert("str is "+str+" url "+url);

        // alert(url + "&q=89DIVISION" + str);
        //alert("value of grp in selectdiv i s " + grp);
        showCustomer(str, url);
        selectGroup(0);



      }

      function selectGroup(grpcode) {
        var url = 'SampleOptRepo2aAjax.do?actionRequested=SampleOptRepo2aAjax';
        var divnm = document.forms[0].dcode.value;
        var cat = document.forms[0].ccode.value;
        var grp = document.forms[0].gcode.value;
        //var grp = grpcode;



        var div = 5;
        if (divnm == "A")
          div = 5;
        if (divnm == "T")
          div = 6;
        if (divnm == "G")
          div = 7;
        if (divnm == "M")
          div = 11;
        if (divnm == "B")
          div = 21;

        var str = "1&cat=" + cat + "&div=" + div + "&grp=" + grp;

        // alert(url + "&q=79GROUP" + str);
        // if (grp != 0)



        var gtext = $("#mygroup").children("option:selected").text();

        //showProducts(str, url);
        $('#product').val('C'); // Select the option with a value of '1'
        $('#product').trigger('change'); // Notify any JS components that the value changed
//        $('#product').val(null).trigger('change');

        $('#product').select2({
          theme: "classic",
         
          placeholder: "Search  a product",

          //templateResult: function (d) { return $(d.text); },
          // templateSelection: function (d) { return $(d.text); },
          //data:o,


          ajax: {
            type: 'post',
            url: 'SampleOptRepo2aAjax.do?actionRequested=SampleOptRepo2aAjax&z=' + str,
            allowClear: true,
            dataType: 'json',
            delay: 250,
              
            data: function (params) {
              var query = {
                q: params.term,     //this is Sending Input key by search
                userInput: $("#mygroup").children("option:selected").val(),  //this is Sending Input key by userInput
                userInput2: $("#mygroup").children("option:selected").text()  //this is Sending Input key by userInput
              }
              console.log(query);
              return query;
            },
            processResults: function (data) { //Return Response
              // console.log(data);
              var results = $.map(data, function (obj) {

                return {
                  id: obj.pval,
                  text: obj.pname
                };
              });
              //  console.log(results);   //Printing Response
              return {
                results: results,
              };
            },





            cache: true
          },

          escapeMarkup: function (m) {
            return m;
          }, // we do not want to escape markup since we are displaying html in results		
          templateResult: function(data){ return data.pname || data.text; },
          templateSelection: function(data){ return data.pname || data.text; }	
        });






      }

      function selectProduct() {
        var url = 'SampleOptRepo2aAjax.do?actionRequested=SampleOptRepo2aAjax';
        var divnm = document.forms[0].dcode.value;
        var cat = document.forms[0].ccode.value;
        var grp = document.forms[0].gcode.value;
        //var grp = grpcode;

        var div = 5;
        if (divnm == "A")
          div = 5;
        if (divnm == "T")
          div = 6;
        if (divnm == "G")
          div = 7;
        if (divnm == "M")
          div = 11;
        if (divnm == "B")
          div = 21;

        var str = "1&cat=" + cat + "&div=" + div + "&grp=" + grp;
        //  alert(str + " in Product url " + url);
        alert(url + "&q=69" + str);
        if (grp != 0)
          showProducts(str, url);



      }



    </script>

  </head>

  <body>



    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <html:form action="SampleListRepo2.do?actionRequested=SampleListRepo2" onsubmit="return validateDate()">

      <center>
        <table align="center" cellpadding="0" cellspacing="0" class="usertable" id="mytable">
          <tr>
            <th colspan="2">Stock Ledger</th>
          </tr>



          <tr>

            <td style="padding-left: 75px; text-align: left;">Division:</td>
            <td style="text-align: left;">
              <html:select property="dcode" styleId="mydiv" onchange="selectDiv()">
                <html:optionsCollection name="SampleRepo2Form" property="divlist" label="dname" value="d_type" />
              </html:select>


            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left; width: 40%;">Category</td>
            <td style="text-align: left;">

              <html:select property="ccode" styleId="mySelect" onchange="selectDiv()" style='width: 300px;'>
                <html:optionsCollection name="SampleRepo2Form" property="catlist" label="catname" value="cat_code" />
              </html:select>

            </td>
          </tr>

          <tr>
            <td style="padding-left: 75px;text-align: left;">Group</td>
            <td style="text-align: left;">
              <div id="abc">
                <html:select property="gcode" styleId="mygroup" onchange="selectGroup(this.value)">
                  <html:optionsCollection name="SampleRepo2Form" property="grplist" label="gpname" value="gpcode" />
                </html:select>
              </div>
            </td>
          </tr>



          <tr>
            <td style="padding-left: 75px;text-align: left;">Product</td>
            <td style="text-align: left;">


              <div id="results">
                <select id="product" name="myproduct" style="width:100%">
                  <option value="mySelectedValue">Search a product</option>
                </select>
              </div>

            </td>
          </tr>
          <tr>
            <td style="padding-left: 75px;text-align: left;">Mkt Year</td>
            <td style="text-align: left;">
              <html:select property="myear" onchange="selectYear(this.value)">
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
              &nbsp; &nbsp; &nbsp;
              <html:radio property="choice" value="1" />
              Monthly &nbsp; &nbsp; &nbsp;
              <html:radio property="choice" value="2" />
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
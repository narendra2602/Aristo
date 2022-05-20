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
      }



      function init() {

        if (!document.getElementById) return false;
        var f = document.getElementById('auto_off');

        f.setAttribute("autocomplete", "off");


      }



      /**
      * Move selected items between lists.
      * <p>
      * @param sourceId ID of source list
      * @param destId ID of destination list
      * @param all true iff moving all
      */
      function doMove(sourceId, destId, all) {
        // Move the items between the lists.
        var sourceElem = document.getElementById(sourceId);
        var destElem = document.getElementById(destId);
        for (var i = 0; (i < sourceElem.length);) {
          if (sourceElem.options[i].selected || all) {
            var newOption = document.createElement("OPTION");
            newOption.text = sourceElem.options[i].text;
            newOption.value = sourceElem.options[i].value;
            destElem.options[destElem.length] = newOption;
            sourceElem.remove(i);
          }
          else
            i++;
        }

        // Update the button states.
        doUpdate(false, false);

        // Store the chosen items in a hidden field.
        var chosenItem = document.getElementById("chosenItem");
        var chosenList = document.getElementById("chosen");
        chosenItem.value = "";
        for (var i = 0; (i < chosenList.length); i++) {
        chosenItem.value += chosenList.options[i].value + '|';
        }
      }





      /**
      * Update the button states based on whether
      * lists have contents and selected items.
      * Deselect list items if requested to ensure
      * at most one list contains selections.
      * <p>
      * @param offAvailable deselect available list
      * @param offChosen deselect chosen list
      */
      function doUpdate(offAvailable, offChosen) {
        // Get the lists and deselect if requested.
        var availableList = document.getElementById("available");
        var chosenList = document.getElementById("chosen");
        if (offAvailable) availableList.selectedIndex = -1;
        if (offChosen) chosenList.selectedIndex = -1;
        // Update the button states.
        document.getElementById("addAll").disabled =
          (availableList.length == 0);
        document.getElementById("removeAll").disabled =
          (chosenList.length == 0);
        document.getElementById("add").disabled =
          (availableList.selectedIndex < 0);
        document.getElementById("remove").disabled =
          (chosenList.selectedIndex < 0);
      }






    </script>

  </head>

  <body class="entry-main-section">

    <div id="calendarDiv"> </div>

    <html:form styleId="auto_off" action="DailyList.do?actionRequested=DailyList" onsubmit="return validateDate()">
      <div class="usertable-div" style=" margin-left: -388px; margin-top: -161px;">
        <table class="usertable1">
          <tr>
            <th colspan="3">DAILY BILL </th>
          </tr>
          <tr>
            <td colspan="3" style="text-align: center;">
              <html:radio property="opt" value="1" />HO Report  &nbsp;&nbsp;&nbsp;&nbsp;
              <html:radio property="opt" value="2" />Branch Report  &nbsp;&nbsp;&nbsp;&nbsp;
              <html:radio property="opt" value="3" />Updation Status 
            </td>
          </tr>
          <tr>
            <td>Select Division 
               <br/>
              <html:select property="type" multiple="true" size="7" style="width:300px" styleId="available" onchange="doUpdate( false,true );">
                <html:optionsCollection name="LoginForm" property="divlist" label="dname" value="d_type" />
              </html:select>
            </td>
            <td>
              <br/>
                 <input type="button" style="width:50px;" id="add" onClick="
                  doMove( 'available', 'chosen', false );" value=">>" />
                
                  <br />
                  <br />
                 <input type="button" style="width:50px;" id="addAll" onclick=
                  "doMove( 'available', 'chosen', true );" value=">>>" />
                  <br />
                  <br />
      
                 <input type="button" style="width:50px;" id="remove" onclick=
                  "doMove( 'chosen', 'available', false );" value="<<" />
                  
                  <br />
                  <br />
                 <input type="button" style="width:50px;" id="removeAll" onclick=
                "doMove( 'chosen', 'available', true );" value="<<<" />
    
                 <html:hidden styleId="chosenItem" property="chosenItem" />							 
             </td>  
             <td>Selected 
              <br/>
                <html:select style="width:300px"  property="type1" multiple="true" size="7"  styleId="chosen" onchange="doUpdate( true, false );">
                  <html:optionsCollection name="LoginForm" property="rlist" label="dname" value="d_type" />
                </html:select>	
             </td>

          </tr>

          <tr>
            <td colspan="3" style="text-align: center;">
              Date: &nbsp;&nbsp;&nbsp;&nbsp;
            
              <html:text property="edate" styleClass="calendarSelectDate" style="width: 120px;" readonly="true" />
            </td>
          </tr>

          <tr>
            <td colspan="3" style="text-align:center">

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
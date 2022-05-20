<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


<html:html>

<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");   
%>
 <jsp:include page="<%=toop%>"/> 



<head>

<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />


<script language=javascript>
/**
* Move selected items between lists.
* <p>
* @param sourceId ID of source list
* @param destId ID of destination list
* @param all true iff moving all
*/
function doMove( sourceId, destId, all )
{
    // Move the items between the lists.
    var sourceElem = document.getElementById( sourceId );
    var destElem = document.getElementById( destId );
    for( var i = 0; ( i < sourceElem.length ); )
    { if( sourceElem.options[ i ].selected || all )
       { var newOption = document.createElement( "OPTION" );
       newOption.text = sourceElem.options[ i ].text;
       newOption.value = sourceElem.options[ i ].value;
       destElem.options[ destElem.length ] = newOption;
       sourceElem.remove( i );
       }
       else
         i++;
    }

    // Update the button states.
    doUpdate( false, false );

    // Store the chosen items in a hidden field.
    var chosenItem = document.getElementById( "chosenItem" );
    var chosenList = document.getElementById( "chosen" );
    chosenItem.value = "";
    for( var i = 0; ( i < chosenList.length ); i++ )
    { chosenItem.value += chosenList.options[ i ].value + '|';
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
function doUpdate( offAvailable, offChosen )
{
   // Get the lists and deselect if requested.
   var availableList = document.getElementById( "available" );
   var chosenList = document.getElementById( "chosen" );
   if( offAvailable ) availableList.selectedIndex = -1;
   if( offChosen ) chosenList.selectedIndex = -1;
   // Update the button states.
     document.getElementById( "addAll" ).disabled =
( availableList.length == 0 );
     document.getElementById( "removeAll" ).disabled =
( chosenList.length == 0 );
     document.getElementById( "add" ).disabled =
( availableList.selectedIndex < 0 );
     document.getElementById( "remove" ).disabled =
( chosenList.selectedIndex < 0 );
} 




</script>



<script src="./js/selectgroup.js"></script>

</head>
<p>&nbsp;</p>
<p>&nbsp;</p>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

               <html:form action="HOListForm6.do?actionRequested=HOListForm6">
                 <p>&nbsp;</p>
                 <table align="center" cellpadding="0" cellspacing="0" class="table2">
                   <th colspan="5">HO-Groupwise/Rupeewise Target/Sale/Ach/Gr/Pmr</th>

              
                <tr>
                  <td colspan="5">
					<table class="table3" align="center">
			
					  <tr>
						 
						  
						 
						<td> 
							Select Month
					<html:select property="emon">
                        <html:option value="1">Oct</html:option>
                        <html:option value="2">Nov</html:option>
                        <html:option value="3">Dec</html:option>
                        <html:option value="4">Jan</html:option>
                        <html:option value="5">Feb</html:option>
                        <html:option value="6">Mar</html:option>
                        <html:option value="7">Apr</html:option>
                        <html:option value="8">May</html:option>
                        <html:option value="9">Jun</html:option>
                        <html:option value="10">Jul</html:option>
                        <html:option value="11">Aug</html:option>
                        <html:option value="12">Sep</html:option>
                    </html:select>
					
					Mkt Year 
                    <html:select property="eyear" onchange="showCustomer(this.value,'HOAjaxForm2.do?actionRequested=HOAjaxForm2')">
                          <html:optionsCollection name="HOForm2Form" property="ylist" label="yname" value="yval" />
                    </html:select>  
						
						</td>
				      </tr>
				   </table>				 </td>
                </tr>
                  

            

             

                         
                  <tr>
                    <td colspan="5">
                      <div align="center">
                        <html:submit value="Submit" styleClass="button"/>
                        &nbsp;&nbsp; 
                        <html:reset value="Cancel" onclick="window.location.href='./jsp/Home.jsp'" styleClass="button"/> 
                      </div></td>
                  </tr>
                </table>
          </html:form>  
          
        
         <p>&nbsp;</p>
		 <p>&nbsp;</p>
		 <p>&nbsp;</p>
         </br>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
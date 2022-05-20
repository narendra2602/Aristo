<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


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





</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

               <html:form action="ListForm13.do?actionRequested=ListForm13">
                 <p>&nbsp;</p>
				 <br/>
                 <table align="center" cellpadding="0" cellspacing="0" class="table2">
                 <tr>
                   <th colspan="5">Selective Product - UnitWise /ValueWise Sales Analysis</th>
				  </tr>
                 <tr colspan="5">
				 <td>
				 <table border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
                   <tr>
                     <td><div align="left">
                       <html:radio property="rep_type"  value="1"/>
                     H.Q.</div></td>
                     <td><div align="left">
                       <html:radio property="rep_type"  value="2"/>
                     Region</div></td>
                     <td><div align="left">
                       <html:radio property="rep_type"  value="3"/>
                     Area</div></td>
                     <td><div align="left">
                       <html:radio property="rep_type"  value="4"/>
                     Branch</div></td>
                   </tr>
                   <tr>
                     <td><div align="left">
                       <html:radio property="uv"  value="1"/>
                     Unit </div></td>
                     <td><div align="left">
                       <html:radio property="uv"  value="2"/>
                     Value</div></td>
                     <td><div align="left">
                       <html:radio property="uv"  value="3"/>
                     Both</div></td>
                     <td><div align="left"></div></td>
                   </tr>
				 </table>
				 </td>
				 </tr>
                  
              <tr>
                <td colspan="5"><hr></td>
              </tr>

              <% String d = (String) request.getAttribute("data"); %>
				  <tr> 
                  <td colspan="5">   
					<table class="table3" align="center">
   					   <tr>
 					       
							 <td align="center">Select <%=d%> <br/>
								   <html:select style="width:300px" property="codes" multiple="true" size="7"
									styleId="available" onchange="doUpdate( false,true );">
									
								   <html:optionsCollection name="MktForm"
									property="xlist" label="nm2" value="qty2" />  
								   </html:select>					     </td> 
							  
							   <td>
									<br/>
									   <input type="button" style="width:50px;" id="add" onClick="
											doMove( 'available', 'chosen', false );" value=">>" />
										
				
									   <input type="button" style="width:50px;" id="addAll" onclick=
											"doMove( 'available', 'chosen', true );" value=">>>" />
											<br />
											<br />
					
									   <input type="button" style="width:50px;" id="remove" onclick=
											"doMove( 'chosen', 'available', false );" value="<<" />
											
				
									   <input type="button" style="width:50px;" id="removeAll" onclick=
										"doMove( 'chosen', 'available', true );" value="<<<" />
				
									   <html:hidden styleId="chosenItem" property="chosenItem" />							   </td> 
						 
							   <td>Selected <%=d%>
								  <br/>
									  <html:select style="width:300px"
									  property="codes1" multiple="true" size="7"
									  styleId="chosen" onchange="doUpdate( true, false );">
									
									  <html:optionsCollection name="MktForm"
									  property="rlist" label="nm2" value="qty2" />  
									  </html:select>							   </td>
					   </tr>
				    </table>				  </td>
				</tr> 

                  <tr>
                    <td colspan="5"><hr></td>
                  </tr>


                <tr>
                  <td colspan="5"><table class="table3" align="center">
                    <tr>
                      <td> 
					                          From
                  <html:select property="mnth">
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
				
				   To
				
                  <html:select property="mnth1">
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
                  </html:select> Mkt Year
				<html:select property="eyear">
		                <html:optionsCollection name="MktForm" property="ylist" label="yname" value="yval" />
        		</html:select>	
				  
					  
					  </td>
					   </tr>
                  </table></td>
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
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
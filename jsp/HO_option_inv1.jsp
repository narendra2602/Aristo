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
<script>
function disable() {
    document.getElementById("mySelect").disabled=true;
}
function enable() {
    document.getElementById("mySelect").disabled=false;
}
</script>



</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

               <html:form action="HOListInv1.do?actionRequested=HOListInv1">
                 <p>&nbsp;</p>
				 <p>&nbsp;</p>
				
				 <p><br/><br/>

			     </p>
				 <table align="center" cellpadding="0" cellspacing="0" class="table2">
                   <th colspan="5">Stock &amp; Sales Statement </th>
  	
	              <tr>
				   <td >&nbsp;</td>
				   <td colspan="5" >
  				     <table class="table3" align="center">
					    <tr>
						  <td> <html:radio property="opt"  value="1"  onclick="enable()"/> Sales (Selective Product)</td>
				          <td > <html:radio property="opt"  value="2" onclick="disable()"/> Sample (All Products)</td>
                        </tr>
                     </table>
                   </td>    
             	  </tr>
		 
			    <tr> 
                  <td colspan="5">
				   <table class="table3" align="center">
					    <tr><td>Product 
				       <html:select property="code" styleId="mySelect">
	                          <html:optionsCollection name="Inv1Form" property="rlist" label="pname" value="pcode" />   
                       </html:select>
					   </td>
					   </tr>
                    </table>
				  </td>
				</tr> 

                <tr>
				   
				   <td colspan="5"><table class="table3" align="center">
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
                    <html:select property="eyear">
                          <html:optionsCollection name="Inv1Form" property="ylist" label="yname" value="yval" />
                    </html:select>   
					 </td>
                    </tr>
                  </table>

                 </td>
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
         <p>&nbsp;</p>
</body>
 <jsp:include page="<%=foot%>"/> 

</html:html>
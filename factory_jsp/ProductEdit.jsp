<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html:html>
<jsp:include page="F_top.jsp"/> 

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" >
        
       <html:form action="FProdUpdate.do?actionRequested=FProdUpdate" >
                <p>&nbsp;</p>
				<p>&nbsp;</p>				
                <table width="33%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_opt">
                <th colspan="6">Product Master Edit</th>
                  <tr>
                    <td width="5%">&nbsp;</td>
                    <td width="42%">&nbsp;</td>
                    <td width="43%">&nbsp;</td>
                    <td width="10%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Factory Product Code </td>
                    <td><html:text property="f_pcode" readonly="true" size="28" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Factory Product Name </td>
                    <td><html:text property="f_pname" readonly="true" size="28" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Code </td>
                    <td><html:text property="pcode" size="28" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Product Name </td>
                    <td><html:text property="pname" size="28"  /></td>
                    <td>&nbsp;</td>
                  </tr>
				  <tr>
                    <td>&nbsp;</td>
                    <td>Packing</td>
                    <td><html:text property="pack" size="28" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Division</td>
					<td><html:text property="division" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Type (Sales/Sample) </td>
					<td><html:text property="tp" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  
				  
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td colspan="4">
	                <div align="center">
 				    <html:submit  value="Submit" styleClass="button"/>&nbsp;&nbsp;
					<html:reset  value="Cancel" styleClass="button"/>                    
                    </div>
					</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>

			<p>&nbsp;</p>
	   </html:form>
	</body>
 <jsp:include page="F_footer.jsp"/> 
	
</html:html>
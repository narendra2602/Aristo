<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html>

 <jsp:include page="F_top.jsp"/> 
 
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 

<br/> 
    
   	<html:form action="ListFProduct.do?actionRequested=ListFProduct" >    	
	<table class="search">
      <tr>
	    <td>
			<html:text property="search" style="Height:26px; Width:200px;" />
			<html:submit value="Search" styleClass="button"/> 
	    </td>
	  </tr>
	</table>
   </html:form>		
   
        <display:table id="data" name="requestScope.ProdForm.plist" requestURI="./ListFProduct.do?actionRequested=ListFProduct"         pagesize="15" export="true" >

            <display:caption media="html"> <strong><br/>Product Master<br/> </strong> </display:caption>
            <display:caption media="excel rtf pdf">Product Master</display:caption>
        
            <display:column property="f_pcode" title="Factory Product Code" style="width:12%"  />
            <display:column property="f_pname" title="Factory Product Name"/>
            <display:column property="pcode" title="Product Code"/>
            <display:column property="pname" title="Product Name" /> 
            <display:column property="pack" title="Packing" /> 
            <display:column property="link" title="Action" href="FProdEdit.do?actionRequested=FProdEdit" paramId="f_pcode" paramProperty="f_pcode" />				
			
        </display:table>
		
		
    </body>
	<br/>
 <jsp:include page="F_footer.jsp"/> 
</html>

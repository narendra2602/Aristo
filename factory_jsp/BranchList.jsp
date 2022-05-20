<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html>

 <jsp:include page="F_top.jsp"/> 
 
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
    
   <br/>
        <display:table id="data" name="requestScope.BranchForm.blist" requestURI="./ListFBranch.do?actionRequested=ListFBranch"         pagesize="15" export="true" >

            <display:caption media="html"> <strong><br/>Branch Master<br/> </strong> </display:caption>
            <display:caption media="excel rtf pdf">Branch Master</display:caption>
        
            <display:column property="compt_code" title="Company Code" style="width: 5%" />
            <display:column property="brn_id" title="Branch Id"/>
            <display:column property="brn_desc" title="Branch Description"/>
            <display:column property="depo_code" title="Depo Code" style="width:8%" /> 
            <display:column property="depo_name" title="Depo Name" /> 
        </display:table>
		
		
    </body>
	<br/>
 <jsp:include page="F_footer.jsp"/> 
</html>

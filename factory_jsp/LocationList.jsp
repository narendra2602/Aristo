<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html>

 <jsp:include page="F_top.jsp"/> 

   
    <body>
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
    
   <br/>
        <display:table id="data" name="requestScope.LocationForm.llist" requestURI="./ListFLocation.do?actionRequested=ListFLocation"         pagesize="15" export="true" >

            <display:caption media="html"> <strong><br/>Location Master<br/> </strong> </display:caption>
            <display:caption media="excel rtf pdf">Location Master</display:caption>
        
            <display:column property="comp_code" title="Company Code" style="width:8%"  />
            <display:column property="loc_id" title="Location Id"/>
            <display:column property="loca_name" title="Location Name"/>
            <display:column property="loc_type" title="Location Type" /> 
            <display:column property="loc_abbr" title="Location ABBR" /> 
        </display:table>
		
		
    </body>
	<br/>
 <jsp:include page="F_footer.jsp"/> 
</html>

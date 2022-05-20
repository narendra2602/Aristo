<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<% 
String css1 = (String) session.getAttribute("css");
%>

<head>

   <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
      
</head>

 <display:table id="data" name="sessionScope.UploadForm.ulist" pagesize="24" requestURI="./UploadStatus.do?actionRequested=UploadStatus" >
            <display:caption media="html">
		      <strong><br/>Upload Status secnd he</strong>
		    </display:caption>
            <display:column property="file_name" title="File Name" />
            <display:column property="file_path" title="File Upload"/>			
            <display:column property="upload" title="Upload Status"/>
            <display:column property="depo_name" title="Depo Name"/>
        	<display:column property="u_date" title="Date"/>			
            <display:column property="u_time" title="Time"/>
        </display:table>
		

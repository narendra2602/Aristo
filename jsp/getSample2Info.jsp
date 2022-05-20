<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="com.aristo.form.SampleRepo2Form" %>

<head>

   <!-- Script -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/js/select2.min.js"></script>

   <!-- CSS -->
   <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet" />
   <link href="css/select2-bootstrap.css" rel="stylesheet" type="text/css" />




</head>
<html:html>

   <body>
      <html:form action="SampleListRepo2.do?actionRequested=SampleListRepo2" styleId="myForm" method="Post">

         
            <div id="results" class="modal fade" role="dialog" style="overflow:hidden;display: inline-block;vertical-align: top;">
               <html:select property="code" styleId="selUser" style="width:500px; ">
                  <html:optionsCollection name="SampleRepo2Form" property="plist" label="pname" value="pval" />
               </html:select>
            </div>
          


      </html:form>

   </body>



</html:html>
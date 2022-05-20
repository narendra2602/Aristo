<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="com.aristo.form.SampleRepo2Form" %>



<html:html>

   <body>
      <html:form action="SampleListRepo2.do?actionRequested=SampleListRepo2" styleId="myForm" method="Post" >
           <div id="abc"  >
         <html:select property="gcode" styleId="mygroup" onchange="selectGroup(this.value)">
            <html:optionsCollection name="SampleRepo2Form" property="grplist" label="gpname" value="gpcode" />
         </html:select>
      </div>
       </html:form>



   </body>

</html:html> 
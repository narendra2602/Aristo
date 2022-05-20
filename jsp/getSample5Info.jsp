<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="com.aristo.form.SampleRepo2Form" %>



<html:html>

   <body>
      <html:form action="SampleListRepo5.do?actionRequested=SampleListRepo5" styleId="myForm" method="Post" >
                  <html:select property="pcode" styleId="mySelect">
                     <html:optionsCollection name="SampleRepo2Form" property="plist" label="pname" value="pval" />
                  </html:select>
      </html:form>

   </body>

</html:html>
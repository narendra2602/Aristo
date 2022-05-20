<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="com.aristo.form.LoginForm" %>
<html:html>
  <body>
    <html:form styleId="auto_off" action="DailyList.do?actionRequested=DailyList" onsubmit="return validateDate()">
      <html:select property="type" style="width: 120px;">
        <html:optionsCollection name="LoginForm" property="divlist" label="dname" value="dcode" />
      </html:select>
    </html:form>
  </body>
</html:html>
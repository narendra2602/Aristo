<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>

<html:html>
	<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
String css2 = (String) session.getAttribute("menucss");
if(css2==null){
	css2="menu.css";
}
%>

	<head>
		<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
		<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
		<link href="./css/new/<%=css2%>" rel="stylesheet" type="text/css" />

		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="./css/new/styles.css" rel="stylesheet">
	

		<jsp:include page="head-scripts.jsp" />


	</head>

	<body>

			<jsp:include page="header-without-user.jsp" />

			<div class="animsition11">
			<div class="brselect">
				<html:form action="UserForward.do?actionRequested=UserForward">



					<div class="brselect-heading">Select </div>

					<div>
						<html:select property="type" styleClass="brselect-options">
							<html:optionsCollection name="LoginForm" property="divlist" label="dname" value="d_type" />
						</html:select>
					</div>
					<div>
						<html:submit value="Submit" styleClass="brselect-button" />
					</div>

					</html:form>
			</div>

		</div>

		<jsp:include page="<%=foot%>"/> 
	</body>

	</html:html>
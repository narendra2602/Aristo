<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  

<html:html>
<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
String css2 = (String) session.getAttribute("menucss");
%>

<head>
	<title>Aristo Pharmaceuticals Pvt. Ltd.</title>
	<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
	<link href="./css/new/<%=css2%>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="./js/TransparentPng.js"></script>	
		<jsp:include page="head-scripts.jsp" />
		<link href="./css/new/styles.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body id="target">

	


<jsp:include page="header-without-user.jsp" />

		
		<div class="animsition111">
			<div class="brselect-branch">
				<html:form action="BranchSelect.do?actionRequested=BranchSelect">



					<div class="brselect-heading">Select Branch</div>

					<div>
						<html:select property="code" size="10"  styleClass="brselect-options-branch" >
							<html:optionsCollection name="LoginForm" property="tlist" label="dname" value="dcode" />  
						 </html:select> 
					</div>
					<div >
						
						<html:submit  value="Submit" styleClass="brselect-button"  styleId="loader4"/>
						
			 
		 
					</div>

				</html:form>
			</div>
		
		</div>



 <jsp:include page="<%=foot%>"/> 

	</div>
  </body>
</html:html>
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

<link href="./css/new/styles.css" rel="stylesheet">

<jsp:include page="head-scripts.jsp" />
	

 


</head>
 
<body id="target">

		<jsp:include page="header-without-user.jsp" />

		
		<div class="animsition11">
			<div class="brselect-branch" style="height: 340px;">
				<html:form action="BranchSelect.do?actionRequested=BranchSelect">



					<div class="brselect-heading">Select Branch</div>

					<div >
						<html:select property="code" size="10" style="max-height: 260px;" styleClass="brselect-options-branch" >
							<html:optionsCollection name="LoginForm" property="tlist" label="dname" value="dcode" />  
						 </html:select> 
					</div>
					<div class="brselect-button-cover">
						
						<html:submit  value="Submit" styleClass="brselect-button-branch" styleId="loader4"/>
						<html:reset value=" Back " onclick="window.location.href='/Aristo/switchUserForward.do?actionRequested=switchUserForward'" styleClass="brselect-button-branch"/>
			 
		 
					</div>

				</html:form>
			</div>
		
		</div>



 <jsp:include page="<%=foot%>"/> 

	</div>
  </body>
</html:html>
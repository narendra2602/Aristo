<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>

<html>
<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" />

<head>
	<link href="css/<%=css1%>" rel="stylesheet" type="text/css" />

	<style>
		.table-cont {
			/**make table can scroll**/
			max-height: 460px;
			overflow: auto;
			/** add some style**/
			/*padding: 2px;*/
			background: #ddd;
			margin: 20px 10px;
			box-shadow: 0 0 1px 3px #ddd;
		}

		thead {
			background-color: #ddd;
		}

	</style>
	
</head>

<body>

	<% 
response.setHeader("Cache-Control","no-cache");  
response.setHeader("Cache-Control","no-store");  
%>


	<%    
	String whead=" ";
	whead= (String) request.getAttribute("whead");
 %>

	<jsp:scriptlet>
		request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
		{
		public String addRowClass()
		{
		int data = ((LoginFormBean)getCurrentRowObject()).getId();
		if (data==2)
		return "bad";
		else
		if (data==3)
		return "vbad";
		else
		if (data==4)
		return "good";
		else
		return "abc";

		}

		});
	</jsp:scriptlet>


	<table cellspacing="0" cellpadding="0" class="status" style="height:20px;">
		<tr>
			<td style="text-align: center;"> <%=whead%> </td>
		</tr>
	</table>


	<div class='table-cont' id='table-cont'>
		<display:table id="data" name="requestScope.LoginForm.ulist"
			requestURI="./DailyList.do?actionRequested=DailyList" export="true" decorator="dyndecorator">

			<display:caption media="excel rtf pdf"><%=whead%></display:caption>
			<display:column property="code" title="CODE" headerClass="l" class="l" />
			<display:column property="f_name" title="BRANCH NAME" class="l" />
			<display:column property="bdate" title="BILLING DATE" format="{0,date,dd/MM/yyyy}" headerClass="l" 	class="l" />
			<display:column property="edate" title="ENTRY DATE" format="{0,date,dd/MM/yyyy}" headerClass="l" class="l" />
			<display:column property="last_ltime" title="TIME" headerClass="l" class="l" />
			<display:column property="status" title="REMARK" class="l" />

		</display:table>

	</div>
	<br /><br /><br />
</body>

<script>
	window.onload = function () {
		var tableCont = document.querySelector('#table-cont')
		/**
		 * scroll handle
		 * @param {event} e -- scroll event
		 */
		function scrollHandle(e) {
			var scrollTop = this.scrollTop;
			this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
		}

		tableCont.addEventListener('scroll', scrollHandle)
	}
</script>

<jsp:include page="<%=foot%>" />

</html>
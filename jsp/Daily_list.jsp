<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
</head>
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
			<display:column property="access_t" title="BR" headerClass="r" class="r" />

			<display:column property="f_name" title="BRANCH NAME" class="l" style="width:150px;" />
			<% // <display:column property="opt" title="BUDGET %"  headerClass="r" class="r" /> %>



			<display:column property="sales_bud" title="BUDGET" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="trd_sale" title="SALES" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="ach" title="Ach % " format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="def" title="SUR/DEF" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="cn100" title="CN 100%" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="sale_today" title="TODAY SALES" format="{0,number,0.00}" headerClass="r"
				class="r" />
			<display:column property="lmsale" title="LM SALE" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="lysale" title="LY SALE" format="{0,number,0.00}" headerClass="r" class="r" />
			<% // <display:column property="collc" title="COLL TODAY" format="{0,number,0.00}" headerClass="r" class="r" /> %>
			<display:column property="collcumm" title="COLL CUMM." format="{0,number,0.00}" headerClass="r" class="r" />
			<%	// <display:column property="remit" title="REMIT TODAY" format="{0,number,0.00}" headerClass="r" class="r" />
		// <display:column property="remitcumm" title="REMIT CUMM." format="{0,number,0.00}" headerClass="r" class="r" />
		%>
			<display:column property="outstnd" title="O/S AS ON" format="{0,number,0.00}" headerClass="r" class="r" />
			<display:column property="status" title="STATUS" headerClass="r" class="r" />
			<display:column property="edate" title="ENTRY DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r" />
			<display:column property="last_ltime" title="TIME" headerClass="r" class="r" />


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
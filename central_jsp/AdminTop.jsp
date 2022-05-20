<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>


<% 
String css2 = (String) session.getAttribute("menucss");

%>

<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=jsp/session_exp.jsp">
<html class=" js no-touch" lang="en">

<head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <jsp:include page="head-scripts.jsp" />


    <!-- css -->

    <link href="./css/new/<%=css2%>" rel="stylesheet" type="text/css" />
    <link href="./css/new/styles.css" rel="stylesheet">

</head>

<body>
    <jsp:include page="header-with-user.jsp" />
    <!-- start header -->
    <header>
        <div class="container" >
            <div class="navbar navbar-static-top" style="display: flex; flex-grow: 0.5;">
                <div class="navigation" style=" display: flex; flex-grow: 1;">
                    <nav style=" display: flex; flex-grow: 1;">



                        <ul class="nav topnav bold" style="display: flex; flex-grow: 1; justify-content: space-between;">
                            <li>
                                <a href="/Aristo/UserHomeForward.do?actionRequested=UserHomeForward">Home </a>
                            </li>

                            <li class="dropdown">
                                <a href="#">Masters <i class="icon-angle-down"></i></a>
                                <ul class="dropdown-menu bold" style="display: none; width:120px;">


                                    <li><a href="/Aristo/AdminListProduct.do?actionRequested=AdminListProduct">Product
                                            Master</a></li>
                                    <li><a href="/Aristo/ListProduct1.do?actionRequested=ListProduct1">Product Master
                                            (C.W.H)</a></li>
                                    <li><a href="/Aristo/AdminListGroupSales.do?actionRequested=AdminListGroupSales">Group
                                            Master (Sales)</a></li>
                                    <li><a href="/Aristo/AdminListGroupMkt.do?actionRequested=AdminListGroupMkt">Group
                                            Master (MKT)</a></li>
                                    <li><a href="/Aristo/ListBranch.do?actionRequested=ListBranch">Branch Master</a>
                                    </li>


                                </ul>
                            </li>



                            <li class="dropdown">
                                <a href="#">User Management <i class="icon-angle-down"></i></a>
                                <ul class="dropdown-menu bold" style="display: none; width:120px;">


                                    <li> <a href="/Aristo/addUsertypeForward.do?actionRequested=addUsertypeForward">Add
                                            New User</a></li>
                                    <li><a href="/Aristo/UserList.do?actionRequested=UserList">Edit User Info</a></li>
                                    <li> <a href="/Aristo/UserRightsList.do?actionRequested=UserRightsList">Edit User
                                            Right</li>
                                    <li> <a href="/Aristo/UploadStatus.do?actionRequested=UploadStatus">Updation
                                            Status</a> </li>
                                    <li><a href="/Aristo/UploadStatus.do?actionRequested=UploadStatus">Updation
                                            Status</a></li>
                                    <li> <a href="/Aristo/UserStatus.do?actionRequested=UserStatus">User Login
                                            Details</a></li>

                                </ul>
                            </li>



                            <li class="dropdown">
                                <a href="#">Administration <i class="icon-angle-down"></i></a>
                                <ul class="dropdown-menu bold" style="display: none; width:120px;">


                                    <li><a href="/Aristo/ChangepassForward.do?actionRequested=ChangepassForward">Change
                                            Password</a></li>
                                </ul>
                            </li>



                            <li class="dropdown">
                                <a href="/Aristo/switchUserForwardAdmin.do?actionRequested=switchUserForwardAdmin">Switch</a>	
                                </ul>
                            </li>
                    </nav>
                </div>
                <!-- end navigation -->
            </div>
        </div>
        </div>
        </div>
    </header>
    <!-- end header -->


    <!-- javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script src="js/custom.js"></script>

</body>

</html>
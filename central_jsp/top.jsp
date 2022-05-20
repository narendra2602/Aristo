<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

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
		<div class="container"  >
				<div class="navbar navbar-static-top"  >
                    <div class="navigation" >
                       
						<nav>

                            <% 
                                LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
                                String typ = lfb.getType();
                                String x = lfb.getBranch_name();
                                String y = lfb.getAccess_t(); 
                                String z = lfb.getD_type();
                                String msg = lfb.getMsg();			
                                String topimg=null;        
                                       x = x + " "+y;
                                      topimg="images/topbas.jpg";   
                                if (z.equals("A"))
                                  topimg="images/topbas.jpg";
                                  
                                if (z.equals("T"))
                                  topimg="images/tf_top.jpg";
                                  
                                if (z.equals("G"))
                                  topimg="images/genetica_top.jpg";
                    
                                List rlist = (List) session.getAttribute("repolist");
                                Iterator itr = rlist.iterator();
                                int rindex = rlist.size();
                                String disp[]=new String [rindex];
                                String link[]=new String [rindex];
                                int ri=0;
                                int k=0;
                                boolean flag=false;
                    
                                List tlist = (List) session.getAttribute("tablist");
                                LoginFormBean l = null;
                                Iterator it = tlist.iterator();
                                int index = tlist.size();
                                int i=0;
                                String a[] = new String[index];
                                String a1[] = new String[index];
                                int a0[] = new int[index];
                                int t2[] = new int[index];
                                while (it.hasNext())
                                {
                                    l = (LoginFormBean) it.next();
                                    a0[i]=l.getTab_id();
                                    t2[i]=l.getTab_width();
                                    a[i]=l.getTab_name();
                                    a1[i]=l.getTab_link();
                                    i++;   
                                }
                          
                           %>
                    
                    
						<ul class="nav topnav bold"  >
							<li>
							<a href="/Aristo/UserHomeForward.do?actionRequested=UserHomeForward">Home </a>
                            </li>
                            
                            <%
                                l = null;
                                for (int n=0;n<index;n++)
                                {
                            %>
    							<li class="dropdown">
                                    <a href="#"><%=a[n]%> <i class="icon-angle-down"></i></a>
                                    <ul class="dropdown-menu bold" style="display: none; width:<%=t2[n]%>px;">                                     
                            <%
                                    ri=0;
                                    if (flag)
                                    { 
                                        disp[0]=l.getRepo_name();
                                        link[0]=l.getRepo_link();
                        
                                        ri=1;
                                     }
                                               
                                    while (itr.hasNext())
                                    {
                                       
                                        l = (LoginFormBean) itr.next();
                                        
                                        if (a0[n]==l.getTab_id())
                                        {
                                            disp[ri]=l.getRepo_name();
                                            link[ri]=l.getRepo_link();
                                            ri++;   
                                        }
                                        else
                                        {
                                            flag=true;
                                            break;
                                        }
                                    }
                        
                                    for (k=0;k<ri;k++)
                                     {
                            %>
                                       <li><a href="<%=link[k]%>"><%=k+1%> - <%=disp[k]%></a></li>
                            <% 
                                     }	
                            %>
                                    </ul>
                                </li>         
                            <%    
                                }     
                            %>


							
						</ul>
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
    <marquee class="marquee-msg"><%=msg%></marquee>
</body>
</html>
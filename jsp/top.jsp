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
	
    <style>
        
        .dropdown1 {
          position: relative;
          display: inline-block;

        }
        
        .dropdown-content {
          display: none;
          position: fixed;
          background-color: #f1f1f1;
          min-width: 160px;

		  top:50%;
		  left:30%;
		  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
          z-index: 1;
 
		  right: -15px;
          

        }
        
        
        
        
        .dropdown1:hover .dropdown-content {display: block;}
        

        </style>
</head>
<body >
   
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
                                String image[]=new String [rindex];
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
                                        image[0]=l.getRepo_image();
                        
                                        ri=1;
                                     }
                                               
                                    while (itr.hasNext())
                                    {
                                       
                                        l = (LoginFormBean) itr.next();
                                        
                                        if (a0[n]==l.getTab_id())
                                        {
                                            disp[ri]=l.getRepo_name();
                                            link[ri]=l.getRepo_link();
                                            image[ri]=l.getRepo_image();
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
                                       <li class="dropdown1">
									     <a href="<%=link[k]%>"><%=k+1%> - <%=disp[k]%> 
									       <div class="dropdown-content">
										      <% if (image[k]!=null)
								             	 {
								                %>	
												<img  sizes="(max-width: 400px) 480px,800px" src="data:image/png;base64,<%=image[k]%>"  />
												 <%   
													}
  								                 %>	

										   </div>
										 </a>
									   </li>

                                       <!-- <div class="dropdown">
                                        <button class="dropbtn">Dropdown</button>
                                        <div class="dropdown-content">
                                          <a href="#">Link 1</a>
                                          <a href="#">Link 2</a>
                                          <a href="#">Link 3</a>
                                        </div>
                                      </div> -->
                                       <!--<li><span class="hiddentxt"><a href="https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg"><a href="<%=link[k]%>"><%=k+1%> - <%=disp[k]%></a></a></span><span class="hiddenimg"><img src="https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg" width="250" /></span></li> -->
                                       <!--<li><a href="<%=link[k]%>" class="hiddentxt"><%=k+1%> - <%=disp[k]%> </a><span class="hiddenimg"><img src="https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg" width="150" /></span></li> -->
                                       <!-- <div class="myDIV">
                                       <li><a href="<%=link[k]%>"><%=k+1%> - <%=disp[k]%></a></li>
                                       </div> -->

                                      
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
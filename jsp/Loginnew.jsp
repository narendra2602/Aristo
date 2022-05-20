<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>
<HEAD>
<TITLE> Aristo - Login   </TITLE>
<META CHARSET="utf-8">
<META NAME="viewport" CONTENT="width=device-width,initial-scale=1.0"> 
<META http-equiv="X-UA-Compatible"  CONTENT="ie=edge">
<META NAME="Description" CONTENT="">
<link href=".\css\new\styles.css" rel="stylesheet"/>
<link href=".\css\new\login.css" rel="stylesheet"/>




</HEAD>

<BODY id="target">
     
    <div class="home-page-circle-2"></div>
    <div class="home-page-square-1"></div>
    <div class="home-page-square-2"></div>
    <div class="home-page-square-3"></div> 
    <div class="container">


            <div class="animsition111">
    <div class="join-main-section" >

        <img class="aristo-logo" src="img\aristologo.png"/>
        
        <html:form styleId="auto_off" action="authenticateUser.do?actionRequested=authenticateUser" styleClass="join-form" style="background-color: brown;">
             
                <div class="login-label">
                        <label><u>LOGIN</u></label>
                </div>
             
                <div class="input-group">
                    <label>Username</label>
                    <html:text property="login_name" />
                </div>
                <div class="input-group">
                    <label>Password</label>
                    <html:password property="password" />
                </div>
                <div style="text-align: center;">
                     
                        <html:submit value="Login" styleClass="input-group-btn" styleId="loader4"/>
                </div>
                <br/><br/>
                
          
        </html:form>
            </div>
          
        </div>     
    </div>
    

    <jsp:include page="footer.jsp" />
    

 
</BODY>
</html:html> 

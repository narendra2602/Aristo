<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  
<%@ page import="com.aristo.valueobject.MktFormBean" %>  
<%@ page import="java.util.*" %>
<%@ page import="org.displaytag.sample.*" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<% 
String toop  = (String) session.getAttribute("top");
String foot = (String) session.getAttribute("footer");
String css1 = (String) session.getAttribute("css");
%>

<jsp:include page="<%=toop%>" flush="true"/> 

<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
    </head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
        <% 
           response.setHeader("Cache-Control","no-cache");  
           response.setHeader("Cache-Control","no-store");  
        %> 
   
        <br>   
		
        <%
        String whead=" ";
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        String ak = lfb.getBranch_name();
        String aki = lfb.getAccess_t();         
                ak = ak + " "+aki;
      
        org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
        pageContext.setAttribute("totals", totals);
		
      Map map = (Map) request.getAttribute( "rlist" );   
      int k = map.size(); 
      String iid = "data";
	  String head=null;
      String head1=null;
      String head2=null;
      String head3=null;
	  String head4=null;
	  String head5=null;

///////////// For UV=3////////////
	  String head12=null;
      String head13=null;
      String head14=null;
      String head15=null;
	  String head16=null;
	  String head17=null;
	 
	  int uv=0;
      for (int j=1; j<=k; j++) 
        {
            MktFormBean rf = new MktFormBean();  
        	List l = (List) map.get(j);  
             	Iterator it =  l.iterator();
             	if(it.hasNext())
             	{
             	 rf=(MktFormBean)it.next();    
             	  whead=rf.getNm3();
             	  head = "TARGET UNIT "+rf.getNm2();
				  head1 = "ACTUAL UNIT "+rf.getNm2();
				  head2 = "DIFF. UNIT "+rf.getNm2();
				  head3 = "CUM TARGET UNIT "+rf.getNm4();
             	  head4 = "CUM ACTUAL UNIT "+rf.getNm4(); 
              	  head5 = "CUM DIFF. UNIT "+rf.getNm4();



             	  head12 = "TARGET VALUE "+rf.getNm2();
				  head13 = "ACTUAL VALUE "+rf.getNm2();
				  head14 = "DIFF. VALUE "+rf.getNm2();
				  head15 = "CUM TARGET VALUE "+rf.getNm4();
             	  head16 = "CUM ACTUAL VALUE "+rf.getNm4(); 
              	  head17 = "CUM DIFF. VALUE "+rf.getNm4();



				  uv = rf.getUv(); 
           	
                 }  
 	 	     String lupdate = rf.getLupdate();	                
             session.setAttribute("list",l);        	 
             iid = iid+j;
%>

     <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((MktFormBean)getCurrentRowObject()).getColor();
			  if (data==2)
			     return "bad";
			  else
			    if (data==3)
 				     return "good";
				  else 
    				       return "abc";
				  	 
            }
        });
  </jsp:scriptlet>
    
    <br/>
<div id="main">	
  <display:table id="<%=iid%>" class="simple nocol" name="sessionScope.list" requestURI="./ListForm10.do?actionRequested=ListForm10" pagesize="25" export="true" decorator="dyndecorator">
        <display:caption media="html"><br/>
		
	    <table cellspacing="0" cellpadding="0" class="status" >
		  <tr>
			<td><div align="left">BRANCH NAME: <%=ak%> </div></td>
			<td><div align="right">LAST UPDATE: <%=lupdate%> </div></td>
		  </tr>
		  <tr>
			<td colspan="2"><div align="center"> <%=whead%> </div></td>
		  </tr>
		</table>
		
        </display:caption>
        <display:caption media=" excel rtf pdf">BRANCH NAME: <%=ak%>, <%=whead%>,  LAST UPDATE: <%=lupdate%></display:caption>

		    <display:column property="mcode" title="CODE"  /> 
            <display:column property="mname" title="NAME"  /> 
            <display:column property="pack" title="PACK"  /> 
		<% if ((uv==1))
		   {
		 %>	
    <display:column property="qty2" title="<%=head%>" style="width: 64px;" headerClass="r" class="r" /> 
    <display:column property="qty3" title="<%=head1%>" style="width: 62px;" headerClass="r" class="r" /> 
    <display:column property="qty4" title="<%=head2%>" style="width: 65px;" headerClass="r" class="r" /> 
    <display:column property="qty5" title="<%=head3%>" style="width: 90px;" headerClass="r" class="r"/> 
    <display:column property="qty6" title="<%=head4%>" style="width: 90px;" headerClass="r" class="r"/> 
    <display:column property="qty7" title="<%=head5%>" style="width: 90px;" headerClass="r" class="r"/> 
		<%  
		   }
		   if ((uv==2))
		    {
		%>	
     <display:column property="dval2" title="<%=head12%>" style="width: 64px;" format="{0,number,0.00}" headerClass="r" class="r" /> 
     <display:column property="dval3" title="<%=head13%>" style="width: 62px;" format="{0,number,0.00}" headerClass="r" class="r" />     <display:column property="dval4" title="<%=head14%>" style="width: 65px;" format="{0,number,0.00}" headerClass="r" class="r" />     <display:column property="dval5" title="<%=head15%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
     <display:column property="dval6" title="<%=head16%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
     <display:column property="dval7" title="<%=head17%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
         <%
		     }
 		   if ((uv==3))
		    {

		 %>	 
     <display:column property="qty2" title="<%=head%>"  style="width: 64px;" headerClass="r" class="r" /> 		 
     <display:column property="dval2" title="<%=head12%>" style="width: 64px;" format="{0,number,0.00}" headerClass="r" class="r" /> 
 	 <display:column property="qty3" title="<%=head1%>" style="width: 62px;" headerClass="r" class="r" />  
     <display:column property="dval3" title="<%=head13%>" style="width: 62px;" format="{0,number,0.00}" headerClass="r" class="r" />     <display:column property="qty4" title="<%=head2%>" style="width: 65px;" headerClass="r" class="r" /> 
 	 <display:column property="dval4" title="<%=head14%>" style="width: 65px;" format="{0,number,0.00}" headerClass="r" class="r" />     <display:column property="qty5" title="<%=head3%>" style="width: 90px;" headerClass="r" class="r"/>  
	 <display:column property="dval5" title="<%=head15%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
     <display:column property="qty6" title="<%=head4%>" style="width: 90px;" headerClass="r" class="r"/>      
	 <display:column property="dval6" title="<%=head16%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
     <display:column property="qty7" title="<%=head5%>" style="width: 90px;" headerClass="r" class="r"/>    
	 <display:column property="dval7" title="<%=head17%>" style="width: 90px;" format="{0,number,0.00}" headerClass="r" class="r"/> 
          <%
		     }
		  %>	 		 
	</display:table> 
</div>	
 <%
   }
 %>
         
        
<br><br> 
</body>
 <jsp:include page="<%=foot%>"/> 
</html>
 
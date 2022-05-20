<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="com.aristo.valueobject.SampleRepo2FormBean" %>  
<%@ page import="org.displaytag.sample.*" %> 

<html>
<head>
<% 
String top  = (String) session.getAttribute("top");
String footer = (String) session.getAttribute("footer");
String css = (String) session.getAttribute("css");
%>

<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 
    <style>
        .table-cont {
          /**make table can scroll**/
          max-height: 480px;
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

String whead = (String) request.getAttribute("whead");

 
%> 


    <jsp:scriptlet>
        request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
        {
            public String addRowClass()
            {
              int data = ((SampleRepo2FormBean)getCurrentRowObject()).getColour();
			  
			  if (data==1)
			    return ((SampleRepo2FormBean)getCurrentRowObject()).getRqty() > 0 ? "money" : "mon";

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


    
   <p>&nbsp;</p>
   <table cellspacing="0" cellpadding="0" class="status" style="height:20px;">

      <tr>
        <td style="text-align: center;"> <%=whead%> </td>
      </tr>
    </table> 

   <div class='table-cont' id='table-cont'>
 <display:table id="data" name="requestScope.SampleRepo2Form.rlist" requestURI="./SampleListRepo2a.do?actionRequested=SampleListRepo2a" export="true" decorator="dyndecorator" >
   
           
            <display:caption media="excel rtf pdf"><%=whead%> </display:caption>
            <display:column property="name" title="PARTY NAME"  />
            <display:column property="city" title="CITY" />
            <display:column property="inv_no" title="DOC NO" />
            <display:column property="inv_dt" title="DOC DATE" format="{0,date,dd/MM/yyyy}"  headerClass="l" class="l" />
            <display:column property="oqty" title="OPENING QTY" class="r"/> 
            <display:column property="rqty" title="RECEIPTS QTY" class="r"/> 
            <display:column property="iqty" title="ISSUE QTY"  headerClass="r" class="r" />
            <display:column property="cqty" title="CLOSING QTY" class="r"/> 
        </display:table>
	</div>	
</body>
<br/>
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
  
<jsp:include page="<%=footer%>"/> 	
</html>
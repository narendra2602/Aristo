<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">


<% String toop  = (String) session.getAttribute("top");
   String foot = (String) session.getAttribute("footer");
   String css1 = (String) session.getAttribute("css");
%>

 <jsp:include page="<%=toop%>"/> 





<head>
<title>Aristo Pharmaceuticals Pvt. Ltd.</title>

  <link href="css/<%=css1%>" rel="stylesheet" type="text/css" />
  <link href="./css/new/styles.css" rel="stylesheet">

</script>


</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">


 <jsp:include page="<%=foot%>"/> 

</body>
</html>

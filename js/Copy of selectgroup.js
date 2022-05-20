
var xmlHttp;   

function showCustomer(str,url)
{ 

 xmlHttp=GetXmlHttpObject();
 if (xmlHttp==null)
   {
   alert ("Your browser does not support");
   return;
   } 

//  var url="AjaxRepo7.do?actionRequested=AjaxRepo7";
  url=url+"&q="+str;
  xmlHttp.open("GET",url,true);
  xmlHttp.onreadystatechange=stateChanged;
  xmlHttp.send(null);

}

function stateChanged() 
 { 
  if (xmlHttp.readyState==4 || xmlHttp.readyState==200)
  { 
     var response = xmlHttp.responseText;
	  if(response){
 	 	document.getElementById("abc").innerHTML = response;
	  }
  }
}


function GetXmlHttpObject()
{
var xmlHttp=null;
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp=new XMLHttpRequest();
  }
catch (e)
  { 
  // Internet Explorer
  try
    {
    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp;
}
 

var xmlHttp;   

function showCustomer(str,url)
{ 
xmlHttp=GetXmlHttpObject();
 
 if (xmlHttp==null)
   {
   alert ("Your browser does not support");
   return;
   } 

//  var url="UploadStatus1.do?actionRequested=UploadStatus1";
//  if (str!="")  
//  {
  url=url+"&search="+str;  
//  }
  nocache = Math.random();
  xmlHttp.open("GET",url+'&nocache = '+nocache,true);
  xmlHttp.onreadystatechange=stateChanged;
  xmlHttp.send(null);

}

function stateChanged() 
 { 
  if (xmlHttp.readyState==4 || xmlHttp.readyState==200)
  { 
     var response = xmlHttp.responseText;
	  if(response){
 	 	document.getElementById("results").innerHTML = response;
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
 


var xmlHttp;   




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
 

//////////////////////////////////////////////////////////////////

var xmlHttp1;   

function showCustomer1(str,url)
{ 
 xmlHttp1=GetXmlHttpObject();
 if (xmlHttp1==null)
   {
   alert ("Your browser does not support");
   return;
   } 

//  var url="AjaxRepo7.do?actionRequested=AjaxRepo7";
  url=url+"&q="+str;
  xmlHttp1.open("GET",url,true);
  xmlHttp1.onreadystatechange=stateChanged1;
  xmlHttp1.send(null);

}

function stateChanged1() 
 { 
  if (xmlHttp1.readyState==4 || xmlHttp1.readyState==200)
  { 
     var response = xmlHttp1.responseText;
	  if(response){
 	 	document.getElementById("abcd").innerHTML = response;
//		document.getElementById("abcd").innerHTML = response;
	  }
  }
}


function GetXmlHttpObject()
{
var xmlHttp1=null;
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp1=new XMLHttpRequest();
  }
catch (e)
  { 
  // Internet Explorer
  try
    {
    xmlHttp1=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp1=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp1;
}


//////////////////////////////////////////////////////////////////


var xmlHttp2;   

function showCustomer2(str,url)
{ 
 var v2 = document.getElementById("location").value;
 xmlHttp2=GetXmlHttpObject();
 if (xmlHttp2==null)
   {
   alert ("Your browser does not support");
   return;
   } 

//  var url="AjaxRepo7.do?actionRequested=AjaxRepo7";
  url=url+"&q="+str+"&r="+v2;
  xmlHttp2.open("GET",url,true);
  xmlHttp2.onreadystatechange=stateChanged2;
  xmlHttp2.send(null);

}

function stateChanged2() 
 { 
  if (xmlHttp2.readyState==4 || xmlHttp2.readyState==200)
  { 
     var response = xmlHttp2.responseText;
	  if(response){
 	 	document.getElementById("abcd").innerHTML = response;
//		document.getElementById("abcd").innerHTML = response;
	  }
  }
}


function GetXmlHttpObject()
{
var xmlHttp2=null;
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp2=new XMLHttpRequest();
  }
catch (e)
  { 
  // Internet Explorer
  try
    {
    xmlHttp2=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp2=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp2;
}



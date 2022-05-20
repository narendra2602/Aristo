<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>


<html:html>
<HEAD>

<script language="javascript">
function Checkfiles()
{
var fcount=0;
var fup;
var fileName;
var flag=false;
var fuup=document.getElementById('f0');

if (fuup.value=="")
{
alert("Please Select File");
}
else
{
flag=true;
}
for (fcount=0; fcount<10;fcount++)
{
fup = document.getElementById('f'+fcount);
fileName = fup.value;
if (fup.value=="")
{
  continue;
}
else
{
var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
if(ext == "xml" || ext == "zip" )
{
flag=true;
} 
else
{
alert("Please Upload Only XML Files\n\nPlease Check File & Upload Again  ");
fup.focus();
flag=false;
}
}
}
if (flag)
{
progress_update();
document.getElementById("bt2").disabled=true;
}


return flag;



}
</script>



<script>

function refreshProgress()
{
    UploadMonitor.getUploadInfo(updateProgress);
}

function updateProgress(uploadInfo)
{
    if (uploadInfo.inProgress)
    {
        var fileIndex = uploadInfo.fileIndex;

        var progressPercent = Math.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);

        document.getElementById('progressBarText').innerHTML = 'upload in progress: ' + progressPercent + '%';

        document.getElementById('progressBarBoxContent').style.width = parseInt(progressPercent * 3.5) + 'px';

        window.setTimeout('refreshProgress()', 1000);
    }

    return true;
}

function startProgress()
{
    updateStatusMessage("");
    document.getElementById('progressBar').style.display = 'block';
    document.getElementById('progressBarText').innerHTML = 'upload in progress: 0%';

    // wait a little while to make sure the upload has started ..
    window.setTimeout("refreshProgress()", 1500);
    return true;
}

function hideProgressBar()
{
    document.getElementById('progressBar').style.display = 'none';
    document.getElementById('progressBarText').innerHTML = '';
}

function UploadMonitor() { }
      UploadMonitor._path = '';
      UploadMonitor.getUploadInfo = function(callback) {
      DWREngine._execute(UploadMonitor._path, 'UploadMonitor', 'getUploadInfo', callback);
}

</script>



<script>

var fCount = 3;
function addFileElement() {

fCount++;

var fObject = document.getElementById('fileSection');
var text = 'File: ';
var tag='<input type="file" onbeforeeditfocus="return false;" name="testFile[' +  fCount +  ']" value="" size="35">';   
var brk='<br>'
var o1 = document.createTextNode(text);
var o2 = document.createElement(tag);
var o3 = document.createElement(brk);
   
   fObject.appendChild(o3);
   fObject.appendChild(o1);
   fObject.appendChild(o2);
   fObject.appendChild(o3); 
   
 // alert("fCount" + fCount);

}


</script>
<TITLE>uploadpage.jsp</TITLE>
<html:base/>
<style type="text/css">
<!--
.style1 {
	color: #FFFFFF;
	font-weight: bold;
	font-size: 14px;
}
body,td,th {
	font-family: Tahoma;
	font-size: 12px;
}
body {
	background-color: #FFEFEC;
}
-->
</style>
<link href="./css/login.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></HEAD>
<html:errors/>

<body>

<div id="wrapper">

  <div id="main" class="clearfix">
    <div id="header">
		<p><img src="./images/topbas.jpg" width="100%" height="85"></p>
   </div> <!-- header -->

<div id="content">
	<p align="center"><strong>Please Upload only XML Files. Do not upload LZH or any other files </strong></p>

<br/>

<html:form  action="/FileUploadAndSave" method="post" enctype="multipart/form-data"  onsubmit="return Checkfiles();">
<table width="45%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DB261B" style="border: 1px solid">
  <tr>
    <td height="32" align="middle" bgcolor="#DB261B"><div align="center"><span class="style1">Upload XML Files</span></div></td>
  </tr>
  <tr> 
   <td align="middle" bgcolor="#FFDAD4"><br/>


<div id="fileSection" >
File: <input id="f0" onbeforeeditfocus="return false;" type="file" name="testFile[0]" value="abc" size="35" ><br>
File: <input id="f1" onbeforeeditfocus="return false;" type="file" name="testFile[1]" value="" size="35"><br>
File: <input id="f2" onbeforeeditfocus="return false;" type="file" name="testFile[2]" value="" size="35"><br>
File: <input id="f3" onbeforeeditfocus="return false;" type="file" name="testFile[3]" value="" size="35"><br>
File: <input id="f4" onbeforeeditfocus="return false;" type="file" name="testFile[4]" value="" size="35"><br>
File: <input id="f5" onbeforeeditfocus="return false;" type="file" name="testFile[5]" value="" size="35"><br>
File: <input id="f6" onbeforeeditfocus="return false;" type="file" name="testFile[6]" value="" size="35"><br>
File: <input id="f7" onbeforeeditfocus="return false;" type="file" name="testFile[7]" value="" size="35"><br>
File: <input id="f8" onbeforeeditfocus="return false;" type="file" name="testFile[8]" value="" size="35"><br>
File: <input id="f9" onbeforeeditfocus="return false;" type="file" name="testFile[9]" value="" size="35"><br>
File: <input id="f10" onbeforeeditfocus="return false;" type="file" name="testFile[10]" value="" size="35"><br>

</div>
<hr>
<p>
  <html:submit property="bt2">Upload All files</html:submit>
  <html:button property="bt" onclick="addFileElement()">Add More Files</html:button>
  <br/>
</p>
<p>
<table align="center"><tr><td>
<div id="showbar" style="font-size:8pt;padding:2px;border:solid black 1px;visibility:hidden">
<span id="progress1">&nbsp; &nbsp;</span>
<span id="progress2">&nbsp; &nbsp;</span>
<span id="progress3">&nbsp; &nbsp;</span>
<span id="progress4">&nbsp; &nbsp;</span>
<span id="progress5">&nbsp; &nbsp;</span>
<span id="progress6">&nbsp; &nbsp;</span>
<span id="progress7">&nbsp; &nbsp;</span>
<span id="progress8">&nbsp; &nbsp;</span>
<span id="progress9">&nbsp; &nbsp;</span>
<span id="progress10">&nbsp; &nbsp;</span>
<span id="progress11">&nbsp; &nbsp;</span>
<span id="progress12">&nbsp; &nbsp;</span>
<span id="progress13">&nbsp; &nbsp;</span>
<span id="progress14">&nbsp; &nbsp;</span>
<span id="progress15">&nbsp; &nbsp;</span>
<span id="progress16">&nbsp; &nbsp;</span>
<span id="progress17">&nbsp; &nbsp;</span>
<span id="progress18">&nbsp; &nbsp;</span>
<span id="progress19">&nbsp; &nbsp;</span>
<span id="progress20">&nbsp; &nbsp;</span>
<span id="progress21">&nbsp; &nbsp;</span>
<span id="progress22">&nbsp; &nbsp;</span>
<span id="progress23">&nbsp; &nbsp;</span>
<span id="progress24">&nbsp; &nbsp;</span>
<span id="progress25">&nbsp; &nbsp;</span>
<span id="progress26">&nbsp; &nbsp;</span>


</div>
</td></tr></table>
<script language="javascript">
var progressEnd = 26; // set to number of progress <span>'s.
var progressColor = 'red'; // set to progress bar color
var progressInterval = 450; // set to time between updates (milli-seconds)

var progressAt = progressEnd;
var progressTimer;
function progress_clear() {
for (var i = 1; i <= progressEnd; i++) document.getElementById('progress'+i).style.backgroundColor = 'transparent';
progressAt = 0;
}
function progress_update() {
document.getElementById('showbar').style.visibility = 'visible';
progressAt++;
if (progressAt > progressEnd) progress_clear();
else document.getElementById('progress'+progressAt).style.backgroundColor = progressColor;
progressTimer = setTimeout('progress_update()',progressInterval);
}
//progress_update(); // start progress bar

</script>
<br/></td>
</tr>
</table>
<p><br/>
</p>
</html:form>
</div>
</div><!-- / main content -->
</div><!-- /wrapper -->

<div id="footer">
<%@include file="log_footer.jsp" %>  
 </div><!-- / footer -->
</BODY>
</html:html>

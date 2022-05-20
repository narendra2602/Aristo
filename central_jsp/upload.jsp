<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html:html>
<head>
<% 
	String top  = (String) session.getAttribute("top");
	String footer = (String) session.getAttribute("footer");
	String css = (String) session.getAttribute("css");
%>
<link href="css/<%=css%>" rel="stylesheet" type="text/css" />
<jsp:include page="<%=top%>"/> 




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
for (fcount=0; fcount<5;fcount++)
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
if(ext == "xml" || ext == "XML" )
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
{   UploadMonitor.getUploadInfo(updateProgress); }

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

<html:base/>

</head>
<html:errors/>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

<p>&nbsp; </p>
<html:form  action="/FileUpload" method="post" enctype="multipart/form-data"  onsubmit="return Checkfiles();">
<table cellpadding="0" cellspacing="0" class="upload">
  <tr>
    <th><div align="center">Upload XML Files</div></th>
  </tr>
  <tr> 
   <td>
   <div align="center"><br/>
   </div>
   <div id="fileSection" >
     <div align="center">File: 
       <input id="f0" onbeforeeditfocus="return false;" type="file" name="testFile[0]" value="abc" size="35" >
       <br>
       File: 
  <input id="f1" onbeforeeditfocus="return false;" type="file" name="testFile[1]" value="" size="35">
  <br>
       File: 
  <input id="f2" onbeforeeditfocus="return false;" type="file" name="testFile[2]" value="" size="35">
  <br>
       File: 
  <input id="f3" onbeforeeditfocus="return false;" type="file" name="testFile[3]" value="" size="35">
  <br>
       File: 
  <input id="f4" onbeforeeditfocus="return false;" type="file" name="testFile[4]" value="" size="35">
  <br>
       File: 
  <input id="f5" onbeforeeditfocus="return false;" type="file" name="testFile[5]" value="" size="35">
  <br>
     </div>
   </div>
<hr align="center">
<p align="center">
  <html:submit property="bt2">Upload All files</html:submit>
  <html:button property="bt" onclick="addFileElement()">Add More Files</html:button>
 <br/>
</p>
<p align="center">
<div align="center">
  <table align="center" class="log">
    <tr><td>
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
        <span id="progress26">&nbsp; &nbsp;</span>        </div>
    </td></tr>
  </table>
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
</div></td>
</tr>
</table>
<br/>
<br/>
<br/>
</html:form>

</body>
<jsp:include page="<%=footer%>"/>
</html:html>

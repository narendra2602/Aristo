<html>
<head>
<title>|| Aristo Pharmaceutical Pvt. Ltd. ||</title>
<link rel="stylesheet" type="text/css" href="./css/central_menu.css" />
<script type="text/javascript" src="./js/jquery_central.js"> </script>
<script type="text/javascript" src="./js/central_menu.js"> </script>
<!--------------------------------------------------------------------------------------+
 |																						|
 | Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)|
 | This notice MUST stay intact for legal use											|
 | Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code				|
 |																						|
 +---------------------------------------------------------------------------------------->
<script type="text/javascript">
ddsmoothmenu.init({
	mainmenuid: "smoothmenu1", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})
</script>
<style type="text/css">
<!--
.style3 {color: #000000}
-->
</style>
</head>

<body Leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<img src="images/head1.gif" width="100%" height="87"><br/>
	<div id="smoothmenu1" class="ddsmoothmenu">
	<ul>
		<li><a href="/Aristo/UserHomeForward1.do?actionRequested=UserHomeForward1"> Home </a></li>
		
		<li><a href="#">Masters</a>
		  <ul>
			  <li><a href="/Aristo/ListAccount1.do?actionRequested=ListAccount1">Branch Master</a></li>
			  <li><a href="/Aristo/ListProduct1.do?actionRequested=ListProduct1">Product Master</a></li>
			  <li><a href="/Aristo/ListGroupSales1.do?actionRequested=ListGroupSales1">Group Master</a></li>  
			  <li><a href="/Aristo/OptBatch.do?actionRequested=OptBatch">Batch Master</a></li>
<!--		  <li><a href="#">Transporter Master</a></li>
			  <li><a href="#">Destination Master</a></li>
			  <li><a href="#">Road Permit Master </a></li>
-->
		  </ul>
		</li>

<!---	<li><a href="#">Transaction</a>
		  <ul>
			  <li><a href="#">Inward Invoice</a></li>
			  <li><a href="#">Outward Invoice</a></li>
			  <li><a href="#">Sales Invoice</a></li>
			  <li><a href="#">Nepal Invoice</a></li>
			  <li><a href="#">Order Entry</a></li>
			  <li><a href="#">Delivery Challan</a></li>
			  <li><a href="#">Packing List</a></li>			  			  
		  </ul>
        </li>
-->

		<li><a href="#">Inventory</a>
		  <ul>
  			  <li><a href="/Aristo/CentralOptInv1.do?actionRequested=CentralOptInv1">Stock & Sales</a></li>
  			  <li><a href="/Aristo/CentralOptInv2.do?actionRequested=CentralOptInv2">Near Expiry List</a></li>
			  <li><a href="/Aristo/CentralOptInv3.do?actionRequested=CentralOptInv3">Non Moving Items</a></li>			  			  			  <li><a href="/Aristo/CentralListRepo2.do?actionRequested=CentralListRepo2">Product/Batch Wise Stock</a></li>
			  <li><a href="/Aristo/CentralOptInv5.do?actionRequested=CentralOptInv5">Stock Ledger</a></li>			  			  
			  <li><a href="/Aristo/CentralOptInv6.do?actionRequested=CentralOptInv6">Location Wise Reciept</a></li>
			  <li><a href="/Aristo/CentralOptInv8.do?actionRequested=CentralOptInv8">Inward Register </a></li>
			  <li><a href="/Aristo/CentralOptInv9.do?actionRequested=CentralOptInv9">Inward Register BranchWise</a></li>
<!--
			  <li><a href="/Aristo/CentralOptInv10.do?actionRequested=CentralOptInv10">Date Wise Stock Value</a></li>
			  <li><a href="#">Allocation V/s Dispatch</a></li>			  			  
-->			  
			  
			  
		  </ul>
		</li>

		<li><a href="#">Dispatch</a>
		  <ul>
			  <li><a href="/Aristo/CentralOptRepo1.do?actionRequested=CentralOptRepo1">Transfer Register</a></li>
			  <li><a href="/Aristo/CentralOptRepo3.do?actionRequested=CentralOptRepo3">Daily Dispatch Report</a></li>
			  <li><a href="/Aristo/CentralOptRepo4.do?actionRequested=CentralOptRepo4">Location Wise Disptach</a></li>
			  <li><a href="/Aristo/CentralOptRepo5.do?actionRequested=CentralOptRepo5">Disptach Summery</a></li>			  
			  <li><a href="/Aristo/CentralOptRepo6.do?actionRequested=CentralOptRepo6">Product Wise Disptach </a></li>			  
		  </ul>
        </li>
		

		<li><a href="#">Tools</a>
		  <ul>
			  <li><a href="/Aristo/UploadForward.do?actionRequested=UploadForward">Upload XML Files</a></li>
			  <li><a href="/Aristo/CUploadStatus.do?actionRequested=CUploadStatus">Upload Status</a></li>
			  <li><a href="/Aristo/ChangepassForward1.do?actionRequested=ChangepassForward1">Change Password</a></li> 
			<% 
			String access=(String) session.getAttribute("all");
			if (access.equals("All"))
			{ 
			%> 
		<li><a href="/Aristo/switchUserForward.do?actionRequested=switchUserForward">Switch (Mf/Tf/Genetica)</a></li> 
			<%
			}
			%>
			 
		  </ul>
			  
			  
			  
			  
		</li>
		
		<li><a href="/Aristo/LogoutUserForward.do?actionRequested=LogoutUserForward">Logout</a>	</li>
	  </ul>
		<div align="right"><br style="clear: left" />
	  </div>
	</div>	
	<%
	String type=(String) session.getAttribute("type");
	String name=(String) session.getAttribute("nm");	
	
	
	%>
	
	
	<table class="dtime" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="20px" background="images/corner.png" ></td>
        <td>&nbsp;</td>
        <td>WELCOME:   <%=name%> <span class="style3">||</span> ACCESS: <%=type%></td>
        <td>&nbsp;</td>
      </tr>
    </table>	</td>
   </tr>
</table>



</body>
</html>
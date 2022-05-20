
<%@ page import="com.aristo.valueobject.LoginFormBean" %>  													 
													 
													 <% 
														LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
													
														String x = lfb.getBranch_name();
														String y = lfb.getAccess_t(); 
														String z = lfb.getD_type();
														String name=lfb.getF_name().split(" ")[1];
														String msg = lfb.getMsg();			
														String topimg=null;        
																	
															
								
												if(z.equals("A"))
													z="MF";	
													else if(z.equals("T"))
													z="TF";
													else if(z.equals("G"))
													z="Genetica";
													else if(z.equals("M"))
													z="MF2";
													else if(z.equals("B"))
													z="MF3";
													else{
														z=y;
													}		
												x= x+"-"+z;										
											
											 %>
								



		<section class="top-section">
			<div class="aristo-title"><img src="./img/aristologo-Cropped.jpg" style="width: 30px; height: 28px; margin-top: 6px;"/>Aristo Pharmaceuticals Pvt. Ltd.</div>
			<section class="top-section-right-cover">
				<div class="top-section-right"><div><img class="User-img-div" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" /></div><div style="margin-right: 10px;"><%=x%> &nbsp; |</div><div><img class="User-img-man" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" /></div><div style="margin-right: 10px;"><%=name%> &nbsp; |</div><div><a href="/Aristo/LogoutUserForward.do?actionRequested=LogoutUserForward"><img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" title="Sign out" class="User-img" /></a></div></div>
			</section>
		</section>

		</section>

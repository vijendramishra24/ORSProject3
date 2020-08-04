    <%@page import="in.co.sunrays.proj3.ctl.ChangePasswordCtl"%>
	<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
	<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
    
<head><style type="text/css">
.container {
	width: auto;
	max-width: 700px;
	margin: 0 auto;
	background-color:black;
	box-shadow: 0px 0px 0px 1px grey, 0px 7px 10px grey;
}
</style></head>
<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.UserDTO"
            scope="request"></jsp:useBean>

<br><br>
<div>
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Change Password</font></label>
		 </div>		
		<br>
		<div align="center">
				
		<div class="alert alert-success" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
		<b> <%=ServletUtility.getSuccessMessage(request)%></b>
		</div>
							
		<div class="alert alert-danger" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%;"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
		<b><%=ServletUtility.getErrorMessage(request)%></b>
		</div>

		</div>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

<div class="container" align="center">
						
            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Old Password<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="password" name="oldPassword" placeholder="Enter Yours Old Password" class="form-control" 
						value="<%=DataUtility
			                    .getString(request.getParameter("oldPassword") == null ? ""
			                            : DataUtility.getStringData(request
			                                    .getParameter("oldPassword")))%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("oldPassword", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
 
 
 			<div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">New Password<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="password" name="newPassword" placeholder="Enter Yours New Password" class="form-control" 
						value="<%=DataUtility
			                    .getString(request.getParameter("newPassword") == null ? ""
			                            : DataUtility.getStringData(request
			                                    .getParameter("newPassword")))%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("newPassword", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
 
 
 			<div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Confirm Password<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="password" name="confirmPassword" placeholder="Confirm Yours New Password" class="form-control" 
						value="<%=DataUtility
			                    .getString(request.getParameter("confirmPassword") == null ? ""
			                            : DataUtility.getStringData(request
			                                    .getParameter("confirmPassword")))%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
 
 <div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-5 col-md-offset-1" align="center">
							
							<button class="btn btn-default" type="submit"	name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=ChangePasswordCtl.OP_SAVE%></button>
							
							<button class="btn btn-default" type="submit"	name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%></button>
		</div>
		</div>
		<br>
 
 </div>

</div>
</div>
</div>
                    
</form>                
<div><%@ include file="Footer.jsp"%></div>
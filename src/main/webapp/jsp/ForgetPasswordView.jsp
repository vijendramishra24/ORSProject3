<%@page import="in.co.sunrays.proj3.ctl.ForgetPasswordCtl"%>
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

 <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

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
			<font style="color:grey; font-style: italic; font-weight: bold; font-size: 2rem;"> Forgot your password?</font></label>
			
			
			
			<label class="text-center container">
			<font style="color:grey; font-style: italic; font-weight: bold; font-size: 2rem;">Submit your email address and we'll send you password.</font></label>
		 </div>		
        
        <div align="center">
				
		<div class="alert alert-success" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
		<b> <%=ServletUtility.getSuccessMessage(request)%></b>
		</div>
							
		<div class="alert alert-danger" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%;"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
		<b><%=ServletUtility.getErrorMessage(request)%></b>
		</div>
<br>
<input type="hidden" name="id" value="<%=bean.getId()%>">
		</div>
        
        <div class="container" align="center">
						
            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-2" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Email ID<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="login" placeholder="Enter User's Email ID" class="form-control" 
						value="<%=ServletUtility.getParameter("login", request)%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("login", request)%></label>
						</div>
						<div class="col-md-2">
						<button class="btn btn-default" type="submit"	name="operation" value="<%=ForgetPasswordCtl.OP_GO%>">
							<span class="glyphicon glyphicon-ok "></span> <%=ForgetPasswordCtl.OP_GO%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=ForgetPasswordCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-ok "></span> <%=ForgetPasswordCtl.OP_RESET%></button>
							
						</div>
			</div>
			</div>
       
       
        </div>
        </div>
        </div>  
    </div>        
            
    </form>
    <div><%@ include file="Footer.jsp"%></div>

</body>
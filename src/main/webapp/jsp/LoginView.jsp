    <%@page import="in.co.sunrays.proj3.ctl.LoginCtl"%>
	<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
	<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<html>


<head>
<style type="text/css">
.container {
	width: auto;
	max-width: 506px;
	margin: 0 auto;
	background-color:black;
	box-shadow: 0px 0px 0px 1px grey, 0px 7px 10px grey;
}
</style>

</head>


 <form action="<%=ORSView.LOGIN_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
 
        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.UserDTO"
            scope="request"></jsp:useBean>

	<br><br>
	<div class="container" align="center">

           <h1 align="center" style="color: grey">LOGIN</h1>
		     
        	<div class="alert alert-success" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%"
							<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
								<b> <%=ServletUtility.getSuccessMessage(request)%></b>
			</div>
		
			<div class="alert alert-danger" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%;"
								<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
								<b><%=ServletUtility.getErrorMessage(request)%></b>
			</div>
			<hr>
           
				<%
					if (request.getAttribute("message") == null) {
					} else {
				%>
				<font color="red"> <%=request.getAttribute("message")%>
				</font>
				</H2>
				<%
					}
				%>
     
	<div style="margin-left: 525px">
           
              <input type="hidden" name="uri" value="<%=request.getAttribute("targeturi")%>">
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
	</div>
          
          
          <div class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-envelope" style="color: black"></i></span> <input
				id="login" type="text" class="form-control" name="login"
				placeholder="Email ID"
				value="<%=DataUtility.getStringData(bean.getLogin())%>">
		</div>
		<div align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label text-danger">
			<%=ServletUtility.getErrorMessage("login", request)%></label>
		</div>
		<br>
          
        <div class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-lock" style="color: black"></i></span> <input
				id="password" type="password" class="form-control" name="password"
				placeholder="Password">

		</div>   
            
        <div align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("password", request)%></label>
		</div>
		<br>
		
		<div align="center">


			<button type="submit" class="btn btn-default btn-block"
				name="operation" value="<%=LoginCtl.OP_SIGN_IN%>">
				<span class="glyphicon glyphicon-log-in"></span>&nbsp;Login
			</button>
			<br> <a href="<%=ORSView.FORGET_PASSWORD_CTL%>" style="font-size: 18px;" > Forget Password<span
				class="glyphicon glyphicon-question-sign" ></span></a>
		</div>      
             
              
    </div>
    </form>
<div> <%@ include file="Footer.jsp"%></div>   


</html>
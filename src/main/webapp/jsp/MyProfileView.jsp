<%@page import="in.co.sunrays.proj3.ctl.MyProfileCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.UserDTO"
        	scope="request"></jsp:useBean>

    	<div>
    	<div class="row">
    	<div class="col-md-3"></div> 
    	<div class="col-md-6">	
    	<div class="label-primary text-center" >
			<label class="text-center container">
			<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;">My Profile</font></label>
		</div>		
    	<br>
    	<div align="center">
							
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
						<label style="color: grey; font-size: 18; font-weight: normal;">LoginId<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="login" placeholder="Enter Login ID" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getLogin())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("login", request)%></label>
						</div>		
			</div>
			</div>
        
         	<div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">First Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="firstName" placeholder="Enter User's First Name" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
        
        	<div id="secondmidpart" style="margin-top: 20px">
			<div class="row" style="white-space: nowrap;">	
			<div class="col-md-2"></div>								
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Last Name<font color="red">*</font></label>
						</div>			
									
						<div class="col-md-4" align="left">
						<input type="text" name="lastName" placeholder="Enter User's Last Name" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getLastName())%>">			
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
						</div>
			</div>
			</div>
        
        	<div id="secondmidpart" style="margin-top: 10px">
			<div class="row" style="white-space: nowrap;">
			<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Gender<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");
							
							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%>
						<%=htmlList%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("gender", request)%></label>
						</div>
		</div>
		</div>
        
        <div id="secondmidpart" style="margin-top: 20px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Date Of Birth<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<input type="text" name="dob" placeholder="Enter date of birth" class="form-control" id = "datepicker"
						value="<%=DataUtility.getDateString(bean.getDob())%>">
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("dob", request)%></label>
						</div>
		</div>
		</div>
        
        <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Mobile No<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="mobileNo" placeholder="Enter User's MobileNo" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
        
        <div class="row" style="margin-top: 10px;">
        <div class="col-md-3"></div>	
		<div class="col-md-5 col-md-offset-1" align="center">
							
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MyProfileCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=MyProfileCtl.OP_SAVE%></button>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
							<span class="glyphicon glyphicon-ok "></span> <%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%></button>
								
	
		</div>
		</div>
        
        <br>
        
        </div>
    	
    	<br>
    	</div>
    	</div>
    	</div>  
    	
    	<div><%@ include file="Footer.jsp"%></div>
  	
    	
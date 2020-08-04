    
<%@page import="in.co.sunrays.proj3.ctl.CollegeCtl"%>
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

		<form action="CollegeCtl" method="POST">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.CollegeDTO"
            scope="request"></jsp:useBean>

        	
        	 <br><br>
<div>
    
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			 	<%if(bean.getId()>0){%>
        		<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Update College </font></label>
       			<%  }else{ %>
				<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Add College </font></label>
				<%} %>
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
        	
        	
        	
            <input type="hidden" name="id" value="<%=bean.getId()%>"> <input
                type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy"
                value="<%=bean.getModifiedBy()%>"> <input type="hidden"
                name="createdDatetime"
                value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime"
                value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<div class="container" align="center">						
            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">College Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="name" placeholder="Enter College's Name" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getName())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("name", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>

	        <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Address<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="address" placeholder="Enter College's Address" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getAddress())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("address", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>


            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">State<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="state" placeholder="Enter State Name" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getState())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("state", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
			
			
            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">City<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="city" placeholder="Enter College's City" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getCity())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("city", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
			
			
            <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Mobile No.<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="phoneNo" placeholder="Enter College's Mobile No" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("phoneNo", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>

<div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-6 col-md-offset-1" align="center">
							<%if(bean.getId()>0){%> 
							<button class="btn btn-default" type="submit"	name="operation" value="<%=CollegeCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=CollegeCtl.OP_UPDATE%></button>   
							<%  }else{ %>
							<button class="btn btn-default" type="submit"	name="operation" value="<%=CollegeCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=CollegeCtl.OP_SAVE%></button>
							<%} %>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=CollegeCtl.OP_CANCEL%>">
							<span class="glyphicon glyphicon-cancel "></span> <%=CollegeCtl.OP_CANCEL%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=CollegeCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%=CollegeCtl.OP_RESET%></button>
						
		</div>
		</div>
<br>
</div>
<br>
</div>
</div>

 </form>
   
   <div><%@ include file="Footer.jsp"%></div> 

</body>
</html>
    
<%@page import="in.co.sunrays.proj3.ctl.FacultyCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
 <head><style type="text/css">
.container {
	width: auto;
	max-width: 700px;
	margin: 0 auto;
	background-color:black;
	box-shadow: 0px 0px 0px 1px grey, 0px 7px 10px grey;
}
</style></head>   

<form action="<%=ORSView.FACULTY_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.FacultyDTO"
            scope="request"></jsp:useBean>

       <%
       List l1 = (List) request.getAttribute("collegeList");
       List l2 = (List) request.getAttribute("subjectList");
       List l3 = (List) request.getAttribute("courseList");
        %>
<br><br>
<div>
    
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			 	<%if(bean.getId()>0){%>
        		<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Update Faculty </font></label>
       			<%  }else{ %>
				<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Add Faculty </font></label>
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

			<div id="secondmidpart" style="margin-top: 20px">
			<div class="row" style="white-space: nowrap;">
			<div class="col-md-2"></div>	
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Email ID<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<input type="text" name="email" placeholder="Enter Login Id" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>>
 						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("email", request)%></label>
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
						<input type="text" name="mobileNo" placeholder="Enter Faculty's MobileNo" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
						</div>
						<div class="col-md-3"></div>		
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
						<label style="color: grey; font-size: 18; font-weight: normal;">College Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()), l1)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("collegeId", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
			
			
			<div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Subject Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubjectId()), l2)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("subjectId", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
			
			<div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Course Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<%=HTMLUtility.getList("courseId",String.valueOf(bean.getCourseId()), l3)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("courseId", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
			
			<div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-6 col-md-offset-1" align="center">
							<%if(bean.getId()>0){%> 
							<button class="btn btn-default" type="submit"	name="operation" value="<%=FacultyCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=FacultyCtl.OP_UPDATE%></button>   
							<%  }else{ %>
							<button class="btn btn-default" type="submit"	name="operation" value="<%=FacultyCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=FacultyCtl.OP_SAVE%></button>
							<%} %>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
							<span class="glyphicon glyphicon-cancel "></span> <%=FacultyCtl.OP_CANCEL%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=FacultyCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%=FacultyCtl.OP_RESET%></button>
						
		</div>
		</div>
			
<br>
</div>
<br>
</div>
</div>
</div>
    </form>
    <%@ include file="Footer.jsp"%>

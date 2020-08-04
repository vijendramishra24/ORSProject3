    
<%@page import="in.co.sunrays.proj3.ctl.SubjectCtl"%>
<%@page import="in.co.sunrays.proj3.ctl.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
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

<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.SubjectDTO"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("courseList");
                
        %>
        
        
        <br><br>
<div>
    
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			 	<%if(bean.getId()>0){%>
        		<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Update Subject </font></label>
       			<%  }else{ %>
				<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Add Subject </font></label>
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
						<label style="color: grey; font-size: 18; font-weight: normal;">Subject Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="name" placeholder="Enter Role Name" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getName())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("name", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
            
            <div id="secondmidpart" style="margin-top: 20px">
			<div class="row" style="white-space: nowrap;">	
			<div class="col-md-2"></div>								
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Description<font color="red">*</font></label>
						</div>			
									
						<div class="col-md-4" align="left">
						<input type="text" name="description" placeholder="Enter Role Description" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getDescription())%>">			
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("description", request)%></label>
						</div>
			</div>
			</div>
		
			<div id="secondmidpart" style="margin-top: 20px">
			<div class="row" style="white-space: nowrap;">	
			<div class="col-md-2"></div>								
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Subject Code<font color="red">*</font></label>
						</div>			
									
						<div class="col-md-4" align="left">
						<input type="text" name="subjectcode" placeholder="Enter Subject Code" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getSubjectCode())%>">			
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("subjectcode", request)%></label>
						</div>
			</div>
			</div>
	
			<div id="secondmidpart" style="margin-top: 20px">
			<div class="row" style="white-space: nowrap;">	
			<div class="col-md-2"></div>								
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Course Name<font color="red">*</font></label>
						</div>			
									
						<div class="col-md-4" align="left">
						<%=HTMLUtility.getList("courseId",String.valueOf(bean.getCourseId()), l)%>			
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("courseId", request)%></label>
						</div>
			</div>
			</div>
	
	
<div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-6 col-md-offset-1" align="center">
							<%if(bean.getId()>0){%> 
							<button class="btn btn-default" type="submit"	name="operation" value="<%=SubjectCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=SubjectCtl.OP_UPDATE%></button>   
							<%  }else{ %>
							<button class="btn btn-default" type="submit"	name="operation" value="<%=SubjectCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=SubjectCtl.OP_SAVE%></button>
							<%} %>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
							<span class="glyphicon glyphicon-cancel "></span> <%=SubjectCtl.OP_CANCEL%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=SubjectCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%=SubjectCtl.OP_RESET%></button>
						
		</div>
		</div>

		<br>
		</div>	
 <br>
 </div>
 
 </div>
 </div>       
</form>
<div>    <%@ include file="Footer.jsp"%></div>
        
        
                     
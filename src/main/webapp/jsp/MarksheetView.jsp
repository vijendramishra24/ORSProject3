    
<%@page import="in.co.sunrays.proj3.ctl.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
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
<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.MarksheetDTO"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("studentList");
        %>

   <br><br>
<div>
    
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			 	<%if(bean.getId()>0){%>
        		<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Update Marksheet </font></label>
       			<%  }else{ %>
				<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Add Marksheet </font></label>
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
						<label style="color: grey; font-size: 18; font-weight: normal;">Roll No<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="rollNo" placeholder="eg. 0837EC11MT18" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>>		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("rollNo", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
       
       <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Student Name<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<%=HTMLUtility.getList("studentId",String.valueOf(bean.getStudentId()), l)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("studentId", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
       
       <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Physics<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="physics" placeholder="Enter Physis Marks" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getPhysics())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("physics", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
       
        <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Chemistry<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="chemistry" placeholder="Enter Chemistry Marks" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getChemistry())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("chemistry", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
       
        <div id="secondmidpart" style="margin-top: 20px;">
			<div class="row" style="white-space: nowrap;">	
						<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Maths<font color="red">*</font></label>
						</div>
										
						<div class="col-md-4">
						<input type="text" name="maths" placeholder="Enter Maths Marks" class="form-control" 
						value="<%=DataUtility.getStringData(bean.getMaths())%>">		
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("maths", request)%></label>
						</div>
						<div class="col-md-3"></div>		
			</div>
			</div>
       
       <div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-6 col-md-offset-1" align="center">
							<%if(bean.getId()>0){%> 
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MarksheetCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=MarksheetCtl.OP_UPDATE%></button>   
							<%  }else{ %>
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MarksheetCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=MarksheetCtl.OP_SAVE%></button>
							<%} %>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
							<span class="glyphicon glyphicon-cancel "></span> <%=MarksheetCtl.OP_CANCEL%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=MarksheetCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%=MarksheetCtl.OP_RESET%></button>
						
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
    
<%@page import="in.co.sunrays.proj3.ctl.SubjectListCtl"%>
<%@page import="in.co.sunrays.proj3.ctl.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.dto.SubjectDTO"%>
<%@page import="in.co.sunrays.proj3.dto.CourseDTO"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.model.CourseModelInt"%>
<%@page import="in.co.sunrays.proj3.model.SubjectModelInt"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.model.UserModelInt"%>
<%@page import="java.util.Iterator"%>

<center>
<%@include file="Header.jsp"%>
<script type="text/javascript">
		function checkAll(bx) {
			var form = bx.form;
			var isChecked = bx.checked;
			for (var i = 0; i < form.length; i++) {
				if (form[i].type == 'checkbox') {
					form[i].checked = isChecked;
				}
			}
		}
	</script>
 		<%
 			List l = (List) request.getAttribute("courseList");
   		%>
   <jsp:useBean id="bean" scope="request" class="in.co.sunrays.proj3.dto.SubjectDTO"></jsp:useBean>
           
<h1><font style=" color:grey;  font-style: italic; font-weight: bold;">Subject List</font></h1>

        <form class="form-inline"  action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post" >
           
           
           <table class="table" >
				<tr align="center">
					<td style="border-color: transparent;">
					
          			<label><font style="color: grey; font-size: 18; font-weight: normal;">Subject Name :</font></label>
					<input class="form-control" type="text" name="name" placeholder="Search By Subject Name" style="font-style: italic; border-color: blue;"
						value="<%=ServletUtility.getParameter("name", request)%>" >	
						
						&emsp;
						<label><font style="color: grey; font-size: 18; font-weight: normal;">Course Name : </font></label>
					<%=HTMLUtility.getList("courseList", String.valueOf(bean.getCourseId()), l)%>
						
						&emsp;
						<button class="btn btn-default " type="submit" name="operation" value="<%=SubjectListCtl.OP_SEARCH%>">
							<span class="glyphicon glyphicon-search"></span> <%= SubjectListCtl.OP_SEARCH%></button>
						
						&emsp; 
						<button class="btn btn-default " type="submit" name="operation" value="<%=SubjectListCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%= SubjectListCtl.OP_RESET%></button>
						
					</td>
				</tr>
			</table>
           
           <tr>
				<%
					if (ServletUtility.getSuccessMessage(request) != null
							&& ServletUtility.getSuccessMessage(request).length() > 0) {
				%>

				<div class="alert alert-success text-center">
					<strong><%=ServletUtility.getSuccessMessage(request)%></strong>
				</div>

				<%
					}
					if (ServletUtility.getErrorMessage(request) != null
							&& ServletUtility.getErrorMessage(request).length() > 0) {
				%>

				<div class="alert alert-danger text-center">
					<strong><%=ServletUtility.getErrorMessage(request)%></strong>
				</div>

				<%
					}
				%>

			</tr>


 	<%
					List list1 = ServletUtility.getList(request);
					if(list1.size() == 0){
				%>
					<td style="border-color: white;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=SubjectListCtl.OP_ADD%>">
					<span class="glyphicon glyphicon-plus"> </span> <%=SubjectListCtl.OP_ADD%></button>
					</td>
				<%
					}else{
				%>
				
				<div class="table-responsive">
				
			<table  class="table table-hover"  style="border-width: medium; border-color: grey; background-color: grey;">
			<thead>
				<tr align="center" style="border: solid;">
				
						
						<th style="text-align: center;"><input type="checkbox" onclick="checkAll(this)"> Select All</th>
					<th style="text-align: center;">S. No.</th>
					<th style="text-align: center;;">Subject Name</th>
					<th style="text-align: center;">Description</th>
					<th style="text-align: center;">Subject Code</th>
					<th style="text-align: center;">Course Name</th>
					<th style="text-align: center;">Edit</th>
				</tr>

			</thead>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<SubjectDTO> it = list.iterator();
					while (it.hasNext()) {
						 bean = it.next();
				%>
				
				 <%
                CourseModelInt course = ModelFactory.getInstance().getCourseModel();
                int courseId = bean.getCourseId();
                CourseDTO coursebean = course.findByPK(courseId);
                coursebean.getName();
                %>
               
				<tbody>
				<tr>
					<td align="center" style="border-color: black;"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
           			<td align="center" style="border-color: black;"><%=index++%></td>
					<td align="center" style="border-color: black;"><%=bean.getName()%></td>
					<td align="center" style="border-color: black;"><%=bean.getDescription()%></td>
					<td align="center" style="border-color: black;"><%=bean.getSubjectCode()%></td>
					<td align="center" style="border-color: black;"><%=coursebean.getName()%></td>
					<td align="center" style="border-color: black;"><a href="SubjectCtl?id=<%=bean.getId()%>"> 
					<span class="glyphicon glyphicon-pencil"></span></a></td>
					
				</tr>
				<%
					}
				%>
				</tbody>
			</table>
			
			
			<table class="table" >
				<tr >
				
					<td  style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=SubjectListCtl.OP_PREVIOUS%>" <%=(pageNo <= 1) ? "disabled" : ""%>> 
					<span class="glyphicon glyphicon-menu-left"> </span> <%= SubjectListCtl.OP_PREVIOUS%></button>
					</td>
					
					
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=SubjectListCtl.OP_ADD%>">
					<span class="glyphicon glyphicon-plus"> </span> <%= SubjectListCtl.OP_ADD%></button>
					</td>
					
					
					
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=SubjectListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>><span class="glyphicon glyphicon-trash"></span> <%=SubjectListCtl.OP_DELETE%></button>
					</td>
					
					
					

					<%
					
					 CourseModelInt roleModel = ModelFactory.getInstance().getCourseModel();
					 
					if (list.size()< pageSize) {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" disabled="disabled" value="<%=SubjectListCtl.OP_NEXT%>">
					<%=SubjectListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span> </button>
					<%
						} else {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation"  value="<%=SubjectListCtl.OP_NEXT%>">
					<%=SubjectListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span></button>
					<%
						}
					%>
					</td>
				</tr>
			</table>
			</div>
			
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		
		<%} %>
		</form>
	</center>
	<%@include file="Footer.jsp"%>
	


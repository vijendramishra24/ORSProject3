<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.model.UserModelInt"%>
<%@page import="in.co.sunrays.proj3.model.RoleModelInt"%>

<%@page import="in.co.sunrays.proj3.ctl.UserListCtl"%>
<%@page import="in.co.sunrays.proj3.ctl.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>



	<%
		List l = (List) request.getAttribute("roleList");
	%>
	<center>
	<%@ include file="Header.jsp"%>
	
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
	
	
	<jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.UserDTO"
				scope="request"></jsp:useBean>
				


<h1><font style=" color:white;  font-weight: italic; font-weight: bold;">User List</font></h1>
	
		<form class="form-inline"  action="<%=ORSView.USER_LIST_CTL%>" method="post" >
			
			
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
			
	
			<table class="table" >
				<tr align="center">
					<td style="border-color: transparent;">
					

					<label><font style="color:white; font-size: 18; font-weight: normal;">Role Name : </font></label>
					<%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%>
						
						&emsp;
						<label><font style="color:white; font-size: 18; font-weight: normal;">First Name :</font></label>
					<input class="form-control" type="text" name="firstName" placeholder="Search By First Name" style="font-style: italic; border-color: blue;"
						value="<%=ServletUtility.getParameter("firstName", request)%>" >	
						
						&emsp;
						<label><font style="color:white; font-size: 18; font-weight: normal;">Login ID : </font></label>
					<input class="form-control" type="text" name="login" placeholder="Search By Email ID" style="font-style: italic; border-color: blue;"
						value="<%=ServletUtility.getParameter("login", request)%>" >	
						
						
						&emsp; 
						<button class="btn btn-default " type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH%>">
							<span class="glyphicon glyphicon-search"></span> <%= UserListCtl.OP_SEARCH%></button>
						
						&emsp; 
						<button class="btn btn-default " type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%= UserListCtl.OP_RESET%></button>
						
					</td>
				</tr>
			</table>

				<%
					List list1 = ServletUtility.getList(request);
					if(list1.size() == 0){
				%>
					<td style="border-color: white;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=UserListCtl.OP_NEW%>">
					<span class="glyphicon glyphicon-plus"> </span> <%= UserListCtl.OP_NEW%></button>
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
					<th style="text-align: center;;">First Name</th>
					<th style="text-align: center;">Last Name</th>
					<th style="text-align: center;">Login ID</th>
					<th style="text-align: center;">Role</th>
					<th style="text-align: center;">Gender</th>
					<th style="text-align: center;">DOB</th>
					<th style="text-align: center;">Edit</th>
				</tr>

			</thead>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<UserDTO> it = list.iterator();
					while (it.hasNext()) {
						 bean = it.next();
				%>
				<tbody>
				<tr>
				<%
						if (bean.getRoleId() == 1) {
					%>

					<td align="center" style="border-color: black;"><input type="checkbox" disabled="disabled"></td>
					
					<%
						} else {
					%>
					<td align="center" style="border-color: black;"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<%
						}
					%>
				
					<%
                RoleModelInt subject = ModelFactory.getInstance().getRoleModel();
                long roleId = bean.getRoleId();
                RoleDTO rolebean = subject.findByPK(roleId);
                rolebean.getName();
                %>
					<td align="center" style="border-color: black;"><%=index++%></td>
					<td align="center" style="border-color: black;"><%=bean.getFirstName()%></td>
					<td align="center" style="border-color: black;"><%=bean.getLastName()%></td>
					<td align="center" style="border-color: black;"><%=bean.getLogin()%></td>
					<td align="center" style="border-color: black;"><%=rolebean.getName()%></td>					
					<td align="center" style="border-color: black;"><%=bean.getGender()%></td>
					<td align="center" style="border-color: black;"><%=DataUtility.getDateString(bean.getDob())%></td>
					
					<%
						if (bean.getRoleId() == 1) {
					%>

					<td align="center" style="border-color: black;">---</td>
					<%
						} else {
					%>
					<td align="center" style="border-color: black;"><a href="UserCtl?id=<%=bean.getId()%>"> 
					<span class="glyphicon glyphicon-pencil"></span></a></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
				</tbody>
			</table>
			
			<table class="table" >
				<tr >
				
					<td  style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=UserListCtl.OP_PREVIOUS%>" <%=(pageNo <= 1) ? "disabled" : ""%>> 
					<span class="glyphicon glyphicon-menu-left"> </span> <%= UserListCtl.OP_PREVIOUS%></button>
					</td>
					
					
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=UserListCtl.OP_NEW%>">
					<span class="glyphicon glyphicon-plus"> </span> <%= UserListCtl.OP_NEW%></button>
					</td>
					
					
					
					
					
					
					
					
					<%
						if (bean.getRoleId() == 1) {
					%>

					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-primary" name="operation" value="<%=UserListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>><span class="glyphicon glyphicon-trash"></span> <%= UserListCtl.OP_DELETE%></button>
					</td>
					
					
					

					<%
						}
					 UserModelInt roleModel = ModelFactory.getInstance().getUserModel();
					 
					if (list.size()< pageSize) {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" disabled="disabled" value="<%=UserListCtl.OP_NEXT%>">
					<%=UserListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span> </button>
					<%
						} else {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation"  value="<%=UserListCtl.OP_NEXT%>">
					<%=UserListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span></button>
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

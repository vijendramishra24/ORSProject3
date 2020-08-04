    
<%@page import="in.co.sunrays.proj3.ctl.RoleListCtl"%>
<%@page import="in.co.sunrays.proj3.ctl.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.model.RoleModelInt"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
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

<jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.RoleDTO"
				scope="request"></jsp:useBean>
	   
       
	<h1><font style=" color:grey;  font-style: italic; font-weight: bold;">Role List</font></h1>

        <form class="form-inline"  action="<%=ORSView.ROLE_LIST_CTL%>" method="post" >
      
      
            <table class="table" >
				<tr align="center">
					<td style="border-color: transparent;">
					
          			<label><font style="color: grey; font-size: 18; font-weight: normal;">Role Name :</font></label>
					<input class="form-control" type="text" name="name" placeholder="Search By Role Name" style="font-style: italic; border-color: blue;"
						value="<%=ServletUtility.getParameter("name", request)%>" >	
						
						&emsp;
						<button class="btn btn-default " type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH%>">
							<span class="glyphicon glyphicon-search"></span> <%= RoleListCtl.OP_SEARCH%></button>
						
						&emsp; 
						<button class="btn btn-default " type="submit" name="operation" value="<%=RoleListCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%= RoleListCtl.OP_RESET%></button>
						
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
					<button type="submit" class="btn btn-default" name="operation" value="<%=RoleListCtl.OP_NEW%>">
					<span class="glyphicon glyphicon-plus"> </span> <%= RoleListCtl.OP_NEW%></button>
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
					<th style="text-align: center;;">Role Name</th>
					<th style="text-align: center;">Description</th>
					<th style="text-align: center;">Edit</th>
				</tr>

			</thead>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<RoleDTO> it = list.iterator();
					while (it.hasNext()) {
						 bean = it.next();
				%>
				<tbody>
				<tr>
					<td align="center" style="border-color: black;"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
           			<td align="center" style="border-color: black;"><%=index++%></td>
					<td align="center" style="border-color: black;"><%=bean.getName()%></td>
					<td align="center" style="border-color: black;"><%=bean.getDescription()%></td>
					<td align="center" style="border-color: black;"><a href="RoleCtl?id=<%=bean.getId()%>"> 
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
					<button type="submit" class="btn btn-default" name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>" <%=(pageNo <= 1) ? "disabled" : ""%>> 
					<span class="glyphicon glyphicon-menu-left"> </span> <%= RoleListCtl.OP_PREVIOUS%></button>
					</td>
					
					
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=RoleListCtl.OP_ADD%>">
					<span class="glyphicon glyphicon-plus"> </span> <%= RoleListCtl.OP_ADD%></button>
					</td>
					
					
					
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" value="<%=RoleListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>><span class="glyphicon glyphicon-trash"></span> <%= RoleListCtl.OP_DELETE%></button>
					</td>
					
					
					

					<%
					
					 RoleModelInt roleModel = ModelFactory.getInstance().getRoleModel();
					 
					if (list.size()< pageSize) {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation" disabled="disabled" value="<%=RoleListCtl.OP_NEXT%>">
					<%=RoleListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span> </button>
					<%
						} else {
					%>
					<td style="border-color: transparent;" align="center">
					<button type="submit" class="btn btn-default" name="operation"  value="<%=RoleListCtl.OP_NEXT%>">
					<%=RoleListCtl.OP_NEXT %> <span class="glyphicon glyphicon-menu-right"></span></button>
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
	
            
      
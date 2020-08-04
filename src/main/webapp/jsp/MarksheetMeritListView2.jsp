    
<%@page import="in.co.sunrays.proj3.ctl.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.sunrays.proj3.dto.StudentDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.model.StudentModelInt"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>

<center>

<%@include file="Header.jsp"%>
<jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.MarksheetDTO"
				scope="request"></jsp:useBean>

    <h1><font style=" color:grey;  font-style: italic; font-weight: bold;">Marksheet Merit List</font></h1>
       
        <form class="form-inline"  action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post" >
 
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
			
			
			<table  class="table table-hover"  style="border-width: medium; border-color: grey; background-color: grey;">
			<thead>
				<tr align="center" style="border: solid;">
				
					<th style="text-align: center;">S. No.</th>
					<th style="text-align: center;;">Roll No</th>
					<th style="text-align: center;">Name</th>
					<th style="text-align: center;">Physics</th>
					<th style="text-align: center;">Chemistry</th>
					<th style="text-align: center;">Maths</th>
					<th style="text-align: center;">Total</th>
					<th style="text-align: center;">Percentage</th>
					
				</tr>

			</thead>
			
           
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetDTO> it = list.iterator();

                    while (it.hasNext()) {

                        bean = it.next();
                %>
                <%
                StudentModelInt subject = ModelFactory.getInstance().getStudentModel();
                long studentId = bean.getStudentId();
                StudentDTO studentbean = subject.findByPK(studentId);
                studentbean.getFirstName();
                %>
               
                <tbody>
				<tr>
					<td align="center" style="border-color: black;"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
           			<td align="center" style="border-color: black;"><%=index++%></td>
           			<td align="center" style="border-color: black;"><%=bean.getRollNo()%></td>
					<td align="center" style="border-color: black;"><%=studentbean.getFirstName()%></td>
					<%if(bean.getPhysics()<40){ %>
					<td align="center" style="border-color: black;"><font color="red"><%=bean.getPhysics()%></font></td>
					<%}else{ %>
					<td align="center" style="border-color: black;"><%=bean.getPhysics()%></td>
					<%}if(bean.getChemistry()<40){ %>
					<td align="center" style="border-color: black;"><font color="red"><%=bean.getChemistry()%></font></td>
					<%}else{ %>
					<td align="center" style="border-color: black;"><%=bean.getChemistry()%></td>
					<%}if(bean.getMaths()<40){ %>
					<td align="center" style="border-color: black;"><font color="red"><%=bean.getMaths()%></font></td>
					<%}else{ %>
					<td align="center" style="border-color: black;"><%=bean.getMaths()%></td>
					<%} %>
					<td align="center" style="border-color: black;"><%=(bean.getMaths()+bean.getChemistry()+ bean.getPhysics())%>/300</td>
					<td align="center" style="border-color: black;"><%=(bean.getMaths()+bean.getChemistry()+ bean.getPhysics())/3%>%</td>
					
				</tr>
				<%
					}
				%>
				</tbody>
			</table>  
               
               <table class="table" >
				<tr >
				
					<%
						if(list.size()>0){
					%>
					<td style="border-color: transparent;" align="center">
					<a href="<%=ORSView.JASPER_CTL%>" target="_blank" class="btn btn-primary">
				Print  <span class="glyphicon glyphicon-print" target="_blank"></span></a>
					<%
						}
					%> 
				 
				
				</td>
					
               </tr>
               
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>

<%@page import="in.co.sunrays.proj3.ctl.GetMarksheetCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataValidator"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.model.StudentModelInt"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.dto.StudentDTO"%>
<%@page import="java.text.DecimalFormat" %>


	<%@ include file="Header.jsp"%>

	<center>

		<jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.MarksheetDTO"
			scope="request"></jsp:useBean>


		<h1>
			<font style="color: grey; font-style: italic; font-weight: bold;">Get
				Marksheet</font>
		</h1>

		<h2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</h2>

		<form class="form-inline" action="<%=ORSView.GET_MARKSHEET_CTL%>"
			method="post">


			<table class="table">
				<tr align="center">
					<td style="border-color: transparent;">&emsp; <label><font
							style="color: grey; font-size: 18; font-weight: normal;">Roll
								No.<font color="red">*</font> :
						</font></label> <input class="form-control" type="text" name="rollNo"
						placeholder="Enter Roll No."
						style="font-style: italic; border-color: grey;"
						value="<%=ServletUtility.getParameter("rollNo", request)%>">


						&emsp;
						<button class="btn btn-default" type="submit" name="operation"
							value="<%=GetMarksheetCtl.OP_GO%>">
							<span class="glyphicon glyphicon-right"></span>
							<%=GetMarksheetCtl.OP_GO%>
						</button> <br> <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>

					</td>
				</tr>
			</table>
			<%
				if (bean.getRollNo() != null && bean.getRollNo().trim().length() > 0
						&& DataValidator.isRollNo(bean.getRollNo())) {
			%>


			<br>
			<div>
				<table border="1"
					style="width: 50%; box-shadow: 0 1px 15px black; background: white; border: 2px black;">

					<tr>
						<td style="height: 45px; color: blue; font-weight: bold;"
							colspan="4" align="center">MARKSHEET</td>

					</tr>
                <%
                StudentModelInt subject = ModelFactory.getInstance().getStudentModel();
                long studentId = bean.getStudentId();
                StudentDTO studentbean = subject.findByPK(studentId);
                studentbean.getFirstName();
                %>

					<tr>
						<th style="height: 35px; text-align: center;">Roll Number:</th>
						<td align="center" width="30%"><%=DataUtility.getStringData(bean.getRollNo())%></td>

						<th style="text-align: center;">Name:</th>
						<td align="center" width="30%"><%=studentbean.getFirstName()%></td>
					</tr>


					<tr>
						<td colspan="4"></td>
					</tr>

					<tr>
						<th style="height: 35px; text-align: center;">Subject</th>
						<th style="text-align: center;">Max. Marks</th>
						<th style="text-align: center;">Marks Obtained</th>
						<th style="text-align: center;">Remark</th>
					</tr>

					<tr>
						<td align="center" style="height: 35px">Physics</td>
						<td align="center">100</td>
						<td align="center"><%=bean.getPhysics()%></td>
						<%
							if (bean.getPhysics() < 35) {
						%>
						<td align="center"><font color="red"> *F </font></td>
						<%
							} else if (bean.getPhysics() > 75) {
						%>
						<td align="center"><font style="color: lime;"> D </font></td>
						<%
							} else {
						%>
						<td align="center">-</td>
						<%
							}
						%>
					</tr>

					<tr>
						<td align="center" style="height: 35px">Chemistry</td>
						<td align="center">100</td>
						<td align="center"><%=bean.getChemistry()%></td>
						<%
							if (bean.getChemistry() < 35) {
						%>
						<td align="center"><font color="red"> *F </font></td>
						<%
							} else if (bean.getChemistry() > 75) {
						%>
						<td align="center"><font style="color: lime;"> D </font></td>
						<%
							} else {
						%>
						<td align="center">-</td>
						<%
							}
						%>
					</tr>

					<tr>
						<td align="center" style="height: 35px">Math</td>
						<td align="center">100</td>
						<td align="center"><%=bean.getMaths()%></td>
						<%
							if (bean.getMaths() < 35) {
						%>
						<td align="center"><font color="red"> *F </font></td>
						<%
							} else if (bean.getMaths() > 75) {
						%>
						<td align="center"><font style="color: lime;"> D </font></td>
						<%
							} else {
						%>
						<td align="center">-</td>
						<%
							}
						%>
					</tr>

					<tr>
						<td colspan="4"></td>
					</tr>
					<tr>
						<th style="height: 35px; text-align: center;">Total Marks</th>
						<th style="text-align: center;">Marks Obtained</th>
						<th style="text-align: center;">Percentage</th>
						<th style="text-align: center;">Result</th>

					</tr>

					<tr>
						<td align="center" style="height: 35px">300</td>
						<td align="center"><%=bean.getPhysics() + bean.getChemistry() + bean.getMaths()%>
						</td>


						<%
							float phy = Float.parseFloat(DataUtility.getStringData(bean.getPhysics()));
								float chem = Float.parseFloat(DataUtility.getStringData(bean.getChemistry()));
								float mat = Float.parseFloat(DataUtility.getStringData(bean.getMaths()));

								float perc = (phy + chem + mat) / 3;
								if (perc < 33) {
						%>
						<td align="center"><%=Float.parseFloat(new DecimalFormat("##.##").format(perc))%>
							%</td>
						<td align="center"><font color="red">Fail</font></td>

						<%
							} else {
						%>
						<td align="center"><%=Float.parseFloat(new DecimalFormat("##.##").format(perc))%>
							%</td>
						<td align="center"><font style="color: lime;" size="4">Pass</font></td>
						<%
							}
						%>
					</tr>

				</table>
				<%
					}
				%>

			</div>

		</form>
	</center>
	<%@ include file="Footer.jsp"%>
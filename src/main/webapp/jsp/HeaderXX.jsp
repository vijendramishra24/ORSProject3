<%@page import="in.co.sunrays.proj3.ctl.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO" %>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO" %>
<%@page import="in.co.sunrays.proj3.ctl.LoginCtl"%>


<script type="text/javascript"></script>
 <script type="text/javascript" src="./js/jquery-1.12.4.js"></script> 
  <script type="text/javascript" src="./js/date.js"></script> 


<script type="text/javascript" src="./js/jquery.min.js"></script>
    
<link rel="stylesheet" href="<%=ORSView.APP_CONTEXT%>/css/jquery-ui.css">

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery-1.12.4.js"></script>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			
			dateFormat : 'yy/mm/dd',
			defaultDate : "2018/08/24",
			changeMonth : true,
			changeYear : true,
			yearRange : '-35:+0',
			maxDate : '-1'
			
		});
	});
</script>

<style>

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.header {
  padding: 5px 8px;
  background-color: black;
  color: white;
  
}

a:link {
    color: white;
}

/* visited link */
a:visited {
    color: white;
}

/* mouse over link */
a:hover {
    color: blue;
}

/* selected link */
a:active {
    color: blue;
}

</style>

<div class="header" id="myHeader">


<%
	UserDTO userBean = (UserDTO) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userBean.getFirstName()+" "+userBean.getLastName() + " (" + role + ")";
	} else {
		welcomeMsg += " Guest";
	}
%>

<table width="100%" border="0">
	<tr>
		<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
			<%
			if (userLoggedIn) {
		%> <a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a>

			<%
				} else {
			%> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
 	}
 %></td>
		<td rowspan="2">
			<h1 align="left" position="fixed">
				<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.png" width="230"
					height="76">
			</h1>
		</td>

	</tr>

	<tr>
		<td>
			<font color="white"><h3><%=welcomeMsg%></h3></font>
		</td>
	</tr>


	<%
		if (userLoggedIn) {
	%>

	
	<tr>
		<td colspan="2"><a href="<%=ORSView.MY_PROFILE_CTL%>">My
				Profile</b>
		</a> <font color="white">|</font> <a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a> <font color="white">|</font><%
			if (userBean.getRoleId() == 2) {
		%> <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</b></a>
			<font color="white">|</font> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> <font color="white">|</font><%
 	}
 %> <%
 	if (userBean.getRoleId() == 5) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> <font color="white">|</font> <a
			href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> <font color="white">|</font> <%
 	}
 %> <%
 	if (userBean.getRoleId() == 3) {
 %> <a href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> <font color="white">|</font> <a
			href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</b></a><font color="white">|</font>
			<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> <font color="white">|</font> <%
 	}
 %> <%
 	if (userBean.getRoleId() == 4) {
 %> <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</b></a>
			<font color="white">|</font> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> <%
 	}
 %> <%
 	if (userBean.getRoleId() == 1) {
 %> <a href="<%=ORSView.USER_CTL%>">Add User</b></a> <font color="white">|</font> <a
			href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> <font color="white">|</font> <a
			href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> <font color="white">|</font> <a
			href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> <font color="white">|</font> <a
			href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.COURSE_CTL%>">Add Course</b></a> <font color="white">|</font> <a
			href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> <font color="white">|</font> <a
			href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a><font color="white">|</font> <a
			href="<%=ORSView.FACULTY_CTL%>">Add Faculty</b></a> <font color="white">|</font> <a
			href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> <font color="white">|</font> <a
			href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</b></a><font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> <font color="white">|</font> <a
			href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> <font color="white">|</font> <a
			href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> <font color="white">|</font> <a
			href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">Java Doc</b></a> <%
 	}
 %></td>

	</tr>
	<%
		}
	%>
	
</table>
</div>



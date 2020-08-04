
<%@page import="in.co.sunrays.proj3.ctl.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO" %>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO" %>
<%@page import="in.co.sunrays.proj3.ctl.LoginCtl"%>



<head>
	
<!-- script from w3 schools

 -->
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


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
body  {
    background-image: url("/ORSProject3/img/v2.jpg");
    background-color: #cccccc;
    
    }
}
</style>
<!--
<style type="text/css">

.background {
	background-color: background;
}

.box-shadow--6dp {
	box-shadow: 0 6px 10px 0 rgba(0, 0, .14, 0), 0 1px 18px 0
		rgba(0, 0, .12, 0), 0 3px 5px rgba(0, 0,.2 ,0 )
}

.sidenav {
	padding-top: 20px;
	background-color: transparent;
}
/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>


  -->



<!--.dropdown-menu {
	background-color: black;
	opacity : .8;
	text-color: white;-->

<body>
<div>
<%
	UserDTO userBean = (UserDTO) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userBean.getFirstName()+" "+"(" + role + ")";
	} else {
		welcomeMsg += " Guest";
	}
%>
<!-- <table width="100%" border="0">
	<tr>
			<h1 align="left" position="fixed">
				<img src="/ORSProject3/img/customLogo.jpg" width="130"
					height="56">
			</h1>
	</tr>
	</table>
 -->
</div>

<!-- navbar-fixed-top -->
 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="<%=ORSView.MY_PROFILE_CTL%>"><%=welcomeMsg%></a>
	 
	<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"><span class="glyphicon glyphicon-home"></span></a>
    </div>
    <%
		if (userLoggedIn) {
	%>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
      
      <%if (userBean.getRoleId() == 1) {%>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown">User
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MY_PROFILE_CTL%>"><span class="glyphicon glyphicon-user"></span> My Profile</a></li>
          <li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><span class="glyphicon glyphicon-lock"></span> Change Password</a></li>
          <li><a href="<%=ORSView.USER_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add User</a></li>
          <li><a href="<%=ORSView.USER_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> User List</a></li>
        </ul>
      </li>
     
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">College
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.COLLEGE_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add College</a></li>
          <li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> College List</a></li>
        </ul>
      </li>
      
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Role
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.ROLE_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Role</a></li>
          <li><a href="<%=ORSView.ROLE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Role List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Subject
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.SUBJECT_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Subject</a></li>
          <li><a href="<%=ORSView.SUBJECT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Subject List</a></li>
        </ul>
      </li>
      
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Course
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.COURSE_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Course</a></li>
          <li><a href="<%=ORSView.COURSE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Course List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Faculty
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.FACULTY_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Faculty</a></li>
          <li><a href="<%=ORSView.FACULTY_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Faculty List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Student
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.STUDENT_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Student</a></li>
          <li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Student List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Timetable
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.TIMETABLE_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Timetable</a></li>
          <li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Timetable List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Marksheet
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MARKSHEET_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet List</a></li>
          <li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span class="glyphicon glyphicon-download-alt"></span> Get Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet Merit List</a></li>
        </ul>
      </li>
      <li><a href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank"><span class="glyphicon glyphicon-file"></span> Java Doc</a></li>
    </ul>
      
      <%} %>
	  
	  <%if (userBean.getRoleId() == 2) {%>
	  <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown">User
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a></li>
          <li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Marksheet
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MARKSHEET_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet List</a></li>
          <li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span class="glyphicon glyphicon-download-alt"></span> Get Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet Merit List</a></li>
        </ul>
      </li>
	  
      <%} %>
		
	  <%if (userBean.getRoleId() == 3) {%>
	  
	  <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Student
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.STUDENT_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Student</a></li>
          <li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Student List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Timetable
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Timetable List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Marksheet
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet List</a></li>
          <li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span class="glyphicon glyphicon-download-alt"></span> Get Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet Merit List</a></li>
        </ul>
      </li>
      <%} %>
      
      <%if (userBean.getRoleId() == 4) {%>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">College
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> College List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Marksheet
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.MARKSHEET_CTL%>"><span class="glyphicon glyphicon-plus"></span> Add Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet List</a></li>
          <li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span class="glyphicon glyphicon-download-alt"></span> Get Marksheet</a></li>
          <li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Marksheet Merit List</a></li>
        </ul>
      </li>
      
      <%} %>
      
      <%if (userBean.getRoleId() == 5) {%>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">College
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> College List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Student
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span class="glyphicon glyphicon-list"></span> Student List</a></li>
        </ul>
      </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown">Marksheet
      <span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span class="glyphicon glyphicon-download-alt"></span> Get Marksheet</a></li>
         </ul>
      </li>
      <%} %>
      
    </ul>
    <%} %>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="<%=ORSView.USER_REGISTRATION_CTL%>"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      
      <%
			if (userLoggedIn) {
		%> <li><a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li><%	} else { %> 
		<li><a href="<%=ORSView.LOGIN_CTL%>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li> <%} %>
      
      
    </ul>
    </div>
  </div>
</nav>

</body>



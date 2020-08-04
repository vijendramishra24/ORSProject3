
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.co.sunrays.proj3.ctl.ORSView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="white">
 
<form action="<%=ORSView.ERROR_CTL%>">
<center>

<img  src="<%=ORSView.APP_CONTEXT%>/img/customLogo.png" width="500"
					height="250">
					<font color="black" ><h1>Oops!!!  Something went wrong!!!</h1></font>
					<a href="<%=ORSView.WELCOME_CTL%>">Back</b></a> 
					
</center>
</form>
</body>
</html>
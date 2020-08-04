<%@page import="in.co.sunrays.proj3.ctl.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO" %>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO" %>


<html>
<body>

<form action="<%=ORSView.WELCOME_CTL%>">
        <%@ include file="Header.jsp"%>
                    <h1 align="Center">
                    <br><br><br>
                        <font size="15px" color="grey">Welcome to ORS </font>
                    </h1>
        
                    <%
                    UserDTO beanUserBean = (UserDTO) session.getAttribute("user");
                        if (beanUserBean != null) {
                            if (beanUserBean.getRoleId() == RoleDTO.STUDENT) {
                    %>
        
                    <h2 align="Center">
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your Mark sheet </a>
                    </h2>
                     
                     <%
                            }
                        }
                     %>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                </form>
        
<div><%@ include file="Footer.jsp"%>
</div> 

</body>
</html>
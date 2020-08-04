<%@page import="in.co.sunrays.proj3.ctl.GetMarksheetCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.model.StudentModelInt"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.dto.StudentDTO"%>
<html>

<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 4px;
}

#customers tr:nth-child(even){background-color: white;}
#customers tr:nth-child(odd){background-color: white;}


#customers th {
    padding-top: 6px;
    padding-bottom: 6px;
    text-align: center;
    background-color: black;
    color: white;
}

</style>
<body>

<%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.MarksheetDTO"
        scope="request"></jsp:useBean>

    <center>
        <h1>Get Marksheet</h1>

        <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
        </font>

        <H2>
            <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
            </font>
        </H2>

        <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            
                <label align="left">RollNo<font color="red">* </font> :</label>&emsp;
                <input type="text" name="rollNo" placeholder="eg. 0837EC11MT18"
                    value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">&emsp;
				<input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_RESET%>">

                <br>
                <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>

                <%
                    if (bean.getRollNo() != null && bean.getRollNo().trim().length() > 0) {
                %>
                <%
                StudentModelInt subject = ModelFactory.getInstance().getStudentModel();
                long studentId = bean.getStudentId();
                StudentDTO studentbean = subject.findByPK(studentId);
                studentbean.getFirstName();
                %>
		<br><br>
		<table id="customers">
                <tr>
                    <td>Rollno :</td>
                    <td><%=DataUtility.getStringData(bean.getRollNo())%></td>
                </tr>
                <tr>
                    <td>Name :</td>
                    <td><%=DataUtility.getStringData(studentbean.getFirstName())%></td>
                </tr>
                <tr>
                    <td>Physics :</td>
                    <%if(bean.getPhysics()<40){ %>
                    <td><font color="red"><%=DataUtility.getStringData(bean.getPhysics())%></font></td>
                    <%}else{ %>
                    <td><%=DataUtility.getStringData(bean.getPhysics())%></td>
                    <%} %>
                </tr>
                <tr>
                    <td>Chemistry :</td>
                    <%if(bean.getChemistry()<40){%>
                    <td><font color="red"><%=DataUtility.getStringData(bean.getChemistry())%></font></td>
                    <%}else{ %>
                    <td><%=DataUtility.getStringData(bean.getChemistry())%></td>
                    <%} %>
                </tr>
                <tr>
                    <td>Maths :</td>
                   <%if(bean.getMaths()<40){ %>
                    <td><font color="red"><%=DataUtility.getStringData(bean.getMaths())%></font></td>
					<%}else{ %>
                    <td><%=DataUtility.getStringData(bean.getMaths())%></td>
                    <%} %>
                </tr>
                 <tr>
                    <td>Total :</td>
                    <td><%=(bean.getMaths()+bean.getChemistry()+bean.getMaths())%>/300</td>
                    
                </tr>
                
                 <tr>
                    <td>Result :</td>
                    
                    <%if((bean.getMaths()<40)||(bean.getChemistry()<40)||(bean.getPhysics()<40)){ %>
                    
                   <td><font color="red">FAIL</font></td><%}else{ %>
                   
                    <td><font color="green">PASS</font></td><%} %>
                </tr>
                
                
                
            </table>
            <%} %>
           
        </form>
    </center>
    
    
    <div><%@include file="Footer.jsp"%></div> 

</body>
</html>
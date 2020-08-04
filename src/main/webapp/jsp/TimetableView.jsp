 <%@page import="in.co.sunrays.proj3.ctl.TimetableCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<head><style type="text/css">
.container {
	width: auto;
	max-width: 700px;
	margin: 0 auto;
	background-color:black;
	box-shadow: 0px 0px 0px 1px grey, 0px 7px 10px grey;
}
</style>
</head>




<!-- JAVA SCRIP FOR CLAENDER -->
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/calendar.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker1").datepicker({
			dateFormat : 'yy/mm/dd',
			changeMonth : true,
			changeYear : true,
			yearRange : 'c:c+1', 
			minDate :'+1'
			
		});
	});
</script>



<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        
        
        
        <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.TimetableDTO"
            scope="request"></jsp:useBean>
		<%
            List l1 = (List) request.getAttribute("courseList");
			List l2 = (List) request.getAttribute("subjectList");
                
        %>

 <br><br>
<div>
   
<div class="row">
    	<div class="col-md-3"></div> 
 		
	 <div class="col-md-6">	
				<div class="label-primary text-center" >
			<label class="text-center container">
			 	<%if(bean.getId()>0){%>
        		<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Update Timetable </font></label>
       			<%  }else{ %>
				<font style="color:grey; font-style: italic; font-weight: bold; font-size: 4rem;"> Add Timetable </font></label>
				<%} %>
			</div>		
		<br>
		<div align="center">
				
		<div class="alert alert-success" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
		<b> <%=ServletUtility.getSuccessMessage(request)%></b>
		</div>
							
		<div class="alert alert-danger" role="alert" style="width: 80%; margin-left: 0%; font-size: 100%;"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
		<b><%=ServletUtility.getErrorMessage(request)%></b>
		</div>

		</div>
       

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


<div class="container" align="center">
		<div id="secondmidpart" style="margin-top: 20px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Course Name<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<%=HTMLUtility.getList("courseId",String.valueOf(bean.getCourseId()), l1)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("courseId", request)%></label>
						</div>
		</div>
		</div>
		
		<div id="secondmidpart" style="margin-top: 10px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Semester<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<%
							HashMap map = new HashMap();
						map.put("I", "I");
                        map.put("II", "II");
                        map.put("III", "III");
                        map.put("IV", "IV");
                        map.put("V", "V");
                        map.put("VI", "VI");
                        map.put("VII", "VII");
                        map.put("VIII", "VIII");
                        map.put("IX", "IX");
                        map.put("X", "X");
                  				
                        String htmlList = HTMLUtility.getList("sem", bean.getSem(), map);
                    %> <%=htmlList%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("sem", request)%></label>
						</div>
		</div>
		</div>
		
		<div id="secondmidpart" style="margin-top: 20px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Subject Name<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubjectId()), l2)%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("subjectId", request)%></label>
						</div>
		</div>
		</div>
		
		
		<div id="secondmidpart" style="margin-top: 20px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4 " align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Date Of Exam<font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<input type="text" name="examDate" readonly="readonly" placeholder="yyyy/mm/dd" class="form-control" id = "datepicker"
						value="<%=DataUtility.getDateString(bean.getExamDate())%>"
								id="datepicker1">
								<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("examDate", request)%></label>
						</div>
		</div>
		</div>
		
		<div id="secondmidpart" style="margin-top: 10px">
		<div class="row" style="white-space: nowrap;">
		<div class="col-md-2"></div>	
						<div class="col-md-4" align="left">
						<label style="color: grey; font-size: 18; font-weight: normal;">Time of Exam <font color="red">*</font></label>
						</div>
						<div class="col-md-4" align="left">
						<%
							HashMap map1 = new HashMap();
						map1.put("08:00 AM - 11:00 AM", "08:00 AM - 11:00 AM");
                        map1.put("11:00 AM - 02:00 PM", "11:00 AM - 02:00 PM");
                        map1.put("02:00 PM - 05:00 PM", "02:00 PM - 05:00 PM");
                          
							String htmlList1 = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
						%>
						<%=htmlList1%>
						<label class="control-label text-danger"><%=ServletUtility.getErrorMessage("examTime", request)%></label>
						</div>
		</div>
		</div>
	
	<div class="row" style="margin-top: 10px;">
        <div class="col-md-2"></div>	
		<div class="col-md-6 col-md-offset-1" align="center">
							<%if(bean.getId()>0){%> 
							<button class="btn btn-default" type="submit"	name="operation" value="<%=TimetableCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=TimetableCtl.OP_UPDATE%></button>   
							<%  }else{ %>
							<button class="btn btn-default" type="submit"	name="operation" value="<%=TimetableCtl.OP_SAVE%>">
							<span class="glyphicon glyphicon-ok "></span> <%=TimetableCtl.OP_SAVE%></button>
							<%} %>
													
							<button class="btn btn-default" type="submit"	name="operation" value="<%=TimetableCtl.OP_CANCEL%>">
							<span class="glyphicon glyphicon-cancel "></span> <%=TimetableCtl.OP_CANCEL%></button>
								
							<button class="btn btn-default" type="submit"	name="operation" value="<%=TimetableCtl.OP_RESET%>">
							<span class="glyphicon glyphicon-refresh "></span> <%=TimetableCtl.OP_RESET%></button>
						
		</div>
		</div>
    
    <br>
    </div>
    	
</div>
</div>
</div>

          </form>
    <%@ include file="Footer.jsp"%>

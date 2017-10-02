<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title">
			<span class="page_title_text">Renew Admission</span>
			<span style="float: right;margin-right: 30px">
				<div style="width: 100%;text-align: center;margin-bottom: 10px">
				Student ID # <input type="text" id="renewalStudentId" value="${student.studentId}" maxlength="13" />&nbsp;&nbsp;&nbsp; 
				<input type="button" class="button"  value="Search" onclick="findRenewalStudent()"/>
				</div>
			</span>
		</div>
		<div id="admission_container" class="working_area_spacer">
			
			<div id="renewalAdmissionInfoContainer">
				
				<c:choose>
					<c:when test="${eligibletorenew==false}">
						<jsp:include page="admission_renewal_error_info.jsp"/>
					</c:when>	
					<c:otherwise>
						<jsp:include page="admission_renewal_info.jsp"/>
					</c:otherwise>
				</c:choose>
				
				
			</div>
		</div>
		
	</div>
</div>	
<jsp:include page="sams_footer.jsp"/>

<script type="text/javascript">

var course_url="<c:url value='/course' />";
var admission_renewal_base_url="<c:url value='/admission/renewadmission' />";
var student_fee_url="<c:url value='/fee'/>";
var btnStudentSaveAction="${action}";
var ACTION_UPDATE="update";
var ACTION_ADD="add";
var image_base_url="<c:url value='/resources'/>";


$(function() {
	$( "#admissionDob" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
});

</script>
        	 
       
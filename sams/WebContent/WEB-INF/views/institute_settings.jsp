<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
<div id="content_area">


	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Institute settings</span></div>
		<div id="admission_container" class="working_area_spacer">
			<form id="settingsForm">
				<input type="hidden" name="id" value="${instituteSetting.id}"/>
				<input type="hidden" name="institute.id" value="${instituteSetting.institute.id}"/>
				<table>
					
					<tr>
						<td>Student ID Prefix</td>
						<td>
							<input type="text" name="studentIdPrefix" value="${instituteSetting.studentIdPrefix}">
						</td>
					</tr>
					<tr>
						<td>Temp Student ID Prefix</td>
						<td>
							<input type="text" name="studentTempIdPrefix" value="${instituteSetting.studentTempIdPrefix}">
						</td>
					</tr>
					
					<tr>
						<td>Addmission Year</td>
						<td>
							<select name="admissionSettings.activeAcademicYear.id">
								<option></option>
								<c:forEach var="academicYear" items="${academicYears}">
									<option value="${academicYear.id}" <c:if test="${academicYear.id == instituteSetting.admissionSettings.activeAcademicYear.id}">selected="selected"</c:if> >${academicYear.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Enquiry Year</td>
						<td>
							<select name="enquirySettings.activeAcademicYear.id">
								<option></option>
							<c:forEach var="academicYear" items="${academicYears}">	
								<option value="${academicYear.id}" <c:if test="${academicYear.id == instituteSetting.enquirySettings.activeAcademicYear.id}">selected="selected"</c:if> >${academicYear.name}</option>
							</c:forEach>	
							</select>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="enquirySettings.enableRegistered"   <c:if test="${true == instituteSetting.enquirySettings.enableRegistered}">checked="checked"</c:if> >Show Registered Enquiries</td>
					</tr>
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="enquirySettings.enablePreviousClass"  <c:if test="${true == instituteSetting.enquirySettings.enablePreviousClass}">checked="checked"</c:if> >Show Previous Class Enquiries</td>
					</tr>
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="enquirySettings.enableInternalExam"  <c:if test="${true == instituteSetting.enquirySettings.enableInternalExam}">checked="checked"</c:if>  >Show Internal Exam Enquiries</td>
					</tr>
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="enquirySettings.enableCompetitiveExam"  <c:if test="${true == instituteSetting.enquirySettings.enableCompetitiveExam}">checked="checked"</c:if> >Show Compititive Exam Enquiries </td>
					</tr>
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="admissionSettings.busStopMandatory"  <c:if test="${true == instituteSetting.admissionSettings.busStopMandatory}">checked="checked"</c:if> >Mandatory Bus on admission</td>
					</tr>
					<tr>
						<td></td>
						<td> <input type="checkbox"  name="admissionSettings.formNoMandatory"  <c:if test="${true == instituteSetting.admissionSettings.formNoMandatory}">checked="checked"</c:if> >Mandatory Form on admission </td>
					</tr>
					
					
				</table>
				<div>
					<input type="button" onclick="updateSettings()" value="Save">
				</div>
			</form>
		</div>
		
	</div>
</div>	
<jsp:include page="sams_footer.jsp"/>
<script type="text/javascript">

<!--

function updateSettings(){
	
	var settingsData = $("#settingsForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : "<c:url value='/institute//settings/save' />",
		data : settingsData,
		success : updateSettingsSuccess
	});	
}

function updateSettingsSuccess(response){
	if("OK"==response.status){
		alert("Updated Successfully..");	
	}
}

//-->


</script>

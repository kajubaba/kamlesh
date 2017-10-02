<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title">
			<span class="page_title_text">Manage Student Fee</span>
			<span style="float: right;margin-right: 30px">
					<a href="<c:url value='/fee/admissionrenewal/${student.studentId}' />">
					Back
					</a>
			</span>
		</div>
		<div class="working_area_spacer">
			
			<div style="width: 100%">
				<input type="hidden" id="studentId" name="id" value="${student.id}">
				<input type="hidden" id="chnageCustBusStop" name="chnageCustBusStop" value="true">
				<div style="border: 1px solid #FFFFFF; padding: 1px;margin-bottom: 15px">
				<div style="background-color: #FDFDFD">
					<table width="100%" cellpadding="0" cellspacing="10" border="0" align="center">
						<tr>
							<td class="label">Name :</td>
							<td class="data_field_width">${student.firstName} ${student.lastName}</td>
							<td class="label">
								<c:choose>
									<c:when test="${student.gender=='male'}">S/O</c:when>
									<c:otherwise>D/O</c:otherwise>
								</c:choose>
								:
							</td>
							<td class="data_field_width">${student.fatherName}</td>
						</tr>
						<tr>
							<td class="label">Academic Year :</td>
							<td class="data_field_width">${classHistory.academicYearClass.academicYear.name}</td>
							<td class="label">Class :</td>
							<td class="data_field_width">${classHistory.academicYearClass.displayName}</td>
						</tr>
						<tr>
							<td class="label">Admission Type :</td>
							<td class="data_field_width">${classHistory.student.admissionType.name}</td>
							<td class="label">Bus Stop : </td>
							<td class="data_field_width">
								<c:choose>
									<c:when test="${null==classHistory.busStop && customizeFee.customized==true}">
										<security:authorize access="hasRole('ROLE_CHANGE_STUDENT_BUS_STOP')">
											<a href="javascript:void(0)" onclick="getChangeBusStopPopup(true)">Add Bus Stop</a>
										</security:authorize>
									</c:when>
									<c:otherwise>${classHistory.busStop.name}</c:otherwise>
								</c:choose>
								
								
								
								
								 
							</td>
						</tr>
						<tr>
							<td class="label">Admission Type  :</td>
							<td class="data_field_width">${classHistory.studentStatus.name}</td>
							<td class="label"></td>
							<td class="data_field_width"></td>
						</tr>
					</table>
				</div>
			</div>
				
				<div style="margin-bottom: 10px; text-align: center;width: 100%">
					Installment(s) : 
					<select id="drpdwnnselectedinstl" style="width: 200px;" onchange="getCustomizeInstallmentsPopupView(${customizeFee.studentId},${classHistoryId})">
						<c:forEach var="installment" items="${installments}" varStatus="rowCounter">
							<c:if test="${installment.id >= installmentCount }">
								<option value="${installment.id}"  <c:if test="${installmentCount==installment.id}" >selected="selected"</c:if> > ${installment.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div id="student_cust_fee_container">
					<jsp:include page="student_cust_installment_content.jsp"/>
				</div>
			</div>
		</div>	
	</div>
</div>
<jsp:include page="sams_footer.jsp"/>
<div id="popupBackground" class="popupBack" onclick="closeChangeStudentStatusPopup()"></div>
<div id="changeBusStopPopupBackground" class="popupBack" onclick="closeChangeBusStopPopup()"></div>
<div id="popupChangeBusStop" class="popup"></div>
<script type="text/javascript">
	var customize_fee_base_url="<c:url value='/fee/customize'/>";
	var student_activity_base_url="<c:url value='/studentactivity' />";
	
	$(function() {
		$(".cust_fee_txt_box_numeric").numeric(false,true);
		sumEveryInstallment();
		sumCustomizeFee();
		validateCustomizedFee();
		bindInstallmentDueDatePicker();
	});
	
</script>	
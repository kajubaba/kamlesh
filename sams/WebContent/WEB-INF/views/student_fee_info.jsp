<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${null!=student}">
		<div class="form_container">
			
			<div style="border: 1px solid #FFFFFF; padding: 1px">
				<div style="background-color: #FDFDFD">
					<div style="width: 20%; float: left;">
						<c:choose>
								<c:when test="${student.imageName==null}">
									<img id="studentImage" height="120px" width="120px" alt="" src="<c:url value="/resources/images/default_theme/image_icon.jpg"></c:url>" />
								</c:when>
								<c:otherwise>
									<img id="studentImage" height="120px" width="120px" alt="" src="<c:url value="/resources/studentpics/${student.imageName}"></c:url>" />
								</c:otherwise>
						</c:choose>
					</div>
					<div style="width: 80%;height: 130px; overflow: hidden;">
						<table style="width:100%;border-spacing: 10px">
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
								<td class="data_field_width">${student.academicYearClass.academicYear.name}</td>
								<td class="label">Current Class :</td>
								<td class="data_field_width">${student.academicYearClass.displayName}</td>
							</tr>
							<tr>
								<td class="label">Admission Type :</td>
								<td class="data_field_width">${student.admissionType.name}</td>
								<td class="label">Current Bus Stop : </td>
								<td class="data_field_width">
									${student.busStop.name}
								</td>
							</tr>
							<tr>
								<td class="label">Admission Type  :</td>
								<td class="data_field_width">${student.studentStatus.name}</td>
								<td class="label">Account Status</td>
								<td class="data_field_width">
									<c:choose>
											<c:when test="${null!= student.isLocked}">
												<c:choose>
													<c:when test="${student.isLocked==true}"> <span style="color: red"> Locked</span></c:when>
													<c:otherwise>Active</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>Active</c:otherwise>
										</c:choose>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			<br />
			<br />
			
			<c:if test="${academicYearFeeVOs.size()>1}">
				<table class="grid grid_color_theme_border">
					<tr class="grid_heading grid_heading_theme">
						<td class="grid_item">Academic Year</td>
						<td class="grid_item">Class</td>
						<td class="grid_item">Fee</td>
						<td class="grid_item">Bus Fee</td>
						<td class="grid_item">Late Fee</td>
						<td class="grid_item">Total Payable</td>
						<td class="grid_item">Paid</td>
						<td class="grid_item">Due</td>
					</tr>
					<c:forEach var="academicYearFeeVO" items="${academicYearFeeVOs}" varStatus="rowCounter">
						<c:choose>
							<c:when test="${rowCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
						</c:choose>	
							<c:set var="ayFee" value="${academicYearFeeVO.academicYearFee.getTotalFee() - (academicYearFeeVO.academicYearFee.getTotalFee()-academicYearFeeVO.studentInstallmentsVO.totalInstallmentFee)}" />
							<c:set var="ayBusFee" value="${academicYearFeeVO.busFee- (academicYearFeeVO.busFee-academicYearFeeVO.studentInstallmentsVO.totalBusFee)}"/>
							<td class="grid_item">${academicYearFeeVO.academicYear.name}</td>
							<td class="grid_item">${academicYearFeeVO.academicYearClass.courseYear.course.displayName}, ${academicYearFeeVO.academicYearClass.courseYear.name} Yr.</td>
							<td class="grid_item">${ayFee}</td>
							<td class="grid_item">${ayBusFee}</td>
							<td class="grid_item">${academicYearFeeVO.totalLatefee()}</td>
							<td class="grid_item">${ayFee+ayBusFee+academicYearFeeVO.totalLatefee()}</td>
							<td class="grid_item">${academicYearFeeVO.studentInstallmentsVO.totalDepositedFee}</td>
							<c:set var="ayDueFee" value="${ayFee+ayBusFee+academicYearFeeVO.totalLatefee() -academicYearFeeVO.studentInstallmentsVO.totalDepositedFee }"></c:set>
							<c:choose>
								<c:when test="${ayDueFee>0}">
									<td class="grid_item" style="color: red"><a href="javascript:loadClassHistoryFee(${academicYearFeeVO.classHistoryId })"><b> ${ayDueFee}</b></a></td>
								</c:when>
								<c:otherwise>
									<td class="grid_item"> <a href="javascript:loadClassHistoryFee(${academicYearFeeVO.classHistoryId })"><b> ${ayDueFee}</b></a></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>	
				
				<br />
				<br />
			
			</c:if>
			
			<div style="margin-bottom: 10px;"><a href='<c:url value="/miscfee/${student.studentId }"/>'>Misc Program Fee Details</a> </div>
			
			<div id="classHistoryContainer">
				<jsp:include page="student_ay_fee_info.jsp"/>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div style="width: 100%; text-align: center;padding-top: 100px">
			Please find student with valid Student ID
		</div>
	</c:otherwise>
</c:choose>


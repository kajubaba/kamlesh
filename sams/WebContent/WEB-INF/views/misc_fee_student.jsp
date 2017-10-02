<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${null!=student}">
		<div class="form_container">
			<div style="border: 1px solid #FFFFFF; padding: 1px">
				<div style="background-color: #FDFDFD">
					<div style="width: 20%;float: left;">
						<c:choose>
								<c:when test="${student.imageName==null}">
									<img id="studentImage" height="120" width="120" alt="" src="<c:url value="/resources/images/default_theme/image_icon.jpg"></c:url>" />
								</c:when>
								<c:otherwise>
									<img id="studentImage" height="120" width="120" alt="" src="<c:url value="/resources/studentpics/${student.imageName}"></c:url>" />
								</c:otherwise>
						</c:choose>
					</div>
					<div style="width: 80%;height: 130px">
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
									<td class="data_field_width">${student.guardianName}</td>
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
									<td class="label"></td>
									<td class="data_field_width"></td>
								</tr>
							</table>
						</div>	
				</div>
			</div>
			
			<div>
			
				<a href="<c:url value="/miscfee/transactions/${student.id}"/>">Misc Program - Fee Transactions</a>
				&nbsp;&nbsp;&nbsp;
				<a href="<c:url value="/fee/admissionrenewal/${student.studentId}"/>">Academic Year Fee</a>
				
			</div>
			
			<br />
			<br />
			
			<c:if test="${academicYearMiscActivityVOs.size()>0}">
				<table class="grid grid_color_theme_border">
					<tr class="grid_heading grid_heading_theme">
						<td class="grid_item">Academic Year</td>
						<td class="grid_item">Class</td>
						<td class="grid_item">Misc Program</td>
						<td class="grid_item">Total Fee</td>
						<td class="grid_item">Paid Fee</td>
						<td class="grid_item">Due Fee</td>
					</tr>
					<c:forEach var="academicYearMiscActivityVO" items="${academicYearMiscActivityVOs}" varStatus="rowCounter">
						<c:choose>
							<c:when test="${rowCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
						</c:choose>	
							<td class="grid_item">
								${academicYearMiscActivityVO.academicYear.name}
							</td>
							<td class="grid_item">${academicYearMiscActivityVO.studnetClass.courseYear.course.displayName}, ${academicYearMiscActivityVO.studnetClass.courseYear.name} Yr.</td>
							<td class="grid_item">
								<c:forEach var="miscActivityVO" items="${academicYearMiscActivityVO.miscActivityVOs}">
									<div style="margin-top:10px; margin-bottom: 5px">
										<a href="javascript:void(0)" onclick="getMiscFeePaymentView(${miscActivityVO.miscActivityCourseYear.miscActivity.id},${miscActivityVO.miscActivityCourseYear.id},${student.id}, ${academicYearMiscActivityVO.studnetClass.id})">
											${miscActivityVO.miscActivityCourseYear.miscActivity.name}
										</a>
									</div>	
								</c:forEach>
							</td>
							<td class="grid_item">
								<c:forEach var="miscActivityVO" items="${academicYearMiscActivityVO.miscActivityVOs}">
									<div style="margin-top:10px; margin-bottom: 5px">
										${miscActivityVO.miscActivityCourseYear.miscActivity.fee}
									</div>		
								</c:forEach>
							</td>
							<td class="grid_item">
								<c:forEach var="miscActivityVO" items="${academicYearMiscActivityVO.miscActivityVOs}">
									<div style="margin-top:10px; margin-bottom: 5px">
										${miscActivityVO.padFee}
									</div>		
								</c:forEach>
							</td>
							<td class="grid_item">
								<c:forEach var="miscActivityVO" items="${academicYearMiscActivityVO.miscActivityVOs}">
									<div style="margin-top:10px; margin-bottom: 5px">
										${miscActivityVO.miscActivityCourseYear.miscActivity.fee -	miscActivityVO.padFee}
									</div>		
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</table>	
				
				<br />
				<br />
			
			</c:if>
			
			<div id="miscFeePaymentContainer">
				
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div style="width: 100%; text-align: center;padding-top: 100px">
			Please find student with valid Student ID
		</div>
	</c:otherwise>
</c:choose>


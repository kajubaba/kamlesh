<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<div class="form_container">
	<div style="border: 1px solid #FFFFFF; padding: 1px">
			<div style="background-color: #FDFDFD;margin-bottom: 10px">
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
							<table style="width: 100%;;border-spacing: 10px">
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
										${classHistory.busStop.name}
									</td>
								</tr>
								<tr>
									<td class="label">Admission Type  :</td>
									<td class="data_field_width">${classHistory.studentStatus.name}</td>
									<td class="label">Account Status :</td>
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


<div>

	<form id="feePaymentForm">
	
		<input type="hidden" name="classHistoryId" value="${classHistory.id}" />
		<input id="printReciept" type="hidden" name="printReciept" value="false" />
		<input type="hidden" name="studentId" value="${student.id}" /> 
		<input type="hidden" name="academciYearClassId" value="${classHistory.academicYearClass.id}" />
		<input type="hidden" name="installmentId" value="${installmentId}" />
		<input type="hidden" name="academicYearFeeInstallmentId" value="${feePaymentVO.academicYearFeeInstallmentId}" />
		<input type="hidden" name="custFeeInstallmentId" value="${feePaymentVO.custFeeInstallmentId}" />
		<input type="hidden" id="studentFeePaymentHeadCount" value="${fn:length(feePaymentVO.feePaymentHeadVOs)}" />
		
		<div style="width: 100%;text-align: center;margin-bottom: 10px">
			Reciept Header
			<select name="feeRecieptHeaderId" style="width: 300px">
				<c:forEach var="feeRecieptHeader" items="${feeRecieptHeaders}">
				
				<option value="${feeRecieptHeader.id}">${feeRecieptHeader.displayName}</option>
				</c:forEach>
			</select>
		</div>
		
		<div>
			<table class="grid grid_color_theme_border">
				<tr class="grid_heading grid_heading_theme">
					<td class="grid_item">Perticular</td>
					<td class="grid_item">Payable</td>
					<td class="grid_item">Paid</td>
					<td class="grid_item">Due</td>
					<td class="grid_item">Pay (Rs.)</td>
				</tr>
				<c:forEach var="feePaymentHeadVO" items="${feePaymentVO.feePaymentHeadVOs}" varStatus="rowCounter">
					<c:choose>
							<c:when test="${rowCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
					</c:choose>
					<td class="grid_item">
						<input type="hidden" name="feeTransactionDetailForms['${rowCounter.index}'].feeHeadId" value="${feePaymentHeadVO.feeHeadId}"/>
						<input type="hidden" name="feeTransactionDetailForms['${rowCounter.index}'].feeHeadName" value="${feePaymentHeadVO.feeHeadName}"/>
						${feePaymentHeadVO.feeHeadName}
					</td>
					<td class="grid_item">${feePaymentHeadVO.amount}</td>
					<td class="grid_item">${feePaymentHeadVO.deplositFee}</td>
					<td class="grid_item" id="dueFee${rowCounter.index}" >${feePaymentHeadVO.amount - feePaymentHeadVO.deplositFee}</td>
					<td class="grid_item">
						<input type="text" maxlength="6" id="payAmt${rowCounter.index}" name="feeTransactionDetailForms[${rowCounter.index}].amount" value="${feePaymentHeadVO.amount - feePaymentHeadVO.deplositFee}" class="fhFeePayment numeric" onkeyup="sumFeePayment()" />
					</td>
				</tr>	
				</c:forEach>
				<tr class="grid_heading grid_heading_theme">
					<td class="grid_item bold">Total</td>
					<td class="grid_item bold">${feePaymentVO.totalFee}</td>
					<td class="grid_item bold">${feePaymentVO.totalDeposited}</td>
					<td class="grid_item bold">${feePaymentVO.totalDue}</td>
					<td class="grid_item bold" id="feePaymentTotal" >${feePaymentVO.totalDue}</td>
				</tr>
			</table>
		</div>
		<div style="margin-top: 10px"> 
			<div>Comments:</div>
			<div>
			 	<textarea rows="2" cols="135" name="comments" style="width: 100%; height: 40px"></textarea>
			</div>  
		</div>
	</form>
</div>
</div>
<div style="width: 100%;text-align: center;" id="printRecieptDiv">
	
</div>

<div style="width: 100%;text-align: center;margin-top: 13px;">
	<input type="button" value="&nbsp;&nbsp;Back&nbsp;&nbsp;" class="button" onclick="findStudentInfo()" />&nbsp;&nbsp;
	<input type="button" id="btnConfirmPayment" value="Confirm Fee" class="button" onclick="payStudentFee()" />
	<input type="button" id="btnConfirmPrintPayment" value="Confirm and Print" class="button" onclick="payAndPrintStudentFee()" />
	
</div>

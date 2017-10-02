<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<form id="miscFeePaymentForm">

	<input type="hidden" name="studentId" value="${studentId}" />
	<input id="printReciept" type="hidden" name="printReciept" value="false" />
	<input type="hidden" name="studentClassId" value="${studentClassId}" /> 
	<input type="hidden" name="miscActivityCourseYearId" value="${miscActivityCourseYearId}" />
	<input type="hidden" id="miscFeeHeadCnt" value="${fn:length(miscFeeHeadVOs)}" />
	
	<div style="margin-bottom: 15px;background-color: #DDDDDD;height: 25px;text-align: center;padding-top: 5px">
		${miscActivityCourseYear.courseYearSetting.academicYear.name} >> 
		${miscActivityCourseYear.courseYearSetting.courseYear.course.displayName} ${miscActivityCourseYear.courseYearSetting.courseYear.name} Yr.
		>> ${miscActivityCourseYear.miscActivity.name}
	</div>
	
	<div style="width: 100%;text-align: center;margin-bottom: 10px">
		Reciept Header
		<select name="receiptHeaderId" style="width: 300px">
			<c:forEach var="feeRecieptHeader" items="${feeRecieptHeaders}">
			
			<option value="${feeRecieptHeader.id}">${feeRecieptHeader.displayName}</option>
			</c:forEach>
		</select>
	</div>

	<table class="grid grid_color_theme_border">
			<tr class="grid_heading grid_heading_theme">
				<td class="grid_item">Fee Head</td>
				<td class="grid_item">Fee</td>
				<td class="grid_item">Paid</td>
				<td class="grid_item">Due</td>
				<td class="grid_item">Pay</td>
			</tr>
					

	<c:forEach var="miscFeeHeadVO" items="${miscFeeHeadVOs}" varStatus="rowCounter">
						<c:choose>
							<c:when test="${rowCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
						</c:choose>	
							<td class="grid_item">
								<input type="hidden" name="miscFeePaymentFormDetails[${rowCounter.index}].feeHeadId"  value="${miscFeeHeadVO.feeHeadId}">
								${miscFeeHeadVO.feeHeadName}
							</td>
							<td class="grid_item">
								${miscFeeHeadVO.amount}
								<c:set var="totalFee" value="${totalFee+miscFeeHeadVO.amount}" />
							</td>
							<td class="grid_item">
								${miscFeeHeadVO.deplositFee}
								<c:set var="totalPaidFee" value="${totalPaidFee+(miscFeeHeadVO.deplositFee)}" />
							</td>
							<td class="grid_item" id="dueMiscFee${rowCounter.index}" >
								${miscFeeHeadVO.amount-miscFeeHeadVO.deplositFee}
								<c:set var="totalDue" value="${totalDue+(miscFeeHeadVO.amount-miscFeeHeadVO.deplositFee)}" />
							</td>
							<td class="grid_item">
								<input type="text" id="miscPayAmt${rowCounter.index}" name="miscFeePaymentFormDetails[${rowCounter.index}].amount" class="mffh numeric"  value="${miscFeeHeadVO.amount-miscFeeHeadVO.deplositFee}" onkeyup="sumMiscFeePayment()"/>
							</td>
						</tr>
					</c:forEach>
					<tr class="grid_heading grid_heading_theme">
			<td class="grid_item bold">Total</td>
			<td class="grid_item bold">${totalFee}</td>
			<td class="grid_item bold">${totalPaidFee}</td>
			<td class="grid_item bold">${totalDue}</td>
			<td class="grid_item bold" id="miscFeePaymentTotal" >${totalDue}</td>
		</tr>
		</table>
		
		<div style="margin-top: 7px">
			<textarea name="comments" rows="3" cols="160" spellcheck="true"></textarea>
		</div>
		
		<div style="width: 100%;text-align: center;margin-top: 13px;">
			<input type="button" id="btnMiscPayment" value="Confirm Fee" class="button" onclick="payStudentMiscFee()" />
			<input type="button" id="btnMiscPaymentPrint" value="Confirm and Print" class="button" onclick="payAndPrintStudentMiscFee()" />
		</div>
</form>

<div id="printRecieptDiv" class="feeReciept">

</div>		
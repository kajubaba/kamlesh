<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
<c:choose>
	<c:when test="${null!=academicYearFeeVOs}">
		
		
		<table class="grid grid_color_theme_border">
			<tr class="grid_heading grid_heading_theme">
				<td class="grid_item"><b>Class :</b> ${academicYearFeeVOs[0].academicYearClass.displayName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <b>Academic Year :</b> ${academicYearFeeVOs[0].academicYearClass.academicYear.name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <b>Bus Stop :</b> ${academicYearFeeVOs[0].busStop.name} </td>
			</tr>
		</table>	
		<br />
		<br />
		
		<div id="payFeeContainer">
			<div style="margin-bottom: 10px">	 
				<security:authorize access="hasRole('ROLE_CUST_STUD_FEE')">
					<a href="<c:url value='/fee/customize'/>/${student.id}/${academicYearFeeVOs[0].classHistoryId}" >Customize</a>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_FEE')">
					&nbsp;&nbsp;&nbsp;<a href="<c:url value="/fee/transaction/${student.id}" />">Transaction Details</a>
				</security:authorize>
			</div>
			<table class="grid grid_color_theme_border">
				<tr class="grid_heading grid_heading_theme">
					<td class="grid_item">Academic Year Fee</td>
					<td class="grid_item">Bus Fee</td>
					<td class="grid_item">Total Fee</td>
					<td class="grid_item">Academic Year Discount</td>
					<td class="grid_item">Bus Fee Discount</td>
					<td class="grid_item">Payable Fee</td>
				</tr>
				<tr>
					<td class="grid_item">${academicYearFeeVOs[0].academicYearFee.getTotalFee()}</td>
					<td class="grid_item">${academicYearFeeVOs[0].busFee}</td>
					<td class="grid_item">${academicYearFeeVOs[0].academicYearFee.getTotalFee()+academicYearFeeVOs[0].busFee}</td>
					<td class="grid_item">${academicYearFeeVOs[0].academicYearFee.getTotalFee()-academicYearFeeVOs[0].studentInstallmentsVO.totalInstallmentFee}</td>
					<td class="grid_item">${academicYearFeeVOs[0].busFee-academicYearFeeVOs[0].studentInstallmentsVO.totalBusFee}</td>
					<td class="grid_item">${academicYearFeeVOs[0].academicYearFee.getTotalFee()+academicYearFeeVOs[0].busFee - (academicYearFeeVOs[0].academicYearFee.getTotalFee()-academicYearFeeVOs[0].studentInstallmentsVO.totalInstallmentFee) - (academicYearFeeVOs[0].busFee-academicYearFeeVOs[0].studentInstallmentsVO.totalBusFee) }</td>
				</tr>
			</table>
			<br/>		
		<div>
			<form id="instldetailform">
					<input type="hidden" name="studentId" value="${student.id}">
					<input type="hidden" name="classHistoryId" value="${academicYearFeeVOs[0].classHistoryId }">
					<table class="grid grid_color_theme_border">
						<tr class="grid_heading grid_heading_theme">
							<td class="grid_item">Installment #</td>
							<td class="grid_item">Due Date</td>
							<td class="grid_item">Academic Fee</td>
							<td class="grid_item">Bus Fee</td>
							
							<td class="grid_item">Late Fee</td>
							<td class="grid_item">Total Payable</td>
							<td class="grid_item">Deposited</td>
							<td class="grid_item">Due Fee</td>
							<security:authorize access="hasRole('ROLE_FEE')">
								<td class="grid_item">Select</td>
							</security:authorize>
						</tr>
						<c:forEach var="installment" items="${academicYearFeeVOs[0].studentInstallmentsVO.installments}" varStatus="rowCounter">
							<c:choose>
								<c:when test="${rowCounter.count % 2 != 0}">
									<tr class="grid_main_row_plain">
								</c:when>
								<c:otherwise>
								   	<tr class="grid_alt_row_plain">
								</c:otherwise>
							</c:choose>	
							<td class="grid_item">${installment.installmentName}</td>
							<td class="grid_item"> <fmt:formatDate pattern="dd-MMM-yyyy" value="${installment.dueDate}" /> </td>
							<td class="grid_item">${installment.totalFee}
								<c:set var="academicFeeTotal" value="${academicFeeTotal+installment.totalFee}"></c:set>
							</td>
							<td class="grid_item">${installment.busFee}
								<c:set var="busFeeTotal" value="${busFeeTotal+installment.busFee}"></c:set>
							</td>
							
							<td class="grid_item">
								<c:set var="lateFeeTotal" value="${lateFeeTotal+installment.lateFee}"></c:set>
								${installment.lateFee}
							</td>
							<td class="grid_item">
								<c:set var="payableFee" value="${installment.totalFee+installment.busFee+installment.lateFee}"></c:set>
								${payableFee}
								<c:set var="payableFeeTotal" value="${payableFeeTotal+payableFee}"></c:set>
								
							</td>
							<td class="grid_item">
								<c:set var="paidFeeTotal" value="${paidFeeTotal+installment.depositeFee}"></c:set>
							${installment.depositeFee}</td>
							<td class="grid_item">
								<c:set var="dueFeeTotal" value="${dueFeeTotal+installment.dueFee}"></c:set>
								${installment.dueFee}
							</td>
							<security:authorize access="hasRole('ROLE_FEE')">
								<td class="grid_item">
									<input type="radio" name="installments" value="${installment.installmentId},${installment.academicYearFeeInstallmentId},${installment.custFeeInstallmentId}" />
									
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
					<tr class="grid_heading grid_heading_theme">
						<td class="grid_item bold">Total</td>
						<td class="grid_item bold"></td>
						<td class="grid_item bold">${academicFeeTotal}</td>
						<td class="grid_item bold">${busFeeTotal}</td>
						
						<td class="grid_item bold">${lateFeeTotal}</td>
						<td class="grid_item bold">${payableFeeTotal}</td>
						<td class="grid_item bold">${paidFeeTotal}</td>
						<td class="grid_item bold">${dueFeeTotal}</td>
						<security:authorize access="hasRole('ROLE_FEE')">
							<td class="grid_item bold"></td>
						</security:authorize>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%;text-align: center;margin-top: 13px">
			<security:authorize access="hasRole('ROLE_FEE')">
				<c:if test="${ (student.academicYearClass.academicYear.id!=academicYearFeeVOs[0].academicYearClass.academicYear.id) || (!student.isLocked && student.academicYearClass.academicYear.id==academicYearFeeVOs[0].academicYearClass.academicYear.id)}">
					<input type="button"  value="Continue to Payment" class="button" onclick="getFeePaymentView()" />
				</c:if>
				
			</security:authorize>
		</div>
		
		</div>
			</c:when>
			<c:otherwise>
				<div style="width:100%;color: red;font-weight: bold;text-align: center;">
					Fee for '${student.academicYearClass.courseYear.course.displayName} ${student.academicYearClass.courseYear.name} 
					Year' is not configured in academic year '${student.academicYear.name}'
					<br/><br/>
					Please contact Administrator
				</div>
			</c:otherwise>
		</c:choose>
</div>		
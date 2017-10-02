<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<input type="hidden" id="noOfInstallments" value="${installmentCount}"/> 
<script type="text/javascript">
	var noOfHeads='${fn:length(customizeFee.feeHeads)}';
</script>

<form id="customizeFeeForm">
<input type="hidden" name="studentId" value="${customizeFee.studentId}" />
<input type="hidden" name="academicYearClassId" value="${customizeFee.academicYearClassId}" />
<input type="hidden" name="academicYearFeeId" value="${academicYearFeeId}" />
<input type="hidden" id="isFeeCustomized" name="customized" value="${customizeFee.customized}" />
<div style="width: 100%; overflow: auto;">
<table class="grid grid_color_theme_border" width="100%">
			    <tr class="grid_heading grid_heading_theme">
				   <td class="grid_item">Fee Head</td>
				   <td class="grid_item">Fee</td>
				   <td class="grid_item">Discount</td>
				   <td class="grid_item">Payable</td>
				   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				   		<td class="grid_item">${rowCounter.index}</td>
				   </c:forEach>
				   <td class="grid_item bold">Total</td>
				</tr>
				<c:forEach var="feeHead" items="${customizeFee.feeHeads}" varStatus="feeHeadCounter">
					<c:choose>
					 		<c:when test="${feeHeadCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
					</c:choose>
					<td class="grid_item">
						<input type="hidden" name="feeHeads[${feeHeadCounter.index}].headId" value="${feeHead.headId}" />
						<input type="hidden" name="feeHeads[${feeHeadCounter.index}].headName" value="${feeHead.headName}" />
						${feeHead.headName}
					</td>
					<td class="grid_item">
						<c:set var="feeHeadSum" value="${feeHeadSum+feeHead.headFee}"></c:set>
						<input type="hidden" name="feeHeads[${feeHeadCounter.index}].headFee" value="${feeHead.headFee}" />
						<div id="fee_head_${feeHeadCounter.index}">${feeHead.headFee}</div>
						
					</td>
					<td class="grid_item">
						 <c:set var="discountSum" value="${discountSum+feeHead.discount}"></c:set>
						 <input type="hidden" name="feeHeads[${feeHeadCounter.index}].feeDiscountId" value="${feeHead.feeDiscountId}" />
						 <input type="text" id="discount_${feeHeadCounter.index}" name="feeHeads[${feeHeadCounter.index}].discount" value="${feeHead.discount}" maxlength="6" size="7" onkeyup="sumDiscount(${feeHeadCounter.index})" class="fee_discount cust_fee_txt_box_numeric"/>
					</td>
					<td class="grid_item">
						<c:set var="payableSum" value="${payableSum+(feeHead.headFee-feeHead.discount)}"></c:set>
						<div id="fee_payable_${feeHeadCounter.index}" class="fee_payable">${feeHead.headFee-feeHead.discount}</div>
					</td>
					<c:forEach var="installment" items="${feeHead.installments}" varStatus="instlCounter">
						<td class="grid_item">
							<c:set var="rowSum" value="${rowSum+installment.amount}"></c:set>
							<input type="hidden" name="feeHeads[${feeHeadCounter.index}].installments[${instlCounter.index}].installmentId" value="${installment.installmentId}" />
							<input type="hidden" name="feeHeads[${feeHeadCounter.index}].installments[${instlCounter.index}].installmentFeeId" value="${installment.installmentFeeId}" />
							<input type="hidden" id="feeHeads_${feeHeadCounter.index}_installments_${instlCounter.index}_deposited" name="feeHeads[${feeHeadCounter.index}].installments[${instlCounter.index}].deposited" value="${installment.deposited}"  />
							<input type="text" id="feeHeads_${feeHeadCounter.index}_installments_${instlCounter.index}_amount" name="feeHeads[${feeHeadCounter.index}].installments[${instlCounter.index}].amount" value="${installment.amount}" class="cust_fee_txt_box_numeric  cust_instl_row_${feeHeadCounter.index} cust_inst_col_${instlCounter.index} red_border_text_box" maxlength="6" size="7" onkeyup="sumInstallmentFee(${feeHeadCounter.index},${instlCounter.index})" />
			 			</td>
					</c:forEach>
					<td id="cust_instl_row_${feeHeadCounter.index}_total" class="grid_item bold">
						<c:set var="grandTotal" value="${grandTotal+rowSum}"></c:set>
						${rowSum}
						<c:set var="rowSum" value="0"></c:set>
					</td>
		</tr>
	</c:forEach>
	 <tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Total</td>
	   <td class="grid_item bold">${feeHeadSum}</td>
	   <td class="grid_item bold" id="total_fee_discount">${discountSum}</td>
	   <td class="grid_item bold" id="total_fee_payable">${payableSum}</td>
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td id="cust_inst_col_${rowCounter.index-1}_total" class="grid_item bold"></td>
		   </c:forEach>
	   <td class="grid_item bold" id="cust_inst_grand_total">${grandTotal}</td>
	</tr>
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Due Date</td>
	   <td class="grid_item"></td>
	   <td class="grid_item"></td>
	   <td class="grid_item"></td>
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td class="grid_item">
					<input type="hidden" name="customizeDueDateVOs[${rowCounter.index-1}].customizeInstallmentId" value="${customizeDueDateVOs[rowCounter.index-1].customizeInstallmentId}">
					<input type="text" id="due_date_picker_${rowCounter.index-1}" name="customizeDueDateVOs[${rowCounter.index-1}].dueDateStr"  value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${customizeDueDateVOs[rowCounter.index-1].dueDate}" />'  readonly="readonly" size="8">
				</td>
		   </c:forEach>
	   <td class="grid_item bold"></td>
	</tr>
	
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Late Fee Rule</td>
	   <td class="grid_item"></td>
	   <td class="grid_item"></td>
	   <td class="grid_item"></td>
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td class="grid_item">
					<select id="latefeeRuleId_${rowCounter.index-1}" name="customizeDueDateVOs[${rowCounter.index-1}].latefeeRuleId">
	   					<option value=""></option>
			   			<c:forEach var="lateFeeRule" items="${lateFeeRules }">
			   				<option value="${lateFeeRule.id}"  <c:if test="${lateFeeRule.id==customizeDueDateVOs[rowCounter.index-1].latefeeRuleId}">selected="selected"</c:if>  >${lateFeeRule.name}</option>
			   			</c:forEach>
			   		</select>
				</td>
		   </c:forEach>
	   <td class="grid_item bold"></td>
	</tr>
</table>
</div>
<div style="margin-top: 10px;" >
	
	<table width="100%">
		<tr>
			<td width="90px">
				<input type="hidden" name="commentsId" value="${feeCustomizeComments.id}">
				Comments:
			</td>
			<td width="470px">
				<textarea rows="2" cols="103" name="comments" maxlength="2000">${feeCustomizeComments.comments}</textarea>
			</td>
			<td style="text-align: center;">
				<input type="button" value="Save Installments" id="btnSaveCustInstallment" class="button" onclick="saveCustomizeFee()" style="width: 171px;height: 50px;font-weight: bold;"/>
			</td>
		</tr>
	</table>
	
	<c:if test="${null!=comments}">
		<table width="100%">
			<tr>
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.academicYear.id != feeCustomizeComments.academicYear.id }">
						<td>${comment.academicYear.name}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.academicYear.id != feeCustomizeComments.academicYear.id }">
						<td>
							<textarea rows="3" cols="30" maxlength="2000" readonly="readonly">${comment.comments}</textarea>
						</td>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</c:if>

</div>
</form>
<div style="text-align: center;margin-top: 30px">
	&nbsp;
</div>
<security:authorize access="hasRole('ROLE_CUST_LATE_FEE')">

<div id="lateFeeContainer" style="margin-top: 10px">
<div class="separatpr_line" style="width:100%">Late Fee</div>
<br/>
<form id="lateFeeForm">	
	<table class="grid grid_color_theme_border" width="100%">
		<tr class="grid_heading grid_heading_theme">
			<td class="grid_item">Installment</td>
			<td class="grid_item">Days Overdue</td>
			<td class="grid_item">Late Fee</td>
			<td class="grid_item">Discount</td>
			<td class="grid_item">Payable Late Fee</td>
			<td class="grid_item">Enable ?</td>
		</tr>
		<c:forEach var="lateFee" items="${lateFees}" varStatus ="rowCounter">
			<c:set var="lateFeeTotal" value="${lateFeeTotal+lateFee.lateFee}"/>
			<c:set var="lateFeeDiscountTotal" value="${lateFeeDiscountTotal+lateFee.discount}"/>
		<c:choose>
					 		<c:when test="${rowCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
					</c:choose>
		
			<td class="grid_item">
				<input type="hidden" name="daysOverdues[${rowCounter.index}].id" value="${lateFee.id}"/>
			${lateFee.installment.name}</td>
			<td class="grid_item">${lateFee.daysOverdue}</td>
			<td class="grid_item">${lateFee.lateFee}</td>
			<td class="grid_item"><input type="text" name="daysOverdues[${rowCounter.index}].discount" value="${lateFee.discount}"/> </td>
			<td class="grid_item">${lateFee.lateFee-lateFee.discount}</td>
			<td class="grid_item">
				<input type="checkbox" name="daysOverdues[${rowCounter.index}].calculate" <c:if test="${lateFee.calculate==true}">checked="checked"</c:if> />
			</td>
			
		</tr>
		</c:forEach>
		<tr class="grid_heading grid_heading_theme">
			<td class="grid_item"></td>
			<td class="grid_item"></td>
			<td class="grid_item">${lateFeeTotal}</td>
			<td class="grid_item">${lateFeeDiscountTotal}</td>
			<td class="grid_item">${lateFeeTotal-lateFeeDiscountTotal}</td>
			<td class="grid_item"></td>
			
		</tr>
	</table>
	<div style="text-align: center;margin-top: 30px">
		<input type="button" value="Save Late Fee" class="button" onclick="saveCustomizeLateFee()" style="width: 120px;height: 27px;font-weight: bold;"/>
	</div>
	
</form>	
</div>
</security:authorize>
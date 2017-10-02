<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<input type="hidden" id="noOfInstallments" value="${installmentCount}"/> 
<script type="text/javascript">
	var feeHeadCount='${fn:length(installmentFeeHeadVOs)}';
</script>



<div id="manage-installment-container">

	<div style="margin-bottom: 10px; text-align: center;width: 100%">
						Installment(s) : 
			<select id="feeInstallments" style="width: 200px;" onchange="changeInstallments(this, ${courseYearSettingId})">
				<c:forEach var="installment" items="${installments}" varStatus="rowCounter">
					<option value="${installment.id}"  <c:if test="${installmentCount==installment.id}" >selected="selected"</c:if> > ${installment.name}</option>
				</c:forEach>
			</select>
	</div>

<form id="instlForm">

<input type="hidden" name="academicYearFeeId" value="${academicYearFeeId}" />
<input type="hidden" name="tab" value="${tab}" />

<div style="width: 100%; overflow: auto;">
			<table class="grid grid_color_theme_border" width="100%">
		 		
		 		<tr class="grid_heading grid_heading_theme">
				   <td class="grid_item">Fee Head</td>
				   <td class="grid_item">Fee</td>
				   
				   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				   		<td class="grid_item">${rowCounter.index}</td>
				   </c:forEach>
				   <td class="grid_item bold">Total</td>
				</tr>
				
				<c:forEach var="installmentFeeHeadVO" items="${installmentFeeHeadVOs}" varStatus="rowCounter">
					<c:choose>
					 		<c:when test="${feeHeadCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
					</c:choose>
					<td class="grid_item">
						<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].feeHeadId" value="${installmentFeeHeadVO.feeHeadId}" />
						${installmentFeeHeadVO.feeHeadName}
					</td>
					<td class="grid_item">
						<c:set var="feetotal" value="${feetotal + installmentFeeHeadVO.amount}" />
						<input type="hidden" name="feeHeads[${rowCounter.index}].headFee" value="${installmentFeeHeadVO.amount}" />
						<div id="fee_head_${rowCounter.index}">${installmentFeeHeadVO.amount}</div>
					</td>
					
					<c:forEach var="installmentDetailVO" items="${installmentFeeHeadVO.installmentDetailVOs}" varStatus="instlCounter">
						<td class="grid_item">
							<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['${instlCounter.index}'].academicYearFeeInstallmentDetailId" value="${installmentDetailVO.academicYearFeeInstallmentDetailId}" />
							<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['${instlCounter.index}'].academicYearFeeInstallmentId" value="${installmentDetailVO.academicYearFeeInstallmentId}" />
							<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['${instlCounter.index}'].installmentId" value="${installmentDetailVO.installmentId}" />
							<input type="text" maxlength="6" size="10" id="feeHeads_${rowCounter.index}_installments_${instlCounter.index}_amount" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['${instlCounter.index}'].amount" value="${installmentDetailVO.amount}" class="inst_col_${instlCounter.index} numeric feehead${rowCounter.index}" onkeyup="sumInstallment(${rowCounter.index},${instlCounter.index})"/>
							<c:set var="rowSum" value="${rowSum+installmentDetailVO.amount}"></c:set>
			 			</td>
					</c:forEach>
					<td id="instl_row_${rowCounter.index}_total" class="grid_item bold">
						<c:set var="grandTotal" value="${grandTotal+rowSum}"></c:set>
						${rowSum}
						<c:set var="rowSum" value="0"></c:set>
					</td>
				</tr>
			</c:forEach>
	 
	
	 <tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Total</td>
	   <td id="totalFee" class="grid_item bold">${feetotal}</td>
	   
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td id="inst_col_${rowCounter.index-1}_total" class="grid_item bold"></td>
		   </c:forEach>
	   <td class="grid_item bold" id="inst_grand_total">${grandTotal}</td>
	</tr>
	
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Due Date</td>
	   <td class="grid_item"></td>
	   
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td class="grid_item">
					<input type="hidden" name="dueDates[${rowCounter.index-1}].installmentId" value="${rowCounter.index}">
					<input type="text" id="due_date_picker_${rowCounter.index-1}" name="dueDates[${rowCounter.index-1}].dueDateStr"  value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${dueDates[rowCounter.index-1].dueDate}" />'  readonly="readonly" size="8">
				</td>
		   </c:forEach>
	   <td class="grid_item bold"></td>
	</tr>
	
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Late Fee Rule</td>
	   <td class="grid_item"></td>
	  
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td class="grid_item">
					<input type="hidden" name="dueDates[${rowCounter.index-1}].installmentId">
					<select id="latefeeRuleId_${rowCounter.index-1}" name="dueDates[${rowCounter.index-1}].lateFeeRuleId">
	   					<option value=""></option>
			   			<c:forEach var="lateFeeRule" items="${lateFeeRules}">
			   				<option value="${lateFeeRule.id}"  <c:if test="${lateFeeRule.id==dueDates[rowCounter.index-1].lateFeeRuleId}">selected="selected"</c:if>  >${lateFeeRule.name}</option>
			   			</c:forEach>
			   		</select>
				</td>
		   </c:forEach>
	   <td class="grid_item bold"></td>
	</tr>
	
			</table>
			<input type="button" value="Save Installments" id="btnSaveCustInstallment" class="button" onclick="saveInstallments()" style="width: 171px;height: 50px;font-weight: bold;"/>
</div>

</form>
</div>

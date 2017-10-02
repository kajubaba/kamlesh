<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:set var="frstinstltotal" value="0" />
<c:set var="scndinstltotal" value="0" />
<c:set var="feetotal" value="0" />


<form id="instlForm">
<input type="hidden" id="totalFeeHeadCount" value="${fn:length(installmentFeeHeadVOs)}">


<input type="hidden" name="academicYearFeeId" value="${academicYearFeeId}">
<table class="grid grid_color_theme_border" width="100%">
    <tr class="grid_heading grid_heading_theme">
	   <td class="grid_item">Fee Head</td>
	   <td class="grid_item">Total Fee (Rs)</td>
	   <td class="grid_item">I installment</td>
	   <td class="grid_item">II installment</td>
	   <td class="grid_item" style="font-weight: bold;">Total</td>
	</tr>
	<c:forEach var="installmentFeeHeadVO" items="${installmentFeeHeadVOs}" varStatus="rowCounter">
		<c:choose>
		 		<c:when test="${rowCounter.count % 2 != 0}">
					<tr class="grid_main_row_plain">
				</c:when>
				<c:otherwise>
				   	<tr class="grid_alt_row_plain">
				</c:otherwise>
		</c:choose>
			<td class="grid_item">
				<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].feeHeadId" value="${installmentFeeHeadVO.feeHeadId}">
				${installmentFeeHeadVO.feeHeadName}
			</td>
			<td class="grid_item" id="headamnt${rowCounter.index}">
				${installmentFeeHeadVO.amount}
				<c:set var="feetotal" value="${feetotal + installmentFeeHeadVO.amount}" />
			</td>
			<td class="grid_item">
				<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['0'].id" value="${installmentFeeHeadVO.installmentDetailVOs['0'].id}" />
				<input type="text" maxlength="6" size="10" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['0'].amount" value="${installmentFeeHeadVO.installmentDetailVOs['0'].amount}" class="firstinstlfee numeric feehead${rowCounter.index}" onkeyup="sumFirstInstlFee(${rowCounter.index})"/>
				<c:set var="frstinstltotal" value="${frstinstltotal + installmentFeeHeadVO.installmentDetailVOs['0'].amount}" />
			 </td>
			<td class="grid_item">
				<input type="hidden" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['1'].id" value="${installmentFeeHeadVO.installmentDetailVOs['1'].id}" />
				<input type="text" maxlength="6" size="10" name="installmentFeeHeadVOs['${rowCounter.index}'].installmentDetailVOs['1'].amount" value="${installmentFeeHeadVO.installmentDetailVOs['1'].amount}" class="scndtinstlfee numeric feehead${rowCounter.index}" onkeyup="sumSecondInstlFee(${rowCounter.index})" />
				<c:set var="scndinstltotal" value="${scndinstltotal + installmentFeeHeadVO.installmentDetailVOs['1'].amount}" />
			</td>
			<td id="feeheadtotal${rowCounter.index}" style="font-weight: bold; text-align: right;">${installmentFeeHeadVO.installmentDetailVOs['0'].amount + installmentFeeHeadVO.installmentDetailVOs['1'].amount}</td>
		</tr>
	</c:forEach>
	 <tr class="grid_heading grid_heading_theme">
	   <td class="grid_item" style="font-weight: bold;">Total</td>
	   <td class="grid_item" style="font-weight: bold;" id="feetotal">${feetotal}</td>
	   <td class="grid_item" style="font-weight: bold;" id="frtsinstltotal">${frstinstltotal}</td>
	   <td class="grid_item" style="font-weight: bold;" id="scndinstltotal">${scndinstltotal}</td>
	   <td class="grid_item" style="font-weight: bold;" id="grandtotal">${frstinstltotal+scndinstltotal}</td>
	</tr>
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item" style="font-weight: bold;">Due Date</td>
	   <td class="grid_item" ></td>
	   <td class="grid_item" >
	   		<input type="hidden" name="firstInstlAYId" value="${firstInstlAYId}" />
	   		<input id="firstInstallmentDueDatePicker" size="12" type="text" name="firstInstallmentDueDateStr" readonly="readonly" value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${firstInstallmentDueDate}"/>' />
	   	</td>
	   <td class="grid_item" style="font-weight: bold;">
	   		<input type="hidden" name="secondInstlAYId" value="${secondInstlAYId}" />
	   		<input id="secondInstallmentDueDatePicker" size="12" type="text"  name="secondInstallmentDueDateStr" readonly="readonly" value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${secondInstallmentDueDate}"/>' />
	   </td>
	   <td class="grid_item" style="font-weight: bold;"></td>
	</tr>
	<tr class="grid_heading grid_heading_theme">
	   <td class="grid_item" style="font-weight: bold;">Late Fee Rule</td>
	   <td class="grid_item" ></td>
	   <td class="grid_item" >
	   		<select name="firstInstallmentLFRId">
	   			<option value=""></option>
	   			<c:forEach var="lateFeeRule" items="${lateFeeRules }">
	   				<option value="${lateFeeRule.id}" <c:if test="${lateFeeRule.id==firstInstallmentLateFeeRule.id}">selected="selected"</c:if>>${lateFeeRule.name}</option>
	   			</c:forEach>
	   		</select>
	   	</td>
	   <td class="grid_item" style="font-weight: bold;">
	   		<select name="secondInstallmentLFRId">
	   			<option value=""></option>
	   			
	   			<c:forEach var="lateFeeRule" items="${lateFeeRules }">
	   				<option value="${lateFeeRule.id}" <c:if test="${lateFeeRule.id==secondInstallmentLateFeeRule.id}">selected="selected"</c:if>>${lateFeeRule.name}</option>
	   			</c:forEach>
	   		</select>
	   </td>
	   <td class="grid_item" style="font-weight: bold;"></td>
	</tr>
</table>
</form>
<div style="text-align: center;margin-top: 30px">
	<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	<input type="button" value="Save" id="btnSaveInstallment" onclick="saveInstallments()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
</div>	

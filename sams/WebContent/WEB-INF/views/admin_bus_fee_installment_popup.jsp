<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<div class="working_area_spacer">
	<div style="width: 600px">
	<form id="busFeeInstlForm">
		<table class="grid grid_color_theme_border" width="100%">
		    <tr class="grid_heading grid_heading_theme">
			   <td class="grid_item">Fee Head</td>
			   <td class="grid_item">I installment (%)</td>
			   <td class="grid_item">II installment (%)</td>
			   <td class="grid_item bold">Total</td>
			</tr>
			<tr>
			   <td class="grid_item">Bus Fee</td>
			   <td class="grid_item"> 
			   		<input type="hidden" name="busFeeFirstInstallment.installment.id" value="1">
			   		<input type="hidden" name="busFeeFirstInstallment.id" value="${busFeeInstallmentVO.busFeeFirstInstallment.id}">
			   		<input type="text" id="busFeeInstl1" name="busFeeFirstInstallment.feePercent" value="${busFeeInstallmentVO.busFeeFirstInstallment.feePercent}" 
			   			   maxlength="3" size="15" class="numericBusFeeInstl" onkeyup="sumBusFeeInstallment()"/>
			   	</td>
			   <td class="grid_item">
			   		<input type="hidden" name="busFeeSecondInstallment.installment.id" value="2">
			   		<input type="hidden" name="busFeeSecondInstallment.id" value="${busFeeInstallmentVO.busFeeSecondInstallment.id}">
			   		<input type="text" id="busFeeInstl2" name="busFeeSecondInstallment.feePercent" value="${busFeeInstallmentVO.busFeeSecondInstallment.feePercent}" 
			   			   maxlength="3" size="15" class="numericBusFeeInstl" onkeyup="sumBusFeeInstallment()"/></td>
			   <td class="grid_item bold" id="busFeeInstlTotal">${busFeeInstallmentVO.busFeeFirstInstallment.feePercent+busFeeInstallmentVO.busFeeSecondInstallment.feePercent}</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center;margin-top: 30px">
		<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
		<input type="button" value="Save" id="btnSaveBussFeeInstallment" onclick="addBussFeeInstallment(${academicYearId})" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	</div>
	</div>
	</div>

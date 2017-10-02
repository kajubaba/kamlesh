<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<div class="working_area_spacer">
	<div style="width: 600px">
	<form id="hostelFeeInstlForm">
		<table class="grid grid_color_theme_border" width="100%">
		    <tr class="grid_heading grid_heading_theme">
			   <td class="grid_item">Fee Head</td>
			   <td class="grid_item">I installment (%)</td>
			   <td class="grid_item">II installment (%)</td>
			   <td class="grid_item bold">Total</td>
			</tr>
			<tr>
			   <td class="grid_item">Hostel Fee</td>
			   <td class="grid_item"> 
			   		<input type="hidden" name="hostelFeeFirstInstallment.installment.id" value="1">
			   		<input type="hidden" name="hostelFeeFirstInstallment.id" value="${hostelFeeInstallmentVO.hostelFeeFirstInstallment.id}">
			   		<input type="text" id="hostelFeeInstl1" name="hostelFeeFirstInstallment.feePercent" value="${hostelFeeInstallmentVO.hostelFeeFirstInstallment.feePercent}" 
			   			   maxlength="3" size="15" class="numericHostelFeeInstl" onkeyup="sumHostelFeeInstallment()"/>
			   	</td>
			   <td class="grid_item">
			   		<input type="hidden" name="hostelFeeSecondInstallment.installment.id" value="2">
			   		<input type="hidden" name="hostelFeeSecondInstallment.id" value="${hostelFeeInstallmentVO.hostelFeeSecondInstallment.id}">
			   		<input type="text" id="hostelFeeInstl2" name="hostelFeeSecondInstallment.feePercent" value="${hostelFeeInstallmentVO.hostelFeeSecondInstallment.feePercent}" 
			   			   maxlength="3" size="15" class="numericHostelFeeInstl" onkeyup="sumHostelFeeInstallment()"/></td>
			   <td class="grid_item bold" id="hostelFeeInstlTotal">${hostelFeeInstallmentVO.hostelFeeFirstInstallment.feePercent+hostelFeeInstallmentVO.hostelFeeSecondInstallment.feePercent}</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center;margin-top: 30px">
		<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
		<input type="button" value="Save" id="btnSaveHostelFeeInstallment" onclick="addHostelFeeInstallment(${academicYearId})" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	</div>
	</div>
	</div>

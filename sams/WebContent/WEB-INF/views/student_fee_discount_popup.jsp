<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="working_area_spacer">
	<div style="width: 1500px">
		
	<input type="hidden" id="noOfInstallmentsForFeeDiscount" value="${installmentCount}"/> 
<script type="text/javascript">
	var noOfHeadsForFeeDiscount='${fn:length(custInstllments)}';
</script>
<table class="grid grid_color_theme_border" width="100%">
			    <tr class="grid_heading grid_heading_theme">
				   <td class="grid_item">Fee Head</td>
				   <td class="grid_item">Total Fee (Rs)</td>
				   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				   		<td class="grid_item">${rowCounter.index}</td>
				   </c:forEach>
				   <td class="grid_item bold">Total</td>
				</tr>
				<c:forEach var="installmentFeeHeadVO" items="${custInstllments}" varStatus="feeHeadCounter">
					<c:choose>
					 		<c:when test="${feeHeadCounter.count % 2 != 0}">
								<tr class="grid_main_row_plain">
							</c:when>
							<c:otherwise>
							   	<tr class="grid_alt_row_plain">
							</c:otherwise>
					</c:choose>
					<td class="grid_item">
						${installmentFeeHeadVO.feeHeadName}
					</td>
					<td class="grid_item" id="feeDiscountHeadTotal_${feeHeadCounter.index}">
						<c:set var="feeHeadSum" value="${feeHeadSum+installmentFeeHeadVO.amount}"></c:set>
						${installmentFeeHeadVO.amount}
					</td>
					<c:forEach items="${installmentFeeHeadVO.installmentDetailVOs}" varStatus="instlCounter">
						<td class="grid_item">
							<input type="text" name="installmentFeeHeadVO[${feeHeadCounter.index}].installmentDetailVOs[${instlCounter.index}]" class="fee_dicnt_txt_box_numeric  fee_discnt_row_${feeHeadCounter.index} fee_discnt_col_${instlCounter.index}" maxlength="6" size="7" value="${installmentFeeHeadVO.installmentDetailVOs[rowCounter.index].amount}" onkeyup="sumDiscountedFee(${feeHeadCounter.index},${instlCounter.index})"/>
			 			</td>
					</c:forEach>
					<td id="fee_discnt_row_${feeHeadCounter.index}_total" class="grid_item bold">
				</td>
		</tr>
	</c:forEach>
	 <tr class="grid_heading grid_heading_theme">
	   <td class="grid_item bold">Total</td>
	   <td class="grid_item" style="font-weight: bold;">${feeHeadSum}</td>
		   <c:forEach var="i" begin="1" end="${installmentCount}" step="1" varStatus ="rowCounter">
				<td id="fee_discnt_col_${rowCounter.index-1}_total" class="grid_item bold"></td>
		   </c:forEach>
	   <td class="grid_item bold" id="fee_discnt_grand_total"></td>
	</tr>
</table>

<div style="text-align: center;margin-top: 30px">
	<input type="button" value="Close" class="button" onclick="closeStudentFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	<input type="button" value="Save" id="btnSaveFeeDiscount"  class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
</div>
		
		
	</div>
</div>	

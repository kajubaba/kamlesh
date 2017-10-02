<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    


<div class="working_area_spacer">
	<div style="width: 600px">
		<div style="margin-bottom: 10px; text-align: center;width: 100%">
			<select id="drpdwnayfeeid" style="width: 200px;" onchange="getInstallments()">
				<c:forEach var="academicYearFee" items="${academicYearFees}" varStatus="rowCounter">
					<option value="${academicYearFee.id}" <c:if test="${academicYearFee.admissionType.id==2}"> selected='selected' </c:if>  > ${academicYearFee.admissionType.name} Admission</option>
				</c:forEach>
			</select>
		</div>
		
		<div id="admntypeinstlContailer">
			<jsp:include page="admin_fee_installment.jsp"/>
		</div>
			
	</div>
</div>	

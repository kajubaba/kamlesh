<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="feeReciept">	
		<input type="hidden" id="genStudId" value="${studentId}" />
		<div class="recieptWidth feeRecieptH">${feeRecieptHeader.header}</div>
		<div class="recieptWidth feeRecieptSH">${feeRecieptHeader.subHeader}</div>
		<div class="recieptWidth labelFeeReciept">
			<span style="border: 1px solid;padding: 8px">Fee Receipt</span>
		</div>
		
		<table class="recieptWidth" padding="2px">
			<tr>
				<td style="float: right;padding-right: ">Receipt No : ${recieptNo}</td>
			</tr>
			<tr>
				<td style="float: right;">Date : <fmt:formatDate pattern="dd-MMM-yyyy" value="${recieptDate}" /></td>
			</tr>
			<tr>
				<td style="float: left;">
					Student ID : ${student.studentId}
				</td>
			</tr>
			<tr>
				<td style="float: left;">
					Student Name : ${student.firstName} ${student.lastName}&nbsp;&nbsp;
			<c:choose>
				<c:when test="${student.gender=='male' }">S/O</c:when>
				<c:otherwise>D/O</c:otherwise>
			</c:choose>
			&nbsp;&nbsp;${student.fatherName}
				</td>
			</tr>
			<tr>
				<td>
					<span style="float: left;">Class : ${classHistory.academicYearClass.displayName}</span>
					<span style="float: right;">Academic Session : ${classHistory.academicYear.name}</span>
				</td>
			</tr>
		</table>
		
		
				<table class="recieptWidth" style="border: 1px solid; margin-top: 10px;" >
					<tr style="border-bottom: 1px solid;">
						<td width="75%" style="border-right: 1px solid;border-bottom: 1px solid;height: 25px;padding-left: 50px;text-align: left;">Fee Head</td>
						<td width="25%" style="border-bottom: 1px solid; text-align: center;;height: 25px">Amount</td>
					</tr>
					<c:set var="amountTotal" value="0"></c:set>
					<c:forEach var="printFeeTransactionDetail" items="${printFeeTransactionDetails}" varStatus="rowCounter">
						<tr>
							<td style="border-right: 1px solid;text-align: left;padding-left: 50px;" valign="top">
								${printFeeTransactionDetail.feeHead.name}
							</td>
							<c:set var="amountTotal" value="${amountTotal+printFeeTransactionDetail.amount}"></c:set>
							<td style="text-align: center;" valign="top">${printFeeTransactionDetail.amount}</td>
						</tr>
					</c:forEach>
					<tr style="border-top: 1px solid;">
						<td style="border-right: 1px solid;border-top: 1px solid; height: 25px;text-align: left;padding-left: 50px;">Total</td>
						<td style="border-top: 1px solid; text-align: center; height: 25px">${amountTotal}</td>
					</tr>
				</table>
				
				<div style="margin-top: 15px; text-align: left;">Amount in words : ${amtWords}</div>
				<table class="recieptWidth" style="margin-top: 40px" >
					<tr>
						<td style="text-align: left;padding-left: 30px">Authorised Signatory</td>
						<td style="text-align: right;padding-right: 40px">Receiver</td>
					</tr>
				</table>
 </div>       	 
       
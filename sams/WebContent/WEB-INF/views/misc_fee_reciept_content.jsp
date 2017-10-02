<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ page import="com.narendra.sams.core.util.AmountInWords" %>
		
			
					<div class="recieptWidth feeRecieptH">${feeTransaction.feeRecieptHeader.header}</div>
					<div class="recieptWidth feeRecieptSH">${feeTransaction.feeRecieptHeader.subHeader}</div>
					<div class="recieptWidth labelFeeReciept">
						<span style="border: 1px solid;padding: 8px">Reciept</span>
					</div>
					
					<table class="recieptWidth" padding="2px">
						<tr>
							<td style="float: right;padding-right: ">Reciept No : ${feeTransaction.recieptNo}</td>
						</tr>
						<tr>
							<td style="float: right;">Date : <fmt:formatDate pattern="dd-MMM-yyyy" value="${feeTransaction.transactionTime}" /></td>
						</tr>
						<tr>
							<td style="float: left;">
								Student ID : ${feeTransaction.student.studentId}
							</td>
						</tr>
						<tr>
							<td style="float: left;">
								Student Name : ${feeTransaction.student.firstName} ${feeTransaction.student.lastName}&nbsp;&nbsp;
						<c:choose>
							<c:when test="${feeTransaction.student.gender=='male' }">S/O</c:when>
							<c:otherwise>D/O</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;${feeTransaction.student.guardianName}
							</td>
						</tr>
						<tr>
							<td>
								<span style="float: left;">Class : ${feeTransaction.studentClass.displayName}</span>
								<span style="float: right;">Academic Session : ${feeTransaction.studentClass.academicYear.name}</span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="float: left;">Paid For : ${feeTransaction.miscActivityCourseYear.miscActivity.name}</span>
								
							</td>
						</tr>
					</table>	
					<c:set var="amountTotal" value="0" />
					<table class="recieptWidth" style="border: 1px solid; margin-top: 10px;" >
						<tr style="border-bottom: 1px solid;">
							<td width="75%" style="border-right: 1px solid;border-bottom: 1px solid;height: 25px;padding-left: 50px;text-align: left;">Fee Head</td>
							<td width="25%" style="border-bottom: 1px solid; text-align: center;;height: 25px">Amount</td>
						</tr>
						
							<c:forEach var="printFeeTransactionDetail" items="${feeTransaction.miscFeeTransactionDetails}" varStatus="rowCounter">
								<tr>
									<td style="border-right: 1px solid;text-align: left;padding-left: 50px;" valign="top">
											${printFeeTransactionDetail.miscActivityHead.headName}
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
							<td style="text-align: right;padding-right: 40px">Reciever</td>
						</tr>
					</table>
				
			
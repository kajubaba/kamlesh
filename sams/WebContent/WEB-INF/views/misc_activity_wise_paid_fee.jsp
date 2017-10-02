<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">Misc Program - Paid Fee</span></div>
		<div class="working_area_spacer">
			
			<div class="search_param">
				<form action="<c:url value='/miscfee/report/mawise' />" method="get">
					<table width="700px">
						<tr>
							<td class="bold">Academic Year : </td>
							<td> 
								<select name="ayid">
									<option value=""></option>
									<c:forEach var="academicYear" items="${academicYears}">
										<option value="${academicYear.id}" <c:if test="${sayid==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
									</c:forEach>
								</select> 
							</td>
							<td class="bold">From : </td>
							<td> <input id="fromDate" name="from" type="text" readonly="readonly" value="${sfromdate}" > </td>
							<td class="bold">To : </td>
							<td> <input id="toDate" name="to" type="text" readonly="readonly" value="${stodate}"> </td>
							<td> <input type="submit" class="button" value="Search"> </td>
						</tr>	
					</table>
				</form>
			</div>
			
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(paidFees)!=0 }">
			         		Displaying 1-${fn:length(paidFees)} of ${fn:length(paidFees)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="paid_fee_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
			            <th class="grid_item">Academic Year</th>
			            <th class="grid_item">Misc Program</th>
			            <th class="grid_item">Fee (Rs.)</th>
			          </tr>
		          </thead>
		          <tbody>
		          		<c:forEach var="paidFee" items="${paidFees}" varStatus="rowCounter">
				         	<tr class="grid_main_row">
					           	<td class="grid_item">
					           		${paidFee.academicYearName}
					           	</td>
					           	<td class="grid_item">
					        		<a href="<c:url value="/miscfee/report/tr?ayid=${paidFee.academicYearId}&from=${sfromdate}&to=${stodate}&maid=${paidFee.feeHeadId}" />">${paidFee.feeHeadName}</a>
					            </td>
					            <td class="grid_item">
					        		
					        		<c:set var="grandSum" value="${grandSum+paidFee.paidFee}"></c:set>
					        		
					        		${paidFee.paidFee}
					            </td>
			           		</tr>
			           		</c:forEach>
		          </tbody>
		          <tfoot>
		          		<tr class="grid_heading grid_heading_theme">
				            <td class="grid_item"></td>
				            <td class="grid_item"></td>
				            <td class="grid_item">${grandSum}</td>
	                     </tr>
		          </tfoot>
		     </table>
		
		     <div class="grid_info" style="margin-top: 12px">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(paidFees)!=0 }">
			         		Displaying 1-${fn:length(paidFees)} of ${fn:length(paidFees)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>					
			
		
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>
<script type="text/javascript">


$(function() {
	
	$( "#fromDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	
	$( "#toDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	$('#paid_fee_table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false
    } );
	
});

</script>

        	 
       
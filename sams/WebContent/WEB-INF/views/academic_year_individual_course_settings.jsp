<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div style="padding-top: 15px">

		<c:choose>
      		<c:when test="${null != courseYearSettings && fn:length(courseYearSettings) > 0 }">
      		
                 <table class="grid grid_color_theme_border">
                      <tr class="grid_heading grid_heading_theme">
                        <td class="grid_item">Course</td>
                        <td class="grid_item">Year/Semester</td>
                        <td class="grid_item">Active</td>
                        <td class="grid_item">Intake</td>
                        <td class="grid_item">Fee (Rs.)</td>
                        <td class="grid_item">Installments #</td>
                      </tr>
                      
                      <c:forEach var="courseYearSetting" items="${courseYearSettings}" varStatus="courseYearSettingCounter">
                      		<c:choose>
				          		<c:when test="${courseYearSettingCounter.count % 2 != 0}">
				          			<tr class="grid_main_row_plain">
				          		</c:when>
				          		<c:otherwise>
				    	      		<tr class="grid_alt_row_plain">
					          	</c:otherwise>
				          	</c:choose>
				          	<%-- <td class="grid_item"> <a href="javascript:void(0)" onclick="viewCourseYearSettingProp(${courseYearSetting.id})">  ${courseYearSetting.courseYear.course.name}</a></td> --%>
				          	<td class="grid_item">${courseYearSetting.courseYear.course.name}</td>
				          	<td class="grid_item">${courseYearSetting.courseYearType.name}</td>
				          	<td class="grid_item">
				          		<c:choose>
									<c:when test="${courseYearSetting.active==true}">Yes</c:when>
									<c:otherwise>No</c:otherwise>
							    </c:choose>
				          	</td>
				          	<td class="grid_item">${courseYearSetting.intake}</td>
				          	<td class="grid_item"> 
				          		<c:if test="${courseYearSetting.active==true}">
				          			<a id="fee-${courseYearSetting.courseYear.id}"  href="javascript:void(0)" onclick="getFeePupup('${academicYearCourse.course.displayName} ${courseYearSetting.courseYear.name} Year - Customize Fee',${courseYearSetting.id},'fee-${courseYearSetting.courseYear.id}')">
				          			<c:choose>
							          		<c:when test="${null!=courseYearSetting.academicYearFees && fn:length(courseYearSetting.academicYearFees)>0}">
							          			<c:forEach var="academicYearFee" items="${courseYearSetting.academicYearFees}">
							          				<c:choose>
										          		<c:when test="${academicYearFee.admissionType.name=='New'}">
										          			${academicYearFee.totalFee} (N) , 		
										          		</c:when>
										          		<c:otherwise>
										    	      		${academicYearFee.totalFee} (R) ,
											          	</c:otherwise>
							          				</c:choose>
				          						</c:forEach>
							          		</c:when>
							          		<c:otherwise>
							    	      		NA
								          	</c:otherwise>
				          			</c:choose>
				          			
				          			</a>
				          		</c:if>
				          	</td>
				          	<td class="grid_item"> 
				          			<c:if test="${courseYearSetting.active==true}">
				          			<a href="javascript:void(0)" onclick="manageInstallments('${courseYearSetting.id}', 'new')">
					          				<c:if test="${null!=courseYearSetting.academicYearFees && fn:length(courseYearSetting.academicYearFees)>0}">
					          					Customize New
					          				</c:if>
					          			</a>
					          			&nbsp;&nbsp;&nbsp;&nbsp;
					          			<a href="javascript:void(0)" onclick="manageInstallments('${courseYearSetting.id}','regular')">
					          				<c:if test="${null!=courseYearSetting.academicYearFees && fn:length(courseYearSetting.academicYearFees)>0}">
					          					Customize Regular
					          				</c:if>
					          			</a>
				          			
				          			
				          			<!-- 
				          			<a id="instl-${courseYearSetting.courseYear.id}" href="javascript:void(0)" onclick="manageInstallments('${academicYearCourse.course.displayName} ${courseYearSetting.courseYear.name} Year - Customize Installments',${courseYearSetting.id},'fee-${courseYearSetting.courseYear.id}')">
				          				<c:if test="${null!=courseYearSetting.academicYearFees && fn:length(courseYearSetting.academicYearFees)>0}">
				          					Customize
				          				</c:if>
				          			</a>
				          			 -->
				          			 
				          			<!-- 
				          			<a id="instl-${courseYearSetting.courseYear.id}" href="javascript:void(0)" onclick="getInstallmentsPopupView('${academicYearCourse.course.displayName} ${courseYearSetting.courseYear.name} Year - Customize Installments',${courseYearSetting.id},'fee-${courseYearSetting.courseYear.id}')">
				          				<c:if test="${null!=courseYearSetting.academicYearFees && fn:length(courseYearSetting.academicYearFees)>0}">
				          					Customize
				          				</c:if>
				          			</a>
				          			 -->
				          			</c:if>   
				          	 </td>
				          	<%-- 
				          	<td class="grid_item"></td>
				          	--%>
                        	
                        </tr>	
				       </c:forEach>
				                      
                      
                      
                    </table>
              
         
	  		
	  
	  
      	
      	
      	</c:when>
      	<c:otherwise>
      			<div style="width:100%; padding-top: 100px;text-align: center;">
      				 No settings are found for selected affiliation authority<br/><br/>
      				 Click <a href="javascript:void(0)" onclick="getReminingCourseListPopup()">here</a> to add course(s)
      			 </div>
      	</c:otherwise>
      </c:choose> 
      
</div>
            

      
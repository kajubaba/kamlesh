<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
 
 <div id="content_area">
  <div id="wider_working_area" class="color_theme_border">
      <div id="enquiry_dashboard_container" class="working_area_spacer">
	  
	  	Welcome	<security:authentication property="principal.firstName" /> <security:authentication property="principal.lastName" />
		
			
		
	  </div>
   </div>
   </div>

<script type="text/javascript">
	function switchAcademicYear(){
		var url='<c:url value="/enquiry/dashboard" />';
		url=url+"?academicYearId="+$("#ayId").val();
		window.location = url;
	}
</script>

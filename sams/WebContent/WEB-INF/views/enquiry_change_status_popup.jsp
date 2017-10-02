<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  	

	  <div class="modal-dialog" role="document">
<div class="modal-content">
	    <div class="modal-header">
        		<h4 class="modal-title"> <span class="glyphicon glyphicon-plus-sign"></span> 
        			Follow enquiry
        		</h4>
      	</div>
      	<div class="modal-body">
      		<div class="form-group">
	            <label><span class="mandatory_mark">*</span> Status :</label>
	            <select id="enqStatusList" class="form-control">
	  							<c:forEach var="enqStatus" items="${enqStatusList}">
	  								<option value="${enqStatus.id}">${enqStatus.name}</option>
	  							</c:forEach>
	  						</select>
	          </div>
	          <div class="form-group">
	            <label>Comments :</label>
	            <textarea class="form-control"  rows="5" cols="47" maxlength="1024" id="changeStatusComments"></textarea>
	          </div>
      	</div>
      	<div class="modal-footer">
	  		<button type="button" class="btn btn-default"  onclick="closeEnqActivityPopup()"><span class="glyphicon glyphicon-plus-sign" ></span> Cancel</button>
	  		<button type="button" class="btn btn-primary"  onclick="changeEnquiryStatus()"><span class="glyphicon glyphicon-plus-sign" ></span> Change Status</button>
      	</div>
</div>  	
</div> 
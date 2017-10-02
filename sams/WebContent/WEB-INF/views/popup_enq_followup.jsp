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
	            <label><span class="mandatory_mark">*</span> Follow up Activity :</label>
	            <select id="followupActivities" class="form-control">
	  						<option value="Visited student home">Visited student home</option>
							<option value="Student visit to institute">Student visit to institute </option>
							<option value="Guardian visit to institute">Guardian visit to institute</option>
							<option value="Call to student">Call to student</option>
	  					</select>
	          </div>
	          <div class="form-group">
	            <label>Comments :</label>
	            <textarea class="form-control"  rows="5" cols="47" maxlength="1024" id="followUpComments"></textarea>
	          </div>
      	</div>
      	<div class="modal-footer">
	  		<button type="button" class="btn btn-default"  onclick="closeEnqActivityPopup()"><span class="glyphicon glyphicon-plus-sign" ></span> Cancel</button>
	  		<button type="button" class="btn btn-primary"  onclick="followEnquiry()"><span class="glyphicon glyphicon-plus-sign" ></span> Save</button>
      	</div>
</div>  	
</div> 
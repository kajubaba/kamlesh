<jsp:include page="ang_header.jsp" />

<div id="page-wrapper-full-screen">
	<div class="container-fluid">
		<div ng-controller="acad.manage.class.section.controller as secCtrl" ng-init="secCtrl.initClasswiseSectionCount()">
	  <!-- Row -1 -->
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <h3 class="page-header">
              	 <div class="row">
                <div class="col-sm-6 col-md-8" ng-controller="sams.academicsession.controller as academicSeasonCtrl">
              	<select class="dropdown-in-page-header" ng-model="academicYear.selectedAcademicYear" ng-options="academicYear.academicYearId as academicYear.academicYearName for academicYear in academicYear.academicYears" ng-change="examCtrl.changeAcademicYear(academicYear.selectedAcademicYear)"></select>
              	Academics  <small>Settings</small>  
              </div>
              
              <div class="col-sm-6 col-md-4">
                <div class="row">
					<div class="col-sm-10 col-md-10">
						 <div class="form-group input-group" ng-controller="student.quick.search.controller as searchCtrl" ng-init="searchCtrl.init()">
							  <input id="txtBoxsearchStud" type="text" class="form-control" placeholder="Search by Student ID or Name">
							  <span class="input-group-btn" >
								<button class="btn btn-default" type="button"><i class="fa fa-search"></i> </button>
							  </span> 
						</div>
					</div>
					<div class="col-sm-2 col-md-2">
				
					</div>
					<div class="clearfix visible-sm-block visible-md-block"></div>
				</div>
              </div>
            <div class="clearfix visible-sm-block visible-md-block"></div>
			</div>  
          </h3>
        </div>
      </div>
      
      <div class="row">
      		<div class="navbar-default sidebar" role="navigation">
	      		<div class="sidebar-nav navbar-collapse">
	      			  <ul class="nav" id="side-menu">
	      			  		<li>
	                            <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Manage Class Subject</a>
	                        </li>
	                        <li>
	                            <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Manage Class Section</a>
	                        </li>
	                             <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Exam<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="flot.html">Exam Schemes</a>
                                </li>
                                <li>
                                    <a href="morris.html">Evaluation Type</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
	      			  </ul>
	      		</div>
    	 </div>
      </div>
       <div id="page-wrapper">
      
       </div>
     
</div>
				
	</div>
	
</div>
<jsp:include page="ang_footer.jsp" />
	
	
	
	
	
	

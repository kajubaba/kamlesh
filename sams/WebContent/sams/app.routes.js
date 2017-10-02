samsApp.config(['$routeProvider', function($routeProvider) {
	
	$routeProvider.
	
	when('/admission', {
		templateUrl: "sams/components/admission/admission_dashboard.html"
	}).
	
	when('/admission/underscheme/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/student_list_under_scheme.html"
	}).
	
	when('/admissions/studentview/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/student_list.html"
	}).
	
	when('/admissions/classview/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/classwise_admission_count.html"
	}).
	
	when('/admissions/classwise/:studentStatusId/:classId/:academicYearId', {
		templateUrl: "sams/components/admission/classwise_student_list.html"
	}).
	
	when('/admissions/busfacility/students/status/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_student_list.html"
	}).
	
	when('/admissions/nobus/students/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_not_adopted_student_list.html"
	}).
	
	when('/admissions/busfacility/classwise/admissioncount/status/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_classwise_admission_count.html"
	}).
	
	when('/admissions/busfacility/classwise/admissions/:studentStatusId/:classId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_classwise_admissions.html"
	}).
	
	when('/admissions/busfacility/busstopwise/admissioncount/status/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_busstopwise_admission_count.html"
	}).
	
	when('/admissions/busfacility/busstopwise/admissions/:studentStatusId/:busStopId/:academicYearId', {
		templateUrl: "sams/components/admission/bus_facility_busstopwise_admissions.html"
	}).
	
	when('/admissions/new', {
		templateUrl: "sams/components/admission/admission_new.html",
		permission: 'ROLE_NEW_REGISTRATION'
	}).
	
	when('/admissions/studentdetails/:studentId', {
		templateUrl: "sams/components/student/student_details.html"
	}).
	when('/admissions/student/:studentId/conversation', {
		templateUrl: "sams/components/student/student_conversation_view.html"
	}).
	
	when('/admissions/studentdetails/:studentId/edit', {
		templateUrl: "sams/components/admission/admission_edit.html"
	}).
	
	
	/*  --- Admission renewal URLs - START --- */
	
	when('/admissions/toberenewed', {
		templateUrl: "sams/components/admission/renewalpending/admisions_to_be_renewed.html",
		permission : "ROLE_RENEW_ADMISSION"
	}).
	
	when('/admissions/toberenewed/classwise', {
		templateUrl: "sams/components/admission/renewalpending/admisions_to_be_renewed_class_wise.html",
		permission : "ROLE_RENEW_ADMISSION"
	}).
	
	when('/admissions/toberenewed/class/:academicYearClassId', {
		templateUrl: "sams/components/admission/renewalpending/class_admisions_to_be_renewed.html",
		permission : "ROLE_RENEW_ADMISSION"
	}).
	
	when('/admission/renew/:studentId', {
		templateUrl: "sams/components/admission/renewalpending/admission_renew.html",
		permission : "ROLE_RENEW_ADMISSION"
	}).
	
	/*  --- Admission renewal URLs - END --- */
	
	/* fee module*/
	
	when('/fee/dashboard/', {
		templateUrl: "sams/components/fee/fee_dashboard.html"
	}).
	
	
	
	when('/fee/studentfee/:studentId/:classHistoryId', {
		templateUrl: "sams/components/fee/student/student_fee_view.html"
	}).
	
	when('/fee/student/:studentId/paymenthistory', {
		templateUrl: "sams/components/fee/student/student_payment_history.html"
	}).
	
	when('/fee/student/:studentId/feehistory', {
		templateUrl: "sams/components/fee/student/student_fee_history.html"
	}).
	
	when('/fee/pay/student/:studentId/:academicYearFeeIInstallmentId/:customiseFeeInstallmentId/:installmentId/:classHistoryId', {
		templateUrl: "sams/components/fee/student/student_pay_fee.html",
		permission : "ROLE_STUD_FEE_COLLECT"
	}).
	
	when('/fee/student/customize/:studentId/class/:classHistoryId/', {
		templateUrl: "sams/components/fee/student/student_fee_customize.html",
		permission : "ROLE_ADJUST_STUD_ACADEMIC_FEE"
	}).
	
	
	
	when('/fee/reports/annualfee/:academicYearId/:studentStatusId', {
		templateUrl: "sams/components/fee/reports/annual_fee.html",
		permission : "ROLE_VIEW_ANNUAL_FEE_REPORT"
	}).
	
	when('/fee/reports/duefee/:academicYearId/:dueDate/:admissionStatus', {
		templateUrl: "sams/components/fee/reports/due_fee.html",
		permission : "ROLE_VIEW_DUE_FEE_REPORT"
	}).
	
	when('/fee/reports/duefee/duestudents/:academicYearId/:courseYearId/:dueDate/:admissionStatus', {
		templateUrl: "sams/components/fee/reports/due_students_of_class.html",
		permission : "ROLE_VIEW_DUE_FEE_REPORT"
	}).
	
	when('/fee/duestudents/:academicYearId/:dueDate/:admissionStatus', {
		templateUrl: "sams/components/fee/reports/due_students_all.html",
		permission : "ROLE_VIEW_DUE_FEE_REPORT"
	}).

	/* Paid Fee  */
	
	when('/fee/paid/transactions/:academicYearId/:fromDate/:toDate', {
		templateUrl: "sams/components/fee/paidfee/fee_transaction_list.html"
	}).
	
	when('/fee/paid/transactions/todays/:academicYearId', {
		templateUrl: "sams/components/fee/paidfee/fee_transaction_list_todays.html"
	}).
	
	when('/fee/paid/headwise/:academicYearId/:fromDate/:toDate', {
		templateUrl: "sams/components/fee/paidfee/headwise_paidefee.html"
	}).
	
	when('/fee/paid/headwisewise/details/:academicYearId/:feeHeadId/:fromDate/:toDate', {
		templateUrl: "sams/components/fee/paidfee/headwise_paidfee_details.html"
	}).
	
	when('/fee/paid/in-head/dateview/:academicYearId/:feeHeadId/:fromDate/:toDate', {
		templateUrl: "sams/components/fee/paidfee/paid_fee_in_head_date_view.html"
	}).

	when('/fee/paid/classwise/:academicYearId/:fromDate/:toDate', {
		templateUrl: "sams/components/fee/paidfee/classwise_paidefee.html"
	}).
	
	/*// Paid Fee */
	
	when('/fee/adjusted/students/:academicYearId', {
		templateUrl: "sams/components/fee/adjustedstudent/fee_adjusted_students.html"
	}).
	
	when('/fee/adjusted/students/:academicYearId', {
		templateUrl: "sams/components/fee/adjustedstudent/fee_adjusted_students.html"
	}).
	
	
	/*//fee module*/
	
	when('/student/activityview/:studentId', {
		templateUrl: "sams/components/student/student_activity_view.html",
		permission: 'ROLE_CHANGE_STUD_CLASS | ROLE_CHANGE_STUD_BUS_STOP | ROLE_CHANGE_STUD_ADMN_SCHEME'
	}).
	
	/* Student Activity  */
	
	/*   */
	
	/*Transportation module*/
	
	when('/transportation/dashbord', {
		templateUrl: "sams/components/transportation/transportation_dashboard.html"
	}).
	
	when('/transportation/settings', {
		templateUrl: "sams/components/transportation/settings/trans_settings.html"
	}).
	
	when('/transportation/vehicle/list', {
		templateUrl: "sams/components/transportation/vehicle/vehicle_list.html"
	}).
	
	when('/transportation/vehicle/:vehicleId', {
		templateUrl: "sams/components/transportation/settings/vehicle/vehicle.html"
	}).
	
	when('/transportation/academicyearvehicle/list', {
		templateUrl: "sams/components/transportation/academicyearvehicle/academic_year_vehicle_list.html"
	}).
	
	when('/transportation/route/list', {
		templateUrl: "sams/components/transportation/route/route_list.html"
	}).
	
	when('/transportation/route/:routeId', {
		templateUrl: "sams/components/transportation/settings/route/route.html"
	}).
	
	when('/transportation/driver/list', {
		templateUrl: "sams/components/transportation/settings/driver/driver_list.html"
	}).
	
	when('/transportation/driver/:driverId', {
		templateUrl: "sams/components/transportation/settings/driver/driver.html"
	}).
	
	when('/transportation/route/plan/:routeId', {
		templateUrl: "sams/components/transportation/route/route_plan.html"
	}).

	
	when('/transportation/busstop/list', {
		templateUrl: "sams/components/transportation/busstop/bus_stop_list.html"
	}).
	
	when('/transportation/busstop/pickupdroppoints/:busStopId', {
		templateUrl: "sams/components/transportation/busstop/bus_stop_pickup_drop_points.html"
	}).
	
	when('/transportation/students/busadopted/list', {
		templateUrl: "sams/components/transportation/students/bus_adopted_students.html"
	}).
	
	when('/transportation/students/busadopted/classwisecount', {
		templateUrl: "sams/components/transportation/students/classwise_admission_count.html"
	}).
	when('/transportation/students/busadopted/busstopwisecount', {
		templateUrl: "sams/components/transportation/students/busstopwise_admission_count.html"
	}).
	
	when('/transportation/students/busstopwise/:busStopId', {
		templateUrl: "sams/components/transportation/students/busstopwise_admissions.html"
	}).
	
	when('/transportation/students/classwise/:classId', {
		templateUrl: "sams/components/transportation/students/classwise_admissions.html"
	}).
	
	when('/transportation/students/pickupdroppoint/:busStopId', {
		templateUrl: "sams/components/transportation/students/busstopwise_students_with_pickup_drop_info.html"
	}).
	
	when('/transportation/bus/:academicSessionBusId/students/arrival', {
		templateUrl: "sams/components/transportation/academicyearvehicle/bus_arrival_students.html"
	}).
	
	when('/transportation/bus/:academicSessionBusId/students/departure', {
		templateUrl: "sams/components/transportation/academicyearvehicle/bus_departure_students.html"
	}).
	
	when('/transportation/student/:studentId', {
		templateUrl: "sams/components/transportation/student/student_details.html"
	}).
	
	when('/transportation/translations/students', {
		templateUrl: "sams/components/transportation/settings/translations/student_translations.html"
	}).
	
	when('/transportation/translations/busstops', {
		templateUrl: "sams/components/transportation/settings/translations/busstop_translations.html"
	}).
	
	/*  //  Transportation module*/
	
	
	when('/fee/adjust/latefee/:studentId/:classHistoryId', {
		templateUrl: "sams/components/fee/student/student_adjust_late_fee.html",
		permission: 'ROLE_ADJUST_STUD_LATE_FEE'
	}).
	
	/* Academic module end*/
	
	when('/unauthorized', {
		templateUrl: "sams/access_denied.html"
	}).
	
	/**
	 * Academics Module start
	 */
	

	when('/academics', {
		templateUrl: "sams/components/academics/academics_dashboard.html"
	}).
	when('/academics/studentview/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/academics/student-list/student_list.html"
	}). 
	when('/academics/classview/:studentStatusId/:academicYearId', {
		templateUrl: "sams/components/academics/student-list/classwise_admission_count.html"
	}).
	when('/academics/classwise/:studentStatusId/:classId/:academicYearId', {
		templateUrl: "sams/components/academics/student-list/classwise_student_list.html"
	}).
	when('/academics/classsubjects/:academicYearId', {
		templateUrl: "sams/components/academics/subject/classwise_subject.html"
	}).
	
	when('/academics/classsubjectDetails/:academicYearId', {
		templateUrl: "sams/components/academics/subject/classwise_subject_details.html"
	}).
	when('/acad/acadclasssections/:academicYearId', {
		templateUrl: "sams/components/acad/classSection/classwise_section.html"
	}).
	
	when('/acad/classSectionDetails/:academicYearId', {
		templateUrl: "sams/components/acad/classSection/classwise_section_details.html"
	}).
	
	when('/academics/birthdaylist/todays', {
		templateUrl: "sams/components/academics/birthday/birthday_student_list.html"
	}).
	
	when('/academics/mgstudsctn/:academicYearId', {
		templateUrl: "sams/components/academics/managesection/manage_student_section.html"
	}).
	when('/academics/assessment/sch/:academicYearId', {
		templateUrl: "sams/components/academics/exam/student_scholastic_score_collection.html",
		permission: 'ROLE_ACADEMICS_EXAM_MARKS_COLLECTION'
		
	}).
	when('/academics/assessment/co-sch/:academicYearId', {
		templateUrl: "sams/components/academics/exam/co_scholastic_assessment_student_search.html"
	}).
	when('/academics/assessment/co-sch/:academicYearId/:auId/:classId/:sectionId/:termId', {
		templateUrl: "sams/components/academics/exam/co_scholastic_assessment_student_search.html",
		permission: 'ROLE_ACADEMICS_EXAM_MARKS_COLLECTION'
	}).
	
	when('/academics/attendance/:academicYearId/:auId/:classId/:sectionId/:termId', {
		templateUrl: "sams/components/academics/exam/student_attendance_collection.html",
		
	}).
	
	when('/academics/rollno/:academicYearId/:auId/:classId/:sectionId', {
		templateUrl: "sams/components/academics/exam/student_roll_no.html",
		
	}).
	
	when('/academics/assessment/co-sch/stud/:studnetId/:classId/:termId', {
		templateUrl: "sams/components/academics/exam/student_co_scholastic_score_collection.html"
	}).
	when('/student/scorecard/:studentId/:classId', {
		templateUrl: "sams/components/academics/exam/student_scorecard.html"
	}).
	when('/academics/settings/:academicYearId', {
		templateUrl: "sams/components/academics/settings/academics_settings.html"
	}).
	
	/* Academics - Manage Class Section */
	when('/academics/settings/section/:academicYearId', {
		templateUrl: "sams/components/academics/settings/section/classwise_section_count.html"
	}).
	when('/academics/settings/section/:academicYearId/class/:classId', {
		templateUrl: "sams/components/academics/settings/section/class_sections.html"
	}).
	
	/*// Academics - Manage Class Section */
	
	/* Academics - Manage Class Subjects */
	when('/academics/settings/subject/:academicYearId', {
		templateUrl: "sams/components/academics/settings/subject/classwise_subject_count.html"
	}).
	
	when('/academics/settings/subject/:academicYearId/class/:classId', {
		templateUrl: "sams/components/academics/settings/subject/class_subjects.html"
	}).
	
	/* Academics - Manage Class Subjects */
	
	/* Academics - Setup Exams */
	
	when('/academics/settings/exam/patterns/:academicYearId', {
		templateUrl: "sams/components/academics/settings/exam/exam_patterns.html"
	}).
	
	when('/academics/settings/:academicYearId/setup/exam-pattern/:examPatternId/step1', {
		templateUrl: "sams/components/academics/settings/exam/exam_pattern_classes.html"
	}).
	
	when('/academics/settings/:academicYearId/setup/exam-pattern/:examPatternId/step2', {
		templateUrl: "sams/components/academics/settings/exam/setup_scholatic_assessment.html"
	}).
	
	when('/academics/settings/:academicYearId/setup/exam-pattern/:examPatternId/step3', {
		templateUrl: "sams/components/academics/settings/exam/exam_pattern_manage_coscholastic_aspects.html"
	}).
	when('/academics/settings/:academicYearId/setup/exam-pattern/:examPatternId/step4', {
		templateUrl: "sams/components/academics/settings/exam/setup_coscholatic_assessment.html"
	}).
	when('/academics/settings/:academicYearId/setup/exam-pattern/:examPatternId/step5', {
		templateUrl: "sams/components/academics/settings/exam/blank_scorecard.html"
	}).
	when('/academics/print/reportcard/:academicYearId/:auId/:classId/:sectionId/:termId', {
		templateUrl: "sams/components/academics/exam/print_marksheet.html"
	}).
	
	/*// Academics - Setup Exams */
	
	
	
	when('/academics/settings/exam/manage/csa', {
		templateUrl: "sams/components/academics/settings/exam/co_scholastic_aspect.html"
	}).
	
	when('/academics/student/scorecard/:studentId', {
		templateUrl: "sams/components/academics/student/student_scorecard.html"
	}).
	
	when('/reports', {
		templateUrl: "sams/components/reports/reports_main_page.html"
	}).
	
	when('/reports/conversation/:from/:to/:mode', {
		templateUrl: "sams/components/reports/communication/student_conversation.html"
	}).
	
	when('/admin/settings', {
		templateUrl: "sams/components/admin/admin_settings.html"
	}).
	
	when('/admin/settings/core', {
		templateUrl: "sams/components/admin/settings/settings.html"
	}).
	
	when('/admin/settings/sms', {
		templateUrl: "sams/components/admin/communication/sms-settings.html"
	}).
	
	when('/admin/busstop/list', {
		templateUrl: "sams/components/admin/bus-stop/bus_stop_list.html"
	}).
	when('/admin/busstop/:busStopId', {
		templateUrl: "sams/components/admin/bus-stop/bus_stop.html"
	}).
	
	when('/admin/feehead/list', {
		templateUrl: "sams/components/admin/fee-head/fee_head_list.html"
	}).
	when('/admin/feehead/:feeHeadId', {
		templateUrl: "sams/components/admin/fee-head/fee_head.html"
	}).
	
	when('/admin/affiliationauthority/list', {
		templateUrl: "sams/components/admin/affiliation-authority/affiliation_authority_list.html"
	}).
	when('/admin/affiliationauthority/:affiliationAuthorityId', {
		templateUrl: "sams/components/admin/affiliation-authority/affiliation_authority.html"
	}).
	
	when('/admin/course/list', {
		templateUrl: "sams/components/admin/course/course_list.html"
	}).
	when('/admin/course/:courseId', {
		templateUrl: "sams/components/admin/course/course.html"
	}).
	
	when('/admin/latefeerule/list', {
		templateUrl: "sams/components/admin/latefee-rule/latefee_rule_list.html"
	}).
	
	when('/admin/admissionscheme/list', {
		templateUrl: "sams/components/admin/admission-scheme/admission_scheme_list.html"
	}).
	
	when('/admin/admissionscheme/:admissionSchemeId', {
		templateUrl: "sams/components/admin/admission-scheme/admission_scheme.html"
	}).
	
	when('/admin/studentcategory/list', {
		templateUrl: "sams/components/admin/student-category/student_category_list.html"
	}).
	
	
	
	when('/admin/studentcategory/:studentCategoryId', {
		templateUrl: "sams/components/admin/student-category/student_status.html"
	}).
	
	when('/admin/studentstatus/list', {
		templateUrl: "sams/components/admin/student-status/student_status_list.html"
	}).
	when('/admin/studentstatus/:studentStatusId', {
		templateUrl: "sams/components/admin/student-status/student_status.html"
	}).
	
	when('/admin/enquirystatus/list', {
		templateUrl: "sams/components/admin/enquiry/enquiry_status_list.html"
	}).
	when('/admin/enquirystatus/:enquiryStatusId', {
		templateUrl: "sams/components/admin/enquiry/enquiry_status.html"
	}).
	
	when('/admin/academicsession/list', {
		templateUrl: "sams/components/admin/academic-session/academic_session_list.html"
	}).
	when('/admin/academicsession/:academicSessionId', {
		templateUrl: "sams/components/admin/academic-session/academic_session.html"
	}).
	
	when('/admin/academicsession/:academicSessionId/setup/busstop', {
		templateUrl: "sams/components/admin/academic-session/academic_session_bus_stops.html"
	}).
	when('/admin/academicsession/:academicSessionId/edit/busfee', {
		templateUrl: "sams/components/admin/academic-session/academic_session_edit_bus_stop_fee.html"
	}).
	when('/admin/academicsession/:academicSessionId/assign/busstop', {
		templateUrl: "sams/components/admin/academic-session/academic_session_unassigned_bus_stops.html"
	}).
	
	when('/admin/academicsession/:academicSessionId/setup/busfee/installments', {
		templateUrl: "sams/components/admin/academic-session/bus_fee_installments.html"
	}).
	
	when('/admin/academicsession/:academicSessionId/setup/classes', {
		templateUrl: "sams/components/admin/academic-session/academic_session_classes.html"
	}).
	
	when('/admin/academicsession/setup/classfee/:academicSessionId/:courseYearSettingId', {
		templateUrl: "sams/components/admin/academic-session/academic_session_class_detail.html"
	}).
	
	when('/admin/academicsession/setup/installments/:academicSessionId/:courseYearSettingId/:admissionTypeId', {
		templateUrl: "sams/components/admin/academic-session/academic_session_class_installments.html"
	}).
	
	when('/admin/academicsession/:academicSessionId/setup/admissionschemes', {
		templateUrl: "sams/components/admin/academic-session/academic_session_admission_schemes.html"
	}).
	when('/admin/academicsession/:academicSessionId/assign/admissionscheme', {
		templateUrl: "sams/components/admin/academic-session/academic_session_unassigned_admission_schemes.html"
	}).
	when('/admin/academicsession/:academicSessionId/setup/documents', {
		templateUrl: "sams/components/admin/academic-session/academic_session_documents.html"
	}).
	
	/**
	 * Academics Module end
	 */
	
	when('/enquiry/dashboard', {
		templateUrl: "sams/components/enquiry/enquiry_dashboard.html"
	}).
	when('/enquiry/list/:academicYearId', {
		templateUrl: "sams/components/enquiry/enquiry_list.html"
	}).
	
	when('/enquiry/list/:academicYearId/issued-form', {
		templateUrl: "sams/components/enquiry/enquiry_issued_form.html"
	}).
	
	when('/enquiry/list/classwise/:academicYearId', {
		templateUrl: "sams/components/enquiry/classwise_enquiry_count.html"
	}).
	
	when('/enquiry/list/:academicYearId/class/:classId', {
		templateUrl: "sams/components/enquiry/class_enquiry_list.html"
	}).
	
	when('/enquiry/list/statuswise/:academicYearId', {
		templateUrl: "sams/components/enquiry/statuswise_enquiry_count.html"
	}).
	
	when('/enquiry/list/:academicYearId/status/:statusId', {
		templateUrl: "sams/components/enquiry/statuswise_enquiry_list.html"
	}).
	
	when('/enquiry/new', {
		templateUrl: "sams/components/enquiry/new_enquiry.html"
	}).
	
	when('/enquiry/:enquiryId', {
		templateUrl: "sams/components/enquiry/enquiry_details.html"
	}).
	
	when('/enquiry/edit/:enquiryId', {
		templateUrl: "sams/components/enquiry/new_enquiry.html"
	}).
	
	when('/enquiry/:enquiryId/followups', {
		templateUrl: "sams/components/enquiry/enquiry_followup_list.html"
	}).
	
	when('/enquiry/:enquiryId/newfollowup', {
		templateUrl: "sams/components/enquiry/enquiry_followup.html"
	}).
	
	when('/enquiry/:enquiryId/followup/:followupId', {
		templateUrl: "sams/components/enquiry/enquiry_followup.html"
	}).
	
	when('/enquiry/followups/:academicYearId/:day', {
		templateUrl: "sams/components/enquiry/enquiry_upcoming_followups.html"
	}).
	when('/enquiry/followups/:academicYearId/:from/:to', {
		templateUrl: "sams/components/enquiry/enquiry_upcoming_followups.html"
	})
	
	
	
	
	
	
	
	
	
	
}])

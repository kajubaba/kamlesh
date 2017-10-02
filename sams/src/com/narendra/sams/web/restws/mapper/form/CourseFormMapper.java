package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.web.restws.form.CourseForm;

public class CourseFormMapper {
    public static Course prepareCourseDomain(CourseForm courseForm) {
        if (courseForm == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseForm.getId());
        course.setName(courseForm.getName());
        course.setDisplayName(courseForm.getDisplayName());
        if (courseForm.getDuration() != null) {
            course.setDuration(courseForm.getDuration());
        }
        if (courseForm.getAffiliationAuthorityId() == null) {
            return course;
        }
        AffiliationAuthority affiliationAuthority = new AffiliationAuthority();
        affiliationAuthority.setId(courseForm.getAffiliationAuthorityId());
        course.setAffiliatedTo(affiliationAuthority);
        return course;
    }
}

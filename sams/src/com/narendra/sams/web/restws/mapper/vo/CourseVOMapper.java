package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.CourseVO;
import java.util.ArrayList;
import java.util.List;

public class CourseVOMapper {
    public static List<CourseVO> prepareCourseVOs(List<Course> courses) {
        List<CourseVO> courseVOs = new ArrayList();
        if (!(courses == null || courses.isEmpty())) {
            for (Course course : courses) {
                courseVOs.add(prepareCourseVO(course));
            }
        }
        return courseVOs;
    }

    public static CourseVO prepareCourseVO(Course course) {
        if (course == null) {
            return null;
        }
        CourseVO courseVO = new CourseVO();
        courseVO.setId(course.getId());
        courseVO.setName(course.getName());
        courseVO.setDisplayName(course.getDisplayName());
        courseVO.setDuration(course.getDuration());
        if (course.getAffiliatedTo() != null) {
            courseVO.setAffiliationAuthorityId(course.getAffiliatedTo().getId());
            courseVO.setAffiliationAuthority(course.getAffiliatedTo().getDisplayName());
        }
        if (course.getCreatedBy() != null) {
            courseVO.setCreatedBy(course.getCreatedBy().getFullName());
        }
        if (course.getCreatedDate() != null) {
            courseVO.setCreatedOn(DateUtil.formatDate(course.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (course.getModifiedBy() != null) {
            courseVO.setModifiedBy(course.getModifiedBy().getFullName());
        }
        if (course.getModifiedDate() == null) {
            return courseVO;
        }
        courseVO.setModifiedOn(DateUtil.formatDate(course.getModifiedDate(), "dd-MMM-yyyy hh:mm a"));
        return courseVO;
    }
}

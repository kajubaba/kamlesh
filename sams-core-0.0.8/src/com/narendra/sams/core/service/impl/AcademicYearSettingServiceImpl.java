package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicYearSettingDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.CourseYearType;
import com.narendra.sams.core.service.AcademicYearSettingService;
import com.narendra.sams.core.service.CourseService;
import com.narendra.sams.core.service.CourseYearTypeService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AcademicYearSettingServiceImpl implements AcademicYearSettingService {
    private AcademicYearSettingDAO academicYearSettingDAO;
    private CourseService courseService;
    private CourseYearTypeService courseYearTypeService;

    public AcademicYearSettingDAO getAcademicYearSettingDAO() {
        return this.academicYearSettingDAO;
    }

    public void setAcademicYearSettingDAO(AcademicYearSettingDAO academicYearSettingDAO) {
        this.academicYearSettingDAO = academicYearSettingDAO;
    }

    public CourseService getCourseService() {
        return this.courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseYearTypeService getCourseYearTypeService() {
        return this.courseYearTypeService;
    }

    public List<AcademicYearCourse> getAcademicYearCourses(Long academicYearId) {
        return this.academicYearSettingDAO.getAcademicYearCourses(academicYearId);
    }

    public void setCourseYearTypeService(CourseYearTypeService courseYearTypeService) {
        this.courseYearTypeService = courseYearTypeService;
    }

    public List<AcademicYearCourse> getAcademicYearCourses(Long academicYearId, Long affiliationAuthorityId) {
        return this.academicYearSettingDAO.getAcademicYearCourses(academicYearId, affiliationAuthorityId);
    }

    public List<Course> getActiveCourses(Long academicYearId, Long affiliationAuthorityId) {
        return this.academicYearSettingDAO.getActiveCourses(academicYearId, affiliationAuthorityId);
    }

    public List<CourseYearSetting> getAllCourseYearSettings(Long courseId, Long academicYearId) {
        return this.academicYearSettingDAO.getAllCourseYearSettings(courseId, academicYearId);
    }

    public void removeCourseFromAcademicYear(Long academicYearCourseId) {
        this.academicYearSettingDAO.removeCourseFromAcademicYear(academicYearCourseId);
    }

    public List<Course> getNotAddedCourses(Long academicYearId, Long affiliationAuthorityId) {
        return this.academicYearSettingDAO.getNotAddedCourses(academicYearId, affiliationAuthorityId);
    }

    public List<Course> getNotAddedCourses(Long academicYearId) {
        if (academicYearId == null) {
            return null;
        }
        return this.academicYearSettingDAO.getNotAddedCourses(academicYearId);
    }

    public void addCoursesInAcademicYearWithDefaultSettings(Long academicYearId, Collection<Long> courseIds) {
        if (academicYearId != null && courseIds != null) {
            List<Course> courses = this.courseService.getCourses(courseIds);
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(academicYearId);
            CourseYearType courseYearType = new CourseYearType();
            courseYearType.setId(CourseYearType.TYPE_YEAR);
            courseYearType.setValue(CourseYearType.TYPE_YEAR);
            if (courses != null && !courses.isEmpty()) {
                List<AcademicYearCourse> academicYearCourses = new ArrayList();
                for (Course course : courses) {
                    AcademicYearCourse academicYearCourse = new AcademicYearCourse();
                    academicYearCourse.setAcademicYear(academicYear);
                    academicYearCourse.setCourse(course);
                    academicYearCourse.setActive(Boolean.TRUE);
                    if (!(course.getCourseYears() == null || course.getCourseYears().isEmpty())) {
                        Set<CourseYearSetting> courseYearSettings = new HashSet();
                        for (CourseYear courseYear : course.getCourseYears()) {
                            CourseYearSetting courseYearSetting = new CourseYearSetting();
                            courseYearSetting.setAcademicYear(academicYear);
                            courseYearSetting.setCourseYear(courseYear);
                            courseYearSetting.setActive(Boolean.TRUE);
                            courseYearSetting.setCourseYearType(courseYearType);
                            Set<AcademicYearClass> classes = new LinkedHashSet();
                            for (int i = 1; ((long) i) <= courseYearType.getValue().longValue(); i++) {
                                AcademicYearClass academicYearClass = new AcademicYearClass();
                                academicYearClass.setAcademicYear(academicYear);
                                academicYearClass.setCourseYear(courseYear);
                                academicYearClass.setActive(Boolean.TRUE);
                                academicYearClass.setName(Short.valueOf(Integer.valueOf(((courseYear.getName().shortValue() - 1) * 2) + i).shortValue()));
                                if (CourseYearType.TYPE_SEMESTER.equals(courseYearSetting.getCourseYearType().getId())) {
                                    academicYearClass.setDisplayName(new StringBuilder(String.valueOf(courseYearSetting.getCourseYear().getCourse().getDisplayName())).append(" , ").append(academicYearClass.getName()).append(" Sem.").toString());
                                } else if (CourseYearType.TYPE_YEAR.equals(courseYearSetting.getCourseYearType().getId())) {
                                    if (courseYearSetting.getCourseYear().getCourse().getDuration().shortValue() > (short) 1) {
                                        academicYearClass.setDisplayName(new StringBuilder(String.valueOf(courseYearSetting.getCourseYear().getCourse().getDisplayName())).append(" , ").append(academicYearClass.getName()).append(" Yr.").toString());
                                    } else {
                                        academicYearClass.setDisplayName(courseYearSetting.getCourseYear().getCourse().getDisplayName());
                                    }
                                }
                                classes.add(academicYearClass);
                            }
                            courseYearSetting.setAcademicYearClasses(classes);
                            courseYearSettings.add(courseYearSetting);
                        }
                        academicYearCourse.setCourseYearSettings(courseYearSettings);
                    }
                    academicYearCourses.add(academicYearCourse);
                }
                this.academicYearSettingDAO.addCoursesInAcademicYear(academicYearCourses);
            }
        }
    }

    public CourseYearSetting getCourseYearSetting(Long courseYearSettingId) {
        return this.academicYearSettingDAO.getCourseYearSetting(courseYearSettingId);
    }

    public AcademicYearClass loadAcademicYearClass(Long academicYearClassId) {
        return this.academicYearSettingDAO.loadAcademicYearClass(academicYearClassId);
    }

    public void saveCourseYearSetting(Long courseYearSettingId, Boolean status, Long courseYearTypeId, Long intake) {
        CourseYearSetting courseYearSetting = getAcademicYearSettingDAO().getCourseYearSetting(courseYearSettingId);
        courseYearSetting.setActive(status);
        courseYearSetting.setIntake(intake);
        if (!courseYearSetting.getCourseYearType().getId().equals(courseYearTypeId)) {
            CourseYearType courseYearType = this.courseYearTypeService.loadCourseYearType(courseYearTypeId);
            courseYearSetting.setCourseYearType(courseYearType);
            courseYearSetting.getAcademicYearClasses().clear();
            for (int i = 1; ((long) i) <= courseYearType.getValue().longValue(); i++) {
                AcademicYearClass academicYearClass = new AcademicYearClass();
                academicYearClass.setAcademicYear(courseYearSetting.getAcademicYear());
                academicYearClass.setCourseYear(courseYearSetting.getCourseYear());
                academicYearClass.setActive(status);
                academicYearClass.setName(Short.valueOf(Integer.valueOf(((courseYearSetting.getCourseYear().getName().shortValue() - 1) * 2) + i).shortValue()));
                if (CourseYearType.TYPE_SEMESTER.equals(courseYearSetting.getCourseYearType().getId())) {
                    academicYearClass.setDisplayName(new StringBuilder(String.valueOf(courseYearSetting.getCourseYear().getCourse().getDisplayName())).append(" , ").append(academicYearClass.getName()).append(" Sem.").toString());
                } else if (CourseYearType.TYPE_YEAR.equals(courseYearSetting.getCourseYearType().getId())) {
                    if (courseYearSetting.getCourseYear().getCourse().getDuration().shortValue() > (short) 1) {
                        academicYearClass.setDisplayName(new StringBuilder(String.valueOf(courseYearSetting.getCourseYear().getCourse().getDisplayName())).append(" , ").append(academicYearClass.getName()).append(" Yr.").toString());
                    } else {
                        academicYearClass.setDisplayName(courseYearSetting.getCourseYear().getCourse().getDisplayName());
                    }
                }
                courseYearSetting.getAcademicYearClasses().add(academicYearClass);
            }
        }
    }

    public List<CourseYearSetting> getActiveCourseYearSettings(Long academicYearid) {
        return this.academicYearSettingDAO.getActiveCourseYearSettings(academicYearid);
    }

    public List<CourseYearSetting> getInvidualCourseSettings(Long academicYearId, Long affiliationAuthorityId) {
        return this.academicYearSettingDAO.getInvidualCourseSettings(academicYearId, affiliationAuthorityId);
    }
}

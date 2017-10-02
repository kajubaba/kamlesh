package com.narendra.sams.web.restws.transportation.mapper.vo;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.web.restws.transportation.vo.RouteStudentVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class RouteStudentVOMapper {
    public static List<RouteStudentVO> prepareRouteStudentVO(List<RouteStudent> routeStudents, Long academicYearId) {
        List<RouteStudentVO> routeStudentVOs = new ArrayList();
        if (!(routeStudents == null || routeStudents.isEmpty())) {
            for (RouteStudent routeStudent : routeStudents) {
                RouteStudentVO routeStudentVO = new RouteStudentVO();
                routeStudentVO.setRouteStudentId(routeStudent.getId());
                routeStudentVO.setStudentDBId(routeStudent.getStudent().getId());
                routeStudentVO.setStudentId(routeStudent.getStudent().getStudentId());
                routeStudentVO.setStudentName(routeStudent.getStudent().getFullName());
                routeStudentVO.setStudentGender(routeStudent.getStudent().getGender());
                routeStudentVO.setStudentStatus(routeStudent.getStudent().getStudentStatus().getName());
                routeStudentVO.setFatherName(routeStudent.getStudent().getFatherName());
                routeStudentVO.setNameInOtherLanguage(routeStudent.getStudent().getTranslatedStudentName());
                routeStudentVO.setFatherNameInOtherLanguage(routeStudent.getStudent().getTranslatedFatherName());
                routeStudentVO.setAddressInOtherLanguage(routeStudent.getStudent().getTranslatedCity());
                ClassHistory studentClass = routeStudent.getStudent().getActiveClassHistory(academicYearId);
                if (studentClass != null) {
                    routeStudentVO.setStudentClass(StudentInformationUtil.getClassName(studentClass.getAcademicYearClass()));
                }
                if (studentClass.getBusStop() != null) {
                    routeStudentVO.setBusStop(studentClass.getBusStop().getName());
                    routeStudentVO.setBusStopNameInOtherLanguage(studentClass.getBusStop().getNameInOtherLang());
                }
                if (routeStudent.getPickupPoint() != null) {
                    routeStudentVO.setPickupPointId(routeStudent.getPickupPointId());
                    routeStudentVO.setPickupPoint(routeStudent.getPickupPoint());
                }
                if (routeStudent.getDropPoint() != null) {
                    routeStudentVO.setDropPointid(routeStudent.getDropPointId());
                    routeStudentVO.setDropPoint(routeStudent.getDropPoint());
                }
                routeStudentVO.setStudentContact(StudentInformationUtil.prepareContactNo(routeStudent.getStudent().getPhone1(), routeStudent.getStudent().getPhone2()));
                routeStudentVO.setFatherContact(StudentInformationUtil.prepareContactNo(routeStudent.getStudent().getFatherContact1(), routeStudent.getStudent().getFatherContact2()));
                routeStudentVO.setMotherContact(StudentInformationUtil.prepareContactNo(routeStudent.getStudent().getMotherContact1(), routeStudent.getStudent().getMotherContact2()));
                routeStudentVOs.add(routeStudentVO);
            }
        }
        return routeStudentVOs;
    }
}

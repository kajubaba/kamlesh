package com.narendra.sams.web.restws.transportation.helper;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.web.restws.transportation.vo.RouteStudentVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class RoutePlanDomainToVOConverter {
    public static List<RouteStudentVO> convertRouteStudentForDisplay(List<RouteStudent> routeStudents, Long academicYearId) {
        List<RouteStudentVO> routeStudentVOs = new ArrayList();
        if (routeStudents != null) {
            for (RouteStudent routeStudent : routeStudents) {
                RouteStudentVO routeStudentVO = new RouteStudentVO();
                routeStudentVO.setRouteStudentId(routeStudent.getId());
                routeStudentVO.setStudentDBId(routeStudent.getStudent().getId());
                routeStudentVO.setStudentId(routeStudent.getStudent().getStudentId());
                routeStudentVO.setStudentName(routeStudent.getStudent().getFullName());
                routeStudentVO.setStudentGender(routeStudent.getStudent().getGender());
                routeStudentVO.setStudentStatus(routeStudent.getStudent().getStudentStatus().getName());
                ClassHistory studentClass = routeStudent.getStudent().getActiveClassHistory(academicYearId);
                if (studentClass != null) {
                    routeStudentVO.setStudentClass(StudentInformationUtil.getClassName(studentClass.getAcademicYearClass()));
                }
                if (routeStudent.getPickupPoint() != null) {
                    routeStudentVO.setPickupPointId(routeStudent.getPickupPointId());
                    routeStudentVO.setPickupPoint(routeStudent.getPickupPoint());
                }
                if (routeStudent.getDropPoint() != null) {
                    routeStudentVO.setDropPointid(routeStudent.getDropPointId());
                    routeStudentVO.setDropPoint(routeStudent.getDropPoint());
                }
                routeStudentVOs.add(routeStudentVO);
            }
        }
        return routeStudentVOs;
    }
}
